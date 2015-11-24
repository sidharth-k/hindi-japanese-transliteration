
import edu.berkeley.nlp.lm.io.MakeKneserNeyArpaFromText;
public class generateArpa {


	public static void main(String[] args) {
		String[] a =  new String[3];
//		System.out.println(a[0]+a[1]);
		a[0]= "5";
		System.out.println(a[0]+a[1]+a[2]);
		a[2]= "/media/sidharth/New Volume/BTP/hindi-syllables";
		a[1]="/media/sidharth/New Volume/BTP/hindi.arpa";
		
	    MakeKneserNeyArpaFromText.main(a);

	}

}
