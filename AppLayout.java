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
import javafx.geometry.Pos; //For alignment

public class AppLayout extends Application {

    Stage window;
    Scene scene1, scene2;

    public static void main(String[] args){
        launch(args);        
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        
        //-------------------- SCENE ONE BELOW --------------------//
        //Labels and formatting - Title and Headers
        Label title = new Label("Budgeting App");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        
        Label labelAntInc = new Label ("Anticipated Income");
        labelAntInc.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        
        Label labelAntExp = new Label ("Anticipated Expenses");
        labelAntExp.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        
        Label labelCategory = new Label ("Category"); 
        labelCategory.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        
        Label labelCategory2 = new Label ("Category");
        labelCategory2.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        
        Label labelAntAm = new Label ("Anticipated Amount");
        labelAntAm.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        
        Label labelAntAm2 = new Label ("Anticipated Amount");
        labelAntAm2.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        
        //Labels - Act as line breaks
        Label labelBlank = new Label ("");
        Label labelBlank2 = new Label ("");
        
        //Buttons & Actions
        Button buttonConfirm = new Button("Confirm"); 
        buttonConfirm.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        
        Button buttonScene2 = new Button("Go to Scene 2");
        buttonScene2.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");

        //Text Fields
        TextField leftField = new TextField();
        TextField leftField2 = new TextField();
        TextField leftField3 = new TextField();
        TextField leftField4 = new TextField();
        TextField leftField5 = new TextField();
        
        TextField rightField = new TextField();
        TextField rightField2 = new TextField();
        TextField rightField3 = new TextField();
        TextField rightField4 = new TextField();
        TextField rightField5 = new TextField();
        
        //Add action for buttonScene2
        buttonScene2.setOnAction(e -> window.setScene(scene2));
        
        //Layout - Vertical column on the far left
        VBox farLeft = new VBox(15);
        farLeft.getChildren().addAll(labelAntInc);
        farLeft.getChildren().addAll(labelCategory);
        
        //Layout - Vertical column on the left
        VBox left = new VBox(10);
        left.getChildren().addAll(labelBlank, labelAntAm);
        
        //Layout - Vertical column on the right
        VBox right = new VBox(15);
        right.getChildren().addAll(labelAntExp);
        right.getChildren().addAll(labelCategory2);
        
        //Layout - Vertical column on the far right
        VBox farRight = new VBox(10);
        farRight.getChildren().addAll(labelBlank2, labelAntAm2);
        
        //Labels - For left column        
        for (int i = 0; i < 5; i++)
        {
            Label label = new Label("Label");
            label.setFont(Font.font("Verdana", 12));
            farLeft.getChildren().add(label);
        }
        
        //Text fields - For left column 
        leftField = new TextField("");
        leftField.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
        
        leftField2 = new TextField("");
        leftField2.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
        
        leftField3 = new TextField("");
        leftField3.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
        
        leftField4 = new TextField("");
        leftField4.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
        
        leftField5 = new TextField("");
        leftField5.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
        
        left.getChildren().addAll(leftField, leftField2, leftField3, leftField4, leftField5); 
        
        //Labels - For right column
        for (int i = 0; i < 5; i++)
        {
            Label label2 = new Label("Label");
            label2.setFont(Font.font("Verdana", 12)); 
            right.getChildren().add(label2);
        }
        
        //Text fields - For right column 
        rightField = new TextField("");
        rightField.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
        
        rightField2 = new TextField("");
        rightField2.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
        
        rightField3 = new TextField("");
        rightField3.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
        
        rightField4 = new TextField("");
        rightField4.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
        
        rightField5 = new TextField("");
        rightField5.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
        
        farRight.getChildren().addAll(rightField, rightField2, rightField3, rightField4, rightField5);
        
        //Layout - Gathers the vertical columns on the far left and left together
        HBox hBoxLeft = new HBox(50);
        hBoxLeft.getChildren().addAll(farLeft, left);
        
        //Layout - Gathers the vertical columns on the right and far right together
        HBox hBoxRight = new HBox(50);
        hBoxRight.getChildren().addAll(right, farRight);
        
        //Layout - Top of the GUI: Title and Images (*ADD IMAGES HERE*)
        HBox hBoxTop = new HBox(50);
        hBoxTop.getChildren().addAll(title);
        hBoxTop.setAlignment(Pos.CENTER);
        
        //Layout - Middle of the GUI: Gather all the columns together
        HBox hBoxMiddle = new HBox(50);
        hBoxMiddle.getChildren().addAll(hBoxLeft, hBoxRight);
        hBoxMiddle.setAlignment(Pos.CENTER);
        
        //Layout - Bottom of the GUI: Buttons
        HBox hBoxBottom = new HBox(50);
        hBoxBottom.getChildren().addAll(buttonConfirm, buttonScene2);
        hBoxBottom.setAlignment(Pos.CENTER);
        
        //Layout - Entire GUI: Gathers the top, middle and bottom to make the entire GUI
        VBox mainScreen = new VBox(25);
        mainScreen.getChildren().addAll(hBoxTop, hBoxMiddle, hBoxBottom);
        mainScreen.setAlignment(Pos.CENTER);
        
        //Display the entire GUI
        scene1 = new Scene(mainScreen, 1000, 500);
        
        //-------------------- SCENE TWO BELOW --------------------//
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
}
