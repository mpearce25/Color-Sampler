import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;


public class SpaceBarListener extends JPanel{

	private static Action action;
	public SpaceBarListener(){
		 action = new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
			
				System.out.println("Space pressed");
			}
			
		};
	}
	
	public Action getAction(){
		return action;
		
	}
}
