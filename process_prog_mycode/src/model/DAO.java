package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import constants.Constant.DAOConstant;

public class DAO {
	// attribute

	// component

	// association

	// constructors
	public DAO() {
	}

	public void initialize() {
	}

	// method
	public Vector<Model> getList(String fileName) {
		Vector<Model> mIndexList = new Vector<>();
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				// deserialize
				String line = scanner.nextLine();
				String[] wordList = line.split("\\s+");
				
				Model mIndex;
				if(wordList[0].length() == DAOConstant.LECTURE_ID_LEN) {
					mIndex = new MLecture();
					mIndex.setId(Integer.parseInt(wordList[0]));
					mIndex.setName(wordList[1]);
					((MLecture) mIndex).setLecturer(wordList[2]);
					((MLecture) mIndex).setCredit(wordList[3]);
					((MLecture) mIndex).setTime(wordList[4]);
				}else if(wordList[0].length() == DAOConstant.STUDENT_ID_LEN) {
					mIndex = new MStudent();
					mIndex.setId(Integer.parseInt(wordList[0]));
					mIndex.setName(wordList[1]);
					((MStudent) mIndex).setPassword(wordList[2]);
					((MStudent) mIndex).setGrade(wordList[3]);
					((MStudent) mIndex).setApplicableCredit(Integer.parseInt(wordList[4]));
				}else {
					mIndex = new MIndex();
					mIndex.setId(Integer.parseInt(wordList[0]));
					mIndex.setName(wordList[1]);
					((MIndex) mIndex).setLink(wordList[2]);
				}
				
				
				mIndexList.add(mIndex);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return mIndexList;
	}
	
}
