import java.awt.image.BufferedImage;

public class Vehicle 
{
	protected BufferedImage obstacle = null;
	
	protected int direction = 0;
	
	protected int weight = 0;
	
	protected boolean hasBeenpainted = false;
	
	protected boolean isMoving = false;
	
	protected int startingCol = 0;
	
	protected int endingCol = 0;
	
	public Vehicle(int startingCol)
	{
		this.startingCol = startingCol;
		this.endingCol = this.startingCol + this.weight-1;
	}
	
	/**
	 * 
	 * @return
	 */
	public BufferedImage getObstacle()
	{
		return this.obstacle;
	}
	
	/**
	 * 
	 * @param direction
	 */
	public void setDirection(int direction)
	{
		this.direction = direction;
	}
	
	public int getDirection()
	{
		return this.direction;
	}
	public int getWeight()
	{
		return this.weight;
	}
	public int getStartingCol()
	{
		return this.startingCol;
	}
	
	public int getEndingCol()
	{
		return this.endingCol;
	}
	
	public boolean getMovement()
	{
		return this.isMoving;
	}
	
	public void setMovement(boolean movement)
	{
		this.isMoving = movement;
	}
}
