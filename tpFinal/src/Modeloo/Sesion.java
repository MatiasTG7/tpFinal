package Modeloo;

import java.time.LocalDate;

public class Sesion {
    private int codSesion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int codTratamiento;
    private int codMasajista;
    private int codPack;
    private boolean estadoInstalacion;

    public Sesion() {
    }

    public Sesion(int codSesion, LocalDate fechaInicio, LocalDate fechaFin, int codTratamiento, int codMasajista, int codPack, boolean estadoInstalacion) {
        this.codSesion = codSesion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.codTratamiento = codTratamiento;
        this.codMasajista = codMasajista;
        this.codPack = codPack;
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

    public boolean isEstadoInstalacion() {
        return estadoInstalacion;
    }

    public void setEstadoInstalacion(boolean estadoInstalacion) {
        this.estadoInstalacion = estadoInstalacion;
    }

    @Override
    public String toString() {
        return "Sesion{" + "codSesion=" + codSesion + ", fechaInicio=" + fechaInicio + ", fechaFin" + fechaFin + ", codTratamiento" + codTratamiento + ", codMasajista=" + codMasajista + ", codPack=" + codPack + ", estadoInstalacion" + "}";
        
        
        
    }
        
}
