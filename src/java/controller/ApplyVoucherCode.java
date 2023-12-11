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
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hoand
 */
@WebServlet(name = "discount", urlPatterns = {"/discount"})
public class ApplyVoucherCode extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        String VoucherCode = request.getParameter("code");
        String BuyFromCart = request.getParameter("buyfromcart");

        ProductDAO ProductDAO = new ProductDAO();
        int Discount = ProductDAO.getDiscountByVoucherCode(VoucherCode);

        Cookie[] cookies = request.getCookies();
        String DiscountData = null;

        // Lay URL noi request
        String referer = request.getHeader("referer");
        
        if (BuyFromCart.equals("true") || BuyFromCart == null) {
            if (cookies != null) {
                for (Cookie data : cookies) {
                    if (data.getName().equals("discount")) {
                        data.setMaxAge(0);
                        response.addCookie(data);
                    }
                }
            }

            DiscountData = VoucherCode + ":" + Discount;

            Cookie newCookie = new Cookie("discount", DiscountData);
            newCookie.setMaxAge(2 * 24 * 60 * 60);
            response.addCookie(newCookie);
        } else {
            if (cookies != null) {
                for (Cookie data : cookies) {
                    if (data.getName().equals("discount_temp")) {
                        data.setMaxAge(0);
                        response.addCookie(data);
                    }
                }
            }

            DiscountData = VoucherCode + ":" + Discount;

            Cookie newCookie = new Cookie("discount_temp", DiscountData);
            newCookie.setMaxAge(2 * 24 * 60 * 60);
            response.addCookie(newCookie);
        }

        // load lại trang trước đó
        // Kiểm tra xem nó có giá trị không
        if (referer != null && !referer.isEmpty()) {
            //load lại trang trước đó
            response.sendRedirect(referer);
        } else {
            // Nếu không chuyển hướng về shop
            response.sendRedirect("shop");
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
