/**
 * Created by LimeStreem on 2014/10/27.
 */
public class Gauss extends GaussBase {
    @Override
    protected VectorMatrixTuple FowardDismission(Matrix equation, Vector ideal) {
        int n=ideal.getSize();
        Matrix matA=equation;
        Vector vecB=ideal;
        for (int k = 0; k < n-1; k++) {
            if(Math.abs(matA.getElements()[k][k])<Double.MIN_NORMAL)
            {
                throw new RuntimeException("解けないでござる");
            }
            for (int i = k+1; i < n; i++) {
                double alpha=matA.GetCoefficientForGauss(i,k);
                Vector sourceVec=matA.GetRow(k).Multiply(alpha);
                matA.SetRow(i,matA.GetRow(i).SubtractWith(sourceVec));
                vecB.getElements()[i]-=alpha*vecB.getElements()[k];
//                System.out.printf("%d列削除、%d段目\n",k,i);
//                System.out.println(vecB.toString());
//                System.out.println(matA.toString());
            }
        }
        return new VectorMatrixTuple(matA,vecB);

    }
}