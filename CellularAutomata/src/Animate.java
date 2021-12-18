import javafx.animation.AnimationTimer;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;
import org.w3c.dom.ls.LSOutput;

import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Animate {
    ArrayList<ArrayList<String>> state;
    GridPane grid;


    public Animate(ArrayList<ArrayList<String>> state, GridPane grid){
        this.state = state;
        this.grid = grid;
    }


    public void createTwoState(){
        TwoStateGeneration twoStateGeneration = new TwoStateGeneration(this.state);
        long timeInterval = TimeUnit.MILLISECONDS.toNanos(500);
        int rowSize = twoStateGeneration.state.size();
        int colSize = twoStateGeneration.state.get(0).size();
        Rectangle[][] rectA= new Rectangle[rowSize][colSize];
        addRectToGrid(rectA, rowSize, colSize);

        final AnimationTimer animation = new AnimationTimer() {
            private long prevUpdate;

            @Override
            public void handle(long now) {
                if (now - prevUpdate >= timeInterval) {
                    runCA();
                    twoStateGeneration.evolve();
                    for (int i = 0; i < rowSize; i++) {
                        twoStateGeneration.state.set(i, new ArrayList<>(twoStateGeneration.new_state.get(i)));
                    }
                    prevUpdate = now;
                }
            }


            private void runCA () {
                for(int i = 0; i < rowSize; i++) {
                    for(int j = 0; j < colSize; j++){
                        if (twoStateGeneration.state.get(i).get(j).equals("1")) {
                            rectA[i][j].setFill(Color.BLACK);
                        } else {
                            rectA[i][j].setFill(Color.WHITE);
                        }

                    }
                }
            }
        };
        animation.start();
    }


    public void createEightState() throws FileNotFoundException {
        EightStateGeneration eightStateGeneration = new EightStateGeneration(this.state);
        long timeInterval = TimeUnit.MILLISECONDS.toNanos(500);
        int rowSize = eightStateGeneration.state.size();
        int colSize = eightStateGeneration.state.get(0).size();
        Rectangle[][] rectA= new Rectangle[rowSize][colSize];
        addRectToGrid(rectA, rowSize, colSize);

        final AnimationTimer animation = new AnimationTimer() {
            private long prevUpdate;

            @Override
            public void handle(long now) {

                if (now - prevUpdate >= timeInterval) {
                    runCA();
                    eightStateGeneration.evolve();
                    for (int i = 0; i < rowSize; i++) {
                        eightStateGeneration.state.set(i, new ArrayList<>(eightStateGeneration.new_state.get(i)));
                    }
                    prevUpdate = now;
                }
            }

            private void runCA () {
                for(int i = 0; i < rowSize; i++) {
                    for(int j = 0; j < colSize; j++){
                        switch (eightStateGeneration.state.get(i).get(j)) {
                            case "0" -> rectA[i][j].setFill(Color.WHITE);
                            case "1" -> rectA[i][j].setFill(Color.BLACK);
                            case "2" -> rectA[i][j].setFill(Color.RED);
                            case "3" -> rectA[i][j].setFill(Color.GREEN);
                            case "4" -> rectA[i][j].setFill(Color.BLUE);
                            case "5" -> rectA[i][j].setFill(Color.PINK);
                            case "6" -> rectA[i][j].setFill(Color.PURPLE);
                            case "7" -> rectA[i][j].setFill(Color.YELLOW);
                        }

                    }
                }
            }
        };
        animation.start();
    }
    public void addRectToGrid(Rectangle[][] rectArray, int rowSize, int colSize) {
        for(int i = 0; i < rowSize; i++) {
            for(int j = 0; j < colSize; j++){
                Rectangle rect = new Rectangle(800.0/rowSize, 800.0/colSize, Color.BLACK);
                rectArray[i][j] = rect;
                grid.add(rectArray[i][j], j, i);
            }
        }
    }
}
