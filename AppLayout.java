import javafx.application.Application;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import java.util.ArrayList;

public class AppLayout extends Application {

    Stage window;
    Scene scene1, scene2;

    public static void main(String[] args){
        launch(args);        
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        //Label label1 = new Label("Welcome to the first scene");
        
        Label labelAntInc = new Label ("Anticipated Income");
        Label labelAntExp = new Label ("Anticipated Expenses");
        
        Label labelCategory = new Label ("Category"); 
        Label labelCategory2 = new Label ("Category");
        Button button1 = new Button("Go to Scene 2"); //ADD BUTTON BACK LATER
        Button submitBTN = new Button("Submit");
        ArrayList<String> arrli = new ArrayList<String>(6);

        button1.setOnAction(e -> window.setScene(scene2));
        
        //Layout 1 - children are laid out in vertical column
        VBox left = new VBox(10);
        left.getChildren().addAll(labelAntInc, labelCategory);
        
        //Layout 2 - children are laid out in vertical column
        VBox right = new VBox(10);
        right.getChildren().addAll(labelAntExp, labelCategory2);
        
        //Makes numerous text fields
        for (int i = 0; i < 5; i++)
        {
            TextField field = new TextField("Text Field");
            TextField field2 = new TextField("Text Field");
            TextField field3 = new TextField("Text Field");
 
            right.getChildren().add(field);
            left.getChildren().add(field2);
        }
        
        // right.getChildren().add(submitBTN);
        // Label label3 = new Label();
        // submitBTN.setOnAction(e -> {
        //     arrli.add(field1.getText());
        // );

        //Layout 3 - children are laid out in a horizontal column
        HBox hBox = new HBox(50);
        //hbox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(left, right);
        
        scene1 = new Scene(hBox, 600, 300);
        
        //Button 2
        Button button2 = new Button("this scene sucks, go back to scene 1");
        button2.setOnAction(e -> window.setScene(scene1));

        //Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 600, 300);

        window.setScene(scene1);
        window.setTitle("bonjour");
        window.show();
    }

    // public void getData(Arraylist<String> arrli){
    //     for (int i = 1; i < 12; i++){
    //         String name = "field" ;
    //         arrli.add(field[i].getText());
    //     }
    // }
}