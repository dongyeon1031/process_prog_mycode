package view;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class VSelectionPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private VIndexPanel vIndexPanel;
	private VLectureTable vLectureTable;
	
	public VSelectionPanel() {
		LayoutManager layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layoutManager);
		
		this.vIndexPanel = new VIndexPanel();
		this.add(vIndexPanel);
		
		this.vLectureTable = new VLectureTable();
		this.add(vLectureTable);
		
		this.vIndexPanel.associate(this.vLectureTable);
	}

	public void initialize() {
		this.vIndexPanel.initialize();
		this.vLectureTable.initialize();
	}
	
	public VLectureTable getLectureTable() {
		return this.vLectureTable;
	}
}
