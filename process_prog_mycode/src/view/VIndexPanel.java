package view;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import constants.Constant.IndexPanel;

public class VIndexPanel extends JPanel{
	private static final long serialVersionUID = IndexPanel.VERSION_NUM;
	// components
	private VIndexTable vCampus;
	private VIndexTable vCollege;
	private VIndexTable vDepartment;
	
	// association

	// constructors
	public VIndexPanel() {
		// components
		LayoutManager layoutManager = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(layoutManager);
		
		this.vCampus = new VIndexTable();
		this.add(this.vCampus);
		
		this.vCollege = new VIndexTable();
		this.add(this.vCollege);
		
		this.vDepartment = new VIndexTable();
		this.add(this.vDepartment);
		
		
		// associations
		this.vCampus.setNext(vCollege);
		this.vCollege.setNext(vDepartment);
	}

	public void initialize() {
		this.vCampus.initialize();
		this.vCollege.initialize();
		this.vDepartment.initialize();
		
		this.vCampus.show(IndexPanel.ROOT_DIRECTORY_PATH);
	}
	public void associate(IIndexTable lecture) {
		this.vDepartment.setNext(lecture);
	}
}
