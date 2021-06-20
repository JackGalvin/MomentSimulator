package Project;

public class ForceClass {
    private float magnitude;
    private boolean antiClockWise;
    private double angle;
    private double position;
    private double moment;
    public static double pivotPosition; //static makes pivot position for all forces global.

    public ForceClass(float magnitude,boolean antiClockWise,double angle,double position){
        this.magnitude = magnitude;
        this.antiClockWise = antiClockWise;
        this.angle = angle;
        this.position = position;
        this.moment = getMoment();
    }

    public float getMagnitude() {
        return magnitude;
    }
    public boolean isAntiClockWise() {
        return antiClockWise;
    }
    public double getAngle() {
        return angle;
    }
    public double getPosition() {
        return position;
    }
    public double getDistanceFromPivot() { 
        return position-pivotPosition;
    }
    public double getMoment() {
        return magnitude*getDistanceFromPivot();
    }
}
