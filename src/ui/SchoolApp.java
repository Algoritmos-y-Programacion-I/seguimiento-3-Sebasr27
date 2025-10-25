package ui;

import java.time.LocalDate;
import java.util.Scanner;

import model.Computer;
import model.SchoolController;

/*
 * ANALISIS
 * Descripcion: este uinos va a permitir:
 * 1) Agregar computador, 2) Reportar incidente, 3) Solucionar incidente,
 * 4) Ver computador con mas incidentes, 5) Ver mapa, 6) Ver horas soporte.
 */

public class SchoolApp {

    private Scanner sc; new Scanner(System.in);
    private SchoolController controller; new SchoolController("Mi Escuela");

        public static void main(String[] args) {
        new SchoolApp().run();
    }

    private void run() {
        int op;
        do {
            menu();
            op = readInt("Opción: ");
            if (op == 1) addComputer();
            else if (op == 2) reportIncident();
            else if (op == 3) solveIncident();
            else if (op == 4) showMostIncidents();
            else if (op == 5) System.out.println(controller.buildingMap());
            else if (op == 6) System.out.println("Horas de soporte: " + controller.getHourSpentSupport() +
                                                  "/" + SchoolController.HOURMAXSUPPORT);
            else if (op == 7) System.out.println("ADIOS");
            else System.out.println("Opción inválida");
        } while (op != 7);
    }

    private void menu() {
        System.out.println("\n=== Menú de SchoolController ===");
        System.out.println("1) Agregar computador");
        System.out.println("2) Reportar incidente");
        System.out.println("3) Resolver incidente");
        System.out.println("4) Ver computador con más incidentes");
        System.out.println("5) Ver mapa del edificio");
        System.out.println("6) Ver horas de soporte");
        System.out.println("7) Salir");
    }

    private int readInt(String msg) {
        System.out.print(msg);
        while (!sc.hasNextInt()) { System.out.print("Número entero: "); sc.next(); }
        int v = sc.nextInt(); sc.nextLine(); return v;
    }
    private String readLine(String msg) { System.out.print(msg); return sc.nextLine(); }
    private LocalDate readDate(String msg) {
        while (true) {
            try { return LocalDate.parse(readLine(msg)); }
            catch (Exception e) { System.out.println("Formato inválido. Usa: YYYY-MM-DD"); }
        }
    }
    private boolean readBoolean(String msg) {
        while (true) {
            String s = readLine(msg + " (s/n): ").trim().toLowerCase();
            if (s.equals("s")) return true;
            if (s.equals("n")) return false;
        }
    }

   

    private void addComputer() {
        String serial = readLine("Número de serie: ");
        boolean nextW = readBoolean("¿Tiene próxima ventana de soporte?");
        int floor = readInt("Piso (1.." + SchoolController.FLOORS + "): ");

        if (controller.existsSerial(serial)) {
            System.out.println("El serial ya existe.");
            return;
        }
        int col = controller.addComputer(serial, nextW, floor);
        if (col >= 0) System.out.println("Computador agregado en Piso " + floor + ", Columna " + col);
        else System.out.println("No hay espacio en ese piso o el serial está duplicado.");
    }

    private void reportIncident() {
        String serial = readLine("Número de serie: ");
        int floor = readInt("Piso (1.." + SchoolController.FLOORS + "): ");
        int col = readInt("Columna (0.." + (SchoolController.COL - 1) + "): ");
        String desc = readLine("Descripción: ");
        LocalDate date = readDate("Fecha (YYYY-MM-DD): ");

        boolean ok = controller.reportIncident(serial, floor, col, date, desc);
        System.out.println(ok ? "Incidente reportado." : "No se encuentra el computador o los datos no coinciden.");
    }

    private void solveIncident() {
        int floor = readInt("Piso (1.." + SchoolController.FLOORS + "): ");
        int col = readInt("Columna (0.." + (SchoolController.COL - 1) + "): ");
        Computer pc = controller.getComputer(floor, col);
        if (pc == null) { System.out.println("No hay computador en esa posición."); return; }
        if (pc.getIncidentsCount() == 0) { System.out.println("No hay incidentes."); return; }

        System.out.println("Incidentes para " + pc.getSerialNumber() + ":");
        for (int i = 0; i < pc.getIncidentsCount(); i++) {
            System.out.println(i + ") " + pc.getIncident(i));
        }
        int idx = readInt("Elige el índice del incidente: ");
        boolean solved = readBoolean("¿Marcar como solucionado?");
        int hours = readInt("Horas de solución: ");

        boolean ok = controller.solveIncident(floor, col, idx, solved, hours);
        if (ok) System.out.println("Incidente actualizado.");
        else System.out.println("No se pudo actualizar (datos inválidos o horas exceden el límite).");
    }

    private void showMostIncidents() {
        Computer top = controller.computerWithMostIncidents();
        if (top == null) System.out.println("No hay computadores registrados.");
        else {
            System.out.println("Computador con más incidentes:");
            System.out.println("- Serial: " + top.getSerialNumber());
            System.out.println("- Incidentes: " + top.getIncidentsCount());
            System.out.println("- Tiene ventana de soporte: " + top.isNextWindow());
        }
    }
}

   



