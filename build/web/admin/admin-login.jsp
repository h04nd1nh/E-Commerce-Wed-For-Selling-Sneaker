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
        <title>PSneaker Admin</title>
    </head>
<body>
    <section id="login-layout">
        <div id="login-form">
            <img src="../assets/img/logo.png" alt="">
            <p>Admin Login</p>
            <form action="login" method="post">
                <div class="form-info">
                    <label for="username">Phone number</label>
                    <input type="text" id="username" name="username" required placeholder="Enter username">
                </div>
            
                <div class="form-info">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required placeholder="enter password">
                </div>
                <p style="color:red; font-size: 16px; font-weight: 600;">${Alert}</p>
                <button type="submit">Login</button>
            </form>
        </div>
    </section>
</body>
</html>