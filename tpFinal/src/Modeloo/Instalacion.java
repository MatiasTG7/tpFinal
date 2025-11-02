
package Modeloo;


public class Instalacion {
    
    private int codInstal;
    private String nombreInstalacion;
    private String detalleDeUso;
    private double precio30m;
    private boolean estadoInstalacion;

    public Instalacion() {
    }

    public Instalacion(String nombreInstalacion, String detalleDeUso, double precio30m, boolean estadoInstalacion) {
        this.nombreInstalacion = nombreInstalacion;
        this.detalleDeUso = detalleDeUso;
        this.precio30m = precio30m;
        this.estadoInstalacion = estadoInstalacion;
    }

    public Instalacion(int codInstal, String nombreInstalacion, String detalleDeUso, double precio30m, boolean estadoInstalacion) {
        this.codInstal = codInstal;
        this.nombreInstalacion = nombreInstalacion;
        this.detalleDeUso = detalleDeUso;
        this.precio30m = precio30m;
        this.estadoInstalacion = estadoInstalacion;
    }

    public int getCodInstal() {
        return codInstal;
    }

    public void setCodInstal(int codInstal) {
        this.codInstal = codInstal;
    }

    public String getNombreInstalacion() {
        return nombreInstalacion;
    }

    public void setNombreInstalacion(String nombreInstalacion) {
        this.nombreInstalacion = nombreInstalacion;
    }

    public String getDetalleDeUso() {
        return detalleDeUso;
    }

    public void setDetalleDeUso(String detalleDeUso) {
        this.detalleDeUso = detalleDeUso;
    }

    public double getPrecio30m() {
        return precio30m;
    }

    public void setPrecio30m(double precio30m) {
        this.precio30m = precio30m;
    }

    public boolean isEstadoInstalacion() {
        return estadoInstalacion;
    }

    public void setEstadoInstalacion(boolean estadoInstalacion) {
        this.estadoInstalacion = estadoInstalacion;
    }

    @Override
    public String toString() {
        return "Instalacion{" + "codInstal=" + codInstal + ", nombreInstalacion=" + nombreInstalacion + ", detalleDeUso=" + detalleDeUso + ", precio30m=" + precio30m + ", estadoInstalacion=" + estadoInstalacion + '}';
    }
    
    
    
}
