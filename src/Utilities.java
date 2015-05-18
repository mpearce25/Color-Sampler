import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;


public class Utilities {

	////Clipboard Tools
	public static void setClipboard(String text){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();

        clipboard.setContents(new StringSelection(text), null);
	}
}
