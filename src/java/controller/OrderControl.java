/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;

/**
 *
 * @author hoand
 */
@WebServlet(name = "order", urlPatterns = {"/order"})
public class OrderControl extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");

        UserDAO UserDAO = new UserDAO();
        ProductDAO ProductDAO = new ProductDAO();

        int UserID = 0;
        String Total = request.getParameter("total");
        String Address = request.getParameter("address");
        String Status = null;
        String Note = request.getParameter("ordernotes");
        String FullName = request.getParameter("fullname");
        String Email = request.getParameter("email");
        String PhoneNumber = request.getParameter("phonenumber");

        String Code = request.getParameter("code");
        int VoucherID = ProductDAO.getVoucherIDByVoucherCode(Code);

        String PaymentAmount = request.getParameter("paymentamount");

        String IsCart = request.getParameter("isCart");
        ProductDAO ProductDao = new ProductDAO();
        List<CartItem> CartItems = new ArrayList<>();

        String encodedatl = "";

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {

                // Lay thong tin nguoi dung
                if (cookie.getName().equals("datl")) {
                    encodedatl += cookie.getValue();
                }

                // Neu mua tu gio hang, xoa thong tin cart va discount
                if (IsCart.equals("true")) {
                    if (cookie.getName().equals("discount")) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }

                    if (cookie.getName().equals("cart")) {
                        String cartData = cookie.getValue();
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);

                        // Chia chuỗi thành các mục dựa trên một ký tự phân tách
                        String[] items = cartData.split("::");

                        for (String item : items) {
                            // Chia mỗi mục thành các giá trị dựa trên một ký tự phân tách khác, ví dụ: ":"

                            String[] values = item.split(":");

                            int productId = Integer.parseInt(values[0]);
                            int colorId = Integer.parseInt(values[1]);
                            int sizeId = Integer.parseInt(values[2]);
                            int quantity = Integer.parseInt(values[3]);

                            // Thực hiện thêm sản phẩm vào ListItem
                            CartItems.add(new CartItem(
                                    productId,
                                    ProductDao.getProductByID(productId).getProductName(),
                                    colorId,
                                    ProductDao.getColorByColorID(colorId),
                                    sizeId,
                                    ProductDao.getSizeBySizeID(sizeId),
                                    quantity,
                                    ProductDao.getProductByID(productId).getImage1(),
                                    ProductDao.getProductByID(productId).getSale()
                            ));

                        }
                    }
                } else {
                    if (cookie.getName().equals("discount_temp")) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }

                    if (cookie.getName().equals("cart_temp")) {
                        String cartData = cookie.getValue();
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);

                        // Chia chuỗi thành các mục dựa trên một ký tự phân tách
                        String[] items = cartData.split("::");

                        for (String item : items) {
                            // Chia mỗi mục thành các giá trị dựa trên một ký tự phân tách khác, ví dụ: ":"

                            String[] values = item.split(":");

                            int productId = Integer.parseInt(values[0]);
                            int colorId = Integer.parseInt(values[1]);
                            int sizeId = Integer.parseInt(values[2]);
                            int quantity = Integer.parseInt(values[3]);

                            // Thực hiện thêm sản phẩm vào ListItem
                            CartItems.add(new CartItem(
                                    productId,
                                    ProductDao.getProductByID(productId).getProductName(),
                                    colorId,
                                    ProductDao.getColorByColorID(colorId),
                                    sizeId,
                                    ProductDao.getSizeBySizeID(sizeId),
                                    quantity,
                                    ProductDao.getProductByID(productId).getImage1(),
                                    ProductDao.getProductByID(productId).getSale()
                            ));

                        }
                    }
                }

            }
        }

        String decodeddatl = new String(Base64.getDecoder().decode(encodedatl));
        String[] parts = decodeddatl.split(":");
        String UserName = parts[0];
        UserID = UserDAO.getUserIDByUserName(UserName);
        Status = "Pending";
        Boolean Check = UserDAO.OrderProceed(UserID, Float.parseFloat(Total), Address, Status, Note, FullName, Email, PhoneNumber, VoucherID, Float.parseFloat(PaymentAmount), CartItems);

        if (Check == true) {
            response.setContentType("text/html;charset=UTF-8");

            // Mã JavaScript để hiển thị thông báo và chuyển hướng
            String alertScript = "<script>"
                    + "alert('Order Succesfully!');"
                    + "window.location.href='home';"
                    + "</script>";

            // Thêm mã JavaScript vào trang
            response.getWriter().write(alertScript);
        } else {
            response.setContentType("text/html;charset=UTF-8");

            // Mã JavaScript để hiển thị thông báo và chuyển hướng
            String alertScript = "<script>"
                    + "alert('Order Failed, Maybe some products in your shopping cart are out of stock');"
                    + "window.location.href='home';"
                    + "</script>";

            // Thêm mã JavaScript vào trang
            response.getWriter().write(alertScript);
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
