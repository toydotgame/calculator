import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* 
 * TODO: Interrupt CopyThread.run() if Input.inputButton() is run before CopyThread.run() is not done running. (Should also fix the issue where the copy error text shows even when there's an answer there and it can be copied (thus the "Copied!" message _does_ show, but is delayed))
 * TODO: Have a lastEquation variable which gets saved to if `equation` is cleared after also being evaluated (`finished`), and will overwrite whatever is in the equation field when up arrow pressed.
 * TODO: Allow equation preview to scroll so that the end of the equation is always in view.
 */

public class Main {
	static ExecutorService executor = Executors.newFixedThreadPool(1);
	static Runnable copyThread = new Copier();
		
	public static void main(String[] args) {
		GUI.main();
	}
}
