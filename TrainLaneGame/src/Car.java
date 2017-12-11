import java.io.IOException;

import javax.imageio.ImageIO;

public class Car extends Vehicle
{		
	public Car(int startingCol)
	{
		super (startingCol);
		try
        {
           this.obstacle = ImageIO.read( this.getClass().getResource("car.png") );
        }
        catch ( IOException exception )
        {
           System.err.println(exception);
        }
		this.weight = 1;
	}
}
