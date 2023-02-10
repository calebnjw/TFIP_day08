package main.java.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVService {
  public static final String FILE_HEADER = "staffNo,fullName,department,role,email,salary";
  public static final String COMMA = ",";
  public static final String NEW_LINE = "\n";

  public void writeToCSV(List<Employee> employees, String fullPath) throws IOException {
    FileWriter fw = new FileWriter(fullPath);

    fw.append(FILE_HEADER);
    fw.append(NEW_LINE);
    // processing to write records, line by line
    for (Employee employee : employees) {
      fw.append(employee.toCSVString());
      fw.append(NEW_LINE);
    }

    fw.flush();
    fw.close();
  }

  public List<Employee> readFromCSV(String fullPath) throws IOException {
    FileReader fr = new FileReader(fullPath);
    BufferedReader br = new BufferedReader(fr);
    String line = "";
    List<Employee> employees = new ArrayList<Employee>();

    br.readLine();
    while ((line = br.readLine()) != null) {
      String[] employeeLine = line.split(COMMA);
      if (employeeLine.length > 0 && employeeLine.length == 6) {
        Employee e = new Employee(
            employeeLine[0],
            employeeLine[1],
            employeeLine[2],
            employeeLine[3],
            employeeLine[4],
            Integer.parseInt(employeeLine[5]));
        employees.add(e);
      }
    }

    br.close();
    fr.close();

    return employees;
  }
}
