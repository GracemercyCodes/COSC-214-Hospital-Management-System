/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.*;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author gmrgk
 */
// Warning: Nurse is buggy, will fix
//Changes made: Program now asks if you are employed at the hospital or entering as a patient. I have left the patient case empty for my teammate to tackle
//Dates have been added. There is now a join date and an expiry date for employees, as well as a DOB parameter for the patient.
//
// An admin Role to over see schedules and pay
// Next Steps: Create a schedule that illustrates when a nurse technichian or doctor is available using isFree
// Next Next Steps: Once its successful with console, replace the system dialog with JOptionPane
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.*;

/**
 *
 * @author gmrgk
 */
public class Main {

    private static String[] NurseSL = {"CNA", "LPN", "RN"};
    private static String[] TechSL = {"Radiologic", "MRI", "Ultrasound"};
    private static String[] DocSL = {"Pediatrics", "Surgical", "Family"};

    private static LinkedList<Nurse> nurselist = new LinkedList<>();
    private static LinkedList<Tech> techlist = new LinkedList<>();
    private static LinkedList<Doc> doclist = new LinkedList<>();

    public static void main(String[] args) {

        // DEFAULT USERS
        nurselist.add(
                new Nurse("Usagi", 300678, NurseSL[0]));

        nurselist.add(
                new Nurse("Minako", 220878, NurseSL[2]));

        /*
        //alternate version that takes Local Date as  parameter
        employeelist.add(
                new Employee("Name", XXXXXX, employSL[X],
                        LocalDate.of(2020, 1, 10),
                        LocalDate.of(2030, 1, 10))
        );

        
         */
        techlist.add(new Tech("Misato", 860912, TechSL[1]));
        techlist.add(new Tech("Shinji", 630999, TechSL[0]));

        doclist.add(new Doc("Mamoru", 998877, DocSL[1]));
        doclist.add(new Doc("Seiya", 300776, DocSL[2]));

        Scanner scnr = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Are you an employee or patient? \b[Enter 1 for yes y/ 2 for no]");
            int choice = scnr.nextInt();
            switch (choice) {
                case 1://employee case
                    System.out.println("Omg you got a j*b");

                    System.out.println("\nDo you already exist in the system?");
                    System.out.println("1: Yes \n2: No\n0: Exit");
                    int existsChoice = scnr.nextInt();
                    scnr.nextLine();

                    switch (existsChoice) {

                        // USER EXISTS CASE
                        case 1:
                            System.out.println("Enter your ID:");
                            int checkID = scnr.nextInt();

                            boolean found = false;

                            // check nurse list
                            for (Nurse n : nurselist) {
                                if (n.ID == checkID) {
                                    System.out.println("Found Nurse:");
                                    System.out.println("Name: " + n.name + "\nSpeciality: " + n.spec);
                                    found = true;
                                }
                            }

                            // check tech list
                            for (Tech t : techlist) {
                                if (t.ID == checkID) {
                                    System.out.println("Found Technician:");
                                    System.out.println("Name: " + t.name + "\nSpeciality: " + t.spec);
                                    found = true;
                                }
                            }

                            // check doc list
                            for (Doc d : doclist) {
                                if (d.ID == checkID) {
                                    System.out.println("Found Doctor:");
                                    System.out.println("Dr. " + d.name + "\nSpeciality: " + d.spec);
                                    found = true;
                                }
                            }

                            if (!found) {
                                System.out.println("No user found with ID " + checkID);
                            }
                            break;

                        // USER DOES NOT EXIST → Reggister new employee
                        case 2:

                            System.out.println("Select your profile type to register:");
                            System.out.println("1: Doctor");
                            System.out.println("2: Nurse");
                            System.out.println("3: Technician");
                            int typeChoice = scnr.nextInt();
                            scnr.nextLine();

                            switch (typeChoice) {

                                // Doctor
                                case 1:
                                    Doc doc = new Doc("", 0, "");

                                    System.out.println("Enter your name");
                                    doc.name = scnr.nextLine();

                                    System.out.println("Enter your ID [6 digits]");
                                    doc.ID = scnr.nextInt();
                                    while (doc.ID < 100000 || doc.ID > 999999) {
                                        System.out.println("Invalid ID");
                                        doc.ID = scnr.nextInt();
                                    }

                                    System.out.println("Select your speciality");
                                    System.out.println("1: " + DocSL[0] + "\n2: " + DocSL[1] + "\n3: " + DocSL[2]);
                                    int docChoice = scnr.nextInt();
                                    doc.spec = DocSL[docChoice - 1];

                                    doclist.add(doc);

                                    System.out.println("\nAdded Doctor:");
                                    System.out.println("Dr. " + doc.name + "\nID: " + doc.ID + "\nSpeciality: " + doc.spec);
                                    break;

                                // Nurse
                                case 2:
                                    Nurse nurse = new Nurse("", 0, "");

                                    System.out.println("Enter your name");
                                    nurse.name = scnr.nextLine();

                                    System.out.println("Enter your ID [6 digits]");
                                    nurse.ID = scnr.nextInt();
                                    while (nurse.ID < 100000 || nurse.ID > 999999) {
                                        System.out.println("Invalid ID");
                                        nurse.ID = scnr.nextInt();
                                    }

                                    System.out.println("Select your speciality");
                                    System.out.println("1: " + NurseSL[0] + "\n2: " + NurseSL[1] + "\n3: " + NurseSL[2]);
                                    int nurseChoice = scnr.nextInt();
                                    nurse.spec = NurseSL[nurseChoice - 1];

                                    nurselist.add(nurse);

                                    System.out.println("\nAdded Nurse:");
                                    System.out.println("Name: " + nurse.name + "\nID: " + nurse.ID + "\nSpeciality: " + nurse.spec);
                                    break;

                                // Technichian
                                case 3:
                                    Tech tech = new Tech("", 0, "");

                                    System.out.println("Enter your name");
                                    tech.name = scnr.nextLine();

                                    System.out.println("Enter your ID [6 digits]");
                                    tech.ID = scnr.nextInt();
                                    while (tech.ID < 100000 || tech.ID > 999999) {
                                        System.out.println("Invalid ID");
                                        tech.ID = scnr.nextInt();
                                    }

                                    System.out.println("Select your speciality");
                                    System.out.println("1: " + TechSL[0] + "\n2: " + TechSL[1] + "\n3: " + TechSL[2]);
                                    int techChoice = scnr.nextInt();
                                    tech.spec = TechSL[techChoice - 1];

                                    techlist.add(tech);

                                    System.out.println("\nAdded Technician:");
                                    System.out.println("Name: " + tech.name + "\nID: " + tech.ID + "\nSpeciality: " + tech.spec);
                                    break;
                            }
                            break;

                        // EXIT
                        case 0:
                            exit = true;
                            System.out.println("Exiting...");
                            break;

                        default:
                            System.out.println("Invalid choice");
                    }

                    break;
                case 2: // Patient Case
                    System.out.println("Omg ur sick");
                    break;

            }

        }
    }

    // Custom Object classes
    static class Nurse {

        String name;
        int ID;
        String spec;
        LocalDate joinDate;
        LocalDate expDate;

        boolean isFree = true; //boolean to determine if they are free or not

        Nurse(String name, int ID, String spec) {
            this.name = name;
            this.ID = ID;
            this.spec = spec;

        }
    }

    static class Tech {

        String name;
        int ID;
        String spec;

        LocalDate joinDate;
        LocalDate expDate;

        boolean isFree = true;

        Tech(String name, int ID, String spec) {
            this.name = name;
            this.ID = ID;
            this.spec = spec;
        }
    }

    static class Doc {

        String name;
        int ID;
        String spec;

        LocalDate joinDate;
        LocalDate expDate;

        boolean isFree = true;

        Doc(String name, int ID, String spec) {
            this.name = name;
            this.ID = ID;
            this.spec = spec;
        }
    }

    static class Patient {

        //Store and retrieve patient details such as name, age, diagnosis, treatment history, etc.
        String name;
        int age;
        String diagnosis;
        float priority;

        LocalDate dateOfBirth;

        Patient(String name, int age, String diagnosis, float priority, LocalDate dateOfBirth) {
            this.name = name;
            this.age = age;
            this.diagnosis = diagnosis;
            this.priority = priority;
            this.dateOfBirth = dateOfBirth;
        }
    }

    //dater 
    public static LocalDate getDateInput(Scanner scnr, String label) {
        System.out.println("Enter " + label + " (YYYY MM DD):");
        int y = scnr.nextInt();
        int m = scnr.nextInt();
        int d = scnr.nextInt();
        scnr.nextLine();
        return LocalDate.of(y, m, d);
    }

}

/*
Nurse Type will include
String Name, int ID (possibly 6 digit), String Speciality, Tuple Join_Date, Tuple Exp_Date
Nurse Speciality List (NurseSL): CNA, LPN,RN
For now I'm using only 3 specialties per type, i can add the rest later

(Actual NSL: CNA, LPN,RN,Admin, L&D, Pediatrics, Trauma, NP, Surgery)
[What the nurse lists stand for:]
CNA - Certified nursing assistant
LPN - Licensed Practitioner nurse
RN - Registered Nurse
NP nurse practitioner
L&D Labor and delivery

Tech Type will include
String Name, int ID (possibly 6 digit), String Speciality, Tuple Join_Date, Tuple Exp_Date
Tech specialty list(TechSL): Radiologic, MRI, Ultrasound
(Actual TSL: Radiologic, MRI, Ultrasound, Nuclear_Med, Cardio, EEG, Surgical)

Haven't done Doc Type (for doctors)
in the future the Speciality list arrays may be replaced with hashtables
Doc Speciality List
Pediatrics, Surgery, Family, Psychiatry,Radiology

Boolean free will be used to determine when a nurse, tech or doctor is free
 */
