package VectorGenerater;

/**
 * Created by LimeStreem on 2014/11/13.
 */
public class SameValueVector implements IVectorGenerater {

    int size;

    double value;

    public SameValueVector(int size, double value) {
        this.size = size;
        this.value = value;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public double getAt(int i) {
        return value;
    }
}
