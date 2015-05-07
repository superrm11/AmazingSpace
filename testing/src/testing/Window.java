package testing;
import net.java.games.input.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.TextArea;
import java.awt.Button;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;
public class Window extends JFrame {
	private static TextArea textArea;
	private static JSpinner spinner; 
	private static int Submit;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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
		JoyInit();
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
		button.setBounds(10, 194, 70, 22);
		panel.add(button);
		
		Button button_1 = new Button("Close");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		button_1.setBounds(86, 194, 70, 22);
		panel.add(button_1);
		
		Button button_2 = new Button("Submit #");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Submit++;
			}
		});
		button_2.setBounds(294, 176, 84, 32);
		panel.add(button_2);
		
		spinner = new JSpinner();
		spinner.setBounds(304, 214, 69, 38);
		panel.add(spinner);
	}
	
	
	
	
	public static void JoyInit() {
		Controller[] con = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for (int x = 0; x < con.length; x++) {
			textArea.append("\n" + x + "  " + con[x]);
		}
		Scanner keyInput =  new Scanner(System.in);
		textArea.append("-----------------Enter Controller-----------------");
		while (Submit != 1) {/*do nothing*/}
		int x = (int) spinner.getValue();
		Component[] cp = con[x].getComponents();
		for (x = 0; x < cp.length; x++) {
			textArea.append("\n      " + x + "      " + cp[x]);
		}
	}
}
