public class Participante {
    private int folio;
    private String nombre;
    private Boolean asistencia;

    public Participante(Boolean asistencia, int folio, String nombre) {
        this.asistencia = asistencia;
        this.folio = folio;
        this.nombre = nombre;
    }

    public int getFolio() {
        return folio;
    }

    public String getNombre() {
        return nombre;
    }

    public Boolean getAsistencia() {
        return asistencia;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAsistencia(Boolean asistencia) {
        this.asistencia = asistencia;
    }
}
