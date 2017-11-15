import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 */
public class ObstacleBoard extends JPanel
{
	// Override paintComponent to perform your own painting
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);     // paint parent's background
		setBackground(Color.WHITE);  // set background color for this JPanel
		drawHorizontalLines(g);
		drawVerticalLines(g);

	}
	private void drawHorizontalLines(Graphics g)
	{
		g.setColor(Color.BLACK);    // set the drawing color
		g.drawLine(0, 5,640, 5);
		g.drawLine(0, 96,640, 96);
		g.drawLine(0, 192,640, 192);
		g.drawLine(0, 288,640, 288);
		g.drawLine(0, 384,640, 384);
	}
	private void drawVerticalLines(Graphics g)
	{
		g.drawLine(0, 5, 0, 384);
		g.drawLine(80, 5, 80, 384);
		g.drawLine(160, 5, 160, 384);
		g.drawLine(240, 5, 240, 384);
		g.drawLine(320, 5, 320, 384);
		g.drawLine(400, 5, 400, 384);
		g.drawLine(480, 5, 480, 384);
	}
	
}
