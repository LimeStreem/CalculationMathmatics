import static org.junit.Assert.*;

import org.junit.Test;


public class CalcTest {

	private double[] X=new double[]{1d,-2d,3d};

	private double[] Y=new double[]{2d,4d,6d};

	private double[] vec1=new double[]{1,2};

	private double[] vec2=new double[]{3,4};

	private double[] vec3=new double[]{5,6};

	private double[][] mat1=new double[][]{vec1,vec2};

	private double[][] mat2=new double[][]{vec2,vec3};

	private void assertArrayEquals(double[] x,double[] y)
	{
		if(x.length!=y.length)fail("長さが違う");
		for(int i=0;i<x.length;i++)
		{
			if(x[i]!=y[i])fail("要素が違う");
		}
	}

	private void assertMatrixEquals(double[][] x,double[][] y)
	{
		if(x.length!=y.length)fail("長さが違う");
		for(int i=0;i<x.length;i++)
		{
			for(int j=0;j<x[i].length;j++)
			{
				if(x[i][j]!=y[i][j])fail("要素が違う");
			}
		}
	}

	@Test
	public void testScalarMultiple() {
		assertArrayEquals(new double[]{3d,-6d,9d},Calc.scalarMultiple(3, X));
	}

	@Test
	public void testAddVec() {
		assertArrayEquals(new double[]{3d,2d,9d},Calc.addVec(X, Y));
	}

	@Test
	public void testSubVec() {
		assertArrayEquals(new double[]{-1d,-6d,-3d},Calc.subVec(X, Y));
	}

	@Test
	public void testInnProd() {
		assertEquals(12d, Calc.innProd(X, Y),0.01d);
	}

	@Test
	public void testMatVec() {
		assertArrayEquals(new double[]{11d,25d},Calc.matVec(mat1,vec2));
	}

	@Test
	public void testResidual() {
		assertArrayEquals(new double[]{10,23},Calc.residual(mat1, vec2, vec1));
	}

	@Test
	public void testAddMat() {
		assertMatrixEquals(new double[][]{{4,6},{8,10}},Calc.addMat(mat1, mat2));
	}

	@Test
	public void testMultipleMat() {
		assertMatrixEquals(new double[][]{{13,16},{29,36}},Calc.multipleMat(mat1, mat2));
	}

	@Test
	public void testVecNorm1()
	{
		assertEquals(7d, Calc.vecNorm1(vec2),0.01d);
	}

	@Test
	public void testVecNorm2()
	{
		assertEquals(5d, Calc.vecNorm2(vec2),0.01d);
	}

	@Test
	public void testVecNormInf()
	{
		assertEquals(4d, Calc.vecInf(vec2),0.01d);
	}

	@Test
	public void testMatNorm1()
	{
		assertEquals(6d,Calc.matNorm1(mat1),0.01d);
	}

	@Test
	public void testMatNormInf()
	{
		assertEquals(7d,Calc.matNormInf(mat1),0.01d);
	}

	@Test
	public void testQuestion5()
	{
		double[] input=new double[100];
		for (int i = 0; i < input.length; i++) {
			input[i]=Math.sqrt(i+1);
		}
		System.out.println(Calc.vecNorm1(input));
		System.out.println(Calc.vecNorm2(input));
		System.out.println(Calc.vecInf(input));
	}
	@Test
	public void testQuestion6()
	{
		double[][] input=new double[100][100];
		for(int i=0;i<100;i++)
		{
			for(int j=0;j<100;j++)
			{
				input[i][j]=Math.sqrt(2*(i+1)+j+1);
			}
		}
		System.out.println(Calc.matNorm1(input));
		System.out.println(Calc.matNormInf(input));
	}
}
