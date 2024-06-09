package view;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JFrame;

import constants.Constant.MainFrame;
import model.MStudent;

public class VMainFrame extends JFrame {
	private static final long serialVersionUID = MainFrame.VERSION_NUM;

	// components
	private VSugangSincheong vSugangSincheong;
	private VLoginDialog vLoginPanel;
	@SuppressWarnings("unused")
	private VLoginTask cLoginTask;

	public VMainFrame() throws HeadlessException {
		super(MainFrame.TITLE);

		// attributes
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		double width = screenSize.width * MainFrame.Monitor_RATIO;
		double height = screenSize.height * MainFrame.Monitor_RATIO;
		this.setSize((int) width, (int) height);
		
		double x = (screenSize.width - width) * MainFrame.X;
		double y = (screenSize.height - height) * MainFrame.Y;
		this.setLocation((int) x, (int) y);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// component
		this.vLoginPanel = new VLoginDialog(this);
		this.vLoginPanel.setVisible(true);
	}

	// method
	public void initialize() {
		
	}
	
	public void login(MStudent student) {
		this.vSugangSincheong = new VSugangSincheong(this);
		this.vSugangSincheong.initialize();
		this.vSugangSincheong.login(student);
		this.add(vSugangSincheong);
		this.paintAll(getGraphics());
		if(this.cLoginTask != null) {
			this.cLoginTask.requestCancel();
		}
		this.cLoginTask = new VLoginTask(this);
	}

	public void logout() {
		this.remove(this.vSugangSincheong);
		this.getContentPane().setBackground(this.getBackground());
		this.repaint();
		this.vLoginPanel.setVisible(true);
	}
	
	public void resetLogoutTime() {
		if(this.cLoginTask != null) {
			this.cLoginTask.requestCancel();
		}
		this.cLoginTask = new VLoginTask(this);
	}
}
