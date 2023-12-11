/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hoand
 */
public class CookieDAO {
    public static String formatCookie(String cookieData) {
        // Kiểm tra xem chuỗi cookie có rỗng hay không
        if (cookieData == null || cookieData.isEmpty()) {
            return "";
        }

        Map<String, Integer> itemQuantityMap = new HashMap<>();
        String[] items = cookieData.split("::");

        // Duyệt qua từng mục và cập nhật Map
        for (String item : items) {
            String[] values = item.split(":");
            String key = values[0] + ":" + values[1] + ":" + values[2];
            int quantity = Integer.parseInt(values[3]);

            // Kiểm tra nếu quantity khác 0 mới thêm vào Map
            if (quantity != 0) {
                // Nếu mục đã tồn tại trong Map, cộng thêm quantity vào giá trị hiện tại
                itemQuantityMap.put(key, itemQuantityMap.getOrDefault(key, 0) + quantity);
            }
        }

        // Tạo chuỗi định dạng từ Map
        StringBuilder formattedCookie = new StringBuilder();
        for (Map.Entry<String, Integer> entry : itemQuantityMap.entrySet()) {
            String[] values = entry.getKey().split(":");
            int productId = Integer.parseInt(values[0]);
            int colorId = Integer.parseInt(values[1]);
            int sizeId = Integer.parseInt(values[2]);
            int quantity = entry.getValue();

            formattedCookie.append(productId)
                    .append(":")
                    .append(colorId)
                    .append(":")
                    .append(sizeId)
                    .append(":")
                    .append(quantity)
                    .append("::");
        }

        // Loại bỏ dấu "::" cuối cùng nếu có
        if (formattedCookie.length() >= 2) {
            formattedCookie.setLength(formattedCookie.length() - 2);
        }

        return formattedCookie.toString();
    }
    
    public static String deleteProduct(String cookieData, int productID, int colorID, int sizeID) {
        // Kiểm tra xem chuỗi cookie có rỗng hay không
        if (cookieData == null || cookieData.isEmpty()) {
            return "";
        }

        Map<String, Integer> itemQuantityMap = new HashMap<>();
        String[] items = cookieData.split("::");

        // Duyệt qua từng mục và cập nhật Map
        for (String item : items) {
            String[] values = item.split(":");
            int productId = Integer.parseInt(values[0]);
            int colorId = Integer.parseInt(values[1]);
            int sizeId = Integer.parseInt(values[2]);
            int quantity = Integer.parseInt(values[3]);

            // Kiểm tra xem mục có phải là mục cần xóa hay không
            if (productId == productID && colorId == colorID && sizeId == sizeID) {
                // Bỏ qua mục cần xóa
                continue;
            }

            // Thêm vào Map mọi mục khác
            itemQuantityMap.put(productId + ":" + colorId + ":" + sizeId, quantity);
        }

        // Tạo chuỗi định dạng từ Map
        StringBuilder formattedCookie = new StringBuilder();
        for (Map.Entry<String, Integer> entry : itemQuantityMap.entrySet()) {
            String[] values = entry.getKey().split(":");
            int productId = Integer.parseInt(values[0]);
            int colorId = Integer.parseInt(values[1]);
            int sizeId = Integer.parseInt(values[2]);
            int quantity = entry.getValue();

            formattedCookie.append(productId)
                    .append(":")
                    .append(colorId)
                    .append(":")
                    .append(sizeId)
                    .append(":")
                    .append(quantity)
                    .append("::");
        }

        // Loại bỏ dấu "::" cuối cùng nếu có
        if (formattedCookie.length() >= 2) {
            formattedCookie.setLength(formattedCookie.length() - 2);
        }

        return formattedCookie.toString();
    }


    public static void main(String[] args) {
        // Example usage
        String originalCookie = "6:1:2:3";
        String format = formatCookie(originalCookie);
        String rs = deleteProduct(format,6,1,2);
        System.out.println(rs.isEmpty());
    }
}
