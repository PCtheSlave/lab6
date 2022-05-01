package lib.utils;

import java.util.Scanner;

public class IO {
    public static Scanner in = new Scanner(System.in);

    public static void Print(String str) {
        System.out.println(str);
    }

    public static String Input() {
        return in.nextLine().trim();
    }
}
