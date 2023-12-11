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
        <form  action="order" method="post">
            <div id="checkout-layout">
                <div id="billing-infomation">
                    <div id="user-info">
                        <p>Billing Infomation</p>
                        <div class="user-info-input">
                            <label for="fullname">Full Name</label>
                            <input type="text" id="fullname" name="fullname" required placeholder="ex: Dinh Van Duc Hoan">
                        </div>

                        <div class="user-info-input">
                            <label for="address">Address</label>
                            <input type="text" id="address" name="address" required placeholder="ex: 192 Tran Phu, Mo Lao, Ha Dong, Ha Noi">
                        </div>

                        <div class="user-info-input">
                            <label for="email">Email</label>
                            <input type="text" id="email" name="email" required placeholder="ex: example@gmail.com">
                        </div>

                        <div class="user-info-input">
                            <label for="phonenumber">Phone Number</label>
                            <input type="text" id="phonenumber" name="phonenumber" required placeholder="ex: 0971182176">
                        </div>
                    </div>

                    <div id="additional-info">
                        <p>Additional Information</p>
                        <div class="user-info-input">
                            <label for="ordernotes">Order notes</label>
                            <textarea id="ordernotes" name="ordernotes" rows="5" placeholder="Notes about your order, e.g. special notes for delivery"></textarea>
                        </div>
                    </div>

                </div>
                <div id="order-summary">
                    <p>Order Summary</p>
                    <c:set var="Subtotal" value="0" />
                    <c:forEach items="${ListItem}" var="item">
                        <div class="order-product-detail">
                            <img src="${item.getImage()}" alt="">
                            <div id="order-product-name">
                                <p id="product-type">${item.getProductName()}</p>
                                <p id="product-type">Size: <span style="margin: 0;
                                                                 font-size: 14px;
                                                                 font-weight: 700;
                                                                 color: #5F6C72;">${item.getSize()}</span> Color: <span style="margin: 0;
                                                                 font-size: 14px;
                                                                 font-weight: 700;
                                                                 color: #5F6C72;">${item.getColor()}</span></p>
                                <p id="product-type">${item.getQuantity()} x <span style="margin: 0;
                                                                                   font-size: 14px;
                                                                                   font-weight: 700;
                                                                                   color: #3858D6;"><fmt:formatNumber type="number" value="${item.getSale()}"  pattern="###,###,###,##0"/>đ</span></p>
                            </div>
                        </div>
                        <c:set var="Subtotal" value="${Subtotal + (item.getSale() * item.getQuantity())}" />
                    </c:forEach>


                    <div class="order-total">
                        <p>Total</p>
                        <span><fmt:formatNumber type="number" value="${Subtotal}"  pattern="###,###,###,##0"/>đ</span>
                    </div>

                    <div class="order-total">
                        <p>Shipping</p>
                        <span>Free</span>
                    </div>

                    <div class="order-total">
                        <p>Discount<c:if test="${Discount != null && Discount != 0}">(${VoucherCode})</c:if></p>
                        <c:if test="${Discount == 0}">
                            <span id="discount"><fmt:formatNumber type="number" value="0"  pattern="###,###,###,##0"/>đ</span>
                        </c:if>
                        <c:if test="${Discount != 0}">
                            <span id="discount"><fmt:formatNumber type="number" value="${Subtotal * (Discount/100)}"  pattern="###,###,###,##0"/>đ</span>
                        </c:if>
                    </div>

                    <div class="order-total">
                        <div class="line"></div>
                    </div>

                    <div class="pri-order-total">
                        <p>Payment Amount</p>
                        <span><fmt:formatNumber type="number" value="${Subtotal - (Subtotal * (Discount/100))}"  pattern="###,###,###,##0"/>đ</span>
                    </div>

                    <div class="pri-order-total">
                        <input type="text" id="code-value" aria-required="false" placeholder="Coupon Code">
                        <button type="button" onclick="ApplyCoupon()">Apply Coupon</button>
                        <script>
                            function ApplyCoupon() {
                                var VoucherCode = document.getElementById("code-value").value;
                                window.location.href = '${pageContext.request.contextPath}/discount?code=' + VoucherCode + "&buyfromcart=" + ${IsBuyFromCart};
                            }
                        </script>
                    </div>

                    <c:if test="${Discount == 0}">
                        <span style="color: red;margin-bottom: 20px;">Invalid discount code, please try again</span>
                    </c:if>


                    <div id="order-button-submit">
                        <input type="hidden" name="isCart" value="${IsBuyFromCart}"/>
                        <input type="hidden" name="code" value="${VoucherCode}"/>
                        <input type="hidden" name="total" value="${Subtotal}"/>
                        <input type="hidden" name="paymentamount" value="${Subtotal - (Subtotal * (Discount/100))}"/>
                        <button type="submit">PLACE ORDER</button>                  
                    </div>
                </div>

            </div>
        </form>

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