/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CookieDAO;
import DAO.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CartItem;

/**
 *
 * @author hoand
 */
@WebServlet(name = "checkout", urlPatterns = {"/checkout"})
public class CheckoutControl extends HttpServlet {

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

        // Lay chuoi Parameter
        String queryString = request.getQueryString();
        List<CartItem> ListItem = new ArrayList<>();

        //tao bien check xem user mua hang tu gio hang hay mua ngay tu shop
        Boolean BuyFromCart;

        // Neu khach hang thanh toan tu gio hang, lay san pham o cookie cart
        if (queryString == null) {
            ProductDAO ProductDao = new ProductDAO();
            Cookie[] cookies = request.getCookies();
            BuyFromCart = true;

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("cart")) {

                        String cartData = cookie.getValue();

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
                            ListItem.add(new CartItem(
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

                    if (cookie.getName().equals("discount")) {
                        String Data = cookie.getValue();
                        String[] DiscountData = Data.split(":");
                        String VoucherCode = DiscountData[0];
                        int Discount = Integer.parseInt(DiscountData[1]);
                        request.setAttribute("VoucherCode", VoucherCode);
                        request.setAttribute("Discount", Discount);
                        System.out.println(Discount);
                        System.out.println(VoucherCode);
                    }
                }
            }

        } else {
            // Neu khach hang mua ngay, tao cookie cart_temp de luu thong tin san pham

            Cookie[] cookies = request.getCookies();
            String CookieData = "";
            String ProductID = request.getParameter("productid");
            String ProductColor = request.getParameter("color");
            String ProductSize = request.getParameter("size");
            String Quantity = request.getParameter("quantity");

            ProductDAO ProductDao = new ProductDAO();
            BuyFromCart = false;

            ListItem.add(new CartItem(
                    Integer.parseInt(ProductID),
                    ProductDao.getProductByID(Integer.parseInt(ProductID)).getProductName(),
                    Integer.parseInt(ProductColor),
                    ProductDao.getColorByColorID(Integer.parseInt(ProductColor)),
                    Integer.parseInt(ProductSize),
                    ProductDao.getSizeBySizeID(Integer.parseInt(ProductSize)),
                    Integer.parseInt(Quantity),
                    ProductDao.getProductByID(Integer.parseInt(ProductID)).getImage1(),
                    ProductDao.getProductByID(Integer.parseInt(ProductID)).getSale()
            ));

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("cart_temp")) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                    
                    if (cookie.getName().equals("discount_temp")) {
                        String Data = cookie.getValue();
                        String[] DiscountData = Data.split(":");
                        String VoucherCode = DiscountData[0];
                        int Discount = Integer.parseInt(DiscountData[1]);
                        request.setAttribute("VoucherCode", VoucherCode);
                        request.setAttribute("Discount", Discount);
                    }

                }
            }

            CookieData = ProductID + ":" + ProductColor + ":" + ProductSize + ":" + Quantity;

            //Format lại cookie
            CookieDAO CookieDAO = new CookieDAO();
            String FormatCookie = CookieDAO.formatCookie(CookieData);

            Cookie newCookie = new Cookie("cart_temp", FormatCookie);
            newCookie.setMaxAge(2 * 24 * 60 * 60);
            response.addCookie(newCookie);
        }

        request.setAttribute("ListItem", ListItem);
        request.setAttribute("IsBuyFromCart", BuyFromCart);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/checkout.jsp");
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
