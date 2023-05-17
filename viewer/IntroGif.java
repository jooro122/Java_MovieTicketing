package MovieTicketing.viewer;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IntroGif extends JFrame{ //인트로 화면
	
	public IntroGif() {
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		
		getContentPane().add(p);
		p.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(""); //인트로 gif	
		lblNewLabel.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\intro_gif\\bmovieIntro.gif"));
		lblNewLabel.setBounds(0, -15, 600, 600); //프레임창때문에 위로 -15	
		p.add(lblNewLabel);
		
		setSize(600, 600); //사이즈
		setTitle("BMOVIE"); //타이틀
		setLocationRelativeTo(null); //프레임창 중앙정렬
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		try{

			Thread.sleep(6500); //인트로화면 6초간 보여주고 창닫기 
		}catch(InterruptedException ie){

			System.out.println(ie.getMessage());

		}

		setVisible(false);	//창닫기
	}
	
	public static void main(String[] args) {
		new IntroGif(); //테스트
		
	}
}
