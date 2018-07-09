
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import triangle.Triangle;

import java.math.BigDecimal;

public class CheckGetSqure {
    Triangle triangle;
    @Test
    public void checkSqure_With_Right_Whole_Numbers()
    {
        triangle = new Triangle(3,4,5);
        Assert.assertEquals(triangle.getSquare(),6.0);
    }
    @Test
    public void checkSquare_With_Right_Fractional_Numbers()// Ошибка в самой Java 0.1*0.1*0.1 = 0.0010000000002
    {
       triangle = new Triangle(0.1,0.1,0.1);
       Assert.assertEquals(triangle.getSquare(),0.00433012701);
    }
    @DataProvider(name = "WrongValues")
    public Object[][] createData()
    {
        return new Object[][]{
                {-20.0,-3.0,-4.0},
                {0.0,3.0,4.0},
                {-3.0,4.0,5.0},
                {-3.0,-4.0,5.0},
                {-3.0,4.0,-5.0},
                {-3.0,-4.0,-5.0}

        };
    }
    @Test(dataProvider = "WrongValues")
    public void checkValues(Double a, Double b , Double c)
    {
        triangle = new Triangle(a,b,c);
        Assert.assertEquals(triangle.getSquare(),Double.NaN);
        System.out.println("NAN " + a + " " + " " + b + " " + c);
    }
    @Test
    public void check_Values_Close_To_MaxDouble()
    {
        triangle = new Triangle(10E100,10E80,10E100);
        boolean result = true;
        if (triangle.getSquare() == 0.0)
        {
            result = false;
        }
        Assert.assertTrue(result);
    }


}
