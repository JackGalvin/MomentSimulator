package SinglePivotSandbox;

import java.util.ArrayList;

public class currentSimulation {
    //private ForceClass workingMemory;
    //private String workingTransaction;
    private double sumOfACW = 0;
    private double sumOfCW = 0;
    private double resultantMoment; //AC
    private ArrayList<forceClass> forces;
    public static double plankLength;
    public static double pivotPosition;

    //constructor
    public void currentSimulation(){
        this.sumOfACW = 0;
        this.sumOfCW = 0;
        this.resultantMoment = sumOfCW - Math.abs(sumOfACW);
        this.forces = new ArrayList<>();
    }

    public void addForce(forceClass Force) {
        forces.add(Force);
        //workingTransaction = "Add"; //haven't set working memory because undo is delete is add
        if (Force.getMoment()>0){
            sumOfCW = sumOfCW + Force.getMoment();
        } else if (Force.getMoment()<0){
            sumOfACW = sumOfACW + Force.getMoment();
        }
        resultantMoment = sumOfCW - Math.abs(sumOfACW); //RM will be in terms of CW
        //guiCreation.updateInformation();
        System.out.println("resultant moment = " + resultantMoment);
        System.out.println("CW moment = " + sumOfCW);
        System.out.println("ACW moment = " + sumOfACW);
    }

    //getters
    public double getSumOfACW() { //why do I have to add static to all these functions?
        return sumOfACW;
    }
    public double getSumOfCW() {
        return sumOfCW;
    }
    public double getResultantMoment() {
        return resultantMoment;
    }
    public ArrayList<forceClass> getForces() {
        return forces;
    }
}