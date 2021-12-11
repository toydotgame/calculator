import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Copier implements Runnable {
	public static boolean isRunning = false;
	
	public void run() {
		isRunning = true;
		if(Calculator.answer != null && Calculator.finished) {
			StringSelection stringSelection = new StringSelection(Calculator.answer);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, stringSelection);
			
			GUI.notificiation.setFont(new Font("Sans", Font.ITALIC, 40));
			GUI.notificiation.setText("Copied!");
			GUI.notificiation.setVisible(true);
			try {
				Thread.sleep(1500);
			} catch(InterruptedException e) {}
			GUI.notificiation.setVisible(false);
		} else {
			GUI.notificiation.setFont(new Font("Sans", Font.ITALIC, 25));
			GUI.notificiation.setText("Complete an equation first!");
			GUI.notificiation.setVisible(true);
			try {
				Thread.sleep(1500);
			} catch(InterruptedException e) {}
			GUI.notificiation.setVisible(false);
		}
		isRunning = false;
	}
}