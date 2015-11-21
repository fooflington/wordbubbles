package uk.org.mafoo.wordbubbles;

import java.util.*;
import java.io.*;

public class Lexicon {

	HashMap<String, Object> words = new HashMap<String, Object>();
	HashMap<String, Object> prefixes = new HashMap<String, Object>();
	static final int maxwordlength = 9;

	public Lexicon (ArrayList<String> wordlist) {
		for (String word : wordlist) {
			if(word.length() > maxwordlength) continue;
			word = word.toLowerCase();
			words.put(word, true);
			char[] word_a = word.toCharArray();
			String prefix = "";
			for(int i=0; i<maxwordlength && i<word_a.length; i++) {
				prefix += word_a[i];
				prefixes.put(prefix, true);
			}
		}
	}

	public boolean checkWord(String word) {
		// System.out.println("Checking word: " + word + " -> " + words.containsKey(word));
		return words.containsKey(word);
	}

	public boolean checkPrefix(String prefix) {
		// System.out.println("Checking prefix: " + prefix + " -> " + prefixes.containsKey(prefix));
		return prefixes.containsKey(prefix);
	}

	public static Lexicon loadLexiconFromFile(String file) throws FileNotFoundException, IOException {
		FileReader fr = new FileReader(file); 
		BufferedReader br = new BufferedReader(fr); 
		String s; 
		ArrayList<String> strings = new ArrayList<String>();
		while((s = br.readLine()) != null) { 
			if(s.length() <= maxwordlength)
				strings.add(s);
		} 
		fr.close(); 
		return new Lexicon(strings);
	}

	public String toString() {
		return "Lexicon object with " + words.size() + " words and " + prefixes.size() + " word prefixes";
	}
}