/**
 * Created by LimeStreem on 2014/10/27.
 */
 public abstract class GaussBase implements ISimultanousEquationSolver {
    @Override
    public Vector Solve(Matrix equation, Vector ideal) {
        VectorMatrixTuple dismissed=FowardDismission(equation, ideal);
        return BackAssigning(dismissed.equation,dismissed.ideal).getIdeal();
    }

    protected abstract VectorMatrixTuple FowardDismission(Matrix equation,Vector ideal);

    protected VectorMatrixTuple BackAssigning(Matrix equation,Vector ideal)
    {
        int n= ideal.getSize();
        //後退代入過程
        for (int k = n-1; k >=0; k--) {
            for (int i = 0; i < n-1-k; i++) {
                ideal.getElements()[k]-=ideal.getElements()[n-1-i]*equation.getElements()[k][n-1-i];
            }
            ideal.getElements()[k]/=equation.getElements()[k][k];
        }
        return new VectorMatrixTuple(equation,ideal);
    }

    public class VectorMatrixTuple
    {
        private Matrix equation;

        private Vector ideal;

        public VectorMatrixTuple(Matrix equation, Vector ideal) {
            this.ideal = ideal;
            this.equation = equation;
        }

        public Vector getIdeal() {
            return ideal;
        }

        public void setIdeal(Vector ideal) {
            this.ideal = ideal;
        }

        public Matrix getEquation() {
            return equation;
        }

        public void setEquation(Matrix equation) {
            this.equation = equation;
        }
    }
}
