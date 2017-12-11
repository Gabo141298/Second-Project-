import java.io.IOException;

import javax.imageio.ImageIO;

public class Bus extends Vehicle
{
	public final int weight = 3;
	
	public Bus()
	{
		try
        {
           this.obstacle = ImageIO.read( this.getClass().getResource("bus.png") );
        }
        catch ( IOException exception )
        {
           System.err.println(exception);
        }
	}
}
