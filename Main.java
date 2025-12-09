/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.io.*;

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
    //Priority type
    private static Priority triage = new Priority();

    //Apppointment queue
    private static Queue<Appointment> appointmentQueue = new LinkedList<>();

    public static void main(String[] args) {

        // DEFAULT USERS
        /* Newer Template/Pseudo for adding employees
        Staff staffX = new staff(
                "", //name
                XXXXXX, // 6 digit ID
                specialityList[X], //type of specialization in their career
                LocalDate.of(y, m, d), //join date
                LocalDate.of(y, m, d) //expiry date
        stafflist.add(staffX) //Adds to their respective LL
        staffHash.put(staffX.ID,staffX) //Adds to their respective HashMap
        ));
         */
        //     ---------Nurses---------
        Nurse nurse1 = new Nurse(
                "Usagi",
                300678,
                NurseSL[0],
                LocalDate.of(1992, 3, 7),
                LocalDate.of(2027, 2, 8)
        );
        nurselist.add(nurse1);
        nHash.put(nurse1.ID, nurse1);

        Nurse nurse2 = new Nurse(
                "Minako",
                220878,
                NurseSL[2],
                LocalDate.of(1991, 8, 3),
                LocalDate.of(2030, 1, 10)
        );
        nurselist.add(nurse2);
        nHash.put(nurse2.ID, nurse2);

        //     ---------Techs---------
        Tech tech1 = new Tech(
                "Misato",
                860912,
                TechSL[1],
                LocalDate.of(2010, 2, 14),
                LocalDate.of(2026, 4, 1));
        techlist.add(tech1);
        tHash.put(tech1.ID, tech1);

        Tech tech2 = new Tech(
                "Shinji", //name
                630999, // 6 digit ID
                TechSL[0], //type of specialization in their career
                LocalDate.of(2015, 3, 14), //join date
                LocalDate.of(2030, 6, 28) //expiry date
        );
        techlist.add(tech2);
        tHash.put(tech2.ID, tech2);

        //     ---------Doctors---------
        Doc doc1 = new Doc(
                "Mamoru",
                998877,
                DocSL[1],
                LocalDate.of(1992, 3, 7),
                LocalDate.of(2027, 2, 8)
        );
        doclist.add(doc1);
        docHash.put(doc1.ID, doc1);

        Doc doc2 = new Doc(
                "Seiya",
                300776,
                DocSL[2],
                LocalDate.of(2020, 1, 10),
                LocalDate.of(2030, 1, 10)
        );
        doclist.add(doc2);
        docHash.put(doc2.ID, doc2);

        // Default Patients
        Patient patient1 = new Patient(
                "Shadow the Hedgehog", // name
                111111, // ID
                50, // age
                "Unknown", // diagnosis
                0, // priority to be determined by admin/doctor
                LocalDate.of(1975, 1, 1) // dob ->  YYYY MM DD
        );
        patientList.add(patient1);
        pHash.put(patient1.ID, patient1);

        Patient patient2 = new Patient(
                "Hatsune Miku",
                393939,
                16,
                "Unknown",
                0,
                LocalDate.of(2008, 3, 9)
        );
        patientList.add(patient2);
        pHash.put(patient2.ID, patient2);

        loadPatientsFromFile("patients.txt");

        Scanner scnr = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Which are you?");
            System.out.println("1: Staff");
            System.out.println("2: Patient");
            System.out.println("3: Admin");
            System.out.println("0: Escape");
            int choice = scnr.nextInt();

            switch (choice) {
                case 1://Staff Case
                    //System.out.println("Omg you got a j*b"); meme line to test

                    System.out.println("\nAre you new or Returning?");
                    System.out.println("1: Returning \n2: New\n0: Exit");
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
                    boolean patientBack = false;
                    /*quality of life update that lets you actually stay in the patient menu until you
                    are done*/

                    while (!patientBack) {
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
                                        System.out.println(p.name + ", " + p.ID + ", " + p.age + " Diagnosis: " + p.diagnosis + ", " + p.priority + " " + p.dateOfBirth.toString());
                                        break;
                                    case 2:
                                        //Schedule appointment
                                        //prints doc speciality options
                                        for (int i = 0; i < doclist.size(); i++) {
                                            System.out.print(i + ": ");
                                            Doc d = doclist.get(i);
                                            System.out.print(i + ":" + d.toString());
                                        }
                                        System.out.println("Which Doctor would you like?");
                                        int docIndex = scnr.nextInt();

                                        break;
                                    case 0:
                                        //goes back
                                        break;
                                }

                                break; //ends case 1 (Existing patient)
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

                                Patient patient = new Patient(name, pID, age, "Unknown", 0, dob);//patients dont set their own priority or diagnosis

                                patientList.add(patient);
                                pHash.put(patient.ID, patient);
                                savePatientsToFile("patients.txt");

                                break;

                            case 0:
                                patientBack = true;
                                System.out.println("Exiting Patient Menu");
                                break;// end exit case
                        }

                    } //end of patient menu then break
                    break;

                case 3: // admin case
                    // Show Admin submenu
                    // 1: Triage center 
                    // 2: Manage employees
                    // 0: Back/Exit

                    boolean adminBack = false;
                    while (!adminBack) {
                        System.out.println("What would you like to Check today?");
                        System.out.println("1: Triage Center\n2: Manage Employees\n0: Back/Exit");
                        int adMenu = scnr.nextInt();
                        scnr.nextLine();
                        switch (adMenu) {
                            case 1:
                                //Triage

                                System.out.println("====Hospital Traige Center====");
                                System.out.println("How many patients are being added? ");
                                int numPatients = scnr.nextInt();
                                for (int i = 0; i < numPatients; i++) {
                                    System.out.print("Enter patient ID: ");
                                    int ID = scnr.nextInt();
                                    Patient p = pHash.get(ID);
                                    if (p != null) {
                                        System.out.print("Enter priority (1=CRITICAL, 2=URGENT, 3=ROUTINE): ");
                                        int priority = scnr.nextInt();
                                        p.priority = priority;
                                        triage.addPatient(p);
                                        savePatientsToFile("patients.txt");

                                        scnr.nextLine();
                                    } else {
                                        System.out.print("Does not exist/Invalid ID");
                                    }

                                }// end Triage loop
                                triage.displayQueues();
                                Patient next = triage.getNextPatient();
                                System.out.println("Next patient to treat: " + next);

                                break;
                            case 2:
                                //employees

                                break;
                            case 0:
                                adminBack = true;
                                System.out.println("Exiting Admin");
                                break;// end exit case

                        }

                    }
                    break;

                case 0: //exit all
                    exit = true;
                    System.out.println("Exiting");
                    break;// end exit case

            }

        }
        scnr.close();
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

        @Override
        public String toString() {
            return "Dr. " + name + " (" + spec + ")";
        }

    }

    static class Patient {

        //Store and retrieve patient details such as name, age, diagnosis, treatment history, etc.
        String name;
        int age;
        int ID;
        String diagnosis;
        int priority;

        LocalDate dateOfBirth;

        Patient(String name, int ID, int age, String diagnosis, int priority, LocalDate dateOfBirth) {
            this.name = name;
            this.age = age;
            this.ID = ID;
            this.diagnosis = diagnosis;
            this.priority = priority;
            this.dateOfBirth = dateOfBirth;
        }

        @Override
        public String toString() {
            String priorityLabel;
            switch (priority) {
                case 1:
                    priorityLabel = "CRITICAL";
                    break;
                case 2:
                    priorityLabel = "URGENT";
                    break;
                case 3:
                    priorityLabel = "ROUTINE";
                    break;
                default:
                    priorityLabel = "UNKNOWN";
                    break;
            }
            return name + " (" + priorityLabel + ")";
        }

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

    //Save2File
    static void savePatientsToFile(String filename) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (Patient p : patientList) {
                // format: ID,name,age,diagnosis,priority,dateOfBirth
                String line = p.ID + "," + p.name + "," + p.age + ","
                        + p.diagnosis + "," + p.priority + ","
                        + p.dateOfBirth.toString(); // LocalDate -> "YYYY-MM-DD"
                out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error saving patients: " + e.getMessage());
        }

    }

    //loadFromFile
    static void loadPatientsFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            // No saved patients yet; nothing to load
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 6) {
                    continue; // or handle error
                }
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                String diagnosis = parts[3];
                int priority = Integer.parseInt(parts[4]);
                LocalDate dob = LocalDate.parse(parts[5]); // "YYYY-MM-DD"

                Patient p = new Patient(name, id, age, diagnosis, priority, dob);
                patientList.add(p);
                pHash.put(p.ID, p);
            }
        } catch (IOException e) {
            System.out.println("Error loading patients: " + e.getMessage());
        }
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

    static class Priority {

        Queue<Patient> criticalQueue = new LinkedList<>();
        Queue<Patient> urgentQueue = new LinkedList<>();
        Queue<Patient> routineQueue = new LinkedList<>();
        Queue<Patient> unknownQueue = new LinkedList<>();

        // Add patient to appropriate queue
        public void addPatient(Patient patient) {
            if (patient.priority == 1) {
                criticalQueue.add(patient);
            } else if (patient.priority == 2) {
                urgentQueue.add(patient);
            } else if (patient.priority == 3) {
                routineQueue.add(patient);
            } else {
                unknownQueue.add(patient);
            }

        }

        //Get next patient to be treated
        public Patient getNextPatient() {
            if (!criticalQueue.isEmpty()) {
                return criticalQueue.poll();
            } else if (!urgentQueue.isEmpty()) {
                return urgentQueue.poll();
            } else if (!routineQueue.isEmpty()) {
                return routineQueue.poll();
            } else if (!unknownQueue.isEmpty()) {
                return unknownQueue.poll();
            } else {
            }
            return null;
        }

//Display all queues
        public void displayQueues() {
            System.out.println("Critical: " + criticalQueue);
            System.out.println("Urgent: " + urgentQueue);
            System.out.println("Routine: " + routineQueue);
            System.out.println("Unkown: " + unknownQueue);
        }

    }

    static class Employee { //generic employee type for admin

        private String name;
        private String role;
        private double salary;

        public Employee(String name, String role, double salary) {
            this.name = name;
            this.role = role;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public String getRole() {
            return role;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return name + " - " + role + " ($" + salary + ")";
        }
    }

}
