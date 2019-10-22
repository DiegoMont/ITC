public class Estudiante {
    private String nombre, matricula;
    private double calificacion;

    public Estudiante(String nombre, String matricula, double calificacion) {
        super();
        this.nombre = nombre;
        this.matricula = matricula;
        this.calificacion = calificacion;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * @return the matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the calificacion
     */
    public double getCalificacion() {
        return calificacion;
    }

    @Override
    public String toString() {
        return matricula + " - " + nombre + "  - - - " + calificacion;
    }
}
