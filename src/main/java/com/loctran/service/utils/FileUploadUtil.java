package com.loctran.service.utils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

  public static String saveFile(String id, String fileName,
      MultipartFile multipartFile) throws IOException {
    Path uploadPath = Paths.get("src/main/resources/static/upload/" +id);
    if (!Files.exists(uploadPath)) {
      Files.createDirectories(uploadPath);
      System.out.println("file not exist");
    }

    try (InputStream inputStream = multipartFile.getInputStream()) {
      Path filePath = uploadPath.resolve(fileName);
      Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
      return id + "/" +fileName;

    } catch (IOException ex) {
      throw new IOException("Could not save file: " + fileName, ex);
    }
  }

  public static void cleanDir(String dir) {
    Path dirPath = Paths.get(dir);

    try {
      Files.list(dirPath).forEach(file -> {
        if (!Files.isDirectory(file)) {
          try {
            Files.delete(file);
          } catch (IOException ex) {
            System.out.println("Could not delete file: " + file);
          }
        }
      });
    } catch (IOException ex) {
      System.out.println("Could not list directory: " + dirPath);
    }
  }
}