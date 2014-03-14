package main.java.chat;

import static main.java.chat.util.Util.*;
import java.util.Scanner;

/**
 * 
 * @author alex
 *
 */
public final class MyChat implements Chat
{
    boolean chat;
    Responder responder;
    Scanner scan;

    public MyChat()
    {
        chat = true;
        scan = new Scanner( System.in );
    }

    @Override
    public void initialize( Responder responderIn )
    {
        responder = responderIn;
        responder.readConfigFile( "..\\..\\..\\resources\\config.chat" );
    }

    @Override
    public String getSentence()
    {
        return scan.nextLine();
    }

    @Override
    public void chat()
    {
    	print( "Welcome to Chat." );

        while( chat )
        {
            String sentence = getSentence();

            responder.respond( sentence );
        }
    }
}
