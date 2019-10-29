public class MyArray {

    String[] array;
    int max;

    public static void main(String[] args) {
        MyArray myArray = new MyArray(10,100);
        myArray.set("h", 0);
        myArray.set("e", 1);
        myArray.set("l", 2);
        myArray.set("l", 3);
        myArray.set("o", 4);
        myArray.set("o", 5);
        myArray.set("o", 100);
        System.out.println(myArray.get(1));
        System.out.println(myArray.get(4));
    }

    public MyArray(int size, int max) {
        this.array = new String[size];
        this.max = max;
        System.out.println("Array of size " + size + " created");
    }

    public String get(int index) {
        try {
            return ("The element at index " + index + " is " + array[index]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException");
        }
        return "null";
    }

    public void set(String element, int index) {
        try {
            array[index] = element;
            System.out.println("Set element " + index + " to " + element);
        } catch (IndexOutOfBoundsException e) {
            int length = array.length;
            String[] temp = new String[length+10];
            for(int i=0; i<array.length-1; i++)
                temp[i] = array[i];
            array = temp;
            temp = null;
            System.out.println("Array resized to size " + (length+10));
            set(element, index);
        }
    }
    
}
