import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* 
 * TODO: Answer functionality: Change equation to read as "Ans" if adding more operators after evaluation; "Ans" button instead of invert button; Prevent inputting "Ans" twice (to avoid "AnsAns" in the calculator - like if "Ans" = "12", then "AnsAns" would be "1212"); maybe use a lastEquation thing?
 */

public class Main {
	static ExecutorService executor = Executors.newFixedThreadPool(1);
	static Runnable copyThread = new Copier();
		
	public static void main(String[] args) {
		if(args.length > 0 && args[0].equals("--debug")) {
			Calculator.debug = true;
		}
		GUI.main();
	}
}
