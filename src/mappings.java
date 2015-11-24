import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;
public class mappings {

	/**
	 * @param args
	 */
	public static HashMap<String,List<String>> main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		File file = new File("/media/sidharth/New Volume/BTP/new-kata");
		//File spaced = new File("/media/sidharth/New Volume/BTP/spaced");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
//		FileReader read = new FileReader(file);
		char ch;
	    String s;
	    
		
	    String katakana;
		HashMap HM = new HashMap<String,List<String>>();
		try {
			while((s=reader.readLine())!=null){
			StringTokenizer tokenizer = new StringTokenizer(s);
			System.out.println(s);
			katakana = tokenizer.nextToken();
			System.out.println(katakana);
			//System.out.println(tokenizer.nextToken());
			List<String> list =  new ArrayList();
			while(tokenizer.hasMoreTokens())
			{
				list.add(tokenizer.nextToken());
				//System.out.println(list.toString());
			}
			System.out.println(list.toString());
			if(HM.containsKey(katakana))
				{
				List<String> newList = (List<String>) HM.get(katakana);
				System.out.println("here");
				
				list.addAll(newList);
				HM.put(katakana, list);
				//System.out.println(HM.get(katakana));
				//System.exit(0);
				}
			else
			HM.put(katakana,list );
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		List<String> out = (List<String>) (HM.get("テ"));
		System.out.println(HM.toString());
		//System.out.println(HM.get("ン"));
		return HM;
	}

}
