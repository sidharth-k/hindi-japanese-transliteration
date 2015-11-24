/*generates all the hindi to japanese mappable syllables. 
 * we consider N with halant and modifiers as syllables since these can be 
 * depicted by a whole character in japanese i.e. ン*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
public class hindiSyllableGen {

	/**
	 * @param args
	 */
	public static Set<String> vow , conso,matra,modifier,nukta,halant;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
     // String s = "तृप्ति";
      String C = "क ख ग घ ङ च छ ज झ ञ त थ द ध न ट ठ ड ढ ण प  फ ब भ म य र ल व स श ष  ह ";
      StringTokenizer tok = new StringTokenizer(C);
      String[] consonants = new String[tok.countTokens()];
      int i=0;
      while(tok.hasMoreElements())
      {
    	  consonants[i]= tok.nextToken();
    	  i+=1;
      }
      conso = new HashSet<String>(Arrays.asList(consonants));
//      System.out.println(conso.contains());
      String  V = "अ आ इ ई उ ऊ ए ऐ ओ औ अं अः ऋ";
      StringTokenizer v = new StringTokenizer(V);
      String[] vowels = new String[v.countTokens()];
      i = 0;
      while(v.hasMoreElements())
      {
    	  vowels[i]= v.nextToken();
    	  i+=1;
      }
      vow = new HashSet<String>(Arrays.asList(vowels));
      // ा         ि           ी       ु             ू             ृ             ॄ           ॅ     े       ै     ॉ      ो     ौ
      
      String  M = " ा         ि           ी       ु             ू            ॄ           ॅ     े       ै     ॉ      ो     ौ ";
      StringTokenizer m = new StringTokenizer(M);
      String[] Matras = new String[m.countTokens()];
      i = 0;
      while(m.hasMoreElements())
      {
    	  Matras[i]= m.nextToken();
    	  i+=1;
      }
      matra = new HashSet<String>(Arrays.asList(Matras));
      
      //ँ   ं   ः  
      
      String  mod= " ँ   ं   ः  ृ   ";
      StringTokenizer modi = new StringTokenizer(mod);
      String[] modif = new String[modi.countTokens()];
      i = 0;
      while(modi.hasMoreElements())
      {
    	  modif[i]= modi.nextToken();
    	  i+=1;
      }
      modifier = new HashSet<String>(Arrays.asList(modif));
      
      String [] nuk = new String[1];
      nuk[0]= "़";
      nukta = new HashSet<String>(Arrays.asList(nuk));
      String[] hal = new String[1];
      hal[0]= "्";
      halant = new HashSet<String>(Arrays.asList(hal));
      
      File file = new File(args[0]);//"/media/sidharth/New Volume/BTP/Candidates"
		File syllabic = new File(args[1]);//"/media/sidharth/New Volume/BTP/test"
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
		FileWriter write = null;
	    try {
			write = new FileWriter(syllabic);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      String input = "";
      String output = "";
      try {
		input = reader.readLine();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
      try {
		while(input!=null){
			if(isNumeric(input))
			{
				write.write(input+"\n");
				System.out.println(input);
				input=reader.readLine();
				if(input==null||input.length()==0)
					break;
			}
			
			
			else if(isHalant(input.charAt(input.length()-1)))
			{
				input = reader.readLine();
				System.out.println(input);
				if(input==null||input.length()==0)
					break;
			}
			
			else {
				for(i=0;i<input.length();i++)
		      
		      {
		    	  System.out.println(input);
		    	 
		    	  if(isVowel(input.charAt(i)))
		    	  {
		    		  output+=input.charAt(i);
		    		  //i+=1;
		              output+=" " ;
		    	  }
		    	  else if(isConsonant(input.charAt(i)))
		    		  {
		    		      //System.out.println("here");
		    			  String out = "";
		    			  //System.out.println("here");
		    			  out+=input.charAt(i);
		    			  if(i+1<input.length())
		    			  {
		    				  if(isMatra(input.charAt(i+1)))
		    				  {
		    					  out+=input.charAt(i+1);
		    					  i+=1;
		    					  out+=" ";
		    				  }
		    				  else if(isNukta(input.charAt(i+1)))
		    				  {

		    					  out+=input.charAt(i+1);
		    					  i+=1;
		    					  if(i+1<input.length())
		    					  {
		    						  if(isMatra(input.charAt(i+1)))
		    						  {
		    							  out+=input.charAt(i+1);
		    							  i+=1;
		    						  }
		    					  }
		    					  out+=" ";
		    				  }
		    				  else if(isHalant(input.charAt(i+1)))
		    				  {
		    					  out+=input.charAt(i+1);
		    					  if((input.charAt(i)!='न')&&(input.charAt(i)==input.charAt(i+2)||input.charAt(i+2)=='ञ'))
		    					  {
		    						  out+=input.charAt(i+2);
		    						  i+=2;
		    						  if(i+1<input.length())
		    						  {
		    							  if(isMatra(input.charAt(i+1)))
		    							  {
		    								  out+=input.charAt(i+1);
		    								  i+=1;
		    							  }
		    						  }
		    						  out+=" ";
		    						  
		    					  }
		    					  else{
		    						  out+=" ";
			    					  i+=1;
		    					  }
		    					  
		    				  }
		    				  
		    				  
		    			  }
		    			  output+=" ";
		    			  output+=out;
		    			 // System.out.println(out);
		    		  }
		    	  else 
		    	  {
		    		  output+=" ";
		    		  output+=input.charAt(i);
		    		  output+=" ";
		    		  //i+=1;
		    	  }
		    	  
		      }
		      System.out.println(output);
		      write.write(output);
		      write.write("\n");
		      write.flush();
		      output="";
		      input = reader.readLine();
		  }
		}
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

      
  	}

	private static boolean isNumeric(String input) {
		
	    return input.matches("[-+]?\\d*\\.?\\d+");  
	}

	private static boolean isHalant(char charAt) {
		// TODO Auto-generated method stub
		String s = ""+charAt;
		if(halant.contains(s))
			return true;
		else 
		return false;
	}

	private static boolean isNukta(char charAt) {
		String s = ""+charAt;
		if(nukta.contains(s))
		{
			//System.out.println("here");
			return true;
		}
			else 
		return false;
	}

	private static boolean isMatra(char charAt) {
		// TODO Auto-generated method stub
		String s = ""+charAt;
		if(matra.contains(s))
			return true;
		else 
		return false;
	}

	private static boolean isConsonant(char charAt) {
		// TODO Auto-generated method stub
		String s = ""+charAt;
		if(conso.contains(s))
		{
			
			return true;
		}
			
		else 
		{
			//System.out.println("here");
			return false;
		}
	}

	private static boolean isVowel(char ch) {
		// TODO Auto-generated method stub
		String s = ""+ch;
		if(vow.contains(s))
			return true;
		else
		return false;
	}   

}
