import jssc.*;
public class Arduino {
	
	
	public static void delay(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void digitalWrite(int Val, int Pin){
		
	}
	
}
