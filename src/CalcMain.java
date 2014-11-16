import MatrixGenerater.HirbeltMatrixGenerater;
import VectorGenerater.SameValueVector;

import java.util.Random;
import java.io.*;
import java.io.PrintWriter;
public class CalcMain {

	public static void main(String[] args) {
        int[] ns=new int[]{5,10,15};
        for (int n :ns)
        {
            calc1(n);
            calc2(n);
            calc3(n);
            calc4(n);
        }

	}

    private static void calc1(int n)
    {
        Matrix mat=new Matrix(new HirbeltMatrixGenerater(n));
        print(String.format("[1](n=%d)%f", n, mat.ConditionNumber()));
    }

    private static void calc2(int n)
    {
        Matrix mat=new Matrix(new HirbeltMatrixGenerater(n));
        Vector b=new Vector(new SameValueVector(n,1d));
        b=mat.MultiplyWith(b);//bの値を求める
        ISimultanousEquationSolver solver=new GausWithPivot();
        Vector answer=solver.Solve(mat, b);
        double residual=b.SubtractWith(mat.MultiplyWith(answer)).InfNorm();
        double error=answer.SubtractWith(new Vector(new SameValueVector(n,1d))).InfNorm();
        print("[2](n="+n+")残差:"+residual+"誤差:"+error);
    }

    private static void calc3(int n)
    {
        Matrix mat=new Matrix(new HirbeltMatrixGenerater(n));
        Vector b=new Vector(new SameValueVector(n,1d));
        b=mat.MultiplyWith(b);//bの値を求める
        Vector error=new Vector(n);
        error.getElements()[0]=b.getElements()[0]*0.001d;
        double maxError=mat.ConditionNumber()*error.InfNorm()/b.InfNorm();
        print("[3](n="+n+")最大誤差:"+maxError);
    }

    private static void calc4(int n)
    {
        Matrix mat=new Matrix(new HirbeltMatrixGenerater(n));
        Vector b=new Vector(new SameValueVector(n,1d));
        b=mat.MultiplyWith(b);//bの値を求める
        Vector be=new Vector(n);
        be.getElements()[0]=b.getElements()[0]*1.001d;
        ISimultanousEquationSolver solver=new GausWithPivot();
        Vector answer=solver.Solve(mat, b.AddWith(be));
        Vector trueB=new Vector(new SameValueVector(n,1d));
        double error=trueB.SubtractWith(answer).InfNorm();
        print("[4](n="+n+")誤差:"+error

        );
    }

    private static void print(String val)
    {
        System.out.println(val);
    }
}
