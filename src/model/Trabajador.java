package model;

public class Trabajador extends Empleados{
    
    
    public Trabajador(String userName, String email, String documento, String password) {
        super(userName,email, documento, password, "supervisor");
        
    }

    public Trabajador() {
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    

    @Override
    public String toString() {
        return "Trabajador{" + "documento=" + documento + ", password=" + password + '}';
    }

    
    
   
}