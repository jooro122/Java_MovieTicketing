package MovieTicketing.viewer;

import MovieTicketing.dao.ClientDAO;

public class MStart {
	ClientDAO db = null;
	MLoginFrame mf = null;
	
	public static void main(String[] args) {
		new IntroGif();
		
		
		MStart opt = new MStart();
		opt.db = new ClientDAO();
		opt.mf = new MLoginFrame(opt);
		
		
		
	}
}
 