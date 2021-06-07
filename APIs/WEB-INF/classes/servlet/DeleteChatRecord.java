package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DB;

@WebServlet("/DeleteChatRecord")
public class DeleteChatRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteChatRecord() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int crid = Integer.parseInt(request.getParameter("crid"));

		DB db = new DB();
		if(db.DeleteChatRecord(crid))
			response.setStatus(200);
		else
			response.setStatus(400);
	}

}
