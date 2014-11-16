public class LUPair
{
    private Matrix L;

    private Matrix U;

    public LUPair(Matrix l, Matrix u) {
        L = l;
        U = u;
    }

    public Matrix getL() {
        return L;
    }

    public Matrix getU() {
        return U;
    }

    @Override
    public String toString() {
        return "LUPair{" +
                "L=" + L.toString() +
                ", U=" + U.toString() +
                '}';
    }
}
