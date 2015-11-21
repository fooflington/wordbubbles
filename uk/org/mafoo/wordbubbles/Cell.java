package uk.org.mafoo.wordbubbles;

import java.util.*;

public class Cell {

	protected final char inmate;
	protected int x = -1;
	protected int y = -1;
	protected Cell (final char c, final int in_x, final int in_y) {
		if(c >= 'a' && c <= 'z') {
			inmate = c;
			x = in_x;
			y = in_y;
		} else throw new RuntimeException("Unable to create a cell with no inmate");
	}

	public String toString() {
		return "" + inmate;
	}

	public String toStringVerbose() {
		return "" + inmate + "@" + x + "," + y;
	}
}