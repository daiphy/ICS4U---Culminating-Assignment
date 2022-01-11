import javafx.application.Application;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;

public class App extends Application {

    Stage window;
    Scene antScene, accScene, trialScene;

    public static void main(String[] args){
        launch(args);        
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        Label label1 = new Label("Welcome to the first scene");
        Label label2 = new Label("Welcome to the first scene");
        Label label3text = new Label("_Enter name:");

        TextField field1 = new TextField();
        label3text.setLabelFor(field1);
        label3text.setMnemonicParsing(true);

        Rectangle rect = new Rectangle(25, 25, 500, 50);
        rect.setFill(Color.CADETBLUE);

        Button button1 = new Button("Go to Scene 2");
        button1.setOnAction(e -> window.setScene(accScene));

        //Layout 1 - children are laid out in vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, label2, button1, label3text, field1);
        antScene = new Scene(layout1, 1200, 700);
     
        //Button 2
        Button button2 = new Button("this scene sucks, go back to scene 1");
        button2.setOnAction(e -> window.setScene(antScene));

        //Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().addAll(rect, button2);
        accScene = new Scene(layout2, 1200, 700);


        //Layout 3
        // StackPane layout3 = new StackPane();
        // layout3.getChildren().addAll(rect, label1);
        // trialScene = new Scene(layout3, 1200, 700);
        
        window.setScene(antScene);
        window.setTitle("bonjour");
        window.show();
    }
}
