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
	static TextArea textArea;
	static String nl = "\n";
	static JSpinner spinner;
	static int Spin = 0;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	//Auto completed by Eclipse===================================================
	
	
	public static void main(String[] args) {
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
		while(Spin != 1){
			//do nothing
		}
		//TODO make the input from the spinner
		int SpinNum = (int) spinner.getValue();
		Component[] co = c[SpinNum].getComponents();
		for (int x = 0; x < co.length; x++) {
			textArea.append(x + "   " + co[x] + nl);
		}
		textArea.append("_____Select Axis_____" + nl);
		
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
		
		Button button = new Button("Start");
		button.addMouseListener(new MouseAdapter() {
		});
		button.setBounds(10, 188, 70, 22);
		panel.add(button);
		
		Button button_1 = new Button("Exit");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		button_1.setBounds(86, 188, 70, 22);
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
