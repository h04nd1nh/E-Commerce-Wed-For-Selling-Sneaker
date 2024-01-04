/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.*;
import DAO.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;

/**
 *
 * @author hoand
 */
@WebServlet(name = "CategoryAdminControl", urlPatterns = {"/admin/categorycontrol"})
public class CategoryAdminControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String brandID = request.getParameter("id");

        ProductDAO ProductDAO = new ProductDAO();
        List<Brand> ListBrand = ProductDAO.getListBrand();
        request.setAttribute("ListBrand", ListBrand);

        String action = request.getParameter("action");
        if (action != null) {
            AdminDAO AdminDAO = new AdminDAO();
            AdminDAO.DeleteBrandByID(Integer.parseInt(brandID));
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer != null ? referer : request.getContextPath());
            
        } else {
            if (brandID == null) {
                request.setAttribute("type", "Add");
                request.getRequestDispatcher("AddCategory.jsp").forward(request, response);
            } else {
                Brand Brand = ProductDAO.getBrandById(Integer.parseInt(brandID));
                request.setAttribute("type", "Edit");
                request.setAttribute("Brand", Brand);
                request.getRequestDispatcher("AddCategory.jsp").forward(request, response);
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoryID = request.getParameter("id");
        String category = request.getParameter("categoryname");
        
        if (categoryID == null) {
            Brand NewBrand = new Brand(1,category);
            AdminDAO AdminDAO = new AdminDAO();
            AdminDAO.AddBrand(NewBrand);
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer != null ? referer : request.getContextPath());
        } else {
            Brand NewBrand = new Brand(Integer.parseInt(categoryID),category);
            AdminDAO AdminDAO = new AdminDAO();
            AdminDAO.UpdateBrand(NewBrand);
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer != null ? referer : request.getContextPath());
        }
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
