import java.io.IOException;

import javax.imageio.ImageIO;

public class Truck extends Vehicle
{
	/**
	 * It calls the superclass constructor and sets the obstacle.
	 * @param leftSide used to set the leftSide form the superclass constructor.
	 */
	public Truck(int leftSide)
	{
		super (leftSide);
		try
        {
           this.obstacle = ImageIO.read( this.getClass().getResource("ambulance.png") );
        }
        catch ( IOException exception )
        {
           System.err.println(exception);
        }
		this.weight = 2;
		this.rightSide = this.leftSide + this.weight-1;
	}
}
