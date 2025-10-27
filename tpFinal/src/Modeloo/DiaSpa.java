
package Modeloo;

import java.time.LocalDate;


public class DiaSpa {
    
    private int codPack;
    private LocalDate FechaYHora;
    private String preferencias;
    private Cliente codCliente;
    private double monto;
    private boolean estadoDia;

    public DiaSpa() {
    }

    public DiaSpa(LocalDate FechaYHora, String preferencias, Cliente codCliente, double monto, boolean estadoDia) {
        this.FechaYHora = FechaYHora;
        this.preferencias = preferencias;
        this.codCliente = codCliente;
        this.monto = monto;
        this.estadoDia = estadoDia;
    }

    public DiaSpa(int codPack, LocalDate FechaYHora, String preferencias, Cliente codCliente, double monto, boolean estadoDia) {
        this.codPack = codPack;
        this.FechaYHora = FechaYHora;
        this.preferencias = preferencias;
        this.codCliente = codCliente;
        this.monto = monto;
        this.estadoDia = estadoDia;
    }

    public int getCodPack() {
        return codPack;
    }

    public void setCodPack(int codPack) {
        this.codPack = codPack;
    }

    public LocalDate getFechaYHora() {
        return FechaYHora;
    }

    public void setFechaYHora(LocalDate FechaYHora) {
        this.FechaYHora = FechaYHora;
    }

    public String getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(String preferencias) {
        this.preferencias = preferencias;
    }

    public Cliente getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Cliente codCliente) {
        this.codCliente = codCliente;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public boolean isEstadoDia() {
        return estadoDia;
    }

    public void setEstadoDia(boolean estadoDia) {
        this.estadoDia = estadoDia;
    }

    @Override
    public String toString() {
        return "DiaSpa{" + "codPack=" + codPack + ", FechaYHora=" + FechaYHora + ", preferencias=" + preferencias + ", codCliente=" + codCliente + ", monto=" + monto + ", estadoDia=" + estadoDia + '}';
    }
    
}
