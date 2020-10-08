package model;

public class basicStudent {
	private String sno;
	private String sname;
	private java.sql.Date sbday;
	private String ssex;
	private String smail;
	private String spwd;
	private String Sid;
	private int Sage;

	public basicStudent() {
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public java.sql.Date getSbday() {
		return sbday;
	}

	public void setSbday(java.sql.Date sbday) {
		this.sbday = sbday;
	}

	public String getSsex() {
		return ssex;
	}

	public void setSsex(String ssex) {
		this.ssex = ssex;
	}

	public String getSmail() {
		return smail;
	}

	public void setSmail(String smail) {
		this.smail = smail;
	}

	public String getSpwd() {
		return spwd;
	}

	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}

	public String getSid() {
		return Sid;
	}

	public void setSid(String sid) {
		Sid = sid;
	}

	public int getSage() {
		return Sage;
	}

	public void setSage(int sage) {
		Sage = sage;
	}
}
