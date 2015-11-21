import java.io.*;
import java.util.*;
import uk.org.mafoo.wordbubbles.*;

class main {

	public static void main(String[] args) throws IOException {
		Lexicon words = Lexicon.loadLexiconFromFile(args[0]);
		// System.out.println(words);
		// String[] testwords = new String[]{"test", "amit", "ppp", "zebra"};
		// for (String w : testwords) {
		// 	System.out.println(w + "? " + words.checkWord(w) + " / " + words.checkPrefix(w));
		// }

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		ArrayList<ArrayList<Character>> grid = new ArrayList<ArrayList<Character>>();
		HashMap<Integer, Object> hunted = null;

		while ((s = in.readLine()) != null && s.length() != 0) {
			if(s.startsWith("#")) {
				hunted = new HashMap<Integer, Object>();
				String[] hunted_input = s.split("\\s+");
				for ( int i=1; i<hunted_input.length; i++ ) {
					hunted.put(new Integer(hunted_input[i]), true);
				}
			} else {
				ArrayList<Character> row = new ArrayList<Character>();
				for ( char c : s.toLowerCase().toCharArray() ) {
					row.add(new Character(c));
				}
				grid.add(row);
			}
		}

		// for ( ArrayList<Character> row : grid ) {
		// 	for (Character c : row) {
		// 		System.out.print((c != null ? c : " ") + " ");
		// 	}
		// 	System.out.println(".");
		// }
		Prison prison = new Prison(grid);
		// System.out.println(prison);

		ArrayList<LinkedHashSet<Cell>> found = prison.search(words);
		for ( LinkedHashSet<Cell> w : found ) {
			if(hunted != null) {
				if( hunted.containsKey(new Integer(w.size())) ) {
					String str = "";
					for ( Cell letter : w ) {
						str += letter;
					}
					System.out.println (str);
				}
			} else {
				String str = "";
				for ( Cell letter : w ) {
					str += letter;
				}
				System.out.println(str);
			}
		}

		// System.out.println(prison.getNeighbours(prison.getCell(0,2)));
	}

}