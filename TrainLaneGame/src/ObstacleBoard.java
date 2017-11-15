import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Used to set the components of the obstacle board, like the lines to limit the spaces for the vehicles
 * in the game.
 */
public class ObstacleBoard extends JPanel
{
	// Override paintComponent to perform your own painting
	/**
	 * It sets the background to white and draws the lines, both horizontally and vertically to limit the 
	 * spaces for the level.
	 * @param graphics used to change how the obstacle board looks.
	 */
	@Override public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);     // paint parent's background
		setBackground(Color.WHITE);  // set background color for this JPanel
		drawHorizontalLines(graphics);
		drawVerticalLines(graphics);

	}
	
	/**
	 * Draws the horizontal lines from the obstacle board.
	 * @param graphics used to draw the lines
	 */
	private void drawHorizontalLines(Graphics graphics)
	{
		graphics.setColor(Color.BLACK);    // set the drawing color
		graphics.drawLine(0, 5,640, 5);
		graphics.drawLine(0, 96,640, 96);
		graphics.drawLine(0, 192,640, 192);
		graphics.drawLine(0, 288,640, 288);
		graphics.drawLine(0, 384,640, 384);
	}
	
	/**
	 * Draws the vertical lines from the obstacle board.
	 * @param graphics used to draw the lines.
	 */
	private void drawVerticalLines(Graphics graphics)
	{
		graphics.drawLine(0, 5, 0, 384);
		graphics.drawLine(80, 5, 80, 384);
		graphics.drawLine(160, 5, 160, 384);
		graphics.drawLine(240, 5, 240, 384);
		graphics.drawLine(320, 5, 320, 384);
		graphics.drawLine(400, 5, 400, 384);
		graphics.drawLine(480, 5, 480, 384);
		graphics.drawLine(560, 5, 560, 384);
		graphics.drawLine(640, 5, 640, 384);
	}
	
}
