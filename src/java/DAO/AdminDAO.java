/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 *
 * @author hoand
 */
public class AdminDAO {

    Connection conn = null;

    public float getSumOrderSale() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT SUM(PaymentAmount) AS TotalPaymentAmount FROM [Order]";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getFloat("TotalPaymentAmount");
            }
        } catch (Exception err) {
            System.out.println(err);
        }

        return 0;
    }

    public int getSumProductSale() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT SUM(Quantity) AS TotalQuantity FROM [OrderDetail]";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("TotalQuantity");
            }
        } catch (Exception err) {
            System.out.println(err);
        }

        return 0;
    }

    public int getSumCustomer() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT SUM(RoleID) AS TotalUser FROM [User] WHERE RoleID=1";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("TotalUser");
            }
        } catch (Exception err) {
            System.out.println(err);
        }

        return 0;
    }

    public List<Product> getTopProductSale() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        String query = "SELECT ProductID, SUM(Quantity) AS TotalQuantity  FROM [OrderDetail] GROUP BY ProductID ORDER BY TotalQuantity DESC";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            ProductDAO productDAO = new ProductDAO();
            int temp = 0;
            while (rs.next()) {
                temp++;
                list.add(productDAO.getProductByID(rs.getInt(1)));

                if (temp >= 5) {
                    break;
                }
            }
        } catch (Exception err) {
            System.out.println(err);
        }

        return list;
    }

    public List<Order> getListOrder() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Order> list = new ArrayList<>();
        String query = "select * from [Order]";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                list.add(new Order(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        "1",
                        rs.getFloat(12)
                ));
            }
        } catch (Exception err) {
            System.out.println(err);
        }

        return list;
    }

    public List<Voucher> getListVoucher() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Voucher> list = new ArrayList<>();
        String query = "select * from [Voucher]";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                list.add(new Voucher(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5)
                ));
            }
        } catch (Exception err) {
            System.out.println(err);
        }

        return list;
    }

    public Voucher getVoucherById(int VoucherID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select * from [Voucher] WHERE VoucherID = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, VoucherID);
            rs = ps.executeQuery();
            while (rs.next()) {
                // Lấy timestamp từ cột datetime2
                Timestamp startDateTimestamp = rs.getTimestamp("StartDate");
                Timestamp expiryTimestamp = rs.getTimestamp("Expiry");

                // Chuyển đổi timestamp thành chuỗi theo định dạng mong muốn
                String startDateString = convertTimestampToString(startDateTimestamp);
                String expiryDateString = convertTimestampToString(expiryTimestamp);

                // Tạo đối tượng Voucher với các giá trị đã chuyển đổi
                return new Voucher(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), startDateString, expiryDateString);
            }
        } catch (Exception err) {
            System.out.println(err);
        }

        return null;
    }

    public List<User> getListUser() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> list = new ArrayList<>();
        String query = "select * from [User]";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                if (rs.getInt(9) == 1) {
                    list.add(new User(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getInt(9)
                    ));
                }

            }
        } catch (Exception err) {
            System.out.println(err);
        }

        return list;
    }

    public int AddProduct(Product product) {
        int generatedProductId = -1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "INSERT INTO Product (ProductName, Price, Sale, Image1, Image2, Image3, BrandID, Status, Description, Stock) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getProductName());
            ps.setFloat(2, product.getPrice());
            ps.setFloat(3, product.getSale());
            ps.setString(4, product.getImage1());
            ps.setString(5, product.getImage2());
            ps.setString(6, product.getImage3());
            ps.setInt(7, product.getBrandID());
            ps.setBoolean(8, product.getStatus());
            ps.setString(9, product.getDescription());
            ps.setInt(10, product.getStock());
            ps.executeUpdate();
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Lấy ID được sinh ra
                    }
                }
            

        } catch (Exception err) {
            System.out.println(err);
        }
        
        return 0;
    }

    public void UpdateProductByID(int productID, Product updatedProduct) {
        PreparedStatement ps = null;
        String query = "UPDATE Product SET "
                + "ProductName = ?, "
                + "Price = ?, "
                + "Sale = ?, "
                + "BrandID = ?, "
                + "Status = ?, "
                + "Description = ?, "
                + "Stock = ? ";

        if (updatedProduct.getImage1() != null) {
            query = query + "Image1 =" + updatedProduct.getImage1() + ", ";
        }

        if (updatedProduct.getImage2() != null) {
            query = query + "Image2 =" + updatedProduct.getImage2() + ", ";
        }

        if (updatedProduct.getImage3() != null) {
            query = query + "Image3 =" + updatedProduct.getImage3() + ", ";
        }
        query = query + "WHERE ProductID = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);

            // Đặt các giá trị mới cho các tham số trong câu lệnh UPDATE
            ps.setString(1, updatedProduct.getProductName());
            ps.setFloat(2, updatedProduct.getPrice());
            ps.setFloat(3, updatedProduct.getSale());
            ps.setInt(4, updatedProduct.getBrandID());
            ps.setBoolean(5, updatedProduct.getStatus());
            ps.setString(6, updatedProduct.getDescription());
            ps.setInt(7, updatedProduct.getStock());
            ps.setInt(8, productID);  // Sử dụng productID để xác định sản phẩm cần cập nhật

            // Thực hiện câu lệnh UPDATE
            ps.executeUpdate();
        } catch (Exception err) {
            System.out.println(err);
        } finally {
            // Đóng tài nguyên (Connection, PreparedStatement, ResultSet) ở đây nếu cần
        }
    }

    public void DeleteProductByID(int productID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "DELETE FROM Product WHERE ProductID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            ps.executeUpdate();

        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void DeleteVoucherByID(int VoucherID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "DELETE FROM Voucher WHERE VoucherID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, VoucherID);
            ps.executeUpdate();

        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void AddVoucher(Voucher voucher) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "INSERT INTO Voucher ([VourcherCode], Discount, StartDate, Expiry) VALUES (?, ?, ?, ?)";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, voucher.getVoucherCode());
            ps.setInt(2, voucher.getDiscount());
            ps.setTimestamp(3, convertStringToTimestamp(voucher.getStartDate()));
            ps.setTimestamp(4, convertStringToTimestamp(voucher.getExpiry()));

            ps.executeUpdate();

        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void updateVoucher(Voucher voucher) {
        PreparedStatement ps = null;
        String query = "UPDATE Voucher SET VoucherCode = ?, Discount = ?, StartDate = ?, Expiry = ? WHERE VoucherID = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, voucher.getVoucherCode());
            ps.setInt(2, voucher.getDiscount());
            ps.setTimestamp(3, convertStringToTimestamp(voucher.getStartDate()));
            ps.setTimestamp(4, convertStringToTimestamp(voucher.getExpiry()));
            ps.setInt(5, voucher.getVoucherID());

            ps.executeUpdate();
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    private Timestamp convertStringToTimestamp(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(dateString);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String convertTimestampToString(Timestamp timestamp) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(timestamp);
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý ngoại lệ nếu có lỗi
            return null;
        }
    }

    public void AddBrand(Brand brand) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = new DBContext().getConnection();
            String query = "INSERT INTO Brand (BrandName) VALUES (?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, brand.getBrandName());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddColor(Color color) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = new DBContext().getConnection();
            String query = "INSERT INTO Colors (Color) VALUES (?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, color.getColor());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddSize(Size size) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = new DBContext().getConnection();
            String query = "INSERT INTO Size (Size) VALUES (?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, size.getSize());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Boolean checkValidBrand(int BrandID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Product WHERE BrandID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, BrandID);
            rs = ps.executeQuery();

            if (rs.next()) {
                return false;
            }

        } catch (Exception err) {
            System.out.println(err);
        }

        return true;
    }

    public Boolean checkValidColor(int ColorID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM ProductColor WHERE ColorID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, ColorID);
            rs = ps.executeQuery();

            if (rs.next()) {
                return false;
            }

        } catch (Exception err) {
            System.out.println(err);
        }

        return true;
    }

    public Boolean checkValidSize(int SizeID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM ProductSizes WHERE SizeID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, SizeID);
            rs = ps.executeQuery();

            if (rs.next()) {
                return false;
            }

        } catch (Exception err) {
            System.out.println(err);
        }

        return true;
    }

    public void DeleteBrandByID(int BrandID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "DELETE FROM Brand WHERE BrandID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, BrandID);
            ps.executeUpdate();

        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void DeleteColorByID(int ColorID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "DELETE FROM Colors WHERE ColorID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, ColorID);
            ps.executeUpdate();

        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void DeleteSizeByID(int SizeID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "DELETE FROM Size WHERE SizeID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, SizeID);
            ps.executeUpdate();

        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void UpdateBrand(Brand brand) {
        PreparedStatement ps = null;
        String query = "UPDATE Brand SET BrandName = ? WHERE BrandID = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, brand.getBrandName());
            ps.setInt(2, brand.getBrandID());

            ps.executeUpdate();
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void UpdateColor(Color color) {
        PreparedStatement ps = null;
        String query = "UPDATE Colors SET Color = ? WHERE ColorID = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, color.getColor());
            ps.setInt(2, color.getColorID());

            ps.executeUpdate();
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void UpdateSize(Size size) {
        PreparedStatement ps = null;
        String query = "UPDATE Size SET Size = ? WHERE SizeID = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, size.getSize());
            ps.setInt(2, size.getSizeID());

            ps.executeUpdate();
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void UpdateProductColor(String[] colorID, int ProductID) {
        PreparedStatement ps = null;
        

        try {
            String query = "DELETE FROM [ProductColor] WHERE [ProductID] = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, ProductID);
            ps.executeUpdate();
        } catch (Exception err) {
            System.out.println(err);
        }
        
        try {
            String query = "INSERT INTO [ProductColor] ([ProductID], [ColorID]) VALUES (?, ?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            try {
                for (String color : colorID) {
                    try {
                        int colorIDInt = Integer.parseInt(color);
                        ps.setInt(1, ProductID);
                        ps.setInt(2, colorIDInt);
                        ps.executeUpdate();
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid colorID: " + colorID);
                    }
                }
            } catch (Exception err) {
                System.out.println(err);
            }
            ps.setInt(1, ProductID);
            ps.executeUpdate();
        } catch (Exception err) {
            System.out.println(err);
        }
    }
    
    public void UpdateProductSize(String[] sizeID, int ProductID) {
        PreparedStatement ps = null;
        

        try {
            String query = "DELETE FROM [ProductSizes] WHERE [ProductID] = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, ProductID);
            ps.executeUpdate();
        } catch (Exception err) {
            System.out.println(err);
        }
        
        try {
            String query = "INSERT INTO [ProductSizes] ([ProductID], [SizeID]) VALUES (?, ?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            try {
                for (String size : sizeID) {
                    try {
                        ps.setInt(1, ProductID);
                        ps.setInt(2, Integer.parseInt(size));
                        ps.executeUpdate();
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid colorID: " + sizeID);
                    }
                }
            } catch (Exception err) {
                System.out.println(err);
            }
            ps.setInt(1, ProductID);
            ps.executeUpdate();
        } catch (Exception err) {
            System.out.println(err);
        }
    }

}
