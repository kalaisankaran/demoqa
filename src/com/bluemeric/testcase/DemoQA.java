package com.bluemeric.testcase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bluemeric.main.Report;

@Listeners(org.uncommons.reportng.HTMLReporter.class)
public class DemoQA {
	@Test
	@Parameters({ "GET_URL"})
	public void testGET(String GET_URL) throws Exception {
		Report report = Report.newInstance();
		if (GET_URL.equals("")){
			Reporter.log("================ URL is Empty, Please check the access URL =================== ");	
		}
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		//con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code -> " + responseCode);
		System.out.println("==========Page Content==========");
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
			con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());
			Reporter.log("Content of the web page");
			Reporter.log("-----------------------");
			Reporter.log(response.toString());	
			report.setStatus(0); // Set abnormal termination 0
		} else {
				System.out.println("GET request not working");
		}
	}
}
