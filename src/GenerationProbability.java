import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class GenerationProbability {
	public static Map<ArrayList<String>,Double> GenerationProbabilities;
	public static void main() {
		generationProb.main();
     Map<String,Integer> kata = generationProb.countKata;
     HashMap<ArrayList<String>, Integer> mappings = generationProb.mappedCounts;
     GenerationProbabilities = new HashMap<ArrayList<String>,Double>();
     Iterator<Entry<ArrayList<String>, Integer>> it = mappings.entrySet().iterator();
     while(it.hasNext())
     {
    	 Map.Entry<ArrayList<String>, Integer> pair = (Map.Entry<ArrayList<String>, Integer>)it.next();
    	 ArrayList<String> list = pair.getKey();
    	 double prob;
    	 int n = kata.get(list.get(0));
    	 prob = (double)pair.getValue()/(double)n;
    	 prob = Math.log10(prob);
    	 GenerationProbabilities.put(list,prob);
    	 //System.out.println(list);
     }
     System.out.println(GenerationProbabilities.toString());
	}

}
