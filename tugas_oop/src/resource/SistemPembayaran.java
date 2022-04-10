package resource;

import Composition.Customer;
import helper.IO;

// contoh abstract class
public abstract class SistemPembayaran {
    public long saldo = 0;
    public String name = "alat pembayaranku";

    public abstract void isiSaldo(Customer customer);
		// TODO Auto-generated method stub
    public abstract void bayar(long harga, Customer customer);
    
    public abstract void buatCard(Customer customer);

//    protected void transfer() {
//
//    }

}
