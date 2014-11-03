

public class Calc {
	private Calc()
	{
	}

	public static void printVec(double[] x)
	{
		System.out.println(stringfyVec(x));
	}

    public static String stringfyVec(double[] x)
    {
        StringBuilder builder=new StringBuilder();
        builder.append("{");
        int count=0;
        for(double elem:x)
        {
            builder.append(elem);
            if(count!=x.length-1)builder.append(",");
        }

        builder.append("}");
        builder.append("[Norm:"+vecNorm2(x)+"]");
        return builder.toString();
    }

    public static String stringfyMat(double[][] A)
    {
        StringBuilder builder=new StringBuilder();
        builder.append("{");
        for(int i=0;i<A.length;i++)
        {
            builder.append("{");
            for(int j=0;j<A[i].length;j++)
            {
                builder.append(A[i][j]);
                if(j!=A[i].length-1)builder.append(",");
            }
            builder.append("}\n");
            if(i!=A.length-1)builder.append(",");
        }
        return builder.toString();
    }


	public static void printMat(double[][]A)
	{
		System.out.println(stringfyMat(A));
	}

	public static double[] scalarMultiple(double c,double[] x)
	{
		double[] ret=new double[x.length];
		for (int i = 0; i < x.length; i++) {
			ret[i]=x[i]*c;
		}
		return ret;
	}

	public static double[] addVec(double[] x,double[] y)
	{
		if(x.length!=y.length)throw new RuntimeException("定義されていない演算:次元数の違うベクトルの加算");
		double[] ret=new double[x.length];
		for(int i=0;i<x.length;i++)
		{
			ret[i]=x[i]+y[i];
		}
		return ret;
	}


	public static double[] subVec(double[] x,double[] y)
	{
		if(x.length!=y.length)throw new RuntimeException("定義されていない演算:次元数の違うベクトルの減算");
		double[] ret=new double[x.length];
		for(int i=0;i<x.length;i++)
		{
			ret[i]=x[i]-y[i];
		}
		return ret;
	}

	public static double innProd(double[]x,double[]y)
	{
		if(x.length!=y.length)throw new RuntimeException("定義されていない演算:次元数の違うベクトルの内積");
		double ret=0;
		for(int i=0;i<x.length;i++)
		{
			ret+=x[i]*y[i];
		}
		return ret;
	}

	public static double[] matVec(double[][]A,double[]x)
	{
		for(int i=0;i<A.length;i++)
		{
			if(A[i].length!=x.length)throw new RuntimeException("行数の不一致");
		}
		double[] ret=new double[A.length];
		for(int i=0;i<A.length;i++)
		{
			ret[i]=0;
			for(int j=0;j<A[i].length;j++)
			{
				ret[i]+=x[j]*A[i][j];
			}
		}
		return ret;
	}

	public static double[] residual(double[][] A,double[]x,double[]b)
	{
		return subVec(matVec(A,x),b);
	}

	public static double[][] addMat(double[][]A,double[][]B)
	{
		if(A.length!=B.length)throw new RuntimeException("行数の不一致");
		for(int i=0;i<A.length;i++)
		{
			if(A[i].length!=B[i].length)throw new RuntimeException("列の不一致");
		}
		double[][] ret=new double[A.length][];
		for(int i=0;i<A.length;i++)
		{
			ret[i]=new double[A[i].length];
		}
		for(int i=0;i<A.length;i++)
		{
			for(int j=0;j<A[i].length;j++)
			{
				ret[i][j]=A[i][j]+B[i][j];
			}
		}
		return ret;
	}

	public static double[][] multipleMat(double[][]A,double[][]B)
	{
		int colmunA=0;
		int colmunB=0;
		for(int i=0;i<A.length;i++)
		{
			if(colmunA!=0&&A[i].length!=colmunA)throw new RuntimeException("列の不一致");
			colmunA=A[i].length;
		}
		for(int i=0;i<B.length;i++)
		{
			if(colmunB!=0&&B[i].length!=colmunB)throw new RuntimeException("列の不一致");
			colmunB=B[i].length;
		}
		if(colmunA!=B.length)throw new RuntimeException("A,Bの乗算は定義されない。");
		double[][] ret=new double[A.length][];
		for(int i=0;i<A.length;i++)
		{
			ret[i]=new double[colmunB];
		}
		for(int i=0;i<A.length;i++)
		{
			for(int j=0;j<colmunB;j++)
			{
				double cell=0;
				for(int k=0;k<colmunA;k++)
				{
					cell+=A[i][k]*B[k][j];
				}
				ret[i][j]=cell;
			}
		}
		return ret;
	}

	public static double vecNorm1(double[] x)
	{
		double ret=0;
		for(int i=0;i<x.length;i++)
		{
			ret+=Math.abs(x[i]);
		}
		return ret;
	}

	public static double vecNorm2(double[] x)
	{
		double ret=0;
		for(int i=0;i<x.length;i++)
		{
			ret+=x[i]*x[i];
		}
		return Math.sqrt(ret);
	}

	public static double vecInf(double[] x)
	{
		double ret=Double.NEGATIVE_INFINITY;
		for(int i=0;i<x.length;i++)ret=Math.max(ret, x[i]);
		return ret;
	}

	public static double matNorm1(double[][] A)
	{
		int colmun=0;
		for(int i=0;i<A.length;i++)
		{
			if(colmun!=0&&colmun!=A[i].length)throw new RuntimeException("列の数がそろっていない!");
			colmun=A[i].length;
		}
		double ret=0;
		for(int j=0;j<colmun;j++)
		{
			double cellSum=0;
			for(int i=0;i<A.length;i++)
			{
				cellSum+=A[i][j];
			}
			ret=Math.max(cellSum, ret);
		}
		return ret;
		}

	public static double matNormInf(double[][] A)
	{
		int colmun=0;
		for(int i=0;i<A.length;i++)
		{
			if(colmun!=0&&colmun!=A[i].length)throw new RuntimeException("列の数がそろっていない!");
			colmun=A[i].length;
		}
		double ret=0;
		for(int i=0;i<A.length;i++)
		{
			double cellSum=0d;
			for(int j=0;j<colmun;j++)
			{
				cellSum+=A[i][j];
			}
			ret=Math.max(ret,cellSum);
		}
		return ret;
		}

	}



