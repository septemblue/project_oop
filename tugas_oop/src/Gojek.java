import helper.Cache;
import Composition.Customer;
import Local.Database;
import aggregation.GoFoodApplication;
import aggregation.GojekApplication;
import aggregation.Gopay;
import aggregation.Jago;
import helper.Custom;
import resource.BasicApp;

import static helper.IO.scanner;
import static helper.IO.clearScreen;

import java.io.IOException;

public class Gojek {
    public static void main(String[] args) {
    	boolean valid = false;
        // user need to sign in to get to app
        Integer menu= 0;
        sign();
        do {
             menu = Custom.getChoice(() -> {
                appMenu();
            }, 7);
            switch (menu) {
                case 0 -> {
                	sign();
                }
                case 1 -> new GojekApplication(Cache.getLoggedCustomer());
                case 2 -> new GoFoodApplication(Cache.getLoggedCustomer());
                case 3 -> new Gopay().buatCard(Cache.getLoggedCustomer());
                case 4 -> new Jago().buatCard(Cache.getLoggedCustomer());
                case 5 -> new Gopay().isiSaldo(Cache.getLoggedCustomer());
                case 6 -> new Jago().isiSaldo(Cache.getLoggedCustomer());
                case 7 -> cekSaldo();
            }
        }while(true);
    }
    
    // AppMenu
    private static void appMenu() {
    	clearScreen();
    	System.out.println("Selamat datang  "+ Cache.getLoggedCustomer().username+" di gojek");
    	System.out.println("=============================================================");
        System.out.println("1. Gojek");
        System.out.println("2. GoFood");
        System.out.println("3. Daftar Gopay");
        System.out.println("4. Daftar Jago");
        System.out.println("5. Top up Gopay");
        System.out.println("6. Top up Jago");
        System.out.println("7. Cek saldo");
        System.out.println("0. Kembali");
        System.out.print(">> ");
    }
    
    private static void cekSaldo() {
    	int menu = Custom.getChoice(() -> {
    	System.out.println("1. Cek Gopay");
    	System.out.println("2. Cek Jago");
    	System.out.print(">> ");
    	},2);
    	switch (menu) {
    		case 1 -> cek(Cache.getLoggedCustomer().gopay);
    		case 2 -> cek(Cache.getLoggedCustomer().jago);
    	}

    }
    
    //contoh overloading
    private static void cek(Gopay gopay) {
    	try {
    	System.out.println("Saldo kamu saat ini ada " + gopay.saldo);
    	}catch (NullPointerException e) {System.out.println("Kamu tidak punya alat pembayaran ini");};
    	Custom.enterToContinue();
    }
    
    private static void cek(Jago jago) {
    	try {
    	System.out.println("Saldo kamu saat ini ada " + jago.saldo);
    	}catch (NullPointerException e) {System.out.println("Kamu tidak punya alat pembayaran ini");};
    	Custom.enterToContinue();
    }

    // Sign in & Sign up Functions
    private static void sign() {
        Integer menu;
        boolean valid;

        do {
        	valid = false;
            System.out.println("1. Login");
            System.out.println("2. Gapunya akun?");
            System.out.println("0. Keluar");
            System.out.print(">> ");

            try {
                menu = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Tolong masukkan dengan benar\n");
                continue;
            }

            switch (menu) {
                case 0 -> {
                    System.out.println("Terimakasih telah menggunakan app");
                    System.exit(1);
                }

                case 1 -> valid = login();

                case 2 -> register();
            }
        } while (!valid);

    }
    private static boolean login() {
        // get username & password
        System.out.print("Masukkan username : ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password : ");
        String password = scanner.nextLine();

        // validate
        boolean valid = false;
        try{
            valid = validate(username, password);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        if(valid) Cache.setLoggedCustomer(Database.customers.get(username));
        System.out.println(Cache.getLoggedCustomer().username);
        return valid;
    }
    private static boolean validate(String username, String password) {
        if (!Database.customers.containsKey(username)) {
            throw new IllegalArgumentException("Username ini tidak ada");
        } else if (!Database.customers.get(username).password.equals(password)) {
            throw new IllegalArgumentException("Password salah");
        }
        return true;
    }
    private static void register() {
        Customer newCustomer;

        // get newCustomer
        String username;
        do {
        	System.out.print("Enter username [5-15 letters]: ");
        	username = scanner.nextLine();
        }while (username.length()<5||username.length()>15);
        System.out.print("Enter phone number : ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter password : ");
        String password = scanner.nextLine();
        newCustomer = new Customer(username, phoneNumber, password);

        // validate if username exist
        try {
            if (Database.customers.containsKey(newCustomer.username)) {
                throw new IllegalArgumentException("Username Exist");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            register();
        }

        // register to database
        Database.customers.put(newCustomer.username, newCustomer);
    }

}
