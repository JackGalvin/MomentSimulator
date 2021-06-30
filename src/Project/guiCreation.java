package Project;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;

public class guiCreation{

    public static VBox MomentsMenu = new VBox(10);

    public static HBox addPlankConfig(){
        HBox plankConfig = new HBox();
        plankConfig.setStyle("-fx-background-color: #9933FF;");
        Label labelPlank = new Label("Plank Length");
        TextField sizeOfPlank = new TextField("Plank Length");
        Button submitPlank = new Button("Submit");
        submitPlank.setOnAction(e -> {
            currentSimulation.plankLength = Float.parseFloat(sizeOfPlank.getText()); //add double check //set plank length to a global variable under the class 
            plankConfig.getChildren().removeAll(labelPlank,sizeOfPlank,submitPlank);
            Label plankValue = new Label("Plank length = " + currentSimulation.plankLength);
            plankConfig.getChildren().add(plankValue);
            MomentsMenu.getChildren().add(addPivotConfig());
        });
        plankConfig.getChildren().addAll(labelPlank,sizeOfPlank,submitPlank);
        return plankConfig; //return so that they can be added to MomentsMenu
    }

    public static HBox addPivotConfig(){
        HBox pivotConfig = new HBox();
        pivotConfig.setStyle("-fx-background-color: #FF33BB;");
        Slider pivotPositionSlider = new Slider(); 
        pivotPositionSlider.setMin(0-(currentSimulation.plankLength/2)); //set min value. 
        pivotPositionSlider.setMax(currentSimulation.plankLength/2); //set max value.
        pivotPositionSlider.setValue(0); //set initial value to middle 0.
        
        Button submitPivot = new Button("Submit");
        submitPivot.setOnAction(e -> {
            currentSimulation.pivotPosition = pivotPositionSlider.getValue();
            pivotConfig.getChildren().removeAll(pivotPositionSlider,submitPivot);
            Label pivotValue = new Label("Pivot at: " + currentSimulation.pivotPosition);
            pivotConfig.getChildren().add(pivotValue);
            MomentsMenu.getChildren().add(addForcesConfig());
        });
        pivotConfig.getChildren().addAll(pivotPositionSlider,submitPivot);
        return pivotConfig; //return so that they can be added to MomentsMenu
    }

    public static VBox addForcesConfig(){
        VBox forcesConfig = new VBox(10);
        forcesConfig.setStyle("-fx-background-color: #33FFE0;");
        Button createNewForce = new Button("add new Force");
        createNewForce.setOnAction(e -> {forcesConfig.getChildren().add(guiCreation.addForceInput(currentSimulation.plankLength,currentSimulation.pivotPosition));});
        forcesConfig.getChildren().add(createNewForce);
        return forcesConfig; //return so that they can be added to MomentsMenu
    }

    public static HBox addForceInput(double plankLength, double pivotPosition){
        HBox forceConfig = new HBox();
        TextField magInput = new TextField("Magnitude");

        //angle input and all attributes
        Slider angleInput = new Slider(); 
        angleInput.setMin(0); 
        angleInput.setMax(180); 
        angleInput.setValue(90);

        //position input and all attributes
        Slider positionInput = new Slider(); 
        positionInput.setMin(0-(plankLength/2)); 
        positionInput.setMax(plankLength/2); 
        positionInput.setValue(0);

        //submit force button and action
        Button submitForce = new Button("submit force");
        submitForce.setOnAction(e -> {
            ForceClass createdForce = new ForceClass(Integer.parseInt(magInput.getText()),angleInput.getValue(),positionInput.getValue());
            System.out.println("Magnitude = " + createdForce.getMagnitude());
            System.out.println("Angle = " + createdForce.getAngle());
            System.out.println("Position = " + createdForce.getPosition());
            System.out.println("Distance from Pivot = " + createdForce.getDistanceFromPivot());
            System.out.println("Moment = " + createdForce.getMoment());
            //write code for adding it to the currentSimulation class
        });
        forceConfig.getChildren().addAll(magInput,angleInput,positionInput,submitForce);
        return forceConfig;
    }

}