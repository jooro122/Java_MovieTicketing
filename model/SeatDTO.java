package MovieTicketing.model;

import java.sql.Date;
import java.sql.Timestamp;

public class SeatDTO {
	
	
	private String ID;
	private String MovieName;
	private String SeatNumber;
	private String Theater;
	private Timestamp Playdate;
	private String Tcode;
	
	public String getTcode() {
		return Tcode;
	}
	public void setTcode(String tcode) {
		Tcode = tcode;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getMovieName() {
		return MovieName;
	}
	public void setMovieName(String movieName) {
		MovieName = movieName;
	}
	public String getSeatNumber() {
		return SeatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		SeatNumber = seatNumber;
	}
	public String getTheater() {
		return Theater;
	}
	public void setTheater(String theater) {
		Theater = theater;
	}
	public Timestamp getPlaydate() {
		return Playdate;
	}
	public void setPlaydate(Timestamp playdate) {
		Playdate = playdate;
	}
	
	
	
	

}
