package com.ains.myspring.services.modules.mission.doc;

import com.ains.myspring.models.modules.equipe.Detailequipe;
import com.ains.myspring.models.modules.lieu.District;
import com.ains.myspring.models.modules.mission.Autresuivi;
import com.ains.myspring.models.modules.mission.Collecte;
import com.ains.myspring.models.modules.mission.Enquete;
import com.ains.myspring.models.modules.mission.Ordermission;
import com.ains.myspring.services.modules.equipe.DetailequipeService;
import com.ains.myspring.services.modules.lieu.DistrictService;
import com.ains.myspring.services.modules.mission.AutresuiviService;
import com.ains.myspring.services.modules.mission.CollecteService;
import com.ains.myspring.services.modules.mission.EnqueteService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Path;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

@Service
public class GenerateOM {
  @Autowired
  private EnqueteService serviceEnquete;
  @Autowired
  private CollecteService serviceCollecte;
  @Autowired
  private AutresuiviService serviceAutresuivi;
  @Autowired
  private DistrictService serviceDistrict;
  @Autowired
  private DetailequipeService servicedetailequipe;

  public String Ordermission(Ordermission ordermission) throws Exception {
    String src = "src/main/resources/static/doc/";
    Enquete enquete = null;
    Collecte collecte = null;
    Autresuivi suivi = null;
    if (ordermission.getIdtypeordermission() == 1) {
      src += "Modele.docx";
      enquete = serviceEnquete.getEnqueteByOrdermission(ordermission.getIdordermission());
    } else if (ordermission.getIdtypeordermission() == 2) {
      src += "ModeleCollecte.docx";
      collecte = serviceCollecte.getCollecteByOrdermission(ordermission.getIdordermission());
    } else {
      src += "Autresuivi.docx";
      suivi = serviceAutresuivi.getAutresuiviByIdodremission(ordermission.getIdordermission());
    }
    String qrCodePath = "src/main/resources/static/doc/example-1.png";
    String dest = "src/main/resources/static/" + ordermission.getNumeroserie() + ".docx";
    Date now = new Date(System.currentTimeMillis());
    SimpleDateFormat yearMonthFormat = new SimpleDateFormat("MM/yyyy");
    String yearMonth = yearMonthFormat.format(now);
    String qrCodeText = "http://localhost:5173/feedback/" + ordermission.getNumeroserie();
    try {
      generateQRCodeImage(qrCodeText, 100, 100, qrCodePath);
    } catch (WriterException | IOException e) {
      e.printStackTrace();
    }
    try (FileInputStream fis = new FileInputStream(src);
        XWPFDocument document = new XWPFDocument(fis)) {
      for (XWPFParagraph paragraph : document.getParagraphs()) {
        for (XWPFRun run : paragraph.getRuns()) {
          String text = run.getText(0);
          if (text != null) {
            text = text.replace("{numero}", ordermission.getNumeroserie()).replace("{date}", yearMonth)
                .replace("{debutmission}", "" + ordermission.getDatedescente());
            run.setText(text, 0);
          }
          if (text != null && text.contains("{societe}")) {
            text = text.replace("{societe}", "" + enquete.getSociete().getNamesociete());
            run.setText(text, 0);
          }
          if (text != null && text.contains("{region}")) {
            District district = null;
            if (collecte != null) {
              district = serviceDistrict.getById(collecte.getIddistrict());
            }
            if (suivi != null) {
              district = serviceDistrict.getById(suivi.getIddistrict());
            }
            String lieu = district.getRegion().getNameregion() + " (" + district.getNameville() + ")";
            text = text.replace("{region}", lieu);
            run.setText(text, 0);
          }
          if (text != null && text.contains("{QR}")) {
            run.setText("", 0);
            paragraph.setAlignment(ParagraphAlignment.RIGHT);
            try (FileInputStream imageStream = new FileInputStream(qrCodePath)) {
              run.addPicture(imageStream, XWPFDocument.PICTURE_TYPE_PNG, qrCodePath,
                  Units.toEMU(100), Units.toEMU(100));
            } catch (InvalidFormatException e) {
              e.printStackTrace();
            }
          }
          if (text != null && text.contains("{Tableau}")) {
            run.setFontFamily("Arial");
            run.setText("", 0);
            XWPFTable table = document.insertNewTbl(paragraph.getCTP().newCursor());
            XWPFTableRow headerRow = table.getRow(0);
            setTextAndFormat(headerRow.getCell(0), "Agent de contrôle", 11, true);
            setTextAndFormat(headerRow.addNewTableCell(), "Qualité", 11, true);
            setTextAndFormat(headerRow.addNewTableCell(), "Matricule", 11, true);
            CreateTableaux(table, ordermission);
            break;
          }
        }
      }
      try (FileOutputStream fos = new FileOutputStream(dest)) {
        document.write(fos);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ordermission.getNumeroserie() + ".docx";
  }

  private void generateQRCodeImage(String text, int width, int height, String filePath)
      throws WriterException, IOException {
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    Map<EncodeHintType, Object> hints = new HashMap<>();
    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);

    Path path = FileSystems.getDefault().getPath(filePath);
    MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
  }

  private void setTextAndFormat(XWPFTableCell cell, String text, int fontSize, boolean isBold) {
    cell.removeParagraph(0);
    XWPFParagraph paragraph = cell.addParagraph();
    XWPFRun run = paragraph.createRun();
    run.setText(text);
    run.setFontSize(fontSize);
    run.setBold(isBold);
  }

  private void CreateTableaux(XWPFTable table, Ordermission ordermission) {
    List<Detailequipe> detailequipe = servicedetailequipe
        .getDetailEquipe(ordermission.getEquipe().getIdequipe());
    for (int i = 0; i < detailequipe.size(); i++) {
      XWPFTableRow row1 = table.createRow();
      setTextAndFormat(row1.getCell(0), detailequipe.get(i).getNameadministration(), 11,
          false);
      setTextAndFormat(row1.getCell(1), detailequipe.get(i).getProfil(),
          11,
          false);
      setTextAndFormat(row1.getCell(2), detailequipe.get(i).getMatricule(), 11, false);
    }
  }
}
