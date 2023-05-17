package MovieTicketing.viewer;

import MovieTicketing.dao.MovieDAO;


public class Ticketing {

	public Ticketing() {
		
		MovieDAO.getInstance().movieSelect();
	}
}
