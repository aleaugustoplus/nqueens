/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nqueens;

import java.util.Random;

/**
 *
 * @author alexandre
 */
public class NQueens {

    final static int N=11;
    final static int MaxPrint=11;
    final static float C1=0.45F;
    final static float C2=500;
    int []Queens;
    int[] dn;
    int[] dp;

//------------------------------------------------------------------------------------------------    
    void RemoveQueenFromDiagonals(int number)
    {        
        dn[CalculateDn(Queens[number], number)]--;                
        dp[CalculateDp(Queens[number], number)]--;
    }
//------------------------------------------------------------------------------------------------    
    void AddQueenOnDiagonals(int number)
    {        
        dn[CalculateDn(Queens[number], number)]++;                
        dp[CalculateDp(Queens[number], number)]++;
    }
//------------------------------------------------------------------------------------------------    
    int CollisionsOnDiagonals(int number)
    {                      
        return CollisionsOnDiagonalDn(number) + CollisionsOnDiagonalDp(number);
    }
 //------------------------------------------------------------------------------------------------    
    int CollisionsOnDiagonalDn(int number)
    {      
        int collisions=0;
        
        if(dn[CalculateDn(Queens[number], number)]>1)
            collisions+=(dn[CalculateDn(Queens[number], number)]-1);
       
        return collisions;
    }       
//------------------------------------------------------------------------------------------------    
    int CollisionsOnDiagonalDp(int number)
    {      
        int collisions=0;
        
        if(dp[CalculateDp(Queens[number], number)]>1)
            collisions+=(dp[CalculateDp(Queens[number], number)]-1);
       
        return collisions;
    }       
  
//------------------------------------------------------------------------------------------------        
    int CalculateDp(int x, int y)
    {
        //System.out.println("x:" + x + " y:" + y +" dp:" + ((x-y) + (N-1)));
        return ((x-y) + (N-1) );
    }    
//------------------------------------------------------------------------------------------------    
    int CalculateDn(int x, int y)
    {
        //System.out.println("x:" + x + " y:" + y +" dn:" + (x+y));
        return x+y;
        
    }        
//------------------------------------------------------------------------------------------------    
    public void printQueens() 
    {
        int size = Queens.length;    
        
        if(N>MaxPrint)
            return;
                                   
        for (int i = size-1; i>=0; i--) 
        {
            for (int j = 0; j < size; j++) 
            {
                if(j==0)
                {
                    System.out.print((i)+" ");
                }
                    
                if (Queens[i] == j) 
                {
                    System.out.print("Q ");
                } 
                else 
                {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.print("  ");
        for(int y=0;y<size;y++)
            System.out.print(y+" ");
        
        System.out.println("");
        printDiagonals();
        
    }
//------------------------------------------------------------------------------------------------    
    public void printDiagonals() 
    {
        if(N>MaxPrint)
            return;
        
        System.out.print("dn: ");
        for(int tmp: dn)
            System.out.print(tmp + " ");

        System.out.println("");
        
        System.out.print("dp: ");
        for(int tmp: dp)
            System.out.print(tmp + " ");

        System.out.println("");

        //System.out.println("---------------------------------------");
    }   
//------------------------------------------------------------------------------------------------    
    int performSwap(int i, int j, int collisions)
    {
        int aux;
        int collisions_ant_i_dp=0;
        int collisions_ant_i_dn=0;
        int collisions_ant_j_dp=0;
        int collisions_ant_j_dn=0;
        int collisions_cur_i_dp=0;
        int collisions_cur_i_dn=0;
        int collisions_cur_j_dp=0;
        int collisions_cur_j_dn=0;
        int DiffCollisions=0;

        
        printDiagonals();
        System.out.println("Before \ni: " + i + " dp:" + dp[CalculateDp(Queens[i], i)] + " dn: " + dn[CalculateDn(Queens[i], i)] +
                           " j: " + j + " dp:" + dp[CalculateDp(Queens[j], j)] + " dn: " + dn[CalculateDn(Queens[j], j)]);
        
        collisions_ant_i_dp+=CollisionsOnDiagonalDp(i);
        collisions_ant_i_dn+=CollisionsOnDiagonalDn(i);
        collisions_ant_j_dp+=CollisionsOnDiagonalDp(j);
        collisions_ant_j_dn+=CollisionsOnDiagonalDn(j);
        
        
        RemoveQueenFromDiagonals(i);
        RemoveQueenFromDiagonals(j);
                       
                
        aux=Queens[i];
        Queens[i]=Queens[j];
        Queens[j]=aux;
        
        AddQueenOnDiagonals(i);
        AddQueenOnDiagonals(j);
        
        collisions_cur_i_dp+=CollisionsOnDiagonalDp(i);
        collisions_cur_i_dn+=CollisionsOnDiagonalDn(i);
        collisions_cur_j_dp+=CollisionsOnDiagonalDp(j);
        collisions_cur_j_dn+=CollisionsOnDiagonalDn(j);
        
        if(collisions_ant_i_dp>collisions_cur_i_dp)
        {    
            DiffCollisions++;
        }
        else if(collisions_ant_i_dp<collisions_cur_i_dp)
        {
            DiffCollisions--;
        }
        
        if(collisions_ant_i_dn>collisions_cur_i_dn)
        {    
            DiffCollisions++;
        }
        else if(collisions_ant_i_dn<collisions_cur_i_dn)
        {
            DiffCollisions--;
        }
        
        if(collisions_ant_j_dp>collisions_cur_j_dp)
        {    
            DiffCollisions++;
        }
        else if(collisions_ant_j_dp<collisions_cur_j_dp)
        {
            DiffCollisions--;
        }
        
        if(collisions_ant_j_dn>collisions_cur_j_dn)
        {    
            DiffCollisions++;
        }
        else if(collisions_ant_j_dn<collisions_cur_j_dn)
        {
            DiffCollisions--;
        }
        

        System.out.println("Result: " + (collisions - DiffCollisions) + 
                          " Compute collisions: " + computeCollisions());
        System.out.println("i: " + i + " dp:" + dp[CalculateDp(Queens[i], i)] + " dn: " + dn[CalculateDn(Queens[i], i)] +
                           " j: " + j + " dp:" + dp[CalculateDp(Queens[j], j)] + " dn: " + dn[CalculateDn(Queens[j], j)]);
        printDiagonals();
        
        
        
        //return computeCollisions();
        return collisions - DiffCollisions;        
    }
//------------------------------------------------------------------------------------------------    
    boolean swapOk(int i, int j)
    {
        int auxi;
        int auxj;
        int DiffCollisions=0;
  
        int collisions_ant_i_dp=0;
        int collisions_ant_i_dn=0;
        int collisions_ant_j_dp=0;
        int collisions_ant_j_dn=0;
        int collisions_cur_i_dp=0;
        int collisions_cur_i_dn=0;
        int collisions_cur_j_dp=0;
        int collisions_cur_j_dn=0;
        
        
        //printDiagonals();
        //--First state--
        collisions_ant_i_dp+=CollisionsOnDiagonalDp(i);
        collisions_ant_i_dn+=CollisionsOnDiagonalDn(i);
        collisions_ant_j_dp+=CollisionsOnDiagonalDp(j);
        collisions_ant_j_dn+=CollisionsOnDiagonalDn(j);
       
        RemoveQueenFromDiagonals(i);
        RemoveQueenFromDiagonals(j);
        
                        
        auxi=Queens[i];
        auxj=Queens[j];
        Queens[i]=auxj;
        Queens[j]=auxi;
        
        //--Mixed state--
        AddQueenOnDiagonals(i);
        AddQueenOnDiagonals(j);
                
        collisions_cur_i_dp+=CollisionsOnDiagonalDp(i);
        collisions_cur_i_dn+=CollisionsOnDiagonalDn(i);
        collisions_cur_j_dp+=CollisionsOnDiagonalDp(j);
        collisions_cur_j_dn+=CollisionsOnDiagonalDn(j);
        
        if(collisions_ant_i_dp>collisions_cur_i_dp)
        {    
            DiffCollisions++;
        }
        else if(collisions_ant_i_dp<collisions_cur_i_dp)
        {
            DiffCollisions--;
        }
        
        if(collisions_ant_i_dn>collisions_cur_i_dn)
        {    
            DiffCollisions++;
        }
        else if(collisions_ant_i_dn<collisions_cur_i_dn)
        {
            DiffCollisions--;
        }
        
        if(collisions_ant_j_dp>collisions_cur_j_dp)
        {    
            DiffCollisions++;
        }
        else if(collisions_ant_j_dp<collisions_cur_j_dp)
        {
            DiffCollisions--;
        }
        
        if(collisions_ant_j_dn>collisions_cur_j_dn)
        {    
            DiffCollisions++;
        }
        else if(collisions_ant_j_dn<collisions_cur_j_dn)
        {
            DiffCollisions--;
        }
       
        printQueens();
        printDiagonals();
        
        //--Return to original state--
        RemoveQueenFromDiagonals(i);
        RemoveQueenFromDiagonals(j);
        
        Queens[i]=auxi;
        Queens[j]=auxj;
        
        AddQueenOnDiagonals(i);
        AddQueenOnDiagonals(j);
        
        
        return DiffCollisions>0;
        
    }    
//------------------------------------------------------------------------------------------------
    int computeAttacks(int []attacks)
    {
       int numAttack=0;
       
       for(int x=0; x<N;x++)
       {
            if( dn[CalculateDn(Queens[x],x)]>1 || dp[CalculateDp(Queens[x],x)]>1)
            {                
                attacks[numAttack++]=x;
            }                            
       }
       
       return numAttack; 
    }    
//------------------------------------------------------------------------------------------------
    int computeCollisions()
    {
        int ret=0;
        
        for(int x=0; x<dn.length;x++)
        {
            dn[x]=0;
            dp[x]=0;
        }
        
        for(int x=0; x<N;x++)
        {
            dn[CalculateDn(Queens[x],x)]++;
            dp[CalculateDp(Queens[x],x)]++;
        }
        
        
        for(int x=0;x<dn.length;x++)
        {
           if(dn[x]>1)
            ret+=dn[x]-1; 
           if(dp[x]>1)
            ret+=dp[x]-1; 
        }
        
        
        
        return ret; 
    }
//------------------------------------------------------------------------------------------------
    void queenSearch2() 
    {
        
        int[] attack;
        int limit, collisions, numberOfAttacks, loopCount;
        int i, j, k;
        Random rand=new Random();
        boolean test=false;
        int tmp=0;
                
        attack = new int[N];

        
        do 
        {
            printQueens();
            System.out.println("Generating new random sequence...");
            //for(int x=0;x<N;x++)
            //    Queens[x]=rand.nextInt(N);
            for(int x=0;x<N;x++)
                Queens[x]=x;
            
            
            printQueens();
            //Generate a random permutation of queen[1] to queen[N];
            collisions = computeCollisions();
            printDiagonals();
            //System.out.println("Collisions:"+collisions);
            
            limit = (int) C1 * collisions;
            numberOfAttacks = computeAttacks(attack);
            loopCount = 0;

            do 
            {
                
                for (k = 0; k < numberOfAttacks;k++) 
                {
                    i = attack[k];                    
                    j=rand.nextInt(N);
                    //j=2;
                    
                    if(N<MaxPrint)
                        System.out.println("---------------------------------------");
                    printQueens();
                    if(N<MaxPrint)
                        System.out.println("i: " + i + " j: " + j);
                    if (swapOk(i, j)) 
                    {
                                                
                        collisions = performSwap(i, j, collisions);
                        if(tmp%1==0)
                            System.out.println("Swap collisions now: " + collisions);
                        tmp++;
                        if (collisions == 0) 
                        {
                            return;
                        }
                        
                        if (collisions < limit) 
                        {
                            limit = (int) C1 * collisions;
                            numberOfAttacks = computeAttacks(attack);
                        }
                    }
                    
                       
                }

                loopCount += numberOfAttacks;

            } while (loopCount < C2 * N);

        } while (collisions != 0);
        
    }
//------------------------------------------------------------------------------------------------
    public NQueens(int []Queens)            
    {
        this.Queens=Queens;
        dn = new int[2 * N - 1];
        dp = new int[2 * N - 1];
    }    
//------------------------------------------------------------------------------------------------    
    public static void main(String[] args) 
    {
       
        int []vQueens=new int[N];
        long time;
        NQueens queens = new NQueens(vQueens);
        
        time=System.currentTimeMillis();
        queens.queenSearch2();
        queens.printQueens();
        System.out.println("Time spent: " + (System.currentTimeMillis()-time) + " ms");
                                
    }
    
}
//------------------------------------------------------------------------------------------------