package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DB;

@WebServlet("/UpdateItem")
public class UpdateItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id       = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String desc  = request.getParameter("desc");
		String price = request.getParameter("price");
		
		DB db = new DB();
		if (title == null) {
			if (db.ItemSold(id))
				response.setStatus(200);
			else 
				response.setStatus(400);
		} else {
			if (db.UpdateItem(id, title, desc, Integer.parseInt(price)))
				response.setStatus(200);
			else 
				response.setStatus(400);
		}
	}

}
