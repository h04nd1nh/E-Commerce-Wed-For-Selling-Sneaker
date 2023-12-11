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
        <script>
            // Kiểm tra nếu đang ở đường dẫn /PSneaker/user, thực hiện chuyển hướng
            if (window.location.pathname === "/PSneaker/") {
                window.location.href = "/PSneaker/home";
            }
        </script>
    </head>
    <body>

        <section id="header">
            <a href="${pageContext.request.contextPath}/home"><img src="assets/img/logo.png" alt=""></a>

            <div>
                <ul id="navbar">
                    <li><a class="active" href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/shop">Shop</a></li>
                    <li><a href="">Blog</a></li>
                    <li><a href="${pageContext.request.contextPath}/about">About</a></li>
                    <li><a href="">Contact</a></li>
                        <c:if test="${cookie['datl'] != null}">
                        <li><a href="${pageContext.request.contextPath}/user"><i class="fa-solid fa-user"></i></a></li>
                        <li><a href="${pageContext.request.contextPath}/cart"><i class="fa-solid fa-bag-shopping"></i></a></li>
                            </c:if>
                            <c:if test="${cookie['datl'] == null}">
                        <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
                        </c:if>
                </ul>
            </div>
        </section>

        <section id="slider">
            <h1>Find the</h1>
            <h1>best <span class="stroke-text">shoes.</span></h1>
            <p>Save more with coupons & up to 70% off!</p>
            <button onclick="window.location.href = '${pageContext.request.contextPath}/shop'">Shop Now</button>
        </section>

        <section id="feature" class="section-p1">
            <div class="fe-box">
                <i class="fa-solid fa-truck-fast fa-xl"></i>
                <h6>Free Shipping</h6>
            </div>
            <div class="fe-box">
                <i class="fa-solid fa-basket-shopping fa-xl"></i>
                <h6>Online Order</h6>
            </div>
            <div class="fe-box">
                <i class="fa-solid fa-money-check-dollar fa-xl"></i>
                <h6>Save Money</h6>
            </div>
            <div class="fe-box">
                <i class="fa-solid fa-percent fa-xl"></i>
                <h6>Promotions</h6>
            </div>
            <div class="fe-box">
                <i class="fa-solid fa-face-smile-wink fa-xl"></i>
                <h6>Happy Sell</h6>
            </div>
            <div class="fe-box">
                <i class="fa-solid fa-headset fa-xl"></i>
                <h6>24/7 Support</h6>
            </div>
        </section>

        <section id="hot-product" class="section-p1">
            <h2>Best Seller</h2>
            <p>Shop now!</p>
            <div class="pro-container">
                <c:forEach var="product" items="${requestScope.productList}">
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
                        <button class="cart" onclick="window.location.href = '${pageContext.request.contextPath}/product?id=${product.getProductID()}'">Add to cart</button>
                    </div>
                </c:forEach>

            </div>
        </section>

        <footer class="section-p1">
            <div class="col">
                <img class="logo" src="assets/img/logo.png" alt="">
                <h4>Contact</h4>
                <p><strong>Address: </strong> 192 Tran Phu, Mo Lao, Ha Dong, Ha Noi</p>
                <p><strong>Phone: </strong> +84 971.182.176</p>
                <p><strong>Hour: </strong> 8:00 - 18:00, Mon - Fri</p>
                <div class="follow">
                    <h4>Follow us</h4>
                    <div class="icon">
                        <i class="fab fa-facebook-f"></i>
                        <i class="fab fa-instagram"></i>
                        <i class="fab fa-twitter"></i>
                    </div>
                </div>
            </div>

            <div class="col">
                <h4>About</h4>
                <a href="#">About Us</a>
                <a href="#">Delivery Infomation</a>
                <a href="#">Privacy Pocily</a>
                <a href="#">Contact Us</a>
            </div>

            <div class="col">
                <h4>My Account</h4>
                <a href="#">Sign In</a>
                <a href="#">View Cart</a>
            </div>

            <div class="col">
                <h4>PayMent Menthod</h4>
                <a href="#">Momo</a>
                <a href="#">VietQR</a>
                <a href="#">VNPay</a>
                <a href="#">Cash on Delivery</a>
            </div>

            <div class="col">
                <h4>Shipping Partner</h4>
                <a href="#">Giaohangtietkiem</a>
                <a href="#">Giaohangnhanh</a>
                <a href="#">Lalamove</a>
                <a href="#">Ahamove</a>
                <a href="#">GrabExpress</a>
            </div>

            <div class="copyright">
                <p>@2023, Dinh Van Duc Hoan - Bai Tap Lon Lap Trinh Wed</p>
            </div>
        </footer>
    </body>
</html>


