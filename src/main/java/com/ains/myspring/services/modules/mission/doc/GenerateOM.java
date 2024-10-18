package com.ains.myspring.services.modules.mission.doc;

import com.ains.myspring.models.modules.equipe.Detailequipe;
import com.ains.myspring.models.modules.mission.Ordermission;
import com.ains.myspring.services.modules.equipe.DetailequipeService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
import java.io.IOException;
import java.nio.file.FileSystems;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Path;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.docx4j.convert.out.pdf.PdfConversion;
import org.docx4j.convert.out.pdf.viaXSLFO.Conversion;
import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;

@Service
public class GenerateOM {
  @Autowired
  private DetailequipeService servicedetailequipe;

  public String Ordermission(Ordermission ordermission) throws Exception {
    String src = "src/main/resources/static/doc/Modelfinal.docx";
    String rendre = "";
    String object = "";
    String qrCodePath = "src/main/resources/static/doc/example-1.png";
    String dest = "src/main/resources/static/" + ordermission.getNumeroserie() + ".docx";
    String outputPdfPath = "src/main/resources/static/" + ordermission.getNumeroserie() + ".pdf";
    Date now = new Date(System.currentTimeMillis());
    SimpleDateFormat yearMonthFormat = new SimpleDateFormat("MM/yyyy");
    String yearMonth = yearMonthFormat.format(now);
    String qrCodeText = "http://localhost:5173/feedback/" + ordermission.getNumeroserie();
    if (ordermission.getIdtypeordermission() == 1) {
      rendre = ordermission.getNomsociete() + "  (" + ordermission.getLieu_controle() + ")";
      object = "DESCENTE";
    } else if (ordermission.getIdtypeordermission() == 2) {
      object = "COLLECTE ECONOMIQUE";
      rendre = ordermission.getLieu_controle();
    } else {
      object = "AUTRE SUIVI";
      rendre = ordermission.getLieu_controle();
    }
    try {
      generateQRCodeImage(qrCodeText, 100, 100, qrCodePath);
    } catch (WriterException | IOException e) {
      e.printStackTrace();
    }
    try (FileInputStream fis = new FileInputStream(src);
        XWPFDocument document = new XWPFDocument(fis);
        FileOutputStream fos = new FileOutputStream(dest)) {
      for (XWPFParagraph paragraph : document.getParagraphs()) {
        for (XWPFRun run : paragraph.getRuns()) {
          String text = run.getText(0);
          if (text != null) {
            text = text.replace("{numero}", ordermission.getNumeroserie())
                .replace("{date}", yearMonth)
                .replace("{debutmission}", "" + ordermission.getDatedescente());
            run.setText(text, 0);
          }
          if (text != null && text.contains("{object}")) {
            text = text.replace("{object}", object);
            run.setText(text, 0);
          }
          if (text != null && text.contains("{lieu}")) {
            text = text.replace("{lieu}", rendre);
            run.setText(text, 0);
          }
          if (text != null && text.contains("{motif}")) {
            text = text.replace("{motif}", ordermission.getMotifs());
            run.setText(text, 0);
          }
          if (text != null && text.contains("{region}")) {
            text = text.replace("{region}", ordermission.getRegion().getNameregion());
            run.setText(text, 0);
          }
          if (text != null && text.contains("{district}")) {
            text = text.replace("{district}", ordermission.getNomdistrict());
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
      document.write(fos);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    File fileDocx = new File(dest);
    if (fileDocx.exists()) {
      generatePdf(dest, outputPdfPath);
      return ordermission.getNumeroserie() + ".pdf";

    } else {
      System.err.println("Le fichier DOCX n'a pas été créé : " + dest);
    }
    return ordermission.getNumeroserie() + ".pdf";
  }

  @SuppressWarnings("deprecation")
  private void generatePdf(String file_docx, String outputPdfPath) {
    try (
        InputStream templateInputStream = new FileInputStream(file_docx);
        FileOutputStream os = new FileOutputStream(outputPdfPath)) {
      WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);
      PdfSettings pdfSettings = new PdfSettings();
      PdfConversion pdfConversion = new Conversion(wordMLPackage);
      pdfConversion.output(os, pdfSettings);
      os.flush();
      os.close();
    } catch (IOException | Docx4JException e) {
      e.printStackTrace();
    }
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
      setTextAndFormat(row1.getCell(1), detailequipe.get(i).getProfil().toLowerCase(),
          11,
          false);
      setTextAndFormat(row1.getCell(2), detailequipe.get(i).getMatricule(), 11, false);
    }
  }
}
