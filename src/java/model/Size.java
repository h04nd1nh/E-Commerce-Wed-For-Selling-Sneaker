/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hoand
 */
public class Size {
    private int SizeID;
    private int Size;
    
    public Size() {
        
    }
    
    public Size(int SizeID, int Size) {
        this.SizeID = SizeID;
        this.Size = Size;
    }

    public int getSizeID() {
        return SizeID;
    }

    public void setSizeID(int SizeID) {
        this.SizeID = SizeID;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int Size) {
        this.Size = Size;
    }
    
    
}
