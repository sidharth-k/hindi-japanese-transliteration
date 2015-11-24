import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;


public class new_CandidateGenerator {

	public static HashMap<String, List<String>> hm = new HashMap<String,List<String>>();
	public static File file;
	public static File candidate;
	private static BufferedReader reader;
	private static FileWriter      write;
	public static int sent =0;
	public static void main(String[] args) {
		hm = mappings.main(args);
		file = new File(args[0]);
		candidate = new File(args[1]);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reader = new BufferedReader(new InputStreamReader(fis));
	     write = null;
	    try {
			     write = new FileWriter(candidate);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    String mappable = "";
	    //System.exit(0);
	    int count;
	    
			try {
				while((mappable = reader.readLine())!=null)
				{
					sent+=1;
//					mappable="ア ヌ  ッター ラー";
					mappable = mappable.trim();
					System.out.println(mappable+sent);
					write.write(sent+"\n");
					write.flush();
					int n;
					mappable.trim();
					
					StringTokenizer tokenizer = new StringTokenizer(mappable);
					
					count = tokenizer.countTokens();
//					System.out.println(count+"**");
					String[] jap = new String[count];
					int i;
					for(i=0;i<count;i++)
					{
						String tk = tokenizer.nextToken();
						
							jap[i] = tk;
					}
					
					
					String res = "";
					genCan(jap,0,res,count);
					System.out.println("done");
//					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		
	    System.out.println(sent);
		}
	private static void genCan(String[] jap, int i, String res, int count) {
		if(i==count)
		{
			try {
				write.write(res);
				write.write("\n");
				System.out.println(res+sent);
				write.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				write.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		List<String> list = null;
		List<String> newlist = new ArrayList<String>();
		if(jap[i].charAt(0)!='ッ')
		{
			list = hm.get(jap[i]);
			System.out.println(list);
			int k=0;
			while(k<list.size())
			{
				newlist.add(list.get(k));
				k+=1;
			}
		}
		else 
		{
			list = hm.get(jap[i].substring(1));
			int k=0;
			while(k<list.size())
			{
				newlist.add(list.get(k));
				k+=1;
			}
			System.out.println(jap[i].substring(1));
			int n = list.size();
			int j =0;
			int lm=0;
			for(lm=0;lm<n;lm++)
			{
				for(j=0;j<n;j++)
				newlist.add(newlist.get(lm).charAt(0)+"्"+newlist.get(j));
			}
			System.out.println(newlist);
		}
		int n = newlist.size();	
		for(int j=0;j<n;j++)
		{
			System.out.println(res);
			System.out.println(i+1+"(***");
			genCan(jap,i+1,res+newlist.get(j),count);
			
		}
	}

	}


