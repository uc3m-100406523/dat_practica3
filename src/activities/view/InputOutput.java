package activities.view;

import activities.model.*;

import java.io.*;
import java.util.ArrayList;

public class InputOutput {

    BufferedReader keyboard;

    public InputOutput() {
        keyboard = new BufferedReader(new InputStreamReader(System.in));
    }

    // This method reads a line from keyboard
    public String read() throws Exception {
        String result = keyboard.readLine();
        return (result);
    }

    // The rest of the methods of this class write different messages on the screen
    public void writeinitialmenu() {
        System.out.println("A. Manager");
        System.out.println("B. Client");
        System.out.println("C. Register as new Client");
    }

    public void writeActArray(ArrayList data) {
        
        System.out.println("ID NAME DESCRIPTION START_DATE PRICE PAVILLION_NAME TOTAL_PLACES OCCUPIED_PLACES");
        for (int i = 0; i < data.size(); i++) {
            Activity a = (Activity) data.get(i);
            int id = a.getid();
            String name = a.getname();
            String description = a.getdescription();
            String initial = a.getinitial();
            float cost = a.getcost();
            String pavname = a.getpavname();
            int total = a.gettotal();
            int occupied = a.getoccupied();
            System.out.println(id + "  " + name + "  " + description + "  " + initial + "  " + cost + "  " + pavname + "  " + total + "  " + occupied);
            System.out.println(" ");
        }
    }

    public void writeUsrArray(ArrayList data) {
        
        System.out.println("LOGIN PASSWORD NAME SURNAME ADDRESS PHONE");
        for (int i = 0; i < data.size(); i++) {
            Client c = (Client) data.get(i);
            String login = c.getlogin();
            String password = c.getpassword();
            String name = c.getname();
            String surname = c.getsurname();
            String address = c.getaddress();
            String phone = c.getphone();
            System.out.println(login + "  " + password + "  " + name + "  " + surname + "  " + address + "  " + phone);
            System.out.println(" ");
        }
    }

    public void writePavArray(ArrayList data) {
        System.out.println("NAME  LOCATION");
        for (int i = 0; i < data.size(); i++) {
            Pavillion p = (Pavillion) data.get(i);
            String name = p.getname();
            String location = p.getlocation();
            System.out.println(name + "  " + location);
            System.out.println(" ");
        }
    }

    public void writelogin() {
        System.out.println("Please enter your login");
    }

    public void writepwd() {
        System.out.println("Please enter your password");
    }

    public void writename() {
        System.out.println("Please enter your first name");
    }

    public void writesurname() {
        System.out.println("Please enter your surname");
    }

    public void writeadress() {
        System.out.println("Please enter your address");
    }

    public void writephone() {
        System.out.println("Please enter your telephone number");
    }

    public void writemanagerauthentication() {
        System.out.println("Please enter the manager's password");
    }

    public void writepwdmanager() {
        System.out.println("You have introduced an incorrect password for the manager");
    }

    public void writeincorrectauth() {
        System.out.println("You have introduced an incorrect login or password");
    }

    public void writeManager() {
        System.out.println("1. Add Client");
        System.out.println("2. Delete Client");
        System.out.println("3. Add Activity");
        System.out.println("4. Add Activity to several dates");
        System.out.println("5. Delete Activity");
        System.out.println("6. Add Pavillion");
        System.out.println("7. Delete Pavillion");
        System.out.println("8. List all Clients");
        System.out.println("9. List Clients with a minimum number of activities");
        System.out.println("10. List all Activities");
        System.out.println("11. List all Pavillions");
        System.out.println("12. List the activities for which there are currently free places");
        System.out.println("13. Exit the application");
    }

    public void writeClient() {
        System.out.println("1. List all Activities");
        System.out.println("2. List all Pavillions");
        System.out.println("3. List activities for which there are currently free places");
        System.out.println("4. List activities for which there are free places costing less than a certain amount");
        System.out.println("5. List activities for which there are free places that take place in a certain pavillion");
        System.out.println("6. List activities in a date range");
        System.out.println("7. Show information about a particular activity");
        System.out.println("8. List the names of the activities to which I am subscribed");
        System.out.println("9. Subscribe to an activity");
        System.out.println("10. Unsubscribe from an activity");
        System.out.println("11. List users with whom you share activities");
        System.out.println("12. Exit the application ");
    }

    public void writedelusr() {
        System.out.println("Introduce the login of the user that you want to delete");
    }

    public void writeactname() {
        System.out.println("Introduce the name of the activity");
    }

    public void writeactdescription() {
        System.out.println("Introduce the description of the activity");
    }

    public void writeactinitial() {
        System.out.println("Introduce the start day of the activity");
    }

    public void writeactinitiallist() {
        System.out.println("Introduce the start days of the activity separated by commas");
    }

    public void writeactprice() {
        System.out.println("Introduce the price of the activity");
    }

    public void writeactpavname() {
        System.out.println("Introduce the name of the pavillion in which the activity is to take place");
    }

    public void writeacttotal() {
        System.out.println("Introduce the total number of places of the activity");
    }

    public void writeactocc() {
        System.out.println("Introduce the initial number of occupied places of the activity");
    }

    public void writedelact() {
        System.out.println("Introduce the identifier of the activity that you want to delete");
    }

    public void writedelpav() {
        System.out.println("Introduce the name of the pavillion that you want to delete");
    }

    public void writepavname() {
        System.out.println("Introduce the pavillion name");
    }

    public void writepavlocation() {
        System.out.println("Introduce the pavillion location");
    }

    public void writemessage(String message) {
        System.out.println(message);
	}

    public void writeactid() {
        System.out.println("Introduce the id of the activity to which you wish to subscribe or unsubscribe");
    }
}
