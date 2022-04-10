package Composition;

import aggregation.Gopay;
import static helper.IO.clearScreen;
import aggregation.Jago;
import helper.Custom;
import resource.SistemPembayaran;

public class Customer {
    public String address = "";
    public String phoneNumber;
    public String username;
    public String password;
    public Gopay gopay;
    public Jago jago;

    public Customer(String username, String phoneNumber, String password) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public void pay(long harga) {

        Integer menu = Custom.getChoice(() -> {
            System.out.println("\nMau bayar pakai apa?");
            System.out.println("1. Gopay");
            System.out.println("2. Jago");
            System.out.println("3. Tunai");
            System.out.println("0. Gajadi pesen deh");
            System.out.print(">> ");
        }, 3);

        switch (menu) {
            case 1 -> payWith(gopay, harga);
            case 2 -> payWith(jago, harga);
            case 3 -> {
            	System.out.println("Terimakasih sudah memesan!, mohon ditunggu");
            	Custom.enterToContinue();
            }
        }
    }
   
	public void payWith(SistemPembayaran alatPembayaran,long harga) {
    	if(alatPembayaran == null) {
    		System.out.println("Kamu belum punya alat pembayaran ini.");
    		Custom.enterToContinue();
    		return;
    	}
    	if(alatPembayaran.saldo < harga) {
        	System.out.println("Saldo anda sisa " + alatPembayaran.saldo + " dan tidak mencukupi, silakan top up");
        	Custom.enterToContinue();
        	return;
    	}
    	alatPembayaran.bayar(harga, this);
    	System.out.println("Terimakasih sudah memesan, mohon ditunggu, Saldo kamu sisa " + alatPembayaran.saldo);
    	Custom.enterToContinue();
    }

    private void makeCard() {
        int menu = Custom.getChoice(() -> {
            System.out.println("kamu mau buat alat pembayaran gopay atau jago?");
            System.out.println("1. ya");
            System.out.println("0. ngga");
        }, 1);
        if (menu == 1) {
        	int menu2 = Custom.getChoice(() -> {
            System.out.println("1. Gopay");
            System.out.println("2. Jago");
        	}, 2);
        	if(menu2 == 1) createGopay();
        	else if (menu2 == 2) createJago();
        }
    }

    // create gopay card
    private void createGopay() {
        this.gopay = new Gopay();
        System.out.println("Gopay berhasil dibuat!");
        
    }

    // create jago card
    private void createJago() {
        this.jago = new Jago();
        System.out.println("Jago berhasil dibuat!");
    }
}
