/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.Cookie;
import model.*;

/**
 *
 * @author hoand
 */
@WebServlet(name = "UserDashBoardControl", urlPatterns = {"/user/dashboard"})
public class UserDashBoardControl extends HttpServlet {

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

        String ActiveClass = "dashboard";
        UserDAO UserDAO = new UserDAO();
        
        String encodedatl = "";

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {

                // Lay thong tin nguoi dung
                if (cookie.getName().equals("datl")) {
                    encodedatl += cookie.getValue();
                }
            }
        }
        
        String decodeddatl = new String(Base64.getDecoder().decode(encodedatl));
        String[] parts = decodeddatl.split(":");
        String UserName = parts[0];
        
        
        int UserID = UserDAO.getUserIDByUserName(UserName);
        List<Order> ListOrder = UserDAO.getAllOrderByUserID(UserID);
        
        int TotalOrders = ListOrder.size();
        int PendingOrders = 0;
        int CompleteOrders = 0;
        int CancelOrders = 0;
        int TotalAmount = 0;
        int temp = 0; 
        int SavedAmount = 0;
        
        for (Order order : ListOrder) {
            if (order.getStatus().equals("Pending")) {
                PendingOrders+=1;
            }
            
            if (order.getStatus().equals("Completed")) {
                CompleteOrders+=1;
            }
            
            if (order.getStatus().equals("Canceled")) {
                CancelOrders+=1;
            }
            
            TotalAmount += order.getPaymentAmount();
            temp += order.getTotal();
        }
        
        SavedAmount = temp - TotalAmount;
        
        
        
        request.setAttribute("TotalOrders", TotalOrders);
        request.setAttribute("PendingOrders", PendingOrders);
        request.setAttribute("CompleteOrders", CompleteOrders);
        request.setAttribute("CancelOrders", CancelOrders);
        request.setAttribute("TotalAmount", TotalAmount);
        request.setAttribute("SavedAmount", SavedAmount);
        request.setAttribute("ActiveClass", ActiveClass);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user.jsp");
        dispatcher.forward(request, response);
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
