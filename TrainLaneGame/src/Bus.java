import java.io.IOException;

import javax.imageio.ImageIO;

public class Bus extends Vehicle
{	
	public Bus(int startingCol)
	{
		super (startingCol);
		try
        {
           this.obstacle = ImageIO.read( this.getClass().getResource("bus.png") );
        }
        catch ( IOException exception )
        {
           System.err.println(exception);
        }
		this.weight = 3;
	}
}
