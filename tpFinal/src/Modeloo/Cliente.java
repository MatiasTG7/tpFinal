
package Modeloo;

public class Cliente {
    private int codCli; //codigo-cliente
    private String dni;
    private String nombreCliente;
    private String telefonoCliente;
    private int edad;
    private boolean afecciones; //enfermedad o cosa que afecte al cliente
    private boolean estadoCliente;

    public Cliente() {
    }

    public Cliente(String dni, String nombreCliente, String telefonoCliente, int edad, boolean afecciones, boolean estadoCliente) {
        this.dni = dni;
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.edad = edad;
        this.afecciones = afecciones;
        this.estadoCliente = estadoCliente;
    }

    public Cliente(int codCli, String dni, String nombreCliente, String telefonoCliente, int edad, boolean afecciones, boolean estadoCliente) {
        this.codCli = codCli;
        this.dni = dni;
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.edad = edad;
        this.afecciones = afecciones;
        this.estadoCliente = estadoCliente;
    }

    public int getCodCli() {
        return codCli;
    }

    public void setCodCli(int codCli) {
        this.codCli = codCli;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isAfecciones() {
        return afecciones;
    }

    public void setAfecciones(boolean afecciones) {
        this.afecciones = afecciones;
    }

    public boolean isEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(boolean estadoCliente) {
        this.estadoCliente = estadoCliente;
    }


    @Override
    public String toString() {
        return dni + " " + nombreCliente;
    }
}
