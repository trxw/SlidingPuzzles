import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Stack;


public class Solver {

		ArrayList<String> lineInit;
		ArrayList<String> lineGoal;
		static String BS = "/";
		static String currDir  = System.getProperty("user.dir")+BS;
		Stack<Tray> fringe;
		HashSet<String> visited;
		Tray initTray; // update in isValidInput
 		
		public Solver(){
			//
			lineInit = new ArrayList<String>();
			lineGoal = new ArrayList<String>();
			fringe = new Stack<Tray>();
			visited = new HashSet<String>();
		}
	
		/**
		 * Solves the problem.
		 */		
		public void solveIt(){
			fringe.push(initTray);
			 while (!fringe.isEmpty()){
				 Tray X= fringe.pop();
				 if (isGoal(X)){
				    moves(X);
				 }else{
					 for(Tray XChild: X.children){
						 if (!fringe.contains(XChild) && !visited.contains(XChild.toString())){
							 fringe.add(XChild);
						 }	
					 }
					 visited.add(X.toString());
				 }
			 }
			 //if no goal is reached by the end do noting and just exist
		}
		
		/**
		 * returns true if the argument is equal to goal
		 * 
		 * @param currTray
		 *          the current Tray under examination
		 * @return
		 *      true if tray is goal else false
		 */
		
		public boolean isGoal(Tray currTray){
			// lineGoal
			int count=0;
			int neededToBeGoal= lineGoal.size();
			// for the Goal... 
			int xtop;
			int ytop;
			int xbottom;
			int ybottom;
			for (String line : lineGoal) {
				xtop = Integer.parseInt(line.split("\\s+")[1]);
				ytop = Integer.parseInt(line.split("\\s+")[0]);
				Point P= new Point(xtop,ytop);
				// if we have the head in the current Tray
				if (currTray.heads.contains(P)){
					xbottom = Integer.parseInt(line.split("\\s+")[3]);
					ybottom = Integer.parseInt(line.split("\\s+")[2]);
					Point P1= new Point(xbottom,ybottom);
					
					int x= xtop+currTray.board[ytop][xtop].size.x-1;
					int y= ytop+currTray.board[ytop][xtop].size.y-1;
					Point PB= new Point(x,y);
					// if the Bottom of the Goal matches the Bottom of the Given Block
					if (P1.equals(PB)){
						count++;
					}
				}
			}
			if (count== neededToBeGoal){
				return true;
			}
			return false;
		}
		
		/**
		 * prints out all the moves from the start to Goal (X)
		 * @param X
		 *          the Goal Tray
		 */
		public void moves(Tray X){
			Stack<String> S= new Stack<String>();
			// avoid visiting the first tray since it has no moves
			while(X.parentTray!=null){ 
				S.push(X.moveFromParent);
				X=X.parentTray;
			}
			while (!S.isEmpty()){
				System.out.println(S.pop());
			}
		}
		
		/**
		 * 
		 * @param args
		 * @return
		 */
		public boolean isValidInput(String[] args){
			// check validity of args
			
			/** possible errors for init file
			 * 
			 * - no size of board
			 * - no blocks given
			 * - inputs are 4 integers separated by space given as strings (unless first line)
			 * - first line is size (two non negative integer inputs)
			 * - no block is outside the boards of the tray
			 * - no blocks are overlapping 
			 * - each number is positive 
			 * - each number is an integer
			 * - 
			 */
			
			
			// update the fringe...(ie. create first Tray....)
			return false;
		}
		
		/**
		 * Reads given file line by line and enters 
		 * @param lineArr
		 * @param fileName
		 * @throws IOException 
		 * @throws FileNotFoundException 
		 */
		
		public void fileReader(ArrayList<String> lineArr, String fileName) throws FileNotFoundException, IOException{
			String file = currDir + fileName;
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			    String linePotato;
			    while ((linePotato = br.readLine()) != null) {
			       // process the line.
			    	lineArr.add(linePotato);
			    }  
			} 
		}
		
	public static void main(String[] args) {
		Solver s = new Solver();
		if(args.length==2){
			//check if args[0] and args[1] files exit
			if(s.isValidInput(args)){
				
				s.solveIt();
			}else {
				System.out.println("Invalid init and/or goal file.");
			}
			//
		}else{
			System.out.println("Invalid init and/or goal file.");
			
		}
		
		//
		
		
	}

}
