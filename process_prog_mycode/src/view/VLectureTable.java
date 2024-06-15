package view;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import constants.Constant;
import control.CIndex;
import model.MLecture;
import model.MStudent;
import model.Model;

public class VLectureTable extends JScrollPane implements IIndexTable{

	//attribute
	private static final long serialVersionUID = 1L;
	private HashMap<String, Integer> registeredRowInfo;
	public enum EListSetOption{
		eRemove, eBlind, eRemain
		;
	}
	//component
	private LectureTable jTable;
	private DefaultTableModel model;	
	private Vector<MLecture> mLectureList;
	//associations
//	private VLectureTable next;
	
	//getters and setters
	public Vector<MLecture> getSelectedList(EListSetOption option){
		int[] selectedIndexes = this.jTable.getSelectedRows();
		Vector<MLecture> selectedList = new Vector<>();
		for(int i : selectedIndexes) {
			selectedList.add(this.mLectureList.get(i));
			switch(option) {
			case eRemove:	// 줄때 지우는 경우
				this.model.removeRow(i);
				break;
			case eBlind:	// 줄때 회색처리로 숨기는 경우
				this.registeredRowInfo.put(this.mLectureList.get(i).getName(), i);
				break;		// 줄때 아무 일도 하지 않는 경우
			case eRemain:
				break;
			}
		}
		if(option == EListSetOption.eRemove) {
			for(int i : selectedIndexes) {
				this.mLectureList.remove(i);
			}
		}
		this.jTable.clearSelection();
		return selectedList;
	}
	public void setSelectedList(Vector<MLecture> selectedList, EListSetOption option) {
		switch(option) {
		case eBlind:	// 줄때 숨김처리 한 경우 받을때는 회색 처리만 지워줌
			this.showSelectedList(selectedList);
			break;
		default: 		// 지운 경우는 새로 추가하고 냅둔 경우는 전부 받음
			this.addSelectedList(selectedList, option);
			break;
		}
		this.jTable.updateUI();
	}
	private void addSelectedList(Vector<MLecture> selectedList, EListSetOption option) {
		for(MLecture mLecture : selectedList) {
			if(this.conatains(mLecture)) {
				if(option == EListSetOption.eRemove) {
					JOptionPane.showMessageDialog(null, Constant.LectureTable.ALREADY_REGISTER_CONTENT, 
							Constant.LectureTable.ALREADY_REGISTER_TITLE, JOptionPane.PLAIN_MESSAGE);	
				}
				continue;
			}
			this.mLectureList.add(mLecture);
			
			String[] columns = {String.valueOf(mLecture.getId()), mLecture.getName(), mLecture.getLecturer(),
					mLecture.getCredit(), mLecture.getTime()};
			this.model.addRow(columns);
		}
		
	}
	private void showSelectedList(Vector<MLecture> selectedList) {
		Vector<Integer> unselectIndexList = new Vector<>();
        for (String key : this.registeredRowInfo.keySet()) {
            int value = this.registeredRowInfo.get(key);
            if(key.equals(this.mLectureList.get(value).getName())){
            	unselectIndexList.add(value);	
            }
        }
        for(int i : unselectIndexList) {
        	this.registeredRowInfo.remove(this.mLectureList.get(i).getName());
        }
	}
	
	//constructor
	public VLectureTable() {
		String[] header = new String[Constant.LectureTable.EHeader.values().length];
		for(int i=0; i<Constant.LectureTable.EHeader.values().length; i++) {
			header[i] = Constant.LectureTable.EHeader.values()[i].getTitle();
		}
		this.registeredRowInfo = new HashMap<>();
		//component
		this.mLectureList = new Vector<>();
		//model
		this.model = new DefaultTableModel(null, header);
		//table
		this.jTable = new LectureTable();
		//associate
		this.jTable.setModel(model);
		this.setViewportView(jTable);
		
	}
	// 정보 저장부 -> 구현 x
//	public VLectureTable(String filepath, MStudent student) {
//		String[] header = new String[Constant.LectureTable.EHeader.values().length];
//		for(int i=0; i<Constant.LectureTable.EHeader.values().length; i++) {
//			header[i] = Constant.LectureTable.EHeader.values()[i].getTitle();
//		}
//		this.registeredRowInfo = new HashMap<>();
//		//component
//		this.mLectureList = new Vector<>();
//		this.setInitalInfo(filepath, student);
//		//model
//		this.model = new DefaultTableModel(null, header);
//		//table
//		this.jTable = new LectureTable();
//		//associate
//		this.jTable.setModel(model);
//		this.setViewportView(jTable);
//		
//	}
//	private void setInitalInfo(String filepath, MStudent student) {
//		
//	}
	//method
	public void initialize() {
		
	}
	public void setNext(VLectureTable next) {
//		this.next = next;
	}
	@Override
	public void show(String fileName) {
		//get data
		CIndex cLecture = new CIndex();
		this.mLectureList.clear(); 
		for(Model i : cLecture.getList(fileName)) {
			this.mLectureList.add((MLecture)i);
		}
		this.model.setRowCount(0);

		for(MLecture mLecture : mLectureList) {
			String[] row = {String.valueOf(mLecture.getId()), mLecture.getName(), mLecture.getLecturer(),
					mLecture.getCredit(), mLecture.getTime()};
			this.model.addRow(row);
		}
//		this.showNext(0);
	}

	private boolean conatains(MLecture lecture) {
		for(MLecture l : this.mLectureList) {
			if(lecture.getName().equals(l.getName())) {
				return true;
			}
		}
 		return false;
	}

	private class LectureTable extends JTable{
        private static final long serialVersionUID = 1L;
        @Override
        public boolean isCellEditable(int row, int column) {
        	// 행 편집이 불가능하게 설정
            return false;
        }
        @Override
        public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
        	// 선택된 행은 클릭할 수 없음.
        	String title = (String) this.getModel().getValueAt(rowIndex, 1);
        	if(registeredRowInfo.get(title) != null) {
        		return;
        	}
            super.changeSelection(rowIndex, columnIndex, toggle, extend);
        }
		@Override
        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
			// 담긴 행은 회색으로 표시
            Component c = super.prepareRenderer(renderer, row, column);
            String title = (String) this.getModel().getValueAt(row, 1);
            
            if(registeredRowInfo.get(title) != null) {
        		c.setBackground(Color.LIGHT_GRAY);
        	} else if(this.isRowSelected(row)) {
            	c.setBackground(getSelectionBackground());
            }else {	
            	c.setBackground(Color.WHITE);
            }
            return c;
        }
	}
}
