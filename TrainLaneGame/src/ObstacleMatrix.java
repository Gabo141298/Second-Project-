import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ObstacleMatrix 
{
	private File level = null;
	
	private Scanner input = null;
	/**     
	 * Used to save the matrix data given from the input.     
	 */    
	private int currentGame[][] = null;   
	
	private Vehicle vehicles[][] = null;
	
	/**     
	 * Used to save the minimum number of plays needed to clear the column     
	 * for the train for each column.    
	 */    
	private int playsPerColumn[] = null;
	
	public ObstacleMatrix(String levelFilename)
	{
		try
		{
			this.level = new File( this.getClass().getResource(levelFilename).toURI() );
			this.input = new Scanner(level);
		}
		catch(FileNotFoundException exception)
		{
			System.err.println(exception);
		} 
		catch (URISyntaxException exception) 
		{
			// TODO Auto-generated catch block
			System.err.println(exception);
		}
	}
	/**
	 * This method starts the game logic.
	 * Creates the game matrix and checks if it's a valid matrix
	 */
	public void run()   
	{    
		// Take the values from the standard input to give currentGame and playsPerColumn their dimensions.        
		int rows = input.nextInt();        
		int columns = input.nextInt();                
		// Give currentGame and playsPerColumn their dimensions.      
		this.vehicles = new Vehicle[rows][columns];
		currentGame = new int[rows][columns];        
		playsPerColumn = new int[columns]; input.nextLine(); input.nextLine();                
		// Reads and assigns the values from the standard input to the matrix.        
		readGame();                
		// Check if the level of the game is actually playable. if it is, it will get the minimum amount of energy needed.        
		if(checkRows())
		{
			 checkPlaysInColumns();  
		}
	}
	
	 /**     
	  * It uses the standard input to assign the values for currentGame.     
	  */   
	public void readGame()    
	{        
		for(int row = 0; row < currentGame.length; ++row)        
		{            
			for(int column = 0; column < currentGame[row].length; ++column)           
			{                
				currentGame[row][column] = input.nextInt();
			}
			
			for(int column = 0; column < vehicles[row].length; ++column)
			{
				if(currentGame[row][column] == 1)
					vehicles[row][column] = new Car(column);
				else if(currentGame[row][column] == 2)
					vehicles[row][column] = vehicles[row][++column] = new Truck(column);
				else if(currentGame[row][column] == 3)
					vehicles[row][column] = vehicles[row][++column] = vehicles[row][++column] = new Bus(column);
				else
					vehicles[row][column] = null;
			}
		}
	}
	
	/**     
	 * It checks if every row has a zero, to know if the level is beatable.     
	 * @return true if the level is beatable and false if it's not.     
	 * If the level is not beatable, it will print the rows that make the level fail.     
	 */    
	public boolean checkRows()    
	{        
		// Create booleans to know if the level and the row are valid.       
		boolean validGame = true;        
		boolean validRow = false;        
		for(int row = 0; row < currentGame.length && !validRow; ++row)        
		{            
			for(int column = 0; column < currentGame[row].length; ++column)           
			{               
				// Checks if there's a zero in the row, to check if the level works.                
				if(currentGame[row][column] == 0)               
				{                    
					validRow = true;               
				}           
			}            
			// If the row's not valid, it prints the row that failed and sets validGame to false.  
			if(!validRow)           
			{                
				// Sets the level to false because the level can't be played.                
				validGame = false;               
				System.out.printf("Invalid row %d%n", row + 1);            
			}            validRow = false;        
		}        
		return validGame;    
	}        
	/**     
	 * It assigns to playsPerColumn the minimum amount of moves needed so the position may be zero.    
	 * It checks for the left and the right side of the position.     
	 */    
	public void checkPlaysInColumns()    
	{        
		// Creates variables to store the energy values, two for each side.        
		int leftPlays = 0;        
		int rightPlays = 0;        
		for(int column = 0; column < currentGame[0].length; ++column)        
		{
			for(int row = 0; row < currentGame.length; ++row)
			{
				// It's only necessary if the position is 1, because if it is 0, you don't have to move any car.                
				if(currentGame[row][column] == 1)               
				{                    
					// Checks if you can move to the left.                    
					if(column > 0)                    
					{                        
						leftPlays = getRequiredPlays(row, column, -1);                    
					}                    
					else                    
					{                        
						leftPlays = Integer.MAX_VALUE;                    
					}                    
					// Checks if you can move to the right.                    
					if(column + 1 < currentGame[row].length)                    
					{                        
						rightPlays = getRequiredPlays(row, column, 1);                    
					}                    
					else                   
					{                        
						rightPlays = Integer.MAX_VALUE;                    
					}                    
					// Adds the least moves needed to playsPerColumn.                    
					playsPerColumn[column] += Math.min(leftPlays, rightPlays);            
				} 
			}
		}
	}

	/**     
	 * Used to get the amount of moves needed to do on the side given so the position given by the      
	 * row and the column may be zero.     
	 * @param row used to set the position in currentGame.     
	 * @param column used to set the position in currentGame.     
	 * @param direction used to change the value of the column when checking to the right or left. It receives     
	 * a negative number to move to the left and a positive number to move to the right.     
	 * @return the number of moves needed to be done so the position is zero.     
	 */   
	public int getRequiredPlays(int row, int column, int direction)    
	{        
		// Create variables, one to know if cars can be moved and the second one to return the moves needed to be made.        
		boolean foundZero = false;        
		int energySpent = 0;        
		// If the column equals zero or currentGame.length - 1, it will still check towards the expected direction.     
		column += direction;       
		// Checks towards the direction given to sum to the energySpent and find a zero to know if cars can be moved.       
		while(column >= 0 && column < currentGame[row].length && !foundZero)        
		{            
			// Sums an extra move until the zero has been found.           
			++energySpent;       
			// Checks if there's a zero.          
			if(currentGame[row][column] == 0)            
			{                
				foundZero = true;      
			}            
			// Goes towards the direction given.             
			column += direction;        
		}        
		// If there was a zero, cars could be moved, so it returns the moves needed so the position is zero.        
		if(foundZero)        
		{           
			return energySpent;        
		}        
		// Else, it returns the maximum value possible. 
		else
		{
			return Integer.MAX_VALUE;
		}
	}
	
	/**
	 * It checks in the array which is the least energy needed to beat the level.     
	 * @return the least amount of energy needed to beat the level.     
	 */    
	public int getMinimumEnergyConsumed()    
	{        
		int minimumPlays = Integer.MAX_VALUE;        
		for(int index = 0; index < playsPerColumn.length; ++index)        
		{           
			// It compares past values with the current one.            
			minimumPlays = Math.min(minimumPlays, playsPerColumn[index]);        
		}        
		return minimumPlays;    
	}        
	
	/**
	 * Gets the column amount on this game matrix
	 * @Return the column count on the game matrix
	 */
	public int getColumnCount()
	{
		return currentGame[0].length;
	}
	/**
	 * Calculates the row amount on the game matrix
	 * @Return the game matrix row count
	 */
	public int getRowCount()
	{
		return currentGame.length;
	}
	/**
	 * Checks if the cell specified is a blank space
	 * @Return true if the cell is occupied
	 */
	public boolean hasObstacleIn(int row, int column)
    {
		if (column >=0 && column < currentGame[row].length)
			return currentGame[row][column] > 0;
		return true;
    }
    /**
     * Saves the movement of a car on the game matrix
     * @Param the row and column to modify
     * @Param the direction on which the car is to be moved
     */
    public void moveObstacle(int row, int column, int direction)
    {
    	Vehicle temp =  vehicles[row][column];
    	vehicles [row][column]= null;
    	int tempNum = currentGame [row][column];
    	
    	for (int count = column; count < vehicles[row][column].getWeight(); count+= direction )
    	{
    		vehicles [row][count] = temp;
    		
    	}
	}
    
    public BufferedImage getObstacle(int row, int column)
    {
    	return this.vehicles[row][column].getObstacle();
    }
	public int getWeigth(int row, int column) 
	{
		return this.vehicles[row][column].getWeight();
	}
	public int getStartColumn(int row, int column) 
	{
		return this.vehicles[row][column].getStartingCol();
	}
	public boolean canMoveFrom(int row, int column, int direction) 
	{
		if (direction == 1)
		{
			return this.currentGame[row][this.vehicles[row][column].getEndingCol()+1] == 0;
		}
		else if(direction == -1)
		{
			return this.currentGame[row][this.vehicles[row][column].getStartingCol()-1] == 0;
		}
		return false;
	}
}
