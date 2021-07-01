package SinglePivotSandbox;

import java.util.ArrayList;

public class currentSimulation {
    //private ForceClass workingMemory;
    //private String workingTransaction;
    private static double sumOfACW = 0; //added static because addForce required to be static I dont know why ask clive!!!!!!!!!!!!!!!!!!!
    private static double sumOfCW = 0;
    private static double resultantMoment; //AC
    private static ArrayList<ForceClass> forces;
    public static double plankLength;
    public static double pivotPosition;

    public static void addForce(ForceClass Force) { //NEED TO ADD AN UPDATE TO GUI in this and below
        forces.add(Force);
        //workingTransaction = "Add"; //haven't set working memory because undo is delete is add
        if (Force.getMoment()>0){
            sumOfCW = sumOfCW + Force.getMoment();
        } else if (Force.getMoment()<0){
            sumOfACW = sumOfACW + Force.getMoment();
        }
        resultantMoment = sumOfCW - sumOfACW;
        guiCreation.updateInformation();
    }

    public static void deleteForce(int index) { // forces index will be used to identify them, index will be given to them on creation. Might not update indexs at all
        forces.remove(index);
        //workingTransaction = "Del";
        //workingMemory = forces.get(index);
        if (forces.get(index).getMoment()>0){
            sumOfCW = sumOfCW + forces.get(index).getMoment();
        } else if (forces.get(index).getMoment()<0){
            sumOfACW = sumOfACW + forces.get(index).getMoment();
        }
        resultantMoment = sumOfCW - sumOfACW;
    }

    //getters
    public static double getSumOfACW() { //why do I have to add static to all these functions?
        return sumOfACW;
    }
    public static double getSumOfCW() {
        return sumOfCW;
    }
    public static double getResultantMoment() {
        return resultantMoment;
    }
    public ArrayList<ForceClass> getForces() {
        return forces;
    }

        /*
    public void undoAddForce() {
        this.deleteForce(forces.size()-1); //last index in the array.
    }

    public void undoDeleteForce() {
       this.addForce(workingMemory); 
    }

    public void editForce() {

    }

    public void undoEditForce() {

    }
    */
}
