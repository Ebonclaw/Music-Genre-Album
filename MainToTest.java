package ca.ubc.ece.eece210.mp2;
import java.io.File;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

// Found this class online http://commons.apache.org/proper/commons-lang/download_lang.cgi
import org.apache.commons.lang3.StringUtils;
import java.io.FileNotFoundException;




public class MainToTest {

	public static final String titleOpen = "<t>";
	public Catalogue Test;
	public static String fileName;
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
	  

	 // PrintStream output = new PrintStream(new File("test.txt"));
//	  fileName = "test1textfilemp2.txt";
//     Catalogue Test = new Catalogue (fileName);
//   Test.saveCatalogueToFile("Testresult.txt");
	
		/*
		 String title = "Metamorphosis";
		 
		String performer = "Hilary Duff"; 
		ArrayList<String> songlist = new ArrayList(); 
		songlist.add("So Yesterday"); 
		songlist.add("Come Clean"); 
		songlist.add("Little Voice"); 
		Genre Pop = new Genre("Pop");
		
		
		String songString = songlist.toString(); 
		System.out.println(songString); 
		
		Album Metamorphosis = new Album(title, performer, songlist, Pop); 
		
		String MetString = Metamorphosis.toString(); 
		
		System.out.println(MetString); 
		
		String test = "<t>Metamorphosis</t><p>Hilary Duff</p><s>So Yesterday|Come Clean|Little Voice</s><g>Pop</g>";
		
		
		String SongString = StringUtils.substringBetween(test, "<s>", "</s>");
		
		ArrayList songsArrayTest = new ArrayList(); 
		
		StringUtils.substringBetween(test, "<s>", "</s>");
		
		
		Album restoredAlbum = new Album(test); 
		
		System.out.println(restoredAlbum.title); 
		System.out.println(restoredAlbum.performer);
		System.out.println(restoredAlbum.songlist);
		System.out.println(restoredAlbum.parentGenre.toString()); 
		System.out.println(SongString); 
		
		
		StringTokenizer st = new StringTokenizer(SongString, "|"); 
		
		while (st.hasMoreTokens()) {
			songsArrayTest.add(st.nextToken()); 
		}
		
		*/
		//Grace's test
		String stringToTest ="<g>Pop<a><g>Pop</g><t>Metamorphosis</t><p>Hilary Duff</p><s>So Yesterday|Come Clean|Little Voice</s></a><a><g>Pop</g><t>PopAlbum2</t><p>ArtistPop2 </p><s>So|Clean|Little </s></a><g>HappyPop<a><g>HappyPop</g><t>Meta</t><p>HappyPopArtist</p><s>HiSongHappyPop |HappyPopsong1</s></a><g> SadPop </g></g></g>";
//		 "<g>Pop<a><g>Pop</g><t>Metamorphosis</t><p>Hilary Duff</p><s>So Yesterday|Come Clean|Little Voice</s></a></g>";
//		String stringToTest = "<g>Pop<g>apop</g><g>bpop</g></g>";
		LinkedList<String> testLinkedList = Element.toLinkedList(stringToTest);
        System.out.println(testLinkedList);
//        System.out.println(testLinkedList.get(1));
//        System.out.println(Element.isOpenTag("</g>"));
       Genre testGenre = Genre.restoreCollection(stringToTest);
        System.out.println(testGenre.toString());
        
//		String what= "</a>";
//        System.out.println(Element.whatDoesTheTagSay(what));
	}
}

//
//package ca.ubc.ece.eece210.mp2;
//import java.io.File;
//
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.PrintStream;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.Map;
//import java.util.StringTokenizer;
//import java.util.TreeMap;
//
//// Found this class online http://commons.apache.org/proper/commons-lang/download_lang.cgi
//import org.apache.commons.lang3.StringUtils;
//
//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
//
//import java.io.FileNotFoundException;
//
//
//
//
//public class MainToTest {
//
//	public static final String titleOpen = "<t>";
//	public Catalogue Test;
//	public static String fileName;
//	/**
//	 * @param args
//	 * @throws IOException 
//	 */
//	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
//	  
//		
//		//List tester = new LinkedList("<g>Pop<a><g>Pop</g><t>Metamorphosis</t><p>Hilary Duff</p>");
//	 
//		PrintStream output = new PrintStream(new File("test2.txt"));
//	  
//	  fileName = "test1textfilemp2.txt";
//     Catalogue Test = new Catalogue (fileName);
//    Test.saveCatalogueToFile("Testresult");
//	
//		
//		/*
//		 String title = "Metamorphosis";
//		 
//		String performer = "Hilary Duff"; 
//		ArrayList<String> songlist = new ArrayList(); 
//		songlist.add("So Yesterday"); 
//		songlist.add("Come Clean"); 
//		songlist.add("Little Voice"); 
//		Genre Pop = new Genre("Pop");
//		
//		
//		
//		String songString = songlist.toString(); 
//		System.out.println(songString); 
//		
//		Album Metamorphosis = new Album(title, performer, songlist, Pop); 
//		Metamorphosis.addChild(Pop); 
//		
//		String MetString = Metamorphosis.toString(); 
//		
//		System.out.println(MetString); 
//		
//		String test = "<t>Metamorphosis</t><p>Hilary Duff</p><s>So Yesterday|Come Clean|Little Voice</s><g>Pop</g>";
//		
//		
//		String SongString = StringUtils.substringBetween(test, "<s>", "</s>");
//		
//		ArrayList songsArrayTest = new ArrayList(); 
//		
//		StringUtils.substringBetween(test, "<s>", "</s>");
//		
//		
//		Album restoredAlbum = new Album(test); 
//		
//		System.out.println(restoredAlbum.title); 
//		System.out.println(restoredAlbum.performer);
//		System.out.println(restoredAlbum.songlist);
//		System.out.println(restoredAlbum.parentGenre.toString()); 
//		System.out.println(SongString); 
//		
//		
//		StringTokenizer st = new StringTokenizer(SongString, "|"); 
//		
//		while (st.hasMoreTokens()) {
//			songsArrayTest.add(st.nextToken()); 
//		}
//		
//		*/
//
//	}
//}
//

