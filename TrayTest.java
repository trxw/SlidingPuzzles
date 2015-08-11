import java.awt.Point;
import java.util.ArrayList;

import junit.framework.TestCase;


public class TrayTest extends TestCase {
	public void testConstuctor1(){
		ArrayList<String> arr= new ArrayList<String>();
		arr.add("3 2");
		arr.add("0 0 0 0");
		arr.add("1 1 1 1");
		Tray T= new Tray(arr);
		Point P= new Point(0,0);
		Point P1= new Point(1,1);
		// check the length of the tray
		assertTrue(T.xLength==2);
		assertTrue(T.yLength==3);
		// check the heads
		assertTrue(T.heads.contains(P1));
		assertTrue(T.heads.contains(P));
		// check the board 
		assertTrue(T.board[0][0]!=null);
		assertTrue(T.board[1][1]!=null);
		assertTrue(T.board[1][0]==null);
		assertTrue(T.board[0][1]==null);
		// check the board and the block
		assertTrue(T.board[0][0].size().equals(P1));
		assertTrue(T.board[1][1].size().equals(P1));
		
	}
	
	public void testToString(){
		ArrayList<String> arr= new ArrayList<String>();
		arr.add("3 2");
		arr.add("0 0 0 0");
		arr.add("1 1 1 1");
		arr.add("2 1 2 1");
		Tray T= new Tray(arr);
		String S= "0 0 0 0\n1 1 1 1\n2 1 2 1";
		
		assertTrue(S.equals(T.toString()));
		
	}
	
	
	
	public void testCanMove(){
		ArrayList<String> arr= new ArrayList<String>();
		arr.add("3 2");
		arr.add("0 0 0 0");
		arr.add("1 1 1 1");
		Tray T= new Tray(arr);
		Point[] moves = { new Point(0, -1), new Point(0, +1),
				new Point(-1, 0), new Point(+1, 0) };
		Block B= T.board[0][0];
		Block B1= T.board[1][1];
		Point oldP= T.heads.get(0);
		Point oldP1= T.heads.get(1);
		 // checks we can't go out of the bounds of the board
		assertFalse(T.canMove(B,oldP, moves[0]));
		assertFalse(T.canMove(B,oldP, moves[2]));
		assertFalse(T.canMove(B1,oldP1, moves[3]));
		
		 // checks we can go to an empty space on the board
		assertTrue(T.canMove(B,oldP, moves[3]));
		assertTrue(T.canMove(B,oldP, moves[1]));
		assertTrue(T.canMove(B1,oldP1, moves[0]));
		assertTrue(T.canMove(B1,oldP1, moves[1]));
		assertTrue(T.canMove(B1,oldP1, moves[2]));
		
		
		arr.add("0 1 0 1");
		Tray T1= new Tray(arr);
		Block BB= T1.board[0][0];
		Block BB1= T1.board[1][1];
		Block BB2= T1.board[0][1];
		Point oldPP= T1.heads.get(0);
		Point oldPP1= T1.heads.get(1);
		Point oldPP2= T1.heads.get(2);
		
		// check that we can't move on-top of a block object
		assertFalse(T1.canMove(BB,oldPP, moves[3]));
		assertFalse(T1.canMove(BB1,oldPP1, moves[0]));
		assertFalse(T1.canMove(BB2,oldPP2, moves[1]));
		assertFalse(T1.canMove(BB2,oldPP2, moves[2]));	
	}
	public void testmove(){
		
		ArrayList<String> arr= new ArrayList<String>();
		arr.add("3 2");
		arr.add("0 0 0 0");
		arr.add("1 1 1 1");
		Tray T= new Tray(arr);
		Point[] moves = { new Point(0, -1), new Point(0, +1),
				new Point(-1, 0), new Point(+1, 0) };
		Block B= T.board[0][0];
		 Point oldP= T.heads.get(0);
		System.out.println(T);
		Tray NewT=  T.move(B, oldP, moves[3]);
		
		//System.out.println(NewT);
		// check the move was executed on the newT
		assertTrue(NewT.board[0][0]==null);
		assertTrue(NewT.board[0][1]!=null);
		// check that newT's parent is T
//		assertTrue(NewT.parentTray.equals(T));
		
		// check that newT is the child of T
		
		for(Tray C: T.children()){
			System.out.println(C);
		}
		//assertTrue(T.children().peek().equals(NewT));
		
		// check moveFromParent
		String S= "0 0 0 1";
		assertTrue(S.equals(NewT.moveFromParent));
		
		// check that the head in NewT is reset right
		Point P= new Point (1,0);
		assertTrue(NewT.heads.get(0).equals(P));
		
		
		
	}
	
	
	public void testchildren(){
		
	}
}



















