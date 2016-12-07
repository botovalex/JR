package test;

public class Reverse {
    public static void main(String[] args) {
        print(6);
    }


    public static void print(int number) {
        StringBuilder sb = new StringBuilder("1");
        if (number <= 0) return;
        System.out.println(sb.toString());
        for (int i = 2; i <= number; i++) {
            if (i % 2 == 1) sb.append("-").append(i).reverse();
            else sb.reverse().append("-").append(i);
            System.out.println(sb.toString());
        }
    }
}
