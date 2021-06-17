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


@WebServlet("/GetItem")
public class GetItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		DB db = new DB();
		
		ResultSet rs = null;
		
		if (id == null) 
			rs = db.ReadItems();
		else 
			rs = db.ReadItem(Integer.parseInt(id));

		String res = "{\"items\":[";
		try {
			while (rs.next()) {
				res += "{";
				res += "\"id\":" + rs.getInt("id") + ",";
				res += "\"title\":\"" + rs.getString("title") + "\",";
				res += "\"description\":\"" + rs.getString("description") + "\",";
				res += "\"price\":" + rs.getInt("price") + ",";
				res += "\"uid\":\"" + rs.getString("seller") + "\",";
				res += "\"sold\":" + rs.getBoolean("sold");
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
