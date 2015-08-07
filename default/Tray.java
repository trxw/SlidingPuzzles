import java.awt.Point;
import java.util.ArrayList;
import java.util.Stack;

public class Tray {
	int xLenght, yLenght;
	String moveFromParent;
	Block[][] board;
	Stack<Tray> children;
	ArrayList<Point> heads;
	Tray parentTray;
	static Point[] moves = { new Point(0, -1), new Point(0, +1),
			new Point(-1, 0), new Point(+1, 0) };

	/**
	 * Initializes a tray with lines from Init file
	 * 
	 * @param lineInitArr
	 *            lines in the init file passes as ArrayList of Strings
	 */

	public Tray(ArrayList<String> lineInitArr) {
		this.children = new Stack<Tray>();

		xLenght = Integer.parseInt(lineInitArr.get(0).split("\\s+")[1]);
		yLenght = Integer.parseInt(lineInitArr.get(0).split("\\s+")[0]);
		this.board = new Block[this.yLenght][this.xLenght];
		parentTray = null;
		// For blocks:
		int xtop;
		int ytop;
		int xbottom;
		int ybottom;
		int xsize;
		int ysize;
		heads= new ArrayList<Point>();
		for (String line : lineInitArr.subList(1, lineInitArr.size())) {
			xtop = Integer.parseInt(line.split("\\s+")[1]);
			ytop = Integer.parseInt(line.split("\\s+")[0]);
			heads.add(new Point(xtop, ytop));
			xbottom = Integer.parseInt(line.split("\\s+")[3]);
			ybottom = Integer.parseInt(line.split("\\s+")[2]);
			xsize = xbottom - xtop+1;
			ysize = ybottom - ytop+1;
			Block b = new Block(ysize, xsize);
			for (int j = ytop; j <= ybottom; j++) {
				for (int i = xtop; i <= xbottom; i++) {
					this.board[j][i] = b;
				}
			}

		}
	}

	/**
	 * use to construct a tray which will end up being the children of the Tray
	 * 
	 * @param pTray
	 *            the parent Tray to the Tray being created
	 */
	@SuppressWarnings("unchecked")
	public Tray(Tray pTray) {
		// does noting
		xLenght = pTray.xLenght;
		yLenght = pTray.yLenght;
		parentTray = pTray;
		heads = (ArrayList<Point>) pTray.heads.clone();
		board = pTray.board.clone();
	}

	/**
	 * Returns a string of all blocks positions Concatenated to make a unique
	 * hash value
	 */
	@Override
	public String toString() {
		String S = "";
		for (Point P : heads) {
			Block B = board[P.y][P.x];
			int xbottom = P.x + B.size.x - 1;
			int ybottom = P.y + B.size.y - 1;
			S += P.y +""+ P.x +""+ ybottom+ ""+ xbottom;
		}
		return S;
	}

	/**
	 * returns true if the given block can move in the given direction move from
	 * the current location
	 * 
	 * @param block
	 *            Block object under consideration of moment
	 * @param oldP
	 *            Point object which has the coordinate of the block on the
	 *            board of the this Tray
	 * @param move
	 *            a Point object which indicates which direction to move a unit
	 *            amount
	 * @return returns true if movement is valid else it will return false!
	 */
	public boolean canMove(Block block, Point oldP, Point move) {
		Point p = new Point(oldP.x + move.x, oldP.y + move.y);
		Point oldF = new Point(oldP.x + block.size().x, oldP.y + block.size().y);
		
		if (p.x < 0 || p.y < 0 || p.x >= xLenght || p.y >= yLenght) {
			return false;
		} else {
			// generate all the in
			if (move.x == 0) {
				if (move.y > 0) {
					for (int i = oldP.x; i < oldF.x; i++) {
						if (board[oldF.y][i] != null) {
							return false;
						}
					}
				} else {
					for (int i = oldP.x; i < oldF.x; i++) {
						if (board[p.y][i] != null) {
							return false;
						}
					}
				}
			} else {
				if (move.x > 0) {
					for (int j = oldP.y; j < oldF.y; j++) {
						if (board[j][oldF.x] != null) {
							return false;
						}
					}
				} else {
					for (int j = oldP.y; j < oldF.y; j++) {
						if (board[j][p.x] != null) {
							return false;
						}
					}
				}

			}
		}

		return true;
	}

	/**
	 * Creates a Tray object and returns it after cloning this Tray and moving
	 * the given block in the given direction (move) from the current location
	 * (oldP)
	 * 
	 * @param block
	 *            Block object under consideration of moment
	 * @param oldP
	 *            Point object which has the coordinate of the block on the
	 *            board of the this Tray
	 * @param move
	 *            a Point object which indicates which direction to move a unit
	 *            amount
	 * @return returns the new Tray objects that was created
	 */
	public Tray move(Block block, Point oldP, Point move) {

		Tray T = new Tray(this);
		// update T...
		Block b = board[oldP.y][oldP.x];
		Point p = new Point(oldP.x + move.x, oldP.y + move.y);
		Point oldF = new Point(oldP.x + block.size().x, oldP.y + block.size().y);

		if (move.x == 0) {
			if (move.y > 0) { // move down

				for (int i = oldP.x; i < oldF.x; i++) {
					T.board[oldF.y][i] = b;
				}
				for (int i = oldP.x; i < oldF.x; i++) {
					T.board[oldP.y][i] = b;
				}
			} else { // move up
				for (int i = oldP.x; i < oldF.x; i++) {
					T.board[p.y][i] = b;
				}
				for (int i = oldP.x; i < oldF.x; i++) {
					T.board[oldF.y - 1][i] = b;
				}
			}
		} else {
			if (move.x > 0) { // move right
				for (int j = oldP.y; j < oldF.y; j++) {
					T.board[j][oldF.x] = b;
				}
				for (int j = oldP.y; j < oldF.y; j++) {
					T.board[j][oldP.x] = null;
				}
			} else { // move left
				for (int j = oldP.y; j < oldF.y; j++) {
					T.board[j][p.x] = b;
				}
				for (int j = oldP.y; j < oldF.y; j++) {
					T.board[j][oldF.x - 1] = b;
				}
			}
		}
		// update the movesFromParent -of the head
		T.moveFromParent = oldP.y + " " + oldP.x + " " + p.y + " " + p.x;
		// update the heads
		T.heads.get(T.heads.indexOf(oldP)).translate(move.x, move.y);
		
		this.children.push(T);
		return T;
	}

	/**
	 * Sets all legal children of the Tray object into the children stack
	 */
	public void findLegitChildren() {
		for (Point h : heads) {
			for (Point move : moves) {
				if (canMove(board[h.y][h.x], h, move)) {
					children.add(move(board[h.y][h.x], h, move));
				}
			}
		}
	}

	/**
	 * given a Tray object it compares the Tray with this tray and returns true
	 * if they are equal else returns false
	 * 
	 * @param T
	 *            the Tray object to be compared to this
	 * @return true if they are equal else returns false
	 */
	public boolean equals(Tray T) {
		return this.toString().equals(T.toString());
	}
}
