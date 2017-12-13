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
	
	private Obstacle vehicles[][] = null;
	
	private int energySpent = 0;
	
	private int levelBeaten = -1;
	
	/**     
	 * Used to save the minimum number of plays needed to clear the column     
	 * for the train for each column.    
	 * It loads a file with the level.
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
		this.vehicles = new Obstacle[rows][columns];
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
					vehicles[row][column] = vehicles[row][++column] = new Truck(column - 1);
				else if(currentGame[row][column] == 3)
					vehicles[row][column] = vehicles[row][++column] = vehicles[row][++column] = new Bus(column - 2);
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
		int middlePlays = Integer.MAX_VALUE;
		for(int column = 0; column < currentGame[0].length; ++column)        
		{
			for(int row = 0; row < currentGame.length && column < currentGame[0].length; ++row)
			{
				// It's only necessary if the position is 1, because if it is 0, you don't have to move any car.                
				if(currentGame[row][column] > 0)               
				{                    
					// Checks if you can move to the left.                    
					if(vehicles[row][column].getLeftSide() > 0)                    
					{
						leftPlays = getRequiredPlays(row, vehicles[row][column].getLeftSide(), -1, 0);  
						if(vehicles[row][column].getWeight() >= 3 && checkOtherSide(row, column + 1, -1, 2))
							middlePlays = leftPlays + vehicles[row][column].getWeight();
					}                    
					else                    
					{                        
						leftPlays = Integer.MAX_VALUE;                    
					}                    
					// Checks if you can move to the right.                    
					if(vehicles[row][column].getRightSide() + 1 < currentGame[row].length)                    
					{
						rightPlays = getRequiredPlays(row, vehicles[row][column].getRightSide(), 1, 0); 
						if(vehicles[row][column].getWeight() >= 3 && checkOtherSide(row, column + 1, 1, 2))
							middlePlays = Math.min(rightPlays + vehicles[row][column].getWeight(), middlePlays);
					}                    
					else                   
					{                        
						rightPlays = Integer.MAX_VALUE;                    
					}   
					
					setPlaysPerColumn(row, column, leftPlays, middlePlays, rightPlays);

					column += vehicles[row][column].weight - 1;
					middlePlays = Integer.MAX_VALUE;
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
	public int getRequiredPlays(int row, int column, int direction, int energySpent)    
	{        
		// Create variables, one to know if cars can be moved and the second one to return the moves needed to be made.        
		boolean foundZero = false;        
		energySpent += vehicles[row][column].getWeight() - 1;        
		// If the column equals zero or currentGame.length - 1, it will still check towards the expected direction.     
		column += direction;    
		
		
		// Checks towards the direction given to sum to the energySpent and find a zero to know if cars can be moved.
		while(column >= 0 && column < currentGame[row].length && !foundZero)        
		{            
			// Sums an extra move until the zero has been found.  			
			energySpent ++;
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
	 * It checks if it is possible for the space given to be empty given a certain direction.
	 * @param row used to locate the vehicle.
	 * @param column used to locate the vehicle.
	 * @param direction used to check to a given side (-1 left and 1 right).
	 * @param neededZeroes the minimum empty spaces needed to leave an empty space in the given column.
	 * @return true if there are more or equal zeroes than the neededZeroes parameter.
	 */
	public boolean checkOtherSide(int row, int column, int direction, int neededZeroes)
	{
		int foundZeroes = 0;
		column += 2 * direction;
		while(column >= 0 && column < currentGame[row].length)        
		{ 
			if(currentGame[row][column] == 0)            
			{                
				foundZeroes++;      
			}           
			column += direction;        
		} 
		
		return foundZeroes >= neededZeroes;
	}
	
	/**
	 * It sets the values in the playsPerColumn array according to each type of vehicle.
	 * @param row used to locate the vehicle.
	 * @param column used to locate the vehicle.
	 * @param leftPlays used to set the energy used for the left side.
	 * @param middlePlays used to set energy needed by the middle part of a vehicle (bus).
	 * @param rightPlays used to set the energy used for the right side.
	 */
	public void setPlaysPerColumn(int row, int column, int leftPlays, int middlePlays, int rightPlays)
	{
		if(vehicles[row][column].getWeight() == 1)
			playsPerColumn[column] += Math.min(leftPlays, rightPlays); 
		else 
		{
			if(vehicles[row][column].getWeight() >= 3 )
			{
				playsPerColumn[column + 1] += middlePlays;
			}
		
			if(checkOtherSide(row, vehicles[row][column].getLeftSide(), 1, vehicles[row][column].getWeight()) )
			{
				playsPerColumn[column] += Math.min(leftPlays, rightPlays +
						vehicles[row][column].getWeight() * (vehicles[row][column].getWeight() - 1) );
			}
			else
				playsPerColumn[column] += leftPlays;
			
			if(checkOtherSide(row, vehicles[row][column].getRightSide(), -1, vehicles[row][column].getWeight()) )
			{
				playsPerColumn[vehicles[row][column].getRightSide()] += Math.min(rightPlays, leftPlays +
						vehicles[row][column].getWeight() * (vehicles[row][column].getWeight() - 1) );
			}
			else
				playsPerColumn[vehicles[row][column].getRightSide()] += rightPlays;
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
	 * It gets the energy spent by the player.
	 * @return the energy spent by the player.
	 */
	public int getEnergySpent()
	{
		return this.energySpent;
	}
	
	/**
	 * It checks a column to know if the level has been beaten
	 * @param column used to check if the column is empty.
	 */
	public void checkColumn(int column)
	{
		int clearColumn = column;
		for(int row = 0; row < vehicles.length && clearColumn>=0; ++row)
		{
			if(vehicles[row][column] != null)
				clearColumn = -1;
		}
		levelBeaten = clearColumn;			
	}
	
	public int getStarsObtained()
	{
		int starsCalculation = (int) (3 + 0.5 * getMinimumEnergyConsumed() - 0.5* energySpent);
		return starsCalculation > 0? starsCalculation : 0;
	}
	
	/**
	 * It returns if the level has been beaten.
	 * @return true if the level has been beaten, false if not.
	 */
	public int columnLevelHasBeenBeaten()
	{
		return this.levelBeaten;
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
		if (column >=0 && column < vehicles[row].length)
			return vehicles[row][column] != null;
		return true;
    }
    /**
     * Saves the movement of a car on the game matrix
     * @Param the row and column to modify
     * @Param the direction on which the car is to be moved
     */
    public void moveObstacle(int row, int column, int direction)
    {
    	this.energySpent += vehicles[row][column].getWeight();
    	Obstacle temp =  vehicles[row][column];
    	int tempNum = currentGame [row][column];
    	int limit = column + vehicles[row][column].getWeight();
    	for (int count = column; count < limit ; ++count )
    	{
    		vehicles [row][count] = null;
        	currentGame[row][count]=0;
    		
    	}
    	limit = column + direction +  temp.getWeight();
    	for (int count = column + direction; count < limit ; ++count )
    	{
    		vehicles [row][count] = temp;
    		currentGame[row][count]= tempNum;    		
    	}
    	temp.move(direction);
    	
    	if(direction == 1)
    		this.checkColumn(temp.getLeftSide() - 1);
    	else if(direction == -1)
    		this.checkColumn(temp.getRightSide() + 1);
	}
    
    /**
     * It returns the the image of the obstacle for a specific vehicle.
     * @param row used to locate the vehicle.
     * @param column used to locate the vehicle.
     * @return the BufferedImage of the specific vehicle.
     */
    public BufferedImage getObstacle(int row, int column)
    {
    	return this.vehicles[row][column].getObstacle();
    }
    
    /**
     * It gets the energy required to move a specific vehicle.
     * @param row used to locate the vehicle.
     * @param column used to locate the vehicle.
     * @return the energy required to move a specific vehicle.
     */
	public int getWeight(int row, int column) 
	{
		return this.vehicles[row][column].getWeight();
	}
	
	/**
	 * It gets the column where the left part of the vehicle stands.
	 * @param row used to locate the vehicle.
	 * @param column used to locate vehicle.
	 * @return the column where the left part of the vehicle stands.
	 */
	public int getLeftSide(int row, int column) 
	{
		return this.vehicles[row][column].getLeftSide();
	}
	
	/**
	 * It gets the column where the right part of the vehicle stands.
	 * @param row used to locate the vehicle.
	 * @param column used to locate vehicle.
	 * @return the column where the right part of the vehicle stands.
	 */
	public int getRightSide(int row, int column)
	{
		return this.vehicles[row][column].getRightSide();
	}
	
	/**
	 * Checks if the vehicle can move in a certain direction.
	 * @param row used to locate the vehicle.
	 * @param column used to locate the vehicle.
	 * @param direction used to know if the vehicle is going to the left or right.
	 * @return true if the vehicle can move to the side, false if not.
	 */
	public boolean canMoveTo(int row, int column, int direction) 
	{
		if(column + direction == vehicles[row].length || column + direction < 0)
			return false;
		if (direction == 1)
		{
			return this.vehicles[row][this.vehicles[row][column].getRightSide() + 1] == null;
		}
		else if(direction == -1)
		{
			return this.vehicles[row][column - 1] == null;
		}
		return false;
	}
}
