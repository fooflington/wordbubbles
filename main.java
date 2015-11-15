import java.io.*;
import java.util.*;
import uk.org.mafoo.wordbubbles.*;

class main {

	public static void main(String[] args) throws IOException {
		WordList words = WordList.loadWordListFromFile(args[0]);
		System.out.println(words);
		// String[] testwords = new String[]{"test", "amit", "ppp", "zebra"};
		// for (String w : testwords) {
		// 	System.out.println(w + "? " + words.checkWord(w) + " / " + words.checkPrefix(w));
		// }

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		ArrayList<ArrayList<Character>> grid = new ArrayList<ArrayList<Character>>();

		while ((s = in.readLine()) != null && s.length() != 0) {
			if(s.startsWith("#")) continue;
			ArrayList<Character> row = new ArrayList<Character>();
			for ( char c : s.toLowerCase().toCharArray() ) {
				row.add(new Character(c));
			}
			grid.add(row);
		}

		// for ( ArrayList<Character> row : grid ) {
		// 	for (Character c : row) {
		// 		System.out.print((c != null ? c : " ") + " ");
		// 	}
		// 	System.out.println(".");
		// }
		Prison prison = new Prison(grid);
		System.out.println(prison);

		System.out.println(prison.search(words));

		// System.out.println(prison.getNeighbours(prison.getCell(0,2)));
	}

}