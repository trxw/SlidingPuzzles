
public class practice {

	
	public static void main(String[] args){
		String s = "abc";
		for(String S: s.split("")){
			System.out.println(S);
		}
		
		System.out.println(!s.substring(0).matches("[^0-9]"));
	}
	
}
