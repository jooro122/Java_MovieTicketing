package MovieTicketing.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import MovieTicketing.dao.MovieDAO;
import MovieTicketing.model.SeatDTO;

class GUI extends JFrame implements MouseListener {

	JFrame f = new JFrame("좌석 확인/예매");
	JPanel p1 = new JPanel(); // Screen 표시 패널
	JPanel p2 = new JPanel(); // 좌석 표시 패널
	JPanel p3 = new JPanel(); // 확인 및 취소 패널
	Font font1 = new Font("맑은 고딕", Font.BOLD, 30);
	Font font2 = new Font("맑은 고딕", Font.BOLD, 20);
	JButton bt1 = new JButton(""); // p3 패널에 들어갈 버튼 생성
	JButton bt2 = new JButton("");
	JButton bt3 = new JButton("");
	JButton[] seatA = new JButton[9];
	JButton[] seatB = new JButton[9];
	JButton[] seatC = new JButton[9];
	JButton[] seatD = new JButton[9];
	JButton[] seatE = new JButton[9];
	JButton[] seatF = new JButton[9];
	JOptionPane jop = new JOptionPane();
	int i;
	int j = i;

	SeatDTO movie = null; // DTO 변수 담기위해서 초기화.

	private Connection conn;
	private final JLabel lblNewLabel = new JLabel("New label");

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

	public GUI() {
		f.getContentPane().setBackground(Color.WHITE);

		// 프레임 세팅//

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x누르면 프레임, 프로세스 종료
		f.setSize(600, 600); // 프레임 사이즈
		f.setLocationRelativeTo(null); // 실행할때 프레임 위치 모니터 가운데
		f.setResizable(false); // 프레임 크기조절 불가능
		f.getContentPane().setLayout(null);

		MovieDAO dao = MovieDAO.getInstance();
		List<SeatDTO> dupSeat = dao.dupSeat();
		SeatDTO dup = null;

		// 좌석버튼 (p2) 세팅//
		for (int i = 0; i < seatA.length; i++) { // for문을 이용해 버튼생성
			seatA[i] = new JButton("A" + (i + 1)); // A0부터 시작하기 위해 i에 +1
			p2.add(seatA[i]); // 패널에 버튼 더하기
			int j = i; // 새로운 변수 입력
			seatA[i].setBackground(Color.white);
			seatA[i].setFont(new Font("Helvetica75", Font.PLAIN, 14));
			seatA[i].setForeground(new Color(59, 0, 166));
			// 이미 예약되어있는 좌석 제거 코드 시작
			if (dupSeat.size() != 0) {
				for (int d = 0; d < dupSeat.size(); d++) {
					dup = dupSeat.get(d);
					if ((seatA[i].getText().equals(dup.getSeatNumber()))
							&& (dup.getPlaydate().equals(MovieDAO.Stime))) {
						seatA[i].setText("X"); // 예매된 자리는 X 로 보이게 함
						seatA[i].setEnabled(false); // 버튼을 더이상 못누르게 함
						seatA[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));						
//						seatA[i].setVisible(false); // 버튼을 더이상 못누르게 함
					}
				}
			}
			// 이미 예약되어있는 좌석 제거 코드 시작 끝

			seatA[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int que = jop.showConfirmDialog(null, "A" + (j + 1) + " 좌석을 선택하시겠습니까?", "확인 메시지",
							jop.YES_NO_OPTION); // 확인 메세지 출력

					if (que == 0) { // "예"를 누른 경우
						seatA[j].setForeground(Color.red); // 폰트 색상 변경
						seatA[j].setFont(new Font("맑은 고딕", Font.BOLD, 14));
						seatA[j].setEnabled(false); // 버튼을 더이상 못누르게 함
						
						
						
						// 좌석을 입력 받음

						bt1.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								String seatnumber = ("A" + (j + 1));
								MovieDAO.getInstance().seatSelection(seatnumber);
							}
						});

					}
				}
			});
		} // A열 버튼
		for (int i = 0; i < seatB.length; i++) {
			seatB[i] = new JButton("B" + (i + 1));
			p2.add(seatB[i]);
			int j = i;
			seatB[i].setBackground(Color.white);
			seatB[i].setFont(new Font("Helvetica75", Font.PLAIN, 14));
			seatB[i].setForeground(new Color(59, 0, 166));

			// 이미 예약되어있는 좌석 제거 코드 시작
			if (dupSeat.size() != 0) {
				for (int d = 0; d < dupSeat.size(); d++) {
					dup = dupSeat.get(d);
					if ((seatB[i].getText().equals(dup.getSeatNumber()))
							&& (dup.getPlaydate().equals(MovieDAO.Stime))) {
						seatB[i].setEnabled(false); // 버튼을 더이상 못누르게 함
						seatB[i].setText("X"); // 예매된 자리는 X 로 보이게 함
						seatB[i].setFont(new Font("맑은 고딕", Font.BOLD, 30));
					}
				}
			}
			// 이미 예약되어있는 좌석 제거 코드 시작 끝

			seatB[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int que = jop.showConfirmDialog(null, "B" + (j + 1) + " 좌석을 선택하시겠습니까?", "확인 메시지",
							jop.YES_NO_OPTION);
					if (que == 0) {
						seatB[j].setFont(new Font("Helvetica75", Font.PLAIN, 14));
						seatB[j].setEnabled(false);

						bt1.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								String seatnumber = ("B" + (j + 1));
								MovieDAO.getInstance().seatSelection(seatnumber);
							}
						});
					}
				}
			});
		} // B열 버튼
		for (int i = 0; i < seatC.length; i++) {
			seatC[i] = new JButton("C" + (i + 1));
			p2.add(seatC[i]);
			int j = i;
			seatC[i].setBackground(Color.white);
			seatC[i].setFont(new Font("Helvetica75", Font.PLAIN, 14));
			seatC[i].setForeground(new Color(59, 0, 166));

			// 이미 예약되어있는 좌석 제거 코드 시작
			if (dupSeat.size() != 0) {
				for (int d = 0; d < dupSeat.size(); d++) {
					dup = dupSeat.get(d);
					if ((seatC[i].getText().equals(dup.getSeatNumber()))
							&& (dup.getPlaydate().equals(MovieDAO.Stime))) {
						seatC[i].setEnabled(false); // 버튼을 더이상 못누르게 함
						seatC[i].setText("X"); // 예매된 자리는 X 로 보이게 함
						seatC[i].setFont(new Font("맑은 고딕", Font.BOLD, 30));
					}
				}
			}
			// 이미 예약되어있는 좌석 제거 코드 시작 끝

			seatC[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int que = jop.showConfirmDialog(null, "C" + (j + 1) + " 좌석을 선택하시겠습니까?", "확인 메시지",
							jop.YES_NO_OPTION);
					if (que == 0) {
						seatC[j].setFont(new Font("Helvetica75", Font.PLAIN, 14));
						seatC[j].setEnabled(false);

						bt1.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								String seatnumber = ("C" + (j + 1));
								MovieDAO.getInstance().seatSelection(seatnumber);
							}
						});
					}
				}
			});

		} // C열 버튼
		for (int i = 0; i < seatD.length; i++) {
			seatD[i] = new JButton("D" + (i + 1));
			p2.add(seatD[i]);
			int j = i;
			seatD[i].setBackground(Color.white);
			seatD[i].setFont(new Font("Helvetica75", Font.PLAIN, 14));
			seatD[i].setForeground(new Color(59, 0, 166));

			// 이미 예약되어있는 좌석 제거 코드 시작
			if (dupSeat.size() != 0) {
				for (int d = 0; d < dupSeat.size(); d++) {
					dup = dupSeat.get(d);
					if ((seatD[i].getText().equals(dup.getSeatNumber()))
							&& (dup.getPlaydate().equals(MovieDAO.Stime))) {
						seatD[i].setEnabled(false); // 버튼을 더이상 못누르게 함
						seatD[i].setText("X"); // 예매된 자리는 X 로 보이게 함
						seatD[i].setFont(new Font("맑은 고딕", Font.BOLD, 30));
					}
				}
			}
			// 이미 예약되어있는 좌석 제거 코드 시작 끝

			seatD[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int que = jop.showConfirmDialog(null, "D" + (j + 1) + " 좌석을 선택하시겠습니까?", "확인 메시지",
							jop.YES_NO_OPTION);
					if (que == 0) {
						seatD[j].setFont(new Font("Helvetica75", Font.PLAIN, 14));
						seatD[j].setEnabled(false);

						bt1.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								String seatnumber = ("D" + (j + 1));
								MovieDAO.getInstance().seatSelection(seatnumber);
							}
						});
					}
				}
			});

		} // D열 버튼
		for (int i = 0; i < seatE.length; i++) {
			seatE[i] = new JButton("E" + (i + 1));
			p2.add(seatE[i]);
			int j = i;
			seatE[i].setBackground(Color.white);
			seatE[i].setFont(new Font("Helvetica75", Font.PLAIN, 14));
			seatE[i].setForeground(new Color(59, 0, 166));
			// 이미 예약되어있는 좌석 제거 코드 시작
			if (dupSeat.size() != 0) {
				for (int d = 0; d < dupSeat.size(); d++) {
					dup = dupSeat.get(d);
					if ((seatE[i].getText().equals(dup.getSeatNumber()))
							&& (dup.getPlaydate().equals(MovieDAO.Stime))) {
						seatE[i].setEnabled(false); // 버튼을 더이상 못누르게 함
						seatE[i].setText("X"); // 예매된 자리는 X 로 보이게 함
						seatE[i].setFont(new Font("맑은 고딕", Font.BOLD, 30));
					}
				}
			}
			// 이미 예약되어있는 좌석 제거 코드 시작 끝

			seatE[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int que = jop.showConfirmDialog(null, "E" + (j + 1) + " 좌석을 선택하시겠습니까?", "확인 메시지",
							jop.YES_NO_OPTION);
					if (que == 0) {
						seatE[j].setBackground(Color.yellow);
						seatE[j].setForeground(Color.white);
						seatE[j].setEnabled(false);

						bt1.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								String seatnumber = ("E" + (j + 1));
								MovieDAO.getInstance().seatSelection(seatnumber);
							}
						});
					}
				}
			});

		} // E열 버튼
		for (int i = 0; i < seatF.length; i++) {
			seatF[i] = new JButton("F" + (i + 1));
			p2.add(seatF[i]);
			int j = i;
			seatF[i].setBackground(Color.white);
			seatF[i].setFont(new Font("Helvetica75", Font.PLAIN, 14));
			seatF[i].setForeground(new Color(59, 0, 166));

			// 이미 예약되어있는 좌석 제거 코드 시작
			if (dupSeat.size() != 0) {
				for (int d = 0; d < dupSeat.size(); d++) {
					dup = dupSeat.get(d);
					if ((seatF[i].getText().equals(dup.getSeatNumber()))
							&& (dup.getPlaydate().equals(MovieDAO.Stime))) {
						seatF[i].setEnabled(false); // 버튼을 더이상 못누르게 함
						seatF[i].setText("X"); // 예매된 자리는 X 로 보이게 함
						seatF[i].setFont(new Font("맑은 고딕", Font.BOLD, 30));
					}
				}
			}
			// 이미 예약되어있는 좌석 제거 코드 시작 끝

			seatF[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int que = jop.showConfirmDialog(null, "F" + (j + 1) + " 좌석을 선택하시겠습니까?", "확인 메시지",
							jop.YES_NO_OPTION);
					if (que == 0) {
						seatF[j].setFont(new Font("Helvetica75", Font.PLAIN, 14));
						seatF[j].setEnabled(false);

						bt1.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								String seatnumber = ("F" + (j + 1));
								MovieDAO.getInstance().seatSelection(seatnumber);
							}
						});
					}
				}
			});

		} // F열 버튼
		lblNewLabel.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\top2.png"));
		lblNewLabel.setBounds(0, 0, 600, 68);

		f.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 600, 600);
		f.getContentPane().add(panel);
		panel.setLayout(null);
		p3.setBounds(50, 475, 500, 79);
		panel.add(p3);
		p3.setBackground(new Color(26, 26, 26));
		bt1.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\ticketbutton.png"));

		// 예약, 취소, 초기화버튼 (p3) 세팅//
		bt1.setFont(font2);
		bt1.setPreferredSize(new Dimension(150, 69));
		bt1.setForeground(Color.DARK_GRAY);
		bt1.setBackground(Color.black);
		bt1.addMouseListener(this);
		bt3.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\resetbutton.png"));

		bt3.setFont(font2);
		bt3.setPreferredSize(new Dimension(150, 69));
		bt3.setForeground(Color.DARK_GRAY);
		bt3.setBackground(Color.black);
		bt3.addMouseListener(this);
		p3.add(bt3);

		bt1.setBorderPainted(false);
		bt3.setBorderPainted(false);

		bt1.setFocusPainted(false);
		bt3.setFocusPainted(false);

		bt3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int que = jop.showConfirmDialog(null, "예매를 다시 하시겠습니까?", "메시지", jop.YES_NO_OPTION);
				if (que == 0) {
					jop.showMessageDialog(null, "예매 정보를 초기화합니다.");
					// MovieDAO.getInstance().DeleteTi(MLoginFrame.uid, MovieDAO.Stime);
					f.dispose();
					new GUI();
				}
			}
		});

		p3.add(bt1); // 예매

		p1.setBounds(68, 113, 470, 46);
		panel.add(p1);
		p1.setBorder(null);

		p1.setBackground(new Color(160, 162, 162));
		p1.setLayout(null);

		JLabel screen = new JLabel("Screen", SwingConstants.LEFT);
		screen.setBounds(146, -7, 170, 60);
		screen.setBackground(new Color(106, 106, 106));
		screen.setForeground(Color.GRAY);
		screen.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\seatscreen.png"));
		p1.add(screen, BorderLayout.CENTER); // Borderlayout으로 가운데 배치
		screen.setFont(font1); // 스크린 폰트 설정

		p1.add(screen);
		p2.setBounds(75, 171, 485, 263);
		panel.add(p2);
		p2.setBackground(new Color(26, 26, 26));
		p2.setLayout(new GridLayout(6, 9, 2, 5));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\seat_text2.png"));
		lblNewLabel.setBounds(55, 196, 11, 223);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\seat_text.png"));
		lblNewLabel_2.setBounds(85, 439, 240, 17);
		panel.add(lblNewLabel_2);
		bt2.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\seat_backbutton.png"));
		bt2.setFocusPainted(false);
		bt2.setBorderPainted(false);
		bt2.setBounds(12, 62, 40, 40);
		panel.add(bt2);
		
				bt2.setFont(font2);
				bt2.setPreferredSize(new Dimension(130, 50));
				bt2.setForeground(Color.DARK_GRAY);
				bt2.setBackground(Color.black);
				bt2.setBorderPainted(false);
				bt2.setFocusPainted(false);
				
				JLabel lblNewLabel_3 = new JLabel("New label");
				lblNewLabel_3.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\seat_background.png"));
				lblNewLabel_3.setBounds(0, 0, 600, 600);
				panel.add(lblNewLabel_3);
				bt2.addMouseListener(this);
				
						bt2.addActionListener(new ActionListener() {
				
							@Override
							public void actionPerformed(ActionEvent e) {
								int que = jop.showConfirmDialog(null, "예매를 취소하시겠습니까?", "메시지", JOptionPane.YES_NO_OPTION);
								if (que == 0) {
									f.dispose();
									new Ticketing();
								}
							}
						});

		// 버튼에 이벤트 부여//
		bt1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jop.showMessageDialog(null, "감사합니다. 예매가 완료되었습니다.");
				f.dispose();
				new AfterMenu();
			}
		});
		f.setVisible(true); // 화면에 출력
	} // End of Class Seat()

	// 마우스 이벤트//
	@Override
	public void mouseClicked(MouseEvent e) {
		// JButton b = (JButton)e.getSource();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JButton b = (JButton) e.getSource();
		b.setBackground(Color.red); // 마우스 들어오면 빨간색으로 전환
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JButton b = (JButton) e.getSource();
		b.setBackground(Color.black); // 마우스 빠져나가면 검은색으로 전환
	}
}

//Main
public class Seat extends JFrame {
	public Seat() {
	}

	public static void main(String[] args) {
		GUI panel = new GUI();
	}
}
