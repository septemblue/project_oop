package helper;

import java.util.Scanner;

public class IO {
    public static Scanner scanner = new Scanner(System.in);
    
    public static void clearScreen() {  
    	for(int i=0;i<30;i++) {
    		System.out.println("\n");
    	}
    }  
}

