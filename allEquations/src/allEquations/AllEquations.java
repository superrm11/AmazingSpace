package allEquations;
import java.util.*;
class AllEquations{
	//public static void main(String[] args) {
	public static void quadratic(){
		Scanner keyInput = new Scanner(System.in);
		System.out.print("Enter a: ");
		int a = keyInput.nextInt();
		System.out.println();
//declaring a
		System.out.print("Enter b: ");
		int b = keyInput.nextInt();
		System.out.println();
//declaring b
		System.out.print("Enter c: ");
		int c = keyInput.nextInt();
		System.out.println();
//declaring c
		double part1 = (b * b) - 4 * a * c;
		if (part1 >= 0){
			double y = ((-b + Math.sqrt((b * b) - (4 * a * c))) / (2 * a));
	        double y1 = ((-b - Math.sqrt((b * b) - (4 * a * c))) / (2 * a));
	        System.out.println("y = " + y);
	        System.out.println("y = " + y1);
		} else {
			part1 = part1 * -1;
			System.out.println("y = " + (-b) + "+-" + " i " + "sqrt( " + part1 + " )" + "/" + (2 * a));
	        	
	    }
	}public static void main(String[] arg){
		Scanner keyInput = new Scanner(System.in);
		System.out.print("State the Formula Needed:");
		if (keyInput.hasNext("quadratic")){
			quadratic();
		}
	}
}