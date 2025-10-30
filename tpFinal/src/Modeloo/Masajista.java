
package Modeloo;


public class Masajista {
    private int codMasajista;
    private String matricula;
    private String nombreMasajista;
    private String telefonoMasajista;
    private boolean estadoMasajista;
    private EspecialidadMasajista especialidad;
    
    public Masajista() {
    }

    public Masajista(String matricula, String nombreMasajista, String telefonoMasajista, boolean estadoMasajista, EspecialidadMasajista especialidad) {
        this.matricula = matricula;
        this.nombreMasajista = nombreMasajista;
        this.telefonoMasajista = telefonoMasajista;
        this.estadoMasajista = estadoMasajista;
        this.especialidad = especialidad;
    }

    public Masajista(int codMasajista, String matricula, String nombreMasajista, String telefonoMasajista, boolean estadoMasajista, EspecialidadMasajista especialidad) {
        this.codMasajista = codMasajista;
        this.matricula = matricula;
        this.nombreMasajista = nombreMasajista;
        this.telefonoMasajista = telefonoMasajista;
        this.estadoMasajista = estadoMasajista;
        this.especialidad = especialidad;
    }

    public int getCodMasajista() {
        return codMasajista;
    }

    public void setCodMasajista(int codMasajista) {
        this.codMasajista = codMasajista;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombreMasajista() {
        return nombreMasajista;
    }

    public void setNombreMasajista(String nombreMasajista) {
        this.nombreMasajista = nombreMasajista;
    }

    public String getTelfonoMasajista() {
        return telefonoMasajista;
    }

    public void setTelfonoMasajista(String telfonoMasajista) {
        this.telefonoMasajista = telfonoMasajista;
    }

    public boolean isEstadoMasajista() {
        return estadoMasajista;
    }

    public void setEstadoMasajista(boolean estadoMasajista) {
        this.estadoMasajista = estadoMasajista;
    }

    public EspecialidadMasajista getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(EspecialidadMasajista especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Masajista{" + "codMasajista=" + codMasajista + ", matricula=" + matricula + ", nombreMasajista=" + nombreMasajista + ", telfonoMasajista=" + telefonoMasajista + ", estadoMasajista=" + estadoMasajista + ", especialidad=" + especialidad + '}';
    }

 
}
