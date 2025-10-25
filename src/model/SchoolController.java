package model;

import java.time.LocalDate;

/*
 * ANALISIS
 * Descripcion: Controlador principal de la escuela. Mantiene una matriz
 * de computadores (5 pisos x 10 columnas, para guardar los computadores ) y controla el registro de equipos,
 * reporte y solucion de incidentes, y el total de horas de soporte.
 */



public class SchoolController {


    private String name;
    private int hourSpentSupport;

    
    public static final int FLOORS = 5;
    public static final int COL = 10;
    public static final int HOURMAXSUPPORT = 100;

    // matrices de los computadores para ordenarlos 
    private Computer[][] computersMatrix = new Computer[FLOORS][COL];

    // constructor
    public SchoolController(String name) {
        this.name = name;
        this.hourSpentSupport = 0;
    }

    

    public String getName() { return name; }
    public int getHourSpentSupport() { return hourSpentSupport; }

    
    public Computer getComputer(int floor, int column) {
        return computersMatrix[floor - 1][column];
    }

    public boolean existsSerial(String serial) {
        for (int f = 0; f < FLOORS; f++) {
            for (int c = 0; c < COL; c++) {
                Computer pc = computersMatrix[f][c];
                if (pc != null && pc.getSerialNumber().equals(serial)) {
                    return true;
                }
            }
        }
        return false;
    }

    /** se retorna el computador con mas incidentes  */
    public Computer computerWithMostIncidents() {
        Computer best = null;
        int max = -1;
        for (int f = 0; f < FLOORS; f++) {
            for (int c = 0; c < COL; c++) {
                Computer pc = computersMatrix[f][c];
                if (pc != null) {
                    int k = pc.getIncidentsCount();
                    if (k > max) { max = k; best = pc; }
                }
            }
        }
        return best;
    }

    
    public String buildingMap() {
        StringBuilder sb = new StringBuilder();
        for (int f = 0; f < FLOORS; f++) {
            sb.append("Floor ").append(f + 1).append(": ");
            for (int c = 0; c < COL; c++) {
                sb.append(computersMatrix[f][c] == null ? "[ ]" : "[X]");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    
    /**
     * se agrega un computador en el piso indicado y lo ubica en la primera columna libre
     * Se devuelve la columna asignada o -1 si llega a fallar.
     */
    public int addComputer(String serialNumber, boolean nextWindow, int floor) {
        if (floor < 1 || floor > FLOORS) return -1;
        if (existsSerial(serialNumber)) return -1;

        for (int c = 0; c < COL; c++) {
            if (computersMatrix[floor - 1][c] == null) {
                computersMatrix[floor - 1][c] = new Computer(serialNumber, nextWindow);
                return c;
            }
        }
        return -1; // ya se quedo sin espacio el piso
    }

   
    public boolean reportIncident(String serialNumber, int floor, int column,
                                  LocalDate date, String description) {
        if (floor < 1 || floor > FLOORS) return false;
        if (column < 0 || column >= COL) return false;

        Computer pc = computersMatrix[floor - 1][column];
        if (pc == null) return false;
        if (!pc.getSerialNumber().equals(serialNumber)) return false;

        pc.addIncident(new Incident(date, description));
        return true;
    }

    /**
     * Marca la solucion de un incidente.
     * - Suma las horas al total de soporte .
     * - Si se llega a exceder , no aplica el cambio y retorna false.
     */
    public boolean solveIncident(int floor, int column, int incidentIndex, boolean solved, int hours) {
        if (floor < 1 || floor > FLOORS) return false;
        if (column < 0 || column >= COL) return false;

        Computer pc = computersMatrix[floor - 1][column];
        if (pc == null) return false;
        if (incidentIndex < 0 || incidentIndex >= pc.getIncidentsCount()) return false;

        // Validamos las horas
        int candidate = hourSpentSupport + Math.max(0, hours);
        if (solved && candidate > HOURMAXSUPPORT) return false;

        pc.getIncident(incidentIndex).setSolution(solved, Math.max(0, hours));
        if (solved) { hourSpentSupport = candidate; }
        return true;
    }
}




 
    



