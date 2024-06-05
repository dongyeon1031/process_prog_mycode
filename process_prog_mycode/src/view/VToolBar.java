package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import model.MStudent;

public class VToolBar extends JToolBar {
	//attribute
    private static final long serialVersionUID = 1L;
    // association
    private VSugangSincheong parent;
    //component
	private JLabel nameLabel;
	private JLabel idLabel;
    private JLabel gradeLabel;
    private JLabel currentCreditLabel;
    private JLabel applicableCreditLabel;
    private JButton logOutButton;

    public VToolBar(VSugangSincheong parent) {
    	// association
    	this.parent = parent;
    	// attribute
    	this.setBackground(Color.LIGHT_GRAY);
    	
    	// layout
    	setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.2;
        gbc.gridx = 0;
        
        //component
        this.nameLabel = new JLabel("이름 : ");
        this.add(nameLabel, gbc);
        gbc.gridx++;
        
        this.gradeLabel = new JLabel("학년 : ");
        this.add(gradeLabel, gbc);
        gbc.gridx++;
        
        this.idLabel = new JLabel("학번 : ");
        this.add(idLabel, gbc);
        gbc.gridx++;

        this.currentCreditLabel = new JLabel("신청 학점 : ");
        this.add(currentCreditLabel, gbc);
        gbc.gridx++;
        
        this.applicableCreditLabel = new JLabel("신청 가능 학점 : ");
        this.add(applicableCreditLabel, gbc);
        gbc.gridx++;
        
        this.logOutButton = new JButton("로그아웃");
        this.logOutButton.addActionListener(new LogOutHandler());
        this.add(this.logOutButton, gbc);
    }
    public void setInfo(MStudent student) {
    	this.setStudentName(student.getName());
    	this.setGrade(student.getGrade());
    	this.setId(student.getId());
    	this.setCurrentCredit(student.getCurrentCredit());
    	this.setApplicableCredit(student.getApplicableCredit());
    }
    private void setStudentName(String name) {
    	this.nameLabel.setText("이름 : " + name);
    }
    private void setGrade(String grad) {
    	this.gradeLabel.setText("학년 : " + grad);
    }
    private void setId(int id) {
    	this.idLabel.setText("학번 : " + id);
    }
    private void setCurrentCredit(int credit) {
    	this.currentCreditLabel.setText("신청 학점: " + credit);
    }
    private void setApplicableCredit(int credit) {
    	this.applicableCreditLabel.setText("신청 가능 학점: " + credit);
    }
    
    private class LogOutHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			parent.logout();
		}
    	
    }
}