import java.awt.Point;


public class Block {
	Point size;
	public Block(int yLength, int xLength){
		size= new Point(xLength,yLength);
	}
	public Point size(){
		return size;
	}
}
