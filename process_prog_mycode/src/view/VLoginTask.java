package view;

import java.util.Timer;
import java.util.TimerTask;

import constants.Constant.LoginTask;

public class VLoginTask extends TimerTask{
	// association
	private VMainFrame vMainFrame;
	//attribtue
	
	//component
	private Timer timer;
	public VLoginTask(VMainFrame vMainFrame) {
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
