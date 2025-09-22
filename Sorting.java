public class Sorting{
    public static void selectionSort(int[] array){
        for(int i=0; i<array.length-1; i++){
            int minIndex = i;
            for(int j =i+1; j<array.length; j++){
                if(array[j]<array[minIndex]){
                    minIndex = j;
                }
            }
            if(minIndex!=i){
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
            }
        }
    }
    public static void bubbleSort(int[] array){
        for(int i=0; i<array.length-1; i++){
            for(int j=0; j<array.length-i-1; j++){
                if(array[j+1]<array[j]){
                    int temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
    public static void insertionSort(int[] array){
        for(int i=0; i<array.length; i++){
            int key = array[i];
            int j = i-1;
            while(j>=0 && array[j]>key){
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = key;
        }
    }
    public static void bubbleSortString(String[] array){
        for(int i =0; i<array.length-1; i++){
            for(int j =0; j<array.length-i-1; j++){
                if((array[j].compareTo(array[j+1]))>0){
                    String temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
    public static int binarySearch(int[] array, int key){
        int low =0;
        int high = array.length-1;
        while(low<=high){
            int mid = (low+high)/2;
            if(key == array[mid]){
                return mid;
            }
            else if(key<array[mid]){
                high = mid-1;
            }
            else if(key>array[mid]){
                low = mid+1;
            }
        }
        return -1;
    }
    public static void main(String[] args){
        int[] array = new int[] {8,7,4,3,1,9,2};
        String[] arrStrings = new String[]{"Hello" , "What", "Zebra" , "Alligator", "Nope" , "Banana", "Hb"};
        insertionSort(array);
        bubbleSortString(arrStrings);
        int key = 5;
        for(int element : array){
            System.out.println(element);
        }
        System.out.println((binarySearch(array, key)!=-1)? "Your index was " + binarySearch(array, key) : "It wasnt found");
    }
}