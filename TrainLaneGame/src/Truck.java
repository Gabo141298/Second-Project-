import java.io.IOException;

import javax.imageio.ImageIO;

public class Truck extends Vehicle
{
	
	public Truck(int startingCol)
	{
		super (startingCol);
		try
        {
           this.obstacle = ImageIO.read( this.getClass().getResource("ambulance.png") );
        }
        catch ( IOException exception )
        {
           System.err.println(exception);
        }
		this.weight = 2;
		this.endingCol = this.startingCol + this.weight-1;
	}
}
