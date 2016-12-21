package com.valentine.game.utils.math;

public class Matrix
{
	public static double determinant(double[][] _matrix)
	{ //method sig. takes a matrix (two dimensional array), returns determinant.
		double sum=0; 
		double s;
		if(_matrix.length==1)
		{	//bottom case of recursion. size 1 matrix determinant is itself.
			return(_matrix[0][0]);
		}
		for(int i=0;i<_matrix.length;i++)
		{ //finds determinant using row-by-row expansion
			double[][]smaller= new double[_matrix.length-1][_matrix.length-1]; //creates smaller matrix- values not in same row, column
			for(int a=1;a<_matrix.length;a++)
			{
				for(int b=0;b<_matrix.length;b++)
				{
					if(b<i){
						smaller[a-1][b]=_matrix[a][b];
					}
					else if(b>i){
						smaller[a-1][b-1]=_matrix[a][b];
					}
				}
			}
			if(i%2==0)
			{ //sign changes based on i
				s=1;
			}
			else
			{
				s=-1;
			}
			sum+=s*_matrix[0][i]*(determinant(smaller)); // recursive step: determinant of larger determined by smaller.
		}
		return(sum); //returns determinant value. once stack is finished, returns final determinant.
	}
	
	public static void main(String ... _args)
	{
		double[][] m = new double[][]
		{
			{1  , 5  , 6  , 2.2},
			{3.3, 9  , 10 , 1  },
			{7  , 9  , 3.2, 5.1},
			{5  , 8  , 6.3, 2  }
		};
		
		double[][] m2 = new double[][]
		{
			{1, 2},
			{3, 4}
		};
		
		System.err.println(determinant(m2));

	}
}
