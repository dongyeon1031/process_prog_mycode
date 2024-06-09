package view;

import java.awt.BorderLayout;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import constants.Constant.LoginDialog;
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
	private JPasswordField passwordTextField;
	private JButton loginButton;
	private JRadioButton loginInfoSaveButton;
	private JButton setPasswordVisibleButton;

	public VLoginDialog(VMainFrame parent) {
		this.isLoginInfoSave = false;
		
		this.parent = parent;
		this.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(LoginDialog.BLANK, LoginDialog.BLANK, LoginDialog.BLANK, LoginDialog.BLANK); //여백

		// 사용자 이름 레이블 및 텍스트 필드 추가
		JLabel userLabel = new JLabel(LoginDialog.ID_LABEL);
		gbc.gridx = LoginDialog.COL_0;
		gbc.gridy = LoginDialog.ROW_0;
		this.add(userLabel, gbc);

		this.idTextField = new JTextField(LoginDialog.ID_LENGTH);
		gbc.gridx = LoginDialog.COL_1;
		gbc.gridy = LoginDialog.ROW_0;
		this.add(this.idTextField, gbc);

		// 비밀번호 레이블 및 비밀번호 필드 추가
		JLabel passwordLabel = new JLabel(LoginDialog.PASSWORD_LABEL);
		gbc.gridx = LoginDialog.COL_0;
		gbc.gridy = LoginDialog.ROW_1;
		this.add(passwordLabel, gbc);

		// password 담을 패널
		JPanel passwordPanel = new JPanel(new BorderLayout());
		// password text field
		this.passwordTextField = new JPasswordField(LoginDialog.PASSWORD_LENGTH);
		passwordPanel.add(passwordTextField, BorderLayout.WEST);
		// password visible button
		this.setPasswordVisibleButton = new JButton(LoginDialog.EYE);
		this.setPasswordVisibleButton.addMouseListener(new passwordVisibleHandler());
		passwordPanel.add(this.setPasswordVisibleButton, BorderLayout.EAST);
		
		gbc.gridx = LoginDialog.COL_1;
		gbc.gridy = LoginDialog.ROW_1;
		this.add(passwordPanel, gbc);
		
		// 로그인 버튼
		this.loginButton = new JButton(LoginDialog.LOGIN_LABEL);
		gbc.gridx = LoginDialog.COL_1;
		gbc.gridy = LoginDialog.ROW_2;
		gbc.anchor = GridBagConstraints.EAST;
		this.loginButton.addActionListener(new LoginHandler());
		this.add(this.loginButton, gbc);
		
		// 로그인 정보 저장 여부 버튼
		this.loginInfoSaveButton = new JRadioButton(LoginDialog.SAVE_LOGIN_INFO_LABEL);
		this.loginInfoSaveButton.addItemListener(new LoginInfoHandler());
		gbc.gridx = LoginDialog.COL_0;
		gbc.gridy = LoginDialog.ROW_2;
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
        MStudent student = (MStudent) cIndex.findModel(id, LoginDialog.STUDENT_DIRECTORY_PATH);
        if(student!=null && student.getPassword().equals(password)) {
        	if(!isLoginInfoSave) {
        		idTextField.setText(LoginDialog.RESET_STRING);
        		passwordTextField.setText(LoginDialog.RESET_STRING);
        	}
        	parent.login(student);
        	setVisible(false);
        }else {
        	this.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog(this, LoginDialog.LOGIN_FAIL_CONTENT, 
					LoginDialog.LOGIN_LABEL, JOptionPane.ERROR_MESSAGE);
			this.setAlwaysOnTop(true);
        }
	}

	private class LoginHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String id = idTextField.getText();
            String password =  String.valueOf(passwordTextField.getPassword());
            
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
	
	private class passwordVisibleHandler implements MouseListener{
		private char defaultChar;
		public passwordVisibleHandler() {
			this.defaultChar = passwordTextField.getEchoChar();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			passwordTextField.setEchoChar(LoginDialog.ASCII_NULL);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			passwordTextField.setEchoChar(defaultChar);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			passwordTextField.setEchoChar(defaultChar);
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		
	}
}
