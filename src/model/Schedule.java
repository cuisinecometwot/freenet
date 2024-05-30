package model;

import java.sql.Date;

public class Schedule {
	  private Date day;
	  private String shift;
	  private String username;

	  public Schedule() {
	  }

	  public Schedule(Date day, String shift, String username) {
	    this.day = day;
	    this.shift = shift;
	    this.username = username;
	  }

	  // Getters and Setters
	  public Date getDay() {
	    return day;
	  }

	  public void setDay(Date day) {
	    this.day = day;
	  }

	  public String getShift() {
	    return shift;
	  }

	  public void setShift(String shift) {
	    this.shift = shift;
	  }

	  public String getUsername() {
	    return username;
	  }

	  public void setUsername(String username) {
	    this.username = username;
	  }

	  // Override toString() for better printing
	  @Override
	  public String toString() {
	    return "Schedule{" +
	            "day=" + day +
	            ", shift='" + shift + '\'' +
	            ", username='" + username + '\'' +
	            '}';
	  }
}
