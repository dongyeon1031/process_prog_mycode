package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import constants.Constant.LoginTask;
import constants.Constant.ToolBar;
import model.MStudent;

public class VToolBar extends JToolBar {
	// attribute
	private static final long serialVersionUID = 1L;
	// association
	private VSugangSincheong parent;
	// component
	private JLabel nameLabel;
	private JLabel idLabel;
	private JLabel gradeLabel;
//    private JLabel currentCreditLabel;
//    private JLabel applicableCreditLabel;
	private JLabel logoutTimeLabel;
	private Thread remainTimeCalculateThread;
	private CalculateRemainTimeRunnable calculateRemainTimeRunnable;
	private JButton logOutButton;
	private JButton renewalButton;

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

		// component
		this.nameLabel = new JLabel(ToolBar.NAME_LABEL);
		this.add(nameLabel, gbc);
		gbc.gridx++;

		this.gradeLabel = new JLabel(ToolBar.GRADE_LABEL);
		this.add(gradeLabel, gbc);
		gbc.gridx++;

		this.idLabel = new JLabel(ToolBar.ID_LABEL);
		this.add(idLabel, gbc);
		gbc.gridx++;

//        this.currentCreditLabel = new JLabel("신청 학점 : ");
//        this.add(currentCreditLabel, gbc);
//        gbc.gridx++;
//        
//        this.applicableCreditLabel = new JLabel("신청 가능 학점 : ");
//        this.add(applicableCreditLabel, gbc);
//        gbc.gridx++;

		this.logoutTimeLabel = new JLabel(ToolBar.LOGOUT_TIME_LABEL);
		this.add(logoutTimeLabel, gbc);
		gbc.gridx++;

		this.renewalButton = new JButton(ToolBar.RENEWAL_LABEL);
		this.renewalButton.addActionListener(new RenewalHandler());
		this.add(this.renewalButton, gbc);
		gbc.gridx++;
		
		this.logOutButton = new JButton(ToolBar.LOGOUT_LABEL);
		this.logOutButton.addActionListener(new LogOutHandler());
		this.add(this.logOutButton, gbc);

		this.resetLogoutTime();
	}

	public void setInfo(MStudent student) {
		this.setStudentName(student.getName());
		this.setGrade(student.getGrade());
		this.setId(student.getId());
//    	this.setCurrentCredit(student.getCurrentCredit());
//    	this.setApplicableCredit(student.getApplicableCredit());
	}
	public void resetLogoutTime() {
		if(this.calculateRemainTimeRunnable != null) {
			this.calculateRemainTimeRunnable.setStop();
		}
		this.calculateRemainTimeRunnable = new CalculateRemainTimeRunnable();
		this.remainTimeCalculateThread = new Thread(this.calculateRemainTimeRunnable);
		this.remainTimeCalculateThread.start();
	}
	private void updateLogoutTime(long time) {
        long seconds = time / 1000;
        long minutes = seconds / 60;
        seconds = seconds % 60;
		this.logoutTimeLabel.setText(ToolBar.LOGOUT_TIME_LABEL+String.format(ToolBar.LOGOUT_TIME_FORMAT, minutes, seconds));
	}

	private void setStudentName(String name) {
		this.nameLabel.setText(ToolBar.NAME_LABEL + name);
	}

	private void setGrade(String grad) {
		this.gradeLabel.setText(ToolBar.GRADE_LABEL + grad);
	}

	private void setId(int id) {
		this.idLabel.setText(ToolBar.ID_LABEL + id);
	}
//    private void setCurrentCredit(int credit) {
//    	this.currentCreditLabel.setText("신청 학점: " + credit);
//    }
//    private void setApplicableCredit(int credit) {
//    	this.applicableCreditLabel.setText("신청 가능 학점: " + credit);
//    }

	private class LogOutHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			parent.logout();
		}

	}
	private class RenewalHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			parent.resetLogoutTime();
		}
		
	}

	class CalculateRemainTimeRunnable implements Runnable {
		private long bindTime;
		private boolean isRun;
		public CalculateRemainTimeRunnable() {
			this.bindTime = System.currentTimeMillis();
			this.isRun = true;
		}

		@Override
		public void run() {
			while (this.isRun) {
                long currentTime = System.currentTimeMillis();
                long timeLeft = LoginTask.DELAY + this.bindTime - currentTime;

				if (timeLeft <= ToolBar.TIME_OUT) {
					break;
				} else {
					updateLogoutTime(timeLeft);
				}
				try {
					Thread.sleep(ToolBar.REFRESH_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void setStop() {
			this.isRun = false;
		}
	}
}