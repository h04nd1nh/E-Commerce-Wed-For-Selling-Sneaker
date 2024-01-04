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
        <title>PSneaker</title>
    </head>
    <body>
        <div id="container">
            <section id="navigation-menu">
                <img src="../assets/img/logo.png" alt="">
                <p>MENU</p>
                <ul>
                    <li>
                        <i class="fa-solid fa-bars"></i>
                        <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
                    </li>
                    <li class="active">
                        <i class="fa-solid fa-box-archive"></i>
                        <a href="${pageContext.request.contextPath}/admin/product">Products</a>
                    </li>
                    <li>
                        <i class="fa-solid fa-list"></i>
                        <a href="${pageContext.request.contextPath}/admin/category">Categories</a>
                    </li>
                    <li>
                        <i class="fa-solid fa-shoe-prints"></i>
                        <a href="${pageContext.request.contextPath}/admin/property">Properties</a>
                    </li>
                    <li>
                        <i class="fa-solid fa-bars-progress"></i>
                        <a href="${pageContext.request.contextPath}/admin/order">Orders</a>
                    </li>
                    <li>
                        <i class="fa-solid fa-ticket"></i>
                        <a href="${pageContext.request.contextPath}/admin/coupon">Coupons</a>
                    </li>
                </ul>
                <p>USER MANAGEMENT</p>
                <ul>
                    <li>
                        <i class="fa-solid fa-user"></i>
                        <a href="${pageContext.request.contextPath}/admin/customer">Customers</a>
                    </li>
                </ul>
            </section>
            <section id="content">
                <div class="${type eq 'Add' ? 'active' : ''} product-admin">
                    <p>Add Product</p>
                    <form action="" method="post">


                        <div>
                            <div class="panel-form">
                                <p>General Information</p>
                                <label for="productname">Product Name</label>
                                <input type="text" name="productname" id="productname" placeholder="Type product name here..." required>

                                <label for="productdescription">Description</label>
                                <textarea id="productdescription" name="productdescription" rows="5" placeholder="Type product description here..." required></textarea>
                            </div>

                            <div class="panel-form">
                                <p>Pricing</p>
                                <label for="price">Base Price</label>
                                <input type="text" name="price" id="price" placeholder="Type price name here..." required>

                                <p>Pricing</p>
                                <label for="sale">Sale Price</label>
                                <input type="text" name="sale" id="sale" placeholder="Type sale name here..." required>
                            </div>

                            <div class="panel-form">
                                <p>Inventory</p>
                                <label for="quantity">Quantity</label>
                                <input type="text" name="quantity" id="quantity" placeholder="Type quantity name here..." required>
                            </div>
                        </div>
                        <div>
                            <button type="submit">+ Add Product</button>
                            <div class="panel-form">
                                <p>Category</p>
                                <label for="brand">Brand</label>
                                <select name="brand" id="brand" required>
                                    <c:forEach items="${ListBrand}" var="brand">
                                        <option value="${brand.getBrandID()}">${brand.getBrandName()}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="panel-form">
                                <p>Color</p>
                                <style>
                                    .c {
                                        display: flex;
                                        align-items: center;
                                        flex-direction: row;
                                        flex-wrap: wrap;
                                        gap: 8px;
                                    }
                                    .checkbox-container {
                                        display: flex;
                                        align-items: center;
                                        flex-direction: row;
                                    }

                                    .checkbox-container input[type="checkbox"] {
                                        margin-right: 5px;
                                        margin-bottom: 5px;
                                    }

                                    .checkbox-container label {
                                        margin: 0;
                                    }
                                </style>
                                <div class="c">

                                    <c:forEach items="${ListColor}" var="color">
                                        <div class="checkbox-container">
                                            <input type="checkbox" id="${color.getColorID()}" name="color" value="${color.getColorID()}">
                                            <label for="checkbox1">${color.getColor()}</label>
                                        </div>
                                    </c:forEach>
                                </div>



                            </div>

                            <div class="panel-form">
                                <p>Size</p>
                                <style>
                                    .c {
                                        display: flex;
                                        align-items: center;
                                        flex-direction: row;
                                        flex-wrap: wrap;
                                        gap: 8px;
                                    }
                                    .checkbox-container {
                                        display: flex;
                                        align-items: center;
                                        flex-direction: row;
                                    }

                                    .checkbox-container input[type="checkbox"] {
                                        margin-right: 5px;
                                        margin-bottom: 5px;
                                    }

                                    .checkbox-container label {
                                        margin: 0;
                                    }
                                </style>
                                <div class="c">
                                    <c:forEach items="${ListSize}" var="size">
                                        <div class="checkbox-container">
                                            <input type="checkbox" id="${size.getSizeID()}" name="size" value="${size.getSizeID()}">
                                            <label for="checkbox1">${size.getSize()}</label>
                                        </div>
                                    </c:forEach>
                                </div>



                            </div>

                            <div class="panel-form">
                                <p>Image</p>
                                <label for="brand">Image 1</label>
                                <img id="uploadedImage1" src="" alt="Uploaded Image" class="hidden">
                                <input type="file"  id="image1"  accept="image/*" value="" required>
                                <input type="hidden" id="base64Image1" name="base64Image1" value="">

                                <label for="brand">Image 2</label>
                                <img id="uploadedImage2" src="" alt="Uploaded Image" class="hidden">
                                <input type="file" id="image2"  accept="image/*" value="">
                                <input type="hidden" id="base64Image2" name="base64Image2" value="">

                                <label for="brand">Image 3</label>
                                <img id="uploadedImage3" src="" alt="Uploaded Image" class="hidden">
                                <input type="file" id="image3"  accept="image/*" value="">
                                <input type="hidden" id="base64Image3" name="base64Image3" value="">


                            </div>

                        </div>

                    </form>
                </div>
                <div class="${type eq 'Edit' ? 'active' : ''} product-admin">
                    <p>Edit Product</p>
                    <form action="" method="post">
                        <div>
                            <div class="panel-form">
                                <p>General Information</p>
                                <label for="productname">Product Name</label>
                                <input type="text" name="productname" id="productname" placeholder="Type product name here..." value="${Product.getProductName()}">

                                <label for="productdescription">Description</label>
                                <textarea id="productdescription" name="productdescription" rows="5" placeholder="Type product description here..." >${Product.getDescription()}</textarea>
                            </div>

                            <div class="panel-form">
                                <p>Pricing</p>
                                <label for="price">Base Price</label>
                                <input type="text" name="price" id="price" placeholder="Type price name here..." value="${Product.getPrice()}">

                                <p>Pricing</p>
                                <label for="sale">Sale Price</label>
                                <input type="text" name="sale" id="sale" placeholder="Type sale name here..." value="${Product.getSale()}">
                            </div>

                            <div class="panel-form">
                                <p>Inventory</p>
                                <label for="quantity">Quantity</label>
                                <input type="text" name="quantity" id="quantity" placeholder="Type quantity name here..." value="${Product.getStock()}">
                            </div>
                        </div>
                        <div>
                            <button type="submit">Save Product</button>
                            <div class="panel-form">
                                <p>Category</p>
                                <label for="brand">Brand</label>
                                <select name="brand" id="brand">
                                    <c:forEach items="${ListBrand}" var="brand">
                                        <option value="${brand.getBrandID()}" ${brand.getBrandID() eq Product.getBrandID() ? 'selected' : ''}>${brand.getBrandName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            
                            <div class="panel-form">
                                <p>Color</p>
                                <style>
                                    .c {
                                        display: flex;
                                        align-items: center;
                                        flex-direction: row;
                                        flex-wrap: wrap;
                                        gap: 8px;
                                    }
                                    .checkbox-container {
                                        display: flex;
                                        align-items: center;
                                        flex-direction: row;
                                    }

                                    .checkbox-container input[type="checkbox"] {
                                        margin-right: 5px;
                                        margin-bottom: 5px;
                                    }

                                    .checkbox-container label {
                                        margin: 0;
                                    }
                                </style>
                                <div class="c">

                                    <c:forEach items="${ListColor}" var="color">
                                        <div class="checkbox-container">
                                            <input type="checkbox" id="${color.getColorID()}" name="color" value="${color.getColorID()}" ${ListColorEnable.contains(color.getColorID()) ? 'checked' : ''}>
                                            <label for="checkbox1">${color.getColor()}</label>
                                        </div>
                                    </c:forEach>
                                </div>



                            </div>

                            <div class="panel-form">
                                <p>Size</p>
                                <style>
                                    .c {
                                        display: flex;
                                        align-items: center;
                                        flex-direction: row;
                                        flex-wrap: wrap;
                                        gap: 8px;
                                    }
                                    .checkbox-container {
                                        display: flex;
                                        align-items: center;
                                        flex-direction: row;
                                    }

                                    .checkbox-container input[type="checkbox"] {
                                        margin-right: 5px;
                                        margin-bottom: 5px;
                                    }

                                    .checkbox-container label {
                                        margin: 0;
                                    }
                                </style>
                                <div class="c">
                                    <c:forEach items="${ListSize}" var="size">
                                        <div class="checkbox-container">
                                            <input type="checkbox" id="${size.getSizeID()}" name="size" value="${size.getSizeID()}"  ${ListSizeEnable.contains(size.getSizeID()) ? 'checked' : ''}>
                                            <label for="checkbox1">${size.getSize()}</label>
                                            
                                        </div>
                                    </c:forEach>
                                </div>



                            </div>

                            <div class="panel-form">
                                <p>Image</p>
                                <label for="brand">Image 1</label>
                                <img id="uploadedImage1" src="${pageContext.request.contextPath}/${Product.getImage1()}" alt="Uploaded Image">
                                <input type="file"  id="image1"  accept="image/*" value="">
                                <input type="hidden" id="base64Image1" name="base64Image1" value="" required>

                                <label for="brand">Image 2</label>
                                <img id="uploadedImage2" src="${pageContext.request.contextPath}/${Product.getImage2()}" alt="Uploaded Image">
                                <input type="file" id="image2"  accept="image/*" value="">
                                <input type="hidden" id="base64Image2" name="base64Image2" value="">

                                <label for="brand">Image 3</label>
                                <img id="uploadedImage3" src="${pageContext.request.contextPath}/${Product.getImage3()}" alt="Uploaded Image">
                                <input type="file" id="image3"  accept="image/*" value="">
                                <input type="hidden" id="base64Image3" name="base64Image3" value="">

                            </div>

                        </div>
                    </form>
                </div>
            </section>
        </div>
        <script>
            document.addEventListener('DOMContentLoaded', () => {
                const imageInputs = document.querySelectorAll('input[type="file"]');
                const base64Inputs = document.querySelectorAll('input[type="hidden"][id^="base64Image"]');
                const uploadedImages = document.querySelectorAll('img[id^="uploadedImage"]');

                imageInputs.forEach((input, index) => {
                    input.addEventListener('change', (event) => {
                        const file = event.target.files[0];

                        if (file) {
                            const reader = new FileReader();
                            reader.onload = function (event) {
                                const imageUrl = event.target.result;
                                uploadedImages[index].src = imageUrl;
                                base64Inputs[index].value = imageUrl;
                                console.log(base64Inputs[index]);

                            };
                            reader.readAsDataURL(file);
                        } else {
                            // Nếu không có file, reset giá trị của trường ẩn
                            uploadedImages[index].src = '';
                            base64Inputs[index].value = '';
                        }
                    });


                });
            });
        </script>

    </body>