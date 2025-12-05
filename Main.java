/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.*;

/**
 *
 * @author gmrgk
 */
// So far in this document i've come up with custom object classes for Nurses, Tech and doctors
// Implemented 3 specialties for each type as String Arrays.
// Created a way for the user to  registeraAs a Nurse, Tech or Doc)
//  Next steps: add some default users
//              Make sure user can add themselves to a doctor nurse or tech list
//              Then add an option to see if you exist in the list
// Next Steps: Create a schedule that illustrates when a nurse technichian or doctor is available using isFree
// Next Next Steps: Once its successful with console, replace the system dialog with JOptionPane
public class Main {

    private static String[] NurseSL = {"CNA", "LPN", "RN"};
    private static String[] TechSL = {"Radiologic", "MRI", "Ultrasound"};
    private static String[] DocSL = {"Pediatrics", "Surgical", "Family"};

    private boolean isFree; //determines if they are free

    private LinkedList<Nurse>[] nurselist;
    private LinkedList<Tech>[] techlist;
    private LinkedList<Doc>[] doclist;

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        int choice;

        System.out.println("Greetings user, please enter your profile type");
        System.out.println("Option 1: Doctor\nOption 2: Nurse\nOption 3: Technician" + "\nOption 0: exit");
        choice = scnr.nextInt();
        scnr.nextLine(); // fix 2: consume newline

        String name = "";
        int ID = 000000;
        String spec = "";

        boolean exit = false;

        while (!exit) {
            switch (choice) {
                case 1:
                    // Option 1: Doctor
                    System.out.println("Doctor");

                    Doc doc = new Doc(name, ID, spec);
                    System.out.println("Enter your name");
                    
                    doc.name = scnr.nextLine();

                    System.out.println("Enter your ID number [6 digits]");
                    doc.ID = scnr.nextInt();
                    while (doc.ID < 100000 || doc.ID > 999999) {
                        System.out.println("Invalid ID");
                        doc.ID = scnr.nextInt();
                    }
                    scnr.nextLine();

                    System.out.println("Select your speciality");
                    System.out.println("1: " + DocSL[0] + "\n2: " + DocSL[1] + "\n3: " + DocSL[2]);
                    int docChoice = scnr.nextInt();

                    switch (docChoice) {
                        case 1:
                            doc.spec = DocSL[0];
                            break;
                        case 2:
                            doc.spec = DocSL[1];
                            break;
                        case 3:
                            doc.spec = DocSL[2];
                            break;

                    }//end mini switch case

                    System.out.println("Dr. " + doc.name + "\nID: " + doc.ID + "\nSpeciality: " + doc.spec);
                    exit = true;// for now it ends the program

                    break;
                case 2:
                    // Option 2: Nurse
                    System.out.println("Nurse");
                    Nurse nurse = new Nurse(name, ID, spec);
                    System.out.println("Enter your name");
                    nurse.name = scnr.nextLine();

                    System.out.println("Enter your ID number [6 digits]");
                    nurse.ID = scnr.nextInt();
                    while (nurse.ID < 100000 || nurse.ID > 999999) {
                        System.out.println("Invalid ID");
                        nurse.ID = scnr.nextInt();
                    }

                    System.out.println("Select your speciality");
                    System.out.println("1: " + NurseSL[0] + "\n2: " + NurseSL[1] + "\n3: " + NurseSL[2]);
                    int nurseChoice = scnr.nextInt();
                    switch (nurseChoice) {
                        case 1:
                            nurse.spec = NurseSL[0];
                            break;
                        case 2:
                            nurse.spec = NurseSL[1];
                            break;
                        case 3:
                            nurse.spec = NurseSL[2];
                            break;

                    }//end mini switch case
                    System.out.println("Name: " + nurse.name + "\nID: " + nurse.ID + "\nSpeciality: " + nurse.spec);
                    exit = true;

                    break;
                case 3:
                    // Option 3: Technician
                    System.out.println("Technician");
                    // Option 2: Nurse
                    System.out.println("Nurse");
                    Tech tech = new Tech(name, ID, spec);
                    System.out.println("Enter your name");
                    scnr.nextLine();
                    tech.name = scnr.nextLine();

                    System.out.println("Enter your ID number [6 digits]");
                    tech.ID = scnr.nextInt();
                    while (tech.ID < 100000 || tech.ID > 999999) {//checks if ID is right length
                        System.out.println("Invalid ID");
                        tech.ID = scnr.nextInt();
                    }

                    System.out.println("Select your speciality");
                    System.out.println("1: " + TechSL[0] + "\n2: " + TechSL[1] + "\n3: " + TechSL[2]);
                    int techChoice = scnr.nextInt();
                    switch (techChoice) {
                        case 1:
                            tech.spec = NurseSL[0];
                            break;
                        case 2:
                            tech.spec = NurseSL[1];
                            break;
                        case 3:
                            tech.spec = NurseSL[2];
                            break;

                    }//end mini switch case
                    System.out.println("Name: " + tech.name + "\nID: " + tech.ID + "\nSpeciality: " + tech.spec);
                    exit = true;

                    break;
                case 0:
                    //exits
                    System.out.println("exiting...");

                    exit = true; // exits program
                    break;
                default:
                    System.out.println("Invalid input, please select an option");
                    System.out.println("Option 1: Doctor\nOption 2: Nurse\nOption 3: Technician");
                    choice = scnr.nextInt();
                    break;

            }
        }
    }

    static class Nurse {

        String name;
        int ID;
        String spec; //short for speciality
//        int joined;
//        int exp;//for now, join date and exp are integers

        //up next, add default constructor
        Nurse(String name, int ID, String spec) {
            this.name = name;
            this.ID = ID;
            this.spec = spec;

        }
    }

    static class Tech {

        String name;
        int ID;
        String spec; //short for speciality
//        int joined;
//        int exp;//for now, join date and exp are integers

        //up next, add default constructor
        Tech(String name, int ID, String spec) {
            this.name = name;
            this.ID = ID;
            this.spec = spec;

        }
    }

    static class Doc {

        String name;
        int ID;
        String spec; //short for speciality
//        int joined;
//        int exp;//for now, join date and exp are integers

        //up next, add default constructor
        Doc(String name, int ID, String spec) {
            this.name = name;
            this.ID = ID;
            this.spec = spec;

        }
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
