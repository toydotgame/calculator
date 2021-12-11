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
	
	static void evaluate() {
		if(equation.length() > 0) {
			if(!initialised) {
				ScriptEngineManager manager = new ScriptEngineManager();
				engine = manager.getEngineByName("js");
			}
			
			if(equation.replaceAll("\\d", "").contains("Math.pow(,Math.pow(")) {
				finished = true;
				GUI.output.setText("Double power error");
			} else {
				try {
					Object objectOutput = engine.eval(equation);
					answer = String.valueOf(objectOutput);
					finished = true;
					refresh();
				} catch (ScriptException e) {
					finished = true;
					GUI.output.setText("Syntax error");
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
			equationDisplay = equation; // Doesn't show an equation preview otherwise.
		}
		
		if(equationDisplay.length() > 22) {
			equationDisplay = "\u2026" + equationDisplay.substring(equationDisplay.length() - 21);
		}
		
		if(answer.endsWith(".0")) { 
			answer = answer.substring(0, answer.length() - 2);
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
			} else {
				equation = equation.substring(0, equation.length() - 1);
			}
			if(equation.endsWith("Math.pow(")) {
				equation = equation.substring(0, equation.length() - 9);
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
		int length = equation.replaceFirst("\\d+\\D*$", "").length();
		int lastNonDigit = equation.length() == length ? 0 : length;
		
		equation = equation.substring(0, lastNonDigit) + "Math.pow(" + equation.substring(lastNonDigit, equation.length()) + ",";
		refresh();
	}
}
