import net.java.games.input.*;
import java.util.Scanner;
public class Joystick {
	public static void Joystick() {
		Controller[] Joystick = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for (int i = 0; i < Joystick.length; i++) {
			System.out.println(i + " " + Joystick[i]);
		}
//		System.out.println("     Enter Joystick:");
//		Scanner keyInput = new Scanner(System.in);
		Component[] comp = Joystick[2].getComponents();
		System.out.println("Components:  ");
		for (int i = 0; i < comp.length; i++) {
			System.out.println(i + " " + comp[i]);
		}
	}
}
