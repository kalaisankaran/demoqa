package com.bluemeric.main;

public class Report {
	private int status;
	private static Report report;

	public static Report newInstance() {
		if (report == null) {
			report = new Report();
			report.status = 1;
			return report;
		}
		return report;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
