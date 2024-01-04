/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.*;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SizeAdminControl", urlPatterns = {"/admin/sizecontrol"})
public class SizeAdminControl extends HttpServlet {

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
        String sizeID = request.getParameter("id");

        ProductDAO ProductDAO = new ProductDAO();

        String action = request.getParameter("action");
        if (action != null) {
            AdminDAO AdminDAO = new AdminDAO();
            AdminDAO.DeleteSizeByID(Integer.parseInt(sizeID));
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer != null ? referer : request.getContextPath());
        } else {
            if (sizeID == null) {
                request.setAttribute("type", "Add");
                request.getRequestDispatcher("AddSize.jsp").forward(request, response);
            } else {
                int Size = ProductDAO.getSizeBySizeID(Integer.parseInt(sizeID));
                request.setAttribute("type", "Edit");
                request.setAttribute("Size", Size);
                request.getRequestDispatcher("AddSize.jsp").forward(request, response);
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
        String sizeID = request.getParameter("id");
        String Size = request.getParameter("sizename");
        
        if (sizeID == null) {
            Size NewSize = new Size(1,Integer.parseInt(Size));
            AdminDAO AdminDAO = new AdminDAO();
            AdminDAO.AddSize(NewSize);
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer != null ? referer : request.getContextPath());
        } else {
            Size NewSize = new Size(Integer.parseInt(sizeID),Integer.parseInt(Size));
            AdminDAO AdminDAO = new AdminDAO();
            AdminDAO.UpdateSize(NewSize);
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
