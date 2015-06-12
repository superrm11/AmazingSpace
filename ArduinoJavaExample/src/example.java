//----------This is an example of how to use jInput and JSSC with eachother
//----------to get input from a controller and output to an arduino.



import java.util.Scanner;

import net.java.games.input.*;
import jssc.*;
public class example {                     //IMPORTANT!!!!!!!!
	private static float polldata;
	private static int send;
	public static void main(String[] args) throws SerialPortException, InterruptedException {
		//____________________JOYSTICK____________________
		
		Controller[] co = ControllerEnvironment.getDefaultEnvironment().getControllers();
		
		for(int x = 0; x < co.length; x++){
			System.out.println( x + "    " + co[x]);
		}
		//create an array showing the available joysticks
		
		Scanner keyInput = new Scanner(System.in);
		int input = keyInput.nextInt();
		//user is able to choose the controller by selecting the number by the name
		
		Component[] cm = co[input].getComponents();
		for (int x  = 0; x < cm.length; x++){
			System.out.println(x + "    " + cm[x]);
		}
		//prints a list of components attached to the controller
		
		int input1 = keyInput.nextInt();
		//get user input for component
		
		//____________________END JOYSTICK___________________
		//____________________COM PORT_______________________
		
		String[] list = SerialPortList.getPortNames();
		for(int x = 0; x < list.length; x++){
			System.out.println(x + "    " + list[x]);
		}
		// list port names for arduino
		int input2 = keyInput.nextInt();
		//get user input for COM port
		
		SerialPort serial = new SerialPort(list[input2]);
		serial.openPort();
		
		//setParams(baudrate, startbits, stopbits, parity)
		serial.setParams(9600, 8, 1, 0);
		Thread.sleep(2000);
		//Pause program while arduino reboots
		// the arduino auto reboots when the port is re-opened
		keyInput.close();
		while(true){
			co[input].poll();
			polldata = cm[input1].getPollData();
			send = (int) ((polldata + 1) * 90);
			serial.writeInt(send);
			Thread.sleep(10);
		}
		
		
		
	}
}
