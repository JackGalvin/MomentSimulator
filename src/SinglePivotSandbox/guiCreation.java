package SinglePivotSandbox;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.text.NumberFormat;

public class guiCreation{

    public static VBox MomentsMenu = new VBox(10);
    public static Label sumOfCWLabel = new Label();
    public static Label sumOfACWLabel = new Label();
    public static Label resultantMomentLabel = new Label();


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
        pivotPositionSlider.setMin(0-(currentSimulation.plankLength/2)); pivotPositionSlider.setMax(currentSimulation.plankLength/2); pivotPositionSlider.setValue(0);  //setting sliders max min and initial value
        TextField pivotPositionTextField = new TextField();
        pivotPositionTextField.textProperty().bindBidirectional(pivotPositionSlider.valueProperty(), NumberFormat.getNumberInstance());
        
        Button submitPivot = new Button("Submit");
        submitPivot.setOnAction(e -> {
            currentSimulation.pivotPosition = pivotPositionSlider.getValue();
            pivotConfig.getChildren().removeAll(pivotPositionSlider,pivotPositionTextField,submitPivot);
            Label pivotValue = new Label("Pivot at: " + currentSimulation.pivotPosition);
            pivotConfig.getChildren().add(pivotValue);
            MomentsMenu.getChildren().add(addForcesConfig());
        });
        pivotConfig.getChildren().addAll(pivotPositionSlider,pivotPositionTextField,submitPivot);
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
        //creating magnitude input textField
        HBox forceConfig = new HBox();
        TextField magInput = new TextField("Magnitude");


        //angle input and all attributes
        VBox forceAngleSection = new VBox(); forceAngleSection.setStyle("-fx-background-color: #989882;");
        Label angleLabel = new Label("Angle");
        Label angleTip = new Label("Angle is measured from the normal");
        Slider angleInputSlider = new Slider(); 
        angleInputSlider.setMin(-90); angleInputSlider.setMax(90); angleInputSlider.setValue(0); //setting sliders max min and initial value
        TextField angleInputTextField = new TextField();
        //code to link slider and textfield together.
        angleInputTextField.textProperty().bindBidirectional(angleInputSlider.valueProperty(), NumberFormat.getNumberInstance());
        forceAngleSection.getChildren().addAll(angleLabel,angleTip,angleInputSlider,angleInputTextField);


        //position input and all attributes
        VBox forcePositionSection = new VBox(); forcePositionSection.setStyle("-fx-background-color: #929292;");
        Label positionLabel = new Label("Position");
        Slider positionInputSlider = new Slider(); 
        positionInputSlider.setMin(0-(plankLength/2)); positionInputSlider.setMax(plankLength/2); positionInputSlider.setValue(0); //setting sliders max min and initial value
        TextField positionInputTextField = new TextField();
        //code to link slider and textfield together.
        positionInputTextField.textProperty().bindBidirectional(positionInputSlider.valueProperty(), NumberFormat.getNumberInstance());
        forcePositionSection.getChildren().addAll(positionLabel,positionInputSlider,positionInputTextField);
        

        //submit force button and action
        Button submitForce = new Button("submit force");
        submitForce.setOnAction(e -> {
            ForceClass createdForce = new ForceClass(Integer.parseInt(magInput.getText()),Math.toRadians(angleInputSlider.getValue()),positionInputSlider.getValue());
            currentSimulation.addForce(createdForce);
            //need to remove/replace children with inputted data.
        });
        forceConfig.getChildren().addAll(magInput,forceAngleSection,forcePositionSection,submitForce);
        return forceConfig;
    }

    public static void updateInformation(){
        String currentSumOfCW = Double.toString(currentSimulation.getSumOfCW());
        guiCreation.sumOfCWLabel.setText(currentSumOfCW);
        String currentSumOfACW = Double.toString(currentSimulation.getSumOfACW());
        guiCreation.sumOfACWLabel.setText(currentSumOfACW);
        String currentResultantMoment = Double.toString(currentSimulation.getResultantMoment());
        guiCreation.resultantMomentLabel.setText(currentResultantMoment);
    } 

}