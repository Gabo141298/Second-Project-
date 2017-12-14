import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.util.Formatter;
import java.util.Scanner;

public class LevelAdministrator 
{
	private int currentLevel = 0;
	
	private final int TOTAL_LEVELS = 10;
	
	private int[] levelStars = new int[TOTAL_LEVELS+1];
	
	private File levelStatusFile = null;
	
	private Scanner levelRecord = null;
	
	private ObstacleMatrix obstacleMatrix = null;
	
	private PrintStream output = null;
	
	/**
	 * Constructor of LevelAdministrator.
	 * It calls the constructor that sets the current level by sending as a parameter level 1.
	 */
	public LevelAdministrator()
	{		
		this(1);
	}

	/**
	 * Constructor of LevelAdministrator.
	 * It sets the current level according to the parameter.
	 * @param level used to set the current level.
	 */
	public LevelAdministrator(int level)
	{
		this.currentLevel = level;
		this.loadLevelStatus();
		this.levelRecord.useDelimiter("");
		for (int readingLevel = 1; readingLevel< levelStars.length; ++readingLevel)
		{
			levelStars[readingLevel] = this.levelRecord.nextInt();
		}
	}
	
	private void loadLevelStatus() 
	{
		try
		{
			this.levelStatusFile = new File( this.getClass().getResource("levelStatusFile.txt").toURI() );
			this.levelRecord = new Scanner(levelStatusFile);
		}
		catch(FileNotFoundException exception)
		{
			System.err.println(exception);
		} 
		catch (URISyntaxException exception) 
		{
			System.err.println(exception);
		}		
	}
	
	/**
	 * Adds the reference to the obstacle Matrix as a class attribute.
	 * @param obstacleMatrix
	 */
	public void addObstacleMatrix(ObstacleMatrix obstacleMatrix) 
	{
		this.obstacleMatrix = obstacleMatrix;
	}
	/**
	 * It adds to the current level count to get to the next level.
	 */
	public void addLevelCount()
	{
		checkStars();
		if(++currentLevel > TOTAL_LEVELS)
			currentLevel = 1;
		
	}
	
	public void setLevel(int newLevel)
	{
		checkStars();
		this.currentLevel = newLevel;
	}
	
	private void checkStars() 
	{
		if (obstacleMatrix.getStarsObtained()> this.levelStars[currentLevel])
		{
			this.levelStars[currentLevel] = obstacleMatrix.getStarsObtained();
					
			try 
			{
				Formatter levelResults = new Formatter(levelStatusFile);
				for(int index = 1; index < levelStars.length; ++index)
				{
					levelResults.format("%d%n", levelStars[index]);
				}
				levelResults.close();
			} 
			catch (IOException exception) 
			{
				System.out.println("File couldn't be cleared");
			}
			
		}
	}

	/**
	 * It gets the number of the current level.
	 * @return the number of the current level.
	 */
	public int getLevelCount()
	{
		return this.currentLevel;
	}
	
	/**
	 * It calls the method getLevel() to return how the file with the level is called.
	 * @return the file with the level is called.
	 */
	public String getCurrentLevel()
	{
		return getLevel(this.currentLevel);
	}
	
	/**
	 * It gets how the file with the level is called according to the parameter.
	 * @param level used to load a specific level.
	 * @return how the file with the level is called.
	 */
	public String getLevel(int level)
	{
		String text = "level";
		
		if(level < 10)
			text = text + "0";
		text = text + level + ".txt";
		
		return text;
	}

	public String[] getLevelArray() 
	{
		String[] levelArray = new String [this.TOTAL_LEVELS +1];
		for (int count = 1; count< levelArray.length; ++count)
		{
			levelArray[count] = String.format("%d", count);
		}
		return levelArray;
	}

	public int getCurrentStars() 
	{
		checkStars();
		return this.levelStars[currentLevel];
	}
	
	public int getFileStars()
	{
		return this.levelStars[currentLevel];
	}

}
