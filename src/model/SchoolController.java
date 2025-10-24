package model;

import java.time.LocalDate;
import java.util.Arraylist;

public class SchoolController {
    private String name;
    private int hoursSpentSupport;

    public static final int FLOORS = 5;
    public static final int COL = 10;
    public static final int HOURMAXSUPPORT = 100;

    private Computer [][] computerMatrix;

    public SchoolController(String name) {
        this.name = name;
        this.hoursSpentSupport = 0;
        computerMatrix = new Computer[FLOORS][COL];
    }

    public String addComputer(String serial, boolean nextWindow, int floor, int column){
        if (computerMatrix [¨floor][Column] != mull) {
            return "YA EXISTE UN COMPUTADOR EN ESA POSICION.";

        }


    }


    /*
     * ATENCION !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * Agregue los atributos (relaciones) necesarios para satisfacer los
     * requerimientos.
     */

    public SchoolController() {

    }

    /*
     * ATENCION !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * Los siguientes metodos estan incompletos.
     * Añada los metodos que considere hagan falta para satisfacer los
     * requerimientos.
     * Para cada metodo:
     * Agregue los parametros y retorno que sean pertinentes.
     * Agregue la logica necesaria (instrucciones) para satisfacer los
     * requerimientos.
     */
    public void agregarComputador() {

    }

    public void agregarIncidenteEnComputador() {

    }

    public void getComputerList() {

    }

}
