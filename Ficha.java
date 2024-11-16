public class Ficha implements Movible {
    protected int lado1;
    protected int lado2;

    public Ficha(int lado1, int lado2) {
        this.lado1 = lado1;
        this.lado2 = lado2;
    }

    public int getLado1() {
        return lado1;
    }

    public int getLado2() {
        return lado2;
    }

    public void invertir() {
        int temp = lado1;
        lado1 = lado2;
        lado2 = temp;
    }

    public void ajustarParaConexion(int numeroConectar, boolean esVertical) {
        if (esVertical) {
            if (lado2 == numeroConectar) {
                invertir(); // Asegura que lado2 quede como lado de conexión para vertical.
            }
        } else {
            if (lado1 != numeroConectar && lado2 == numeroConectar) {
                invertir(); // Asegura que lado1 sea el lado de conexión.
            }
        }
    }

    public boolean puedeConectarse(Ficha otra) {
        return lado1 == otra.lado1 || lado1 == otra.lado2 || lado2 == otra.lado1 || lado2 == otra.lado2;
    }

    @Override
    public void rotateRight() {
        invertir(); // Para fichas de dominó, girar equivale a invertir.
    }

    @Override
    public void rotateLeft() {
        rotateRight(); // La rotación izquierda es equivalente aquí.
    }

    @Override
    public String toString() {
        return "[ " + lado1 + " ]\n[ " + lado2 + " ]";
    }

    public String toHorizontalString() {
        return "[ " + lado1 + " | " + lado2 + " ]";
    }
}
