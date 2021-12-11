import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyPress extends KeyAdapter {
	ActionEvent a;
	long time;
	
	public void keyPressed(KeyEvent e) {
		char c = e.getKeyChar();

		switch(c) {
			case '0':
				CalculatorInput.input(0);
				break;
			case '1':
				CalculatorInput.input(1);
				break;
			case '2':
				CalculatorInput.input(2);
				break;
			case '3':
				CalculatorInput.input(3);
				break;
			case '4':
				CalculatorInput.input(4);
				break;
			case '5':
				CalculatorInput.input(5);
				break;
			case '6':
				CalculatorInput.input(6);
				break;
			case '7':
				CalculatorInput.input(7);
				break;
			case '8':
				CalculatorInput.input(8);
				break;
			case '9':
				CalculatorInput.input(9);
				break;
			case '\n':
				Calculator.evaluate();
				break;
			case '=': // Button ID 14
				Calculator.evaluate();
				break;
			case '*':
				CalculatorInput.input(15);
				break;
			case '/':
				CalculatorInput.input(16);
				break;
			case '^':
				CalculatorInput.input(17);
				break;
			case '(':
				CalculatorInput.input(10);
				break;
			case ')':
				CalculatorInput.input(11);
				break;
			case '+':
				CalculatorInput.input(12);
				break;
			case '-':
				CalculatorInput.input(13);
				break;
			case '.':
				CalculatorInput.input(18);
				break;
			// 19: Sqrt
			// 20: Pi
			// 21: 1/x
			case '\u0003': // Ctrl+C
				CalculatorInput.input(22);
				break;
			case '\u007F': // Delete
				CalculatorInput.input(23);
				break;
			case '\u0008': // Backspace
				CalculatorInput.input(23);
				break;
			case '\u001B': // Esc
				CalculatorInput.input(24);
				break;
		}
	}
}
