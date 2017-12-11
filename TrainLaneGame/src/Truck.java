import java.io.IOException;

import javax.imageio.ImageIO;

public class Truck extends Vehicle
{
	public final int weight = 2;
	
	public Truck()
	{
		try
        {
           this.obstacle = ImageIO.read( this.getClass().getResource("ambulance.png") );
        }
        catch ( IOException exception )
        {
           System.err.println(exception);
        }
	}
}
