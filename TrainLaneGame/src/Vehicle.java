import java.awt.image.BufferedImage;

public class Vehicle 
{
	protected BufferedImage obstacle = null;
	
	protected int direction = 0;
	
	public final int weight = 0;
	
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
}
