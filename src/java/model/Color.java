/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hoand
 */
public class Color {
    private int ColorID;
    private String Color;
    
    public Color() {
        
    }
    
    public Color(int ColorID, String Color) {
        this.ColorID = ColorID;
        this.Color = Color;
    }

    public int getColorID() {
        return ColorID;
    }

    public void setColorID(int ColorID) {
        this.ColorID = ColorID;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }
    
    
}
