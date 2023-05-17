package MovieTicketing.model;

import java.sql.Timestamp;

public class SlamDTO {
	
	private String slamCode;
	private String slamTitle;
	private String slamTheater;
	private Timestamp slamDate;
	private int slamRunTime;
	
	public String getSlamTheater() {
		return slamTheater;
	}
	public void setSlamTheater(String slamTheater) {
		this.slamTheater = slamTheater;
	}

	
	public String getSlamCode() {
		return slamCode;
	}
	public void setSlamCode(String slamCode) {
		this.slamCode = slamCode;
	}
	public String getSlamTitle() {
		return slamTitle;
	}
	public void setSlamTitle(String slamTitle) {
		this.slamTitle = slamTitle;
	}
	public Timestamp getSlamDate() {
		return slamDate;
	}
	public void setSlamDate(Timestamp slamDate) {
		this.slamDate = slamDate;
	}
	public int getSlamRunTime() {
		return slamRunTime;
	}
	public void setSlamRunTime(int slamRunTime) {
		this.slamRunTime = slamRunTime;
	}
	

}