package SinglePivotSandbox;

public class forceClass {
    private float magnitude;
    private double angle;
    private double position;
    private double moment; //not needed but good for clarification for what the object has.

    public forceClass(float magnitude,double angle,double position){
        this.magnitude = magnitude;
        this.angle = angle;
        this.position = position;
        this.moment = getMoment();
    }

    public float getMagnitude() {
        return magnitude;
    }
    public double getAngle() {
        return angle;
    }
    public double getPosition() {
        return position;
    }
    public double getDisplacementFromPivot() { 
        return position-currentSimulation.pivotPosition;
    }
    public double getMoment() {
        return magnitude*Math.cos(angle)*getDisplacementFromPivot();
    }
}