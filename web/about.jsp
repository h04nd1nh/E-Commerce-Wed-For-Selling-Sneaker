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
                    <li><a href="${pageContext.request.contextPath}/shop">Shop</a></li>
                    <li><a href="">Blog</a></li>
                    <li><a  class="active" href="${pageContext.request.contextPath}/about">About</a></li>
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

    <section>
        <div id="about-content">
            <div class="content-center">
                <h1>Welcome to Psneaker!</h1>
                <p>At Psneaker, we are a passionate team of sneaker enthusiasts, redefining class and style through
                    every pair of shoes. We don't just provide high-quality products, we offer a unique and exciting
                    shopping experience.
                </p>
                <img src="assets/img/about.jpg" alt="">
            </div>

            <h2>Passion for Sneakers</h2>
            <p>Our love for sneakers goes beyond their impressive aesthetics, embracing the diversity in design and
                subtle meaning behind each pair. Our mission is to share this passion with the sneakerhead community and
                create a fascinating space for you to explore the world of sneakers.</p>
            <h2>Diversity and Style</h2>
            <p>At Psneaker, we take pride in our diverse collection, ranging from classic sneakers to the latest trends.
                With us, it's not just about choosing shoes, it's about expressing yourself and defining your personal
                style.</h2>
            <h2>Quality First</h2>
            <p>We are committed to bringing you the highest quality products. Every pair of shoes at Psneaker is
                carefully curated from reputable brands, ensuring comfort and durability.</p>
            <h2>Community Mission</h2>
            <p>Psneaker is more than just a store, it's a community of sneaker passion. Join us in exploring the unique
                world of sneakers and share your passion with a vast community of sneaker enthusiasts.</p>
            <div class="content-center">
                <i>"Embark on this journey with Psneaker, where style and class meet"</i>
            </div>
           
        </div >
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