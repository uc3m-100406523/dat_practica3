package activities.control;

import activities.view.*;
import activities.model.*;

import java.util.ArrayList;

public class ManagerActions {

    DBInteraction db;
    InputOutput io;

    public ManagerActions(DBInteraction dbparam) throws Exception {

        db = dbparam;
        io = new InputOutput();
    }

    public void addusr() throws Exception {

        io.writelogin();
        String login = io.read();
        io.writepwd();
        String pwd = io.read();
        io.writename();
        String name = io.read();
        io.writesurname();
        String surname = io.read();
        io.writeadress();
        String address = io.read();
        io.writephone();
        String phone = io.read();
        db.addusr(login, pwd, name, surname, address, phone);
    }

    public void delusr() throws Exception {

        io.writedelusr();
        String login = io.read();
        db.delusr(login);
    }

    public void addact() throws Exception {

        io.writeactname();
        String name = io.read();
        io.writeactdescription();
        String description = io.read();
        io.writeactinitial();
        String initial = io.read();
        io.writeactprice();
        String prices = io.read();
        float price = Float.parseFloat(prices);
        io.writeactpavname();
        String pavname = io.read();
        io.writeacttotal();
        String totals = io.read();
        int total = Integer.parseInt(totals);
        io.writeactocc();
        String occs = io.read();
        int occ = Integer.parseInt(occs);
        db.addact(name, description, initial, price, pavname, total, occ);
    }

    // Registra la misma actividad varias veces
    public void iteraddact() throws Exception {

        io.writeactname();
        String name = io.read();
        io.writeactdescription();
        String description = io.read();
        io.writeactinitiallist();
        String initialListStr = io.read();
        String initialList[] = initialListStr.split(",");
        String initial;
        io.writeactprice();
        String prices = io.read();
        float price = Float.parseFloat(prices);
        io.writeactpavname();
        String pavname = io.read();
        io.writeacttotal();
        String totals = io.read();
        int total = Integer.parseInt(totals);
        io.writeactocc();
        String occs = io.read();
        int occ = Integer.parseInt(occs);
        for(int i=0;i<initialList.length;i++) {
            initial = initialList[i];
            db.addact(name, description, initial, price, pavname, total, occ);
        }
    }

    public void delact() throws Exception {

        io.writedelact();
        String ids = io.read();
        int id = Integer.parseInt(ids);
        db.delact(id);
    }

    public void addpav() throws Exception {

        io.writepavname();
        String pavname = io.read();
        io.writepavlocation();
        String pavlocation = io.read();
        db.addpav(pavname, pavlocation);
    }

    public void delpav() throws Exception {

        io.writedelpav();
        String pavname = io.read();
        db.delpav(pavname);
    }

    public void listallusr() throws Exception {

        ArrayList data = new ArrayList();
        data = db.listallusr();
        io.writeUsrArray(data);
    }    

    public void listallact() throws Exception {

        ArrayList data = new ArrayList();
        data = db.listallact();
        io.writeActArray(data);
    }

    public void listactfreeplaces() throws Exception {

        ArrayList data = new ArrayList();
        data = db.listactfreeplaces();
        io.writeActArray(data);
    }

    public void listallpav() throws Exception {

        ArrayList data = new ArrayList();
        data = db.listallpav();
        io.writePavArray(data);
    }

    public void listUsersWithMinActivities() throws Exception {  // TODO: Fix
        io.writemessage("Ingrese el número mínimo de actividades en común: ");
        String minActivitiesStr = io.read();
        int minActivities = Integer.parseInt(minActivitiesStr);

        ArrayList data = db.listUsersWithMinActivities(minActivities);

        if (data.isEmpty()) {
            io.writemessage("No hay usuarios tengan el número mínimo de actividades especificado.");
        } else {
            io.writemessage("Usuarios con al menos " + minActivities + " actividades:");
            for (Object obj : data) {
                Client client = (Client) obj;
                io.writemessage(client.getname() + " " + client.getsurname()); // Usar getname() y getsurname()
            }
        }
    }
}

