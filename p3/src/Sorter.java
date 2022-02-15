import java.util.ArrayList;

//**************************************************************************************************
// CLASS: Sorter (Sorter.java)
// DESCRIPTION: implements quick sort algorithm for ordering student records
// CSE205 Object Oriented Programming and Data Structures
// Project Number: 3
// AUTHOR: Jeff Cragle, jcragle1, jcragle1@asu.edu
//**************************************************************************************************
public class Sorter {
    private static int partition(ArrayList<Student> pList, int pFromIndex, int pToIndex){
        Student pivot = pList.get(pFromIndex);
        int leftIndex = pFromIndex-1;
        int rightIndex = pToIndex+1;

        while(leftIndex < rightIndex) {
            leftIndex++;
            while ((pList.get(leftIndex).getLastName().compareTo(pivot.getLastName())) < 0) {
               leftIndex++;
            }
            rightIndex--;
            while((pList.get(rightIndex).compareTo(pivot)) > 0) {
               rightIndex--;
            }
            if(leftIndex < rightIndex){
                swap(pList,leftIndex,rightIndex);
            }
        }
        return rightIndex;
    }

    private static void quickSort(ArrayList<Student> pList, int pFromIndex, int pToIndex){
        if(pFromIndex >= pToIndex){
            return;
        }

        int partitionIndex = partition(pList, pFromIndex, pToIndex);

        quickSort(pList, pFromIndex, partitionIndex);
        quickSort(pList, partitionIndex + 1, pToIndex);

    }

    public static void sort(ArrayList<Student> pList){
        quickSort(pList,0,pList.size()-1);
    }

    public static void swap(ArrayList<Student> plist, int pIndex1, int pIndex2){
        Student temp = plist.get(pIndex1);
        plist.set(pIndex1, plist.get(pIndex2));
        plist.set(pIndex2,temp);
    }
}
