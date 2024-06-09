package view;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.MStudent;
import view.VLectureTable.EListSetOption;

public class VSugangSincheong extends JPanel {
	// attribute
	private VSelectionPanel vSelectionPanel;
	private VControlPanel vControlPanel_Miridamgi;
	private VLectureTable vMiridamgiTable;
	private VControlPanel vControlPanel_Sincheong;
	private VLectureTable vSincheongTable;
	private MStudent currentStudent;
	
	// component
	private VToolBar vToolBar;
	private JPanel mainPanel;
	// association
	private VMainFrame parent;
	
	private static final long serialVersionUID = 1L;

	// constructors
	public VSugangSincheong(VMainFrame parent) {
		this.parent = parent;
		// layout
		BorderLayout borderlayout = new BorderLayout();
		this.setLayout(borderlayout);
		
		this.mainPanel = new JPanel();
		this.add(mainPanel, BorderLayout.CENTER);

		this.vToolBar = new VToolBar(this);
		this.add(vToolBar, BorderLayout.NORTH);
		
		//attribute를 관리하기 위한 attribute
		LayoutManager layoutManager = new BoxLayout(this.mainPanel, BoxLayout.X_AXIS);
		this.mainPanel.setLayout(layoutManager);
		
		//attribute
		this.vSelectionPanel = new VSelectionPanel();
		this.mainPanel.add(this.vSelectionPanel);
		
		this.vControlPanel_Miridamgi = new VControlPanel(this);
		this.mainPanel.add(this.vControlPanel_Miridamgi);
		
		this.vMiridamgiTable = new VLectureTable();
		this.mainPanel.add(vMiridamgiTable);
		
		this.vControlPanel_Sincheong = new VControlPanel(this);
		this.mainPanel.add(this.vControlPanel_Sincheong);
		
		this.vSincheongTable = new VLectureTable();
		this.mainPanel.add(vSincheongTable);
		
		this.vControlPanel_Miridamgi.associate(this.vSelectionPanel.getLectureTable(), EListSetOption.eBlind,
				this.vMiridamgiTable, EListSetOption.eRemove);
		this.vControlPanel_Sincheong.associate(this.vMiridamgiTable, EListSetOption.eRemain,
				this.vSincheongTable, EListSetOption.eRemove);
	}

	// method
	public void initialize() {
		this.vSelectionPanel.initialize();
		this.vControlPanel_Miridamgi.initialize();
		this.vMiridamgiTable.initialize();
		this.vControlPanel_Sincheong.initialize();
		this.vSincheongTable.initialize();
	}
	public void run() {

	}
	public void login(MStudent student) {
		this.currentStudent = student;
		this.vToolBar.setInfo(this.currentStudent);
	}
	public void logout() {
		parent.logout();
		this.currentStudent = null;
	}
	
	public void resetLogoutTime() {
		this.parent.resetLogoutTime();
		this.vToolBar.resetLogoutTime();
	}
}
