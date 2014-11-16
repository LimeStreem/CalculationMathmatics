import MatrixGenerater.IMatrixGenerater;

/**
 * Created by LimeStreem on 2014/10/27.
 */
public class Matrix {
    private double[][] elements;
    private int size;

    public static Matrix Identity(int n) {
        Matrix mat = new Matrix(n);
        for (int i = 0; i < n; i++) {
            mat.getElements()[i][i] = 1;
        }
        return mat;
    }



    public int getSize() {
        return size;
    }

    public void setElements(double[][] mat) {
        this.elements = mat.clone();
        this.size = mat.length;
    }

    public double[][] getElements() {
        return elements;
    }

   public Matrix(IMatrixGenerater gen)
   {
       this(gen.size());
       for (int i = 0; i < size; i++) {
           for (int j = 0; j < size; j++) {
               getElements()[i][j]=gen.getAt(i,j);
           }
       }
   }

    public Matrix(double[][] mat) {
        this.setElements(mat);
    }

    public Matrix(int size) {
        this.setElements(new double[size][size]);
    }

    public String toString() {
        return Calc.stringfyMat(elements);
    }

    public Matrix AddWith(Matrix mat) {
        return new Matrix(Calc.addMat(this.elements, mat.elements));
    }

    public Matrix SubtractWith(Matrix mat){
     return new Matrix(Calc.subtractMat(this.elements,mat.elements));
    }

    public Matrix MultiplyWith(Matrix mat) {
        return new Matrix(Calc.multipleMat(this.elements, mat.elements));
    }

    public Vector MultiplyWith(Vector vec) {
        return new Vector(Calc.matVec(elements, vec.getElements()));
    }

    public Vector GetRow(int row) {
        return new Vector(elements[row]);
    }

    public void SetRow(int row, Vector vec) {
        elements[row] = vec.getElements();
    }

    public Vector GetColumn(int col) {
        double[] colmuns = new double[size];
        for (int i = 0; i < size; i++) {
            colmuns[i] = elements[i][col];
        }
        return new Vector(colmuns);
    }

    public void SetColumn(int colmun, Vector vec) {
        for (int i = 0; i < size; i++) {
            elements[i][colmun] = vec.getElements()[i];
        }
    }

    public Matrix Duplicate() {
        return new Matrix(elements);
    }

    public Matrix ExchangeRow(int k, int l) {
        Vector rk = GetRow(k);
        Vector rl = GetRow(l);
        Matrix result = Duplicate();
        result.SetRow(k, rl);
        result.SetRow(l, rk);
        return result;
    }

    public double NormN(NormType norm)
    {
        switch (norm)
        {

            case Infinity:
                return Calc.matNormInf(this.elements);
            case Two:
                throw new RuntimeException("Not implemented");
            case One:
                return Calc.matNorm1(this.elements);
            default:
                throw new RuntimeException("Index out of bounds");
        }
    }

    public Matrix ExchangeColmun(int k, int l) {
        Vector rk = GetColumn(k);
        Vector rl = GetColumn(l);
        Matrix result = Duplicate();
        result.SetColumn(k, rl);
        result.SetColumn(l, rk);
        return result;
    }

    public int MaxAbsAtColumn(int column, int min) {
        int r = min;
        for (int i = min; i < size; i++) {
            r = Math.abs(elements[r][column]) < Math.abs(elements[i][column]) ? i : r;
        }
        return r;
    }

    public double GetCoefficientForGauss(int from, int source) {
        Vector fromVector = GetRow(from);
        Vector sourceVector = GetRow(source);
        return (fromVector.getElements()[source]) / (sourceVector.getElements()[source]);
    }

    public LUPair DissolveLU() {
        ILUDissolver dissolver=new Gauss();
        return dissolver.getLUPair(this);
    }

    public double ConditionNumber()
    {
        return NormN(NormType.Infinity)*Invert().NormN(NormType.Infinity);
    }

    public Matrix Invert()
    {
        Matrix inverted=Matrix.Identity(size);
        Matrix result=new Matrix(size);
        LUPair lu=DissolveLU();
        for (int i = 0; i < getSize(); i++) {
            Vector yVec=new Vector(size);
            Vector xVec=new Vector(size);
            Vector ei=Matrix.Identity(size).GetColumn(i);
            for (int k = 0; k < getSize(); k++) {
                for (int j = 0; j <k; j++) {
                    ei.getElements()[k]=ei.getElements()[k]-lu.getL().getElements()[k][j]*yVec.getElements()[j];
                }
                yVec.getElements()[k]=ei.getElements()[k]/lu.getL().getElements()[k][k];
            }
            //後退代入
            for (int k = size-1; k>=0; k--) {
               for (int j=size-1;j>k;j--)
               {
                   yVec.getElements()[k]-=xVec.getElements()[j]*lu.getU().getElements()[k][j];
               }
                xVec.getElements()[k]=yVec.getElements()[k]/lu.getU().getElements()[k][k];
            }
            result.SetColumn(i,xVec);
        }
        return result;
    }
}



