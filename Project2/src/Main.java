//**************************************************************************************************
// CLASS: Main
//
// DESCRIPTION
// The Main class for Project 2.
//**************************************************************************************************
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /**
     * Instantiate a Main object and call run() on the object. Note that essentially now, run()
     * becomes the starting point of execution for the program.
     */

    public static void main(String[] args){
        Main mainObject = new Main();
        mainObject.run();
    }


    /**
     *  Calls other methods
     */
    private void run(){
        ArrayList<Student> studentList = null;

        try{
            studentList = readFile();

        }catch (FileNotFoundException e){
            System.out.println("Sorry, could not open 'p2-students.txt' for reading. Stopping.");
            System.exit(-1);
        }

        calcTuition(studentList);

        Sorter.insertionSort(studentList, Sorter.SORT_ASCENDING);

        try{
            writeFile(studentList);
        }catch (FileNotFoundException e){
            System.out.println("Sorry, could not open 'p2-tuition.txt' for writing. Stopping");
            System.exit(-1);
        }
    }

    /**
     * Calculates the tuition for each Student in pStudentList. Write an enhanced for loop that
     * iterates over each Student in pStudentList. For each Student, call calcTuition() on that
     * Student object. Note: this is a polymorphic method call. What does that mean?
     */
    private void calcTuition(ArrayList<Student> pStudentList) {
        for (Student student : pStudentList) {
            student.calcTuition();
        }
    }

    /**
     * Reads the student information from "p2-students.txt" and returns the list of students as
     * an ArrayList<Student> object. Note that this method throws FileNotFoundException if the
     * input file could not be opened. The exception is caught and handled in run().
     */
    private ArrayList<Student> readFile() throws FileNotFoundException{
    ArrayList<Student> studentList = new ArrayList<>();

    Scanner in = new Scanner(new File("p2-students.txt"));

    while (in.hasNext()){
        String studentType = in.next();
        if(studentType.equals("C")){
            studentList.add(readOnCampusStudent(in));
        }
        else{
            studentList.add(readOnlineStudent(in));
        }
    }
    in.close();
    return studentList;
}
    /**
     * Reads the information for an on-campus student from the input file
     */
    private OnCampusStudent readOnCampusStudent(Scanner pIn){
        String id = pIn.next();
        String lName = pIn.next();
        String fName = pIn.next();

        OnCampusStudent newOnCampusStudent = new OnCampusStudent(id, fName, lName);

        String res = pIn.next();
        double fee = pIn.nextDouble();
        int credits = pIn.nextInt();

        if(res.equals("R")){
            newOnCampusStudent.setResidency(OnCampusStudent.RESIDENT);
        }
        else{
            newOnCampusStudent.setResidency(OnCampusStudent.NON_RESIDENT);
        }

        newOnCampusStudent.setProgramFee(fee);
        newOnCampusStudent.setCredits(credits);

        return newOnCampusStudent;
    }

    /**
     * Reads the information for an online student from the input file.
     */
    private OnlineStudent readOnlineStudent(Scanner pIn){
        String id = pIn.next();
        String lName = pIn.next();
        String fName = pIn.next();

        OnlineStudent newOnlineStudent = new OnlineStudent(id, fName, lName);

        String fee = pIn.next();
        int credits = pIn.nextInt();

        if(fee.equals("T")){
            newOnlineStudent.setTechFee(true);
        }
        else{
            newOnlineStudent.setTechFee(false);
        }

        newOnlineStudent.setCredits(credits);
        return  newOnlineStudent;
    }

    /**
     * Writes the output to "p2-tuition.txt" per the software requirements. Note that this method
     * throws FileNotFoundException if the output file could not be opened. The exception is caught
     * and handled in run().
     */
    private void writeFile(ArrayList<Student> pStudentList) throws FileNotFoundException{
        PrintWriter out = new PrintWriter("p2-tuition.txt");

        for(Student student : pStudentList){
            out.printf("%-16s %-20s %-15s %8.2f \n",student.getId(), student.getLastName(),
                    student.getFirstName(), student.getTuition());
        }
        out.close();
    }
}


