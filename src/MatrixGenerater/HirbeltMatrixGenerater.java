package MatrixGenerater;

import java.util.Vector;

/**
 * Created by LimeStreem on 2014/11/12.
 */
public class HirbeltMatrixGenerater implements IMatrixGenerater{
    int size;

    public HirbeltMatrixGenerater(int size) {
        this.size = size;
    }

    @Override
    public double getAt(int i, int j) {
        return 1d/(i+j+1d);
    }

    @Override
    public int size() {
        return size;
    }
}
