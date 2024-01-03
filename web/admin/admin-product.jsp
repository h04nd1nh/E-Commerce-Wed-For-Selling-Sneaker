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
                    <li class="active">
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
                    <li>
                        <i class="fa-solid fa-user"></i>
                        <a href="${pageContext.request.contextPath}/admin/customer">Customers</a>
                    </li>
                </ul>
            </section>
            <section id="content">
                <div id="products" class=active>
                    <p>Products</p>
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Product</th>
                                <th>Category</th>
                                <th>Stock</th>
                                <th>Price</th>
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
                        <button onclick="window.location.href = '${pageContext.request.contextPath}/admin/productcontrol'">+ Add Product</button>
                    </div>

                    <script>
                        // Mảng chứa dữ liệu sản phẩm
                        var products = [
                        <c:forEach items="${listProduct}" var="product" varStatus="loop">
                        {
                        id: ${product.getProductID()},
                                name: "${product.getProductName()}",
                                image: "${pageContext.request.contextPath}/${product.getImage1()}",
                                            price: "<fmt:formatNumber type="number" value="${product.getPrice()}"  pattern="###,###,###,##0"/>đ",
                                            category: "${product.getBrandName()}",
                                            stock: ${product.getStock()}
                                    // Add other properties as needed
                                    }<c:if test="${!loop.last}">,</c:if>
                        </c:forEach>
                                    ];



                                    // Số dòng trên mỗi trang và số lượng trang
                                    var rowsPerPage = 11;
                                    var totalPages = Math.ceil(products.length / rowsPerPage);

                                    // Hiển thị trang đầu tiên khi tải trang
                                    showPage(1);

                                    function showPage(page) {
                                        var startIndex = (page - 1) * rowsPerPage;
                                        var endIndex = startIndex + rowsPerPage;

                                        var tbody = document.getElementById("table-product-body");
                                        tbody.innerHTML = "";

                                        for (var i = startIndex; i < endIndex && i < products.length; i++) {
                                            var product = products[i];
                                            var row = "<tr>" +
                                                    "<td><p>" + product.id + "</p></td>" +
                                                    "<td><div id='product-name'><img src='" + product.image + "' alt=''><p>" + product.name + "</p></div></td>" +
                                                    "<td><p>" + product.category + "</p></td>" +
                                                    "<td><p>" + product.stock + "</p></td>" +
                                                    "<td><p>" + product.price + "</p></td>" +
                                                    "<td><div id='button-action'><button onclick=\"window.location.href='" + '${pageContext.request.contextPath}/admin/productcontrol?id=' + product.id + "'\"><i class='fa-solid fa-pencil'></i></button><button onclick=\"window.location.href='" + '${pageContext.request.contextPath}/admin/productcontrol?id=' + product.id +"&action=del" + "'\"><i class='fa-solid fa-trash'></i></button></div></td>" +
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