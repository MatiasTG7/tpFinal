
package Modeloo;

import java.time.LocalDate;


public class Masaje {
    private int codSesion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int codTratamiento;
    private int codMasajista;
    private int codPack;
    private int codInstal;
    private boolean estadoInstalacion;

         public Masaje() {
    }
         
    public Masaje(int codSesion, LocalDate fechaInicio, LocalDate fechaFin, int codTratamiento, int codMasajista, int codPack, int codInstal, boolean estadoInstalacion) {
        this.codSesion = codSesion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.codTratamiento = codTratamiento;
        this.codMasajista = codMasajista;
        this.codPack = codPack;
        this.codInstal = codInstal;
        this.estadoInstalacion = estadoInstalacion;
    }

    public int getCodSesion() {
        return codSesion;
    }

    public void setCodSesion(int codSesion) {
        this.codSesion = codSesion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getCodTratamiento() {
        return codTratamiento;
    }

    public void setCodTratamiento(int codTratamiento) {
        this.codTratamiento = codTratamiento;
    }

    public int getCodMasajista() {
        return codMasajista;
    }

    public void setCodMasajista(int codMasajista) {
        this.codMasajista = codMasajista;
    }

    public int getCodPack() {
        return codPack;
    }

    public void setCodPack(int codPack) {
        this.codPack = codPack;
    }

    public int getCodInstal() {
        return codInstal;
    }

    public void setCodInstal(int codInstal) {
        this.codInstal = codInstal;
    }

    public boolean isEstadoInstalacion() {
        return estadoInstalacion;
    }

    public void setEstadoInstalacion(boolean estadoInstalacion) {
        this.estadoInstalacion = estadoInstalacion;
    }

    @Override
    public String toString() {
        return "sesion{"+
                "codSesion=" + codSesion
                +", fechaInicio" + fechaInicio
                +", fechafin" + fechaFin
                +", codTratamiento" + codTratamiento
                +", codMasajista" + codMasajista
                +", codPack" + codPack
                +", codInstal" + codInstal
                +", EstadoInstalacion" + estadoInstalacion +
                "}";
        
    }
}
