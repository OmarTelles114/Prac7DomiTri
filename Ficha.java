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

    public void ajustarParaConexion(int numeroConectar) {
        if (lado1 != numeroConectar && lado2 == numeroConectar) {
            invertir();
        }
    }

    public boolean puedeConectarse(int numero) {
        return lado1 == numero || lado2 == numero;
    }

    @Override
    public void rotateRight() {
        invertir();
    }

    @Override
    public void rotateLeft() {
        invertir();
    }

    @Override
    public String toString() {
        return "[ " + lado1 + " ]\n[ " + lado2 + " ]";
    }

    public String toHorizontalString() {
        return "[ " + lado1 + " | " + lado2 + " ]";
    }

    public int getPuntaje() {
        return lado1 + lado2;
    }
}
