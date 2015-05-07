import jssc.*;

import java.util.Scanner;
public class RXTX {

	public static void Setup() {
		try {
		String[] list = SerialPortList.getPortNames();
		for(int i = 0; i < list.length; i++) {
			System.out.println(i + " " + list[i]);
		}
		System.out.print("		Select Port #: ");
		Scanner keyInput = new Scanner(System.in);
		int Port = keyInput.nextInt();
		SerialPort serialport = new SerialPort(list[Port]);
		
			serialport.openPort();
			serialport.setParams(9600, 8, 1, 0);
		
		
		System.out.println("");
		System.out.println("");
		System.out.println("Serial Build Successful.");
		keyInput.close();
		Arduino.delay(2000);
		while(true) {
			
		}
		
		
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
	}
}