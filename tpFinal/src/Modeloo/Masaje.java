
package Modeloo;

import java.time.LocalDate;


public class Masaje {
    private int codTratamiento;
    private String nombreTratamiento;
    private String detalleTratamiento;
    private int duracionTratamiento;
    private double costoTratamiento;
    private boolean activo;
    private TipoMasaje tipo;

    public Masaje() {
    }

    public Masaje(String nombreTratamiento, String detalleTratamiento, int duracionTratamiento, double costoTratamiento, boolean activo, TipoMasaje tipo) {
        this.nombreTratamiento = nombreTratamiento;
        this.detalleTratamiento = detalleTratamiento;
        this.duracionTratamiento = duracionTratamiento;
        this.costoTratamiento = costoTratamiento;
        this.activo = activo;
        this.tipo = tipo;
    }

    public Masaje(int codTratamiento, String nombreTratamiento, String detalleTratamiento, int duracionTratamiento, double costoTratamiento, boolean activo, TipoMasaje tipo) {
        this.codTratamiento = codTratamiento;
        this.nombreTratamiento = nombreTratamiento;
        this.detalleTratamiento = detalleTratamiento;
        this.duracionTratamiento = duracionTratamiento;
        this.costoTratamiento = costoTratamiento;
        this.activo = activo;
        this.tipo = tipo;
    }

    public int getCodTratamiento() {
        return codTratamiento;
    }

    public void setCodTratamiento(int codTratamiento) {
        this.codTratamiento = codTratamiento;
    }

    public String getNombreTratamiento() {
        return nombreTratamiento;
    }

    public void setNombreTratamiento(String nombreTratamiento) {
        this.nombreTratamiento = nombreTratamiento;
    }

    public String getDetalleTratamiento() {
        return detalleTratamiento;
    }

    public void setDetalleTratamiento(String detalleTratamiento) {
        this.detalleTratamiento = detalleTratamiento;
    }

    public int getDuracionTratamiento() {
        return duracionTratamiento;
    }

    public void setDuracionTratamiento(int duracionTratamiento) {
        this.duracionTratamiento = duracionTratamiento;
    }

    public double getCostoTratamiento() {
        return costoTratamiento;
    }

    public void setCostoTratamiento(double costoTratamiento) {
        this.costoTratamiento = costoTratamiento;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public TipoMasaje getTipo() {
        return tipo;
    }

    public void setTipo(TipoMasaje tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Masaje{" + "codTratamiento=" + codTratamiento + ", nombreTratamiento=" + nombreTratamiento + ", detalleTratamiento=" + detalleTratamiento + ", duracionTratamiento=" + duracionTratamiento + ", costoTratamiento=" + costoTratamiento + ", activo=" + activo + ", tipo=" + tipo + '}';
    }
}