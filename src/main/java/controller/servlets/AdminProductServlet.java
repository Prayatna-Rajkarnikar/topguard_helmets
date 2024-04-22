package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.HelmetDbController;
import model.HelmetModel;
import util.StringUtil;

/**
 * Servlet implementation class AdminProductServlet
 */
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String helmet_name = request.getParameter("helmet_name");
        double price = 0.0; // Default value in case of null
        String priceParam = request.getParameter("helmet_price");
        if (priceParam != null && !priceParam.trim().isEmpty()) {
            try {
                price = Double.parseDouble(priceParam);
            } catch (NumberFormatException e) {
                // Handle parsing error
                e.printStackTrace();
            }
        }
        String brand = request.getParameter("helmet_brand");
        String color = request.getParameter("helmet_color");
        String size = request.getParameter("helmet_size");
        Part helmet_image = request.getPart("helmet_image");
        System.out.println("image"+helmet_image);
        
        if (helmet_image != null) {
            HelmetModel helmetModel = new HelmetModel(helmet_name, price, brand, color, size, helmet_image);

            String savePath = StringUtil.IMAGE_DIR_SAVE_PATH;
            String fileName = helmetModel.getUserImageUrl();
            if (!fileName.isEmpty() && fileName != null)
            	helmet_image.write(savePath + fileName);

            System.out.println(helmetModel.getUserImageUrl());
            int result = dbController.addHelmet(helmetModel);

            if (result > 0) {
                // Product added successfully, redirect to admin page
                response.sendRedirect("/pages/register.jsp");
            } else {
                // Error adding product, handle accordingly
            }
        } else {
            // Handle the case when user_image is null
            System.out.println("user_image is null");
        }
    }

}
