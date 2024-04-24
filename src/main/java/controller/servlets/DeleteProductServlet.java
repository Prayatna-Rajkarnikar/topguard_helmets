package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.HelmetDbController;
import model.HelmetModel;
import util.StringUtil;

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HelmetDbController dbController;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductServlet() {
        super();
        this.dbController = new HelmetDbController();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String helmetName = request.getParameter("helmet_name");
	    System.out.print(helmetName);

	    int result = dbController.deleteHelmet(helmetName);

	    if (result > 0) {
	        response.sendRedirect("/pages/adminPanel.jsp");
	    } else {
	        // Error deleting helmet, handle accordingly
	    	response.sendRedirect("/pages/register.jsp");
	    }
	}

}
