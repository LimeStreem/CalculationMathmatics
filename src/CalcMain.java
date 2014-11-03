import java.util.Random;
import java.io.*;
import java.io.PrintWriter;
public class CalcMain {

    private static double[] generateRandomVector(int num)
    {
        double[] result=new double[num];
        for (int i = 0; i < num; i++) {
            result[i]= Math.random();
            if(result[i]==0f)i--;
        }
        return result;
    }

    private static double[][] generateRandomMatrix(int num)
    {
        double[][] result=new double[num][];
        for (int i = 0; i < num; i++) {
            result[i]=generateRandomVector(num);
        }
        return result;
    }

    private static double calcError(double[][] A,double[] B,ISimultanousEquationSolver solver)
    {
        Vector result=solver.Solve(new Matrix(A),new Vector(B));
        double[] multiplied=Calc.matVec(A,result.getElements());
        double[] err=Calc.subVec(B,multiplied);
        return Calc.vecNorm2(err);
    }

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
        StringBuilder builder=new StringBuilder();
        builder.append("Pivot選択あり,Pivot選択なし\n");
        for (int i = 0; i < 100; i++) {
            double[][] A=generateRandomMatrix(100);
            double[] B=generateRandomVector(100);
            //Pivotなし
            ISimultanousEquationSolver solver=new Gauss();
            double withoutPivot=calcError(A,B,solver);
            solver=new GausWithPivot();
            double withpivot=calcError(A,B,solver);
            builder.append(withpivot+","+withoutPivot+"\n");
        }
        try {
            File file = new File("result.csv");
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.append(builder.toString());
            pw.close();
        }catch(Exception e)
        {

        }
//		int n=5;
//		//前進消去過程
//        for (int k = 0; k < n-1; k++) {
//            int l=matA.MaxAbsAtColumn(k);
//            if(matA.getElements()[l][k]<Double.MIN_NORMAL)
//            {
//                throw new RuntimeException("解けないでござる");
//            }
//            System.out.printf("%d列削除、%d,%d段入れ替え前\n",k,k,l);
//            System.out.println(vecB.toString());
//            System.out.println(matA.toString());
//            matA=matA.ExchangeRow(k,l);
//            vecB=vecB.Exchange(k,l);
//            System.out.printf("%d列削除、%d,%d段入れ替え後\n",k,k,l);
//            System.out.println(vecB.toString());
//            System.out.println(matA.toString());
//            for (int i = k+1; i < n; i++) {
//                double alpha=matA.GetCoefficientForGauss(i,k);
//                Vector sourceVec=matA.GetRow(k).Multiply(alpha);
//                matA.SetRow(i,matA.GetRow(i).SubtractWith(sourceVec));
//                vecB.getElements()[i]-=alpha*vecB.getElements()[k];
//                System.out.printf("%d列削除、%d段目\n",k,i);
//                System.out.println(vecB.toString());
//                System.out.println(matA.toString());
//            }
//
//        }
//        System.out.println(vecB.toString());
//        System.out.println(matA.toString());
//        //後退代入過程
//        for (int k = n-1; k >=0; k--) {
//            for (int i = 0; i < n-1-k; i++) {
//                B[k]-=B[n-1-i]*A[k][n-1-i];
//            }
//            B[k]/=A[k][k];
//        }
//        Calc.printMat(A);
//        Calc.printVec(B);
//
//        A=new double[][]{{1,2,1,2,1},
//                {2,3,2,3,2},
//                {1,2,3,4,5},
//                {4,3,8,1,2},
//                {8,2,4,1,9}};
//        Calc.printVec(Calc.matVec(A,B));
//        Calc.printVec(Calc.subVec(Calc.matVec(A,B),new double[]{7,7,7,7,7}));

	}

}
