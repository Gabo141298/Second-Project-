import java.io.IOException;

import javax.imageio.ImageIO;

public class Car extends Vehicle
{		
	
	/**
	 * It calls the superclass constructor and sets the obstacle.
	 * @param leftSide used to set the leftSide form the superclass constructor.
	 */
	public Car(int leftSide)
	{
		super (leftSide);
		try
        {
           this.obstacle = ImageIO.read( this.getClass().getResource("car.png") );
        }
        catch ( IOException exception )
        {
           System.err.println(exception);
        }
		this.weight = 1;
		this.rightSide = this.leftSide + this.weight - 1;
	}
}
