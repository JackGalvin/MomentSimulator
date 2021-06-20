package Project;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application{
    //global Variables
    float pivotPosition = 0; //0 is the default position until set
    public static void main(String[] args) { launch(args);}

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TestMoments"); //name of window
        Button button = new Button("Click me");

        StackPane layout = new StackPane(); //layout where every object is stacked ontop of each other.
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 300, 500); //window dimensions and main layout added.
        primaryStage.setScene(scene); //give stage attributes from scene object
        primaryStage.show();

        //ForceClass.pivotPosition = pivotPosition; (pass value)
    }
}
