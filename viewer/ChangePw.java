package MovieTicketing.viewer;

import java.awt.Color;




import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import MovieTicketing.dao.ClientDAO;

public class ChangePw extends JFrame {

	public ChangePw() {
		getContentPane().setBackground(Color.WHITE);

		Label l1 = new Label("아이디");
		l1.setAlignment(Label.RIGHT);
		l1.setBounds(190, 198, 50, 30);
		Label l2 = new Label("비밀번호");
		l2.setAlignment(Label.RIGHT);
		l2.setBounds(157, 240, 83, 30);
		getContentPane().setLayout(null);
		getContentPane().add(l1);
		getContentPane().add(l2);

		TextField t1 = new TextField();
		t1.setEnabled(false);
		t1.setText(MLoginFrame.uid);
		t1.setBounds(258, 198, 140, 30);
		TextField t2 = new TextField();
		t2.setBounds(258, 240, 140, 30);
		getContentPane().add(t1);
		getContentPane().add(t2);
		t2.setEchoChar('*');

		JButton j1 = new JButton("확인");
		j1.setBounds(203, 298, 195, 30);
		getContentPane().add(j1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\top2.png"));
		lblNewLabel.setBounds(0, 0, 600, 68);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\idtitle.png"));
		lblNewLabel_2.setBounds(136, 52, 328, 40);
		getContentPane().add(lblNewLabel_2);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton.hasFocus()) {
					new EditClient();
					dispose();
				}

			}
		});
		btnNewButton.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\backbutton.png"));
		btnNewButton.setBounds(12, 52, 40, 40);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		getContentPane().add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\add4.png"));
		lblNewLabel_1.setBounds(25, 445, 550, 101);
		getContentPane().add(lblNewLabel_1);
		setSize(600, 600);
		setTitle("비밀번호 변경");
		setLocationRelativeTo(null);
		setVisible(true);

		j1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String id = t1.getText();
				String password = t2.getText();

				if (ClientDAO.getInstance().isMember(id) == 0) {
					JOptionPane.showMessageDialog(null, "존재하지 않는 ID 입니다.");
				} else {
					ClientDAO.getInstance().checkPassword(id, password);
					if (ClientDAO.getInstance().checkPassword(id, password) == true) {
						JOptionPane.showMessageDialog(null, "입력하신 아이디와 비밀번호가 확인 되었습니다.");
						new ChangePw2();
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "틀린 비밀번호 입니다.");
					}
				}

			}

		});

	}
}
