
public class LevelAdministrator 
{
	private int currentLevel = 0;
	
	public LevelAdministrator()
	{
		this(2);
	}
	
	public LevelAdministrator(int level)
	{
		this.currentLevel = level;
	}
	
	public void addLevelCount()
	{
		++currentLevel;
	}
	
	public String getCurrentLevel()
	{
		return getLevel(this.currentLevel);
	}
	
	public String getLevel(int level)
	{
		String text = "level";
		
		if(level < 10)
			text = text + "0";
		text = text + level + ".txt";
		
		return text;
	}
}
