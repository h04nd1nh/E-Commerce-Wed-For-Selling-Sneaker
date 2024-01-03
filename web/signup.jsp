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
                <li><a href="${pageContext.request.contextPath}/about">About</a></li>
                <li><a href="">Contact</a></li>
                <c:if test="${cookie['datl'] != null}">
                    <li><a href="${pageContext.request.contextPath}/user"><i class="fa-solid fa-user"></i></a></li>
                    <li><a href="${pageContext.request.contextPath}/cart"><i class="fa-solid fa-bag-shopping"></i></a></li>
                </c:if>
                <c:if test="${cookie['datl'] == null}">
                    <li><a class="active" href="${pageContext.request.contextPath}/login">Login</a></li>
                </c:if>
            </ul>
        </div>
    </section>

    <section id="login-layout">
        <div id="login-layout-image">
            <img src="assets/img/sneaker1.png" alt="">
        </div>
        <div id="login-form">
            <img src="assets/img/logo.png" alt="">
            <p>Sign up</p>
            
            <form accept-charset="UTF-8" action="signup" method="post">
                <div class="form-info">
                    <label for="username">Full Name</label>
                    <input type="text" id="fullname" name="fullname" required placeholder="Enter username">
                </div>
                
                <div class="form-info">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" required placeholder="Enter username">
                </div>
                
                <div class="form-info">
                    <label for="phone">Phone number</label>
                    <input type="text" id="numberphone" name="numberphone" required placeholder="Enter phone number">
                </div>
            
                <div class="form-info">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required placeholder="enter password">
                </div>

                <div class="form-info">
                    <label for="password">Confirm Password</label>
                    <input type="password" id="repassword" name="repassword" required placeholder="re-enter password">
                </div>

                <div class="form-info">
                    <a href="">Forgot password ?</a>
                </div>
                <p style="color:red; font-size: 16px; font-weight: 600;">${Alert}</p>
                <button type="submit">Sign up</button>
                <div class="form-info">
                    <a href="login">Login now</a>
                </div>
            </form>
        </div>
    </section>
</body>
</html>