import java.awt.image.BufferedImage;

public class Obstacle 
{
	protected BufferedImage obstacle = null;
	
	protected int direction = 0;
	
	protected int weight = 0;	
	
	protected int leftSide = 0;
	
	protected int rightSide = 0;
	
	/**
	 * Constructor of the class Vehicle.
	 * @param startingCol used to set the column where the left side is.
	 */
	public Obstacle(int leftSide)
	{
		this.leftSide = leftSide;
	}
	
	/**
	 * It gets the BufferedImage for the vehicle.
	 * @return the BufferedImage for the vehicle.
	 */
	public BufferedImage getObstacle()
	{
		return this.obstacle;
	}
	
	/**
	 * It sets the direction where the vehicle is going (1 to the right, -1 to the left).
	 * @param direction where it is going.
	 */
	public void setDirection(int direction)
	{
		this.direction = direction;
	}
	
	/**
	 * It gets the current direction of the vehicle.
	 * @return the direction of the vehicle.
	 */
	public int getDirection()
	{
		return this.direction;
	}
	
	/**
	 * It gets the energy required to move the vehicle.
	 * @return the energy required to move the vehicle.
	 */
	public int getWeight()
	{
		return this.weight;
	}
	
	/**
	 * It gets the column where the left side of the vehicle is.
	 * @return the column where the left side of the vehicle is.
	 */
	public int getLeftSide()
	{
		return this.leftSide;
	}
	
	/**
	 * It gets the column where the right side of the vehicle is.
	 * @return the column where the right side of the vehicle is.
	 */
	public int getRightSide()
	{
		return this.rightSide;
	}

	/**
	 * It changes the values of the leftSide and rightSide according to the direction.
	 * @param direction
	 */
	public void move(int direction) 
	{
		this.leftSide += direction;
		this.rightSide += direction;		
	}
	
}
