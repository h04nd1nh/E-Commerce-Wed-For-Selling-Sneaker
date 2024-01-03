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
                    <li class="active">
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
                    <li>
                        <i class="fa-solid fa-user"></i>
                        <a href="${pageContext.request.contextPath}/admin/customer">Customers</a>
                    </li>
                </ul>
            </section>
            <section id="content">
                <div id="dasboard" class="active">
                    <p>Dasboard</p>
                    <div class="dasboard-layout">
                        <div class="tag-info">
                            <div>
                                <p>Totals Sales</p>
                                <span><fmt:formatNumber type="number" value="${TotalSale}"  pattern="###,###,###,##0"/>đ</span>
                                <p>We have sold ${totalProductSale} items</p>
                            </div>
                            <i class="fa-solid fa-clipboard"></i>
                        </div>

                        <div class="tag-info">
                            <div>
                                <p>Todays Sales</p>
                                <span>7.000.000đ</span>
                                <p>We have sold 123 items</p>
                            </div>
                            <i class="fa-solid fa-calendar-day"></i>
                        </div>

                        <div class="tag-info">
                            <div>
                                <p>Users count</p>
                                <span>${TotalUser}</span>
                                <p>Total user signed up to PSneaker</p>
                            </div>
                            <i class="fa-solid fa-user"></i>
                        </div>
                    </div>

                    <div class="dasboard-layout">
                        <div class="tag-info">
                            <div>
                                <p>Most sold items</p>
                      
                                <c:forEach items="${topProduct}" var="product">
                                    <div id="product-info">
                                        <img src="${pageContext.request.contextPath}/${product.getImage1()}" alt="">
                                        <div>
                                            <p id="product-name">${product.getProductName()}</p>
                                            <p id="prodcut-sold">Top products sold</p>
                                        </div>
                                    </div>
                                </c:forEach>

                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </body>