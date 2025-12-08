/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 *
 * @author gmrgk
 */
// Hashmaps have been added
//Nurse is fixed
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

    private static String[] NurseSL = {"CNA", "LPN", "RN", "Admin", "L&D", "Pediatrics", "Trauma", "NP", "Surgery"};
    /*
    CNA - Certified nursing assistant
    LPN - Licensed Practitioner nurse
    RN - Registered Nurse
    NP nurse practitioner
    L&D Labor and delivery
     */
    private static String[] TechSL = {"Radiologic", "MRI", "Ultrasound", "Nuclar_Med", "Cardio", "EEG", "Surgical"};
    private static String[] DocSL = {"Pediatrics", "Surgical", "Family"};

    //Staff LLs
    private static LinkedList<Nurse> nurselist = new LinkedList<>();
    private static LinkedList<Tech> techlist = new LinkedList<>();
    private static LinkedList<Doc> doclist = new LinkedList<>();

    //Staff Hashmaps for fast search
    private static HashMap<Integer, Nurse> nHash = new HashMap<>();// -> <staff.ID, staff>
    private static HashMap<Integer, Tech> tHash = new HashMap<>();
    private static HashMap<Integer, Doc> docHash = new HashMap<>();

    //Patient LL and Hashmap
    private static LinkedList<Patient> patientList = new LinkedList<>();
    private static HashMap<Integer, Patient> pHash = new HashMap<>(); // <patient.ID, patient>

    //Apppointment queue
    private static Queue<Appointment> appointmentQueue = new LinkedList<>();

    public static void main(String[] args) {

        // DEFAULT USERS
        /* Template/ pseudo for adding employees
        stafflist.add(new staff(
                "", //name
                XXXXXX, // 6 digit ID
                specialityList[X], //type of specialization in their career
                LocalDate.of(y, m, d), //join date
                LocalDate.of(y, m, d) //expiry date
        ));
         */
        nurselist.add(new Nurse(
                "Usagi",
                300678,
                NurseSL[0],
                LocalDate.of(1992, 3, 7),
                LocalDate.of(2027, 2, 8)
        ));

        nurselist.add(new Nurse(
                "Minako",
                220878,
                NurseSL[2],
                LocalDate.of(1991, 8, 3),
                LocalDate.of(2030, 1, 10)
        ));

        techlist.add(new Tech(
                "Misato",
                860912,
                TechSL[1],
                LocalDate.of(2010, 2, 14),
                LocalDate.of(2026, 4, 1)
        ));

        techlist.add(new Tech(
                "Shinji", //name
                630999, // 6 digit ID
                TechSL[0], //type of specialization in their career
                LocalDate.of(2015, 3, 14), //join date
                LocalDate.of(2030, 6, 28) //expiry date
        ));

//        techlist.add(new Tech("Misato", 860912, TechSL[1]));
//        techlist.add(new Tech("Shinji", 630999, TechSL[0]));
        doclist.add(new Doc(
                "Mamoru",
                998877,
                DocSL[1],
                LocalDate.of(1992, 3, 7),
                LocalDate.of(2027, 2, 8)
        ));

        doclist.add(new Doc(
                "Seiya",
                300776,
                DocSL[2],
                LocalDate.of(2020, 1, 10),
                LocalDate.of(2030, 1, 10)
        ));
//        doclist.add(new Doc("Mamoru", 998877, DocSL[1]));
//        doclist.add(new Doc("Seiya", 300776, DocSL[2]));

        // Adding Patients
//        patientList.add(new Patient(String name, int ID, int age, String diagnosis, float priority, LocalDate dateOfBirth))
        Scanner scnr = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Are you an employee or patient? \b[Enter 1 for yes y/ 2 for no]");
            int choice = scnr.nextInt();
            switch (choice) {
                case 1://Staff Case
                    //System.out.println("Omg you got a j*b"); meme line to test

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
                                    docHash.put(doc.ID, doc);

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
                                    nHash.put(nurse.ID, nurse);

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
                                    tHash.put(tech.ID, tech);

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
                    //System.out.println("Omg ur sick"); also meme case
                    System.out.println("\nDo you already exist in the system?");
                    System.out.println("1: Yes \n2: No\n0: Exit");
                    int existsChoice2 = scnr.nextInt(); //exists choice is already in the Staff case
                    scnr.nextLine();
                    switch (existsChoice2) {
                        case 1:
                            //fetch existing name in the system
                            System.out.print("Enter Patient ID: ");
                            int searchID = scnr.nextInt();
                            scnr.nextLine();
                            Patient p = pHash.get(searchID);
                            if (p == null) {
                                System.out.println("User not found");
                            } else {
                                // String name, int ID, int age, String diagnosis, float priority, LocalDate dateOfBirth

                            }
                            /*
                            1: View my info again
                            2: Schedule an appointment
                            0: Back
                             */

                            System.out.println("1: View my info again \n2: Schedule Appointment\n0: Back");
                            int pMenu = scnr.nextInt();
                            switch (pMenu) {
                                case 1:
                                    //View info
                                    System.out.println(p.name + " " + p.ID + " " + p.age + p.diagnosis + p.priority + p.dateOfBirth);
                                    break;
                                case 2:
                                    //Schedule appointment
                                    //prints doc speciality options
                                    for (int i = 0; i < doclist.size(); i++) {
                                        System.out.print(doclist.get(i) + " ");
                                    }

                                    break;
                                case 0:
                                    //goes back
                                    break;
                            }

                            break; //ends case 1
                        case 2:
                            //Add yourself to the system

                            System.out.println("Enter your name");
                            String name = scnr.nextLine();
                            System.out.println("Now choose your ID [6 digits]");
                            int pID = scnr.nextInt();
                            while (pID < 100000 || pID > 999999) { //prevents you from making a longer ID
                                System.out.println("Invalid ID");
                                pID = scnr.nextInt();
                            }
                            System.out.println("Enter your age");
                            int age = scnr.nextInt();
                            scnr.nextLine();

                            LocalDate dob = getDateInput(scnr, "Your Date of Birth ");

                            Patient patient = new Patient(name, pID, age, "Unknown", 0.0f, dob);//patients dont set their own priority or diagnosis
                            patientList.add(patient);
                            pHash.put(patient.ID, patient);

                            break;
                        case 0:
                            exit = true;
                            System.out.println("Exiting");
                            break;
                    }

                    //in here we will mirror what went down in the employee case
                    //check if patient exists, if patient is in the list, they get info on who 
                    break;

            }

        }
    }

    // Custom Object classes
    //People: Nurse, Tech,Doc,Patients
    static class Nurse {

        String name;
        int ID;
        String spec;
        LocalDate joinDate;
        LocalDate expDate;

        boolean isFree = true; //boolean to determine if they are free or not

        Nurse(String name, int ID, String spec, LocalDate joinDate, LocalDate expDate) {
            this.name = name;
            this.ID = ID;
            this.spec = spec;
            this.joinDate = joinDate;
            this.expDate = expDate;
        }

        Nurse(String name, int ID, String spec) {
            this(name, ID, spec, LocalDate.now(), LocalDate.now().plusYears(1)); // or nulls or default values
        }
    }

    static class Tech {

        String name;
        int ID;
        String spec;

        LocalDate joinDate;
        LocalDate expDate;

        boolean isFree = true;

        Tech(String name, int ID, String spec, LocalDate joinDate, LocalDate expDate) {
            this.name = name;
            this.ID = ID;
            this.spec = spec;
            this.joinDate = joinDate;
            this.expDate = expDate;
        }

        Tech(String name, int ID, String spec) {
            this(name, ID, spec, LocalDate.now(), LocalDate.now().plusYears(1));
        }
    }

    static class Doc {

        String name;
        int ID;
        String spec;

        LocalDate joinDate;
        LocalDate expDate;

        boolean isFree = true;

        Doc(String name, int ID, String spec, LocalDate joinDate, LocalDate expDate) {
            this.name = name;
            this.ID = ID;
            this.spec = spec;
            this.joinDate = joinDate;
            this.expDate = expDate;
        }

        Doc(String name, int ID, String spec) {
            this(name, ID, spec, LocalDate.now(), LocalDate.now().plusYears(1));
        }
    }

    static class Patient {

        //Store and retrieve patient details such as name, age, diagnosis, treatment history, etc.
        String name;
        int age;
        int ID;
        String diagnosis;
        float priority;

        LocalDate dateOfBirth;

        Patient(String name, int ID, int age, String diagnosis, float priority, LocalDate dateOfBirth) {
            this.name = name;
            this.age = age;
            this.ID = ID;
            this.diagnosis = diagnosis;
            this.priority = priority;
            this.dateOfBirth = dateOfBirth;
        }

//        Patient(String name, int age, String diagnosis, float priority) {
//            this(name, age, diagnosis,priority, LocalDate.now(), LocalDate.now().plusYears(1)); // or nulls or default values
//        }
    }

    //Date Setter 
    public static LocalDate getDateInput(Scanner scnr, String label) {
        System.out.println("Enter " + label + " (YYYY MM DD):");
        int y = scnr.nextInt();
        int m = scnr.nextInt();
        int d = scnr.nextInt();
        scnr.nextLine();
        return LocalDate.of(y, m, d);
    }

    static class Appointment {

        Patient patient;
        Doc doctor;
        LocalDate date;
        // time slot or reason
        /*
        When a patient wants an appointment:
            Check if the doctor exists (search map or list).
            Check if doctor is free at that time (using boolean isFree)
            If both sucessful, declare an Appointment and offer() it into appointmentQueue
         */
    }

}
