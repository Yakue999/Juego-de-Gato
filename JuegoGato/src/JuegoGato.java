import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class JuegoGato extends Application {

    // Variables globales para controlar el estado del juego
    private Stage stage;
    private Jugador jugadorActual;    
    private Jugador jugadorActual2;    
    private TextArea areaHistorial = new TextArea("Historial de Movimientos\n");
    private boolean turnoX = true; // true = Turno de X, false = Turno de O
    private Button[][] tablero = new Button[3][3];
    private Label etiquetaEstado = new Label("Turno de: X");
    private boolean juegoTerminado = false;

    @Override
    public void start(Stage primaryStage) {
    this.stage = primaryStage; 
                 primaryStage.setTitle("Juego del Gato");
                 primaryStage.setScene(Registro()); 
                 primaryStage.show(); 
}
 public Scene Registro() {
    // Jugador 1
    Label lbJ1 = new Label("Jugador 1:");
    TextField tfNombreJ1 = new TextField();
    tfNombreJ1.setPromptText("Nombre");
    Label lbID1 = new Label("ID 1:"); // Etiqueta para ID 1
    TextField tfIDJ1 = new TextField();
    tfIDJ1.setPromptText("ID");

    // Jugador 2
    Label lbJ2 = new Label("Jugador 2:");
    TextField tfNombreJ2 = new TextField();
    tfNombreJ2.setPromptText("Nombre");
    Label lbID2 = new Label("ID 2:"); // Etiqueta para ID 2
    TextField tfIDJ2 = new TextField();
    tfIDJ2.setPromptText("ID");

    // Botón
    Button btnEnviar = new Button("Enviar");
    btnEnviar.setOnAction(e -> { 
        try{
            String nombre = tfNombreJ1.getText();
            int id = Integer.parseInt(tfIDJ1.getText());
            String nombre2 = tfNombreJ2.getText();
            int id2 = Integer.parseInt(tfIDJ2.getText());
            jugadorActual = new Jugador(id, nombre, 0,0);
            jugadorActual2 = new Jugador(id2, nombre2,0,0);
            System.out.print(jugadorActual);
            System.out.print(jugadorActual2);
            stage.setScene(Interfaz()); 
        }catch(NumberFormatException ex){
            System.out.println("Error: El ID debe ser un número");
        }
        });
    VBox vbPpal = new VBox(10); // Espaciado de 10 entre elementos
    vbPpal.setPadding(new Insets(20));
    vbPpal.setAlignment(Pos.CENTER);
    
    // AGREGAR TODOS LOS NODOS (Sin repetir ninguno)
    vbPpal.getChildren().addAll(
        lbJ1, tfNombreJ1, lbID1, tfIDJ1, 
        lbJ2, tfNombreJ2, lbID2, tfIDJ2, 
        btnEnviar
    );

    return new Scene(vbPpal, 450, 450);
}
    public Scene Interfaz(){
        Button btnJugar = new Button("Jugar ");
        Button btnEstadistica = new Button("Estadísticas");
	Button btnSalir = new Button("Salir");

        btnJugar.setOnAction(e -> {stage.setScene(Juego()); });
        btnEstadistica.setOnAction(e->{stage.setScene(Estadisticas());});
        btnSalir.setOnAction(e -> stage.close());
        VBox vbPpal = new VBox(20);
	vbPpal.getChildren().addAll(btnJugar,btnEstadistica,btnSalir);
        vbPpal.setAlignment(Pos.CENTER);
        Scene scnPpal = new Scene(vbPpal,450,450);
        return scnPpal;
    }
    
   public Scene Estadisticas() {
    Label lblDatos = new Label("Estadísticas de: " + jugadorActual.getNombre());
    Label lblID = new Label("ID de Usuario: " + jugadorActual.getId());
    Label lblCompletadas = new Label("Partidas Ganadas: " + jugadorActual.getCompletadas());
    Label lblNoCompletadas = new Label("Partidas Perdidas: " + jugadorActual.getNoCompletadas());

    Label lblDatos2 = new Label("Estadísticas de: " + jugadorActual2.getNombre());
    Label lblID2 = new Label("ID de Usuario: " + jugadorActual2.getId());
    Label lblCompletadas2 = new Label("Partidas Ganadas: " + jugadorActual2.getCompletadas());
    Label lblNoCompletadas2 = new Label("Partidas Perdidas: " + jugadorActual2.getNoCompletadas());
    
    Button btnRegresar = new Button("Volver al Menú");
    btnRegresar.setOnAction(e -> {stage.setScene(Interfaz());}); 

    VBox vbPpal = new VBox(20, 
            lblDatos, lblID, lblCompletadas, lblNoCompletadas,
            lblDatos2, lblID2, lblCompletadas2, lblNoCompletadas2, btnRegresar);
    vbPpal.setAlignment(Pos.CENTER);
    return new Scene(vbPpal, 450, 450);
} 
    
    public Scene Juego(){
    // Contenedor principal
        BorderPane raiz = new BorderPane();
        raiz.setPadding(new Insets(20));

        // Etiqueta de estado en la parte superior
        etiquetaEstado.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        BorderPane.setAlignment(etiquetaEstado, Pos.CENTER);
        raiz.setTop(etiquetaEstado);
        
        //Historial de la partida
        areaHistorial.setEditable(false);
        areaHistorial.setPrefWidth(180); 
        raiz.setRight(areaHistorial);

        // Cuadrícula para los botones (El tablero 3x3)
        GridPane cuadricula = new GridPane();
        cuadricula.setAlignment(Pos.CENTER);
        cuadricula.setHgap(5); // Espacio horizontal entre botones
        cuadricula.setVgap(5); // Espacio vertical entre botones

        // Llenar el tablero usando los índices estándar i (filas) y j (columnas)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button boton = new Button("");
                boton.setPrefSize(100, 100); // Tamaño cuadradito
                boton.setFont(Font.font("Arial", FontWeight.BOLD, 36));
                
                // Acción al hacer clic en el botón
                boton.setOnAction(e -> realizarMovimiento(boton));
                
                tablero[i][j] = boton;
                cuadricula.add(boton, j, i); // En GridPane se añade (columna, fila) -> (j, i)
            }
        }
        
        raiz.setCenter(cuadricula);

        // Botón de reiniciar en la parte inferior
        Button btnReiniciar = new Button("Reiniciar Juego");
        Button btnRegresar = new Button("Regresar");
        btnReiniciar.setFont(Font.font("Arial", 16));
        btnReiniciar.setOnAction(e -> reiniciarJuego());
        btnRegresar.setOnAction(e->{stage.setScene(Interfaz());});
        VBox cajaInferior = new VBox(btnReiniciar, btnRegresar);
        cajaInferior.setAlignment(Pos.CENTER);
        cajaInferior.setPadding(new Insets(20, 0, 0, 0));
        raiz.setBottom(cajaInferior);
        Scene scnPpal = new Scene(raiz,700,500);
        return scnPpal;
    }


    // Método que se ejecuta cuando tocas un cuadro
    private void realizarMovimiento(Button boton) {
        
        if (juegoTerminado || !boton.getText().isEmpty()) return;

        String ficha = turnoX ? "X" : "O";
        String nombreJugador = turnoX ? jugadorActual.getNombre() : jugadorActual2.getNombre();

        boton.setText(ficha);
        boton.setStyle(turnoX ? "-fx-text-fill: red;" : "-fx-text-fill: blue;");

        // AGREGAR AL HISTORIAL
        areaHistorial.appendText(nombreJugador + " puso " + ficha + "\n");

        etiquetaEstado.setText("Turno de: " + (turnoX ? "O" : "X"));
        verificarGanador(); 
        turnoX = !turnoX;
        
        // Si el juego ya acabó o el botón ya tiene texto, no hacemos nada
        if (juegoTerminado || !boton.getText().isEmpty()) {
            return;
        }

        // Poner X o O dependiendo de quién sea el turno
        if (turnoX) {
            boton.setText("X");
            boton.setStyle("-fx-text-fill: red;"); // Un poco de color para diferenciar
            etiquetaEstado.setText("Turno de: O");
        } else {
            boton.setText("O");
            boton.setStyle("-fx-text-fill: blue;");
            etiquetaEstado.setText("Turno de: X");
        }

        verificarGanador(); // Revisar si este movimiento ganó el juego
        turnoX = !turnoX;   // Cambiar el turno al otro jugador
    }

    // Lógica para revisar si alguien ganó
    private void verificarGanador() {
        String ganador = "";

        // Revisar filas y columnas usando i y j
        for (int i = 0; i < 3; i++) {
            // Revisar fila i
            if (!tablero[i][0].getText().isEmpty() &&
                tablero[i][0].getText().equals(tablero[i][1].getText()) &&
                tablero[i][0].getText().equals(tablero[i][2].getText())) {
                ganador = tablero[i][0].getText();
            }
            // Revisar columna i
            if (!tablero[0][i].getText().isEmpty() &&
                tablero[0][i].getText().equals(tablero[1][i].getText()) &&
                tablero[0][i].getText().equals(tablero[2][i].getText())) {
                ganador = tablero[0][i].getText();
            }
        }

        // Revisar diagonales
        if (!tablero[0][0].getText().isEmpty() &&
            tablero[0][0].getText().equals(tablero[1][1].getText()) &&
            tablero[0][0].getText().equals(tablero[2][2].getText())) {
            ganador = tablero[0][0].getText();
        }
        if (!tablero[0][2].getText().isEmpty() &&
            tablero[0][2].getText().equals(tablero[1][1].getText()) &&
            tablero[0][2].getText().equals(tablero[2][0].getText())) {
            ganador = tablero[0][2].getText();
        }

        // Si hay un ganador, detener el juego y anunciarlo
        if (!ganador.isEmpty()) {
            etiquetaEstado.setText("¡El ganador es: " + ganador + "!");
            juegoTerminado = true;
        } else if (tableroLleno()) {
            etiquetaEstado.setText("¡Es un Empate!");
            juegoTerminado = true;
        }
    
    if (!ganador.isEmpty()) {
        juegoTerminado = true;
        if (ganador.equals("X")) {
            etiquetaEstado.setText("¡Ganó " + jugadorActual.getNombre() + "!");
            jugadorActual.registrarVictoria(); // +1 al J1
            jugadorActual2.registrarDerrota();  // +1 al J2
            areaHistorial.appendText(">>> GANADOR: " + jugadorActual.getNombre() + "\n");
        } else {
            etiquetaEstado.setText("¡Ganó " + jugadorActual2.getNombre() + "!");
            jugadorActual2.registrarVictoria(); // +1 al J2
            jugadorActual.registrarDerrota();  // +1 al J1
            areaHistorial.appendText(">>> GANADOR: " + jugadorActual2.getNombre() + "\n");
        }
    } else if (tableroLleno()) {
        etiquetaEstado.setText("¡Es un Empate!");
        juegoTerminado = true;
        areaHistorial.appendText(">>> EMPATE\n");
    }
    
    
    }

    // Método auxiliar para saber si ya se llenaron todos los cuadros
    private boolean tableroLleno() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j].getText().isEmpty()) {
                    return false; // Todavía hay espacios vacíos
                }
            }
        }
        return true; // No hay espacios vacíos
    }
    
   
    
    // Dejar todo como nuevo para volver a jugar
    private void reiniciarJuego() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j].setText("");
            }
        }
        turnoX = true;
        juegoTerminado = false;
        etiquetaEstado.setText("Turno de: X");
        
        areaHistorial.setText("Historial de Movimientos\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}