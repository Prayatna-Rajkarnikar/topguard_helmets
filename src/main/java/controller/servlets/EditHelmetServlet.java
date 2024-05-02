package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.HelmetDbController;
import model.HelmetModel;
import model.HelmetTableModel;

@WebServlet("/EditHelmetServlet")
public class EditHelmetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HelmetDbController dbController;

	public EditHelmetServlet() {
		super();
		this.dbController = new HelmetDbController();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String helmetId = request.getParameter("helmet_id");
        String helmetName = request.getParameter("helmet_name");
        String price = request.getParameter("price");
        String brand = request.getParameter("brand");
        String color = request.getParameter("color");
        String size = request.getParameter("size");

        // Set attributes to forward to the JSP
        request.setAttribute("helmet_id", helmetId);
        request.setAttribute("helmet_name", helmetName);
        request.setAttribute("price", price);
        request.setAttribute("brand", brand);
        request.setAttribute("color", color);
        request.setAttribute("size", size);

        // Forward to the JSP for editing
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/editHelmet.jsp");
        dispatcher.forward(request, response);
    }
}
