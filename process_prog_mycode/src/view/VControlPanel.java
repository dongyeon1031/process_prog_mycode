package view;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import constants.Constant.ControlPanel;
import model.MLecture;
import view.VLectureTable.EListSetOption;

public class VControlPanel extends JPanel{
	//attribute
	private static final long serialVersionUID = 1L;
	private EListSetOption pretendSetOption;
	private EListSetOption nextSetOption;
	//component
	private JButton button_to_pretend;
	private JButton button_to_next;
	//association
	private VLectureTable lectureTable_pretend;
	private VLectureTable lectureTable_next;
	
	//constructor
	public VControlPanel() {
		LayoutManager layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layoutManager);
		
		ActionHandler actionHandler = new ActionHandler();
		
		this.button_to_pretend = new JButton(ControlPanel.TO_LEFT);
		this.button_to_pretend.addActionListener(actionHandler);
		this.add(button_to_pretend);
		
		this.button_to_next = new JButton(ControlPanel.TO_RIGHT);
		this.button_to_next.addActionListener(actionHandler);
		this.add(button_to_next);
	}
	public void initialize() {
		
	}

	public void associate(VLectureTable pretend, EListSetOption option_p, VLectureTable next, EListSetOption option_n) {
		this.pretendSetOption = option_p;
		this.nextSetOption = option_n;
		this.lectureTable_pretend = pretend;
		this.lectureTable_next = next;
	}
	
	private void movePretend() {
		Vector<MLecture> selectedList;
		selectedList = this.lectureTable_next.getSelectedList(this.nextSetOption);
		this.lectureTable_pretend.setSelectedList(selectedList, this.pretendSetOption);
	}
	
	private void moveNext() {
		Vector<MLecture> selectedList;
		selectedList = this.lectureTable_pretend.getSelectedList(this.pretendSetOption);
		this.lectureTable_next.setSelectedList(selectedList, this.nextSetOption);
	}
	
	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == button_to_pretend) {
				movePretend();
			}else if(e.getSource() == button_to_next) {
				moveNext();
			}
		}
		
	}
}
