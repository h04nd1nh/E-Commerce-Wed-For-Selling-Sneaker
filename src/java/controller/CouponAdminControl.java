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
@WebServlet(name = "CouponAdminControl", urlPatterns = {"/admin/couponcontrol"})
public class CouponAdminControl extends HttpServlet {

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
        String couponID = request.getParameter("id");

        

        String action = request.getParameter("action");
        if (action != null) {
            AdminDAO AdminDAO = new AdminDAO();
            AdminDAO.DeleteVoucherByID(Integer.parseInt(couponID));
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer != null ? referer : request.getContextPath());
        } else {
            if (couponID == null) {
                request.setAttribute("type", "Add");
                request.getRequestDispatcher("AddCoupon.jsp").forward(request, response);
            } else {
                AdminDAO AdminDAO = new AdminDAO();
                Voucher Voucher = AdminDAO.getVoucherById(Integer.parseInt(couponID));
                request.setAttribute("type", "Edit");
                request.setAttribute("Voucher", Voucher);
                request.getRequestDispatcher("AddCoupon.jsp").forward(request, response);
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
        String couponID = request.getParameter("id");
        String CouponCode = request.getParameter("couponcode");
        String Discount = request.getParameter("discount");
        String StartDate = request.getParameter("startdate");
        String EndDate = request.getParameter("enddate");
        
        if (couponID == null) {
            Voucher NewVoucher = new Voucher(1,CouponCode,Integer.parseInt(Discount),StartDate,EndDate);
            AdminDAO AdminDAO = new AdminDAO();
            AdminDAO.AddVoucher(NewVoucher);
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer != null ? referer : request.getContextPath());
        } else {
            Voucher NewVoucher = new Voucher(Integer.parseInt(couponID),CouponCode,Integer.parseInt(Discount),StartDate,EndDate);
            AdminDAO AdminDAO = new AdminDAO();
            AdminDAO.AddVoucher(NewVoucher);
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
