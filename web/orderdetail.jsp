<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <title>PSneaker</title>
        <script>
            // Kiểm tra nếu đang ở đường dẫn /PSneaker/user, thực hiện chuyển hướng
            if (window.location.pathname === "/PSneaker/user") {
                window.location.href = "/PSneaker/user/dashboard";
            }
        </script>
    </head>
    <body>
        <section id="header">
            <a href="#"><img src="${pageContext.request.contextPath}/assets/img/logo.png" alt=""></a>

            <div>
                <ul id="navbar">
                    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/shop">Shop</a></li>
                    <li><a href="">Blog</a></li>
                    <li><a href="${pageContext.request.contextPath}/about">About</a></li>
                    <li><a href="">Contact</a></li>
                        <c:if test="${cookie['datl'] != null}">
                        <li><a class="active" href="${pageContext.request.contextPath}/user"><i class="fa-solid fa-user"></i></a></li>
                        <li><a href="${pageContext.request.contextPath}/cart"><i class="fa-solid fa-bag-shopping"></i></a></li>
                            </c:if>
                            <c:if test="${cookie['datl'] == null}">
                        <li><a  href="${pageContext.request.contextPath}/login">Login</a></li>
                        </c:if>
                </ul>
            </div>
        </section>

        <section id="order-detail">
            <p>Order Detail</p>
            <div id="order-detail-tag">
                <div>
                    <p>${Order.getOrderID()}</p>
                    <span>${Quantity} Products • Order Placed in ${formattedDate}</span>
                </div>
                <div>
                    <p><fmt:formatNumber type="number" value="${Order.getPaymentAmount()}"  pattern="###,###,###,##0"/>đ</p>
                </div>
            </div>
            <p>Order Activity</p>
            <div id="order-detail-status">
                <div>
                    <i style="color: #2DB224; background-color: #D5F0D3;" class="fa-solid fa-check"></i>
                    <div>
                        <p>Your order has been delivered. Thank you for shopping at PSneaker.</p>
                        <span>23 Jan, 2021 at 7:32 PM</span>
                    </div>
                </div>

                <div>
                    <i style="color: #3858D6; background-color: #D5EDFD;" class="fa-solid fa-truck-fast"></i>
                    <div>
                        <p>Your order on the way.</p>
                        <span>23 Jan, 2021 at 7:32 PM</span>
                    </div>
                </div>

                <div>
                    <i style="color: #3858D6; background-color: #D5EDFD;" class="fa-solid fa-bars"></i>
                    <div>
                        <p>Your order has been confirmed.</p>
                        <span>23 Jan, 2021 at 7:32 PM</span>
                    </div>
                </div>
            </div>

            <p>Product</p>
            <div id="order-detail-product">
                <table>
                    <thead>
                        <tr>
                            <th>PRODUCTS</th>
                            <th>PRICE</th>
                            <th>QUANTITY</th>
                            <th>SUB-TOTAL</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${ListItem}" var="item">
                            <tr>
                                <td>
                                    <img src="${pageContext.request.contextPath}/${item.getProduct().getImage1()}" alt="">
                                    <div>
                                        <p>${item.getProduct().getProductName()}</p>
                                        <p id="product-type">Size: <span style="margin: 0;
                                                                         font-size: 14px;
                                                                         font-weight: 700;
                                                                         color: #5F6C72;">${item.getSize()}</span> Color: <span style="margin: 0;
                                                                         font-size: 14px;
                                                                         font-weight: 700;
                                                                         color: #5F6C72;">${item.getColor()}</span></p>
                                    </div>

                                </td>
                                <td>
                                    <p><fmt:formatNumber type="number" value="${item.getPrice()}"  pattern="###,###,###,##0"/>đ</p>
                                </td>
                                <td>
                                    <p>${item.getQuantity()}</p>
                                </td>
                                <td>
                                    <p><p><fmt:formatNumber type="number" value="${item.getPrice() * item.getQuantity()}"  pattern="###,###,###,##0"/>đ</p>
                                </td>
                                <td>
                                    <button>Rating</button> 
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <p></p>
            <div id="order-detail-infomation">
                <div>
                    <p>Shipping Address</p>
                    <p>Name: <span>${Order.getFullName()}</span></p>
                    <p>Address: <span>${Order.getAddress()}</span></p>
                    <p>Phone number: <span>${Order.getPhone()}</span></p>
                    <p>Email: <span>${Order.getEmail()}</span></p>
                </div>
                <div>
                    <p>Order Notes</p>
                    <span>${Order.getNote()}</span>
                </div>
            </div>

            <button>Cancel Order</button>

        </section>


        <footer class="section-p1">
            <div class="col">
                <img class="logo" src="${pageContext.request.contextPath}/assets/img/logo.png" alt="">
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