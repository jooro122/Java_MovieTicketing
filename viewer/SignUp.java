package MovieTicketing.viewer;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MovieTicketing.dao.ClientDAO;
import MovieTicketing.model.ClientDTO;

public class SignUp extends JFrame {// 회원가입 화면

	public SignUp() {
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);

		getContentPane().add(p);
		p.setLayout(null);

		JLabel lblNewLabel = new JLabel(""); // 상단바 이미지
		lblNewLabel.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\top2.png"));
		lblNewLabel.setBounds(0, 0, 600, 68);
		p.add(lblNewLabel);
		JTextField t1 = new JTextField();
		p.add(t1);

		t1.setBounds(271, 228, 124, 20);
		TextField t2 = new TextField();
		p.add(t2);
		t2.setEchoChar('*');
		t2.setBounds(274, 273, 118, 20);
		JTextField t3 = new JTextField();
		p.add(t3);
		t3.setBounds(271, 311, 89, 20);
		JTextField t4 = new JTextField();
		p.add(t4);
		t4.setText("ex)19930530");
		t4.setBounds(271, 349, 100, 20);
		JTextField t5 = new JTextField();
		p.add(t5);
		t5.setText("ex)01055667788");
		t5.setBounds(271, 387, 124, 20);
		JButton j1 = new JButton("회원가입");
		j1.setBackground(new Color(48, 28, 77));
		p.add(j1);

		j1.setBounds(209, 455, 179, 30);
		JButton j2 = new JButton("");
		j2.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\backbutton.png"));
		j2.setFocusPainted(false);
		j2.setBorderPainted(false);
		p.add(j2);
		j2.setBounds(10, 62, 40, 40);

		JLabel lblNewLabel_1 = new JLabel("아이디");
		lblNewLabel_1.setBounds(209, 228, 62, 16);
		p.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("비밀번호");
		lblNewLabel_2.setBounds(209, 273, 62, 16);
		p.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("이름");
		lblNewLabel_3.setBounds(209, 313, 62, 16);
		p.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("생년월일");
		lblNewLabel_4.setBounds(209, 351, 62, 16);
		p.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("전화번호");
		lblNewLabel_5.setBounds(209, 389, 62, 16);
		p.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\signuptitle.png"));
		lblNewLabel_6.setBounds(100, 143, 400, 30);
		p.add(lblNewLabel_6);

		j2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

		j1.addActionListener(new ActionListener() {// 가입 버튼 누를 시
			@Override
			public void actionPerformed(ActionEvent e) {// 회원가입 데이터 저장

				try {
					ClientDTO client = new ClientDTO();
					client.setClientId(t1.getText());
					client.setClientPassword(t2.getText());
					client.setClientName(t3.getText());
					client.setBirthdate(Integer.parseInt(t4.getText()));
					client.setPhonenumber(t5.getText());

					if (t1.getText().length() >= 8 && t1.getText().length() <= 12) {
		                  if ((t1.getText()).matches(".*[0-9].*")) {
		                     ClientDAO.getInstance().signUp(client);
		                     
		                     dispose();
		                  } else {
		                     JOptionPane.showMessageDialog(null, "ID중에는 반드시 숫자가 포함되어 있어야 합니다.");
		                  }
		               } else {
		                  JOptionPane.showMessageDialog(null, "아이디를 8 ~ 12자 사이로 입력해주세요.");// 아이디 생성시 문자열 8 ~ 12자 검사
		               }

		            } catch (NumberFormatException n) {
		               JOptionPane.showMessageDialog(null, "생일과 전화번호에 숫자만 입력해 주세요.");
		            } catch (Exception e2) {
		               JOptionPane.showMessageDialog(null, "회원가입에 문제 발생");
		            }
		         }
		      });

		setSize(600, 600);
		setTitle("회원가입");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
