import java.awt.Point;
import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class Tray implements Comparable<Tray>{
	int xLength, yLength;
	String moveFromParent;
	Block[][] board;
	
	ArrayList<Point> heads;
	Tray parentTray;
	public int yLenght;
	static Point[] moves = { new Point(0, -1), new Point(0, +1),
			new Point(-1, 0), new Point(+1, 0) };

	/**
	 * Initializes a tray with lines from Init file
	 * 
	 * @param lineInitArr
	 *            lines in the init file passes as ArrayList of Strings
	 */

	public Tray(ArrayList<String> lineInitArr) {
	
		xLength = Integer.parseInt(lineInitArr.get(0).split("\\s+")[1]);
		yLength = Integer.parseInt(lineInitArr.get(0).split("\\s+")[0]);
		this.board = new Block[this.yLength][this.xLength];
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
		xLength = pTray.xLength;
		yLength = pTray.yLength;
		parentTray = pTray;
		int xtop;
		int ytop;
		int xbottom;
		int ybottom;
		
		board = new Block[this.yLength][this.xLength];
		heads = new ArrayList<Point>();
		for(Point p: pTray.heads){
			heads.add(p);
			xtop = p.x;
			ytop = p.y;
			xbottom = xtop+pTray.board[ytop][xtop].size().x-1;
			ybottom = ytop+pTray.board[ytop][xtop].size().y-1;
			Block b = pTray.board[ytop][xtop];
			for (int j = ytop; j <= ybottom; j++) {
				for (int i = xtop; i <= xbottom; i++) {
					this.board[j][i] = b;
				}
			}
		}
		
	}

	/**
	 * Returns a string of all blocks positions Concatenated to make a unique
	 * hash value
	 */
	@Override
	public String toString() {//reCoded, and double-checked, adjusted the format 
								//to the required output format
		String S = "";
		int size = heads.size();
		int counter = 0;
		for (Point P : heads) {
			//System.out.println("the head is" + P);
			Block B = board[P.y][P.x];
			int xbottom = P.x + B.size.x - 1;
			int ybottom = P.y + B.size.y - 1;
			counter += 1;
			if(counter<size){
				S += P.y +" "+ P.x +" "+ ybottom+ " "+ xbottom+'\n';
			}else{
				S += P.y +" "+ P.x +" "+ ybottom+ " "+ xbottom;				
			}
			
//			if(counter<size){
//				S += P.y +" "+ P.x +" "+ ybottom+ " "+ xbottom;
//			}
		
		}
		return S;
	}

	/**
	 * returns true if the given block can move in the given direction move from
	 * the current location
	 * 
	 * @param block
	 *            Block object under consideration of moment
	 * @param oldTopLeft
	 *            Point object which has the coordinate of the block on the
	 *            board of the this Tray
	 * @param move
	 *            a Point object which indicates which direction to move a unit
	 *            amount
	 * @return returns true if movement is valid else it will return false!
	 */
	public boolean canMove(Block block, Point oldTopLeft, Point move) {//reCoded, and double-checked
		Point newTopLeft     = new Point(oldTopLeft.x + move.x, oldTopLeft.y + move.y);
		Point newBottomRight = new Point(newTopLeft.x + block.size().x-1, newTopLeft.y + block.size().y-1);
		
		if ( move.y > 0 ) {
			if( newBottomRight.y<this.yLength ){
				for (int i = newTopLeft.x; i <= newBottomRight.x; i++) {
					if (board[newBottomRight.y][i]!=null){
						return false;
					}
				}
			}else{
				return false;
			}

		} else if ( move.y<0 ){
			if( newTopLeft.y>=0 ){
				for (int i = newTopLeft.x; i <= newBottomRight.x; i++) {
					if (board[newTopLeft.y][i]!=null){
						return false;
					}
				}
			}else{
				return false;
			}
		}else if( move.x > 0){
			if( newBottomRight.x<this.xLength ){
				for (int j = newTopLeft.y; j <= newBottomRight.y; j++) {
					if (board[j][newBottomRight.x]!=null){
						return false;
					}
				}	
			}else{
				return false;
			}


		}else if( move.x < 0 ){
			if ( newTopLeft.x>=0 ){
				for (int j = newTopLeft.y; j <= newBottomRight.y; j++){
					if (board[j][newTopLeft.x]!=null){
						return false;
					}
				}
			}else{
				return false;
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
	 * @param oldTopLeft
	 *            Point object which has the coordinate of the block on the
	 *            board of the this Tray
	 * @param move
	 *            a Point object which indicates which direction to move a unit
	 *            amount
	 * @return returns the new Tray objects that was created
	 */
	public Tray move(Block block, Point oldTopLeft, Point move) {//reCoded

		Tray T = new Tray(this);
		//System.out.println(T);
		// update T...
		Block b = board[oldTopLeft.y][oldTopLeft.x];
		Point oldBottomRight = new Point(oldTopLeft.x + block.size().x-1, oldTopLeft.y + block.size().y-1);
		Point newTopLeft     = new Point(oldTopLeft.x + move.x, oldTopLeft.y + move.y);
		Point newBottomRight = new Point(newTopLeft.x + block.size().x-1, newTopLeft.y + block.size().y-1);
		
		if ( move.y > 0 ) {
				for (int i = newTopLeft.x; i <= newBottomRight.x; i++) {
					T.board[newBottomRight.y][i] = b;
					T.board[oldTopLeft.y][i]     = null;
				}

		} else if ( move.y<0 ){
			for (int i = newTopLeft.x; i <= newBottomRight.x; i++) {
				T.board[oldBottomRight.y][i] = null;
				T.board[newTopLeft.y][i]     = b;
			}

		}else if( move.x > 0){
			for (int j = newTopLeft.y; j <= newBottomRight.y; j++) {
				T.board[j][newBottomRight.x] = b;
				T.board[j][oldTopLeft.x]     = null;
			}
		} else if( move.x < 0 ){
			for (int j = newTopLeft.y; j <= newBottomRight.y; j++) {
				T.board[j][oldBottomRight.x] = null;
				T.board[j][newTopLeft.x]     = b;
	
			}
		}

////		// update the movesFromParent -of the head
		T.moveFromParent = oldTopLeft.y + " " + oldTopLeft.x + " " + newTopLeft.y + " " + newTopLeft.x;
//		// update the heads
		//Point phead = T.heads.remove(T.heads.indexOf(oldTopLeft));
		//phead.translate(move.x, move.y);
		//T.heads.add(phead);

		
		T.heads.set(T.heads.indexOf(oldTopLeft), newTopLeft);
		
		return T;
	}

	/**
	 * Sets all legal children of the Tray object into the children stack
	 */
	public Stack<Tray> children() {
		Stack<Tray> children = new Stack<Tray>();
		
		for (Point h : heads) {
			for (Point move : moves) {
				if (canMove(board[h.y][h.x], h, move)) {
					
					children.add(move(board[h.y][h.x], h, move));
					
				}
			}
		}
		return children;
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
		Tray t = (Tray) T;
		return (this.toString().equals(t.toString()));
	}

	
	
	@Override
	public int hashCode(){
		String s = this.toString();
		String[] arr = s.split(System.getProperty("line.separator"));
		int hash = 0;
		for(String str: arr){
			hash += str.hashCode()+101;
		}
		return hash;
	}
//
//	@Override
//	public int hashCode(){
//		return this.toString().hashCode();
//	}
	
	
	@Override
	public int compareTo(Tray o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
