package test;

public class Parallelepiped extends Rectangle {
    private int high;

    public Parallelepiped(int field1, int field2, int field3) {
        super(field1, field2);
        this.high = field3;
        System.out.println("width = " + width);
        System.out.println("length = " + length);
        System.out.println("high = " + high);
        System.out.println("measure = " + measure);
    }

    @Override
    public void mult() {
        System.out.println("==========");
        System.out.println("in mult paral");
        System.out.println("width = " + width);
        System.out.println("length = " + length);
        System.out.println("high = " + high);
        System.out.println("measure = " + measure);
        this.measure = width * length * high;
        System.out.println("measure = " + measure);
        System.out.println("==========");
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }
}
