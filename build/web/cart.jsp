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
                        <li><a class="active" href="${pageContext.request.contextPath}/cart"><i class="fa-solid fa-bag-shopping"></i></a></li>
                            </c:if>
                            <c:if test="${cookie['datl'] == null}">
                        <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
                        </c:if>
                </ul>
            </div>
        </section>

        <section id="cart-layout">
            <c:set var="Subtotal" value="0" />
            <div id="shopping-card">

                <div id="shopping-card-table">
                    <p>Cart</p>
                    <table>
                        <thead>
                            <tr id="table-title">
                                <th>Products</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Sub-total</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${ListItem}" var="item">
                                <tr>
                                    <td>
                                        <div>
                                            <img src="${item.getImage()}" alt="">
                                            <div>
                                                <p>${item.getProductName()}</p>
                                                <p id="product-type">Size: <span style="margin: 0;
                                                                                 font-size: 14px;
                                                                                 font-weight: 700;
                                                                                 color: #5F6C72;">${item.getSize()}</span> Color: <span style="margin: 0;
                                                                                 font-size: 14px;
                                                                                 font-weight: 700;
                                                                                 color: #5F6C72;">${item.getColor()}</span></p>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <p><fmt:formatNumber type="number" value="${item.getSale()}"  pattern="###,###,###,##0"/>đ</p>
                                    </td>
                                    <td>
                                        <div>
                                            <i onclick="Minus(${item.getProductID()},${item.getColorID()},${item.getSizeID()},${item.getQuantity()})" class="fa-solid fa-minus"></i>
                                            <p>${item.getQuantity()}</p>
                                            <i onclick="Plus(${item.getProductID()},${item.getColorID()},${item.getSizeID()})" class="fa-solid fa-plus"></i>
                                        </div>
                                    </td>
                                    <td><p><fmt:formatNumber type="number" value="${item.getSale() * item.getQuantity()}"  pattern="###,###,###,##0"/>đ</p></td>
                                    <td><button onclick="Remove(${item.getProductID()},${item.getColorID()},${item.getSizeID()})">Remove</button></td>
                                    
                            <script>
                                function Plus(ProductID, ProductColor, ProductSize) {
                              
                                    window.location.href = '${pageContext.request.contextPath}/AddToCart?productid='+ ProductID +'&color='+ ProductColor + '&size=' + ProductSize + '&quantity=1';
                                    
                                }

                                function Minus(ProductID, ProductColor, ProductSize, Quantity) {

                                    if (Quantity > 1) {

                                        window.location.href = '${pageContext.request.contextPath}/AddToCart?productid=' + ProductID +'&color='+ ProductColor + '&size=' + ProductSize + '&quantity=-1';
                                    }
                                }

                                function Remove(ProductID, ProductColor, ProductSize) {
                                    window.location.href = '${pageContext.request.contextPath}/DeleteFromCart?productid=' + ProductID +'&color='+ ProductColor + '&size=' + ProductSize;
                                }
                            </script>
                            </tr>
                            <c:set var="Subtotal" value="${Subtotal + (item.getSale() * item.getQuantity())}" />
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div id="card-infomation">
                <div id="card-totals">
                    <p>Cart totals</p>

                    <div class="sub-card-total">
                        <p>Total</p>
                        <span><fmt:formatNumber type="number" value="${Subtotal}"  pattern="###,###,###,##0"/>đ</span>
                    </div>

                    <div class="sub-card-total">
                        <p>Shipping</p>
                        <span id="shipping-total">Free</span>
                    </div>

                    <div class="sub-card-total">
                        <p>Discount<c:if test="${Discount != null && Discount != 0}">(${VoucherCode})</c:if></p>
                        <c:if test="${Discount == 0}">
                            <span id="discount"><fmt:formatNumber type="number" value="0"  pattern="###,###,###,##0"/>đ</span>
                        </c:if>
                        <c:if test="${Discount != 0}">
                            <span id="discount"><fmt:formatNumber type="number" value="${Subtotal * (Discount/100)}"  pattern="###,###,###,##0"/>đ</span>
                        </c:if>

                    </div>

                    <div class="line"></div>

                    <div class="pri-card-total">
                        <p>Payment Amount</p>
                        <span><fmt:formatNumber type="number" value="${Subtotal - (Subtotal * (Discount/100))}"  pattern="###,###,###,##0"/>đ</span>
                    </div>
                </div>

                <div id="card-info-cus">
                    <p>Coupon Code</p>
                    <div>
                        <div class="form-info">
                            <input type="text" id="code" name="code" required placeholder="Coupon Code">
                        </div>
                        <button onclick="ApplyCoupon()">Apply Coupon</button>
                    </div>
                    <c:if test="${Discount == 0}">
                        <span style="color: red;margin-bottom: 20px;">Invalid discount code, please try again</span>
                    </c:if>
                    <script>
                        function ApplyCoupon() {
                            var VoucherCode = document.getElementById("code").value;
                            window.location.href = '${pageContext.request.contextPath}/discount?code=' + VoucherCode + "&buyfromcart=true";
                        }
                    </script>
                </div>


                <div id="card-button-submit">
                    <button onClick="window.location.href = '${pageContext.request.contextPath}/checkout'" >PROCEED TO CHECKOUT</button>
                </div>
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