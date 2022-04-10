package aggregation;

import Composition.Customer;
import helper.Custom;
import helper.IO;
import resource.SistemPembayaran;

public class Jago extends SistemPembayaran {

    @Override
    public void isiSaldo(Customer customer) {
    	System.out.print("Nominal >> ");
    	long nominal;
    	try {
    		nominal = Integer.parseInt(IO.scanner.nextLine());
    	}catch (NumberFormatException e) 
    	{System.out.println("Masukkan angka!");return;};
    	customer.jago.saldo += nominal;
    	System.out.println("Terimakasih sudah top up, saldo kamu sekarang "+ customer.jago.saldo);
    	Custom.enterToContinue();
    }

    @Override
    public void bayar(long harga, Customer customer) {
    	customer.jago.saldo -= harga;
    }
    
    @Override
    public void buatCard(Customer customer) {
    	if(customer.jago != null) {
    		System.out.println("Kamu sudah memiliki alat pembayaran ini");
    		Custom.enterToContinue();
    		return;
    	}
    	customer.jago = new Jago();
    	System.out.println("Kartu berhasil dibuat");
    	Custom.enterToContinue();
    }
}
