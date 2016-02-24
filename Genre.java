package ca.ubc.ece.eece210.mp2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Represents a genre (or collection of albums/genres).
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public final class Genre extends Element {
    //Tags
	public static String genreOpen =  "<g>";
	public static String genreClose = "</g>";
	public static String titleOpen = "<t>";
	public static String titleClose = "</t>";
	public static String performerOpen = "<p>";
	public static String performerClose = "</p>";
	public static String songOpen = "<s>";
	public static String songClose = "</s>";
	public static String songSeparator = "<x>";
	public static String albumOpen = "<a>";
	public static String albumClose = "</a>";
	
	
	static protected Map <String, Genre> MapOfGenres = new HashMap();
	//public Genre newParentGenre;
    /**
	 * Creates a new genre with the given name.
	 * 
	 * @param name
	 *            the name of the genre.
	 */
	public Genre(String name) {
		// TODO implementation
		if (MapOfGenres.containsKey(name)) 
			 {
				MapOfGenres.get(name); 
			 }
		
		else {
			this.genreName = name;// set the name from passed name
			this.albumList = new ArrayList<>();
			this.genreList = new ArrayList<>();
			this.parentGenreforG = "no";
		}
	}

	/**
	 * Restores a genre from its given string representation.
	 * 
	 * @param stringRepresentation
	 */
	public static Genre restoreCollection(String stringRepresentation) {
		// TODO implement
		
		//we change the string into linkedlist 
		LinkedList<String> repQue = toLinkedList(stringRepresentation);
		//pass the linked list to restore genre method
		Genre restoredGenre = restoreGenre(repQue);
		return restoredGenre;
	}

	/**
	 * Returns the string representation of a genre
	 * 
	 * @return a stringRepresentation of the genre.
	 */
	public String toString() {
		// TODO implement
		String stringRepresentation;//the string to be returned
	    String storeGenre = this.genreName;
	    stringRepresentation = genreOpen + storeGenre; // ex.  "<g>Classic"
	    //iterate through album list under parent genre
	    for (int i =0; i < albumList.size();i++){
	      
	    	// stringRepresentation += albumList.get(i).getAlbumContent(); // get all album information into string
	    	stringRepresentation += albumList.get(i).toString();
	    }
	    //iterate through list of sub genre
	    for (int i = 0; i< genreList.size(); i++){
	    	
	    	stringRepresentation += genreList.get(i).toString(); // get all sub genre information into the string
	    	
	    	
	    }
	    
	    
	    stringRepresentation += genreClose; // close the genre tag;
	    if (this.parentGenreforG.equals("no")){
	    	stringRepresentation += "<end>";
	    }
	    
		return stringRepresentation;
	}

	/**
	 * Adds the given album or genre to this genre
	 * 
	 * @param b
	 *            the element to be added to the collection.
	 */
	public void addToGenre(Element b) {
		addChild(b);
		b.parentGenreforG = this.genreName;
		
	}

	/**
	 * Returns true, since a genre can contain other albums and/or
	 * genres.
	 */
	@Override
	public boolean hasChildren() {
		return true;
	}
	
	/**[[[NEW METHOD]]]
	 * restore genre from string representation
	 * 
	 * 
	 * @param repQue
	 * @return Genre restored from a linkedlist
	 */
	protected static Genre restoreGenre(LinkedList repQue){
		//initializing a segment to catch each string in the queue passed
		String segment;		
		//we assume when the method is called the first segment is always going to be genre tag
		String tag = (String) repQue.pollFirst();//this must be a genre
		String nameOfG = (String) repQue.pollFirst();//the second must be a genre name
		Genre newParentGenre = new Genre(nameOfG);//we create new genre object
		
		//we will go until the repQue is totally converted to genre object
		while(repQue.size() != 0){
	
			//we use pollFirst to retrieve the first segment in the queue
			segment=(String)repQue.peek(); 
			
			//we see if the tag is a tag
			if(isTag(segment)){
				//we assume the first tag we meet is a genre tag
				if(isGenreTag(segment)){
					//and we see if it is open?
					if(isOpenTag(segment)){				
						//when the sub loop(recursion of restoreGenre) loops out, we add the restored
						//genre to genreList of our parent
						newParentGenre.addToGenre(restoreGenre(repQue));
						
					}
					else{
						repQue.pollFirst();//poll the </g> off the head of the que
						return newParentGenre;//loop out
					}
				}
				//if it is not a genre tag, it is going to be an album tag
				else {
					String albumInfo = "";
					repQue.pollFirst();//peel <a>
					//we restore all the album segments into string representation of album and then
					//pass it to album constructor(which takes in string representation)
					//stop taking segments when meet album close tag
					while(!repQue.peekFirst().equals(albumClose)){
						albumInfo += repQue.pollFirst();//we add the first segment to album string rep.
					}
					//after we get the string rep of album we pass it to the album constructor in album class
					Album newAlbum = new Album(albumInfo);
					//we add our album to album list
					newParentGenre.albumList.add(newAlbum);
					//as in the for loop we stopped before closing tag so now we are going to take it
					//off linked list
					repQue.pollFirst();
						
				}
			}
			
		}
		return newParentGenre;
	}
}