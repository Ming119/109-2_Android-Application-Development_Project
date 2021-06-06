package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DB;

@WebServlet("/CreateItem")
public class CreateItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String desc  = request.getParameter("desc");
		int price    = Integer.parseInt(request.getParameter("price"));
		String uid   = request.getParameter("uid");
		
		DB db = new DB();
		if(db.CreateItem(title, desc, price, uid))
			response.setStatus(200);
		else
			response.setStatus(400);
	}

}
