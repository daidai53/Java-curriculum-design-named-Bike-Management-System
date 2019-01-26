package userVerify;

import java.sql.ResultSet;
import java.sql.SQLException;

import sql.sqlConnect;

public class userVerify {
	
	static String staffID;
	static String option;
	public userVerify(){
	}
	
	public static boolean loginCheck(String staff,String password) throws ClassNotFoundException, SQLException{
		staffID=staff;
		 sqlConnect sqlConnect=new sqlConnect();
		 String sql="select * from staff where staff='"+staff+"' and password='"+password+"'";
		 ResultSet rSet = null;
		try {
			rSet = sqlConnect.getResultSet(sql);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		 try {
			 int count=0;
			 while(rSet.next()){
				 count++;
			 }
			if(count==0){
				 return false;
			 }else{
				 return true;
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return false;
	}
	public static String getCurrentStaff(){
		return staffID;
	}
}
