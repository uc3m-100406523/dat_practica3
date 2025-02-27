package activities.control;

import activities.view.*;
import activities.model.*;

public class AccessControl {

    String login;
    DBInteraction db;
    private static final String MANAGER_PASSWORD = "manager";

    public AccessControl(DBInteraction dbparam) throws Exception {
        db = dbparam;
    }

    public String getlogin() {
        return login;
    }

    // This method returns 'true' if the password of the manager is correct
    // or if the pair login/password of the client is correct
    public boolean authentication(String option) throws Exception {

        boolean auth = false;
        InputOutput io = new InputOutput();

        // In case the introduced option is for the manager
        if (option.equals("A")) {
            // Print message to request the password
            io.writemanagerauthentication();
            // Read the password
            String pwd = io.read();
            // Check if the password is the one of the manager (configured in the source code)
            if (pwd.equals(MANAGER_PASSWORD)) {
                return true;
            } else {
                // Print a message indicating that the manager password was incorrect
                io.writepwdmanager();
                return false;
            }
        } // In case the introduced option is for the client
        else if (option.equals("B")) {

            // Print message to request the login
            io.writelogin();
            // Read the login
            login = io.read();
            // Print message to request the password
            io.writepwd();
            // Read the password
            String pwd = io.read();
            // Interact with the database to know if the pair login/password exists in the CLIENTS table
            auth = db.authentication(login, pwd);
            if (auth == false) {
                // Print a message indicating that the client authentication info (login/password) was incorrect
                io.writeincorrectauth();
            }
        }
        return auth;
    }
}

