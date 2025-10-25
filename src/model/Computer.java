package model;

import java.util.ArrayList;

/*
 * ANALISIS
 * Descripcion: EL Computador tiene que tener un serial unico 
 */
public class Computer {

    private String serialNumber;                // (String): Número de serie único para identificar al computador.
    private boolean nextWindow;                 // indicamos si el computador tiene una ventana de soporte 
    private ArrayList<Incident> incidents;      

    public Computer(String serialNumber, boolean nextWindow) {
        this.serialNumber = serialNumber;
        this.nextWindow = nextWindow;
        this.incidents = new ArrayList<>();
    }

    public String getSerialNumber() { return serialNumber; }
    public boolean isNextWindow() { return nextWindow; }
    public void setNextWindow(boolean nextWindow) { this.nextWindow = nextWindow; }

    public int getIncidentsCount() { return incidents.size(); }
    public Incident getIncident(int index) { return incidents.get(index); }
    public ArrayList<Incident> getIncidents() { return incidents; }

    /** Agregamos un incidente. */
    public void addIncident(Incident inc) { incidents.add(inc); }

    @Override
    public String toString() {
        return "Computer{" +
                "serial='" + serialNumber + '\'' +
                " nextWindow=" + nextWindow +
                " incidents=" + incidents.size() +
                '}';
    }
}


  