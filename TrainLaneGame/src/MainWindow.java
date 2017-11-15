import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author jeisson.hidalgo@ecci.ucr.ac.cr
 *
 */
public class MainWindow extends JFrame
{

	public MainWindow()
	{
		super("Train Lane Game");
		this.setSize(640, 480);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BorderLayout mainLayout = new BorderLayout();
		this.setLayout( mainLayout );
		
		this.createObstacleBoard();
		this.createGameIndicators();
	}

	private void createObstacleBoard()
	{
		ObstacleBoard obstacleBoard = new ObstacleBoard();
		this.add(obstacleBoard, BorderLayout.CENTER);
	}
	
	private void createGameIndicators()
	{
		JPanel indicators = new JPanel();
		
		indicators.setLayout( new BorderLayout() );
		
		Font font = new Font("Sans", Font.BOLD, 20);
		
		JLabel labelLevel = new JLabel("Level: 1");
		labelLevel.setFont(font);
		indicators.add(labelLevel, BorderLayout.WEST);
		
		JLabel labelEnergy = new JLabel("Energy: 0");
		labelEnergy.setFont(font);
		indicators.add(labelEnergy, BorderLayout.EAST);
		
		this.add(indicators, BorderLayout.SOUTH);
	}

}
