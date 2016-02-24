package ca.ubc.ece.eece210.mp2;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author Sathish Gopalakrishnan
 * 
 * This class contains the information needed to represent 
 * an album in our application.
 * 
 */

public final class Album extends Element {
	
	// fields for album objects 
		protected String title; 
		protected String performer; 
		protected ArrayList<String> songlist;
		protected Album albumName;
		
		
		// List of delimeters 
		public static final String titleOpen = "<t>";
		public static final String titleClose = "</t>";
		public static final String performerOpen = "<p>";
		public static final String performerClose = "</p>";
		public static final String songOpen = "<s>";
		public static final String songClose = "</s>";
		public static final String songSeparator = "|";
		public static final String genreOpen = "<g>";
		public static final String genreClose = "</g>";
		public static String albumOpen = "<a>";
		public static String albumClose = "</a>";
		
	/**
	 * Builds an album with the given title, performer and song list
	 * 
	 * @param title
	 *            the title of the album
	 * @param author
	 *            the performer 
	 * @param songlist
	 * 			  the list of songs in the album
	 */
	public Album(String title, String performer, ArrayList<String> songlist, Genre parentGenre) {
		// TODO implement this
		this.title = title; 
		this.performer = performer;
		this.songlist = songlist; 
		this.parentGenre = parentGenre;
	}


	/**
	 * Builds an album from the string representation of the object. It is used
	 * when restoring an album from a file.
	 * 
	 * @param stringRepresentation
	 *            the string representation
	 * @return 
	 * @return 
	 * @throws an exception if the string is null
	 */
	public Album (String stringRepresentation) {
		// TODO implement this
		
		// Restores the title 
		String restoredTitle = StringUtils.substringBetween(stringRepresentation, "<t>", "</t>");
		// Restores the performer
		String restoredPerformer = StringUtils.substringBetween(stringRepresentation, "<p>", "</p>");
		// Restores Genre and then makes a genre object that goes with it 
		String restoredGenreString = StringUtils.substringBetween(stringRepresentation, "<g>", "</g>");
		Genre restoredGenre = new Genre(restoredGenreString); 
		
		//Creates a string that removes the opening and closing statements 
		String SongString = StringUtils.substringBetween(stringRepresentation, "<s>", "</s>");
	    
		//Create an empty array to store songs 
		ArrayList songsArray = new ArrayList(); 
		
		// Create a tokenizer which splits on the song separator 
		StringTokenizer st = new StringTokenizer(SongString, songSeparator); 
		
		// While the tokenizer has more tokens, add them to songArray 
		while (st.hasMoreTokens()) {
			songsArray.add(st.nextToken()); 
		}
		
		
		this.title = restoredTitle; 
		this.performer = restoredPerformer; 
		this.songlist = songsArray; 
		this.parentGenre = restoredGenre; 
		
		
		
		
	}

	/**
	 * Returns the string representation of the given album. The representation
	 * contains the title, performer and songlist, as well as all the genre
	 * that the album belongs to.
	 * 
	 * @return the string representation
	 */
	public String toString() {
		// TODO implement this
	
		
		String stringRepresentation = ""; //the value to be returned
		String stringOfSongs = "";
		String oldSongString = ""; 
		//now we add songs from list
		
		for (int i=0; i<songlist.size(); i++){
		
		
			String storeSong = songlist.get(i);
			
			if (i < songlist.size()-1){
				storeSong = (storeSong + songSeparator);// add separator if current song is not the last
			}
			
			stringOfSongs = oldSongString + storeSong; 
			oldSongString = stringOfSongs;
		
		}
		
		stringRepresentation = albumOpen + genreOpen+ parentGenre.genreName + genreClose+ titleOpen + title + titleClose + performerOpen + performer + performerClose + songOpen 
				+ stringOfSongs + songClose + albumClose;
		return stringRepresentation;
	
	}



	/**
	 * Add the album to the given genre
	 * 
	 * @param genre
	 *            the genre to add the album to.
	 */
	public void addToGenre(Genre genre) {
		// TODO implement this
		genre.addChild(albumName);
		parentGenre = genre; 
	}

	/**
	 * Returns the genre that this album belongs to.
	 * 
	 * @return the genre that this album belongs to
	 */
	public Genre getGenre() {
		// TODO implement this
		return parentGenre;
	}

	/**
	 * Returns the title of the album
	 * 
	 * @return the title
	 */
	public String getTitle() {
		// TODO implement this
		return title;
	}

	/**
	 * Returns the performer of the album
	 * 
	 * @return the performer
	 */
	public String getPerformer() {
		// TODO implement this
		return performer;
	}

	/**
	 * An album cannot have any children (it cannot contain anything).
	 */
	@Override
	public boolean hasChildren() {
		return false;
	}
	

}
