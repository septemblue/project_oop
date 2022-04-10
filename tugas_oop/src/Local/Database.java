package Local;

import Composition.Customer;

import java.util.HashMap;

public class Database {
    // Map to contain customers
    public static HashMap<String , Customer> customers = new HashMap() {{
        put("admin", new Customer("admin", "08123232", "admin"));
    }};


}
