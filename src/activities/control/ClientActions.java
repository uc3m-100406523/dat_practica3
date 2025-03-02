package activities.control;

import activities.view.*;
import activities.model.*;

import java.util.ArrayList;

public class ClientActions {

    DBInteraction db;
    InputOutput io;

    public ClientActions(DBInteraction dbparam) throws Exception {

        db = dbparam;
        io = new InputOutput();
    }
    
    public void listallact() throws Exception {

        ArrayList data = new ArrayList();
        data = db.listallact();
        io.writeActArray(data);
    }

    public void listallpav() throws Exception {

        ArrayList data = new ArrayList();
        data = db.listallpav();
        io.writePavArray(data);
    }

    public void listactfreeplaces() throws Exception {

        ArrayList data = new ArrayList();
        data = db.listactfreeplaces();
        io.writeActArray(data);
    }

    public void listactprice() throws Exception {

        ArrayList data = new ArrayList();
        io.writeactprice();
        String prices = io.read();
        float price = Float.parseFloat(prices);
        data = db.listactprice(price);
        io.writeActArray(data);
    }

    public void listactpav() throws Exception {

        ArrayList data = new ArrayList();
        io.writeactpavname();
        String nameio = io.read();
        data = db.listactpav(nameio);
        io.writeActArray(data);
    }

    public void listactname() throws Exception {

        ArrayList data = new ArrayList();
        io.writeactname();
        String nameio = io.read();
        data = db.listactname(nameio);
        io.writeActArray(data);
    }

    public void listactusr(String login) throws Exception {

        ArrayList data = new ArrayList();
        data = db.listactusr(login);
        io.writeActArray(data);
    }

    public void regactivity(String login) throws Exception {

        io.writeactid();
        String id = io.read();
        db.regactivity(login, id);
    }

    public void unregactivity(String login) throws Exception {

        io.writeactid();
        String id = io.read();
        db.unregactivity(login, id);
    }

    // Nuevo método para listar actividades entre dos fechas
    public void listactbydates() throws Exception {
        io.write("Ingrese la fecha de inicio (YYYY-MM-DD): ");
        String startDate = io.read();
        io.write("Ingrese la fecha de fin (YYYY-MM-DD): ");
        String endDate = io.read();

        ArrayList data = db.listactbydates(startDate, endDate);
        io.writeActArray(data);
    }

    public void listUsersWithMinCommonActivities(String login) throws Exception {
        io.write("Ingrese el número mínimo de actividades en común: ");
        String minActivitiesStr = io.read();
        int minActivities = Integer.parseInt(minActivitiesStr);

        ArrayList data = db.listUsersWithMinCommonActivities(login, minActivities);

        if (data.isEmpty()) {
            io.write("No hay usuarios que coincidan en el número mínimo de actividades especificado.");
        } else {
            io.write("Usuarios con los que coincidís en al menos " + minActivities + " actividades:");
            for (Object obj : data) {
                Client client = (Client) obj;
                io.write(client.getname() + " " + client.getsurname()); // Usar getname() y getsurname()
            }
        }
    }
}