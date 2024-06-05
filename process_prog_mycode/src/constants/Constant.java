package constants;

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
	
	public class LoginPanel{
		public final static String STUDENT_DIRECTORY_PATH = System.getProperty("user.dir") + "/src/data/student.txt";
		public final static String ID_LABEL = "아이디 :";
		public final static String PASSWORD_LABEL = "비밀번호 :";
		public final static String LOGIN_LABEL = "로그인";
		public final static String SAVE_LOGIN_INFO_LABEL = "로그인 정보 저장";
		
		public final static int TEXT_LENGTH = 20;
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
}