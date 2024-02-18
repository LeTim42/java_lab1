public class Complex {
    private double re, im;

    public Complex() {
        this(0, 0);
    }

    public Complex(double re) {
        this(re, 0);
    }

    public Complex(Complex other) {
        this(other.re, other.im);
    }

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public void setRe(double re) {
        this.re = re;
    }

    public double getIm() {
        return im;
    }

    public void setIm(double im) {
        this.im = im;
    }

    public boolean equals(Complex other) {
        return re == other.re && im == other.im;
    }

    public Complex add(Complex other) {
        return new Complex(re + other.re, im + other.im);
    }

    public Complex sub(Complex other) {
        return new Complex(re - other.re, im - other.im);
    }

    public Complex mul(Complex other) {
        return new Complex(re * other.re - im * other.im,
                           re * other.im + im * other.re);
    }

    public Complex div(Complex other) {
        double denominator = Math.pow(other.modulus(), 2);
        if (denominator == 0)
            throw new ArithmeticException("Divide by zero");
        return mul(other.conjugate()).mul(new Complex(1/denominator));
    }

    public Complex conjugate() {
        return new Complex(re, -im);
    }

    public double modulus() {
        return Math.sqrt(re * re + im * im);
    }

    public double argument() {
        double res = Math.atan(im / re);
        if (re < 0) {
            if (im < 0) res -= Math.PI;
            else res += Math.PI;
        }
        return res;
    }

    @Override
    public String toString() {
        if (im == 0) return Double.toString(re);
        if (re == 0) return im + "i";
        return re + (im > 0 ? "+" : "") + im + "i";
    }
}
