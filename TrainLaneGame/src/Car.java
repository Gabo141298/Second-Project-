import java.io.IOException;

import javax.imageio.ImageIO;

public class Car extends Vehicle
{
	public final int weight = 1;
	
	public Car()
	{
		try
        {
           this.obstacle = ImageIO.read( this.getClass().getResource("car.png") );
        }
        catch ( IOException exception )
        {
           System.err.println(exception);
        }
	}
}
