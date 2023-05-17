package MovieTicketing.viewer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MovieTicketing.dao.ClientDAO;


@SuppressWarnings("serial")
public class AfterMenu extends JFrame

{

	public AfterMenu() {
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton1 = new JButton("");
		btnNewButton1.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\button_ticket.png"));
		btnNewButton1.setBounds(135, 108, 160, 160);
		panel.add(btnNewButton1);
		
		JButton btnNewButton2 = new JButton("");
		btnNewButton2.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\button_my.png"));
		btnNewButton2.setBounds(135, 277, 160, 160);
		panel.add(btnNewButton2);
		
		JButton btnNewButton3 = new JButton("");
		btnNewButton3.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\button_ticket2.png"));
		btnNewButton3.setBounds(304, 108, 160, 160);
		panel.add(btnNewButton3);
		
		JButton btnNewButton4 = new JButton("");
		btnNewButton4.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\button_logout.png"));
		btnNewButton4.setBounds(304, 277, 160, 160);
		panel.add(btnNewButton4);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\top2.png"));
		lblNewLabel.setBounds(0, 0, 600, 68);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\cs.png"));
		lblNewLabel_2.setBounds(153, 480, 293, 86);
		panel.add(lblNewLabel_2);
		
		

		setSize(600, 600);
		setTitle("BMOVIE");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		btnNewButton1.setBorderPainted(false);
		btnNewButton2.setBorderPainted(false);
		btnNewButton3.setBorderPainted(false);
		btnNewButton4.setBorderPainted(false);

		btnNewButton1.setFocusPainted(false);
		btnNewButton2.setFocusPainted(false);
		btnNewButton3.setFocusPainted(false);
		btnNewButton4.setFocusPainted(false);
		
		
		// 컴포넌트 생성 및 추가하기

		btnNewButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Ticketing();
			}
			// 영화예매 버튼 이벤트
		});
		btnNewButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new EditClient();
				// 마이페이지(회원정보, 회원탈퇴) 버튼 이벤트
			}
		});
		btnNewButton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MyTicket();
			}
			// 영화예매조회 버튼 이벤트
		});
		btnNewButton4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				MStart opt = new MStart();
				opt.db = new ClientDAO();
				opt.mf = new MLoginFrame(opt);
			}
			// 로그아웃 버튼 이벤트
		});

		
		



		




	}
}