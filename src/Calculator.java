import java.util.Arrays;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Calculator {
	public static String equation = "";
	public static String equationDisplay = "";
	public static String answer = "";
	public static boolean finished = false;
	public static boolean initialised = false;
	public static ScriptEngine engine;
	static String[] operators = {
		"*", "-", "/", "Math.pow(", ",", "Math.sqrt(", "(3.14159265358980)"
	};
	public static boolean debug = false;
	
	static void evaluate() {
		if(equation.length() > 0) {
			if(!initialised) {
				ScriptEngineManager manager = new ScriptEngineManager();
				engine = manager.getEngineByName("js");
			}
			
			if(equation.replaceAll("\\d", "").contains("Math.pow(,Math.pow(")) {
				finished = true;
				GUI.output.setText("Double power error");
				if(debug) {
					System.out.println(equation + " = Double power error");
				}
			} else {
				try {
					Object output = engine.eval(equation);
					answer = String.valueOf(output);
					finished = true;
					refresh();
					if(debug) {
						System.out.println(equation + " = " + answer);
					}
				} catch (ScriptException e) {
					finished = true;
					GUI.output.setText("Syntax error");
					if(debug) {
						System.out.println(equation + " = Syntax error");
					}
				}
			}
		}
	}
	
	static void refresh() {
		if(Arrays.stream(operators).anyMatch(equation::contains)) {
			equationDisplay = equation.replace("*", "\u00D7");
			equationDisplay = equationDisplay.replace("-", "\u2212");
			equationDisplay = equationDisplay.replace("/", "\u00F7");
			equationDisplay = equationDisplay.replace("Math.pow(", "");
			equationDisplay = equationDisplay.replace(",", "^(");
			equationDisplay = equationDisplay.replace("Math.sqrt(", "\u221A(");
			equationDisplay = equationDisplay.replace("(3.14159265358980)", "\u03C0");
		} else {
			equationDisplay = equation; // Doesn't need any changes (the equation is most likely only numbers).
		}
		
		if(equationDisplay.length() > 22) {
			equationDisplay = "\u2026" + equationDisplay.substring(equationDisplay.length() - 21); // Effectively renders only the end of the equationDisplay String.
		}
		
		if(answer.endsWith(".0")) { 
			answer = answer.substring(0, answer.length() - 2); // Sometimes JavaScript returns integers, but still feels the need to add ".0" on to the end.
		}
				
		GUI.fullEquationPreview.setText(equationDisplay);
		GUI.output.setText(answer);
	}
	
	static void delete() {
		if(equation.length() > 0) {
			if(equation.endsWith("(3.14159265358980)")) {
				equation = equation.substring(0, equation.length() - 18);
			} else if(equation.endsWith("Math.sqrt(")) {
				equation = equation.substring(0, equation.length() - 10);
			} else if(equation.endsWith("Math.pow(")) {
				equation = equation.substring(0, equation.length() - 9);
			} else {
				equation = equation.substring(0, equation.length() - 1);
			}
			refresh();
		}
	}
	
	static void allClear() {
		equation = "";
		answer = "";
		refresh();
	}
	
	static void addPower() {
		int length = equation.replaceFirst("\\d+\\D*.\\($", "").length();
		int lastNonDigit = equation.length() == length ? 0 : length;
		
		equation = equation.substring(0, lastNonDigit) + "Math.pow(" + equation.substring(lastNonDigit, equation.length()) + ",";
		refresh();
	}
}
