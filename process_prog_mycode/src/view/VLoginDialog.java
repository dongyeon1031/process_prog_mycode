package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import constants.Constant.LoginPanel;
import constants.Constant.MainFrame;
import control.CIndex;
import model.MStudent;

public class VLoginDialog extends JDialog {
	// attribute
	private static final long serialVersionUID = 1L;
	private boolean isLoginInfoSave;
	// association
	private VMainFrame parent;
	// component
	private JTextField idTextField;
	private JTextField passwordTextField;
	private JButton loginButton;
	private JRadioButton loginInfoSaveButton;

	public VLoginDialog(VMainFrame parent) {
		this.isLoginInfoSave = false;
		
		this.parent = parent;
		this.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(LoginPanel.BLANK, LoginPanel.BLANK, LoginPanel.BLANK, LoginPanel.BLANK); //여백

		// 사용자 이름 레이블 및 텍스트 필드 추가
		JLabel userLabel = new JLabel(LoginPanel.ID_LABEL);
		gbc.gridx = LoginPanel.COL_0;
		gbc.gridy = LoginPanel.ROW_0;
		this.add(userLabel, gbc);

		this.idTextField = new JTextField(LoginPanel.TEXT_LENGTH);
		gbc.gridx = LoginPanel.COL_1;
		gbc.gridy = LoginPanel.ROW_0;
		this.add(this.idTextField, gbc);

		// 비밀번호 레이블 및 비밀번호 필드 추가
		JLabel passwordLabel = new JLabel(LoginPanel.PASSWORD_LABEL);
		gbc.gridx = LoginPanel.COL_0;
		gbc.gridy = LoginPanel.ROW_1;
		this.add(passwordLabel, gbc);

		this.passwordTextField = new JPasswordField(LoginPanel.TEXT_LENGTH);
		gbc.gridx = LoginPanel.COL_1;
		gbc.gridy = LoginPanel.ROW_1;
		this.add(this.passwordTextField, gbc);

		// 로그인 버튼
		this.loginButton = new JButton(LoginPanel.LOGIN_LABEL);
		gbc.gridx = LoginPanel.COL_1;
		gbc.gridy = LoginPanel.ROW_2;
		gbc.anchor = GridBagConstraints.EAST;
		this.loginButton.addActionListener(new LoginHandler());
		this.add(this.loginButton, gbc);
		
		// 로그인 정보 저장 여부 버튼
		this.loginInfoSaveButton = new JRadioButton(LoginPanel.SAVE_LOGIN_INFO_LABEL);
		this.loginInfoSaveButton.addItemListener(new LoginInfoHandler());
		gbc.gridx = LoginPanel.COL_0;
		gbc.gridy = LoginPanel.ROW_2;
		this.add(this.loginInfoSaveButton, gbc);
		
		this.idTextField.addKeyListener(new EnterListener());
		this.passwordTextField.addKeyListener(new EnterListener());
		this.loginButton.addKeyListener(new EnterListener());
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		this.pack();
		double x = (screenSize.width - this.getWidth()) * MainFrame.X;
		double y = (screenSize.height - this.getHeight()) * MainFrame.Y;
		this.setLocation((int) x, (int) y);
		
		this.setAlwaysOnTop(true);
	}

	private void doOk(String id, String password) {
		// 아이디 비밀번호로 컨트롤러에 확인하고 맞으면 수강신청 패널 new 하기
        CIndex cIndex = new CIndex();
        MStudent student = (MStudent) cIndex.findModel(id, LoginPanel.STUDENT_DIRECTORY_PATH);
        if(student!=null && student.getPassword().equals(password)) {
        	if(!isLoginInfoSave) {
        		idTextField.setText("");
        		passwordTextField.setText("");
        	}
        	parent.login(student);
        	setVisible(false);
        }else {
        	this.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog(this, LoginPanel.LOGIN_FAIL_CONTENT, 
					LoginPanel.LOGIN_LABEL, JOptionPane.ERROR_MESSAGE);
			this.setAlwaysOnTop(true);
        }
	}

	private class LoginHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String id = idTextField.getText();
            String password = passwordTextField.getText();
            
            doOk(id, password);
		}
	}
	private class LoginInfoHandler implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
            	isLoginInfoSave = true;
            } else {
            	isLoginInfoSave = false;
            }
        }
		
	}
	private class EnterListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
		}
		@Override
		public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            	loginButton.doClick();
            }
		}
		@Override
		public void keyReleased(KeyEvent e) {
		}
		
	}
}
