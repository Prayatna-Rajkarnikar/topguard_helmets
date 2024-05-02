package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.HelmetDbController;
import model.HelmetTableModel;

@WebServlet("/UpdateHelmetServlet")
public class UpdateHelmetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HelmetDbController dbController;

    public UpdateHelmetServlet() {
        super();
        this.dbController = new HelmetDbController();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve updated values from the form
        String helmetId = request.getParameter("helmet_id");
        String helmetName = request.getParameter("helmet_name");
        String pricePara = request.getParameter("price");
        String brand = request.getParameter("brand");
        String color = request.getParameter("color");
        String size = request.getParameter("size");

        // Parse price from string to double
        double price = 0.0;
        try {
            if (pricePara != null && !pricePara.isEmpty()) {
                price = Double.parseDouble(pricePara);
            }
        } catch (NumberFormatException e) {
            // Handle the case where price is not a valid double
            e.printStackTrace(); // Log the exception
            response.sendRedirect(request.getContextPath() + "/pages/adminProduct.jsp?error=true");
            return; // Exit the method to avoid further processing
        }

        // Check if helmetId is null or empty
        if (helmetId == null || helmetId.isEmpty()) {
            // Handle the case where helmetId is null or empty
            response.sendRedirect(request.getContextPath() + "/pages/adminProduct.jsp?error=true");
            return; // Exit the method to avoid further processing
        }

        // Update the database with the new values
        int result = dbController.updateHelmet(
                new HelmetTableModel(Integer.parseInt(helmetId), helmetName, price, brand, color, size, null));

        // Redirect back to the original JSP page with success or error message
        if (result == 1) {
            response.sendRedirect(request.getContextPath() + "/pages/adminProduct.jsp?success=true");
        } else {
            response.sendRedirect(request.getContextPath() + "/pages/adminProduct.jsp?error=true");
        }
    }
}
