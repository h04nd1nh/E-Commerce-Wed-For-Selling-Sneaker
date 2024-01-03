/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.*;
import model.*;
import context.DBContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author hoand
 */
public class ProductDAO {

    Connection conn = null;

    public List<Product> getListProduct() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        String query = "select * from product";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int brandId = rs.getInt(8);
                String brandName = findBrandNameById(brandId);

                list.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getFloat(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        brandId,
                        brandName,
                        rs.getBoolean(9),
                        rs.getString(10),
                        rs.getInt(11)
                ));
            }
        } catch (Exception err) {
            System.out.println(err);
        }

        return list;
    }

    public List<Brand> getListBrand() {
        PreparedStatement ps = null;
        ResultSet br = null;
        List<Brand> brandList = new ArrayList<>();
        String query = "select * from brand";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            br = ps.executeQuery();
            while (br.next()) {
                brandList.add(new Brand(
                        br.getInt(1),
                        br.getString(2)
                ));
            }
        } catch (Exception err) {
            System.out.println(err);
        }

        return brandList;
    }
    
    public List<Color> getListColor() {
        PreparedStatement ps = null;
        ResultSet br = null;
        List<Color> ColorList = new ArrayList<>();
        String query = "select * from Colors";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            br = ps.executeQuery();
            while (br.next()) {
                ColorList.add(new Color(
                        br.getInt(1),
                        br.getString(2)
                ));
            }
        } catch (Exception err) {
            System.out.println(err);
        }

        return ColorList;
    }

    public List<Size> getListSize() {
        PreparedStatement ps = null;
        ResultSet br = null;
        List<Size> SizeList = new ArrayList<>();
        String query = "select * from Size";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            br = ps.executeQuery();
            while (br.next()) {
                SizeList.add(new Size(
                        br.getInt(1),
                        br.getInt(2)
                ));
            }
        } catch (Exception err) {
            System.out.println(err);
        }

        return SizeList;
    }
    
    public String findBrandNameById(int BrandID) {
        PreparedStatement ps = null;
        ResultSet brand = null;
        String query = "SELECT BrandName FROM Brand WHERE BrandID = ?";
        String brandName = null;

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, BrandID);
            brand = ps.executeQuery();

            if (brand.next()) {
                brandName = brand.getString("BrandName");
            }
        } catch (Exception err) {
            System.out.println(err);
        } finally {
            try {
                // Close the resources in reverse order of their creation to avoid potential issues
                if (brand != null) {
                    brand.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exceptions during closing resources
            }
        }

        return brandName;
    }
    
    public Brand getBrandById(int BrandID) {
        PreparedStatement ps = null;
        ResultSet brand = null;
        String query = "SELECT * FROM Brand WHERE BrandID = ?";
        

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, BrandID);
            brand = ps.executeQuery();

            if (brand.next()) {
                return new Brand(brand.getInt(1), brand.getString(2));
            }
        } catch (Exception err) {
            System.out.println(err);
        } finally {
            try {
                // Close the resources in reverse order of their creation to avoid potential issues
                if (brand != null) {
                    brand.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exceptions during closing resources
            }
        }

        return null;
    }

    public List<Product> getProductByBrandID(int BrandID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        String query = "select * from product where BrandID = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, BrandID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int brandId = rs.getInt(8);
                String brandName = findBrandNameById(brandId);

                list.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getFloat(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        brandId,
                        brandName,
                        rs.getBoolean(9),
                        rs.getString(10),
                        rs.getInt(11)
                ));
            }
        } catch (Exception err) {
            System.out.println(err);
        }

        return list;
    }

    public Product getProductByID(int ProductID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select * from product where ProductID = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, ProductID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int brandId = rs.getInt(8);
                String brandName = findBrandNameById(brandId);

                return new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getFloat(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        brandId,
                        brandName,
                        rs.getBoolean(9),
                        rs.getString(10),
                        rs.getInt(11)
                );
            }
        } catch (Exception err) {
            System.out.println(err);
        }

        return null;
    }

    public List<ProductSize> getAllProductSize(int ProductID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ProductSize> list = new ArrayList<>();
        String query = "select * from ProductSizes where ProductID = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, ProductID);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductSize(
                        rs.getInt(1),
                        rs.getInt(2)
                ));
            }
        } catch (Exception err) {
            System.out.println(err);
        }

        return list;

    }

    public int getSizeBySizeID(int SizeID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT Size FROM Size WHERE SizeID = ?";
        int Size = 0;

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, SizeID);
            rs = ps.executeQuery();

            if (rs.next()) {
                Size = rs.getInt("Size");
            }
        } catch (Exception err) {
            System.out.println(err);
        } finally {
            try {
                // Close the resources in reverse order of their creation to avoid potential issues
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exceptions during closing resources
            }
        }

        return Size;
    }

    public List<Size> getSizeByProductID(int ProductID) {
        List<ProductSize> AllSize = getAllProductSize(ProductID);
        List<Size> listSize = new ArrayList<>();
        for (ProductSize size : AllSize) {

            listSize.add(new Size(size.getSizeID(), getSizeBySizeID(size.getSizeID())));
        }

        return listSize;
    }

    public List<ProductColor> getAllProductColor(int ProductID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ProductColor> list = new ArrayList<>();
        String query = "select * from ProductColor where ProductID = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, ProductID);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductColor(
                        rs.getInt(1),
                        rs.getInt(2)
                ));
            }
        } catch (Exception err) {
            System.out.println(err);
        }

        return list;

    }

    public String getColorByColorID(int ColorID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT Color FROM Colors WHERE ColorID = ?";
        String Color = null;

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, ColorID);
            rs = ps.executeQuery();

            if (rs.next()) {
                Color = rs.getString("Color");
            }
        } catch (Exception err) {
            System.out.println(err);
        } finally {
            try {
                // Close the resources in reverse order of their creation to avoid potential issues
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exceptions during closing resources
            }
        }

        return Color;
    }
    
    public Color getColorByID(int ColorID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Colors WHERE ColorID = ?";


        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, ColorID);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Color(rs.getInt(1),rs.getString(2));
            }
        } catch (Exception err) {
            System.out.println(err);
        } finally {
            try {
                // Close the resources in reverse order of their creation to avoid potential issues
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exceptions during closing resources
            }
        }

        return null;
    }

    public List<Color> getColorByProductID(int ProductID) {
        List<ProductColor> AllColor = getAllProductColor(ProductID);
        List<Color> listColor = new ArrayList<>();
        for (ProductColor color : AllColor) {

            listColor.add(new Color(color.getColoriD(), getColorByColorID(color.getColoriD())));
        }

        return listColor;
    }

    public List<ProductReview> getReviewByProductID(int ProductID) {

        List<ProductReview> listReview = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM ProductReview WHERE ProductID = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, ProductID);
            rs = ps.executeQuery();
            SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (rs.next()) {
                java.sql.Timestamp timestamp = rs.getTimestamp(6);
                Date DateTime = new Date(timestamp.getTime());
                
                listReview.add(new ProductReview(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        formatTime.format(DateTime),
                        rs.getString(7)
                ));

            }
        } catch (Exception err) {
            System.out.println(err);
        } finally {
            try {
                // Close the resources in reverse order of their creation to avoid potential issues
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exceptions during closing resources
            }
        }
        
        return listReview;

    }
    
    public void AddProductReview(int ProductID, String Content, String UserName) {
        String query = "insert into [ProductReview]\n" + "values(?,?,?,?,?,?)";
        PreparedStatement ps = null;
        
        UserDAO UserDAO = new UserDAO();
        int UserID = UserDAO.getUserIDByUserName(UserName);
        String FullName = UserDAO.getFullNameByUserName(UserName);
        String Avatar = UserDAO.getAvatarByUserName(UserName);
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSS");
        String formattedTime = currentTime.format(formatter);
        
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, UserID);
            ps.setInt(2, ProductID);
            ps.setString(3, FullName);
            ps.setString(4, Avatar);
            ps.setString(5, formattedTime);
            ps.setString(6, Content);
            ps.executeUpdate();
        } catch (Exception err) {
            System.out.println(err);
        }
    }
    
    public int getProductReviewCount(int ProductID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM ProductReview WHERE ProductID = ?";
        int ReviewCount = 0;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, ProductID);
            rs = ps.executeQuery();
            SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (rs.next()) {
                ReviewCount+=1;
            }
        } catch (Exception err) {
            System.out.println(err);
        } finally {
            try {
                // Close the resources in reverse order of their creation to avoid potential issues
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exceptions during closing resources
            }
        }
        
        return ReviewCount;
    }
    
    public ProductRate ProductRateByProductID(int ProductID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM ProductRate WHERE ProductID = ?";
        ProductRate ProductRate = null;
                
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, ProductID);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                
                ProductRate = new ProductRate(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));
            }
        } catch (Exception err) {
            System.out.println(err);
        } finally {
            try {
                // Close the resources in reverse order of their creation to avoid potential issues
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exceptions during closing resources
            }
        }
        
        if (ProductRate == null) {
            return new ProductRate(0,0,0,0,0,0,0);
        }
        
        return ProductRate;
        
    }
    
    public int getProductRateCountByProductID(int ProductID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM ProductRate WHERE ProductID = ?";
        int ProductRateCount  = 0;
                
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, ProductID);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                ProductRateCount = rs.getInt(3) + rs.getInt(4) + rs.getInt(5) + rs.getInt(6) + rs.getInt(7);
            }
        } catch (Exception err) {
            System.out.println(err);
        } finally {
            try {
                // Close the resources in reverse order of their creation to avoid potential issues
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exceptions during closing resources
            }
        }
        
        return ProductRateCount;
    }
    
    public int getProductRateAverageByProductID(int ProductID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM ProductRate WHERE ProductID = ?";
        int TotalRate = 0;
        int ProductRateCount  = 0;
        
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, ProductID);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                ProductRateCount = rs.getInt(4) + rs.getInt(5) + rs.getInt(6) + rs.getInt(7);
                TotalRate = + rs.getInt(3)*1+rs.getInt(4)*2+rs.getInt(5)*3+rs.getInt(6)*4+rs.getInt(7)*5;
            }
        } catch (Exception err) {
            System.out.println(err);
        } finally {
            try {
                // Close the resources in reverse order of their creation to avoid potential issues
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exceptions during closing resources
            }
        }
        
        if (ProductRateCount == 0) {
            return 5;
        } 
        
        return TotalRate/ProductRateCount;
    }
    
    public int getDiscountByVoucherCode(String VoucherCode) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT Discount FROM Voucher WHERE VourcherCode = ?";
        int Discount = 0;
        
        
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, VoucherCode);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Discount = rs.getInt(1);
            }
        } catch (Exception err) {
            System.out.println(err);
        } finally {
            try {
                // Close the resources in reverse order of their creation to avoid potential issues
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exceptions during closing resources
            }
        }
        
        return Discount;
    }
    
    public int getVoucherIDByVoucherCode(String VoucherCode) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT VoucherID FROM Voucher WHERE VourcherCode = ?";
        int VoucherID = 0;
        
        
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, VoucherCode);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                VoucherID  = rs.getInt(1);
            }
        } catch (Exception err) {
            System.out.println(err);
        } finally {
            try {
                // Close the resources in reverse order of their creation to avoid potential issues
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exceptions during closing resources
            }
        }
        
        return VoucherID;
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        System.out.println(dao.getVoucherIDByVoucherCode("PSNEAKER20"));
    }

}
