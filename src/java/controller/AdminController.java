package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean isLoggedIn = checkLoginStatus(request);

            if (isLoggedIn) {
                // Nếu đã đăng nhập, hiển thị nội dung của trang quản trị
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                // Thêm nội dung cụ thể của trang quản trị tại đây
            } else {
                // Nếu chưa đăng nhập, chuyển hướng sang trang đăng nhập
                response.sendRedirect(request.getContextPath() + "/admin/login");
            }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Admin Controller";
    }

    private boolean checkLoginStatus(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("datl-admin".equals(cookie.getName()) && "true".equals(cookie.getValue())) {
                    // Nếu cookie "isLoggedIn" có giá trị "true", đánh dấu là đã đăng nhập
                    return true;
                }
            }
        }
        return false;
    }
}
