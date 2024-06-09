package constants;

import javax.swing.ImageIcon;

public class Constant {
	
	// 원래는 파일에서 값을 읽어서 세팅한다.
	public class MainFrame{
		public final static long VERSION_NUM = 1L;
		// main frame에 관련된 상수를 모아둠.
		public final static String TITLE = "수강신청";
		public final static double Monitor_RATIO = 0.666;
		public final static double X = 0.5;
		public final static double Y = 0.5;
		
		public final static String SUGANG_SHINCHEONG_PANEL = "shincheongPanel";
		public final static String LOGIN_PANEL = "loinPanel";
		
	}
	
	public class IndexTable{
		public final static long VERSION_NUM = 1L;
		public final static String DATA_DIRECTORY_PATH = System.getProperty("user.dir") + "/src/data/";
		public enum EHeader{
			eID("아이디"),
			eTitle("이름"),
			;
			private String title;
			private EHeader(String title) {
				this.title = title;
			}
			public String getTitle() {
				return this.title;
			}
		}
		
	}
	
	public class LectureTable{
		public final static String ALREADY_REGISTER_CONTENT =  "이미 신청한 과목입니다.";
		public final static String ALREADY_REGISTER_TITLE = "이미 신청한 과목";
		public enum EHeader{
			eID("아이디"),
			eTitle("강좌명"),
			eLecturer("강사"),
			eCredit("학점"),
			eTime("시간")
			;
			private String title;
			private EHeader(String title) {
				this.title = title;
			}
			public String getTitle() {
				return this.title;
			}
		}
	}
	
	public class IndexPanel{
		public final static long VERSION_NUM = 1L;
		public final static String ROOT_DIRECTORY_PATH = System.getProperty("user.dir") + "/src/data/root.txt";
	}
	
	public class LoginDialog{
		public final static ImageIcon EYE = new ImageIcon(System.getProperty("user.dir") + "/img/eye.png");
		
		public final static String STUDENT_DIRECTORY_PATH = System.getProperty("user.dir") + "/src/data/student.txt";
		public final static String ID_LABEL = "아이디 :";
		public final static String PASSWORD_LABEL = "비밀번호 :";
		public final static String LOGIN_LABEL = "로그인";
		public final static String SAVE_LOGIN_INFO_LABEL = "로그인 정보 저장";
		public final static String RESET_STRING = "";
		public final static char ASCII_NULL = 0x0000;
		
		public final static int ID_LENGTH = 20;
		public final static int PASSWORD_LENGTH = 17;
		public final static int BLANK = 10;
		public final static String LOGIN_FAIL_CONTENT = "로그인에 실패했습니다.\n입력 정보를 확인하세요.";
		public final static String LOGIN_FAIL_TITLE = "로그인 실패";
		
		public final static int COL_0 = 0;
		public final static int COL_1 = 1;
		public final static int ROW_0 = 0;
		public final static int ROW_1 = 1;
		public final static int ROW_2 = 2;
	}
	
	public class ControlPanel{
		public final static String TO_LEFT = "<<";
		public final static String TO_RIGHT = ">>";
	}
	public class DAOConstant{
		public final static int LECTURE_ID_LEN = 4;
		public final static int STUDENT_ID_LEN = 8;
	}
	public class LoginTask{
		public final static long DELAY = 120000; //2분
	}
	public class ToolBar{
		public final static String NAME_LABEL = "이름 : ";
		public final static String GRADE_LABEL = "학년 : ";
		public final static String ID_LABEL = "학번 : ";
		public final static String LOGOUT_TIME_LABEL = "로그인 남은 시간 : ";
		public final static String RENEWAL_LABEL = "연장";
		public final static String LOGOUT_LABEL = "로그아웃";
		
		public final static String LOGOUT_TIME_FORMAT = "%02d:%02d";
		public final static int REFRESH_TIME = 100;
		public final static int TIME_OUT = 0;
	}
}