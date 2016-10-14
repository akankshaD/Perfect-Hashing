import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;	
import java.util.Iterator;
import java.util.Arrays;


class PriHashTable{              // Primary Hash Table

	int a, b, n_i, m;
	long p;
	ArrayList<Long> sec = new ArrayList<Long>();       // Permanent Storage
	long[] second;                                   // Secondary Hash Table Storage
	

}


public class perfectHashing{


	public static void main(String[] args) throws Exception
	{

		// Writing keys to the file of size 10^10

		File file = new File("randomKeys.txt");
		FileWriter wr = new FileWriter(file);

		int n = 1000000;
		for(int i=0; i < n; i++)
		{
			long val = (long)(Math.random() * 10000000000L);
			wr.write(String.valueOf(val)+ " ");	
		}

		wr.close();


		// Initialising a and p and primary hash table

		int b = 0;
		int m = 2 * n;
		long p = 10000000019L;
		
		int a = (int)((Math.random() * 1000) % p) ;


		PriHashTable[] primary = new PriHashTable[m];
		for(int i =0; i< m; i++)
		{
			primary[i] = new PriHashTable();
		}

		// Generating b randomly and checking the equation accordingly

		int n_i = 0;
		
		do
		{
			b = (int)((Math.random() * 1000)% p);                     // Randomly generated b
			Scanner sc = new Scanner(file);

			for(int i =0; i < n; i++)
			{
				long val = sc.nextLong();
				
				int hashVal = (int)(((a*val + b)% p)% m);              // Hash function
				primary[hashVal].sec.add(val);
			}

			for(int i = 0; i < m; i++)
			{
				
				int temp = primary[i].sec.size();
				primary[i].n_i = temp;
				n_i += (int)(Math.pow(temp, (double)2));
			}

			//System.out.println(n_i);

		}while(n_i > m);

		/*for(int i =0; i < 20; i++)
		{
			System.out.print("i: " + i);
			for(long value: primary[i].sec)
				System.out.print(" " + value + " ");
			System.out.println("");
		}*/

		//System.out.println(a + " " +  b + " " + p + " " + m + " " + n_i );


		// ***************************************** Secondary Level Hashing starts from here!! **************************************

		for(int i = 0; i< m; i++)
		{
			primary[i].p = 71784986521L;
			
			primary[i].m = (int)(Math.pow(primary[i].n_i, (double)2));

			primary[i].second = new long[primary[i].m];

			//System.out.println("i: " + i + "  p: " + primary[i].p + " m: " + primary[i].m);

			int collision;

			do
			{
				collision  = 0;

				Arrays.fill(primary[i].second, -1);   // Initialising the static array to -1

				primary[i].b = (int)((Math.random() * 100000) % primary[i].p);

				primary[i].a = (int)((Math.random() * 100000) % primary[i].p) ; 

				//System.out.println( " a: " + primary[i].a + " b: " + primary[i].b);

				for(long val : primary[i].sec)
				{
					int hashVal = (int)((((primary[i].a * val) + primary[i].b) % primary[i].p) % primary[i].m);

					//System.out.println("val: " + val + " hash: "  + hashVal);

					if(primary[i].second[hashVal] == -1)
					{
						
						primary[i].second[hashVal] = val ;
						
					}
					else 
					{
						// Collision happened!         
						if(primary[i].second[hashVal] != val)       // Ignoring duplicate values
						{
							collision = 1;
							
						}
						break;
						
					}
				}


			}while(collision != 0);
		}


		/*for(int i =0; i < 100; i++)
		{
			System.out.print("i: " + i);
			
			for(int j = 0; j < primary[i].m; i++)
			{
				System.out.print(" " + primary[i].second[j] + " ");
			}

			System.out.println("");
		}*/

		
	}



}
