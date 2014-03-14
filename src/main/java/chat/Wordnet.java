package main.java.chat;

import java.util.ArrayList;

import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;

public class Wordnet {

	//Instances
	private WordNetDatabase database1=null;
	private Synset[] synsetList=null;

	//constructor
	public Wordnet(final String userInput){
		
		System.setProperty("wordnet.database.dir", "lib\\WordNet\\2.1\\dict");
		WordNetDatabase database1=WordNetDatabase.getFileInstance();
		synsetList=database1.getSynsets(userInput);
	}
	
	//getSynonyms
	public String[] getSynonyms(){
		ArrayList<String[]> arrayTemp=new ArrayList<>();
		ArrayList<String> stringTemp=new ArrayList<>();
		String[] result=null;
		
		//add synset elements to temporary ArrayLists
		
		for(int i=0;i<synsetList.length;i++){
			arrayTemp.add(i, synsetList[i].getWordForms());			
		}//synsetList to ArrayList<[string words]>
			
		for(int j=0; j<arrayTemp.size();j++){
			for(int k=0;k<arrayTemp.get(j).length;k++){
				stringTemp.add(arrayTemp.get(j)[k]);
			}
		}//ArrayList<[string words]> to ArrayList<string words>
		
		//ArrayList<string words> to String[words]
		result=new String[stringTemp.size()];
		for(int l=0;l<stringTemp.size();l++){
			result[l]=stringTemp.get(l);
		}
		
//		//output string[] result
//		System.out.println("This is the getSynonyms() result:\n");
//		for(String s:result){
//			System.out.println(s);
//		}
		return result;
	}
	
//	//getSynonyms2
//	public String[] getSynonyms2(){
//		String[] result=database1.getBaseFormCandidates(userInput, synsetList);
//	}
	
//	//getDefinition
//	public String getDefinition(){
//		Synset word=new Synset("the");
//		//String def=synsetList[i].
//		return null;
//	}
	
}//class
