/**
 * Controller for the Train Lane Game.
 */
public class TrainLaneGame
{

	/**
	 * Used as the main window in the game.
	 * It will display the level that the player is in, the level number and the energy that the player 
	 * has available.
	 */
	private MainWindow mainWindow = null;
	
	/**
	 * It starts running the Train Lane Game
	 * @param args
	 */
	public static void main(String[] args)
	{
		TrainLaneGame trainLaneGame = new TrainLaneGame();
		trainLaneGame.run();
	}
	
	/**
	 * It runs the Train Lane Game.
	 * It creates the main window and will set it visible.
	 */
	public void run()
	{
		this.mainWindow = new MainWindow();
		this.mainWindow.setVisible(true);
	}

}
