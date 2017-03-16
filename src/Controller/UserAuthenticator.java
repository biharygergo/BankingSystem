package Controller;

import Model.Customer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import sun.misc.IOUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Gergo on 2017. 03. 16..
 */
public class UserAuthenticator {
    private static UserAuthenticator ourInstance = new UserAuthenticator();

    public static UserAuthenticator getInstance() {
        return ourInstance;
    }

    private UserAuthenticator() {
        //retrieveUsers();
    }

    private ArrayList<Customer> customers = new ArrayList<>();

    private Customer findCustomer(String username) throws Exception {
        for (Customer customer : customers) {
            if (customer.getName().equals(username))
                return customer;
        }
        throw new Exception("User not found!");
    }

    public Customer tryLogin(String username, String password) throws Exception {
        try {
            Customer customer = findCustomer(username);
            if (customer.getPassword().equals(password)) {
                return customer;
            } else {
                return null;
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        //saveUsers();
    }

    public void listCustomers() {
        int i = 0;
        for (Customer customer : customers) {
            System.out.println(i + ") Name: " + customer.getName() + " Account No.: " + customer.getId());
        }
    }

    public Customer getCustomerFromId(long id) throws Exception {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        throw new Exception("Account number is invalid!");
    }

    /*
    public void saveUsers() {
        Gson gson = new Gson();
        Type listOfCustomers = new TypeToken<ArrayList<Customer>>() {
        }.getType();
        String json = gson.toJson(customers);
        try {
            gson.toJson(customers, listOfCustomers, new FileWriter("savedata.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        //Encryption would go here
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("savedata.txt"), "utf-8"))) {
            writer.write("json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    //}

    /*
    public void retrieveUsers() {

        Gson gson = new Gson();
        try {
            Type listOfCustomers = new TypeToken<ArrayList<Customer>>() {
            }.getType();
            ArrayList<Customer> customersRetrieved = gson.fromJson(new FileReader("savedata.json"), listOfCustomers);
            if (customersRetrieved != null)
                customers = customersRetrieved;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /*
        try(BufferedReader br = new BufferedReader(new FileReader("savedata.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        */
    //}


}
