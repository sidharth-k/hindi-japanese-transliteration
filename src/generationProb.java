import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class generationProb {

	private static BufferedReader reader;
	private static FileWriter write;
	private static FileInputStream hinFis;
	private static BufferedReader hinRead;
	public static Map<String, Integer> countKata;
	public static HashMap<ArrayList<String>, Integer> mappedCounts;

	/**
	 * @param args
	 */
	public static void main() {
		// TODO Auto-generated method stub

		File japanese = new File("/media/sidharth/New Volume/BTP/mappable");
		File hindi = new File("/media/sidharth/New Volume/BTP/hindi-syllables");
		FileInputStream japFis = null;
		try {
			japFis = new FileInputStream(japanese);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reader = new BufferedReader(new InputStreamReader(japFis));
	    hinFis = null;
	    try {
			hinFis = new FileInputStream(hindi);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    hinRead = new BufferedReader(new InputStreamReader(hinFis));
	    String jap = "";
	    String hin ="";
	    countKata = new HashMap<String,Integer>();
	    mappedCounts = new HashMap<ArrayList<String>,Integer>();
	    int countWords=0;
	    try {
			while((jap = reader.readLine())!=null)
			{
				hin = hinRead.readLine();
				StringTokenizer japTok = new StringTokenizer(jap);
				StringTokenizer hinTok = new StringTokenizer(hin);
				String kana = "";
				String hinSyl = "";
				
				if(japTok.countTokens()==hinTok.countTokens())
				{
					countWords+=1;
					while(japTok.hasMoreTokens())
					{
						kana = japTok.nextToken();
						hinSyl = hinTok.nextToken();
						if(countKata.containsKey(kana))
						{
						  int count = 	countKata.get(kana);
						  count=count+1;
						  countKata.put(kana, count);
						}
						else   
						{
							countKata.put(kana,1);
						}
						ArrayList<String> list = new ArrayList<String>();
						list.add(kana);
						list.add(hinSyl);
						if(mappedCounts.containsKey(list))
						{
							int mapped = mappedCounts.get(list);
							mapped+=1;
							mappedCounts.put(list,mapped);
						}
						else 
						{
							mappedCounts.put(list,1);
						}
					}
				}
				else 
				{
					continue;
//					System.out.println(hin+hinTok.countTokens());
//					System.out.println(jap+japTok.countTokens());
				}
					//continue;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //System.out.println(mappedCounts.toString());
	    //System.out.println(countWords);
	}

}
