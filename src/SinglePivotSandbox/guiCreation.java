package SinglePivotSandbox;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.text.NumberFormat;

public class guiCreation{

    public static VBox MomentsMenu = new VBox(10);
    public static VBox momentInformation = new VBox();
    public static Label labelACW = new Label();
    public static Label labelCW = new Label();
    public static Label labelRM = new Label();
    public static currentSimulation simulation;

    public static HBox addPlankConfig(){
        HBox plankConfig = new HBox();
        plankConfig.setStyle("-fx-background-color: #9933FF;");
        Label labelPlank = new Label("Plank Length");
        TextField sizeOfPlank = new TextField("Plank Length"); //still a textField because I don't want a max or min value
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
        double minValue = 0-(currentSimulation.plankLength/2); double maxValue = (currentSimulation.plankLength/2); int initialValue = 0; //creating min/max/initial values
        pivotPositionSlider.setMin(minValue); pivotPositionSlider.setMax(maxValue); pivotPositionSlider.setValue(initialValue);  //setting sliders max/min/initial values
        IntField pivotPositionIntField = new IntField(minValue,maxValue,initialValue); //min/max/initial set by constructor
        pivotPositionIntField.textProperty().bindBidirectional(pivotPositionSlider.valueProperty(), NumberFormat.getNumberInstance());
        
        Button submitPivot = new Button("Submit");
        submitPivot.setOnAction(e -> {
            currentSimulation.pivotPosition = pivotPositionSlider.getValue();
            pivotConfig.getChildren().removeAll(pivotPositionSlider,pivotPositionIntField,submitPivot);
            Label pivotValue = new Label("Pivot at: " + currentSimulation.pivotPosition);
            pivotConfig.getChildren().add(pivotValue);
            MomentsMenu.getChildren().add(addForcesConfig());
        });
        pivotConfig.getChildren().addAll(pivotPositionSlider,pivotPositionIntField,submitPivot);
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

        //textField to add magnitude
        TextField magInput = new TextField("Magnitude");

        //angle input and all attributes
        VBox forceAngleSection = new VBox(); forceAngleSection.setStyle("-fx-background-color: #989882;");
        Label angleLabel = new Label("Angle");
        Label angleTip = new Label("Angle is measured from the normal");
        Slider angleInputSlider = new Slider(); 
        double minAngleValue = -90; double maxAngleValue = 90; int initialAngleValue = 0; //creating min/max/initial values for angleInputs
        angleInputSlider.setMin(minAngleValue); angleInputSlider.setMax(maxAngleValue); angleInputSlider.setValue(initialAngleValue); //setting sliders max min and initial value
        IntField angleInputTextField = new IntField(minAngleValue,maxAngleValue,initialAngleValue); //min/max/initial set by constructor
        //code to link slider and textfield together.
        angleInputTextField.textProperty().bindBidirectional(angleInputSlider.valueProperty(), NumberFormat.getNumberInstance());
        forceAngleSection.getChildren().addAll(angleLabel,angleTip,angleInputSlider,angleInputTextField);

        //position input and all attributes
        VBox forcePositionSection = new VBox(); forcePositionSection.setStyle("-fx-background-color: #929292;");
        Label positionLabel = new Label("Position");
        Slider positionInputSlider = new Slider();
        double minPositionValue = 0-(plankLength/2); double maxPositionValue = (plankLength/2); int initialPositionValue = 0; //creating min/max/initial values for positionInputs
        positionInputSlider.setMin(minPositionValue); positionInputSlider.setMax(maxPositionValue); positionInputSlider.setValue(initialPositionValue); //setting sliders max min and initial value
        IntField positionInputTextField = new IntField(minPositionValue,maxPositionValue,initialPositionValue); //min/max/initial set by constructor
        //code to link slider and textfield together.
        positionInputTextField.textProperty().bindBidirectional(positionInputSlider.valueProperty(), NumberFormat.getNumberInstance()); //some error here which doesnt stop running the code?
        forcePositionSection.getChildren().addAll(positionLabel,positionInputSlider,positionInputTextField);
        
        //submit force button and action
        Button submitForce = new Button("submit force");
        submitForce.setOnAction(e -> {
            forceClass createdForce = new forceClass(Integer.parseInt(magInput.getText()),Math.toRadians(angleInputSlider.getValue()),positionInputSlider.getValue());
            simulation.addForce(createdForce);
        });
        forceConfig.getChildren().addAll(magInput,forceAngleSection,forcePositionSection,submitForce);
        return forceConfig;
    }

    public static void updateInformation(){
        //guiCreation.momentInformation = new VBox();
        String stringOfCW = simulation.getSumOfCW() + "CW";
        labelCW.setText(stringOfCW);//Label labelOfCW = new Label(stringOfCW);
        String stringOfACW = simulation.getSumOfACW() + "ACW";
        labelACW.setText(stringOfACW);//Label labelOfACW = new Label(stringOfACW);
        String stringResultantMoment = simulation.getResultantMoment() + "RM";
        labelRM.setText(stringResultantMoment);//Label labelResultantMoment = new Label(stringResultantMoment);
        guiCreation.momentInformation.getChildren().removeAll(guiCreation.labelCW,guiCreation.labelACW,guiCreation.labelRM);
        guiCreation.momentInformation.getChildren().addAll(guiCreation.labelCW,guiCreation.labelACW,guiCreation.labelRM); //not working.
    } 

}