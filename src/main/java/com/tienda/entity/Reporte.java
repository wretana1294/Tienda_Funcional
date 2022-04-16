/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.entity;

import java.io.ByteArrayInputStream;

/**
 *
 * @author Carlitos
 */
public class Reporte {
    private String flieName;
    private ByteArrayInputStream stream;
    private int length;

    public String getFlieName() {
        return flieName;
    }

    public void setFlieName(String flieName) {
        this.flieName = flieName;
    }

    public ByteArrayInputStream getStream() {
        return stream;
    }

    public void setStream(ByteArrayInputStream stream) {
        this.stream = stream;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
    
}
