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
// Next Steps: Create a way for the user to add their name to the list (As a Nurse, Tech or Doc)
// A number menu using case switch to select options
// Once its successful with console, replace the system dialog with JOptionPane

// Next Steps: Create a schedule that illustrates when a nurse technichian or doctor is available using isFree
public class Main {

    private static String[] NurseSL = {"CNA", "LPN", "RN"};
    private String[] TechSL = {"Radiologic", "MRI", "Ultrasound"};
    private String[] DocSL = {"Pediatrics", "Surgical", "Family"};

    private boolean isFree; //determines if they are free

    private LinkedList<Nurse>[] nurselist;
    private LinkedList<Tech>[] techlist;
    private LinkedList<Doc>[] doclist;
    
    public static void main(String [] args){
    
    System.out.println(NurseSL.toString());
    
    }

    class Nurse {

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

    class Tech {

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

    class Doc {

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
