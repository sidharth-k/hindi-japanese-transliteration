import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class generateSeparatedJapanese {

	private static Scanner scan;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File(args[0]);//"/media/sidharth/New Volume/BTP/Japanese-output"
		File spaced = new File(args[1]);//"/media/sidharth/New Volume/BTP/mappable"
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
//		FileReader read = new FileReader(file);
		FileWriter write = null;
	    try {
			write = new FileWriter(spaced);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String s;
		try {
			while((s=reader.readLine())!=null)
			{
				int i=0;
			//System.out.println(s);
				//s = "ニディ―シュ";
			String res="";
			for(i=0;i<s.length();i++)
			{
				
				if(s.charAt(i)=='ッ')
					{
					res+=" ";
					res+=s.charAt(i);
					}
				else if(s.charAt(i)=='ー')
				{
					res+=s.charAt(i);
					//System.out.println("sdhere");
					res+=" ";
				}
				else if(s.charAt(i)=='ァ'||s.charAt(i)=='ィ'||s.charAt(i)=='ゥ'||s.charAt(i)=='ェ'||s.charAt(i)=='ォ'||s.charAt(i)=='ョ'||s.charAt(i)=='ュ'||s.charAt(i)=='ャ')
				{
					if(i+1<s.length())
					{
						//System.out.println(s.charAt(i+1));
						if(s.charAt(i+1)=='ー')
							{
							res+=s.charAt(i);
							//System.out.println("here");
							i+=1;
							res+=s.charAt(i);
							res+=" ";
							
							}
						else {
							res+=s.charAt(i);
							//System.out.println(s.charAt(i+1));
							res+=" ";
						}
					}
					else {
						res+=s.charAt(i);
						res+=" ";
					}
				}
				else {
					res+=s.charAt(i);
					if(i<s.length()-1)
					{
						if(s.charAt(i+1)!='ァ'&&s.charAt(i+1)!='ィ'&&s.charAt(i+1)!='ゥ'&&s.charAt(i+1)!='ェ'&&s.charAt(i+1)!='ー'&&s.charAt(i+1)!='ォ'&&s.charAt(i+1)!='ョ'&&s.charAt(i+1)!='ュ'&&s.charAt(i+1)!='ャ')
							res+=" ";
					}
				}
				}
					
			System.out.println(res);
			write.write(res);
			write.write("\n");
			write.flush();
			//break;
}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	}


