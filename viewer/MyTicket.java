package MovieTicketing.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import MovieTicketing.model.SeatDTO;
import javax.swing.SwingConstants;

public class MyTicket extends JFrame implements ActionListener {

	private static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "jooho122", "1234");
			System.out.println("컨넥션 OK.. 정보 --> " + conn);

		} catch (Exception e) {
			System.out.println("Connection생성시 예외 발생함.");
			System.out.println("예외 내용 : " + e.getMessage());
		}
		return conn;
	}

	private Connection conn;
	private Container con;
	private JLabel title_lb = new JLabel("", JLabel.CENTER);

	// 검색된 파일의 정보를 저장할 Vector 생성
	private Vector data_vc = new Vector();
	private Vector field_vc = new Vector();

	private DefaultTableModel datamodel = new DefaultTableModel();

	// JTable 은 엑셀처럼 데이터를 그리드 형식으로 저장,관리,표현 해준다. JTableModel 과 같이 사용된다.
	private JTable view_jt;
	private JScrollPane view_jsp;

	private JButton search_bt = new JButton("예매 조회");
	private JButton cancle_bt = new JButton("예매 취소");
	private JButton end_bt = new JButton("뒤로가기");

	private JPanel jpjp;

	public MyTicket() {
		super("예매 정보 확인 및 취소");
		this.init();
		this.start();
		this.setSize(600, 600);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = this.getSize();

		int x = (int) (screen.getWidth() / 2 - frm.getWidth() / 2);
		int y = (int) (screen.getHeight() / 2 - frm.getHeight() / 2);
		this.setLocation(x, y);
		this.setVisible(true);
	}

	private void init() {
		// 프레임 바닥,,,제일 아래에 있는 컨테이너 생성..하위에 생성되는 컴포넌트를 포함한 컨테이너를 나중에 모두 add 시킨다.
		con = this.getContentPane();

		// 보더레이아웃 배치 설정.
		con.setLayout(new BorderLayout(10, 10));

		// 판넬 컨테이너 구성
		JPanel jp4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		jpjp = new JPanel(new BorderLayout(10, 10));

		// 검색 결과 컴포넌트용 JPanel 생성.
		JPanel jpjp1 = new JPanel(new BorderLayout(5, 5));
		title_lb.setBounds(0, 0, 600, 50);
		title_lb.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\top_myticket.png"));

		// 검색 중인 파일을 표현할 Label 객체의 보더를 설정한다.
		title_lb.setBorder(new BevelBorder(BevelBorder.LOWERED));

		// 컨테이너 크기 재설정.
		jpjp1.setPreferredSize(new Dimension(600, 50));

		// RESULT 및 파일검색 내용을 표시할 Label 을 담는 컨테이너 생성(Orange Color)
		JPanel jpjpjp = new JPanel();
		jpjpjp.setBorder(null);
		title_lb.setFont(new Font("맑은-고딕", Font.BOLD, 20));
		jpjpjp.setBackground(Color.WHITE);
		jpjpjp.setLayout(null);
		// 가운데 검색중인 파일 표시 Label 배치
		jpjpjp.add(title_lb);

		// 바닥 컨테이너에 jpjpjp 컨테이너 add
		jpjp1.add("Center", jpjpjp);
		// 제일 바닥 컨테이너에 위 컨테이너 add
		jpjp.add("North", jpjp1);

		// JTable 의 컬럼명으로 사용될 Vector 에 컬럼명 세팅
		field_vc.add("티켓코드");
		field_vc.add("예매자 아이디");
		field_vc.add("영화제목");
		field_vc.add("좌석번호");
		// field_vc.add("상영관");
		field_vc.add("상영시간");

		// clear를 위해 한번 담아주기
		datamodel = new DefaultTableModel(data_vc, field_vc);
		// JTable 생성..초기 값은 없음.
		view_jt = new JTable(datamodel);

		// 스크롤생성
		view_jsp = new JScrollPane(view_jt);

		// 파일 검색용 영영의 중앙에 JTable 컨테이너 배치
		jpjp.add("Center", view_jsp);

		// 하단의 clear, end 버튼 add 할 컨테이너 생성.
		JPanel jpjp2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jpjp2.add(search_bt);
		jpjp2.add(cancle_bt);
		jpjp2.add(end_bt);

		// 위 컨테이너를 남쪽방위에 add
		jpjp.add("South", jpjp2);

		// JFram 작업공간에 검색 컴포넌트(컨테이너) add
		con.add("Center", jpjp);

	}

	private void start() {
		// 이벤트 등록
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		search_bt.addActionListener(this);
		cancle_bt.addActionListener(this);
		end_bt.addActionListener(this);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// OS 에 무관하게 같은 모양으로 보여주도록 객체의 메서드 호출..그냥 쓰면됨
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO: handle exception
		}
		new MyTicket();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (search_bt.hasFocus()) { // 예매내역 조회
			new MyThread();
		} else if (cancle_bt.hasFocus()) { // 예매내역 삭제
			deleteticket();
		} else if (end_bt.hasFocus()) {// 메인메뉴로 돌아가기
			dispose();
			new AfterMenu();

		}
	}

	private void updateTable(Vector<SeatDTO> imsi) { // 쓰레드의 searchTicket에서 imsi를 받아와서 출력하는 구문
		// 먼저 검색했던것 삭제하는 코드
		DefaultTableModel jdtm = (DefaultTableModel) view_jt.getModel();
		jdtm.setNumRows(0);

		// 아랫부분 수정함..Vector 를 Data로 주지않고 배열을 넣어줌.
		for (int i = 0; i < imsi.size(); i++) {
			String[] data = new String[6];

			data[0] = imsi.get(i).getTcode();
			data[1] = imsi.get(i).getID();
			data[2] = imsi.get(i).getMovieName();
			data[3] = imsi.get(i).getSeatNumber();
			// data[4] = imsi.get(i).getTheater();
			data[4] = imsi.get(i).getPlaydate().toString();

			// Model 에 Row 추가
			datamodel.addRow(data);
		}
		// 추가된 Row 테이블에 setting
		view_jt.setModel(datamodel);

		// 기존 데이터 모델 얻기.
		datamodel = (DefaultTableModel) view_jt.getModel();

		// 데이터 모델에 Data 변경 내용 알리는 메서드 호출
		datamodel.fireTableDataChanged();

		jpjp.remove(view_jsp);// 기존 내용(Jtable)을 담고 있는 ScrollPane 삭제..이유는 검색 한 파일의 정보를 JTable 에 넣었으니 다시 생성해야함.

		view_jt = new JTable(datamodel);
		view_jsp = new JScrollPane(view_jt);
		jpjp.add("Center", view_jsp);

		// 아래의 메서드는 컨테이너의 배치관리자나, 컴포넌트가 재배치 또는 컴포넌트가 새롭게 추가되거나 할때 호출해줘야 갱신된 내용을 다시 보여주기
		// 때문이다.
		jpjp.validate();
	}

	public void deleteticket() {// 삭제
		String ticketnumber = JOptionPane.showInputDialog(null, "티켓코드 입력", "취소하실 티켓코드를 입력해주세요.");
		String sql = "delete from ticketing where TCODE = ?";
		conn = getConnection();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ticketnumber);

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();

			if (ticketnumber == null) {
				JOptionPane.showMessageDialog(null, "취소하셨습니다");
			} else {
				JOptionPane.showMessageDialog(null, "입력하신 예매번호 :" + ticketnumber + " 이 예매 취소 되었습니다");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	class MyThread extends Thread {
		public MyThread() {
			this.start();
		}

		@Override
		public void run() {
			this.searchticket();
		}

	     private  void  searchticket() {//조회
	         
	         String id = MLoginFrame.uid; //기능구현을 위한 아이디 변수값 선언 실제로 합쳐지면 로그인 아이디값을 받아옴
	         SeatDTO dto = null;
	         String sql = "select * from ticketing where clientid = ?";
	         ResultSet rs = null;
	         PreparedStatement pstmt = null;
	         conn = getConnection();
	         Vector imsi = new Vector();
	         
	         try {
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, id);
	            rs = pstmt.executeQuery();
	              
	            if(rs.next()) { 
	               do {// db에서 검색값 불러와서 종류별로 dto에 대입.
	                  dto = new SeatDTO();
	                  dto.setTcode(rs.getString("tcode"));
	                  dto.setID(rs.getString("clientid"));
	                  dto.setMovieName(rs.getString("movietitle"));
	                  dto.setSeatNumber(rs.getString("seatnumber"));
	                  //dto.setTheater(rs.getString("theater"));
	                  dto.setPlaydate(rs.getTimestamp("moviedate"));
	                 
	                  imsi.add(dto); //담긴 dto값을 벡터 imsi에 넣어줌
	                  
	                  dto = null;
	                 
	                  
	               }while(rs.next());
	            }
	            updateTable(imsi); // 위 반복문에서 담긴 imsi를 updateTable로 보냄
	            rs.close();
	            pstmt.close();
	            conn.close();
	            
	            
	         }catch (Exception e1) {
	            System.out.println(e1.getMessage());
	         }
	         
	      }
	}

}
