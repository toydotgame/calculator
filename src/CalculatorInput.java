import java.util.concurrent.Executors;

public class CalculatorInput {
	static String[] functions = {
		"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
		"(", ")", "+", "-", "", "*", "/", "Math.pow(",
		".", "Math.sqrt(", "(3.14159265358980)",
		"1/", "", "", ""
	};
	
	static void input(int buttonID) {
		if(Copier.isRunning) {
			Main.executor.shutdownNow();
			GUI.notificiation.setVisible(false);
			Main.executor = Executors.newFixedThreadPool(1); // New ThreadPool needs to be created because shutdownNow() also deletes the ThreadPool.
		}
		
		if(Calculator.finished) {
			switch(buttonID) {
				case 12: case 13: case 15: case 16:
					Calculator.finished = false;
					Calculator.answer = "";
					Calculator.equation += functions[buttonID];
					Calculator.refresh();
					break;
				case 14:
					// Don't bother calculating the same equation again.
					break;
				case 17:
					Calculator.finished = false;
					Calculator.answer = "";
					Calculator.addPower();
					break;
				case 22:
					Main.executor.execute(Main.copyThread);
					break;
				case 23:
					Calculator.finished = false;
					Calculator.answer = "";
					Calculator.delete();
					break;
				default:
					Calculator.allClear();
					Calculator.finished = false;
					Calculator.equation += functions[buttonID];
					Calculator.refresh();
					break;
			}
		} else {
			switch(buttonID) {
				case 14:
					Calculator.evaluate();
					break;
				case 17:
					Calculator.addPower();
					break;
				case 22:
					Main.executor.execute(Main.copyThread);
					break;
				case 23:
					Calculator.delete();
					break;
				case 24:
					Calculator.allClear();
					break;
				default:
					Calculator.equation += functions[buttonID];
					Calculator.refresh();
					break;
			}
		}
	}
}
