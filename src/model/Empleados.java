/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author sofia
 */
public abstract class Empleados {
    
    protected String nombre;
    protected String correo;
    protected String documento;
    protected String password;
    protected String cargo;

    public Empleados(String nombre, String correo, String documento, String password, String cargo) {
        this.nombre = nombre;
        this.correo = correo;
        this.documento = documento;
        this.password = password;
        this.cargo = cargo;
    }

    public Empleados() {
    }
    
    
    
}
