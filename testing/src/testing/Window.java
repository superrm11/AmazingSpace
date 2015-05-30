package testing;
import net.java.games.input.*;
import jssc.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.TextArea;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JSpinner;
import javax.swing.JButton;

public class Window extends JFrame {
	
	private static void Delay(int x) throws InterruptedException {
		Thread.sleep(x);
	}
	
	
	static TextArea textArea;
	static String nl = "\n";
	static JSpinner spinner;
	static int Spin = 0;
	private JPanel contentPane;
	static int dataOut;

	/**
	 * Launch the application.
	 * @throws SerialPortException 
	 * @throws InterruptedException 
	 */
	//Auto completed by Eclipse===================================================
	
	
	public static void main(String[] args) throws SerialPortException, InterruptedException {
//		test();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		//End Auto completed=======================================================
		//Code for Joysticks and such==============================================
		
		
		Controller[] c = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for (int x = 0; x < c.length; x++) {
			textArea.append(x + "  " + c[x] + nl);
		}
		
		textArea.append("_____Select Controller Number_____" + nl);
		
		while(Spin < 1){
			Delay(50);
		}
		
		
		int SpinNum = (int) spinner.getValue();
		
		Component[] co = c[SpinNum].getComponents();
		
		for (int x = 0; x < co.length; x++) {
			textArea.append(x + "   " + co[x] + nl);
		}
		
		textArea.append("_____Select Axis_____" + nl);
		
		while(Spin < 2){
			Delay(50);
		}
		
		int axis = (int) spinner.getValue();
		
		
		
		//Serial Port Activation
		String[] serial = SerialPortList.getPortNames();
		for (int x = 0; x < serial.length; x++) {
			textArea.append(x + "   " + serial[x] + nl);
		}
		textArea.append("_____ Select Serial Port _____");
		while(Spin < 3){
			Delay(50);
		}
		int serialNum = (int) spinner.getValue();
		SerialPort serialPort = new SerialPort(serial[serialNum]);
		serialPort.openPort();
		serialPort.setParams(9600, 8, 1, 0);
		Delay(2000);
		while(true) {
			c[SpinNum].poll();
			dataOut = (int) ((co[axis].getPollData() + 1) * 90);
			System.out.println(dataOut);
			if (co[8].getPollData() == 1F){
				serialPort.writeInt(200);
			}else{
				serialPort.writeInt(dataOut);
				Delay(5);
			}
		}
		
	}
	public static void test() throws InterruptedException {
		Controller[] c = ControllerEnvironment.getDefaultEnvironment().getControllers();
		Component[] co = c[2].getComponents();
		while(true){
			c[2].poll();
			System.out.println(co[8].getPollData());
			Delay(5);
		}
	}

	/**
	 * Create the frame.
	 */
	public Window() {
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
		textArea.setBounds(10, 10, 380, 160);
		panel.add(textArea);
		
		Button button_1 = new Button("Exit");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		button_1.setBounds(10, 188, 70, 22);
		panel.add(button_1);
		
		spinner = new JSpinner();
		spinner.setBounds(201, 176, 70, 39);
		panel.add(spinner);
		
		Button button_2 = new Button("Submit #");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Spin++;
			}
		});
		button_2.setBounds(277, 188, 70, 22);
		panel.add(button_2);
	}
}
