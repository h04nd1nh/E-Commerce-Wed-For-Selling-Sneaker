/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author hoand
 */
@WebServlet(name = "AdminLogin", urlPatterns = {"/admin/login"})
public class AdminLogin extends HttpServlet {

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
        request.getRequestDispatcher("admin-login.jsp").forward(request, response);
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
        String UserName = request.getParameter("username");
        String Password = request.getParameter("password");
         
        UserDAO accountDao = new UserDAO();

        User user = accountDao.login(UserName, Password);
        
        if (user == null || user.getRoleID() != 2) {
            request.setAttribute("Alert", "Wrong username or password, please try again!");
            doGet(request, response);
            
        } else {

            String DataUser = UserName + ":" + Password;

            // Mã hoá dữ liệu
            String encodedData = Base64.getEncoder().encodeToString(DataUser.getBytes());
            // Lưu thông tin người dùng vào cookie
            Cookie DataUserCookie = new Cookie("datl-admin", encodedData);

            // Đặt thời gian sống của cookie là vĩnh viễn (sử dụng giá trị số âm)
            int cookieAge = -1;
            DataUserCookie.setMaxAge(cookieAge);

            // Đặt path để cookie có hiệu lực trên toàn bộ trang web
            DataUserCookie.setPath("/");

            // Thêm cookie vào response
            response.addCookie(DataUserCookie);

            // Chuyển hướng đến trang home
            response.sendRedirect("dashboard");
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
