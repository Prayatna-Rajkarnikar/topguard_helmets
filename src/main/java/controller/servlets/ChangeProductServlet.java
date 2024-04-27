package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.HelmetDbController;
import model.HelmetTableModel;
import util.StringUtil;

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/ChangeProductServlet")
public class ChangeProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HelmetDbController dbController;
       
    public ChangeProductServlet() {
        super();
        this.dbController = new HelmetDbController();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String updateId = request.getParameter(StringUtil.UPDATE_ID);
        String deleteIdString = request.getParameter(StringUtil.DELETE_ID);
        int deleteId = 0; // Default value for int

        if (deleteIdString != null && !deleteIdString.isEmpty()) {
            deleteId = Integer.parseInt(deleteIdString);
        }

        if (updateId != null && !updateId.isEmpty()) {
            doPut(request, response);
        }
        if (deleteId != 0) {
            doDelete(request, response);
        }
    }


	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("put triggered");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    System.out.println("delete triggered");
	    String deleteIdString = req.getParameter(StringUtil.DELETE_ID);
	    int deleteId = 0; // Default value for int

	    if (deleteIdString != null && !deleteIdString.isEmpty()) {
	        deleteId = Integer.parseInt(deleteIdString);
	    }

	    if (dbController.deleteHelmet(deleteId) == 1) {
	    	ArrayList<HelmetTableModel> helmets = dbController.getAllHelmets();
            req.setAttribute("helmets", helmets);
            req.getRequestDispatcher("/pages/adminProduct.jsp").forward(req, resp);
	    } else {
	        req.setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.MESSAGE_ERROR_DELETE);
	        resp.sendRedirect(req.getContextPath() + StringUtil.URL_ADMIN_PRODUCT);
	    }
	}

}
