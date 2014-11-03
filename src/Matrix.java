import java.util.ArrayList;

/**
 * Created by LimeStreem on 2014/10/27.
 */
public class Matrix {
    private double[][] elements;
    private int size;

    public void setElements(double[][] mat)
    {
        this.elements=mat.clone();
        this.size=mat.length;
    }

    public double[][] getElements()
    {
        return elements;
    }
    public Matrix(double[][] mat)
    {
        this.setElements(mat);
    }

    public Matrix(int size)
    {
        this.setElements(new double[size][size]);
    }

    public String toString()
    {
        return Calc.stringfyMat(elements);
    }

    public Matrix AddWith(Matrix mat)
    {
        return new Matrix(Calc.addMat(this.elements,mat.elements));
    }

    public Matrix MultiplyWith(Matrix mat) {
        return new Matrix(Calc.multipleMat(this.elements,mat.elements));
    }

    public Vector MultiplyWith(Vector vec)
    {
        return new Vector(Calc.matVec(elements,vec.getElements()));
    }

    public Vector GetRow(int row)
    {
        return new Vector(elements[row]);
    }

    public void SetRow(int row,Vector vec)
    {
        elements[row]=vec.getElements();
    }

    public Vector GetColumn(int col)
    {
        double[] colmuns=new double[size];
        for (int i = 0; i < size; i++) {
            colmuns[i]=elements[i][col];
        }
        return new Vector(colmuns);
    }

    public void SetColumn(int colmun,Vector vec)
    {
        for (int i = 0; i < size; i++) {
           elements[i][colmun]=vec.getElements()[i];
        }
    }

    public Matrix Duplicate()
    {
        return new Matrix(elements);
    }

    public Matrix ExchangeRow(int k,int l)
    {
        Vector rk=GetRow(k);
        Vector rl=GetRow(l);
        Matrix result=Duplicate();
        result.SetRow(k, rl);
        result.SetRow(l,rk);
        return result;
    }

    public Matrix ExchangeColmun(int k,int l)
    {
        Vector rk=GetColumn(k);
        Vector rl=GetColumn(l);
        Matrix result=Duplicate();
        result.SetColumn(k, rl);
        result.SetColumn(l, rk);
        return result;
    }

    public int MaxAbsAtColumn(int column,int min)
    {
        int r=min;
        for (int i = min; i < size; i++) {
            r=Math.abs(elements[r][column])<Math.abs(elements[i][column])?i:r;
        }
        return r;
    }

    public double GetCoefficientForGauss(int from,int source)
    {
        Vector fromVector=GetRow(from);
        Vector sourceVector=GetRow(source);
        return (fromVector.getElements()[source])/(sourceVector.getElements()[source]);
    }
}
