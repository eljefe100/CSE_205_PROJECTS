import java.util.ArrayList;

//**************************************************************************************************
// CLASS: Searcher (Searcher.java)
// DESCRIPTION: implements binary search for student record retrieval
// CSE205 Object Oriented Programming and Data Structures
// Project Number: 3
// AUTHOR: Jeff Cragle, jcragle1, jcragle1@asu.edu
//**************************************************************************************************
public class Searcher {
    public  static int search(ArrayList<Student> pList, String pStudentKey){
        int first = 0;
        int last = pList.size()-1;
        int mid;
        while(first <= last){
            mid = (first + last) / 2;
            if(pList.get(mid).getLastName().compareTo(pStudentKey) == 0){
                return mid;
            }else if(pList.get(mid).getLastName().compareTo(pStudentKey) < 0){
                first = mid + 1;
            }else if(pList.get(mid).getLastName().compareTo(pStudentKey) >0){
                last = mid - 1;
            }
        }
        return -1;
    }
}
