package aggregation;

import Composition.Customer;
import helper.Custom;
import helper.IO;
import resource.BasicApp;

import static helper.IO.scanner;
import static helper.IO.clearScreen;

public class GojekApplication implements BasicApp {
    Integer menuChoice;
    Customer customer;
    String destination;
    long deliverPrice;

    // Composition with customer
    public GojekApplication(Customer customer) {
        this.customer = customer;
        start();
    }

    // main gojek application
    private void start() {
        do {
            if (menu() == 1) deliver();
        }while (menuChoice != 0);
    }

    // gojek deliver
    private void deliver() {
        System.out.print("Masukkan nama jalannya : ");
        destination = scanner.nextLine();
        deliverPrice = calculateDistance(destination);
        System.out.println("Harga ke " + destination + " adalah : Rp." + deliverPrice);
        // sistem pembayaran disini
        customer.pay(deliverPrice);
    }

    private long calculateDistance(String destination) {
        // 550 perak for each character XD
        return destination.trim().length() * 750L;
    }

    @Override
    public int menu() {
    	clearScreen();	
        menuChoice = Custom.getChoice(() -> {
        	System.out.println("Pilihan menu");
        	System.out.println("========================================");
            System.out.println("1. Jemput");
            System.out.println("0. Gajadi");
            System.out.print(">> ");
        }, 1);
        return menuChoice;
    }
}