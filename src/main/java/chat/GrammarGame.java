package main.java.chat;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import opennlp.tools.util.Sequence;

public class GrammarGame {

	// Instances
	POS pos1 = null;
	Scanner scan = new Scanner(System.in);
	Random random = new Random();
	// WordTypes
	public final String[] Adjective = { "JJ", "JJR", "JJS", "WRB" };
	public final String[] Verb = { "VB", "VBD", "VBG", "VBN", "VBP", "VBZ" };
	public final String[] Adverb = { "RB", "RBR", "RBS", "WRB" };
	public final String[] Noun = { "NN", "NNS", "NNP", "NNPS" };
	public String[] selected = null;
	public String name = null;
	// WordType Integer
	Integer randomInteger;

	// constructor
	public GrammarGame() {
		pos1 = new POS();
	}

	// play
	public void play() {
		String rSentence = selectRandomSentence();
		play(rSentence);
	}

	// play
	public void play(String sentence) {
		// hand userInput String into the POS object
		pos1.init(sentence);

		// select the word type to be used
		selected = selectWordType();

		// collect POS data
		String[] words = pos1.getSent();
		String[] tags = pos1.getTags();

		// Copy/Translate string[]tags to String[] tagsT.
		String[] tagsT = new String[tags.length];
		System.arraycopy(tags, 0, tagsT, 0, tags.length);
		for (int i = 0; i < tags.length; i++) {
			tagsT[i] = pos1.translateTag(tagsT[i]);
		}

		// collect correct answer from POS data
		Integer correct = 0;
		ArrayList<String> correctWords = new ArrayList<>();
		// for each tag in the selected array of tags
		for (String s : selected) {
			// and for each tag in the input words array
			for (int i = 0; i < tags.length; i++) {
				// if match
				if (tags[i].equalsIgnoreCase(s)) {
					if(words[i] != null){ //!@#null handled
					// add 1 to correct int
					correct++;
					// add word to correct arraylist
					correctWords.add(words[i]);
					}
				}
			}// forW
		}// forT

//		// test
//		System.out.println("Correct: " + correct);
//
//		// test
//		System.out.println("---tags[]:\n");
//		for (String s : tags) {
//			System.out.println(s);
//		}
//		System.out.println("----words[]:\n");
//		for (String s : words) {
//			System.out.println(s);
//		}
//		// test
//		System.out.println("---correctWords ArrayList:\n");
//		for (String s : correctWords) {
//			System.out.println(s);
//		}

		// user Instructions
		System.out.println("Read the sentence below. How many " + name
				+ "s can you identify within?\nWrite the number of "+name+"s, or all of the correct "+name+"s in the sentence.\n");
		System.out.println("\"" + sentence + "\"");

		// scan user input
		String[] response1a = scan.nextLine().split(" ");
		// add the string equivelant and the right number into a string[]
		String[] correctNumberString = { correctNumberString(correct),
				correct.toString() };

				
		// analyze correctness of user response1a
		
		//assume first that the user wrote the number of elements
		Boolean yes = false;
		
		//check that the number is correct
		tag1: for (String r : response1a) {
			for (String c : correctNumberString) {
				if (r.equalsIgnoreCase(c)) {
					yes = true;
					break tag1;
				}
			}
		}// forR
		
		//assume that the user wrote out ALL of the elements
		if(!yes){
			//counter n
			Integer n=0;
			for(String r: response1a){
				for (String s : correctWords) {
					if (r.equalsIgnoreCase(s)) {
						n++;
					}
				}
			}//forR
			if(n==correct){
				yes=true;
			}
		}//if
		
		// end condition
		if (yes) {
			// success
			System.out.println("Congrats, you got all " + correctWords.size()
					+ " of the " + name + "s right!\n" + name + "(s):");
			for (String s : correctWords) {
				System.out.println(s);
			}
		} else {
			// fail
			System.out
					.println("I'm sorry, you did not find all of the correct "
							+ name + "s." + "\nThe correct list of " + name
							+ "s are:");
			for (String s : correctWords) {
				System.out.println(s);
			}
		}

	}// play

	// supporting methods

	// correctNumberString
	public String correctNumberString(Integer in) {
		String result = null;
		switch (in) {
		case 1:
			result = "one";
			break;
		case 2:
			result = "two";
			break;
		case 3:
			result = "three";
			break;
		case 4:
			result = "four";
			break;
		case 5:
			result = "five";
			break;
		case 6:
			result = "six";
			break;
		case 7:
			result = "seven";
			break;
		case 8:
			result = "eight";
			break;
		case 9:
			result = "nine";
			break;
		case 10:
			result = "ten";
			break;
		case 11:
			result = "eleven";
			break;
		case 12:
			result = "twelve";
			break;
		case 13:
			result = "thirteen";
			break;
		case 14:
			result = "fourteen";
			break;
		case 15:
			result = "fifteen";
			break;
		default:
			break;

		}

		return result;
	}

	// SelectWordType
	public String[] selectWordType() {
		// random integer
		randomInteger = random.nextInt(4);
		switch (randomInteger) {
		case 0:
			name = "noun";
			return Noun;
		case 1:
			name = "verb";
			return Verb;
		case 2:
			name = "adjective";
			return Adjective;
		case 3:
			name = "adverb";
			return Adverb;
		default:
			System.out.print("Error selecting word type");
			return null;
		}// switch
	}// testSwitch

	// selectRandomSentence
	public String selectRandomSentence() {
		String result = null;
		String[] sentences = {
				"The dog and the cat do not like eachother.",
				"This sentence is very short.",
				"I think that this project is driving me crazy.",
				"Brian and his friend like to commit crime.",
				"You are getting bored playing this game aren't you?"};

		result = sentences[random.nextInt(sentences.length)];
		return result;
	}
	// public static void main(String[] agrs) {
	// POS api1 = new POS();
	// api1.init();
	//
	// for (Sequence s : topSequences) {
	// System.out.println(s);
	// }
	//
	// int i=0;
	// for (String z: tags){
	// System.out.print(sent[i].toString());
	// String translation=api1.translateTag(z);
	// System.out.print("_#"+translation+" ");
	// i++;
	// }
	// }// main

	//

}// class
