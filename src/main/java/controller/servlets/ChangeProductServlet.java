package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.HelmetDbController;
import model.HelmetModel;
import model.HelmetTableModel;

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

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String deleteIdString = request.getParameter("deleteId");

        if (deleteIdString != null && !deleteIdString.isEmpty()) {
            int deleteId = Integer.parseInt(deleteIdString);
            dbController.deleteHelmet(deleteId);
            System.out.println("Deleted");
        } 
        

        // Fetch all helmets from the database
        ArrayList<HelmetTableModel> helmets = dbController.getAllHelmets();
        request.setAttribute("helmets", helmets);
        request.getRequestDispatcher("/pages/adminProduct.jsp").forward(request, response);
    }
}


