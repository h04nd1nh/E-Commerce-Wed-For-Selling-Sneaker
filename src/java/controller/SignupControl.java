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
import model.*;

/**
 *
 * @author hoand
 */
@WebServlet(name = "SignupControl", urlPatterns = {"/signup"})
public class SignupControl extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String FullName = request.getParameter("fullname");
        String UserName = request.getParameter("username");
        String NumberPhone = request.getParameter("numberphone");
        String Password = request.getParameter("password");
        String RePassword = request.getParameter("repassword");

        if (!Password.equals(RePassword)) {
            request.setAttribute("Alert","Password do not match");
            doGet(request,response);
        } else {
            UserDAO userDAO = new UserDAO();
            Boolean isUserExist = userDAO.isUserExist(UserName);

            if (isUserExist) {
                request.setAttribute("Alert","Username already exist");
                doGet(request,response);
            } else {
                userDAO.signup(UserName, FullName, NumberPhone, Password);
                response.sendRedirect("login");
            }
        }
    }

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
}
