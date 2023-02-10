package main.java.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ProfileService {
  public String dirName = "day08data";
  public String fileName = "profile.txt";
  public String fullPath = dirName + File.separator + fileName;

  public void readFile() throws FileNotFoundException, IOException {
    File file = new File(fullPath);
    FileReader fr = new FileReader(file);
    BufferedReader br = new BufferedReader(fr);

    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the word you want to find. ");
    String word = scanner.nextLine();

    String line = "";
    String[] buffer;
    int wordCount = 0;

    while ((line = br.readLine()) != null) {
      line = line.replace(",", "");
      line = line.replace(".", "");

      buffer = line.split(" ");
      for (String s : buffer) {
        System.out.println(s);
        if (s.equalsIgnoreCase(word)) {
          wordCount++;
        }
      }
    }

    if (wordCount == 0) {
      System.out.println("The word does not appear in the text. ");
    } else {
      System.out.printf("The word %s appears %d times. \n", word, wordCount);
    }

    br.close();
    fr.close();
  }
}
