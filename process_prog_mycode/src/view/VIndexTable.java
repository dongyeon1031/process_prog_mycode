package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import constants.Constant.IndexTable;
import control.CIndex;
import model.MIndex;
import model.Model;

public class VIndexTable extends JScrollPane implements IIndexTable{
	//attribute
	private static final long serialVersionUID = IndexTable.VERSION_NUM;
	protected String fileName;
	//component
	private JTable jTable;
	protected IndexModel model;
	private Vector<MIndex> mIndexList;
	//associations
	private IIndexTable next;
	
	//constructor
	public VIndexTable() {
		String[] header = new String[IndexTable.EHeader.values().length];
		for(int i=0; i<IndexTable.EHeader.values().length; i++) {
			header[i] = IndexTable.EHeader.values()[i].getTitle();
		}
		//attribute
		//component
		this.mIndexList = new Vector<>();
		//model
		this.model = new IndexModel(null, header);
		//table
		this.jTable = new JTable();
		//associate
		this.jTable.setModel(model);
		this.setViewportView(jTable);
		
		//set listener
		MouseHandler mouseHandler = new MouseHandler();
		this.jTable.addMouseListener(mouseHandler);

	}
	//method
	public void initialize() {
		
	}
	public void setNext(IIndexTable next) {
		this.next = next;
	}
	@Override
	public void show(String fileName) {
		this.fileName = fileName;
		this.model.setRowCount(0);
		//get data
		CIndex cIndex = new CIndex();
		this.mIndexList.clear(); 
		for(Model i : cIndex.getList(fileName)) {
			this.mIndexList.add((MIndex)i);
		}
		for(MIndex c : mIndexList) {
			String[] row = {String.valueOf(c.getId()), c.getName()};
			this.model.addRow(row);
		}
        
		this.showNext(0);
	}
	protected void showNext(int rowIndex) {
		if(this.next != null) {
			this.next.show(IndexTable.DATA_DIRECTORY_PATH+this.mIndexList.get(rowIndex).getLink()+".txt");
		}
	}

	protected class MouseHandler implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
        	jTable.requestFocusInWindow();
            int rowIndex = jTable.getSelectedRow();
            showNext(rowIndex);
        }
		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
    }
	protected class IndexModel extends DefaultTableModel{
		// 데이터 편집을 불가능하게 수정한 모델
	    private static final long serialVersionUID = 1L;
		public IndexModel(String[][] data, String[] columnNames) {
	        super(data, columnNames);
	    }
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
	}
	
}
