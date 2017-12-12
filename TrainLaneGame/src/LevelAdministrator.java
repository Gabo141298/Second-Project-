
public class LevelAdministrator 
{
	private int currentLevel = 0;
	
	private final int TOTAL_LEVELS = 2;
	
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
	}
	
	/**
	 * It adds to the current level count to get to the next level.
	 */
	public void addLevelCount()
	{
		if(++currentLevel > TOTAL_LEVELS)
			currentLevel = 1;
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
}
