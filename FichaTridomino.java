public class FichaTridomino extends Ficha {
    private int lado3;

    public FichaTridomino(int lado1, int lado2, int lado3) {
        super(lado1, lado2);
        this.lado3 = lado3;
    }

    @Override
    public int getPuntos() {
        return super.getPuntos() + lado3;
    }

    @Override
    public boolean puedeColocarse(Ficha otra) {
        if (otra instanceof FichaTridomino) {
            FichaTridomino fichaTridomino = (FichaTridomino) otra;
            return lado1 == fichaTridomino.lado1 || lado1 == fichaTridomino.lado2 || lado1 == fichaTridomino.lado3 ||
                    lado2 == fichaTridomino.lado1 || lado2 == fichaTridomino.lado2 || lado2 == fichaTridomino.lado3 ||
                    lado3 == fichaTridomino.lado1 || lado3 == fichaTridomino.lado2 || lado3 == fichaTridomino.lado3;
        }
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
        int temp = lado1;
        lado1 = lado2;
        lado2 = lado3;
        lado3 = temp;
    }

    @Override
    public String toString() {
        return "   *\n    * " + lado1 + " *\n  * " + lado2 + " | " + lado3 + " *\n*  *  *  *  *";
    }
}