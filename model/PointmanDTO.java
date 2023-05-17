package MovieTicketing.model;

import java.sql.Timestamp;

public class PointmanDTO {
	
	private String pointmanCode;
	private String pointmanTitle;
	private String pointmanTheater;
	private Timestamp pointmanDate;
	private int pointmanRunTime;
	
	public String getPointmanCode() {
		return pointmanCode;
	}
	public void setPointmanCode(String pointmanCode) {
		this.pointmanCode = pointmanCode;
	}
	public String getPointmanTitle() {
		return pointmanTitle;
	}
	public void setPointmanTitle(String pointmanTitle) {
		this.pointmanTitle = pointmanTitle;
	}
	public String getPointmanTheater() {
		return pointmanTheater;
	}
	public void setPointmanTheater(String pointmanTheater) {
		this.pointmanTheater = pointmanTheater;
	}
	public Timestamp getPointmanDate() {
		return pointmanDate;
	}
	public void setPointmanDate(Timestamp pointmanDate) {
		this.pointmanDate = pointmanDate;
	}
	public int getPointmanRunTime() {
		return pointmanRunTime;
	}
	public void setPointmanRunTime(int pointmanRunTime) {
		this.pointmanRunTime = pointmanRunTime;
	}

	
}
