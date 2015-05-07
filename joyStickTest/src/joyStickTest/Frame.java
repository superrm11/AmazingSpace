package joyStickTest;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.TextArea;
import java.awt.Button;

import javax.swing.JSpinner;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class Frame extends JFrame {
	private static TextArea textArea;
	private static boolean start;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		if (start == true) {
			try {
				Init();
			} catch (SerialPortException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setTitle("Joystick - Arduino");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textArea = new TextArea();
		textArea.setBounds(21, 10, 380, 160);
		panel.add(textArea);
		
		Button button = new Button("Start");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				start = true;
			}
		});
		button.setBounds(21, 190, 70, 22);
		panel.add(button);
		
		Button button_1 = new Button("Close");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		button_1.setBounds(97, 190, 70, 22);
		panel.add(button_1);
		
		Button button_2 = new Button("Enter Number");
		button_2.setBounds(297, 176, 70, 22);
		panel.add(button_2);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(307, 204, 60, 37);
		panel.add(spinner);
	}
	public static void Init() throws SerialPortException {
		
		float polldata;
		float y;
		int send;
		Controller[] list = ControllerEnvironment.getDefaultEnvironment().getControllers();
		textArea.setText("");
		for (int i = 0; i < list.length; i++) {
			textArea.append("\n" + i + "  " + list[i].getName());
		}
		textArea.append("\n ----------------Select Controller:  ");
		Scanner keyInput = new Scanner(System.in);
		int joy = keyInput.nextInt();
		Component[] comp = list[joy].getComponents();
		for (int i = 0; i < comp.length; i++) {
			textArea.append("\n    " + i + " " + comp[i]);
		}
		textArea.append("\n ----------------Select Axis:");
		int Axis = keyInput.nextInt();
		textArea.append("\n Starting Serial Port...");
		String[] SerialList = SerialPortList.getPortNames();
		for(int i = 0; i < SerialList.length; i++) {
			textArea.append("\n" + i + "   " + SerialList[i]);
		}
		textArea.append("\n ----------------Select COM port:");
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
			textArea.append(String.valueOf(send));
			Delay(25);
		}
	}
	public static void Delay(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
