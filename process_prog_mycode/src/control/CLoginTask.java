package control;

import java.util.Timer;
import java.util.TimerTask;

import constants.Constant.LoginTask;
import view.VMainFrame;

public class CLoginTask extends TimerTask{
	// association
	private VMainFrame vMainFrame;
	//attribtue
	
	//component
	private Timer timer;
	public CLoginTask(VMainFrame vMainFrame) {
		this.vMainFrame = vMainFrame;
    	this.timer = new Timer();
    	this.timer.schedule(this, LoginTask.DELAY);
	}
	@Override
	public void run() {
		this.vMainFrame.logout();
	}
	public void requestCancel() {
		this.timer.cancel();
	}
	
}
