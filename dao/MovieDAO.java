package MovieTicketing.dao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.UIManager;

import MovieTicketing.model.AvataDTO;
import MovieTicketing.model.ClientDTO;
import MovieTicketing.model.PhantomDTO;
import MovieTicketing.model.PointmanDTO;
import MovieTicketing.model.SeatDTO;
import MovieTicketing.model.SlamDTO;
import MovieTicketing.viewer.AfterMenu;
import MovieTicketing.viewer.MLoginFrame;
import MovieTicketing.viewer.Seat;

public class MovieDAO extends JFrame {

	private Connection conn;
	private static String movielist = null;
	private Timestamp movietime = null;
	private static MovieDAO dao = new MovieDAO();
	public static Timestamp Stime = null;
	public static String Smovie = null;
	int tcount = 0;

	public MovieDAO() {

	}

	public static MovieDAO getInstance() {
		return dao;
	}

	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "jooho122", "1234");
			System.out.println("커넥션 OK -->" + conn);
		} catch (Exception e) {
			System.out.println("Connetion 생성시 예외 발생함.");
			System.out.println("예외 내용 : " + e.getMessage());
		}
		return conn;
	}


	public SeatDTO seatSelection(String seatnumber) {// 선택한 좌석 DB 입력 메서드.
		SeatDTO movie = null;

		String sql = "Insert into Ticketing (TCODE,CLIENTID,MOVIETITLE,MOVIEDATE,SEATNUMBER)" // 이건 예매하기 버튼 이벤트(좌석 선택 후
				// 진행)
				+ " Values (?,?,?,?,?)";

		SeatDTO dup = null;

		List<SeatDTO> dupTcode = dao.dupSeat();

		Connection con = getConnection();
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(sql);

			if (dupSeat().isEmpty()) {
				pstmt.setInt(1, 0);
				pstmt.setString(2, MLoginFrame.uid);
				pstmt.setString(3, Smovie);
				pstmt.setTimestamp(4, Stime);
				pstmt.setString(5, seatnumber);

				pstmt.executeUpdate();
			} else {
				dup = dupTcode.get(dupTcode.size() - 1);

				pstmt.setInt(1, Integer.parseInt(dup.getTcode()) + 1);
				pstmt.setString(2, MLoginFrame.uid);
				pstmt.setString(3, Smovie);
				pstmt.setTimestamp(4, Stime);
				pstmt.setString(5, seatnumber);
				pstmt.executeUpdate();
			}

			movie = new SeatDTO();

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}

		return movie;

	}

	public List<AvataDTO> avataInfo() {// 아바타영화 정보담기
		List<AvataDTO> avata = Collections.EMPTY_LIST;
		AvataDTO dto = null;
		String sql = "Select * from movie";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection cn = getConnection();
		try {
			pstmt = cn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 데이터가 존재한다면..
			if (rs.next()) {// if절로 커서를 Data 테이블로 내리고.
				avata = new ArrayList<AvataDTO>();
				do {
					dto = new AvataDTO();
					dto.setAvataTitle(rs.getString("movietitle"));
					dto.setAvataDate(rs.getTimestamp("moviedate"));
					dto.setAvataRunTime(rs.getInt("runningtime"));
					dto.setAvatatheater(rs.getString("theater"));
					avata.add(dto);
				} while (rs.next());
			}
			rs.close();
			pstmt.close();
			cn.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return avata;
	}

	public List<SlamDTO> slamInfo() {// 슬램덩크 정보담기
		List<SlamDTO> slam = Collections.EMPTY_LIST;
		SlamDTO dto = null;
		String sql = "Select * from movie2";// 영화 테이블 선택
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection cn = getConnection();
		try {
			pstmt = cn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 데이터가 존재한다면..
			if (rs.next()) {
				slam = new ArrayList<SlamDTO>();
				do {
					dto = new SlamDTO();
					dto.setSlamTitle(rs.getString("movietitle"));
					dto.setSlamDate(rs.getTimestamp("moviedate"));
					dto.setSlamRunTime(rs.getInt("runningtime"));
					dto.setSlamTheater(rs.getString("theater"));

					slam.add(dto);
				} while (rs.next());
			}
			rs.close();
			pstmt.close();
			cn.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return slam;
	}

	public List<PointmanDTO> pointmanInfo() {// 교섭 정보담기
		List<PointmanDTO> Pointman = Collections.EMPTY_LIST;
		PointmanDTO dto = null;
		String sql = "Select * from movie3";// 영화 테이블 선택
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection cn = getConnection();
		try {
			pstmt = cn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 데이터가 존재한다면..
			if (rs.next()) {
				Pointman = new ArrayList<PointmanDTO>();
				do {
					dto = new PointmanDTO();
					dto.setPointmanTitle(rs.getString("movietitle"));
					dto.setPointmanDate(rs.getTimestamp("moviedate"));
					dto.setPointmanRunTime(rs.getInt("runningtime"));
					dto.setPointmanTheater(rs.getString("theater"));

					Pointman.add(dto);
				} while (rs.next());
			}
			rs.close();
			pstmt.close();
			cn.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return Pointman;
	}

	public List<PhantomDTO> phantomInfo() {// 유령 정보담기
		List<PhantomDTO> Phantom = Collections.EMPTY_LIST;
		PhantomDTO dto = null;
		String sql = "Select * from movie4";// 영화 테이블 선택
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection cn = getConnection();
		try {
			pstmt = cn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 데이터가 존재한다면..
			if (rs.next()) {
				Phantom = new ArrayList<PhantomDTO>();
				do {
					dto = new PhantomDTO();
					dto.setPhantomTitle(rs.getString("movietitle"));
					dto.setPhantomDate(rs.getTimestamp("moviedate"));
					dto.setPhantomRunTime(rs.getInt("runningtime"));
					dto.setPhantomTheater(rs.getString("theater"));

					Phantom.add(dto);
				} while (rs.next());
			}
			rs.close();
			pstmt.close();
			cn.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return Phantom;
	}

	public void movieSelect() {

		JFrame f = new JFrame("영화예매 프로그램");
		f.getContentPane().setBackground(Color.WHITE);

		JLabel mb = new JLabel("");
		mb.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\btn_moviechoice.png"));
		JLabel db = new JLabel("");
		db.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\btn_moviedate.png"));

		JButton b = new JButton("");// 좌석선택 버튼 생성
		b.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\seat.png"));
		b.setBounds(224, 436, 150, 100); // 좌석선택 버튼 위치,사이즈 조정
		b.setFocusPainted(false);
		b.setBorderPainted(false);

		DefaultListModel<Object> ml = new DefaultListModel<>();// 영화리스트 변수선언(영화제목 리스트니까 여기에 영화제목 추가)
		DefaultListModel<Object> mt = new DefaultListModel<>();// 영화시간 변수선언(영화시간 리스트니까 여기에 영화제목 추가)

		// ★★★★★★★★★★★★★★★★ 포스터 추가시 추가/수정할부분 시작 ★★★★★★★★★★★★★★★★

		ImageIcon imgavata = new ImageIcon("./src\\MovieTicketing\\buttonImage\\movie1.png");
		JLabel avatalabel = new JLabel();
		avatalabel.setBackground(Color.WHITE);
		avatalabel.setIcon(imgavata);
		avatalabel.setBounds(397, 145, 151, 274);
		avatalabel.setVisible(false);
		f.getContentPane().add(avatalabel);

		ImageIcon imgslam = new ImageIcon("./src\\MovieTicketing\\buttonImage\\movie2.png");
		JLabel slamlabel = new JLabel();
		slamlabel.setBackground(Color.WHITE);
		slamlabel.setIcon(imgslam);
		slamlabel.setBounds(397, 145, 151, 274);
		slamlabel.setVisible(false);
		f.getContentPane().add(slamlabel);

		ImageIcon imgpointman = new ImageIcon("./src\\MovieTicketing\\buttonImage\\movie3.png");
		JLabel pointmanlabel = new JLabel();
		pointmanlabel.setBackground(Color.WHITE);
		pointmanlabel.setIcon(imgpointman);
		pointmanlabel.setBounds(397, 145, 151, 274);
		pointmanlabel.setVisible(false);
		f.getContentPane().add(pointmanlabel);

		ImageIcon imgphantom = new ImageIcon("./src\\MovieTicketing\\buttonImage\\movie4.png");
		JLabel phantomlabel = new JLabel();
		phantomlabel.setBackground(Color.WHITE);
		phantomlabel.setIcon(imgphantom);
		phantomlabel.setBounds(397, 145, 151, 274);
		phantomlabel.setVisible(false);
		f.getContentPane().add(phantomlabel);

		// ★★★★★★★★★★★★★★★★ 포스터 추가시 추가/수정할부분 끝!! ★★★★★★★★★★★★★★★★

		// ★★★★★★★★★★★★★★★★ 영화정보 추가시 추가/수정할부분 시작 ★★★★★★★★★★★★★★★★

		ArrayList<AvataDTO> avata = (ArrayList<AvataDTO>) dao.avataInfo();
		ArrayList<SlamDTO> slam = (ArrayList<SlamDTO>) dao.slamInfo();
		ArrayList<PointmanDTO> pointman = (ArrayList<PointmanDTO>) dao.pointmanInfo();
		ArrayList<PhantomDTO> phantom = (ArrayList<PhantomDTO>) dao.phantomInfo();

		AvataDTO avatatitle = avata.get(1);// 아바타 제목 가져오기
		SlamDTO slamtitle = slam.get(1);// 슬램덩크 제목 가져오기
		PointmanDTO pointtitle = pointman.get(1);// 교섭 제목 가져오기
		PhantomDTO phantomtitle = phantom.get(1);// 유령 제목 가져오기

		ml.addElement(avatatitle.getAvataTitle());// 영화리스트에 아바타 제목 추가
		ml.addElement(slamtitle.getSlamTitle());// 영화리스트에 슬램덩크 제목 추가
		ml.addElement(pointtitle.getPointmanTitle());// 영화리스트에 교섭 제목 추가
		ml.addElement(phantomtitle.getPhantomTitle());// 영화리스트에 유령 제목 추가

		// ★★★★★★★★★★★★★★★★ 영화정보 추가시 추가/수정할부분 끝!! ★★★★★★★★★★★★★★★★

		JList<Object> movielist = new JList<Object>(ml); // 영화리스트 생성
		movielist.setBorder(UIManager.getBorder("InsetBorder.aquaVariant"));
		JList<Object> movietime = new JList<Object>(mt); // 영화시간 리스트 생성
		movietime.setBorder(UIManager.getBorder("InsetBorder.aquaVariant"));
		movietime.setBackground(Color.WHITE);

		movielist.setBounds(53, 172, 75, 247);// 영화리스트 위치,사이즈조정
		mb.setBounds(58, 145, 65, 25);// 상영영화(위치 좌우,상하)(사이즈 좌우,상하)

		movietime.setBounds(163, 172, 186, 247);// 영화시간 위치,사이즈 조정 ((위치 좌우,상하)(사이즈 좌우,상하)
		db.setBounds(169, 145, 175, 25);// 상영날짜((위치 좌우,상하)(사이즈 좌우,상하)

		f.getContentPane().add(mb);// 프레임에 "상영영화" 라벨 넣기
		f.getContentPane().add(db);// 프레임에 "상영날짜" 라벨 넣기

		f.getContentPane().add(movielist);
		f.getContentPane().add(movietime);

		f.getContentPane().add(b);// 프레임에 "좌석선택" 버튼 넣기
		f.setSize(600, 600);// 프레임(창) 전체 크기
		f.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\top2.png"));
		lblNewLabel.setBounds(0, 0, 600, 68);
		f.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\textMovie.png"));
		lblNewLabel_1.setBounds(136, 52, 328, 40);
		f.getContentPane().add(lblNewLabel_1);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton.hasFocus()) {
					f.dispose();
					new AfterMenu();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\backbutton.png"));
		btnNewButton.setBounds(12, 52, 40, 40);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		f.getContentPane().add(btnNewButton);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		movielist.addMouseListener(new MouseListener() {// 영화리스트 부분 마우스 클릭리스너 생성

			@Override
			public void mouseClicked(MouseEvent e) {// 영화리스트 제목 클릭시 클릭이벤트 발생

				if (movielist.getSelectedIndex() == 0) {// 영화리스트의 첫번째(인덱스의 0번) 누를시 발생 이벤트

					mt.removeAllElements();// 기존에 있던 데이터들 모두 삭제 후
					avatalabel.setVisible(true);
					for (int i = 0; i < avata.size(); i++) { // 아바타 "상영날짜" 상영날짜 리스트에 넣기
						AvataDTO avatatime = avata.get(i);
						mt.addElement(avatatime.getAvataDate());
					}

				}
				if (movielist.getSelectedIndex() == 1) {// 영화리스트의 두번째(인덱스의 1번) 누를시 발생 이벤트
					mt.removeAllElements();
					avatalabel.setVisible(false);
					slamlabel.setVisible(true);
					for (int i = 0; i < slam.size(); i++) { // 아바타 시간 리스트에 넣기
						SlamDTO slamtime = slam.get(i);
						mt.addElement(slamtime.getSlamDate());
					}
				}
				if (movielist.getSelectedIndex() == 2) {// 영화리스트의 세번째(인덱스의 2번) 누를시 발생 이벤트
					mt.removeAllElements();
					avatalabel.setVisible(false);
					slamlabel.setVisible(false);
					pointmanlabel.setVisible(true);
					for (int i = 0; i < pointman.size(); i++) { // 아바타 시간 리스트에 넣기
						PointmanDTO pointmantime = pointman.get(i);
						mt.addElement(pointmantime.getPointmanDate());
					}
				}
				if (movielist.getSelectedIndex() == 3) {// 영화리스트의 네번째(인덱스의 3번) 누를시 발생 이벤트
					mt.removeAllElements();
					avatalabel.setVisible(false);
					slamlabel.setVisible(false);
					pointmanlabel.setVisible(false);
					phantomlabel.setVisible(true);
					for (int i = 0; i < phantom.size(); i++) { // 아바타 시간 리스트에 넣기
						PhantomDTO phantomtime = phantom.get(i);
						mt.addElement(phantomtime.getPhantomDate());
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		b.addActionListener(new ActionListener() { // 좌석선택
			public void actionPerformed(ActionEvent e) {

				Smovie = (String) movielist.getSelectedValue();
				Stime = (Timestamp) movietime.getSelectedValue();

				f.dispose();
				new Seat().main(null);

			}
		});

	}

	public List<SeatDTO> dupSeat() {// 중복좌석값제거 ★★★★★★★★★★★★★★★★★★★
		List<SeatDTO> dupSeat = Collections.EMPTY_LIST;
		SeatDTO dto = null;
		String sql = "Select * from Ticketing";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection cn = getConnection();
		try {
			pstmt = cn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 데이터가 존재한다면..
			if (rs.next()) {// if절로 커서를 Data 테이블로 내리고.
				// DTO가 담겨질 ArrayList를 생성합니다.
				dupSeat = new ArrayList<SeatDTO>();
				do {
					dto = new SeatDTO();
					dto.setSeatNumber(rs.getString("seatnumber"));
					dto.setPlaydate(rs.getTimestamp("moviedate"));
					dto.setTcode(rs.getString("tcode"));
					// DTO의 값이 모두 완료되면, members에 담습니다.
					dupSeat.add(dto);
				} while (rs.next());
			}

			rs.close();
			pstmt.close();
			cn.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return dupSeat;
	}

	public SeatDTO TicketDelete(String uid) {// 예매 삭제 기능
		SeatDTO movie = null;

		String sql = "Update ticketing SET seatnumber = ?" + "where clientId = ? ";

		Connection conn = getConnection();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, null);
			pstmt.setString(2, MLoginFrame.uid);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				movie = new SeatDTO();
				movie.setSeatNumber(null);
				rs.close();
				pstmt.cancel();
			} else {
				rs.close();
				pstmt.cancel();
			}
		} catch (Exception e) {
			System.out.println("예외 발생 " + e.getMessage());
		}
		return movie;
	}

	public int DeleteTi(String uid, Timestamp Stime) {// 초기화 및 뒤로가기
		String sql = "delete from Ticketing where clientId = ? and moviedate = ?";
		Connection conn = getConnection();
		int result = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MLoginFrame.uid);
			pstmt.setTimestamp(2, Stime);

			result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
