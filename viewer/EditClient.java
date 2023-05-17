package MovieTicketing.viewer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class EditClient extends JFrame{
	private final JLabel lblNewLabel = new JLabel("");

	public EditClient() {
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		
		
		getContentPane().add(p, BorderLayout.CENTER);
		p.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\add.png"));
		lblNewLabel_1.setBounds(25, 445, 550, 101);
		p.add(lblNewLabel_1);
		JButton j1 = new JButton("");
		j1.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\editbutton.png"));
		j1.setBounds(154, 236, 150, 97);
		p.add(j1);
		JButton j2 = new JButton("");
		j2.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\exitbutton.png"));
		j2.setBounds(303, 236, 150, 97);
		p.add(j2);
		lblNewLabel.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\top2.png"));
		lblNewLabel.setBounds(0, 0, 600, 68);
		p.add(lblNewLabel);
		
		
		j1.setBorderPainted(false);
		j2.setBorderPainted(false);

		j1.setFocusPainted(false);
		j2.setFocusPainted(false);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\backbutton.png"));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnNewButton.hasFocus()) {
					dispose();
					new AfterMenu();
				}
			}
		});
		btnNewButton.setBounds(12, 52, 40, 40);
		p.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("./src\\MovieTicketing\\imagefile\\textmypage.png"));
		lblNewLabel_2.setBounds(136, 52, 328, 40);
		p.add(lblNewLabel_2);
		
		
		j2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MDelete();
				
			}
		});
		
		j1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new ChangePw();
			}
		});
		setSize(600, 600);
		setTitle("마이페이지");
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
