package test;

public class OOPTest {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        int length = 100;
        int width = 100;
        int high = 100;
        Parallelepiped room = new Parallelepiped(length, width, high);
        System.out.println("Room volume is " + room.getMeasure());
    }
}
