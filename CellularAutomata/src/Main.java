/*
This program creates a Cellular Automata animation for different dimension and different stages.
 */


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import java.util.Scanner;


public class Main extends Application {

    /*
    This class allows user to navigate and create various forms of automata.
    It uses different tools of JavaFx to visualize the Cellular Automata animations.
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("Cellular Automata");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(1000);

        final GridPane grid = new GridPane();
        final Scene scene = new Scene(grid);
        primaryStage.setScene(scene);

        //Provides user to choose one or dimension options
        System.out.println("Choose from the options below: ");
        System.out.println("1: One Dimension Cellular Automata");
        System.out.println("2: Two Dimension Cellular Automata");
        System.out.println();
        System.out.print("Enter a selection: ");
        Scanner choice = new Scanner(System.in);
        String userInput = choice.next();

        if (userInput.equals("1")){
            OneDimensionCA oneDimCA = new OneDimensionCA();
            oneDimCA.startCA(grid);
        }else if(userInput.equals("2")){
            TwoDimensionCA twoDimCA = new TwoDimensionCA();
            twoDimCA.startCA(grid);
        } else{
            System.out.println("Invalid selection, Please run the application again.");
            System.exit(0);
        }
        primaryStage.show();
    }
}