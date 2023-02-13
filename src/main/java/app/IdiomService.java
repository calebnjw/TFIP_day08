package app;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IdiomService implements Runnable {

  final Socket socket;

  private String fullPath;
  private List<String> idioms;
  private String idiom;

  public IdiomService(Socket s, String p) {
    this.socket = s;
    this.fullPath = p;
  }

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

  public String getRandom(List<String> idioms) {
    if (idioms.size() < 1) {
      tryAgain();
      return "";
    }

    Random random = new Random();
    int randomIndex = random.nextInt(idioms.size());
    String line = idioms.get(randomIndex);

    printDivider();
    System.out.println(line);
    printDivider();

    return (line);
  }

  public static void tryAgain() {
    System.out.println("There are no idioms right now. ");
    System.out.println("Please read the idioms file first. ");
  }

  public static void printDivider() {
    System.out.println("——————————————————————————————————————————————————————————————————————————————————————————");
  }

  @Override
  public void run() {
    try {
      InputStream is = this.socket.getInputStream();
      BufferedInputStream bis = new BufferedInputStream(is);
      DataInputStream dis = new DataInputStream(bis);

      OutputStream os = this.socket.getOutputStream();
      BufferedOutputStream bos = new BufferedOutputStream(os);
      DataOutputStream dos = new DataOutputStream(bos);

      idioms = this.readFile(fullPath); // read the file using previously declared function

      String command = "";
      while (!command.equalsIgnoreCase("quit")) { // command is blank, so will always run once
        command = dis.readUTF(); // if command is set to quit, then it will not loop the next time
        System.out.println(command);

        if (command.equalsIgnoreCase("get")) {
          idiom = this.getRandom(idioms);
        }

        dos.writeUTF(idiom);
        dos.flush();
      }

      dos.close();
      bos.close();
      os.close();

      dis.close();
      bis.close();
      is.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
