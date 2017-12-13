import java.io.IOException;

import javax.imageio.ImageIO;

public class Bus extends Obstacle
{	
	/**
	 * It calls the superclass constructor and sets the obstacle.
	 * @param leftSide used to set the leftSide form the superclass constructor.
	 */
	public Bus(int leftSide)
	{
		super (leftSide);
		try
        {
           this.obstacle = ImageIO.read( this.getClass().getResource("bus.png") );
        }
        catch ( IOException exception )
        {
           System.err.println(exception);
        }
		this.weight = 3;
		this.rightSide = this.leftSide + this.weight - 1;
	}
}
