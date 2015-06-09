package testing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.TextArea;
import java.awt.Choice;

import javax.swing.JLabel;

import net.java.games.input.*;

import java.awt.Color;

public class Window2 extends JFrame {
	private static Choice choice;
	private static Choice choice_1;
	private static Choice choice_2;
	private static Controller[] co = ControllerEnvironment.getDefaultEnvironment().getControllers();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window2 frame = new Window2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		for(int x = 0; x < co.length; x++) {
			choice.add(co[x].getName());
		}
		//TODO    Create for loops to get the names of the axis and com ports (for the choices)
		
	}

	/**
	 * Create the frame.
	 */
	public Window2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		choice = new Choice();
		choice.setBackground(Color.WHITE);
		choice.setBounds(10, 27, 153, 20);
		panel.add(choice);
		
		choice_1 = new Choice();
		choice_1.setBounds(195, 27, 145, 20);
		panel.add(choice_1);
		choice_2 = new Choice();
		choice_2.setBounds(346, 27, 68, 20);
		panel.add(choice_2);
		
		JLabel lblController = new JLabel("Controller");
		lblController.setBounds(10, 11, 76, 14);
		panel.add(lblController);
		
		JLabel lblAxis = new JLabel("Axis");
		lblAxis.setBounds(195, 7, 46, 14);
		panel.add(lblAxis);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(346, 11, 46, 14);
		panel.add(lblPort);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(22, 82, 380, 160);
		panel.add(textArea);
	}
}
