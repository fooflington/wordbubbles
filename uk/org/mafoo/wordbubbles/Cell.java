package uk.org.mafoo.wordbubbles;

import java.util.*;

class Cell {

	protected final char inmate;
	protected final int x, y;
	protected Cell (final char c, final int in_x, final int in_y) {
		if(c >= 'a' && c <= 'z') {
			inmate = c;
			x = in_x;
			y = in_y;
		} else 
			throw new RuntimeException("Unable to create a cell with no inmate");
	}

	public String toString() {
		return "" + inmate;
	}

}