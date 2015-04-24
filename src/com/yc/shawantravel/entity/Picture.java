package com.yc.shawantravel.entity;

public class Picture {

	private String text; 
    private int icon; 
 
    public Picture() 
    { 
        super(); 
    } 
 
    public Picture(String text, int icon) 
    { 
        super(); 
        this.text = text; 
        this.icon = icon; 
    } 
 
    public String getText() 
    { 
        return text; 
    } 
 
    public void setText(String text) 
    { 
        this.text = text; 
    } 
 
    public int getIcon() 
    { 
        return icon; 
    } 
 
    public void setIcon(int icon) 
    { 
        this.icon = icon; 
    } 
}
