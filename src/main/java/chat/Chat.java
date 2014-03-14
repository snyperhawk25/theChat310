package main.java.chat;

/**
 * 
 * @author alex
 *
 */
public interface Chat
{

    void initialize( Responder responder );

    /**
     * Get the sentence from the user
     * @return the String of the sentence
     */
    String getSentence();

    void chat();

}
