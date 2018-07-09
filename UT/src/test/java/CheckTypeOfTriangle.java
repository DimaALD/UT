import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import triangle.Triangle;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckTypeOfTriangle {
    Triangle triangle;
    @DataProvider(name = "DATA_FOR_EQUILATERAL")
    public Object[][] createData0()
    {
        return new Object[][]
                {
                        {4.2,4.2,4.2},
                        {100.0,100.0,100.0}
                };
    }
    @Test(dataProvider = "DATA_FOR_EQUILATERAL")
    public void checkType_EQUILATERAL(Double a , Double b , Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), 3);
        System.out.println("checkType_EQUILATERAL : "+a + " " + b + " " + " " + c);
    }

    @DataProvider(name = "DATA_FOR_ISOSCELES")
    public Object[][] createData() {
        return new Object[][]
                {
                        {4.0, 4.0, 2.0},
                        {5.0, 2.0, 5.0},
                        {3.2, 5.1, 5.1},
                };
    }

    @Test(dataProvider = "DATA_FOR_ISOSCELES")
    public void checkType_ISOSCELES(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), 2);
        System.out.println("checkType_ISOSCELES: "+a + " " + b + " " + " " + c);
    }

    @DataProvider(name = "DATA_FOR_RECTANGULAR")
    public Object[][] createData1() {
        return new Object[][]
                {
                        {3.0, 4.0, 5.0},
                        {10.0, 6.0, 8.0},
                        {6.0, 10.0, 8.0},
                };
    }

    @Test(dataProvider = "DATA_FOR_RECTANGULAR")
    public void checkType_RECTANGULAR(Double a , Double b , Double c) {//BUG
    triangle = new Triangle(a,b,c);
    Assert.assertEquals(triangle.detectTriangle(),8);
    System.out.println("checkType_RECTANGULAR: "+ a + " " + b + " " + " " + c);
    }

    @Test
    public void checkType_ORDINARY()
    {
        triangle = new Triangle(3.2 , 5.4 , 7.6);
        Assert.assertEquals(triangle.detectTriangle(),4);
    }

    @Test
    public void checkType_ISOSCELES_AND_RECTANGULAR()//BUG
    {
        triangle = new Triangle(Math.sqrt(32),Math.sqrt(32),8.0);
        System.out.println(Math.pow(Math.sqrt(32),2));
        Assert.assertEquals(triangle.detectTriangle(),10);
    }
    @DataProvider(name = "Data")
    public Object[][] create()
    {
        return new Object[][]
                {
                        {"Равносторонний",new ArrayList<Double>(Arrays.asList(Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE))},
                        {"Равносторонний",new ArrayList<Double>(Arrays.asList(Double.MIN_VALUE,Double.MIN_VALUE,Double.MIN_VALUE))},
                        {"Равносторонний",new ArrayList<Double>(Arrays.asList(10E300,10E300,10E300))},
                        {"Равнобедренный", new ArrayList<Double>(Arrays.asList(Double.MAX_VALUE,Double.MAX_VALUE,10E200))},
                        {"Равнобедренный", new ArrayList<Double>(Arrays.asList(20E30,20E30,10E20))},
                        {"Равнобедренный", new ArrayList<Double>(Arrays.asList(Double.MIN_VALUE*10,Double.MIN_VALUE*10,Double.MIN_VALUE))},
                        {"Обычный",new ArrayList<Double>(Arrays.asList(10E300,15E300,6E300))},
                        {"Обычный",new ArrayList<Double>(Arrays.asList(10E-300,15E-300,6E-300))},
                };
    }
    @Test(dataProvider = "Data")
    public void checkValues_Beyound_Double(String type , ArrayList<Double> list)
    {
        if (type.equals("Равносторонний"))
        {
            ArrayList<Double> tr_EQ = list;
            triangle = new Triangle(tr_EQ.get(0),tr_EQ.get(1),tr_EQ.get(2));
            System.out.println(type + " " + tr_EQ.get(0)+ " "+ tr_EQ.get(1) + " " +tr_EQ.get(2) );
            Assert.assertEquals(triangle.detectTriangle(),3);
        }
        else if (type.equals("Равнобедренный"))
        {
            ArrayList<Double> tr_IS = list;
            triangle = new Triangle(tr_IS.get(0),tr_IS.get(1),tr_IS.get(2));
            System.out.println(type + " " + tr_IS.get(0)+ " "+ tr_IS.get(1) + " " +tr_IS.get(2) );
            Assert.assertEquals(triangle.detectTriangle(),2);
        }
        else if (type.equals("Обычный"))
        {
            ArrayList<Double> tr_ORD = list;
            triangle = new Triangle(tr_ORD.get(0),tr_ORD.get(1),tr_ORD.get(2));
            Assert.assertEquals(triangle.detectTriangle(),4);
        }
    }
    @DataProvider(name = "MAX_AND_MIN")
    public Object[][] createBIG_VALUES()
    {
        return new Object[][]
                {
                        {"Равносторонний",new ArrayList<Double>(Arrays.asList(Double.MAX_VALUE*10E100,Double.MAX_VALUE*10E100,Double.MAX_VALUE*10E100))},
                        {"Равносторонний",new ArrayList<Double>(Arrays.asList(Double.MIN_VALUE/10E100,Double.MIN_VALUE/10E100,Double.MIN_VALUE/10E100))},
                        {"Равнобедренный", new ArrayList<Double>(Arrays.asList(Double.MAX_VALUE*10E100,Double.MAX_VALUE*10E100,10E200))},
                        {"Обычный",new ArrayList<Double>(Arrays.asList(10E300,15E300,8E300))},
                        {"Обычный",new ArrayList<Double>(Arrays.asList(10E-300,15E-300,8E-300))},
                };
    }
    @Test(dataProvider = "Data",expectedExceptions = Exception.class)
    public void checkValues_Double(String type , ArrayList<Double> list)
    {
        if (type.equals("Равносторонний"))
        {
            ArrayList<Double> tr_EQ = list;
            triangle = new Triangle(tr_EQ.get(0),tr_EQ.get(1),tr_EQ.get(2));
        }
        else if (type.equals("Равнобедренный"))
        {
            ArrayList<Double> tr_IS = list;
            triangle = new Triangle(tr_IS.get(0),tr_IS.get(1),tr_IS.get(2));
        }
        else if (type.equals("Обычный"))
        {
            ArrayList<Double> tr_ORD = list;
            triangle = new Triangle(tr_ORD.get(0),tr_ORD.get(1),tr_ORD.get(2));
        }
    }

}