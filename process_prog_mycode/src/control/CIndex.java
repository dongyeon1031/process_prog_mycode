package control;

import java.util.Vector;

import model.DAO;
import model.Model;

public class CIndex {
	private DAO dao;
	
	public CIndex() {
		this.dao = new DAO();
	}
	
	public Vector<Model> getList(String fileName) {
		Vector<Model> mModelList = this.dao.getList(fileName);
		return mModelList;
	}
	public Model findModel(String id, String fileName) {
		Vector<Model> mModelList = this.dao.getList(fileName);
		for(Model m : mModelList) {
			if(id.equals(String.valueOf(m.getId()))) {
				return m;
			}
		}
		return null;
	}
}
