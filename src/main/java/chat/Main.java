package main.java.chat;

/**
 * 
 * @author alex
 *
 */
public class Main
{
    public static void main( String[] args )
    {
        Chat chat = new MyChat();
        chat.initialize( new MyResponder() );

        chat.chat();
    }
}
