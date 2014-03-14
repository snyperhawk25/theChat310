package main.java.chat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SynonymGame {

	// Instances
	String result = null;
	ArrayList<String> dictionary = new ArrayList<>();
	Boolean play = true;
	Scanner scan = new Scanner(System.in);
	Random random = new Random();

	// Constructor
	public SynonymGame() throws IOException {
		getDictionary();
	}

	// getDictionary
	public void getDictionary() throws IOException {
		// Instances
		String line = null;
		BufferedReader read = new BufferedReader(new FileReader(
				"lib/dictionary.txt"));

		int i = 0;
		while ((line = read.readLine()) != null) {
			dictionary.add(i, line);
			i++;
		}

		read.close();
		// test
		System.out.println("Dictionary Size: " + dictionary.size());
	}// getDictionary

	// play
	public void play() {

		String[] correctSyn = null;
		String[] incorrectSyn1 = null;
		String[] incorrectSyn2 = null;
		String[] incorrectSyn3 = null;
		String correctWord = null;
		String incorrectWord1 = null;
		String incorrectWord2 = null;
		String incorrectWord3 = null;

		// create the correct synonym
		do {
			do {
				Integer correctInt = random.nextInt(dictionary.size());
				correctWord = dictionary.get(correctInt);
				correctSyn = (String[]) new Wordnet(correctWord).getSynonyms();
//				// test
//				System.out.println("\nCorrectSyn[]:");
//				for (String s : correctSyn) {
//					System.out.println(s);
//				}
			} while (correctSyn.length < 1);
		} while (correctWord.equalsIgnoreCase(correctSyn[0]));

		// create the 3 incorrect synonyms
		// 1
		do {
			do {
				incorrectWord1 = dictionary.get(random.nextInt(dictionary
						.size()));
				incorrectSyn1 = new Wordnet(dictionary.get(random
						.nextInt(dictionary.size()))).getSynonyms();
//				// test
//				System.out.println("\nInorrectSyn1[]:");
//				for (String s : incorrectSyn1) {
//					System.out.println(s);
//				}
			} while (incorrectSyn1.length < 1);
		} while (incorrectWord1.equalsIgnoreCase(incorrectSyn1[0]));
		// 2
		do {
			do {
				incorrectWord2 = dictionary.get(random.nextInt(dictionary
						.size()));
				incorrectSyn2 = new Wordnet(dictionary.get(random
						.nextInt(dictionary.size()))).getSynonyms();
//				// test
//				System.out.println("\nInorrectSyn2[]:");
//				for (String s : incorrectSyn2) {
//					System.out.println(s);
//				}
			} while (incorrectSyn2.length < 1);
		} while (incorrectWord2.equalsIgnoreCase(incorrectSyn2[0]));
		// 3
		do {
			do {
				incorrectWord3 = dictionary.get(random.nextInt(dictionary
						.size()));
				incorrectSyn3 = new Wordnet(dictionary.get(random
						.nextInt(dictionary.size()))).getSynonyms();
//				// test
//				System.out.println("\nInorrectSyn3[]:");
//				for (String s : incorrectSyn3) {
//					System.out.println(s);
//				}
			} while (incorrectSyn3.length < 1);
		} while (incorrectWord3.equalsIgnoreCase(incorrectSyn3[0]));

		// !@#no (".") period fix

		// Build&Randomize array
		String s1 = new String(correctWord + "=" + correctSyn[0]);
		String s2 = new String(incorrectWord1 + "=" + incorrectSyn1[0]);
		String s3 = new String(incorrectWord2 + "=" + incorrectSyn2[0]);
		String s4 = new String(incorrectWord3 + "=" + incorrectSyn3[0]);
		String[] theCollection = { s1, s2, s3, s4 };
		List<?> theList = Arrays.asList(theCollection);
		Collections.shuffle(theList);
		theCollection = (String[]) theList.toArray();

		// Generate String to output
		System.out
				.println("Which one of the following four word/synonym pairs IS CORRECT (ie.(a=b)==TRUE))?"
						+ "\nWrite one of the words from the pair to answer:\n"
						+ "a) "
						+ theCollection[0]
						+ "\n"
						+ "b) "
						+ theCollection[1]
						+ "\n"
						+ "c) "
						+ theCollection[2]
						+ "\n" + "d) " + theCollection[3] + "\n");

		// get User response
		String[] response = scan.nextLine().split(" ");

		// evaluate correctness
		Boolean correct = false;
		tag: for (String r : response) {
			// word
			if (r.equalsIgnoreCase(correctWord)) {
				correct = true;
				break tag;
			}
			// synonyms
			for (String v : correctSyn) {
				if (v.equalsIgnoreCase(r)) {
					correct = true;
					break tag;
				}
			}
		}// forR

		// correct?
		if (correct) {
			System.out.println("Great Job, you guessed the correct synonym!");
		} else {
			System.out
					.println("Oops, that's incorrect. The correct synonym pair was \""
							+ correctWord + "=" + correctSyn[0] + "\".");
		}

	}// play

	// // Add all synonyms to ArrayList<String> synonyms
	// ArrayList<String> synonyms = new ArrayList<>();
	// synonyms.add(new String(correctWord + "=" + correctSyn[0] + "."));
	// synonyms.add(new String(incorrectWord1 + "=" + incorrectSyn1[0]));
	// synonyms.add(new String(incorrectWord2 + "=" + incorrectSyn2[0]));
	// synonyms.add(new String(incorrectWord3 + "=" + incorrectSyn3[0]));
	// // Randomize the ordering of the elements
	// synonyms = randomOrder(synonyms);

	// // randomOrder
	// private ArrayList<String> randomOrder(ArrayList<String> in) {
	// ArrayList<String> result = new ArrayList<>();
	//
	// // test
	// System.out.println("in:");
	// for (String s : in) {
	// System.out.println(s);
	// }
	//
	// ArrayList<Integer> chosenIntegers = new ArrayList<>();
	// for (int i = 0; i < 4; i++) {
	// // Random number between 0 and size
	// Integer index = random.nextInt(in.size());
	// // add to chosen numbers list
	// chosenIntegers.add(index);
	// result.add(in.get(index));
	// in.remove(index);
	// }
	//
	// // test
	// System.out.println("result:");
	// for (String s : result) {
	// System.out.println(s);
	// }
	//
	// return result;
	// }

}// class
