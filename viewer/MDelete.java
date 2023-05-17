package MovieTicketing.viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import MovieTicketing.dao.ClientDAO;

public class MDelete extends JFrame {

	JFrame j = new JFrame();
	static Scanner sc = new Scanner(System.in);

	JPanel basePanel = new JPanel();

	/* Label */
	JLabel idL = new JLabel("아이디");
	JLabel pwL = new JLabel("비밀번호");
	

	/* TextField */
	JTextField id = new JTextField();
	JPasswordField pw = new JPasswordField();
	
	

	/* Button */
	JButton deleteBtn = new JButton("탈퇴하기");
	private final JPanel panel = new JPanel();
	private final JButton btnNewButton = new JButton("");
	private final JLabel lblNewLabel = new JLabel("");
	private final JLabel lblNewLabel_1 = new JLabel("");
	private final JLabel lblNewLabel_2 = new JLabel("");
	

	MDelete() {

		setTitle("회원탈퇴");

		

		setContentPane(basePanel); // panel을 기본 컨테이너로 설정
		basePanel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 600, 572);
		
		basePanel.add(panel);
		panel.setLayout(null);
		deleteBtn.setBounds(203, 298, 195, 30);
		panel.add(deleteBtn);
		deleteBtn.setPreferredSize(new Dimension(75, 63));
		idL.setBounds(203, 198, 50, 30);
		panel.add(idL);
		idL.setPreferredSize(new Dimension(50, 30));
		id.setEnabled(false);
		id.setBounds(258, 198, 140, 30);
		panel.add(id);
		id.setPreferredSize(new Dimension(140, 30));
		pwL.setBounds(203, 240, 50, 30);
		panel.add(pwL);
		pwL.setPreferredSize(new Dimension(50, 30));
		pw.setBounds(258, 240, 140, 30);
		panel.add(pw);
		pw.setPreferredSize(new Dimension(140, 30));
		
		id.setText(MLoginFrame.uid);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(btnNewButton.hasFocus()) {
					dispose();
					new EditClient();
					
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\backbutton.png"));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(12, 52, 40, 40);
		
		panel.add(btnNewButton);
		lblNewLabel.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\top2.png"));
		lblNewLabel.setBounds(0, 0, 600, 68);
		
		panel.add(lblNewLabel);
		lblNewLabel_1.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\add2.png"));
		lblNewLabel_1.setBounds(25, 445, 550, 101);
		
		panel.add(lblNewLabel_1);
		lblNewLabel_2.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\textexit.png"));
		lblNewLabel_2.setBounds(136, 52, 328, 40);
		
		panel.add(lblNewLabel_2);
		


		/* Button 이벤트 리스너 추가 */
		ButtonListener bl = new ButtonListener();
		deleteBtn.addActionListener(bl);

		setSize(600, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}

	/* Button 이벤트 리스너 */
	class ButtonListener implements ActionListener {
		private static final Object YES_OPTION = null;

		@Override
		public void actionPerformed(ActionEvent e) {

			/* TextField에 입력된 아이디와 비밀번호를 변수에 초기화 */
			String uid = id.getText();
			String upass = "";
			for (int i = 0; i < pw.getPassword().length; i++) {
				upass = upass + pw.getPassword()[i];
			}


			/* 탈퇴하기 버튼 이벤트 */
			if (deleteBtn.hasFocus()) {
				if (ClientDAO.getInstance().isMember(uid) == 0) {
						JOptionPane.showMessageDialog(null, "존재하지 않는 ID 입니다.	", "탈퇴 실패", JOptionPane.ERROR_MESSAGE);
				} else {
					if (ClientDAO.getInstance().checkPassword(uid, upass)) {
						int i = JOptionPane.showConfirmDialog(null, "회원탈퇴를 하시겠습니까?","회원탈퇴", JOptionPane.YES_NO_OPTION);
						if(i == JOptionPane.YES_OPTION) {
						if (ClientDAO.getInstance().MDelete(uid) == 1) {
							
								JOptionPane.showMessageDialog(null, uid + "님 탈퇴가 정상적으로 처리되었습니다.\n그동안 이용해주셔서 감사합니다.");
								dispose();
								MStart opt = new MStart();
								opt.db = new ClientDAO();
								opt.mf = new MLoginFrame(opt);
							}
							
						}
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호가 맞지않습니다.");
					}
				}

			}
		}
	}
}