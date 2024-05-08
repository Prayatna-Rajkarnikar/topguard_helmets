package controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.StringUtil;



@WebServlet("/EditHelmetServlet")
public class EditHelmetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	public EditHelmetServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String helmetId = request.getParameter(StringUtil.helmetID);
        String helmetName = request.getParameter(StringUtil.helmetName);
        String price = request.getParameter(StringUtil.helmetPrice);
        String brand = request.getParameter(StringUtil.brand);
        String color = request.getParameter(StringUtil.color);
        String size = request.getParameter(StringUtil.size);

        // Set attributes to forward to the JSP
        request.setAttribute(StringUtil.helmetID, helmetId);
        request.setAttribute(StringUtil.helmetName, helmetName);
        request.setAttribute(StringUtil.helmetPrice, price);
        request.setAttribute(StringUtil.brand, brand);
        request.setAttribute(StringUtil.color, color);
        request.setAttribute(StringUtil.size, size);

        // Forward to the JSP for editing
        RequestDispatcher dispatcher = request.getRequestDispatcher(StringUtil.URL_EDIT_HELMET);
        dispatcher.forward(request, response);
    }
}
