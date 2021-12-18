import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;

public class EightStateGeneration {
    ArrayList<ArrayList<String>> state;
    ArrayList<ArrayList<String>> new_state;
    HashMap<String, ArrayList<ArrayList<String>>> ruleMap = new HashMap<>();
    HashMap<String, Integer[]> neighborIndex = new HashMap<>();
    String center = "";
    String tempNeighbor = "";

    public EightStateGeneration(ArrayList<ArrayList<String>> state)throws FileNotFoundException{
        this.state = state;
        this.new_state = new ArrayList<>();
        this.setup();
    }

    public void setup()throws FileNotFoundException {
        this.neighborIndex.put("north",new Integer[]{0,0}) ;
        this.neighborIndex.put("south",new Integer[]{0,0}) ;
        this.neighborIndex.put("west",new Integer[]{0,0}) ;
        this.neighborIndex.put("east",new Integer[]{0,0}) ;

        for (int i = 0; i < 8; i++){
            this.ruleMap.put(Integer.toString(i), new ArrayList<>());
        }

        File ruleFile = new File(System.getProperty("user.dir") + "/src/resources/langtonsLoop/rule_table.txt");
        Scanner lines = new Scanner(ruleFile);
        String line;
        String key, combo1, combo2, combo3, combo4, value;
        ArrayList<String> temp;

        while (lines.hasNextLine()){
            line = lines.nextLine();
            key = line.substring(0, 1);
            combo1 = line.substring(1, 5);
            combo2 = String.valueOf(line.charAt(2)) + line.charAt(3) + line.charAt(4) + line.charAt(1);
            combo3 = String.valueOf(line.charAt(3)) + line.charAt(4) + line.charAt(1) + line.charAt(2);
            combo4 = String.valueOf(line.charAt(4)) + line.charAt(1) + line.charAt(2) + line.charAt(3);
            value = line.substring(5,6);
            temp = new ArrayList<>();
            temp.add(combo1);
            temp.add(combo2);
            temp.add(combo3);
            temp.add(combo4);
            temp.add(value);
            this.ruleMap.get(key).add(temp);
        }
    }


    public void evolve() {
        for(ArrayList<String> row: state){
            this.new_state.add(new ArrayList<>(row));
        }
        int noOfRows = this.state.size();
        int noOfCols = this.state.get(0).size();
        for(int i = 0; i < this.state.size(); i++) {
            for(int j = 0; j < this.state.get(i).size(); j++){
                center = this.state.get(i).get(j);
                this.neighborIndex.get("north")[0] = i - 1;
                this.neighborIndex.get("north")[1] = j;

                this.neighborIndex.get("south")[0] = i + 1;
                this.neighborIndex.get("south")[1] = j;

                this.neighborIndex.get("east")[0] = i;
                this.neighborIndex.get("east")[1] = j + 1;

                this.neighborIndex.get("west")[0] = i;
                this.neighborIndex.get("west")[1] = j - 1;

                for (String key: this.neighborIndex.keySet()){
                    if (this.neighborIndex.get(key)[0] < 0) this.neighborIndex.get(key)[0] = noOfRows - 1;
                    else if (this.neighborIndex.get(key)[0] > noOfRows - 1 ) this.neighborIndex.get(key)[0] = 0;

                    if (this.neighborIndex.get(key)[1] < 0) this.neighborIndex.get(key)[1] = noOfCols - 1;
                    else if (this.neighborIndex.get(key)[1] > noOfCols -1 ) this.neighborIndex.get(key)[1] = 0;
                }

                this.tempNeighbor =this.state.get(this.neighborIndex.get("north")[0]).get(this.neighborIndex.get("north")[1])
                        + this.state.get(this.neighborIndex.get("east")[0]).get(this.neighborIndex.get("east")[1])
                        + this.state.get(this.neighborIndex.get("south")[0]).get(this.neighborIndex.get("south")[1])
                        + this.state.get(this.neighborIndex.get("west")[0]).get(this.neighborIndex.get("west")[1]);

                label:
                for (ArrayList<String> rule : this.ruleMap.get(center)){
                    for(String combo : rule){
                        if (combo.equals(tempNeighbor)){
                            this.new_state.get(i).set(j, rule.get(4));
                            break label;
                        }
                    }
                }
            }
        }
    }
}