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
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import model.*;

/**
 *
 * @author hoand
 */
@WebServlet(name = "ProductAdminController", urlPatterns = {"/admin/productcontrol"})
public class ProductAdminController extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "assets/product-img";

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
        String ProductID = request.getParameter("id");

        ProductDAO ProductDAO = new ProductDAO();
        List<Brand> ListBrand = ProductDAO.getListBrand();
        List<Size> ListSize = ProductDAO.getListSize();
        List<Color> ListColor = ProductDAO.getListColor();
        request.setAttribute("ListBrand", ListBrand);
        request.setAttribute("ListSize", ListSize);
        request.setAttribute("ListColor", ListColor);

        String action = request.getParameter("action");
        if (action != null) {
            AdminDAO AdminDAO = new AdminDAO();
            AdminDAO.DeleteProductByID(Integer.parseInt(ProductID));
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer != null ? referer : request.getContextPath());
        } else {
            if (ProductID == null) {
                request.setAttribute("type", "Add");
                request.getRequestDispatcher("AddProduct.jsp").forward(request, response);
            } else {
                Product Product = ProductDAO.getProductByID(Integer.parseInt(ProductID));
                List<Size> ListSizeValid = ProductDAO.getSizeByProductID(Integer.parseInt(ProductID));
                List<Color> ListColorValid = ProductDAO.getColorByProductID(Integer.parseInt(ProductID));
                
                
                List<Integer> ListSizeEnable = new ArrayList<>();
                for (Size size : ListSizeValid) {

                    ListSizeEnable.add(size.getSizeID());
                }
                
                List<Integer> ListColorEnable = new ArrayList<>();
                for (Color color : ListColorValid) {

                    ListColorEnable.add(color.getColorID());
                }
                
               
                
                request.setAttribute("type", "Edit");
                request.setAttribute("Product", Product);
                request.setAttribute("ListSizeEnable", ListSizeEnable);
                request.setAttribute("ListColorEnable", ListColorEnable);
                request.getRequestDispatcher("AddProduct.jsp").forward(request, response);
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
        String ProductID = request.getParameter("id");

        ProductDAO ProductDAO = new ProductDAO();
        String ProductName = request.getParameter("productname");
        String Description = request.getParameter("productdescription");
        String Price = request.getParameter("price");
        String Sale = request.getParameter("sale");
        String Quantity = request.getParameter("quantity");
        String brandID = request.getParameter("brand");
        String BrandName = ProductDAO.findBrandNameById(Integer.parseInt(brandID));
        String Image1 = request.getParameter("base64Image1");
        String Image2 = request.getParameter("base64Image2");
        String Image3 = request.getParameter("base64Image3");
        String PathImage1 = null;
        String PathImage2 = null;
        String PathImage3 = null;
        String[] colors = request.getParameterValues("color");
        String[] sizes = request.getParameterValues("size");

        if (Image1.length() != 0) {
            PathImage1 = saveImage(Image1, request);
        }
        if (Image2 != null && !Image2.isEmpty()) {
            PathImage2 = saveImage(Image2, request);
        }
        if (Image3 != null && !Image3.isEmpty()) {
            PathImage3 = saveImage(Image3, request);
        }

        AdminDAO AdminDAO = new AdminDAO();
        Product newProduct = new Product(1, ProductName, Float.parseFloat(Price), Float.parseFloat(Sale), PathImage1, PathImage2, PathImage3, Integer.parseInt(brandID), BrandName, true, Description, Integer.parseInt(Quantity));

        if (ProductID == null) {
            int newProductID = AdminDAO.AddProduct(newProduct);
            AdminDAO.UpdateProductColor(colors, newProductID);
            AdminDAO.UpdateProductSize(sizes, newProductID);
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer != null ? referer : request.getContextPath());
        } else {
            AdminDAO.UpdateProductByID(Integer.parseInt(ProductID), newProduct);
            AdminDAO.UpdateProductColor(colors, Integer.parseInt(ProductID));
            AdminDAO.UpdateProductSize(sizes, Integer.parseInt(ProductID));
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer != null ? referer : request.getContextPath());
        }

    }

    private String saveImage(String base64Data, HttpServletRequest request) {
        // Kiểm tra xem người dùng có thay đổi avatar không
        if (base64Data != null && !base64Data.isEmpty()) {
            try {
                // Tách phần dữ liệu base64 từ chuỗi
                String[] parts = base64Data.split(",");
                String base64Image = parts[1];

                // Giải mã base64 thành dữ liệu bytes
                byte[] decodedBytes = Base64.getDecoder().decode(base64Image);

                // Xác định đường dẫn tuyệt đối của thư mục upload trên máy chủ (thay bằng đường dẫn tuyệt đối của thư mục bạn muốn)
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

                // Trả về đường dẫn mới của ảnh
                return UPLOAD_DIRECTORY + "/" + fileName;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            // Nếu không có dữ liệu base64, trả về null hoặc một giá trị khác để thể hiện không có thay đổi
            return null;
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
