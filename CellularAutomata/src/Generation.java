import java.util.ArrayList;


/**
 * This class creates a new generation for one dimension cellular automata.
 */
public class Generation{
    ArrayList<String> state;
    String rule;
    Neighborhood neighbor;

    public Generation(ArrayList<String> state, String rule){
        this.state = state;
        this.rule = rule;
        this.neighbor = new Neighborhood(this.rule);
    }


    /**
     * This function creates a new generation and updates the state of current generation
     * @return arraylist of string to update the new generation
     */
    public ArrayList<String> evolve(){
        ArrayList<String> newGen = new ArrayList<>();
        String left = "";
        String middle = "";
        String right = "";
        for (int i = 0; i < state.size(); i++) {
            middle = state.get(i);
            if (i == state.size() - 1) {
                left = state.get(i - 1);
                right = state.get(0);
            } else if (i == 0) {
                left = state.get(state.size() - 1);
                right = state.get(i + 1);
            } else {
                left = state.get(i - 1);
                right = state.get(i + 1);
            }
            String value = left + middle + right;
            newGen.add(this.neighbor.hash.get(value));
        }
        return newGen;
    }

}
