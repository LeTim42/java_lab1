public class Main {
    public static void main(String[] args) {
        complexTest();
        matrixTest();
    }

    public static void complexTest() {
        System.out.println("======== COMPLEX TEST ========");
        Complex numA = new Complex(5, -12);
        Complex numB = new Complex(-4, 3);
        print("A", numA);
        print("B", numB);
        print("A + B", numA.add(numB));
        print("A - B", numA.sub(numB));
        print("A * B", numA.mul(numB));
        print("A / B", numA.div(numB));
        print("~A", numA.conjugate());
        print("~B", numB.conjugate());
        print("mod(A)", numA.modulus());
        print("mod(B)", numB.modulus());
        print("arg(A)", numA.argument());
        print("arg(B)", numB.argument());
        System.out.println();
    }

    public static void matrixTest() {
        System.out.println("======== MATRIX TEST ========");
        Matrix matrixA = new Matrix(new double[][]
                {{ 1,  3, -2},
                 {-1,  2,  1},
                 { 1,  0, -2}});
        Matrix matrixB = new Matrix(new double[][]
                {{ 1,  3,  4},
                 { 0,  2,  1},
                 {-2,  5, -1}});
        print("Matrix A", matrixA);
        print("Matrix B", matrixB);
        print("Matrix A_T", matrixA.transpose());
        print("Matrix B_T", matrixB.transpose());
        print("Matrix A + B", matrixA.add(matrixB));
        print("Matrix A - B", matrixA.sub(matrixB));
        print("Matrix A * B", matrixA.mul(matrixB));
        print("Matrix B * A", matrixB.mul(matrixA));
        print("Matrix A * 2", matrixA.mul(new Complex(2)));
        print("det(A)", matrixA.determinant());
        print("det(B)", matrixB.determinant());
    }

    private static void print(String text, double num) {
        System.out.println(text + " = " + String.format(java.util.Locale.US, "%.2f", num));
    }

    private static void print(String text, Complex num) {
        System.out.println(text + " = " + num);
    }

    private static void print(String text, Matrix matrix) {
        System.out.println(text + ":");
        System.out.println(matrix);
    }
}
