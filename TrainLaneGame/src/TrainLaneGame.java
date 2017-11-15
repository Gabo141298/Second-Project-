/**
 *
 */
public class TrainLaneGame
{

	private MainWindow mainWindow = null;
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		TrainLaneGame trainLaneGame = new TrainLaneGame();
		trainLaneGame.run();
	}
	
	public void run()
	{
		this.mainWindow = new MainWindow();
		this.mainWindow.setVisible(true);
	}

}
