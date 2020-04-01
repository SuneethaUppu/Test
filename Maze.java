import java.util.Scanner;

public class Maze
{
    public static final int EMPTY = 0;
    public static final int WALL = 1;
    public static final int START = 2;
    public static final int END = 3;
    public static final int VISITED = 4;
    
    public static final char NORTH = 'n';
    public static final char EAST = 'e';
    public static final char SOUTH = 's';
    public static final char WEST = 'w';

    public static void main(String[] args)
    {
        int[][] grid = {
            { EMPTY,  EMPTY,  WALL,  WALL,  WALL,  WALL,  WALL,  WALL,  WALL, WALL},
            {END, EMPTY,  WALL,  WALL, EMPTY, EMPTY, EMPTY, EMPTY,  WALL, WALL},
            { END, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,  WALL, EMPTY,  WALL, WALL},
            { WALL,  WALL,  WALL,  WALL, EMPTY,  WALL, EMPTY,  WALL, EMPTY, WALL},
            { WALL, EMPTY, EMPTY, EMPTY, EMPTY,  WALL, EMPTY, EMPTY, EMPTY, WALL},
            { WALL,  WALL, EMPTY,  WALL,  WALL, EMPTY, EMPTY,  WALL, EMPTY, WALL},
            { WALL,  WALL, EMPTY, EMPTY, EMPTY, EMPTY,  WALL,  WALL, EMPTY,  END},
            { WALL,  WALL,  WALL,  WALL,  WALL,  WALL,  WALL,  WALL,  WALL, WALL},
        };
                
        Gridviewer.view(grid);
        
        String solution;
        Scanner sc = new Scanner(System.in);
        System.out.println("Find a solution to get from ^^ to $$, using the characters " 
            + "'" + NORTH + "', '" + EAST + "', '" + SOUTH + "' and '" + WEST + "'"
            + " (for north, east, south and west).");
        System.out.print("Your solution: ");
        solution = sc.nextLine();
        
        int currentRow = 1;
        int currentCol = 0;
        boolean done = false;
        boolean solved = false;
        int charIndex = 0;
        int solutionLength = solution.length();
        
        while(!done && charIndex < solutionLength)
        {
            char direction = solution.charAt(charIndex);
            System.out.println("Location: (" + currentRow + ", " + currentCol 
                + "), next direction: '" + direction + "'");
            
            switch(direction)
            {
                case NORTH:
                    currentRow--;
                    break;
                
                case EAST:
                    currentCol++;
                    break;
                    
                case SOUTH:
                    currentRow++;
                    break;
                    
                case WEST:
                    currentCol--;
                    break;
            
                default:
                    System.out.println("You have no idea where you are going"); // Invalid direction.
            }
            
            if(currentRow < 0 || currentCol < 0
                || currentRow >= grid.length || currentCol >= grid[currentRow].length)
            {
                done = true;
                System.out.println("You fall into the chasm of doom"); // Out of bounds.
            }
            else
            {
                switch(grid[currentRow][currentCol])
		{
			case Maze.EMPTY:
				grid[currentRow][currentCol] = Maze.VISITED;
				break;

			case Maze.WALL:
				System.out.println("you stumble blindly into a solid concrete wall.");
				break;

			case Maze.END:
				done = true;
				System.out.println("solved");
				break;

			default:
				//Do anything
		}

            }
            
            charIndex++;
        }
        
        if(!solved)
        {
            System.out.println("you have failed to escape. Further archeologists gaze upon your remains in bafflement"); // Did not reach the end.
        }
        
        Gridviewer.view(grid);
    }
}
