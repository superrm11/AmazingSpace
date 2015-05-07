package joyStickTest;
import jssc.*;
import net.java.games.input.*;

import java.util.Scanner;
public class JoyInput {
	public static void Delay(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	@SuppressWarnings("resource")
	public static void Init() throws SerialPortException {
		
		float polldata;
		float y;
		int send;
		Controller[] list = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for (int i = 0; i < list.length; i++) {
			System.out.println(i + "  " + list[i].getName());
		}
		System.out.print("----------------Select Controller:  ");
		Scanner keyInput = new Scanner(System.in);
		int joy = keyInput.nextInt();
		Component[] comp = list[joy].getComponents();
		for (int i = 0; i < comp.length; i++) {
			System.out.println("    " + i + " " + comp[i]);
		}
		System.out.print("----------------Select Axis:");
		int Axis = keyInput.nextInt();
		System.out.println("Starting Serial Port...");
		String[] SerialList = SerialPortList.getPortNames();
		for(int i = 0; i < SerialList.length; i++) {
			System.out.println(i + "   " + SerialList[i]);
		}
		System.out.print("----------------Select COM port:");
		int COM = keyInput.nextInt();
		SerialPort serial = new SerialPort(SerialList[COM]);
			serial.openPort();
			serial.setParams(9600, 8, 1, 0);
			Delay(2000);
		while(true) {
			list[joy].poll();
			polldata = comp[Axis].getPollData();
			y = (float) ((polldata * 90) + 90);
			send = (int) y;
			serial.writeInt(send);
			System.out.println(send);
			Delay(25);
		}
	}
}