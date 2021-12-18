import java.util.ArrayList;
import java.util.HashMap;


public class TwoStateGeneration {
    ArrayList<ArrayList<String>> state;
    ArrayList<ArrayList<String>> new_state;
    int liveCount;
    int deadCount;
    HashMap<String, Integer[]> neighborIndex = new HashMap<>();
    String center = "";


    public TwoStateGeneration(ArrayList<ArrayList<String>> state){
        this.state = state;
        this.new_state = new ArrayList<>();
        this.setup();
    }


    public void setup(){
        this.neighborIndex.put("north",new Integer[]{0,0}) ;
        this.neighborIndex.put("south",new Integer[]{0,0}) ;
        this.neighborIndex.put("west",new Integer[]{0,0}) ;
        this.neighborIndex.put("east",new Integer[]{0,0}) ;
        this.neighborIndex.put("nEast",new Integer[]{0,0}) ;
        this.neighborIndex.put("nWest",new Integer[]{0,0}) ;
        this.neighborIndex.put("sEast",new Integer[]{0,0}) ;
        this.neighborIndex.put("sWest",new Integer[]{0,0}) ;
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

               this.neighborIndex.get("nEast")[0] = i - 1;
               this.neighborIndex.get("nEast")[1] = j + 1;

               this.neighborIndex.get("nWest")[0] = i - 1;
               this.neighborIndex.get("nWest")[1] = j - 1;

               this.neighborIndex.get("sEast")[0] = i + 1;
               this.neighborIndex.get("sEast")[1] = j + 1;

               this.neighborIndex.get("sWest")[0] = i + 1;
               this.neighborIndex.get("sWest")[1] = j -1 ;

               for (String key: this.neighborIndex.keySet()){
                   if (this.neighborIndex.get(key)[0] < 0) this.neighborIndex.get(key)[0] = noOfRows - 1;
                   else if (this.neighborIndex.get(key)[0] > noOfRows - 1 ) this.neighborIndex.get(key)[0] = 0;

                   if (this.neighborIndex.get(key)[1] < 0) this.neighborIndex.get(key)[1] = noOfCols - 1;
                   else if (this.neighborIndex.get(key)[1] > noOfCols -1 ) this.neighborIndex.get(key)[1] = 0;
               }

               countLiveDead(this.neighborIndex);

              if (center.equals("1")){
                   if (this.liveCount < 2) this.new_state.get(i).set(j, "0");
                   else if (this.liveCount > 3) this.new_state.get(i).set(j, "0");
                   else this.new_state.get(i).set(j, "1");
               }
              else  if (center.equals("0") && this.liveCount == 3){
                  this.new_state.get(i).set(j, "1");
              }
               else {
                   this.new_state.get(i).set(j,"0");
               }
           }
       }
   }


   public void countLiveDead( HashMap<String, Integer[]> neighbors){
       this.liveCount = 0;
       this.deadCount = 0;
        for (String key: neighbors.keySet()) {
            if (this.state.get(neighbors.get(key)[0]).get(neighbors.get(key)[1]).equals("0")) this.deadCount++;
            else this.liveCount++;
        }
   }
}