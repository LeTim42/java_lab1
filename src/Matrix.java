import java.util.Arrays;

public class Matrix {
    private final Complex[][] matrix;
    private final int n, m;

    public Matrix(int n) {
        this(n, n);
    }

    public Matrix(int n, int m) {
        if (n <= 0 || m <= 0)
            throw new IllegalArgumentException("Matrix size must be non-negative");
        this.n = n;
        this.m = m;
        matrix = new Complex[n][m];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                matrix[i][j] = new Complex();
    }

    public Matrix(double[] data) {
        this(new double[][]{data});
    }

    public Matrix(double[][] data) {
        n = data.length;
        if (n == 0)
            throw new IllegalArgumentException("Matrix size must be non-negative");
        m = data[0].length;
        if (m == 0)
            throw new IllegalArgumentException("Matrix size must be non-negative");
        matrix = new Complex[n][m];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                matrix[i][j] = new Complex(data[i][j]);
    }

    public Matrix(Matrix other) {
        n = other.n;
        m = other.m;
        matrix = new Complex[n][m];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                matrix[i][j] = other.get(i, j);
    }

    public static Matrix identity(int n) {
        Matrix res = new Matrix(n, n);
        for (int i = 0; i < n; ++i)
            res.matrix[i][i] = new Complex(1);
        return res;
    }

    public int getRowsCount() {
        return matrix.length;
    }

    public int getColumnsCount() {
        return matrix[0].length;
    }

    public Complex get(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= m)
            throw new IndexOutOfBoundsException("Index out of matrix bounds");
        return new Complex(matrix[i][j]);
    }

    public void set(int i, int j, Complex num) {
        matrix[i][j] = new Complex(num);
    }

    public boolean equals(Matrix other) {
        if (n != other.n || m != other.m)
            return false;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                if (!matrix[i][j].equals(other.matrix[i][j]))
                    return false;
        return true;
    }

    public Matrix add(Matrix other) {
        if (n != other.n || m != other.m)
            throw new IllegalArgumentException("Matrix sizes do not match");
        Matrix res = new Matrix(n, m);
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                res.matrix[i][j] = matrix[i][j].add(other.matrix[i][j]);
        return res;
    }

    public Matrix sub(Matrix other) {
        if (n != other.n || m != other.m)
            throw new IllegalArgumentException("Matrix sizes do not match");
        Matrix res = new Matrix(n, m);
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                res.matrix[i][j] = matrix[i][j].sub(other.matrix[i][j]);
        return res;
    }

    public Matrix mul(Matrix other) {
        if (m != other.n)
            throw new IllegalArgumentException("Matrix sizes do not fit");
        Matrix res = new Matrix(n, other.m);
        for (int i = 0; i < res.n; ++i)
            for (int j = 0; j < res.m; ++j)
                for (int k = 0; k < m; ++k)
                    res.matrix[i][j] = res.matrix[i][j].add(matrix[i][k].mul(other.matrix[k][j]));
        return res;
    }

    public Matrix mul(Complex num) {
        Matrix res = new Matrix(n, m);
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                res.matrix[i][j] = matrix[i][j].mul(num);
        return res;
    }

    public Matrix transpose() {
        Matrix res = new Matrix(m, n);
        for (int i = 0; i < res.n; ++i)
            for (int j = 0; j < res.m; ++j)
                res.matrix[i][j] = get(j, i);
        return res;
    }

    public Complex determinant() {
        if (n != m)
            throw new IllegalStateException("Matrix must be square");
        Matrix res = new Matrix(this);
        Complex det = new Complex(1), total = new Complex(1);
        for (int i = 0; i < n; ++i) {
            int index = i;
            while (index < n && res.matrix[index][i].modulus() == 0)
                ++index;
            if (index == n) continue;
            if (index != i) {
                for (int j = 0; j < n; ++j) {
                    Complex temp = res.get(index, i);
                    res.matrix[index][i] = res.get(i, j);
                    res.matrix[i][j] = temp;
                }
                if ((index - i) % 2 == 1)
                    det = det.mul(new Complex(-1));
            }
            Complex[] temp = new Complex[n];
            for (int j = 0; j < n; ++j)
                temp[j] = res.get(i, j);
            Complex num1 = temp[i];
            for (int j = i + 1; j < n; ++j) {
                Complex num2 = res.get(j, i);
                for (int k = 0; k < n; ++k)
                    res.matrix[j][k] = res.matrix[j][k].mul(num1).sub(temp[k].mul(num2));
                total = total.mul(num1);
            }
        }
        for (int i = 0; i < n; ++i)
            det = det.mul(res.matrix[i][i]);
        return det.div(total);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j)
                res.append(matrix[i][j]).append(" ");
            res.append("\n");
        }
        return res.toString();
    }
}
