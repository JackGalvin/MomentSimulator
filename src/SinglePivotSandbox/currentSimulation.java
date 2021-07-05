package SinglePivotSandbox;

import java.util.ArrayList;

public class currentSimulation {
    //private ForceClass workingMemory;
    //private String workingTransaction;
    private double sumOfACW = 0;
    private double sumOfCW = 0;
    private double resultantMoment; //AC
    private forceClass forces[] = new forceClass[5];
    public static double plankLength;
    public static double pivotPosition;

    //constructor
    public void currentSimulation(){
        this.sumOfACW = 0;
        this.sumOfCW = 0;
        this.resultantMoment = sumOfCW - Math.abs(sumOfACW);
        //this.forces = new ArrayList<>(0);
    }

    public void addForce(forceClass Force) {
        //forces.add(Force);
        //workingTransaction = "Add"; //haven't set working memory because undo is delete is add
        forces[0] = Force; //forces are not technically being stored the values are changing however.
        if (Force.getMoment()>0){
            sumOfCW = sumOfCW + Force.getMoment();
        } else if (Force.getMoment()<0){
            sumOfACW = sumOfACW + Force.getMoment();
        }
        resultantMoment = sumOfCW - Math.abs(sumOfACW); //RM will be in terms of CW
        System.out.println(this.getSumOfCW() + "CW");
        System.out.println(this.getSumOfACW() + "ACW");
        System.out.println(this.getResultantMoment() + "RM");
        guiCreation.updateInformation();
    }

    //getters
    public double getSumOfACW() {
        return sumOfACW;
    }
    public double getSumOfCW() {
        return sumOfCW;
    }
    public double getResultantMoment() {
        return resultantMoment;
    }
    /*
    public ArrayList<forceClass> getForces() { //changed to array not arraylist fix later
        return forces;
    }
    */
}