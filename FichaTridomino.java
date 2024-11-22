public class FichaTridomino extends Ficha {
    private int lado3;

    public FichaTridomino(int lado1, int lado2, int lado3) {
        super(lado1, lado2);
        this.lado3 = lado3;
    }

    public int getLado3() {
        return lado3;
    }

    public void ajustarParaConexion(int ladoReferencia) {
        int intentos = 0;
        while (lado1 != ladoReferencia && lado2 != ladoReferencia && lado3 != ladoReferencia) {
            rotateRight();
            intentos++;
            if (intentos > 3) {
                break; // Evitar bucles infinitos en casos atípicos
            }
        }
    }

    @Override
    public void rotateRight() {
        int temp = lado1;
        lado1 = lado3;
        lado3 = lado2;
        lado2 = temp;
    }

    @Override
    public boolean puedeConectarse(int numero) {
        return lado1 == numero || lado2 == numero || lado3 == numero;
    }

    public String imprimirArriba() {
        return "[ " + lado2 + " | " + lado3 + " ]\n[     " + lado1 + "     ]";
    }

    public String imprimirAbajo() {
        return "[ " + lado2 + " | " + lado3 + " ]\n[     " + lado1 + "     ]";
    }

    @Override
    public String toString() {
        return "[ " + lado1 + " ]\n[ " + lado2 + " | " + lado3 + " ]";
    }

    public String toHorizontalString() {
        return "[ " + lado1 + " | " + lado2 + " | " + lado3 + " ]";
    }

    @Override
    public int getPuntaje() {
        return lado1 + lado2 + lado3;
    }
}
