package uk.org.mafoo.wordbubbles;

import java.util.*;

public class Prison {

	private ArrayList<ArrayList<Cell>> cells = new ArrayList<ArrayList<Cell>>();

	public Prison(ArrayList<ArrayList<Character>> grid) {
		// System.out.println("Initialising Prison");

		for (int i=0; i<grid.size(); i++) {
			ArrayList<Cell> row = new ArrayList<Cell>();
			for (int j=0; j<grid.get(i).size(); j++) {
				// System.out.println("[" + i + "][" + j + "]");
				Character c = grid.get(i).get(j);
				row.add((c.charValue() != ' ' ? new Cell(c, i, j) : null));
			}
			cells.add(row);
		}
	}

	public ArrayList<Cell> getNeighbours(Cell cell) {
		ArrayList<Cell> neighbours = new ArrayList<Cell>();
		for(int i=-1; i<=1; i++) {
			for(int j=-1; j<=1; j++) {
				if( !(i==0 && j==0) ) {
					try {
						Cell c = cells.get(cell.x + i).get(cell.y + j);
						if (c != null) neighbours.add(c);
					} catch (NullPointerException e) {
						// Swallow nulls as cell doesn't exist
					} catch (ArrayIndexOutOfBoundsException e) {

					} catch (IndexOutOfBoundsException e) {

					}
				}
			}
		}
		return neighbours;
	}

	public String toString() {
		String s = new String();
		for ( ArrayList<Cell> row : cells ) {
			for (Cell c : row) {
				s += (c != null ? c : " ");
			}
			s += "\n";
		}
		return s;
	}

	public Cell getCell(int x, int y) {
		return cells.get(x).get(y);
	}

	public ArrayList<String> search(WordList words) {
		ArrayList<String> found = new ArrayList<String>();
		// System.out.println("Started hunting");

		for (int i=0; i<cells.size(); i++) {
			for (int j=0; j<cells.get(i).size(); j++) {
				if(cells.get(i).get(j) != null) {
					try {
						// System.out.println("Starting down a new rabbit hole at " + i + "," + j);
						rabbithole(cells.get(i).get(j), new HashMap<Cell, Object>(), "", words, found);
					} catch (ImpossibleException e) {

					}
				}
			}
		}
		return found;
	}

	private void rabbithole(Cell c, HashMap<Cell, Object> visited, String embryo, WordList words, ArrayList<String> found) throws ImpossibleException {
		// System.out.println("At " + c.x + "," + c.y);

		embryo += c.inmate;

		if(words.checkWord(embryo)) {
			found.add(embryo);
			// System.out.println("Found a word: " + embryo);
		}

		if(words.checkPrefix(embryo)) {
			// Then it's worth carrying on
			// System.out.println("Found valid embryo: " + embryo);
			visited.put(c, true);
			for (Cell next : getNeighbours(c)) {
				if(visited.containsKey(next)) {
					continue;
				} else {
					rabbithole(next, visited, embryo, words, found);
				}
			}
			visited.remove(c);
		}
	}
}