<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <title>PSneaker</title>
</head>
<body>
    <section id="header">
        <a href="#"><img src="assets/img/logo.png" alt=""></a>

        <div>
            <ul id="navbar">
                <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                <li><a  class="active" href="${pageContext.request.contextPath}/shop">Shop</a></li>
                <li><a href="">Blog</a></li>
                <li><a href="${pageContext.request.contextPath}/about">About</a></li>
                <li><a href="">Contact</a></li>
                <c:if test="${cookie['datl'] != null}">
                    <li><a href="${pageContext.request.contextPath}/user"><i class="fa-solid fa-user"></i></a></li>
                    <li><a href="${pageContext.request.contextPath}/cart"><i class="fa-solid fa-bag-shopping"></i></a></li>
                </c:if>
                <c:if test="${cookie['datl'] == null}">
                    <li><a  href="${pageContext.request.contextPath}/login">Login</a></li>
                </c:if>
            </ul>
        </div>
    </section>

    <section id="shop-banner">
        <img src="assets/img/banner.png" alt="">
    </section>

    <section id="pro-page" class="section-p1">
        <div id="sidebar">
            <form action="" method="GET">
                <h4>Category</h4>
                <ul>
                    <c:forEach items="${brandList}" var="brand">
                        <li><a href="brand?id=${brand.getBrandID()}">${brand.getBrandName()}</a></li>
                    </c:forEach>
                </ul>
            </form>
        </div>

        <div id="pro-list">
            <div class="search-container">
                <form action="" id="search-box">
                    <input type="text" id="search-text" placeholder="Search product...">
                    <button><i class="fa-solid fa-magnifying-glass"></i></button>
                </form>
                <p>Result for <span>Nike Air Force 1</span></p>
            </div>
            <div class="pro-container">
              
                <c:forEach var="product" items="${productList}">
                <div class="pro">
                    <img src="${product.getImage1()}" alt="">
                    <div class="pro-des">
                        <div class="pro-name">
                            <h5>${product.getProductName()}</h5>
                            <span>${product.getBrandName()}</span>
                        </div>
                        <div class="pro-price">
                            <span><fmt:formatNumber type="number" value="${product.getPrice()}"  pattern="###,###,###,##0"/>đ</span>
                            <h4><fmt:formatNumber type="number" value="${product.getSale()}"  pattern="###,###,###,##0"/>đ</h4>
                        </div>
                    </div>
                    <div class="rate">
                        <div>
                            <c:set var="endValue" value="${Integer.parseInt(product.getProductRateAverage())}" />
                            <c:set var="rateTotal" value="5" />
                            <c:forEach var="i" begin="1" end="${endValue}" step="1">
                                <i class="fas fa-star"></i>
                            </c:forEach>
                            <c:forEach var="i" begin="1" end="${rateTotal - endValue}" step="1">
                                <i class="fas fa-star" style="color: #393c39;"></i>
                            </c:forEach>
                        </div>
                        <span>(${product.GetReviewCount()} reviews)</span>
                    </div>
                    <button class="cart" onclick="window.location.href='${pageContext.request.contextPath}/product?id=${product.getProductID()}'">Add to cart</button>
                </div>
            </c:forEach>
                
            </div>
        </div>
    </section>



</body>