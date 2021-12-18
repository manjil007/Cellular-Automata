/**
 *
 */

import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class TwoDimensionCA {
    public void startCA(GridPane grid) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        String input = "";
        File file = null;
        ArrayList<ArrayList<String>> state = new ArrayList<>();
        do{
            System.out.print("Choose 2 or 8 states(Enter 2 or 8): ");
            input = scan.next();
            System.out.println();
            if (input.equals("2")){
                runTwoStates(state, file);
                Animate animate = new Animate(state, grid);
                animate.createTwoState();
            }else if(input.equals("8")){
                runEightStates(state);
                System.out.println("!!!!!!Animation is being displayed in a new Console!!!!!!!!");
                Animate animate = new Animate(state, grid);
                animate.createEightState();
            }
        }while(!input.equals("2") && !input.equals("8"));
    }


    public void runEightStates(ArrayList<ArrayList<String>> state) throws FileNotFoundException {
        File file = new File("/Users/manjilpradhan/IdeaProjects/CellularAutomata/src/resources/langtonsLoop/init_config.txt");
        setInitialState(state, file);
    }


    public void runTwoStates(ArrayList<ArrayList<String>> state, File file ) throws FileNotFoundException {
        String choice;
        Scanner scan = new Scanner(System.in);
        int rowInt, colInt;
        ArrayList<String> temp;
        do {
            System.out.println("Please enter your selection: ");
            System.out.print("1. Run Using rule file. \n2. Enter Behavior and Initial State. :");
            choice = scan.next();
            if (choice.equals("1")) {
                do {
                    String path = System.getProperty("user.dir") + "/src/resources/gameOfLife/";
                    System.out.println("Choose from the following available rule files:-");
                    System.out.println("1: Blinker");
                    System.out.println("2: Dies1");
                    System.out.println("3: Dies2");
                    System.out.println("4: Glider1");
                    System.out.println("5: Repeats1");
                    System.out.println("6: Stable1");
                    System.out.println("7: Stable2");
                    System.out.print("Select the Config file option from the list above (1 - 7): ");
                    String option = scan.next();
                    switch(option){
                        case "1" -> path = path + "blinker.txt";
                        case "2" -> path = path + "dies1.txt";
                        case "3" -> path = path + "dies2.txt";
                        case "4" -> path = path + "glider1.txt";
                        case "5" -> path = path + "repeats1.txt";
                        case "6" -> path = path + "stable1.txt";
                        case "7" -> path = path + "stable2.txt";
                        default -> {
                            System.out.println("!!!!!!!!!Invalid Option!!!!!!!!");
                            System.out.println();
                            path = path + "invalid";
                        }
                    }
                    file = new File(path);

                } while (!file.exists());
                setInitialState(state, file);

            }
            else if(choice.equals("2")) {
                System.out.println();
                System.out.print("Enter number of Rows: ");
                rowInt = scan.nextInt();
                System.out.print("Enter number of Colums: ");
                colInt = scan.nextInt();
                scan.nextLine();
                boolean valid;
                do {
                    valid = true;
                    state.clear();
                    System.out.println("\nEnter Initial grid line by line");
                    String tempString = "";
                    ArrayList<String> tempArray = new ArrayList<String>();
                    for (int row = 0; row < rowInt; row++) {
                        System.out.print("Enter row " + (row + 1) + " grid data: ");
                        tempString = scan.nextLine();
                        if (tempString.length() != colInt)
                        {
                            valid=false;
                            System.out.println("Input does not match dimension. Enter again!!!");
                            break;

                        }
                        for (int col = 0; col < colInt; col++) {
                            tempArray.add(String.valueOf(tempString.charAt(col)));
                        }
                        state.add(new ArrayList<>(tempArray));
                        tempArray.clear();
                    }
                }while(!valid);
            }

        } while (!choice.equals("1") && !choice.equals("2"));
        System.out.println("\n!!!!!!Animation is being displayed in a new Console!!!!!!!!");


    }

    public void setInitialState(ArrayList<ArrayList<String>> state, File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        int rowInt = scan.nextInt();
        int colInt = scan.nextInt();
        scan.nextLine();
        String temp = "";
        ArrayList<String> tempArray = new ArrayList<String>();
        for (int row = 0; row < rowInt; row++) {
            temp = scan.nextLine();

            for (int col = 0; col < colInt; col++) {
                tempArray.add(String.valueOf(temp.charAt(col)));
            }
            state.add(new ArrayList<>(tempArray));
            tempArray.clear();
        }
    }
}