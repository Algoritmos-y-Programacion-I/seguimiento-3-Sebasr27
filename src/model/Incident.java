package model;


import java.time.LocalDate;


/*
 * ANALISIS
 * Descripcion: Incidente reportado a un computador con fecha, descripcion
 * y estado de solucion .
 */
public class Incident {


    private LocalDate dateReport; // no null
    private String description; // no vacia
    private boolean solution;  // false por defecto
    private int solutionHours;


    public Incident (LocalDate dateReport, String description){

        this.dateReport = dateReport;
        this.description = description;
        this.solution = false;
        this.solutionHours = 0;

    }

    public LocalDate getDateReport() {
        return dateReport;
    }
    
    public void setDateReport(LocalDate dateReport){
        this.dateReport = dateReport;
    }

    public String getDescrition(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public boolean getSolution(){
        return solution;
    }

    public void setSolution(boolean solution){
        this.solution = solution;
    }

    public int getSolutionHours(){
        return solutionHours;
    }

    public void setSolutionHours(int solutionHours){
        this.solutionHours = solutionHours;
    }

}

    

