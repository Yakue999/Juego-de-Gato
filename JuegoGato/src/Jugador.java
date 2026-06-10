public class Jugador {
private int id;
    private String nombre;
    private int completadas;
    private int noCompletadas;

    /**
     * Constructor por defecto de la clase Jugador.
     * 
     * Inicializa los atributos con valores por defecto.
     */
    public Jugador() {
        this.id=0;
	this.nombre="";
	this.completadas=0;
	this.noCompletadas=0;
    }

    /**
     * Constructor parametrizado de la clase Jugador.
     * 
     * @param id El identificador único del jugador.
     * @param nombre El nombre del jugador.
     * @param completadas El número de partidas completadas.
     * @param noCompletadas El número de partidas no completadas.
     */
    public Jugador(int id, String nombre, int completadas, int noCompletadas) {
        this.id = id;
        this.nombre = nombre;
        this.completadas = completadas;
        this.noCompletadas = noCompletadas;
    }

    /**
     * Método getter para obtener el ID del jugador.
     * 
     * @return El ID del jugador.
     */
    public int getId() { return id; }

    /**
     * Método setter para establecer el ID del jugador.
     * 
     * @param id El nuevo ID del jugador.
     */
    public void setId(int id) { this.id = id; }

    /**
     * Método getter para obtener el nombre del jugador.
     * 
     * @return El nombre del jugador.
     */
    public String getNombre() { return nombre; }

    /**
     * Método setter para establecer el nombre del jugador.
     * 
     * @param nombre El nuevo nombre del jugador.
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Método getter para obtener el número de partidas completadas.
     * 
     * @return El número de partidas completadas.
     */
    public int getCompletadas() { return completadas; }

    /**
     * Método setter para establecer el número de partidas completadas.
     * 
     * @param completadas El nuevo número de partidas completadas.
     */
    public void setCompletadas(int completadas) { this.completadas = completadas; }

    /**
     * Método getter para obtener el número de partidas no completadas.
     * 
     * @return El número de partidas no completadas.
     */
    public int getNoCompletadas() { return noCompletadas; }

    /**
     * Método setter para establecer el número de partidas no completadas.
     * 
     * @param noCompletadas El nuevo número de partidas no completadas.
     */
    public void setNoCompletadas(int noCompletadas) { this.noCompletadas = noCompletadas; }

    /**
     * Método para registrar una victoria en el contador de partidas completadas.
     * 
     * Incrementa el contador de completadas en 1.
     */
    public void registrarVictoria() {this.completadas++;}

    /**
     * Método para registrar una derrota en el contador de partidas no completadas.
     * 
     * Incrementa el contador de no completadas en 1.
     */
    public void registrarDerrota() {this.noCompletadas++;}

    /**
     * Método equals para comparar dos objetos Jugador.
     * 
     * Compara basado en el ID del jugador.
     * 
     * @param obj El objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Jugador jugador = (Jugador) obj;
        return id == jugador.id;
    }

    /**
     * Método toString para representar el objeto Jugador en forma de cadena.
     * 
     * @return Una cadena con la información del jugador.
     */
    @Override
    public String toString() {
        return "Jugador ID = " + id + ", Nombre = " + nombre + 
               ", Completadas =" + completadas + ", No Completadas = " + noCompletadas;
    }
}
