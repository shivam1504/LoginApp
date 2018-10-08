package com.netlink.login;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Login extends HttpServlet{

	public void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException
	{
		Connection con;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "shivam", "password");
			PreparedStatement ps=con.prepareStatement("select * from LOGIN where usrnm=? and pwd=?");
			ps.setString(1,req.getParameter("usrnm"));
			ps.setString(2,req.getParameter("pwd"));
			ResultSet rs=ps.executeQuery();
			
			PrintWriter pw=resp.getWriter();
			resp.setContentType("text/html");
			if(rs.next())
			{
				pw.println("<html>");
				pw.println("<head><title>Welcome User</title></head>");
				pw.println("<body>");
				pw.println("<h1 style='color:green;'>Welcome "+rs.getString("name")+" to our website</h1>");
				pw.println("</body>");
				pw.println("</html>");
			}
			else
			{
				pw.println("<html>");
				pw.println("<head><title>Login Failed</title></head>");
				pw.println("<body>");
				pw.println("<h1 style='color:red;'>Incorrect username or password</h1>");
				pw.println("</body>");
				pw.println("</html>");
			}
			 }
			 
		 catch(Exception e){
			 e.printStackTrace();
		 }

	}

}
