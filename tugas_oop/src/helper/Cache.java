package helper;

import Composition.Customer;

public class Cache {
    private static Customer loggedCustomer;

    public static Customer getLoggedCustomer() {
        return loggedCustomer;
    }

    public static void setLoggedCustomer(Customer loggedCustomer) {
        Cache.loggedCustomer = loggedCustomer;
    }
}
