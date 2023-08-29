/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

module unaplanilla {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires jakarta.xml.bind;
    requires jakarta.ws.rs;
    requires jakarta.json;
    requires java.logging;
    requires java.sql;
    requires java.base;
    
    opens cr.ac.una.unaplanilla to javafx.fxml,javafx.graphics;
    opens cr.ac.una.unaplanilla.controller to java.base,javafx.fxml,javafx.base,com.jfoenix;
    opens cr.ac.una.unaplanilla.util to java.base,javafx.fxml,javafx.base,com.jfoenix;
    
    exports cr.ac.una.unaplanilla.model;
}
