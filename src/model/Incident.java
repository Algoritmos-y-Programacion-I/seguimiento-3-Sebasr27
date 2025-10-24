package model;


import java.time.LocalDate;
public class Incident {

    private LocalDate dateReport;
    private String description;
    private boolean solution;
    private int solutionHours;


    public Incident (LocalDate dateReport, String description){

        this.DateReport = dateReport;
        this.description = description;
        this.solution = false;
        this.solutionHours = 0;

    }

    public LocalDate getDateReport() {
        return DateReport;
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

    

