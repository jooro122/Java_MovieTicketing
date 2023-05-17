package MovieTicketing.viewer;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.DropMode;

public class MLoginFrame extends JFrame {
	
	public static String uid = null;
	private Image background=new ImageIcon(MLoginFrame.class.getResource("..")).getImage();
	
	/* Panel */
	JPanel basePanel = new JPanel();
	
	/* Label */
	JLabel idL = new JLabel("아이디");
	JLabel pwL = new JLabel("비밀번호");
	
	/* TextField */
	JTextField id = new JTextField();
	JPasswordField pw = new JPasswordField();
	
	/* Button */
	JButton loginBtn = new JButton("LOGIN");
	JButton joinBtn = new JButton("회원가입");
	JButton exitBtn = new JButton("프로그램 종료");
	ButtonListener bl = new ButtonListener();
	
	MStart o = null;
	private final JLabel lblNewLabel = new JLabel("");

	
	
	MLoginFrame(MStart _o){
		o = _o;
		
		setTitle("로그인");
		
		/* Panel 추가 작업 */
		setContentPane(basePanel);	//panel을 기본 컨테이너로 설정
		basePanel.setLayout(null);
		id.setForeground(Color.DARK_GRAY);
		id.setBackground(Color.WHITE);
		id.setBounds(209, 421, 140, 30);
		basePanel.add(id);
		id.setPreferredSize(new Dimension(140, 30));
		pw.setToolTipText("");
		pw.setBounds(209, 453, 140, 30);
		basePanel.add(pw);
		pw.setPreferredSize(new Dimension(140, 30));
		idL.setBackground(Color.LIGHT_GRAY);
		idL.setForeground(Color.WHITE);
		idL.setBounds(166, 420, 50, 30);
		basePanel.add(idL);
		idL.setPreferredSize(new Dimension(50, 30));
		pwL.setBackground(Color.LIGHT_GRAY);
		pwL.setForeground(Color.WHITE);
		pwL.setBounds(163, 453, 50, 30);
		basePanel.add(pwL);
		pwL.setPreferredSize(new Dimension(50, 30));
		loginBtn.setFont(new Font("Helvetica73-Extended", Font.PLAIN, 13));
		loginBtn.setBackground(Color.WHITE);
		loginBtn.setForeground(new Color(40, 0, 114));
		loginBtn.setBounds(354, 421, 81, 63);
		basePanel.add(loginBtn);
		loginBtn.setPreferredSize(new Dimension(75, 63));
		
		loginBtn.addActionListener(bl);
		joinBtn.setForeground(Color.DARK_GRAY);
		joinBtn.setBounds(153, 499, 135, 25);
		basePanel.add(joinBtn);
		joinBtn.setPreferredSize(new Dimension(135, 25));
		exitBtn.setForeground(Color.DARK_GRAY);
		exitBtn.setBounds(300, 500, 135, 25);
		basePanel.add(exitBtn);
		exitBtn.setPreferredSize(new Dimension(135, 25));
		
		exitBtn.addActionListener(bl);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\newposterintro.gif"));
		lblNewLabel_1.setBounds(0, 87, 600, 200);
		basePanel.add(lblNewLabel_1);
		lblNewLabel.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\javamain2.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(0, 0, 600, 572);
		
		basePanel.add(lblNewLabel);
		joinBtn.addActionListener(bl);
		
		/* Button 이벤트 리스너 추가 */
		
		setSize(600, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/* Button 이벤트 리스너 */
	class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			
			/* TextField에 입력된 아이디와 비밀번호를 변수에 초기화 */
			uid = id.getText();
			String upass = "";
			for(int i=0; i<pw.getPassword().length; i++) {
				upass = upass + pw.getPassword()[i];
			}
			
			/* 게임종료 버튼 이벤트 */
			if(exitBtn.hasFocus()) {
				int result = JOptionPane.showConfirmDialog(null, "프로그램을 종료 하시겠습니까?", "프로그램 종료", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
			
			/* 회원가입 버튼 이벤트 */
			else if(joinBtn.hasFocus()) {
				new SignUp();
			}
			
			/* 로그인 버튼 이벤트 */
			else if(loginBtn.hasFocus()) {
				if(uid.equals("") || upass.equals("")) {
					JOptionPane.showMessageDialog(null, "아이디와 비밀번호 모두 입력해주세요", "로그인 실패", JOptionPane.ERROR_MESSAGE);
				}
				
				else if(uid != null && upass != null) {
					if(o.db.logincheck(uid, upass)) {	//이 부분이 데이터베이스에 접속해 로그인 정보를 확인하는 부분이다.
						dispose();
						new AfterMenu();
					} else {
						System.out.println("로그인 실패 > 로그인 정보 불일치");
						JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다" + "\n"
						+ "아이디와 비밀번호를 확인해주세요.");
					}
				}
			}
		}
	}
}

