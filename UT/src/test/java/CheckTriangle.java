import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import triangle.Triangle;

import java.io.IOException;
import java.util.Random;

//Производим тестирование метода checkTriangle() класса Triangle
public class CheckTriangle {
    Triangle triangle;
    @DataProvider(name = "WrongValues")
    public java.lang.Object[] createData()
    {
        return new Object[][]
                {
                        {0.0,0.0,0.0},//Нулевые одно , два или все значения;
                        {0.0,1.0,2.0},
                        {1.0,0.0,2.0},
                        {1.0,2.0,0.0},
                        {0.0,0.0,1.0},
                        {0.0,1.0,0.0},
                        {1.0,0.0,0.0},
                        {-2.2,-3.4,-4.5},//Отриццательные одно , два или все значения;
                        {-2.1,3.3,4.2},
                        {2.2,-3.4,4.6},
                        {2.7,3.2,-4.8},
                        {-2.1,-3.3,4.2},
                        {2.2,-3.4,-4.6},
                        {-2.7,3.2,-4.8},
                        {3.3,1.2,5.8},//Значения , где а > b + c , b > a + c , c > a + b;
                        {3.2,5.5,1.7},
                        {3.3,1.2,2.0},
                };
    }

    @DataProvider(name = "BigValues")
    public Object[][] createBigValues()
    {
        return new Object[][]
                {
                        {999E300,999E300,999E300},
                        {999E-300,999E-300,999E-300},
                        {999E300, 999E300,10E270},
                };
    }

    @DataProvider(name = "MAX_AND_MIN")
    public Object[][] createData2()
    {
        return new Object[][]
                {
                        {Double.MAX_VALUE*10,Double.MAX_VALUE*10,Double.MAX_VALUE*10},
                        {Double.MAX_VALUE*10,Double.MAX_VALUE*10,Double.MAX_VALUE},
                        {Double.MIN_VALUE,Double.MIN_VALUE,Double.MIN_VALUE/10}
                };
    }

    @Test(dataProvider = "BigValues")
    public void checkTriangle_With_Big_Values(Double a , Double b , Double c)
    {
        triangle = new Triangle(a,b,c);
        Assert.assertTrue(triangle.checkTriangle());
        System.out.println("BigValues " + a + " " + b + " " + " " + c);
    }

    @Test(dataProvider = "WrongValues")
    public void checkWrongTriangle(Double a, Double b,Double c)
    {
        Assert.assertFalse(new Triangle(a,b,c).checkTriangle());
        System.out.println("WrongValues " +a + " " + b + " " + " " + c);
    }

    @Test(dataProvider = "MAX_AND_MIN" , expectedExceptions = {Exception.class})
    public void checkValue_Beyound_Double(Double a , Double b , Double c)
    {
        triangle = new Triangle(a,b,c);
        System.out.println(triangle.checkTriangle());
    }

    @Test
    public void checkRightTriangle()
    {
        triangle = new Triangle(10.1,12.1,14.1);
        Assert.assertTrue(triangle.checkTriangle());
    }

    @Test public void checkValue_A_Negative()
    {
        triangle = new Triangle(-4,5,6);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(),"a<=0");
    }

    @Test public void checkValue_A_Zero()
    {
        triangle = new Triangle(0,5,6);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(),"a<=0");
    }

    @Test public void checkValue_B_Negative()
    {
        triangle = new Triangle(2,-3,5);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(),"b<=0");
    }
    @Test public void checkValue_B_Zero()
    {
        triangle = new Triangle(2,0,5);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(),"b<=0");
    }

    @Test
    public void checkValue_C_Negative()//Ошибка.Неверное сообщение об ошибке
    {
        triangle = new Triangle(2,3,-5);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(),"c<=0");
    }

    @Test
    public void checkValue_C_Zero()//Ошибка.Неверное сообщение об ошибке
    {
        triangle = new Triangle(2,3,0);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(),"c<=0");
    }

    @Test
    public void checkValue_A_More_Than_B_Plus_C()
    {
        triangle = new Triangle(10.4,4.3,3.7);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(),"b+c<=a");
    }

    @Test
    public void checkValue_A_Equaly_Sum_B_Plus_C()
    {
        triangle = new Triangle(10,6.3,3.7);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(),"b+c<=a");
    }

    @Test
    public void checkValue_B_More_Than_A_Plus_C()
    {
        triangle = new Triangle(5.7,23.5,14.3);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(),"a+c<=b");
    }

    @Test
    public void checkValue_B_Equaly_Sum_A_Plus_C()
    {
        triangle = new Triangle(9.2,23.5,14.3);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(),"a+c<=b");
    }

    @Test
    public void checkValue_C_More_Than_A_Plus_B()
    {
        triangle = new Triangle(34.2,65.6,123.7);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(),"a+b<=c");
    }

    @Test
    public void checkValue_C_Equaly_Sum_A_Plus_B()
    {
        triangle = new Triangle(58.1,65.6,123.7);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(),"a+b<=c");
    }


}
