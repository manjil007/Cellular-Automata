/*
The OneDimensionCA class takes the user input or reads the given file to create a one dimension cellular automata
visualization.
 */

import javafx.scene.layout.GridPane;
import javafx.animation.AnimationTimer;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.concurrent.TimeUnit;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class OneDimensionCA {
    public void startCA(GridPane grid) throws FileNotFoundException{
        Scanner scan = new Scanner(System.in);
        int ruleInt = 30;
        String rule = "";
        String initialState= "00000100000";
        File file;
        String input = "";

        //option to choose between rule file or user input.
        do{
            System.out.println("Please enter your selection: ");
            System.out.print("1. Run Using rule file. \n2. Enter Behavior and Initial State. :");
            input = scan.next();
            System.out.println();
        }while(!input.equals("1") && !input.equals("2"));

        if (input.equals("1")){
            do{
                String path = System.getProperty("user.dir") + "/src/resources/elementaryCA/";
                System.out.println("Choose from the following available rule files:-");
                System.out.println("1: Rule30");
                System.out.println("2: Rule126");
                System.out.print("Select the Config file option from the list above (1 - 2): ");
                String option = scan.next();

                switch(option){
                    case "1" -> path = path + "rule30.txt";
                    case "2" -> path = path + "rule126.txt";
                    default -> {
                        System.out.println("!!!!!!!!!Invalid Option!!!!!!!!");
                        System.out.println();
                        path = path + "invalid";
                        System.exit(0);
                    }
                }

                file = new File(path);
                Scanner scnr = new Scanner(file);
                while (scnr.hasNextLine()) {
                    rule = scnr.nextLine();
                    initialState = scnr.nextLine();
                }
            }while(!file.exists());
        }else{
            //takes the user input for rule and checks for validity
            do {
                System.out.print("Enter the integer for the rule [0-255]: ");
                ruleInt = scan.nextInt();
            } while (!checkInt(ruleInt));

            //takes the user input for initial state and checks the validity.
            do{
                System.out.print("Enter the initial state(only 0's and 1's): ");
                initialState = scan.next();
            }while(!checkBinary(initialState));

            for (int i = 0; i < 8; i++){
                rule = ruleInt % 2 + rule;
                ruleInt = ruleInt / 2;
            }
        }

        int numOfCell = initialState.length();
        ArrayList<String> initialArray =  new ArrayList<String>();
        for (int i = 0; i < numOfCell; i++){
            initialArray.add(String.valueOf(initialState.charAt(i)));
        }

        Generation newGeneration = new Generation(initialArray, rule);

        final AnimationTimer animation = new AnimationTimer() {
            private long prevUpdate;
            int row = 0;

            @Override
            public void handle(long now) {
                if (now - prevUpdate >= TimeUnit.MILLISECONDS.toNanos(350)) {
                    runCA(row++);
                    newGeneration.state = newGeneration.evolve();
                    prevUpdate = now;
                }
            }

            private void runCA (int row) {
                for (int i = 0; i < newGeneration.state.size(); i++) {
                    Rectangle rect = new Rectangle(1000.0/numOfCell, 1000.0/numOfCell, Color.BLACK);
                    if (newGeneration.state.get(i).equals("1")) {
                        rect.setFill(Color.BLACK);
                    } else {
                        rect.setFill(Color.WHITE);
                    }
                    grid.add(rect, i, row);
                }
            }
        };
    animation.start();
    }

    /**
     * This function checks the validity of binary string.
     * @param binary user input for binary string
     * @return boolean
     */
    public boolean checkBinary(String binary){
        for (int i = 0; i < binary.length(); i++){
            if (!(binary.charAt(i) == '0') && !(binary.charAt(i) == '1')){
                System.out.println("Invalid initial state");
                return false;
            }
        }
        return true;
    }


    /**
     * This function checks if the input from user is between 0-255.
     * @param ruleInt - user input for rule
     * @return boolean
     */
    public boolean checkInt(Integer ruleInt){
        if (ruleInt > 255 || ruleInt < 0){
            System.out.println("Invalid Integer Value");
            return false;
        }
        return true;
    }
}