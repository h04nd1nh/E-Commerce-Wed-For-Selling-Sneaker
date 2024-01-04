/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.*;
import model.*;
import context.DBContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 *
 * @author hoand
 */
public class UserDAO {

    Connection conn = null;

    public User login(String UserName, String Password) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select * from [User]\n" + "where username = ?\n" + "and password = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, UserName);
            ps.setString(2, Password);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new User(rs.getInt(1),
                        UserName,
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        Password,
                        rs.getInt(9));
            }

        } catch (Exception err) {
            System.out.println(err);
        }

        return null;
    }

    public Boolean isUserExist(String UserName) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select * from [User]\n" + "where username = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, UserName);
            rs = ps.executeQuery();

            while (rs.next()) {
                return true;
            }

        } catch (Exception err) {
            System.out.println(err);
        }

        return false;
    }

    public void signup(String Username, String Fullname, String Phonenumber, String Password) {

        String query = "insert into [User]\n" + "values(?,?,'assets/img/logo.png',NULL,?,NULL,?,1)";
        PreparedStatement ps = null;

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, Username);
            ps.setString(2, Fullname);
            ps.setString(3, Phonenumber);
            ps.setString(4, Password);
            ps.executeUpdate();
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public int getUserIDByUserName(String UserName) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT UserID FROM [User] WHERE UserName = ?";
        int UserID = 0;

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, UserName);
            rs = ps.executeQuery();

            if (rs.next()) {
                UserID = rs.getInt("UserID");
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

        return UserID;
    }

    public String getFullNameByUserName(String UserName) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT FullName FROM [User] WHERE UserName = ?";
        String FullName = "";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, UserName);
            rs = ps.executeQuery();

            if (rs.next()) {
                FullName = rs.getString("FullName");
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

        return FullName;
    }

    public String getAvatarByUserName(String UserName) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT Avatar FROM [User] WHERE UserName = ?";
        String Avatar = "";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, UserName);
            rs = ps.executeQuery();

            if (rs.next()) {
                Avatar = rs.getString("Avatar");
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

        return Avatar;
    }

    public User getUserByUserName(String UserName) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM [User] WHERE UserName = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, UserName);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9));
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

    public Boolean OrderProceed(int UserID, float Total, String Address, String Status, String Note, String FullName, String Email, String Phone, int VoucherID, float PaymentAmmout, List<CartItem> CartItems) {
        
        
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSS");
        String formattedTime = currentTime.format(formatter);

        // Tạo chuỗi OrderID;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder randomString = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            // Chọn ngẫu nhiên một ký tự từ chuỗi characters
            char randomChar = characters.charAt(random.nextInt(characters.length()));

            // Thêm ký tự vào chuỗi ngẫu nhiên
            randomString.append(randomChar);
        }

        String OrderID = randomString.toString();
        
        
        for (CartItem item : CartItems) {

            try {
                String query = "Select [Stock] from [Product] WHERE [ProductID] = ?";
                PreparedStatement ps = null;
                ResultSet rs = null;
                conn = new DBContext().getConnection();
                ps = conn.prepareStatement(query);
                ps.setInt(1, item.getProductID());
                
                rs = ps.executeQuery();
                
                if(rs.next()) {
                    if (rs.getInt(1) < item.getQuantity()) {
                        return false;
                    }
                }

            } catch (Exception err) {
                System.out.println(err);
            }

        }

        try {
            String query = "insert into [Order]\n" + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = null;
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, OrderID);
            ps.setInt(2, UserID);
            ps.setString(3, formattedTime);
            ps.setFloat(4, Total);
            ps.setString(5, Address);
            ps.setString(6, Status);
            if (Note.isEmpty()) {
                ps.setNull(7, java.sql.Types.NVARCHAR);
            } else {
                ps.setString(7, Note);
            }

            ps.setString(8, FullName);
            ps.setString(9, Email);
            ps.setString(10, Phone);
            if (VoucherID == 0) {
                ps.setNull(11, java.sql.Types.INTEGER);
            } else {
                ps.setInt(11, VoucherID);
            }

            ps.setFloat(12, PaymentAmmout);
            ps.executeUpdate();
        } catch (Exception err) {
            System.out.println(err);
        }

        for (CartItem item : CartItems) {

            try {
                String query = "insert into [OrderDetail]\n" + "values(?,?,?,?,?,?)";
                PreparedStatement ps = null;
                conn = new DBContext().getConnection();
                ps = conn.prepareStatement(query);
                ps.setString(1, OrderID);
                ps.setInt(2, item.getProductID());
                ps.setInt(3, item.getQuantity());
                ps.setFloat(4, item.getSale());
                ps.setInt(5, item.getSizeID());
                ps.setInt(6, item.getColorID());
                ps.executeUpdate();

            } catch (Exception err) {
                System.out.println(err);
            }

        }

        for (CartItem item : CartItems) {

            try {
                PreparedStatement ps1 = null;
                String queryupdate = "UPDATE [Product] SET [Stock] = [Stock] - ?  WHERE [ProductID] = ?";
                ps1 = conn.prepareStatement(queryupdate);
                ps1.setInt(1, item.getQuantity());
                ps1.setInt(2, item.getProductID());
                ps1.executeUpdate();

            } catch (Exception err) {
                System.out.println(err);
            }

        }
        
        return true;

    }

    public List<Order> getAllOrderByUserID(int UserID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM [Order] WHERE UserID = ?";
        List<Order> ListOrder = new ArrayList<>();

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, UserID);
            rs = ps.executeQuery();

            while (rs.next()) {
                ListOrder.add(new Order(
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
                        "TEST",
                        rs.getFloat(12)
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

        return ListOrder;
    }

    public void UpdateUser(User User) {
        String updateQuery = "UPDATE [dbo].[User] "
                + "SET [UserName] = ?, [FullName] = ?, [Avatar] = ?, "
                + "[Email] = ?, [PhoneNumber] = ?, [Address] = ?, "
                + "[Password] = ?, [RoleID] = ? "
                + "WHERE [UserID] = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(updateQuery);
            ps.setString(1, User.getUserName());
            ps.setString(2, User.getFullName());
            ps.setString(3, User.getAvatar());
            ps.setString(4, User.getEmail());
            ps.setString(5, User.getPhoneNumber());
            ps.setString(6, User.getAddress());
            ps.setString(7, User.getPassword());
            ps.setInt(8, User.getRoleID());
            ps.setInt(9, User.getUserID());
            rs = ps.executeQuery();

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
    }

    public Order getOrderbyOrderID(String OrderID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM [Order] WHERE OrderID = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, OrderID);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Order(rs.getString(1),
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
                        "TEST",
                        rs.getFloat(12));
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

    public List<OrderDetail> getOrderDetailbyOrderID(String OrderID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM [OrderDetail] WHERE OrderID = ?";
        List<OrderDetail> ListOrderDetail = new ArrayList<>();

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, OrderID);
            rs = ps.executeQuery();

            while (rs.next()) {
                ListOrderDetail.add(new OrderDetail(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getFloat(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8)
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

        return ListOrderDetail;
    }

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        List<OrderDetail> list = dao.getOrderDetailbyOrderID("WFGXBR");
        System.out.println(list);

    }
}
