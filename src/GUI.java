import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

public class GUI implements ActionListener {
	// Colours:
	public static String buttonColor = "#000000";
	public static String buttonTextColor = "#ffffff";	
	public static String specialButtonColor = "#ff6400";
	public static String specialButtonTextColor = "#000000";	
	public static String backgroundColor = "#1c1c1c";
	public static String outputColor = "#cccccc";
	public static String outputTextColor = "#000000";
	// JObjects:
	public static JFrame frame;
	public static JPanel panel;
	public static JLabel output;
	public static JLabel fullEquationPreview;
	public static JLabel screenBackground;
	public static JLabel notificiation;
	public static JButton button1;
	public static JButton button2;
	public static JButton button3;
	public static JButton button4;
	public static JButton button5;
	public static JButton button6;
	public static JButton button7;
	public static JButton button8;
	public static JButton button9;
	public static JButton button0;
	public static JButton buttonDivide;
	public static JButton buttonMultiply;
	public static JButton buttonSubtract;
	public static JButton buttonAdd;
	public static JButton buttonEvaluate;
	public static JButton buttonSqrt;
	public static JButton buttonPower;
	public static JButton buttonInverse;
	public static JButton buttonPi;
	public static JButton buttonOpeningBracket;
	public static JButton buttonClosingBracket;
	public static JButton buttonDelete;
	public static JButton buttonAllClear;
	public static JButton buttonCopy;
	public static JButton buttonDecimal;
	static String[] buttonNames = {
		"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
		"(", ")", "+", "\u2212", /* - */ "=", "\u00D7", /* * */ "\u00F7", /* / */ "\uD835\uDCCD\u02B8", /* ^ */
		"\u00B7", /* . */ "\u221A", /* sqrt */ "\u03C0", /* pi */
		"\u215F\uD835\uDCCD", /* 1/x */ "\uD83D\uDCCB", /* Copy */ "DEL", "AC"
	};
	
	public static void main() {
		new GUI();
	}
	
	public GUI() {
		frame = new JFrame("Calculator");
		frame.setSize(480, 660);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // JFrame.EXIT_ON_CLOSE waits for threads to finish before closing, WindowConstants.EXIT_ON_CLOSE does not. (It just kills them instantly)
		frame.setResizable(false);
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {}
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.decode(backgroundColor));
		panel.setFocusable(true); // Due to no buttons having focus, the Panel steals focus and thus can receive keystrokes.
		panel.addKeyListener(new KeyPress());
		frame.add(panel);
		
		notificiation = new JLabel();
		notificiation.setHorizontalAlignment(SwingConstants.CENTER);
		notificiation.setVerticalAlignment(SwingConstants.CENTER);
		notificiation.setForeground(Color.decode(outputTextColor));
		notificiation.setBounds(30, 30, 420, 120);
		notificiation.setVisible(false);
		panel.add(notificiation);
		
		output = new JLabel();
		output.setHorizontalAlignment(SwingConstants.RIGHT);
		output.setForeground(Color.decode(outputTextColor));
		output.setFont(new Font("Monospaced", Font.PLAIN, 30));
		output.setBounds(40, 90, 400, 60);
		panel.add(output);
		
		fullEquationPreview = new JLabel();
		fullEquationPreview.setForeground(Color.decode(outputTextColor));
		fullEquationPreview.setFont(new Font("Monospaced", Font.PLAIN, 30));
		fullEquationPreview.setBounds(40, 30, 400, 60);
		panel.add(fullEquationPreview);
		
		screenBackground = new JLabel();
		LineBorder border = new LineBorder(Color.decode(outputColor), 60, true);
		screenBackground.setBorder(border);
		screenBackground.setBounds(30, 30, 420, 120);
		panel.add(screenBackground);
		
		// Special Buttons: (Need custom font scaling or otherwise)
		buttonInverse = new JButton(buttonNames[21]);
		buttonInverse.setBounds(30, 180, 60, 60);
		buttonInverse.setFont(new Font("Sans", Font.PLAIN, 24));
		buttonInverse.setBorder(null);
		buttonInverse.setOpaque(true);
		buttonInverse.setForeground(Color.decode(buttonTextColor));
		buttonInverse.setBackground(Color.decode(buttonColor));
		buttonInverse.addActionListener(this);
		buttonInverse.setFocusable(false);
		panel.add(buttonInverse);
		
		buttonCopy = new JButton(buttonNames[22]);
		buttonCopy.setBounds(390, 180, 60, 60);
		buttonCopy.setFont(new Font("Sans", Font.PLAIN, 30));
		buttonCopy.setBorder(null);
		buttonCopy.setOpaque(true);
		buttonCopy.setForeground(Color.decode(specialButtonTextColor));
		buttonCopy.setBackground(Color.decode(specialButtonColor));
		buttonCopy.addActionListener(this);
		buttonCopy.setFocusable(false);
		panel.add(buttonCopy);
		
		buttonDelete = new JButton(buttonNames[23]);
		buttonDelete.setBounds(300, 270, 60, 60);
		buttonDelete.setFont(new Font("Sans", Font.PLAIN, 24));
		buttonDelete.setBorder(null);
		buttonDelete.setOpaque(true);
		buttonDelete.setForeground(Color.decode(specialButtonTextColor));
		buttonDelete.setBackground(Color.decode(specialButtonColor));
		buttonDelete.addActionListener(this);
		buttonDelete.setFocusable(false);
		panel.add(buttonDelete);
		
		buttonAllClear = new JButton(buttonNames[24]);
		buttonAllClear.setBounds(390, 270, 60, 60);
		buttonAllClear.setFont(new Font("Sans", Font.PLAIN, 24));
		buttonAllClear.setBorder(null);
		buttonAllClear.setOpaque(true);
		buttonAllClear.setForeground(Color.decode(specialButtonTextColor));
		buttonAllClear.setBackground(Color.decode(specialButtonColor));
		buttonAllClear.addActionListener(this);
		buttonAllClear.setFocusable(false);
		panel.add(buttonAllClear);
		
		// Normal Buttons:
		createButton(buttonOpeningBracket, buttonNames[10], 120, 180);
		createButton(buttonClosingBracket, buttonNames[11], 210, 180);
		createButton(buttonPi, buttonNames[20], 300, 180);
		createButton(button7, buttonNames[7], 30, 270);
		createButton(button8, buttonNames[8], 120, 270);
		createButton(button9, buttonNames[9], 210, 270);
		createButton(button4, buttonNames[4], 30, 360);
		createButton(button5, buttonNames[5], 120, 360);
		createButton(button6, buttonNames[6], 210, 360);
		createButton(buttonMultiply, buttonNames[15], 300, 360);
		createButton(buttonDivide, buttonNames[16], 390, 360);
		createButton(button1, buttonNames[1], 30, 450);
		createButton(button2, buttonNames[2], 120, 450);
		createButton(button3, buttonNames[3], 210, 450);
		createButton(buttonAdd, buttonNames[12], 300, 450);
		createButton(buttonSubtract, buttonNames[13], 390, 450);
		createButton(button0, buttonNames[0], 30, 540);
		createButton(buttonDecimal, buttonNames[18], 120, 540);
		createButton(buttonPower, buttonNames[17], 210, 540);
		createButton(buttonSqrt, buttonNames[19], 300, 540);
		createButton(buttonEvaluate, buttonNames[14], 390, 540);
		
		frame.setLocationRelativeTo(null); // Positions the window at the centre of the screen.
		frame.setVisible(true);
	}
	
	public void createButton(JButton button, String text, int x, int y) {
		button = new JButton(text);
		button.setBounds(x, y, 60, 60);
		button.setFont(new Font("Sans", Font.PLAIN, 33));
		button.setBorder(null);
		button.setOpaque(true);
		button.setForeground(Color.decode(buttonTextColor));
		button.setBackground(Color.decode(buttonColor));
		button.addActionListener(this);
		button.setFocusable(false);
		panel.add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int buttonID = Arrays.asList(buttonNames).indexOf(e.getActionCommand());
		CalculatorInput.input(buttonID);
	}
}
