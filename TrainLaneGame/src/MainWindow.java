import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 * Used as the main window for the Train Lane Game.
 * It sets the size of the window and its components, like the design of the level, the current level
 * and the energy available to the player after any quantity of moves.
 */
public class MainWindow extends JFrame implements ActionListener
{
	private Timer elapsedTime = null;
	private long elapsedSeconds = -1;
	private JLabel labelLevel = null;
	private JLabel labelEnergy = null;
	private ObstacleBoard obstacleBoard = null;
	
	/**
	 * Constructor for the MainWindow class. It sets the title of the window as "Train Lane Game".
	 * The size of the window, the obstacle board and the game indicators are also created here.
	 */
	public MainWindow()
	{
		super("Train Lane Game");
		this.setSize(640, 480);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BorderLayout mainLayout = new BorderLayout();
		this.setLayout( mainLayout );
		
		this.createObstacleBoard();
		this.createGameIndicators();
		
		this.elapsedTime = new Timer(1000, this);
	    this.elapsedTime.start();
	}

	/**
	 * It creates the obstacle board (the level).
	 */
	private void createObstacleBoard()
	{
		obstacleBoard = new ObstacleBoard();
		this.add(obstacleBoard, BorderLayout.CENTER);
	}
	
	/**
	 * It creates the game indicators: the current level the player is in and the energy the player has
	 * available after the moves he/she has done.
	 */
	private void createGameIndicators()
	{
		JPanel indicators = new JPanel();
		
		indicators.setLayout( new BorderLayout() );
		
		Font font = new Font("Sans", Font.BOLD, 20);
		
		this.labelLevel = new JLabel("Level: 1");
		labelLevel.setFont(font);
		indicators.add(labelLevel, BorderLayout.WEST);
		
		labelEnergy = new JLabel("Energy: 0");
		labelEnergy.setFont(font);
		indicators.add(labelEnergy, BorderLayout.EAST);
		
		this.add(indicators, BorderLayout.SOUTH);
	}
	/**
	 * Causes the window to repaint if an action is being performed
	 */
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if ( event.getSource() == this.elapsedTime )
		{
			this.updateElapsedTime();
		}
		
		if(this.obstacleBoard.levelHasBeenBeaten())
		{
			if(!obstacleBoard.hasFinishedAnimation()&& !obstacleBoard.isDoingTrainLaneAnimation())
				this.obstacleBoard.doWinAnimation();
			else
			{
				Object[] options = { "Play next level", "Play again"};
				int option = JOptionPane.showOptionDialog(null, "Congratulations on beating the level", "Congratulations", 
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				if(option == 0)
				{
					this.obstacleBoard.nextLevel();
				}
				this.obstacleBoard.loadLevel();
				this.elapsedSeconds = -1;
				this.elapsedTime.restart();
			}
			
		}
	}
	/**
	 * Shows the elapsed time on the in game label
	 */
	private void updateElapsedTime()
	{
		++this.elapsedSeconds;
		long minutes = this.elapsedSeconds / 60;
		long seconds = this.elapsedSeconds % 60;		
		int level = this.obstacleBoard.getCurrentLevel();
		String text = String.format("Level: %d. Time %02d:%02d", level , minutes, seconds);
		this.labelLevel.setText(text);
		text = String.format("Energy: %02d", obstacleBoard.getEnergySpent());
		this.labelEnergy.setText(text);
	}
}
