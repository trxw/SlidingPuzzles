import java.io.BufferedReader;
import java.io.FileReader;


public class Solver {

		ArrayList<String> lineInit;
		ArrayList<String> lineGoal;
		Sring BS = "/";
		static String currDir  = System.getProperty("user.dir")+BS;
		Queue<Tray> fringe;
		HashSet<Tray> visited; 
 		
		public Solver( ){
			//
			lineInit = new ArrayList<String>();
			lineGoal = new ArrayList<String>();
			fringe = new Queue<Tray>();
			visited = new HashSet<Tray>();
			
		}
	
		/**
		 * Solves the problem.
		 */		
		public void solveIt(){
			
			
		}
		
		public boolean isValidInput(String[] args){
			
			// update the fringe...(ie. create first Tray....)
			return false;
		}
		
		/**
		 * Reads given file line by line and enters 
		 * @param lineArr
		 * @param fileName
		 */
		
		public fileReader(ArrayList<String> lineArr, String fileName){
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
		// TODO Auto-generated method stub
		Solver s = new Solver();
		if(args.length==2){
			//check if args[0] and args[1] files exit
			if(isValid(args)){
				
			
				s.solveIt();
			}
			//
		}else{
			System.out.println("Invalid input");
			
		}
		
		//
		
		
	}

}
