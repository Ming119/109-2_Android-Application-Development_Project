package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DB;

@WebServlet("/GetChatRecord")
public class GetChatRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetChatRecord() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		
		DB db = new DB();
		ResultSet rs = null;
		
		if (uid == null) 
			rs = db.ReadChatRecords();
		else 
			rs = db.ReadChatRecord(uid);
		
		String res = "{\"chatRecord\":[";
		try {
			while (rs.next()) {
				res += "{";
				res += "\"crid\":\"" + rs.getString("crid") + "\",";
				res += "\"fromUID\":\"" + rs.getString("fromUID") + "\",";
				res += "\"toUID\":\"" + rs.getString("toUID") + "\"";
				res += "\"message\":\"" + rs.getString("message") + "\"";
				res += "}";
				if (!rs.isLast())
					res += ",";
			}
			
			res += "]}";
			
		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("VendorError: " + e.getErrorCode());
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(res);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
