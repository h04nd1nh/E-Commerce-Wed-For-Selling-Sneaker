/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.*;
import java.net.URLEncoder;
import javax.servlet.http.Cookie;


/**
 *
 * @author hoand
 */
@WebServlet(name = "AddToCart", urlPatterns = {"/AddToCart"})
public class AddToCart extends HttpServlet {

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

        String ProductID = request.getParameter("productid");
        String ProductColor = request.getParameter("color");
        String ProductSize = request.getParameter("size");
        String Quantity = request.getParameter("quantity");
        Cookie[] cookies = request.getCookies();
        String CookieData = "";
       
        
        if (cookies != null) {
            for (Cookie data:cookies) {
                if (data.getName().equals("cart")) {
                    CookieData += data.getValue();
                    data.setMaxAge(0);
                    response.addCookie(data);
                }
            }
        }
        

        
        if (CookieData.isEmpty()) {
            CookieData = ProductID + ":" + ProductColor + ":" + ProductSize + ":" + Quantity;
        } else {
            CookieData = CookieData+="::"+ProductID + ":" + ProductColor + ":" + ProductSize + ":" + Quantity;
        }
        
        //Format lại cookie
        CookieDAO CookieDAO = new CookieDAO();
        String FormatCookie = CookieDAO.formatCookie(CookieData);
        
        Cookie newCookie = new Cookie("cart", FormatCookie);
        newCookie.setMaxAge(2*24*60*60);
        response.addCookie(newCookie);
        
        
        // load lại trang trước đó
        String referer = request.getHeader("referer");

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
