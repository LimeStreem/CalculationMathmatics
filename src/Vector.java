import VectorGenerater.IVectorGenerater;

/**
 * Created by LimeStreem on 2014/10/27.
 */
public class Vector {
    private double[] elements;
    private int size;

    public double[] getElements() {
        return elements;
    }

    public void setElements(double[] elements) {
        this.elements = elements;
        this.size=elements.length;
    }

    public int getSize() {
        return size;
    }

    public Vector(double[] vec)
    {
        setElements(vec.clone());
    }

    public Vector(int size)
    {
        setElements(new double[size]);
    }

    public Vector(IVectorGenerater gen)
    {
        this(gen.size());
        for (int i = 0; i < size; i++) {
            elements[i]=gen.getAt(i);
        }
    }

    public String toString()
    {
        return Calc.stringfyVec(elements);
    }

    public Vector AddWith(Vector vec)
    {
        return new Vector(Calc.addVec(this.elements,vec.elements));
    }

    public Vector SubtractWith(Vector vec)
    {
        return new Vector(Calc.subVec(this.elements,vec.elements));
    }

    public double OneNorm()
    {
        return Calc.vecNorm1(this.elements);
    }

    public double TwoNorm()
    {
        return Calc.vecNorm2((this.elements));
    }

    public double InfNorm()
    {
        return Calc.vecInf(this.elements);
    }

    public Vector Duplicate()
    {
        return new Vector(elements);
    }

    public Vector Exchange(int k,int l)
    {
        Vector result=Duplicate();
        double ke=result.elements[k];
        result.elements[k]=result.elements[l];
        result.elements[l]=ke;
        return result;
    }

    public Vector Multiply(double d)
    {
        Vector result=Duplicate();
        for (int i = 0; i < size; i++) {
            result.elements[i]*=d;
        }
        return result;
    }
}
