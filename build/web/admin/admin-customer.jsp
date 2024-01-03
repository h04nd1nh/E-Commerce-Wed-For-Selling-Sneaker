<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../assets/css/index.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <title>PSneaker</title>
    </head>
    <body>
        <div id="container">
            <section id="navigation-menu">
                <img src="../assets/img/logo.png" alt="">
                <p>MENU</p>
                <ul>
                    <li>
                        <i class="fa-solid fa-bars"></i>
                        <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
                    </li>
                    <li>
                        <i class="fa-solid fa-box-archive"></i>
                        <a href="${pageContext.request.contextPath}/admin/product">Products</a>
                    </li>
                    <li>
                        <i class="fa-solid fa-list"></i>
                        <a href="${pageContext.request.contextPath}/admin/category">Categories</a>
                    </li>
                    <li>
                        <i class="fa-solid fa-shoe-prints"></i>
                        <a href="${pageContext.request.contextPath}/admin/property">Properties</a>
                    </li>
                    <li>
                        <i class="fa-solid fa-bars-progress"></i>
                        <a href="${pageContext.request.contextPath}/admin/order">Orders</a>
                    </li>
                    <li>
                        <i class="fa-solid fa-ticket"></i>
                        <a href="${pageContext.request.contextPath}/admin/coupon">Coupons</a>
                    </li>
                </ul>
                <p>USER MANAGEMENT</p>
                <ul>
                    <li class="active">
                        <i class="fa-solid fa-user"></i>
                        <a href="${pageContext.request.contextPath}/admin/customer">Customers</a>
                    </li>
                </ul>
            </section>
        <section id="content">
            <div id="products" class=active>
                <p>Customer</p>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Phone</th>
                        
                        </tr>
                    </thead>
                    <tbody id="table-product-body">

                    </tbody>
                </table>
                <div id="options">
                    <div class="pagination" id="pagination">
                        <!-- Pagination buttons will be dynamically added here -->
                    </div>
                </div>

                <script>
                    // Mảng chứa dữ liệu sản phẩm
                    var users = [
                        <c:forEach items="${ListUser}" var="user" varStatus="loop">
                        {
                            id: "${user.getUserID()}",
                            name: "${user.getFullName()}",
                            avatar: "${pageContext.request.contextPath}/${user.getAvatar()}",
                            username: "${user.getUserName()}",
                            email: "${user.getEmail()}",
                            phone: "${user.getPhoneNumber()}",
                                // Add other properties as needed
                        }<c:if test="${!loop.last}">,</c:if>
                        </c:forEach>
                        ];

                    // Số dòng trên mỗi trang
                    var rowsPerPage = 8;

                    // Số lượng trang
                    var totalPages = Math.ceil(users.length / rowsPerPage);

                    // Hiển thị trang đầu tiên khi tải trang
                    showPage(1);

                    function showPage(page) {
                        var startIndex = (page - 1) * rowsPerPage;
                        var endIndex = startIndex + rowsPerPage;

                        var tbody = document.getElementById("table-product-body");
                        tbody.innerHTML = "";

                        for (var i = startIndex; i < endIndex && i < users.length; i++) {
                            var user = users[i];
                            var row = "<tr>" +
                                "<td><p>" + user.id + "</p></td>" +
                                "<td><div id='product-name'><img src="+ user.avatar +" alt=''><p>" + user.name + "</p></div></td>" +
                                "<td><p>" + user.username + "</p></td>" +
                                "<td><p>" + user.email + "</p></td>" +
                                "<td><p>" + user.phone + "</p></td>" +
                                "</tr>";
                            tbody.innerHTML += row;
                        }

                        // Hiển thị các nút phân trang
                        showPagination(page);
                    }

                    function showPagination(currentPage) {
                        var pagination = document.getElementById("pagination");
                        pagination.innerHTML = "";

                        for (var i = 1; i <= totalPages; i++) {
                            var button = document.createElement("button");
                            button.innerText = i;
                            button.className = "page-button";
                            button.onclick = function () {
                                showPage(parseInt(this.innerText));
                            };
                            pagination.appendChild(button);

                            if (i === currentPage) {
                                button.classList.add("active");
                            }
                        }
                    }
                </script>
            </div>
        </section>
    </div>
</body>