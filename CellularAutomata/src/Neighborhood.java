import java.util.HashMap;

public class Neighborhood {
    HashMap<String, String> hash;
    public Neighborhood( String keys){
        this.hash = oneDimRule(keys);
    }

    public HashMap<String, String> oneDimRule(String ruleSet){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("111", String.valueOf(ruleSet.charAt(0)));
        map.put("110", String.valueOf(ruleSet.charAt(1)));
        map.put("101", String.valueOf(ruleSet.charAt(2)));
        map.put("100", String.valueOf(ruleSet.charAt(3)));
        map.put("011", String.valueOf(ruleSet.charAt(4)));
        map.put("010", String.valueOf(ruleSet.charAt(5)));
        map.put("001", String.valueOf(ruleSet.charAt(6)));
        map.put("000", String.valueOf(ruleSet.charAt(7)));
        return map;
    }
}
