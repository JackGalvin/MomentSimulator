package Project;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;

public class Main extends Application{
    //global Variables
    //double pivotPosition = 0; //0 is the default position until set
    //double plankLength = 100;
    public static void main(String[] args) { launch(args);}

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TestMoments"); //name of window
        
        guiCreation.MomentsMenu.setStyle("-fx-background-color: #92e3df;"); 
        //making config title
        Label MomentsMenuTitle = new Label("Configuration Menu");
        Font MomentsMenuTitleFont = new Font("Arial",20);
        MomentsMenuTitle.setFont(MomentsMenuTitleFont);
        guiCreation.MomentsMenu.getChildren().add(MomentsMenuTitle);

        //add objects to MomentsMenu
        guiCreation.MomentsMenu.getChildren().add(guiCreation.addPlankConfig());

        //setting MomentMenu in canvas
        BorderPane canvas = new BorderPane(); //create canvas/layout object
        canvas.setRight(guiCreation.MomentsMenu);
        Scene scene = new Scene(canvas, 1600, 900); //window dimensions and main layout added.
        primaryStage.setScene(scene); //give stage attributes from scene object
        primaryStage.show();
    }
}