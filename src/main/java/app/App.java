package app;

import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.java.app.CSVService;
import main.java.app.Employee;
import main.java.app.EmployeeService;
import main.java.app.FileService;
import main.java.app.IdiomService;
import main.java.app.ProfileService;

/**
 * Hello world!
 *
 */
public final class App {
  private static String dirName = "day08data";
  private static String CSVFileName = "data.txt";
  private static String idiomFileName = "idioms.txt";
  private static String CSVFullPath = dirName + File.separator + CSVFileName;
  private static String idiomFullPath = dirName + File.separator + idiomFileName;

  private App() {
  };

  public static void main(String[] args) {
    if (args.length > 0) {
      if (args[0].equals("args")) {
        System.exit(0);
      }
    }

    FileService fs = new FileService();
    try {
      Boolean createdDirectory = fs.createDir(dirName);
      if (createdDirectory) {
        System.out.println("Directory created: " + dirName);
      } else {
        System.out.println("Directory already exists: " + dirName);
      }

      Boolean createdFile = fs.createFile(dirName, CSVFileName);
      if (createdFile) {
        System.out.println("File created: " + CSVFileName);
      } else {
        System.out.println("File already exists: " + CSVFileName);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    Console console = System.console();
    String consoleInput = "";

    IdiomService is = new IdiomService();
    List<String> idioms = new ArrayList<String>();

    ProfileService ps = new ProfileService();

    while (!consoleInput.equalsIgnoreCase("Q")) {
      displayMenu();
      consoleInput = console.readLine("Which action do you want to perform? ");

      try {
        switch (consoleInput) {
          case "1":
            CSVExample();
            break;
          case "2":
            idioms = is.readFile(idiomFullPath);
            break;
          case "3":
            is.getRandom(idioms);
            break;
          case "4":
            is.showAll(idioms);
            break;
          case "5":
            ps.readFile();
            break;
          case "Q":
            System.out.println("Goodbye. ");
            break;
          default:
            break;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void CSVExample() throws IOException {
    try {
      FileOutputStream fos = new FileOutputStream(CSVFullPath);

      // WRITING TO FILE
      // for (int i = 0; i < 30; i++) {
      // byte[] number = Integer.toString(i).getBytes(); // convert numbers to byte
      // array

      // fos.write(number); // fos needs bytes to write from array into the file
      // fos.write('\n');
      // }

      EmployeeService es = new EmployeeService();
      List<Employee> originalEmployeeList = es.generateEmployees();

      System.out.println("ORIGINAL LIST");
      for (Employee e : originalEmployeeList) {
        System.out.println(e);
      }

      CSVService cs = new CSVService();
      cs.writeToCSV(originalEmployeeList, CSVFullPath);

      fos.flush(); // clear the buffer
      fos.close(); // close the file
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      CSVService cs = new CSVService();
      List<Employee> newEmployeeList = cs.readFromCSV(CSVFullPath);

      System.out.println("NEW LIST");
      for (Employee e : newEmployeeList) {
        System.out.println(e);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void displayMenu() {
    message("Welcome to my app.");
    message("——————————————————————————————————————————————————————————————————————————————————————————");
    message("1: CSV read and write test.");
    message("2: Read idioms.");
    message("3: Pick a random idiom.");
    message("4: List all idioms.");
    message("5: Count given word in a profile.");
    message("Q: Quit the program.");
    message("——————————————————————————————————————————————————————————————————————————————————————————");
  }

  public static void message(String text) {
    System.out.println(text);
  }
}
