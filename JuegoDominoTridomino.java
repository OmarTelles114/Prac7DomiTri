import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class JuegoDominoTridomino {
    private List<Ficha> pozo;
    private List<Ficha> mesa;
    private List<Ficha> jugador1;
    private List<Ficha> jugador2;
    private String nombreJugador1;
    private String nombreJugador2;

    public JuegoDominoTridomino() {
        pozo = new ArrayList<>();
        mesa = new ArrayList<>();
        jugador1 = new ArrayList<>();
        jugador2 = new ArrayList<>();
        inicializarPozo();
        repartirFichas();
    }

    private void inicializarPozo() {
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                pozo.add(new Ficha(i, j));
            }
        }
        Random rand = new Random();
        for (int i = 0; i < 56; i++) {
            pozo.add(new FichaTridomino(rand.nextInt(7), rand.nextInt(7), rand.nextInt(7)));
        }
        Collections.shuffle(pozo);
    }

    private void repartirFichas() {
        for (int i = 0; i < 10; i++) {
            jugador1.add(pozo.remove(0));
            jugador2.add(pozo.remove(0));
        }
    }

    public void jugar() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del Jugador 1: ");
        nombreJugador1 = scanner.nextLine();

        System.out.print("Ingrese el nombre del Jugador 2: ");
        nombreJugador2 = scanner.nextLine();

        System.out.println("¡Inicia el juego de Dominó y Tridominó!");
        boolean turnoJugador1 = true;

        while (!jugador1.isEmpty() && !jugador2.isEmpty()) {
            List<Ficha> jugadorActual = turnoJugador1 ? jugador1 : jugador2;
            String nombreActual = turnoJugador1 ? nombreJugador1 : nombreJugador2;

            System.out.println("\nMesa:");
            imprimirMesa();
            System.out.println(nombreActual + ", tus fichas son:");
            for (int i = 0; i < jugadorActual.size(); i++) {
                System.out.println((i + 1) + ": " + jugadorActual.get(i).toHorizontalString());
            }
            System.out.println("0: Tomar ficha del pozo");

            boolean fichaColocada = false;
            while (!fichaColocada) {
                System.out.print(nombreActual + ", elige una ficha: ");
                int eleccion = scanner.nextInt();

                if (eleccion == 0) {
                    if (!pozo.isEmpty()) {
                        Ficha nuevaFicha = pozo.remove(0);
                        jugadorActual.add(nuevaFicha);
                        System.out.println("Tomaste una ficha: " + nuevaFicha.toHorizontalString());
                    } else {
                        System.out.println("No hay más fichas en el pozo.");
                    }
                    break;
                } else if (eleccion > 0 && eleccion <= jugadorActual.size()) {
                    Ficha fichaSeleccionada = jugadorActual.get(eleccion - 1);

                    if (mesa.isEmpty()) {
                        mesa.add(fichaSeleccionada);
                        jugadorActual.remove(fichaSeleccionada);
                        fichaColocada = true;
                    } else {
                        System.out.print("¿Colocar arriba (1) o abajo (2)? ");
                        int posicion = scanner.nextInt();

                        if (posicion == 1 && fichaSeleccionada.puedeConectarse(mesa.get(0))) {
                            fichaSeleccionada.ajustarParaConexion(mesa.get(0).getLado1(), true);
                            mesa.add(0, fichaSeleccionada);
                            jugadorActual.remove(fichaSeleccionada);
                            fichaColocada = true;
                        } else if (posicion == 2 && fichaSeleccionada.puedeConectarse(mesa.get(mesa.size() - 1))) {
                            fichaSeleccionada.ajustarParaConexion(mesa.get(mesa.size() - 1).getLado2(), true);
                            mesa.add(fichaSeleccionada);
                            jugadorActual.remove(fichaSeleccionada);
                            fichaColocada = true;
                        } else {
                            System.out.println("No puedes colocar esa ficha. Intenta otra.");
                        }
                    }
                } else {
                    System.out.println("Elección inválida. Intenta de nuevo.");
                }
            }

            turnoJugador1 = !turnoJugador1;
        }

        determinarGanador();
        scanner.close();
    }

    private void imprimirMesa() {
        for (Ficha ficha : mesa) {
            System.out.println(ficha);
        }
    }

    private void determinarGanador() {
        int puntosJugador1 = jugador1.size();
        int puntosJugador2 = jugador2.size();

        System.out.println("\nJuego terminado.");
        System.out.println(nombreJugador1 + ": " + puntosJugador1 + " puntos.");
        System.out.println(nombreJugador2 + ": " + puntosJugador2 + " puntos.");

        if (puntosJugador1 < puntosJugador2) {
            System.out.println("¡" + nombreJugador1 + " gana!");
        } else if (puntosJugador1 > puntosJugador2) {
            System.out.println("¡" + nombreJugador2 + " gana!");
        } else {
            System.out.println("¡Es un empate!");
        }
    }
}
