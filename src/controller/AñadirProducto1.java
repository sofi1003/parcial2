/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import access.GenericDAO;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import model.Producto;

/**
 *
 * @author sofia
 */
public class AñadirProducto1 {
    
    private String nombre;
    private String descripcion;
    private Double precio;
    private String cantidad;

    public AñadirProducto1(String nombre, String descripcion, Double precio, String cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    
    
    
    public void saveProducto() {
        GenericDAO<Producto> productoDao = new GenericDAO<>(Producto.class);
        System.out.println("Ingrese el nombre del producto");
        String nombre = this.nombre;
        System.out.print("Ingrese la descripcion del producto: ");
        String descripcion = this.descripcion;
        System.out.print("Ingrese el precio del producto: ");
        Double precio = this.precio;
        System.out.print("Ingrese la cantidad: ");
        String cantidad = this.cantidad;

        String key = UUID.randomUUID().toString().substring(0, 6);
        Map<String, Object> productos = new HashMap<String, Object>() {
            {
                put("nombre", nombre);
                put("descripcion", descripcion);
                put("precio", precio);
                put("cantidad", cantidad);
            }
        };

        productoDao.save(key, productos, "productos/");
        System.out.println("producto guardado");}
       
    }
    

