package main.java.chat.util;

/**
 * 
 * @author alex
 *
 */
public final class Util
{
	public static void print( String message )
	{
		System.out.println( message );
	}
	
	/**
	 * Checks if a given string starts with any of the given inputs
	 * @param string 	The string to check
	 * @param match		Array of elements that are allowed to match
	 * @return		@value true if the string starts with any one of the elements in the match array.
	 * 				@value false otherwise
	 */
    public static boolean startsWith( String string, String... match )
    {
    	for( String m : match )
    	{
    		if( string.startsWith( m ) ) return true;
    	}
    	return false;
    }
    
    /**
     * Choose a random element from a string array to return
     * @param array	The input array of strings to choose from
     * @return		A random element from the array;
     */
    public static String randomFromArray( String... array )
    {
    	int rand = (int) Math.floor( Math.random() * array.length );		
		return array[ rand ];
    }
}
