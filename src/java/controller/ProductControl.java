/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.*;
import java.util.*;
import javax.servlet.http.Cookie;
import model.*;

/**
 *
 * @author hoand
 */
@WebServlet(name = "ProductControl", urlPatterns = {"/product"})
public class ProductControl extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String ProductID = request.getParameter("id");
        ProductDAO dao = new ProductDAO();

        Product Product = dao.getProductByID(Integer.parseInt(ProductID));

        List<Size> ProductSize = dao.getSizeByProductID(Integer.parseInt(ProductID));
        List<Color> ProductColor = dao.getColorByProductID(Integer.parseInt(ProductID));
        List<ProductReview> ProductReview = dao.getReviewByProductID(Integer.parseInt(ProductID));
        ProductRate ProductRate = dao.ProductRateByProductID(Integer.parseInt(ProductID));

        request.setAttribute("Product", Product);
        request.setAttribute("ProductSize", ProductSize);
        request.setAttribute("ProductColor", ProductColor);
        request.setAttribute("ProductReview", ProductReview);
        request.setAttribute("ProductRate", ProductRate);
        request.getRequestDispatcher("product.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String Comment = request.getParameter("comment");
        String ProductID = request.getParameter("productid");
        System.out.println(Comment);
        
        // Lay thong tin id nguoi comment
        Cookie[] cookies = request.getCookies();
        String encodedatl = "";
        
        if (cookies != null) {
            for (Cookie data:cookies) {
                if (data.getName().equals("datl")) {
                    encodedatl += data.getValue();
                }
            }
        }
        
        String decodeddatl = new String(Base64.getDecoder().decode(encodedatl));
        String[] parts = decodeddatl.split(":");
        
        String UserName = parts[0];
        
        ProductDAO ProductDAO = new ProductDAO();
        ProductDAO.AddProductReview(Integer.parseInt(ProductID),Comment,UserName);
        
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
