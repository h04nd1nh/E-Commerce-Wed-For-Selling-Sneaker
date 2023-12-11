/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.*;
import java.io.File;
import java.io.FileOutputStream;
import model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hoand
 */
@WebServlet(name = "UserAccountDetailControl", urlPatterns = {"/user/account"})
public class UserAccountDetailControl extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "assets/img";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        response.setContentType("text/html;charset=UTF-8");

        String ActiveClass = "account";
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

        User User = UserDAO.getUserByUserName(UserName);

        String FullName = User.getFullName();
        String Email = User.getEmail();
        String PhoneNumber = User.getPhoneNumber();
        String Address = User.getAddress();
        String Avatar = User.getAvatar();

        request.setAttribute("UserName", UserName);
        request.setAttribute("FullName", FullName);
        request.setAttribute("Email", Email);
        request.setAttribute("PhoneNumber", PhoneNumber);
        request.setAttribute("Address", Address);
        request.setAttribute("Avatar", Avatar);

        request.setAttribute("ActiveClass", ActiveClass);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user.jsp");
        dispatcher.forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");

        // Lay thong tin nguoi dung
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
        String[] datl = decodeddatl.split(":");
        String UserName = datl[0];

        User User = UserDAO.getUserByUserName(UserName);

        // Kiem tra xem nguoi dung update thong tin hay mat khau;
        String type = request.getParameter("type");

        if (type.equals("infomation")) { // Nguoi dung update thong tin
            String base64Data = request.getParameter("avatar");
            String FullName = request.getParameter("fullname");
            String Email = request.getParameter("email");
            String PhoneNumber = request.getParameter("phonenumber");
            String Address = request.getParameter("address");

            String Avatar = null;
            // Kiem tra xem nguoi dung co thay doi avatar khong
            if (base64Data != User.getAvatar() && base64Data != null && !base64Data.isEmpty()) {
                // Tách phần dữ liệu base64 từ chuỗi
                String[] parts = base64Data.split(",");
                String base64Image = parts[1];

                // Giải mã base64 thành dữ liệu bytes
                byte[] decodedBytes = Base64.getDecoder().decode(base64Image);

                // Xác định đường dẫn tuyệt đối của thư mục upload trên máy chủ
                String uploadPath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

                // Tạo thư mục upload nếu nó chưa tồn tại
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                // Tạo tên file duy nhất (có thể sử dụng UUID để đảm bảo duy nhất)
                String fileName = UUID.randomUUID().toString() + ".jpg";

                // Lưu dữ liệu bytes vào tệp hình ảnh
                try (FileOutputStream fos = new FileOutputStream(new File(uploadPath, fileName))) {
                    fos.write(decodedBytes);
                }

                Avatar = UPLOAD_DIRECTORY + "/" + fileName;
            } else {
                Avatar = User.getAvatar();
            }

            // UpdateUser
            User.setAddress(Address);
            User.setFullName(FullName);
            User.setEmail(Email);
            User.setPhoneNumber(PhoneNumber);
            User.setAvatar(Avatar);

            UserDAO.UpdateUser(User);
            // reload lại trang
            doGet(request, response);
        } else {

            String CurrentPassword = request.getParameter("currentpassword");
            String Newpassword = request.getParameter("newpassword");
            String Confirmpassword = request.getParameter("confirmpassword");

            String Alert = null;

            if (!CurrentPassword.equals(User.getPassword())) {
                Alert = "Old password is wrong!";
                request.setAttribute("Alert", Alert);
                doGet(request, response);
            } else if (!Newpassword.equals(Confirmpassword)) {
                Alert = "Password do not match!";
                request.setAttribute("Alert", Alert);
                doGet(request, response);
            } else {
                // UpdateUser
                User.setPassword(Newpassword);
                UserDAO.UpdateUser(User);
                request.setAttribute("success", "Password change succesfully!");
                // reload lại trang
                doGet(request, response);
            }

            
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
