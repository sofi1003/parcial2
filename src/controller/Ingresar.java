/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import access.GenericDAO;
import model.Supervisor;
import model.Trabajador;

/**
 *
 * @author sofia
 */
public class Ingresar {
    
    private String documento;
    private String password;
    private String cargo;

    public Ingresar(String documento, String password, String cargo) {
        this.documento = documento;
        this.password = password;
        this.cargo = cargo;
    }
    
    
    
    
    public Boolean getTrabajador() {
        Trabajador trabajador = new Trabajador();
        Boolean res = false;
        GenericDAO<Trabajador> trabajadorDao = new GenericDAO<>(Trabajador.class);
        System.out.println("documento:");
        String documento = this.documento;
        String contraseñaTrabajador=trabajadorDao.recover("documento", documento, "trabajadores").getPassword();

        System.out.println("contraseña:");
        String contraseña = this.password;
        
        if (contraseña.equals(contraseñaTrabajador)) {
           
            res = true;
        }
        else{
            res = false;
            
        }
        
        return res;
    }
    
    public Boolean getSupervisor() {
        Supervisor supervisor = new Supervisor();
        Boolean res = false;
        GenericDAO<Supervisor> supervisorDao = new GenericDAO<>(Supervisor.class);


        System.out.println("documento:");
        String documento = this.documento;
        String contraseñaTrabajador=supervisorDao.recover("documento", documento, "supervisores").getPassword();

        System.out.println("contraseña:");
        String contraseña = this.password;
        
        if (contraseña.equals(contraseñaTrabajador)) {
            System.out.println("Ingreso correctamente");
            res = true;
        }
        else{
            System.out.println("La contraseña ingresada no es correcta");
        }
        
        return res;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    
   
    
}
