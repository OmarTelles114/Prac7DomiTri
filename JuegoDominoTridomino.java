import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class JuegoDominoTridomino {

    private List<Ficha> pozo;
    private List<Ficha> mesa;
    private List<Ficha> jugador1;
    private List<Ficha> jugador2;
    private int numeroArriba;
    private int numeroAbajo;
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
        // Inicialización de fichas de dominó
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                pozo.add(new Ficha(i, j));
            }
        }

        // Inicialización de fichas de trídominó
        for (int i = 0; i < 56; i++) {
            pozo.add(new FichaTridomino(
                    (int) (Math.random() * 7),
                    (int) (Math.random() * 7),
                    (int) (Math.random() * 7)
            ));
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

        // Ambos jugadores seleccionan una ficha inicial
        System.out.println("Ambos jugadores seleccionan una ficha inicial.");
        Ficha fichaInicialJ1 = seleccionarFichaInicial(jugador1, scanner);
        Ficha fichaInicialJ2 = seleccionarFichaInicial(jugador2, scanner);

        // Determinar quién comienza
        if (fichaInicialJ1.getPuntaje() >= fichaInicialJ2.getPuntaje()) {
            System.out.println(nombreJugador1 + " comienza el juego.");
            mesa.add(fichaInicialJ1);
            jugador1.remove(fichaInicialJ1);
            numeroArriba = fichaInicialJ1.getLado1();
            numeroAbajo = fichaInicialJ1.getLado2();
        } else {
            System.out.println(nombreJugador2 + " comienza el juego.");
            mesa.add(fichaInicialJ2);
            jugador2.remove(fichaInicialJ2);
            numeroArriba = fichaInicialJ2.getLado1();
            numeroAbajo = fichaInicialJ2.getLado2();
        }

        boolean turnoJugador1 = fichaInicialJ1.getPuntaje() >= fichaInicialJ2.getPuntaje();

        // Ciclo del juego
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

                    System.out.print("¿Colocar arriba (1) o abajo (2)? ");
                    int posicion = scanner.nextInt();

                    if (posicion == 1 && fichaSeleccionada.puedeConectarse(numeroArriba)) {
                        colocarFichaEnMesa(fichaSeleccionada, 1);
                        jugadorActual.remove(fichaSeleccionada);
                        fichaColocada = true;
                    } else if (posicion == 2 && fichaSeleccionada.puedeConectarse(numeroAbajo)) {
                        colocarFichaEnMesa(fichaSeleccionada, 2);
                        jugadorActual.remove(fichaSeleccionada);
                        fichaColocada = true;
                    } else {
                        System.out.println("No puedes colocar esa ficha. Intenta otra.");
                    }
                } else {
                    System.out.println("Elección inválida. Intenta de nuevo.");
                }
            }
            turnoJugador1 = !turnoJugador1;
        }

        determinarGanador();
    }

    private Ficha seleccionarFichaInicial(List<Ficha> jugador, Scanner scanner) {
        System.out.println("Selecciona tu ficha inicial:");
        for (int i = 0; i < jugador.size(); i++) {
            System.out.println((i + 1) + ": " + jugador.get(i).toHorizontalString());
        }
        int eleccion = scanner.nextInt();
        return jugador.get(eleccion - 1);
    }

    private void colocarFichaEnMesa(Ficha ficha, int posicion) {
        if (ficha instanceof FichaTridomino) {
            FichaTridomino fichaTri = (FichaTridomino) ficha;
            fichaTri.ajustarParaConexion(posicion == 1 ? numeroArriba : numeroAbajo);
            if (posicion == 1) {
                mesa.add(0, fichaTri);
                numeroArriba = fichaTri.getLado1();
            } else {
                mesa.add(fichaTri);
                numeroAbajo = fichaTri.getLado2();
            }
        } else {
            ficha.ajustarParaConexion(posicion == 1 ? numeroArriba : numeroAbajo);
            if (posicion == 1) {
                mesa.add(0, ficha);
                numeroArriba = ficha.getLado1();
            } else {
                mesa.add(ficha);
                numeroAbajo = ficha.getLado2();
            }
        }
    }

    private void imprimirMesa() {
        System.out.println("Estado de la mesa:");
        for (Ficha ficha : mesa) {
            if (ficha instanceof FichaTridomino) {
                System.out.println(((FichaTridomino) ficha).toString());
            } else {
                System.out.println(ficha.toString());
            }
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
