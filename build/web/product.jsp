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
                    <li><a class="active" href="${pageContext.request.contextPath}/shop">Shop</a></li>
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


        <section class="section-p1" id="product-tag-des">
            <div id="product-preview">
                <div id="product-preview-large">
                    <img src="${Product.getImage1()}" alt="">
                </div>
                <div id="product-preview-small">
                    <c:if test="${not empty Product.getImage2()}">
                        <img src="${Product.getImage2()}" alt="">
                    </c:if>

                    <c:if test="${not empty Product.getImage3()}">
                        <img src="${Product.getImage3()}" alt="">
                    </c:if>
                </div>
            </div>
            <div id="product-description">
                <!--            <form>-->
                <div id="product-description-infomation">
                    <div class="rate">
                        <div>
                            <c:set var="endValue" value="${Integer.parseInt(Product.getProductRateAverage())}" />
                            <c:set var="rateTotal" value="5" />
                            <c:forEach var="i" begin="1" end="${endValue}" step="1">
                                <i class="fas fa-star"></i>
                            </c:forEach>
                            <c:forEach var="i" begin="1" end="${rateTotal - endValue}" step="1">
                                <i class="fas fa-star" style="color: #393c39;"></i>
                            </c:forEach>
                        </div>
                        <span>(${Product.GetReviewCount()} reviews)</span>
                    </div>
                    <div id="pro-name">
                        <h5>${Product.getProductName()}</h5>
                    </div>
                    <div id="product-description-origin">
                        <span>ID: ${Product.getProductID()}</span>
                        <input type="hidden" name="productid" id="productid" value="${Product.getProductID()}">
                        <span>Availability: <span class="product-description-availability">${Product.getStock() != 0 ? "In Stock" : "Out of stock"}</span></span>
                        <span>Brand: ${Product.getBrandName()}</span>
                        <span>Category: ${Product.getBrandName()}</span>
                    </div>
                    <div id="product-description-price">
                        <h4 id="sale-price"><fmt:formatNumber type="number" value="${Product.getSale()}"  pattern="###,###,###,##0"/>đ<span><fmt:formatNumber type="number" value="${Product.getPrice()}"  pattern="###,###,###,##0"/>đ</span></h4>
                        <div id="product-description-price-saleoff">
                            <c:set var="phanTramGiamGia" value="${((Product.getPrice() - Product.getSale()) / Product.getPrice()) * 100}" />
                            <c:set var="phanTramGiamGiaLamTron" value="${Math.round(phanTramGiamGia)}" />
                            <h4>${phanTramGiamGiaLamTron}% OFF</h4>
                        </div>
                    </div>
                </div>

                <div id="product-optional">
                    <div class="product-optional-selection">
                        <span>Size</span>
                        <div class="product-optional-selection-input">
                            <select name="size" id="size">
                                <c:forEach items="${ProductSize}" var="size">
                                    <option value="${size.getSizeID()}">${size.getSize()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="product-optional-selection">
                        <span>Color</span>
                        <div class="product-optional-selection-input">
                            <select name="color" id="color">
                                <c:forEach items="${ProductColor}" var="color">
                                    <option value="${color.getColorID()}">${color.getColor()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="product-optional-selection">
                        <span>Quantity</span>
                        <div class="product-optional-selection-input">
                            <div id="product-optional-quantity">
                                <input type="number" id="quantityValue" name="quantity" value="1" required>
                            </div>
                        </div>
                        <div class="product-optional-selection-button">
                            <div id="button-area">
                                <button onclick="updateQuantity('decrease')">-</button>
                                <button onclick="updateQuantity('increase')">+</button>
                            </div>
                        </div>
                    </div>

                    <script>
                        function updateQuantity(action) {
                            var quantityInput = document.getElementById("quantityValue");
                            var currentQuantity = parseInt(quantityInput.value);

                            if (action === 'decrease' && currentQuantity > 1) {
                                quantityInput.value = currentQuantity - 1;
                            } else if (action === 'increase') {
                                quantityInput.value = currentQuantity + 1;
                            }
                        }
                    </script>
                </div>

                <div class="product-submit-form">
                    <c:if test="${cookie['datl'] == null}">
                        <button onclick="window.location.href = '${pageContext.request.contextPath}/login'">Login now to order</button>
                    </c:if>
                    <c:if test="${cookie['datl'] != null}">
                        <c:if test="${Product.getStock() == 0}">
                            <button style="background-color: gray;border: 1px solid gray">Out of stock</button>
                        </c:if>

                        <c:if test="${Product.getStock() != 0}">
                            <button onclick="addToCart()">Add to cart</button>
                            <button onclick="buyNow()">Buy now</button>
                            <script>
                                function addToCart() {
                                    var productID = document.getElementById("productid").value;
                                    var selectedColor = document.getElementById("color").value;
                                    var selectedSize = document.getElementById("size").value;
                                    var quantity = document.getElementById("quantityValue").value;

                                    window.location.href = '${pageContext.request.contextPath}/AddToCart?productid=' + productID + '&color=' + selectedColor + '&size=' + selectedSize + '&quantity=' + quantity;
                                    // Thực hiện các hành động cần thiết khi click vào nút "Add to cart"

                                }

                                function buyNow() {
                                    var productID = document.getElementById("productid").value;
                                    var selectedColor = document.getElementById("color").value;
                                    var selectedSize = document.getElementById("size").value;
                                    var quantity = document.getElementById("quantityValue").value;

                                    window.location.href = '${pageContext.request.contextPath}/checkout?productid=' + productID + '&color=' + selectedColor + '&size=' + selectedSize + '&quantity=' + quantity;
                                }
                            </script>
                        </c:if>

                    </c:if>
                </div>
                <!--            </form>-->
            </div>
        </section>


        <section class="section-p1" id="product-content">
            <div id="product-content-tab">

                <div id="product-tab-title">
                    <h1>Description</h1>
                </div>

                <div id="tab-content">
                    <div id="description-content">
                        <h2>Description</h2>
                        <span>${Product.getDescription()}</span>
                    </div>

                    <div id="description-feature">
                        <h2>Feature</h2>
                        <div>
                            <i class="fa-solid fa-wrench"></i>
                            <p>Free 1 Year Warranty</p>
                        </div>
                        <div>
                            <i class="fa-solid fa-truck"></i>
                            <p>Free Shipping & Fasted Delivery</p>
                        </div>
                        <div>
                            <i class="fa-solid fa-money-bill"></i>
                            <p>100% Money-back guarantee</p>
                        </div>
                        <div>
                            <i class="fa-solid fa-headset"></i>
                            <p>24/7 Customer support</p>
                        </div>
                        <div>
                            <i class="fa-solid fa-credit-card"></i>
                            <p>Secure payment method</p>
                        </div>
                    </div>

                    <div id="shipping-infomation">
                        <h2>Shipping Information</h2>
                        <p>Courier: <span>2-4 days. free shipping</span></p>
                        <p>Local Shipping: <span>up to one week</span></p>
                    </div>

                </div>
            </div>
        </section>

        <section class="section-p1" id="product-review">
            <div id="product-review-tab">
                <div id="review-tab-title">
                    <h1>Review</h1>
                </div>

                <div id="tab-review">
                    <div id="tab-customer">
                        <c:forEach items="${ProductReview}" var="review">
                            <div class="customer-comment">
                                <div id="customer-name">
                                    <img src="${review.getAvatar()}" alt="">
                                    <h2 id="customer-name">${review.getFullName()}</h2>
                                </div>
                                <div id="comment">
                                    <span>${review.getReviewContent()}</span>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <div id="tab-rate">
                        <div>
                            <div class="star-rate">
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                            </div>
                            <div class="star-rate">
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                            </div>
                            <div class="star-rate">
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                            </div>
                            <div class="star-rate">
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                            </div>
                            <div class="star-rate">
                                <i class="fas fa-star"></i>
                            </div>
                        </div>
                        <div>
                            <p id="5-rate">(${ProductRate.getFiveStar()} rate)</p>
                            <p id="4-rate">(${ProductRate.getFourStar()} rate)</p>
                            <p id="3-rate">(${ProductRate.getThreeStar()} rate)</p>
                            <p id="2-rate">(${ProductRate.getTwoStar()} rate)</p>
                            <p id="1-rate">(${ProductRate.getOneStar()} rate)</p>
                        </div>
                    </div>
                </div>

                <div id="input-comment">
                    <c:if test="${cookie['datl'] == null}">
                        <button onclick="window.location.href = '${pageContext.request.contextPath}/login'">Login now to comment</button>
                    </c:if>
                    <c:if test="${cookie['datl'] != null}">
                        <img src="assets/img/logo.png" alt="">
                        <form accept-charset="UTF-8"  method="post">
                            <textarea id="comment" name="comment" rows="5" placeholder="Add a comment here"></textarea>
                            <input type="hidden"  name="productid" value="${Product.getProductID()}">
                            <button type="submit">Comment</button>
                        </form>
                    </c:if>

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