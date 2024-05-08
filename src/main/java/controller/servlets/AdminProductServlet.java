package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.HelmetDbController;
import model.HelmetModel;
import model.HelmetTableModel;
import util.StringUtil;

@WebServlet("/AdminProductServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10, // 10MB
    maxRequestSize = 1024 * 1024 * 50)
public class AdminProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HelmetDbController dbController;

    public AdminProductServlet() {
        super();
        this.dbController = new HelmetDbController();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all helmets from the database
        ArrayList<HelmetTableModel> helmets = dbController.getAllHelmets();
        request.setAttribute("helmets", helmets);
        request.getRequestDispatcher(StringUtil.URL_ADMIN_PRODUCT).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String helmet_name = request.getParameter(StringUtil.helmetName);
        double price = 0.0; // Default value in case of null
        String priceParam = request.getParameter(StringUtil.helmetPrice);
        if (priceParam != null && !priceParam.trim().isEmpty()) {
            try {
                price = Double.parseDouble(priceParam);
            } catch (NumberFormatException e) {
            	request.setAttribute("errorMessage", "Price must be a valid positive number.");
                request.getRequestDispatcher(StringUtil.URL_ADMIN_PRODUCT).forward(request, response);
                return;
            }
        }
        String brand = request.getParameter(StringUtil.brand);
        String color = request.getParameter(StringUtil.color);
        String size = request.getParameter(StringUtil.size);
        Part helmet_image = request.getPart("helmet_image");
        
        
        if (!brand.matches("^[a-zA-Z0-9]{6,}$")) {
            request.setAttribute("errorMessage", StringUtil.SYMBOL_ERROR_MESSAGE);
            request.getRequestDispatcher(StringUtil.URL_ADMIN_PRODUCT).forward(request, response);
            return;
        }

        if (!color.matches("^[a-zA-Z0-9]{1,}$")) {
            request.setAttribute("errorMessage", StringUtil.SYMBOL_ERROR_MESSAGE);
            request.getRequestDispatcher(StringUtil.URL_ADMIN_PRODUCT).forward(request, response);
            return;
        }

        if (!size.matches("^[a-zA-Z0-9]{1,}$")) {
            request.setAttribute("errorMessage", StringUtil.SYMBOL_ERROR_MESSAGE);
            request.getRequestDispatcher(StringUtil.URL_ADMIN_PRODUCT).forward(request, response);
            return;
        }
	
        if (helmet_image != null) {
            HelmetModel helmetModel = new HelmetModel(helmet_name, price, brand, color, size, helmet_image);

            String savePath = StringUtil.IMAGE_DIR_SAVE_PATH_HELMET;
            String fileName = helmetModel.getUserImageUrl();
            if (!fileName.isEmpty() && fileName != null)
                helmet_image.write(savePath + fileName);

            int result = dbController.addHelmet(helmetModel);

            if (result > 0) {
                // Fetch all helmets from the database
                ArrayList<HelmetTableModel> helmets = dbController.getAllHelmets();
                request.setAttribute("helmets", helmets);
    			request.setAttribute("successMessage",StringUtil.ADD_HELMET_SUCCESS);
                request.getRequestDispatcher(StringUtil.URL_ADMIN_PRODUCT).forward(request, response);
            } else {
                // Error adding product, handle accordingly
            	request.setAttribute("errorMessage",StringUtil.ADD_HELMET_ERROR);
    			request.getRequestDispatcher(StringUtil.URL_ADMIN_PRODUCT).forward(request, response);
    			return;
            }
        } else {
            // Handle the case when helmet_image is null
            System.out.println("helmet_image is null");
        }
    }
}


