package SinglePivotSandbox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;

public class Main extends Application{
    public static void main(String[] args) { launch(args);}

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TestMoments"); //name of window
        
        guiCreation.MomentsMenu.setStyle("-fx-background-color: #92e3df;"); 
        //making config title
        Label MomentsMenuTitle = new Label("Configuration Menu");
        Font MomentsMenuTitleFont = new Font("Arial",20);
        MomentsMenuTitle.setFont(MomentsMenuTitleFont);
        guiCreation.MomentsMenu.getChildren().add(MomentsMenuTitle);

        guiCreation.simulation = new currentSimulation();
        //add objects to MomentsMenu
        guiCreation.MomentsMenu.getChildren().add(guiCreation.addPlankConfig());

        //creating VBox for moment Information RM,ACW,CW  temp/wip no all made global variables under guiCreation
        guiCreation.momentInformation.setStyle("-fx-background-color: #92e3df;");
        guiCreation.momentInformation.getChildren().addAll(guiCreation.labelCW,guiCreation.labelACW,guiCreation.labelRM);

        Button button = new Button("show values");
        button.setOnAction(e -> {
            System.out.println(guiCreation.simulation.getSumOfCW() + "CW");
            System.out.println(guiCreation.simulation.getSumOfACW() + "ACW");
            System.out.println(guiCreation.simulation.getResultantMoment() + "RM");
        }); //added temp

        //setting MomentMenu in canvas
        BorderPane canvas = new BorderPane(); //create canvas/layout object
        canvas.setRight(guiCreation.MomentsMenu);
        canvas.setLeft(guiCreation.momentInformation);//set Vbox with all info about RM,ACW and CW
        canvas.setBottom(button);//added temp
        Scene scene = new Scene(canvas, 1600, 900); //window dimensions and main layout added.
        primaryStage.setScene(scene); //give stage attributes from scene object
        primaryStage.show();
    }
}