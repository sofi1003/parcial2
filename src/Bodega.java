
/*import controller.ProductoController;
import controller.TrabajadorController;
import java.util.Scanner;


public class Bodega {

    public static void main(String[] args) {

        mostrarMenus();

    }

    public static void mostrarMenus() {
        TrabajadorController iT = new TrabajadorController();
        ProductoController iP = new ProductoController();
        Scanner scInt = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("BODEGA".toUpperCase());
            System.out.println("1. Mostrar menu para registrar o cambiar contraseña de un trabajador");
            System.out.println("2. Mostrar menu para productos");
            System.out.println("3. salir");
            System.out.print("Option ? : ");
            opcion = scInt.nextInt();
            scInt.nextLine();
            switch (opcion) {
                case 1:
                    iT.handleTrabajadores();
                    break;
                case 2:
                    iP.handleProductos();
                    break;
            }

        } while (opcion != 3);
    }
}*/
