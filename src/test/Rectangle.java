package test;

public class Rectangle {
    protected int width;
    protected int length;
    protected int measure;

    public Rectangle(int field1, int field2) {
        this.width = field1;
        this.length = field2;
        this.mult();
    }

    public void mult() {
        measure = width*length;
        System.out.println("in mult rect");
    }

    public int getMeasure() {
        return measure;
    }

    public int getWidth() {

        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
