package Utilities;


import java.util.Date;


public class Utilities {
	public static String email;
	public static Date date;
	public static final int IMPLICIT_WAIT = 100;
	public static final int PAGELOAD_TIME= 100;
	public static final int SCRIPTLOAD_TIME = 100;
	
	public static String getEmail() {
		
	
		date = new Date();
		
		String email = date.toString().replaceAll(" ","_").replaceAll(":","_");	
		
		return email;
		
	}
}
