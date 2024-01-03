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
                    <li class="active">
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
                <div id="categories" class="active">
                    <p>Categories</p>
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Category Name</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody id="table-product-body">
                            <c:forEach items="${ListBrand}" var="brand">
                                <tr>
                                    <td>#${brand.getBrandID()}</td>
                                    <td>${brand.getBrandName()}</td>
                                    <td>
                                        <div id="button-action">
                                            <button onclick="window.location.href='${pageContext.request.contextPath}/admin/categorycontrol?id=${brand.getBrandID()}'">
                                                <i class='fa-solid fa-pencil'></i>
                                            </button>
<!--                                            <button>
                                                <i class='fa-solid fa-trash'></i>
                                            </button>-->
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div id="options">
                        <div class="pagination" id="pagination">
                            <!-- Pagination buttons will be dynamically added here -->
                        </div>
                        <button onclick="window.location.href = '${pageContext.request.contextPath}/admin/categorycontrol'">+ Add Category</button>
                    </div>
                </div>
            </section>
        </div>
    </body>