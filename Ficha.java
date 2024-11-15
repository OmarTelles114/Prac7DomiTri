public class Ficha implements Movible {
    protected int lado1;
    protected int lado2;
    private boolean horizontal = true; // Para controlar si se imprime en horizontal o vertical

    public Ficha(int lado1, int lado2) {
        this.lado1 = lado1;
        this.lado2 = lado2;
    }

    public int getPuntos() {
        return lado1 + lado2;
    }

    public boolean puedeColocarse(Ficha otra) {
        return this.lado1 == otra.lado1 || this.lado1 == otra.lado2 ||
                this.lado2 == otra.lado1 || this.lado2 == otra.lado2;
    }

    @Override
    public void rotateRight() {
        horizontal = !horizontal; // Cambia de horizontal a vertical y viceversa
        int temp = lado1;
        lado1 = lado2;
        lado2 = temp;
    }

    @Override
    public void rotateLeft() {
        horizontal = !horizontal; // Cambia de horizontal a vertical y viceversa
        int temp = lado1;
        lado1 = lado2;
        lado2 = temp;
    }

    @Override
    public String toString() {
        if (horizontal) {
            return "[ " + lado1 + " | " + lado2 + " ]";
        } else {
            return "[ " + lado1 + " ]\n[ " + lado2 + " ]";
        }
    }
}