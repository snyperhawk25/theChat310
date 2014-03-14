package main.java.chat;

import static main.java.chat.util.Util.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import main.java.chat.component.Keyword;
import main.java.chat.component.Response;
import main.java.chat.util.ConfigReader;

/**
 * 
 * @author Barrett (with some assistance from Alex)
 * 
 */
public final class MyResponder implements Responder {
	private static List<Keyword> keywords;
	Random random=new Random();
	Scanner scan=new Scanner(System.in);

	public MyResponder() {
	}

	@Override
	public void readConfigFile(String relativePath) {
		keywords = new ArrayList<Keyword>();
		ConfigReader.readConfig(keywords, relativePath);
	}

	@Override
	public void respond(final String inputSentence) {
		String[] split = inputSentence.split("\\s+");
		boolean respond = false;

		search: for (Keyword keyword : keywords) {
			/*
			 * If phrase, do one thing, else, it's a word match so do something
			 * else.
			 */
			if (keyword.getType().equals(Keyword.KeywordType.PHRASE)) {
				for (String k : keyword.getKeywords()) {
					if ((keyword.getSentenceMatch().equals(
							Keyword.MatchType.EXACT) && inputSentence.equals(k))
							|| (keyword.getSentenceMatch().equals(
									Keyword.MatchType.STARTS_WITH) && inputSentence
									.startsWith(k))
							|| (keyword.getSentenceMatch().equals(
									Keyword.MatchType.ENDS_WITH) && inputSentence
									.endsWith(k))
							|| (keyword.getSentenceMatch().equals(
									Keyword.MatchType.CONTAINS) && inputSentence
									.contains(k))) {
						respond = true;
						pickResponse(inputSentence, keyword.getResponses());
						break search;
					}
				}

			}
			// Word //!@# reorder the end with condition to check for other
			// releveant match.
			else { // else, keywordMathchType is a WORD,
				for (int i = 0; i < split.length; i++) {// and, for every user
														// input word,
					String word = split[i].trim(); // !@# at bottom, original
													// Alex code

					// check to see if the keyword can be skipped (ie, an ENDS
					// WITH failure)
					boolean cont = false;
					if (keyword.getSentenceMatch().equals(
							Keyword.MatchType.ENDS_WITH)) {
						cont = true;
						for (String key : keyword.getKeywords()) {
							if (key.contains(split[split.length - 1]))
								cont = false;
						}
					}

					if (cont == true) {
						continue search;
					}

					// and, for every keyword's sub keywords (since there are
					// multiple)
					for (String k : keyword.getKeywords()) {
						if (keyword.getWordMatch().equals(
								Keyword.MatchType.EXACT)
								&& word.equals(k)
								|| keyword.getWordMatch().equals(
										Keyword.MatchType.STARTS_WITH)
								&& word.startsWith(k)
								|| keyword.getWordMatch().equals(
										Keyword.MatchType.ENDS_WITH)
								&& word.endsWith(k)
								|| keyword.getWordMatch().equals(
										Keyword.MatchType.CONTAINS)
								&& word.contains(k)) {
							respond = true;
							pickResponse(inputSentence, keyword.getResponses());
							break search;
						}
					}
				}
			}// word/phrase
		}// keywords

		if (!respond) {
			pickResponse();
		}
	}// response

	private void pickResponse(final String inputSentence,
			final List<Response> responses) {
		boolean question = inputSentence.contains("?")
				|| startsWith(inputSentence, "do", "how", "is", "were", "can",
						"when", "who", "what", "where", "why");
		boolean respond = false;

		search: for (Response response : responses) {
			if (response.getQuestionFlag().equals(
					Response.QuestionFlag.QUESTION_ONLY)
					&& !question
					|| response.getQuestionFlag().equals(
							Response.QuestionFlag.STATEMENT_ONLY) && question) {
				continue search;
			}

			for (String keyword : response.getKeywords()) {
				if (inputSentence.contains(keyword) || keyword.equals("")) {
					respond = true;
					print(randomFromArray(response.getResponses()));
					break search;
				}
			}
		}
		if (!respond) {
			pickResponse();
		}
	}

	private void pickResponse() {
		//choose either genericResponse or game
		Integer randomInteger= random.nextInt(2); //50% chance to suggest play game
		
		if(randomInteger!=0){
			//random response
			print(genericResponse());
		}else{
			//game
			System.out.println("We could play some word games? How about that?");
			
			//first response handeling
			
			
			String[] response1a=scan.nextLine().split(" ");
			
			//load synsets of "okay" + "sure"
			String[] okay=new Wordnet("okay").getSynonyms();
			String[] sure= new Wordnet("sure").getSynonyms();
			String[] choice1={"yes","yeah","by all means", "absolutely", "agreed","yup","yep","ya","yea"};
			ArrayList<String> affirmative=new ArrayList<>();
			affirmative=addTwoStringArraysToArrayList(okay, sure);
			affirmative=addOneStringArrayAndOneArrayListToArrayList(choice1, affirmative);
						
			//first response condition
			boolean yes=checkMatchingWords(affirmative, response1a);
			
		
			breakTag:
			if(yes){
				
				Boolean replay=false;
				do{
				System.out.println("Which game would you like to play?:\n1) Find The Synonym\nor\n2) Grammer Gauntlet");
				
				//second response handeling
				String[] response2a=scan.nextLine().split(" ");
				String[] one={"1","one","first","find","synonym","1)"};
				ArrayList<String> choice2= new ArrayList<>();
				choice2=addOneStringArrayAndOneArrayListToArrayList(one,choice2);
				
				//second response condition
				Boolean game1=checkMatchingWords(choice2, response2a);
				
				
				if(game1){
					System.out.println("Okay, Find The Synonym it is!\n----------------------------------");
					
					try {
						SynonymGame g1=new SynonymGame();
						g1.play();
					} catch (IOException e) {
						System.out.print("G1 error:");
						e.printStackTrace();
					}

				}else{
					System.out.println("Okay, let the Grammar Gauntlet begin!\n----------------------------------");
					GrammarGame g1=new GrammarGame();
					g1.play();
				}
				
				//Prompt user for replay?
				System.out.println("Would you like to replay a game?:");
				
				//third response handeling
				
				String[] response3a=scan.nextLine().split(" ");
				replay=checkMatchingWords(affirmative, response3a);
				
				
				}while(replay);
				
			System.out.println("Let's get back to our conversation.");	
				
			}else{
				//random response
				print(genericResponse());
			}//yes
			
		}
	}//pickResponse
	
	private String genericResponse(){
		String str=randomFromArray("Whatever...",
				"Can we talk about something else?",
				"I don't really have anything to respond to that."
				,"[*awkward silence*]"
				,"There is nothing for me to say.. :("
				,"Why must you ask me about stuff I don't know about.");
		return str;
	}
	
	private ArrayList<String> addTwoStringArraysToArrayList(String[] a1, String[] a2){
		ArrayList<String> result= new ArrayList<>();
		
		for(String s: a1){
			result.add(s);
		}
		
		for(String t: a2){
			result.add(t);
		}
		
		return result;
	}
	
	private ArrayList<String> addOneStringArrayAndOneArrayListToArrayList(String[]a1, ArrayList<String> al1){
		ArrayList<String> result= new ArrayList<>();
		
		for(String s: al1){
			result.add(s);
		}
		
		for(String t:a1){
			result.add(t);
		}
		
		return result;
	}
	
	private Boolean checkMatchingWords(ArrayList<String> matches, String[] userText){
		Boolean result=false;
		
		tag:
		for(String s: userText){
			for(String t: matches){
				if(t.equalsIgnoreCase(s)){
					result=true;
					break tag;
				}
			}
		}
		
		return result;
	}
}// class


////test
//System.out.println("Response1a:");
//for(String s: response1a){
//	System.out.println(s);
//}
//
//System.out.println("\nChoice1:");
//for(String s: choice1){
//	System.out.println(s);
//}
//System.out.println("done....");



