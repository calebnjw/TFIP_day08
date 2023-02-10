package main.java.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IdiomService {

  public List<String> readFile(String fullPath) throws IOException {
    List<String> idiomList = new ArrayList<String>(); // list to store idioms from file
    String line = ""; // for readlines

    File file = new File(fullPath); // get the file from path
    FileReader fr = new FileReader(file); // read the file
    BufferedReader br = new BufferedReader(fr); // read file by lines

    while ((line = br.readLine()) != null) {
      idiomList.add(line);
    }

    br.close();
    fr.close();

    printDivider();
    System.out.println("Idioms have been read! ");
    printDivider();

    return idiomList;
  }

  public void showAll(List<String> idioms) {
    if (idioms.size() < 1) {
      tryAgain();
      return;
    }
    printDivider();
    for (String s : idioms) {
      System.out.println(s);
    }
    printDivider();
  }

  public void getRandom(List<String> idioms) {
    if (idioms.size() < 1) {
      tryAgain();
      return;
    }

    Random random = new Random();
    int randomIndex = random.nextInt(idioms.size());
    String line = idioms.get(randomIndex);

    printDivider();
    System.out.println(line);
    printDivider();
  }

  public static void tryAgain() {
    System.out.println("There are no idioms right now. ");
    System.out.println("Please read the idioms file first. ");
  }

  public static void printDivider() {
    System.out.println("——————————————————————————————————————————————————————————————————————————————————————————");
  }
}
