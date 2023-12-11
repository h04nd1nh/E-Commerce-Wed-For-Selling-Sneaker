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

        <div id="user-layout-container">
            <section id="user-sidebar">
                <ul>
                    <li class="${ActiveClass eq 'dashboard' ? 'active' : ''}">
                        <i class="fa-solid fa-bars"></i>
                        <a href="${pageContext.request.contextPath}/user/dashboard">Dashboard</a>
                    </li>
                    <li class="${ActiveClass eq 'account' ? 'active' : ''}">
                        <i class="fa-solid fa-user"></i>
                        <a href="${pageContext.request.contextPath}/user/account">Account Detail</a>
                    </li>
                    <li class="${ActiveClass eq 'order' ? 'active' : ''}">
                        <i class="fa-solid fa-cart-shopping"></i>
                        <a href="${pageContext.request.contextPath}/user/order">Order History</a>
                    </li>
                    <li>
                        <i class="fa-solid fa-right-from-bracket"></i>
                        <a href="${pageContext.request.contextPath}/logout">Log Out</a>
                    </li>
                </ul>
            </section>

            <section class="user-content ${ActiveClass eq 'dashboard' ? 'active-content' : ''}">
                <div id="fun-fact">
                    <div>
                        <i class="fa-solid fa-truck-fast"></i>
                        <div class="fun-fact-content">
                            <p>${TotalOrders}</p>
                            <span>Total Orders</span>
                        </div>
                    </div>
                    <div style="background-color:#FFF3EB;">
                        <i class="fa-solid fa-box-open" style="color:#FA8232;"></i>
                        <div class="fun-fact-content">
                            <p>${PendingOrders}</p>
                            <span>Pending Orders</span>
                        </div>
                    </div>
                    <div style="background-color:#EAF7E9;">
                        <i class="fa-solid fa-check" style="color:#2DB224;"></i>
                        <div class="fun-fact-content">
                            <p>${CompleteOrders}</p>
                            <span>Completed Orders</span>
                        </div>
                    </div>
                    <div style="background-color:#f5d6dc;">
                        <i class="fa-solid fa-ban" style="color:#d63857;"></i>
                        <div class="fun-fact-content">
                            <p>${CancelOrders}</p>
                            <span>Canceled Orders</span>
                        </div>
                    </div>
                </div>

                <div id="fun-fact-content">
                    <p>Total Order Ammount: <span><fmt:formatNumber type="number" value="${TotalAmount}"  pattern="###,###,###,##0"/>đ</span></p>
                    <p>You Have Saved: <span><fmt:formatNumber type="number" value="${SavedAmount}"  pattern="###,###,###,##0"/>đ</span></p>
                </div>
            </section>

            <section class="user-content ${ActiveClass eq 'account' ? 'active-content' : ''}">
                <div class="account-setting">
                    <p>Account Setting</p>
                    <form  method="post">
                        <img id="uploadedImage" src="${pageContext.request.contextPath}/${Avatar}" alt="Uploaded Image" style="cursor: pointer;">
                        <input type="file" id="fileInput" style="display: none;" accept="image/*" value="">
                        <script>
                            document.addEventListener('DOMContentLoaded', function () {
                                // Lắng nghe sự kiện click trên thẻ img
                                document.getElementById('uploadedImage').addEventListener('click', function () {
                                    // Kích hoạt sự kiện click cho input type="file"
                                    document.getElementById('fileInput').click();
                                });

                                // Lắng nghe sự kiện change khi người dùng chọn file
                                document.getElementById('fileInput').addEventListener('change', function () {
                                    // Lấy file đã chọn
                                    const selectedFile = this.files[0];

                                    if (selectedFile) {
                                        // Đọc dữ liệu của file và hiển thị trong thẻ img
                                        const reader = new FileReader();
                                        reader.onload = function (event) {
                                            document.getElementById('uploadedImage').src = event.target.result;

                                            document.getElementById('avatarInput').value = event.target.result;
                                        };
                                        reader.readAsDataURL(selectedFile);
                                    }
                                });
                            });
                        </script>
                        <div id="text-edit">
                            <label for="username">User Name</label>
                            <p>${UserName}</p>

                            <label for="fullname">Full Name</label>
                            <input type="text" name="fullname" id="fullname" value="${FullName}">

                            <label for="email">Email</label>
                            <input type="text" name="email" id="email" value="${Email}">

                            <label for="phonenumber">Phone Number</label>
                            <input type="text" name="phonenumber" id="phonenumber" value="${PhoneNumber}">

                            <label for="address">Address</label>
                            <input type="text" name="address" id="address" value="${Address}">

                            <input type="hidden" name="type" value="infomation">
                            <input id="avatarInput" type="hidden" name="avatar" value="">
                            <button type="submit">Save Changes</button>
                        </div>
                    </form>
                </div>
                <div class="account-setting">
                    <p>Change Password</p>
                    <form method="post">
                        <div id="text-edit">
                            <label for="password">Current Password</label>
                            <input type="password" name="currentpassword" id="currentpassword" required>

                            <label for="newpassword">New Password</label>
                            <input type="password" name="newpassword" id="newpassword"  required>

                            <label for="confirmpassword">Confirm Password</label>
                            <input type="password" name="confirmpassword" id="confirmpassword"  required>

                            <p style="color:red;">${Alert}</p>
                            <p style="color:green;">${success}</p>
                            <input type="hidden" name="type" value="password"/>
                            <button type="submit">Change Password</button>
                        </div>
                    </form>
                </div>
            </section>

            <section class="user-content ${ActiveClass eq 'order' ? 'active-content' : ''}">
                <p>Order History</p>

                <c:forEach items="${ListOrder}" var="order">
                    <div class="order-history">
                        <p>${order.getOrderID()}</p>
                        <c:if test="${order.getStatus() eq 'Pending'}">
                            <p class="pending">IN PROGRESS</p>
                        </c:if>
                        <c:if test="${order.getStatus() eq 'Delivered'}">
                            <p class="done">DELIVERED</p>
                        </c:if>
                        <c:if test="${order.getStatus() eq 'Canceled'}">
                            <p class="cancel">CANCELED</p>
                        </c:if>
                        <p>${order.getDateCreate()}</p>
                        <p><fmt:formatNumber type="number" value="${order.getPaymentAmount()}"  pattern="###,###,###,##0"/>đ</p>
                        <a href="${pageContext.request.contextPath}/user/order/orderdetail?id=${order.getOrderID()}">View Details<i class="fa-solid fa-arrow-right"></i></a>
                    </div>
                </c:forEach>
            </section>

        </div>

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