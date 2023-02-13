package app;

import java.io.File;
import java.io.IOException;

public class FileService {
  public boolean createDir(String dirName) {
    File directory = new File(dirName);
    Boolean createdDir = directory.mkdir();

    return createdDir;
  }

  public boolean createFile(String dirName, String fileName) throws IOException {
    File file = new File(dirName + File.separator + fileName);
    Boolean createdFile = file.createNewFile();

    return createdFile;
  }

  public void listFiles(String dirName) throws IOException {
    File directory = new File(dirName);

    if (directory.exists()) {
      File dirFileList[] = directory.listFiles();

      for (File file : dirFileList) {
        System.out.println("Canonical Path " + file.getCanonicalPath());
        System.out.println("Get Path " + file.getPath());
      }
    } else {
      System.out.println("The directory you entered does not exist.");
    }
  }
}
