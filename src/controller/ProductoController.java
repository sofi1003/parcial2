package controller;

import access.GenericDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Producto;

/**
 *
 * @author sofia
 */
public class ProductoController {

    Scanner scStr = new Scanner(System.in);
    Scanner scInt = new Scanner(System.in);
    Scanner scDou = new Scanner(System.in);

    public void handleProductos() {
        int opcion;
        do {
            System.out.println("Productos".toUpperCase());
            System.out.println("1. Añadir producto");
            System.out.println("2. Verificar producto");
            System.out.println("3. Listar productos");
            System.out.println("4. exit");
            System.out.print("Option ? : ");
            opcion = scInt.nextInt();
            scInt.nextLine();
            switch (opcion) {
                case 1:
                    saveProducto();
                    break;
                case 2:
                    getProducto();
                    break;
                case 3:
                    getProductos();
                    break;
            }

        } while (opcion != 4);
    }

    private void saveProducto() {
        TrabajadorController ix = new TrabajadorController();
        if(ix.getTrabajador()){

        GenericDAO<Producto> productoDao = new GenericDAO<>(Producto.class);
        System.out.println("ADD producto".toUpperCase());

        System.out.println("Ingrese el nombre del producto");
        String nombre = scStr.nextLine();
        System.out.print("Ingrese la descripcion del producto: ");
        String descripcion = scStr.nextLine();
        System.out.print("Ingrese el precio del producto: ");
        Double precio = scDou.nextDouble();
        System.out.print("Ingrese la cantidad: ");
        String cantidad = scStr.nextLine();

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
        else{
            System.out.println("SOLO LOS TRABAJADORES PUEDEN INGRESAR PRODUCTOS");
        }
    }

    private void getProducto() {
        GenericDAO<Producto> productoDao = new GenericDAO<>(Producto.class);
        Producto producto;
        System.out.println("Verificar producto".toUpperCase());

        System.out.println("ingresa el nombre del producto que deseas verificar:");
        String nombre = scStr.nextLine();
        producto = productoDao.recover("nombre", nombre, "productos");

        if (producto.getCantidad() != null) {
            System.out.println("El producto tiene disponible " + producto.getCantidad() + " unidades");

        } else {
            System.out.println("Producto no existe");
        }

    }

    private void getProductos() {
        GenericDAO<Producto> productoDao = new GenericDAO<>(Producto.class);
        System.out.println("LISTAR PRODUCTOS".toUpperCase());
        List<Producto> res = productoDao.list("productos");
        pausa();
        System.out.println("Lista de productos:: " + res);
    }

    private void pausa() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
