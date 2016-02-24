package ca.ubc.ece.eece210.mp2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Container class for all the albums and genres. Its main 
 * responsibility is to save and restore the collection from a file.
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public final class Catalogue {
	
	protected ArrayList<Genre> genres; // Contain all genres in a array list
	//protected StringBuilder stringfile;
    protected Genre currentGenre;
	/**
	 * Builds a new, empty catalogue.
	 */
	public Catalogue() {
		// TODO implement
		genres = new ArrayList<> (); // empty catalogue
		
		
	}

	/**
	 * Builds a new catalogue and restores its contents from the 
	 * given file.
	 * 
	 * @param fileName
	 *            the file from where to restore the library.
	 * @throws IOException 
	 */
	public Catalogue(String fileName) throws IOException {
		// TODO implement
		
		
		Reader file = new FileReader (fileName);
		
		BufferedReader input = new BufferedReader (file);
		
		try {
			String stringfile = input.readLine();
			
			 // split the whole string into a string list by the <end>
			// which means each string element contains a information of one genre

			String[] Genreinformation = stringfile.split("<end>");
			for (int i=0;i <Genreinformation.length;i++){
				
				genres.add(Genre.restoreCollection(Genreinformation[i])); // convert the string into a genre and add all genres into the arraylist
				
			}

			
		//	while (line != null){
			//	stringfile.append(line);
		//		line = input.readLine();
		//	}
		}
		finally{
			input.close();
		}

	}

	/**
	 * Saved the contents of the catalogue to the given file.
	 * @param fileName the file where to save the library
	 * @throws FileNotFoundException 
	 */
	public void saveCatalogueToFile(String fileName) throws FileNotFoundException {
		
		PrintStream output = new PrintStream(new File(fileName));// build a new file to record all information
		
		
		for (int i = 0; i< genres.size();i++){             //a loop to save all genres in the catalogue into the 
			                                               // given file
			output.println(genres.get(i).toString());
		}
		
		output.close();                              // close the file
		
		
	}
}