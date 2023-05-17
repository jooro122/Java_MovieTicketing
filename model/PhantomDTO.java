package MovieTicketing.model;

import java.sql.Timestamp;

public class PhantomDTO {
	
	private String phantomCode;
	private String phantomTitle;
	private String phantomTheater;
	private Timestamp phantomDate;
	private int phantomRunTime;
	
	public String getPhantomCode() {
		return phantomCode;
	}
	public void setPhantomCode(String phantomCode) {
		this.phantomCode = phantomCode;
	}
	public String getPhantomTitle() {
		return phantomTitle;
	}
	public void setPhantomTitle(String phantomTitle) {
		this.phantomTitle = phantomTitle;
	}
	public String getPhantomTheater() {
		return phantomTheater;
	}
	public void setPhantomTheater(String phantomTheater) {
		this.phantomTheater = phantomTheater;
	}
	public Timestamp getPhantomDate() {
		return phantomDate;
	}
	public void setPhantomDate(Timestamp phantomDate) {
		this.phantomDate = phantomDate;
	}
	public int getPhantomRunTime() {
		return phantomRunTime;
	}
	public void setPhantomRunTime(int phantomRunTime) {
		this.phantomRunTime = phantomRunTime;
	}

}
