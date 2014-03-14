package main.java.chat.util;

/**
 * 
 * @author alex
 *
 */
public interface VariableInjector
{
    String inject( String inputSentence, String... variables );
}
