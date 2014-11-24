import java.util.*;
public class SudokuSolverBackTracking {
	int[][] grid = new int[9][9];
	
	Boolean[][] RowData=new Boolean[9][9];
	Boolean[][] ColData=new Boolean[9][9];
	Boolean[][] SquareData=new Boolean[9][9];
			 
	public static final int UNASSIGNED=-1;
		
	SudokuSolverBackTracking(int[][] m)
	{
		grid=m;
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
			{
				RowData[i][j]=false;
				ColData[i][j]=false;
				SquareData[i][j]=false;
			}
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
			{
				if(grid[i][j]!=-1){
					RowData[i][grid[i][j]-1]=true;
					ColData[j][grid[i][j]-1]=true;
					SquareData[(i/3)*3+(j/3)][grid[i][j]-1]=true;
				}
			}
	}
	
	public Location FindUnassignedLocation()
	{
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(grid[i][j]==-1)
				{
					return new Location(i,j);
				}
				
			}
		}
		return null;
	}
	
	public Boolean IsValid(Location l, int Num)
	{
		if(RowData[l.row][Num-1]==true || ColData[l.col][Num-1]==true || SquareData[(l.row/3)*3+(l.col/3)][Num-1]==true)
			return false;
		return true;
	}
	
	public void InsertNum(Location l,int Num)
	{
		grid[l.row][l.col]=Num;
		RowData[l.row][Num-1]=true;
		ColData[l.col][Num-1]=true;
		SquareData[(l.row/3)*3+(l.col/3)][Num-1]=true;
	}
	public void DeleteNum(Location l,int Num) {
		grid[l.row][l.col]=UNASSIGNED;
		RowData[l.row][Num-1]=false;
		ColData[l.col][Num-1]=false;
		SquareData[(l.row/3)*3+(l.col/3)][Num-1]=false;
		
	}
	
	
	public Boolean SolveSudoku()
	{
		Location currLocation=FindUnassignedLocation();
		if(currLocation==null)
			return true;
		
		for(int i=1;i<=9;i++)
		{
			if(IsValid(currLocation,i))
			{
				InsertNum(currLocation, i);
				
				if(SolveSudoku())
					return true;
				
				DeleteNum(currLocation, i);
			}
			
			
		}
		
		return false;
	}
	
	
	public void PrintGrid()
	{
		for(int i=0;i<9;i++)
		{
			System.out.println("");
			for(int j=0;j<9;j++)
			{
				System.out.print("\t"+grid[i][j]);
			}
		}
	}
	

}
class Location
{
	public int row;
	public int col;
	
	Location(int r,int c)
	{
		row=r;
		col=c;
	}
	@Override
	 public boolean equals(Object obj) {
	    if(obj==this)
	    	return true;
	    	
		if(obj == null || obj.getClass() != getClass())
	        return false;
		
		Location l=(Location) obj;
	    return row==l.row && col==l.col;
	 }
	@Override
	public int hashCode(){
		final int prime=31;
		final int prime2=17;
		int result=1;
		result=prime*result+row;
		result=prime*result;
		result=prime*result+col*prime2;
		return result;
	}
	
}