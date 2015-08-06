import java.awt.Point;
import java.util.ArrayList;
import java.util.Stack;


public class Tray {
	int xLenght, yLenght; 
	String moveFromParent;
	Block[][] board; 
	Stack<Tray> children; 
	ArrayList<Point> heads;
	static Point[] moves = {Point(0,-1), Point(0,+1), Point(-1,0), Point(+1,0)};
	
	/**
	 * Initializes a tray with lines from Init file
	 * @param lineInitArr
	 */
	public Tray(ArrayList<String> lineInitArr){
		this.children = new Stack<Tray>();

		this.xLenght = lineInitArr.get(0).split("\\s+")[0];
		this.yLenght = lineInitArr.get(0).split("\\s+")[1];
		this.board = new Block[this.yLenght][this.xLenght];
		//For blocks:
		int xtop;
		int ytop;
		int xbottom;
		int ybottom; 
		int xsize;
		int ysize;
		for(line: lineInitArr.subList(1, lineInitArr.size())){
			
			xtop = line.split("\\s+")[1]; 
			ytop = line.split("\\s+")[0];
			heads.add(new Point(xtop,ytop));
			xbottom = line.split("\\s+")[3];
			ybottom = line.split("\\s+")[2];
			xsize = xbottom-xtop;
			ysize = ybottom-ytop;
			
			Block b = new Block(ysize, xsize);

			for(int j=ytop; j<=ybottom; j++){
				for(int i=xtop; i<=xbottom; i++){
					this.board[j][i] = b;
				}
			}
					
		}
		
		
	}

	 
	
	/**
	 * Returns a string of all blocks positions
	 */
	@Override
	public String toString(){
		
		
	}
	
	
	/**
	 *  
	 * @param block
	 * @param newP 
	 * 1:- up
	 * 
	 * @return
	 */
	public boolean canMove(Block block, Point oldP, move){
		Point p = oldP+move;
		Point oldF = oldP+block.size();
		if(p.x<0 || p.y<0 || p.x>=xLength || p.y>=yLenght){
			return false;
		}else{
			//generate all the in
			if(move.x==0){
				if(move.y>0){
					for(int i = oldP.x; i<oldF.x; i++){
						if(board[oldF.y, i]!=null)
							return false;
					}
				}else{
					for(int i = oldP.x; i<oldF.x; i++){
						if(board[p.y, i]!=null)
							return false;					
				}
			}else{
				if(move.x>0){
					for(int j = oldP.y; j<oldF.y; j++){
						if(board[j, oldF.x]!=null)
							return false;
				}else{
					for(int j = oldP.y; j<oldF.y; j++){
						if(board[j, p.x]!=null)
							return false;	
				}				
			}
			
			return true;
			
		}
		
	}
	
	/**
	 * Should return a new object
	 * @param block
	 * @param newP: 
	 * @return
	 */
	public Tray move(Block block, Point newP, Point oldP){
		// update all instance Ver.... 
		
		return new Tray();
	}
	
	/**
	 * Sets all legal children of the Tray object into the childtren stack.
	 * i==1 :- up
	 * i==2:- down
	 * i==3 :- left
	 * i==4:- right
	 */
	public void findLegitChildren(){
	
		for (Point h: heads){
				for (move: moves){
					if(canMove(board[h.y][h.x], h, move)){
						 children.add(move(board[h.y][h.x], h, move));
					 } 
				}
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
