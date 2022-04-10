package aggregation;

import static helper.IO.clearScreen;
import static helper.IO.scanner;

import Composition.Customer;
import helper.Custom;
import helper.IO;
import resource.BasicApp;

public class GoFoodApplication implements BasicApp{
	int menuChoice;
	int deliver;
	Customer customer;
	
	public GoFoodApplication(Customer customer) {
	        this.customer = customer;
	        start();
	   }
	
	@Override
	public int menu() {
		clearScreen();	
        menuChoice = Custom.getChoice(() -> {
        	System.out.println("Pilihan menu");
        	System.out.println("========================================");
            System.out.println("1. Pesan");
            System.out.println("0. Gajadi");
            System.out.print(">> ");
        }, 1);
        
        return menuChoice;
	}
	
	
	// main gofood application
    private void start() {
        do {
            if (menu() == 1) lihatMenu();
        }while (menuChoice != 0);
    }
    
    private void lihatMenu() {
    	int menu = Custom.getChoice(() -> {
    	System.out.println("\n1. Menu makanan");
    	System.out.println("2. Menu minuman");
    	System.out.print(">> ");
    	},2);
    	switch(menu) {
    		case 1 -> menuMakanan();
    		case 2 -> menuMinuman();
    	}
    }
    
    private void menuMakanan() {
    	int menu = Custom.getChoice(() -> {
    		System.out.println("\n1. Nasi goreng");
    		System.out.println("2. Nasi padang");
    		System.out.println("3. Nasi kuning");
    		System.out.println("4. Custom");
    		System.out.print(">> ");
    	},4);
    	switch(menu) {
    		case 1 -> bayarMakan(1);
    		case 2 -> bayarMakan(2);
    		case 3 -> bayarMakan(3);
    		case 4 -> bayarMakan(4);
    	}
    }

    private void menuMinuman() {
    	int menu = Custom.getChoice(() -> {
    		System.out.println("\n1. Es teh manis");
    		System.out.println("2. Es teh doang");
    		System.out.println("3. Air mineral");
    		System.out.println("4. Custom");
    		System.out.print(">> ");
    	},4);
    	switch(menu) {
    		case 1 -> bayarMinum(1);
    		case 2 -> bayarMinum(2);
    		case 3 -> bayarMinum(3);
    		case 4 -> bayarMinum(4);
    	}
    }
    
    private void bayarMinum(int i) {
    	long harga = 0;
    	System.out.println("");
    	switch(i) {
    	case 1 -> {
    		System.out.println("Harga es teh manis Rp.8000");
    		harga = 8000;
    	}
    	case 2 -> {
    		System.out.println("Harga es teh doang Rp.5000");
    		harga = 5000;
    	}
    	case 3 -> {
    		System.out.println("Harga air mineral Rp.5000");
    		harga = 5000;
    	}
    	case 4 -> {
    		System.out.print("Masukkan nama minuman >> "); 
    		String customBeverage = IO.scanner.nextLine();
    		harga = calculateLen(customBeverage);
    		System.out.println("Harga "+ customBeverage +" Rp."+ harga);
    		}
    	}
    	customer.pay(harga);   
    }
    
    private void bayarMakan(int i) {
    	System.out.println("");
    	long harga = 0;
    	switch(i) {
    	case 1 -> {
    		System.out.println("Harga nasi goreng Rp.13000");
    		harga = 13000;
    	}
    	case 2 -> {
    		System.out.println("Harga nasi padang Rp.20000");
    		harga = 20000;
    	}
    	case 3 -> {
    		System.out.println("Harga nasi kuning Rp.10000");
    		harga = 10000;
    	}
    	case 4 -> {
    		System.out.print("Masukkan nama makanan >> "); 
    		String customFood = IO.scanner.nextLine();
    		harga = calculateLen(customFood);
    		System.out.println("Harga "+ customFood +" Rp."+ harga);
    		}
    	}
    	customer.pay(harga);   
    }
    
    private long calculateLen(String order) {
      // 1500 perak for each character XD
      return order.trim().length() * 1500L;
  }
    
//    private void deliver() {
//        System.out.print("Masukkan nama jalannya : ");
//        destination = scanner.nextLine();
//        deliverPrice = calculateDistance(destination);
//        System.out.println("Harga ke " + destination + " adalah : Rp." + deliverPrice);
//        // sistem pembayaran disini
//        customer.pay(deliverPrice);
//    }
//    
//    
}
