package com.ains.myspring.services.function.upload;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Service
public class FileUpload {
  @Value("${app.urlbytescale.url}")
  private String uploadUrl;
  @Value("${tokenpublic}")
  private String token;

  public String uploadFile(MultipartFile file)
      throws IOException, InterruptedException {
    try (CloseableHttpClient client = HttpClients.createDefault()) {
      Gson gson = new Gson();
      System.out.println("eto keyly oe : " + uploadUrl + "token : " + token);
      HttpPost post = new HttpPost(uploadUrl);
      post.addHeader("Authorization", "Bearer " + token);
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
      builder.addPart("file",
          new ByteArrayBody(file.getBytes(), ContentType.DEFAULT_BINARY, file.getOriginalFilename()));
      HttpEntity multipart = builder.build();
      post.setEntity(multipart);
      try (CloseableHttpResponse response = client.execute(post)) {
        HttpEntity responseEntity = response.getEntity();
        String responseBody = EntityUtils.toString(responseEntity);
        if (response.getStatusLine().getStatusCode() == 200) {
          JsonObject jsonResponse = gson.fromJson(responseBody, JsonObject.class);
          String fileUrl = jsonResponse.getAsJsonArray("files")
              .get(0)
              .getAsJsonObject()
              .get("fileUrl")
              .getAsString();
          return fileUrl;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
