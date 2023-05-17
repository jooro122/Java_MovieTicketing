package MovieTicketing.model;

import java.awt.event.ActionListener;
import java.sql.Timestamp;

public class AvataDTO {
	
	private String avataCode;
	private String avataTitle;
	private String avataTheater;
	private Timestamp avataDate;
	private int avataRunTime;
	
	
	public String getAvataTheater() {
		return avataTheater;
	}
	public void setAvatatheater(String avataTheater) {
		this.avataTheater = avataTheater;
	}
	
	public String getAvataCode() {
		return avataCode;
	}
	public void setAvataCode(String avataCode) {
		this.avataCode = avataCode;
	}
	public int getAvataRunTime() {
		return avataRunTime;
	}
	public void setAvataRunTime(int avataRunTime) {
		this.avataRunTime = avataRunTime;
	}

	public String getAvataTitle() {
		return avataTitle;
	}
	public void setAvataTitle(String avataTitle) {
		this.avataTitle = avataTitle;
	}
	public Timestamp getAvataDate() {
		return avataDate;
	}
	public void setAvataDate(Timestamp avataDate) {
		this.avataDate = avataDate;
	}
	public void addAncestorListener(ActionListener actionListener) {
		// TODO Auto-generated method stub
		
	}
	

}
