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
                    <li class="active">
                        <i class="fa-solid fa-ticket"></i>
                        <a href="${pageContext.request.contextPath}/admin/coupon">Coupons</a>
                    </li>
                </ul>
                <p>USER MANAGEMENT</p>
                <ul>
                    <li>
                        <i class="fa-solid fa-user"></i>
                        <a href="${pageContext.request.contextPath}/admin/customer">Customers</a>
                    </li>
                </ul>
            </section>
        <section id="content">
            <div id="products" class=active>
                <p>Coupon</p>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Code</th>
                            <th>Discount</th>
                            <th>Start</th>
                            <th>Expiry</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody id="table-product-body">

                    </tbody>
                </table>
                <div id="options">
                    <div class="pagination" id="pagination">
                        <!-- Pagination buttons will be dynamically added here -->
                    </div>
                    <button onclick="window.location.href='${pageContext.request.contextPath}/admin/couponcontrol'">+ Add Voucher</button>
                </div>

                <script>
                    // Mảng chứa dữ liệu sản phẩm
                    var coupons = [
                        <c:forEach items="${ListVoucher}" var="coupon" varStatus="loop">
                        {
                            id: "${coupon.getVoucherID()}",
                            code: "${coupon.getVoucherCode()}",
                            discount: ${coupon.getDiscount()},
                            startdate: "${coupon.getStartDate()}",
                            expiry: "${coupon.getExpiry()}"
                                // Add other properties as needed
                        }<c:if test="${!loop.last}">,</c:if>
                        </c:forEach>
                        ];

                    // Số dòng trên mỗi trang
                    var rowsPerPage = 8;

                    // Số lượng trang
                    var totalPages = Math.ceil(coupons.length / rowsPerPage);

                    // Hiển thị trang đầu tiên khi tải trang
                    showPage(1);

                    function showPage(page) {
                        var startIndex = (page - 1) * rowsPerPage;
                        var endIndex = startIndex + rowsPerPage;

                        var tbody = document.getElementById("table-product-body");
                        tbody.innerHTML = "";

                        for (var i = startIndex; i < endIndex && i < coupons.length; i++) {
                            var coupon = coupons[i];
                            var row = "<tr>" +
                                "<td><p>" + coupon.id + "</p></td>" +
                                "<td><div id='product-name'><p>" + coupon.code + "</p></div></td>" +
                                "<td><p>" + coupon.discount + "</p></td>" +
                                "<td><p>" + coupon.startdate + "</p></td>" +
                                "<td><p>" + coupon.expiry + "</p></td>" +
                                "<td><div id='button-action'><button onclick=\"window.location.href='" + '${pageContext.request.contextPath}/admin/couponcontrol?id=' + coupon.id + "'\"><i class='fa-solid fa-pencil'></i></button><button onclick=\"window.location.href='" + '${pageContext.request.contextPath}/admin/couponcontrol?id=' + coupon.id + "&action=del" +"'\"><i class='fa-solid fa-trash'></i></button></div></td>" +
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