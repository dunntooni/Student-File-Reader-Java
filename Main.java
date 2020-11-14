/* 
This program will read a list from a file of students
and display the class correctly.
Provides a name, grade, age in years,
and whether or not they're graduating this year.
Also provides the option to add a new student
to the roster.
 */

 import java.io.File;
 import java.io.FileWriter;
 import java.io.BufferedWriter;
 import java.util.Scanner;
 import java.io.IOException;
 import java.util.ArrayList;

 public class Main {

    private static String promptFileName(Scanner myObj) {
        System.out.print("Please enter the file name: ");
        String fileName = myObj.nextLine();
        return fileName;
    }

    private static ArrayList<String> readFile(String fileName) {
        System.out.println("Reading File...");
        ArrayList<String> classList = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner reader = new Scanner(f);
            if (f.createNewFile()) {
                System.out.println("A new file was created: " + f.getName());
            }
            while (reader.hasNextLine()) {
                classList.add(reader.nextLine());
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return classList;
    }

    private static void displayClassList(ArrayList<String> classList) {
        System.out.println(" ");
        System.out.println("Class Info:\n");
        System.out.println("Student Name   Grade   Age   Graduating");
        System.out.println("------------   -----   ---   ----------");

        for (String x : classList) {
            String[] studentInfo = x.split(" ");
            System.out.printf("%-14s %s %8s %13s %n", studentInfo[0], studentInfo[1], studentInfo[2], studentInfo[3]);
        }
    }

    private static void writeFile(String fileName, Scanner myObj) {
        System.out.print("Please input the student's name: ");
        String name = myObj.nextLine();
        System.out.print("Please input the student's current grade out of 100: ");
        int grade = myObj.nextInt();
        System.out.print("Please input the student's birth year: ");
        int bYear = myObj.nextInt();
        System.out.print("Is the student set to graduate this year? (Y/N) ");
        myObj.nextLine();
        String graduating = myObj.nextLine();
        if (graduating.equals("Y") || graduating.equals("y") || graduating.equals("yes")
            || graduating.equals("YES") || graduating.equals("Yes")) {
                graduating = "Yes";
            } else if (graduating.equals("N") || graduating.equals("n") || graduating.equals("no")
                || graduating.equals("NO") || graduating.equals("No")) {
                    graduating = "No";
            } else {
                graduating = "Unknown";
            }
        String yearValue = String.valueOf(2020 - bYear);
        System.out.println("name " + name + " grade " + grade + " bYear " + bYear + " graduating " + graduating);
        try {
            FileWriter fWrite = new FileWriter(fileName, true);
            BufferedWriter bWrite = new BufferedWriter(fWrite);
            
            bWrite.write("\n" + name + " " + grade + " " + yearValue + " " + graduating);
            System.out.println("is this hit");
            bWrite.close();
        } catch (IOException ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }

    public static void main(String[] argv) {
        Scanner myObj = new Scanner(System.in);
        String fileName = promptFileName(myObj);
        ArrayList<String> classList = readFile(fileName);
        displayClassList(classList);
        System.out.print("Would you like to add a student to this roster? (Y/N) ");
        String writeFilePrompt = myObj.nextLine();
        if (writeFilePrompt.equals("y") || writeFilePrompt.equals("Y")
        || writeFilePrompt.equals("yes") || writeFilePrompt.equals("Yes")
        || writeFilePrompt.equals("YES")) {
            writeFile(fileName, myObj);
            classList = readFile(fileName);
            System.out.println("\nUpdating class list...");
            displayClassList(classList);
        }
    }
 }