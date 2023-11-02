/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import access.GenericDAO;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Trabajador;

/**
 *
 * @author sofia
 */
public class TrabajadorController {

    Scanner scStr = new Scanner(System.in);
    Scanner scInt = new Scanner(System.in);

    public void handleTrabajadores() {
        int opcion;
        do {
            System.out.println("Trabajadores".toUpperCase());
            System.out.println("1. Registrarse");
            System.out.println("2. Cambiar contraseña");
            System.out.println("3. Salir");
            System.out.print("Option ? : ");
            opcion = scInt.nextInt();
            scInt.nextLine();
            switch (opcion) {
                case 1:
                    saveTrabajador();
                    break;
                case 2:
                    editTrabajador();
                    break;
               
            }

        } while (opcion != 3);
    }

    private void saveTrabajador() {

        GenericDAO<Trabajador> trabajadorDao = new GenericDAO<>(Trabajador.class);
        System.out.println("ADD TRABAJADOR".toUpperCase());

        System.out.println("Ingrese su documento de identificacion");
        String documento = scStr.nextLine();
        System.out.println("Ingrese su nombre");
        String nombre = scStr.nextLine();
        System.out.print("Ingrese su correo electronico ");
        String correo = scStr.nextLine();
        System.out.print("Cree su contraseña");
        String password = scStr.nextLine();

        String key = UUID.randomUUID().toString().substring(0, 6);
        Map<String, Object> trabajadores = new HashMap<String, Object>() {
            {
                put("documento", documento);
                put("nombre", nombre);
                put("correo", correo);
                put("password", password);
            }
        };

        trabajadorDao.save(key, trabajadores, "trabajadores/");
        System.out.println("trabajador guardado");
    }

    public Boolean getTrabajador() {
        Trabajador trabajador = new Trabajador();
        Boolean res = false;
        GenericDAO<Trabajador> trabajadorDao = new GenericDAO<>(Trabajador.class);
        System.out.println("Ingresar".toUpperCase());

        System.out.println("documento:");
        String documento = scStr.nextLine();
        String contraseñaTrabajador=trabajadorDao.recover("documento", documento, "trabajadores").getPassword();

        System.out.println("contraseña:");
        String contraseña = scStr.nextLine();
        
        if (contraseña.equals(contraseñaTrabajador)) {
            System.out.println("Ingreso correctamente");
            res = true;
        }
        else{
            System.out.println("La contraseña ingresada no es correcta");
        }
        
        return res;
    }

    private void editTrabajador() {
        GenericDAO<Trabajador> usuarioDao = new GenericDAO<>(Trabajador.class);
        System.out.println("Cambiar contraseña".toUpperCase());

        System.out.print("Digite su documento de identificacion:");
        String documento = scStr.nextLine();

        System.out.print("Digite la nueva contraseña:");
        String password = scStr.nextLine();

        Trabajador trabajador = usuarioDao.recover("documento", documento, "trabajadores");
        pausa();
        trabajador.setPassword(password);

        String keyUsuario = usuarioDao.key("documento", documento, "usuarios");

        Map<String, Object> trabajadores = new HashMap<String, Object>() {
            {
                put(keyUsuario + "/documento", trabajador.getDocumento());
                put(keyUsuario + "/correo", trabajador.getCorreo());
                put(keyUsuario + "/nombre", trabajador.getNombre());
                put(keyUsuario + "/password", trabajador.getPassword());
            }
        };

        usuarioDao.update(keyUsuario, trabajadores, "trabajadores");
        System.out.println("Contraseña actualizada correctamente");
    }

    private void pausa() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TrabajadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
