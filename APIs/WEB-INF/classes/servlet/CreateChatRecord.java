package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DB;

@WebServlet("/CreateChatRecord")
public class CreateChatRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateChatRecord() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String from = request.getParameter("fromUID");
		String to   = request.getParameter("toUID");
		String msg  = request.getParameter("message");
//		int    id   = Integer.parseInt(request.getParameter("id"));
		
		DB db = new DB();
//		if(db.CreateChatRecord(from, to, msg, id))
		if(db.CreateChatRecord(from, to, msg))
			response.setStatus(200);
		else
			response.setStatus(400);
	}

}
