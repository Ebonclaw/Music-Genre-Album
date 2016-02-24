package ca.ubc.ece.eece210.mp2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An abstract class to represent an entity in the catalogue. The element (in
 * this implementation) can either be an album or a genre.
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public abstract class Element {
	//
	protected ArrayList<Album> albumList;
	protected ArrayList<Genre> subGenres;
    //private Genre overallGenre;
	protected Album album;
	protected Element ele;
    //field for Genre
	protected ArrayList<Album> ablumList;//albums in genre
	protected ArrayList<Genre> genreList ;// Sub genres in a more general genre: it also contains sub genres and albums
	public Genre parentGenre; 
	public String genreName;  // the name the genre is going to be called
	public String parentGenreforG;
	
	
	/**
	 * Returns all the children of this entity. They can be albums or genres. In
	 * this particular application, only genres can have children. Therefore,
	 * this method will return the albums or genres contained in this genre.
	 * 
	 * @return the children
	 */
	public List<Element> getChildren() {
		// TODO implement this
		List<Element> children = new ArrayList<>();
		//first we get all albums
		for(Album currentAlbum: this.albumList)
			children.add(currentAlbum);
		//then we add all genres
		for(Genre currentGenre: this.subGenres)
			children.add(currentGenre);
		
		return children;
	}

	/**
	 * Adds a child to this entity. Basically, it is adding an album or genre to
	 * an existing genre
	 * 
	 * @param b
	 *            the entity to be added.
	 */
	protected void addChild(Element b) throws IllegalArgumentException {
		// TODO implement this (should throw an exception if trying to add
		// to a leaf object);
		//if(hasChildren())
			//throw new IllegalArgumentException();
		//else{  
			//we check what b is, genre(True) or album(False)
			if(b.hasChildren()){
				//if it is a genre, we add it to the sub-genre list
				genreList.add((Genre)b);
				
			}
			else
				//if it is an album, we add it to the Album list
				albumList.add((Album)b);
		//}
	}

	/**
	 * Abstract method to determine if a given entity can (or cannot) contain
	 * any children.
	 * 
	 * @return true if the entity can contain children, or false otherwise.
	 */
	public abstract boolean hasChildren();
	
	/**-----------------[[[NEW METHOD]]]--------------------------------------------------------------
	 * return a LinkedList that contains all the informations in order as the string that is passed
	 * eg. passed{tag1|tag2|name1|tag2|tag3|name2|tag3|tag1} "|"is just indicator of different "information",
	 *     it is not in the original string. the passed string really looks like
	 *     {tag1tag2name1tag2tag3name2tag3tag1}
	 *     
	 *     
	 * @param stringRepresentation
	 * @precondition stringRepresentation has to be in the correct format 
	 * @return a linked list that contains all the elements in a string representation in the for of 
	 *         {"tag1" "tag2" "name1" "tag2" "tag3" "name" "tag3" "tag1"}
	 * 
	 * 
	 * *reference from Lab4 HtmlTag*
	 */
	//TODO: collaborate with MyTokenizer from Megan
	protected static LinkedList toLinkedList(String stringRepresentation){
		LinkedList<String> repQue = new LinkedList<String>(); //the que containing all informations in order
		//we convert string into string buffer so we can use the indexes of string buffer.
		StringBuffer stringRep = new StringBuffer(stringRepresentation);
		String infos="" ; //information between tags.
		String tag; //tag to be put into the buffer.
		
		//when we have not done converting string into a queue of segments(eg. tag, info),
		//we keep looping.
		while(stringRep.length()!=0){
			//we check if the one we encounter is a tag
			if(stringRep.substring(0,1).equals("<")){
				tag = nextTag(stringRep);//we get the first tag in the stringRep
			
				repQue.add(tag);//add the first tag to the representation queue(always to the last of Que)

				//we delete the whole tag (indexOf returns the first occurrence)
				stringRep.delete(stringRep.indexOf("<"),stringRep.indexOf(">")+1);
				
			}
			//as we always keep the segment(eg. tag, info) in the very front of our string buffer
			//we can start searching from the head of string buffer.
			else{
				int count = 0;//this count is going to count how many chars we are going to delete
				//we iterate trough the infos between tag and stop when we come to a tag
				for(int i = 0;i < stringRep.length() && !stringRep.substring(i,i+1).equals("<");i++,count++){
				    // now we make all the informations between tags into a string.
			    	infos += stringRep.substring(i,i+1).toString();
				}
				//we add the string to the end of Que
				repQue.add(infos);
				//now we delete the infos we just added to repQue, so we have same "for" condition
				for(int countDel = 0; countDel < count; countDel++){
					//delete the one segment we are on! and the char to be deleted 
					//is always going to be the first one in string buffer
					stringRep.delete(0,1);
				}
				//we have to refresh it after using so it can store 
				//new segment of information next time
				infos = "";
		    }
			
		}
		return repQue;//finally, whew
	}
				
	/**[[[NEW METHOD]]] 
	 * get the First tag in passed string buffer
	 * 
	 * @param stringRep
	 * @return
	 */
	public static String nextTag(StringBuffer stringRep){
		String tag = "";//if does not initialize with "" it caused bug
		int i = stringRep.indexOf("<"); // get the first char of a tag to start with
		
		//we check if it is an end so we can return
		for(;i<stringRep.length() && !stringRep.substring(i,i+1).equals(">"); i++){ 
			//(i,i+1) means that we only need the substring at this particular index i 
			//(see TestSubStringMethod, we got that(i,i) wouldn't work)
			tag += stringRep.substring(i,i+1);//this line should get a tag in form of"<tag>"
		}
		tag += ">"; // because we stopped at ">" and we did not take it so we have to add it here
		return tag;
	}
	
	

	/**[[[NEW METHOD]]]
	 * return if the passed tag is an open tag
	 * 
	 * @param tag
	 * @return boolean results
	 */
	public static boolean isOpenTag(String tag){
		//we convert string to string buffer(so we can use more method and make it mutable)
		StringBuffer tagToCheck = new StringBuffer(tag);
		//we see if there is closing indicator.
		int countClosing = 0;
		for(int i=0;i<tagToCheck.length();i++){
			//if it is an closing tag, we have count closing greater than 0
			if(tagToCheck.substring(i,i+1).equals("/"))
				countClosing++;
			//if we do not have closing indicator , we iterate through the string buffer doing nothing
		}
		if(countClosing > 0)
			return false; //if there is closings in the tag we say isOpenTag is false.
		else
			return true;
	}
	
	/**[[[NEW METHOD]]]
	 * Returns if the tag passed is an album tag
	 * 
	 * @param current tag that we are looking at
	 * @precondition it has to be a tag
	 * @return if it is an <a> or </a> tag
	 * 
	 */
	public static boolean isAlbumTag(String tag){
		String yes = "a"; // the a tag content
		if(whatDoesTheTagSay(tag).equals(yes))
			return true;// if it is an "a" tag we say true
		else
			return false;			
	}
	
	/**[[[NEW METHOD]]]
	 * Returns if the tag passed is an Genre tag
	 * 
	 * @param tag
	 * @precondition is has to be a tag
	 * @return
	 */
	public static boolean isGenreTag(String tag){
		String yes = "g"; // the genre tag content
		if(whatDoesTheTagSay(tag).equals(yes))
			return true;//if it is an "g" tag we say true
		else
			return false;
	}
	
	/**[[[NEW METHOD]]]
	 * return the string in between of the tags, the tag can have more than one chars in it
	 * even though we only used one-character tags (eg </a>,<g>,<s>).
	 * 
	 * @param tag to be determined
	 * @return what is the characters in between of the tags
	 * 
	 */
	protected static String whatDoesTheTagSay(String tag){
		// the tag type
		String tagType = "";
		//we convert it into a new string buffer so we can modify it more easily
		StringBuffer tagToCheck = new StringBuffer(tag);
		int length = tagToCheck.length();
		for(int count = 0;count < length;count++){
			//check the first element in the string buffer
			String element = tagToCheck.substring(0,1);
			//here we delete all the "tag indicators" and just extract the content from it
			if(element.equals("<"))
				tagToCheck.delete(0,1);
			else if(element.equals("/"))
				tagToCheck.delete(0,1);
			else if(element.equals(">"))
				tagToCheck.delete(0,1);
			else{
				//if it is not a tag indicator, then it has to be the content
				tagType += tagToCheck.substring(0,1);
				//and we still have to delete it and check the next element
				tagToCheck.delete(0,1);
			}
		}
		return tagType;
	}
	
	/**[[[NEW METHOD]]]
	 * return if the given piece is a tag or not, even though we did not 
	 * initialize the tag content we still say it is a tag of the form <content>
	 * 
	 * @param segment to be tested
	 * @return true if it is a tag, false if otherwise
	 */
     protected static boolean isTag(String segment){
		//we convert it into a new string buffer so we can modify it more easily
		StringBuffer segToCheck =new StringBuffer(segment);
		//if it starts with "<" and end with ">" it is a tag and we assume ">" only appears once
		if(segToCheck.substring(0,1).equals("<") && segToCheck.indexOf(">") == segToCheck.length()-1)
			return true;
		else
			return false;
		
	}
}

    