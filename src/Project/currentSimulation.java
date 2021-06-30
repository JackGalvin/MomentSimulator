package Project;

import java.util.ArrayList;

public class currentSimulation {
    private ForceClass workingMemory;
    private String workingTransaction;
    private double sumOfACW = 0;
    private double sumOfCW = 0;
    private double resultantMoment; //AC
    private ArrayList<ForceClass> forces;
    public static double plankLength;
    public static double pivotPosition;


    public currentSimulation(double pivotPosition, ArrayList<ForceClass> forces) {
        this.pivotPosition = pivotPosition;
        this.forces = forces;
        for (int i = 0; i < forces.size(); i++){
            if (forces.get(i).getMoment()<0){ //less than 0, takes all - values,  ACW moments stored as -  because distance = - 
                sumOfACW = sumOfACW + forces.get(i).getMoment();
            }
        }
        for (int i = 0; i < forces.size(); i++){
            if (forces.get(i).getMoment()>0){ //more than 0, takes all + values,                   
                sumOfCW = sumOfCW + forces.get(i).getMoment();
            }
        } 
        resultantMoment = sumOfCW + sumOfACW; //RM in CW direction. 
    }

    public void addForce(ForceClass Force) {
        forces.add(Force);
        workingTransaction = "Add"; //haven't set working memory because undo is delete is add
    }

    public void deleteForce(int index) { // forces index will be used to identify them, index will be given to them on creation. Might not update indexs at all
        forces.remove(index);
        workingTransaction = "Del";
        workingMemory = forces.get(index);
    }

    public void undoAddForce() {
        this.deleteForce(forces.size()-1); //last index in the array.
    }

    public void undoDeleteForce() {
       this.addForce(workingMemory); 
    }

    /*
    public void editForce() {

    }

    public void undoEditForce() {

    }
    */

    //getters
    public double getPivotPosition() {
        return pivotPosition;
    }
    public double getSumOfACW() {
        return sumOfACW;
    }
    public double getSumOfCW() {
        return sumOfCW;
    }
    public double getResultantMoment() {
        return resultantMoment;
    }
    public ArrayList<ForceClass> getForces() {
        return forces;
    }
}
