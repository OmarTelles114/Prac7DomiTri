public class FichaTridomino extends Ficha {
    private int lado3;

    public FichaTridomino(int lado1, int lado2, int lado3) {
        super(lado1, lado2);
        this.lado3 = lado3;
    }

    public int getLado3() {
        return lado3;
    }

    @Override
    public void ajustarParaConexion(int numeroConectar, boolean esVertical) {
        while (lado1 != numeroConectar && lado2 != numeroConectar && lado3 != numeroConectar) {
            rotateRight();
        }

        if (esVertical && lado3 != numeroConectar) {
            rotateRight(); // Asegura que lado3 sea el lado de conexión para vertical.
        }
    }

    @Override
    public boolean puedeConectarse(Ficha otra) {
        return lado1 == otra.lado1 || lado1 == otra.lado2 || lado2 == otra.lado1 || lado2 == otra.lado2 ||
                lado3 == otra.lado1 || lado3 == otra.lado2;
    }

    @Override
    public void rotateRight() {
        int temp = lado1;
        lado1 = lado3;
        lado3 = lado2;
        lado2 = temp;
    }

    @Override
    public void rotateLeft() {
        rotateRight(); // La rotación izquierda es equivalente aquí.
    }

    @Override
    public String toString() {
        return "[ " + lado1 + " ]\n[ " + lado2 + " | " + lado3 + " ]";
    }

    public String toHorizontalString() {
        return "[ " + lado2 + " | " + lado3 + " ]\n[ " + lado1 + " ]";
    }
}
