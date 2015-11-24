import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import edu.berkeley.nlp.lm.ArrayEncodedNgramLanguageModel;
import edu.berkeley.nlp.lm.ContextEncodedNgramLanguageModel;
import edu.berkeley.nlp.lm.cache.ArrayEncodedCachingLmWrapper;
import edu.berkeley.nlp.lm.io.LmReader;
import edu.berkeley.nlp.lm.io.LmReaders;


public class transliterate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//generation probability
		GenerationProbability.main();
		HashMap<ArrayList<String>,Double> EmissionProbabilities = (HashMap<ArrayList<String>, Double>) GenerationProbability.GenerationProbabilities;
		
		//Step 1 - generate all the space separated japanese words 
		String filePaths[] = new String[2];
		filePaths[0]="/media/sidharth/New Volume/BTP/Testing/TestSet";
		filePaths[1]="/media/sidharth/New Volume/BTP/Testing/mappableTestSet";
		String MappableJap = filePaths[1];
		File reference = new File("/media/sidharth/New Volume/BTP/Testing/hindiOutput");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(reference);
		} catch (FileNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		BufferedReader read = new BufferedReader(new InputStreamReader(fis));
		
		//"/media/sidharth/New Volume/BTP/Japanese-output"
		//"/media/sidharth/New Volume/BTP/mappable"
		generateSeparatedJapanese.main(filePaths);
		//Step 2 - generate candidates from the above space separated file
		filePaths[0]="/media/sidharth/New Volume/BTP/Testing/mappableTestSet";
		filePaths[1]="/media/sidharth/New Volume/BTP/Testing/TestCandidates";
		String CandidateFile = filePaths[1];
		new_CandidateGenerator.main(filePaths);
		String Candidate = filePaths[1];
		
		
		//Step 3 - generate space separated Hindi syllabic file for probability calculation
		filePaths[0]=CandidateFile;
		filePaths[1]="/media/sidharth/New Volume/BTP/Testing/SyllabicCandidates";
		hindiSyllableGen.main(filePaths);
		String SyllabicCandidate = filePaths[1];
		File output = new File("/media/sidharth/New Volume/BTP/Testing/topCandidates");
		FileWriter writer = null;
		try {
			 writer  = new FileWriter(output);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//Step 4 - using the hindi arpa file generated from the previous set calculate the candidate individual probability
		FileInputStream Fis_map = null;
		try {
			Fis_map = new FileInputStream(new File(MappableJap));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader Jap_mappable_reader = new BufferedReader(new InputStreamReader(Fis_map));
		
		FileInputStream Can_fis = null;
		try {
			Can_fis= new FileInputStream(new File(SyllabicCandidate));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader Candidate_reader = new BufferedReader(new InputStreamReader(Can_fis));
		String jap=null;
		String hin= null;
		try {
			hin = Candidate_reader.readLine();
			hin = Candidate_reader.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<HindiProb> prob = new ArrayList<HindiProb>();
	   System.out.println("here");
	   double correct =0 ,total =0;
	   String Corre = null;
//	try {
//		Corre = read.readLine();
//		System.out.println(Corre);
////		System.exit(0);
//	} catch (IOException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
		try {
			while((jap = Jap_mappable_reader.readLine())!=null&&jap.length()!=0)
			{
				// System.out.println(jap+"**");
				Corre = read.readLine();
				total+=1;
				if(hin.length()!=0&&isNumeric(hin))
				{
				//do nothing	
				}
				else while(hin!=null||!isNumeric(hin))
				{
//					 System.out.println("here"+"d");
					//calculating emission probabilities
					//transition probabilities from ARPA
					
					
					String constant = jap;
					StringTokenizer japanese = new StringTokenizer(jap);
					System.out.println(jap);
					StringTokenizer hindi = new StringTokenizer(hin);
					System.out.println(hin);
					if(japanese.countTokens() == hindi.countTokens())
					{
						
						int j,h;
						j = japanese.countTokens();
						h = hindi.countTokens();
						ArrayList<String> japToHin = new ArrayList<String>();
						int i =0;
						  ContextEncodedNgramLanguageModel<String> hindiSyll = LmReaders.readContextEncodedLmFromArpa("/media/sidharth/New Volume/BTP/Testing/hindi.arpa");
						double emissionProb = 0.0;
						for(i=0;i<j;i++)
						{
//							System.out.println(i);
//							System.out.println(japanese.nextToken());
							japToHin.add(japanese.nextToken());
							japToHin.add(hindi.nextToken());
							if(EmissionProbabilities.containsKey(japToHin))
							emissionProb+= EmissionProbabilities.get(japToHin);
							else emissionProb+=-100.0;
							System.out.println(emissionProb);
							japToHin.clear();
						}
						double genProb ;//= hindiSyll.getLogProb(Arrays.asList(hin.split(" ")));
						genProb = hindiSyll.scoreSentence(Arrays.asList(hin.split(" ")));
						emissionProb+=genProb;
						HindiProb token = new HindiProb(hin, emissionProb);
						prob.add(token);
						
						hin = Candidate_reader.readLine();
					}
					else hin = Candidate_reader.readLine();
//					System.out.println(hin+"jer");
					if(hin==null||isNumeric(hin))
					{
						Collections.sort(prob, new probComparator());
					    System.out.println(prob.get(0).name+"**");
					    writer.write(prob.get(0).name.replaceAll("\\s", "")+"\n");
					    System.out.println(Corre);
					    
					    if(Corre.equals(prob.get(0).name.replaceAll("\\s", "")))
					    	correct+=1;
					    if(prob.size()>=2)
					    {
					    	System.out.println(prob.get(1).name);
						    writer.write(prob.get(1).name.replaceAll("\\s", "")+"\n");
						    if(Corre.equals(prob.get(1).name.replaceAll("\\s", "")))
						    	correct+=1;
					    }
					    if(prob.size()>=3)
					    {
					    	System.out.println(prob.get(2).name);
						    writer.write(prob.get(2).name.replaceAll("\\s", "")+"\n");
						    if(Corre.equals(prob.get(2).name.replaceAll("\\s", "")))
						    	correct+=1;
					    }
					    writer.flush();
					    break;
					}
					jap = constant;
				}
//				hin = Candidate_reader.readLine();
				hin = Candidate_reader.readLine();
				//System.out.println(hin+"herefdjb");
				prob.clear();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(total+" "+correct);
		System.out.println("Accuracy "+correct/total);

	}
private static boolean isNumeric(String input) {
		
	    return input.matches("[-+]?\\d*\\.?\\d+");  
	}
	
}
class probComparator implements Comparator<HindiProb>{
	 
    @Override
    public int compare(HindiProb e1, HindiProb e2) {
    	double probE1 = e1.getprob()*-1;
    	double probE2 = e2.getprob()*-1;
        if(probE1>probE2){
            return 1;
        } else {
            return -1;
        }
    }
}
 class HindiProb{
	String name;
	double prob;
	HindiProb(String name)
	{
		this.name = name;
		this.prob = 0.0;
	}
	HindiProb(String name,double prob)
	{
		this.name = name;
		this.prob = prob;
	}
	public double getprob()
	{
		return prob;
	}
}
 
