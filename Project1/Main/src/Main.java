import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/************************************************************************************************
 * CLASS: Main.java
 *
 * DESCRIPTION
 * Count the number of runs
 *
 * COURSE AND PROJECT INFORMATION
 * CSE205 Object-Oriented Programming and Data Structures, Spring 2022
 * Project Number: P1
 *
 * AUTHOR: Jeff Cragle, jcragle1, jcragle1@asu.edu

 ************************************************************************************************/
// Main.java
public class Main {

    final static int RUNS_UPS = 1;
    final static int RUNS_DN = -1;

    public static void main(String[] args){
        Main mainObject = new Main();
        mainObject.run();
    }
    /*
    used to run main
     */
    private void run() {
        // create arraylist<Integer> and instantiate with numbers from input file
        // and handle FileNotFoundException
        ArrayList<Integer> list = null;
        try{
        list = readInputFIle("p1-in.txt");
        }catch(FileNotFoundException e){
            System.out.println("Sorry, file not found");
            System.exit(-100);
        }

        // create arraylists to store runs count
        ArrayList<Integer> listRunsUpCount = findRuns(list, RUNS_UPS);
        ArrayList<Integer> listRunsDnCount = findRuns(list, RUNS_DN);

        //create merged list for file output
        ArrayList<Integer> listRunsCount = mergeLists(listRunsUpCount,listRunsDnCount);

        //output to new file and handle FileNotFoundExceptions
        try{
            writeOutputFile("p1-runs.txt",listRunsCount);
        }catch (FileNotFoundException e){
            System.out.println("Sorry, output file not found");
            System.exit(-200);
        }
    }

    /*
   calculate number of runs in a list
    */
    private ArrayList<Integer> findRuns(ArrayList<Integer> pList, int pDir){
        // create array of proper size to store runs count
        ArrayList<Integer> listRunsCount = arrayListCreate(pList.size(), 0);

        // i and k track current location in array and k count
        int i = 0;
        int k = 0;

        // calculate runs up and runs down for listRunsCount
        while (i < pList.size() - 1) {
            if (pDir == RUNS_UPS && (pList.get(i) < pList.get(i + 1))) {
                k++;
            } else if (pDir == RUNS_DN && (pList.get(i) > pList.get(i + 1))) {
                k++;
            }
            else {
                if (k != 0) {
                    listRunsCount.set(k, listRunsCount.get(k) + 1);
                    k = 0;
                }
            }
            i++;
        }
        if(k != 0){
            listRunsCount.set(k, listRunsCount.get(k) + 1);
        }
        return listRunsCount;
    }

    /*
    merge both lists for final output
    */
    private ArrayList<Integer> mergeLists(ArrayList<Integer> pListRunsUpCount,
                                          ArrayList<Integer> pListRunsDnCount){
        ArrayList<Integer> listRunsCount = arrayListCreate(
                pListRunsUpCount.size(), 0);

        for(int i = 0; i <= pListRunsUpCount.size()-1; i++){
            listRunsCount.set(i, pListRunsUpCount.get(i) + pListRunsDnCount.get(i));
        }
        return listRunsCount;
    }

    /*
    creates an arraylist of desired size and instantiates with desired value
    */
    private ArrayList<Integer> arrayListCreate(int pSize, int pInitValue){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < pSize; i++){
            list.add(pInitValue);
        }
        return list;
    }

    /*
    write results to output file
    */
    private void writeOutputFile(String pFilename, ArrayList<Integer> pListRuns) throws FileNotFoundException{
        PrintWriter out = new PrintWriter(new File(pFilename));
        out.printf("runs_total:%d\n", getSum(pListRuns));
        for(int k = 1; k < pListRuns.size(); k++){
            out.printf("runs_%d: %d\n",k, pListRuns.get(k));
        }
        out.close();
    }

    /*
    Opens file, reads into arraylist, exceptions thrown from this method
    */
    private ArrayList<Integer> readInputFIle(String fileName) throws FileNotFoundException{
        ArrayList<Integer> inputList = new ArrayList<>();
        Scanner in = new Scanner(new File(fileName));
        while(in.hasNextInt()) {
            inputList.add(in.nextInt());
        }
        return inputList;
    }

    /*
    calculate running sum of pListRuns
    */
    private Integer getSum(ArrayList<Integer> pListRuns){
        int sum = 0;
        for(int i = 0; i < pListRuns.size();i++){
            if(pListRuns.get(i) > 0){
                sum += pListRuns.get(i);
            }
        }
        return sum;
    }


}
