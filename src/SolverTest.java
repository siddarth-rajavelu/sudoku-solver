import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

public class SolverTest {

	public static void main(String[] args) {
		int[][] mat=new int[9][9];
		
		try(BufferedReader br=new BufferedReader(new FileReader("/home/siddarth/Documents/SudokuHard"));){
			String str;
			while((str=br.readLine())!=null){
			
			for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
			{
				mat[i][j]=(str.charAt(i*9 + j)=='.')?-1:Character.getNumericValue(str.charAt(i*9+j));
			}
			
			SudokuSolverBackTracking s=new SudokuSolverBackTracking(mat);
			//System.out.println("Given Matrix ");
			//s.PrintGrid();
			//System.out.println("\n Solved Matrix");
			long startTime=System.nanoTime();
			s.SolveSudoku();
			long endTime=System.nanoTime();
			long elapsedTimeMillis=(endTime-startTime)/1000000;
			//s.PrintGrid();
			System.out.println("\n Elapsed Time in milliseconds:"+ elapsedTimeMillis);
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		
		System.out.println("Solved successfully");
		

	}
}
