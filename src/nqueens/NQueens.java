/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nqueens;

/**
 *
 * @author alexandre
 */
public class NQueens {

    final static int N=15;

    boolean swapOk(int i, int j, int queen[], int []dn, int dp)
    {
        return true;
    }
//------------------------------------------------------------------------------------------------
    void computeAttacks(int queen[], int []dn, int []dp, int attacks)
    {
        
    }    
//------------------------------------------------------------------------------------------------
    void computeCollisions(int queen[], int []dn, int dp)
    {
        
    }
//------------------------------------------------------------------------------------------------
    void queenSearch2(int queen[])
    {
       int []dn;
       int []dp;
       int []attack;
       int limit,collisions,numberOfAttacks,loopCount;
       int i,j,k;
       
       
       dn=new int[(2*N)-2];
       dp=new int[(N-1)-(1-N)]; 
       attack=new int[N]; 
       
       do
       {
        
        do
        {
        
         //Generate a random permutation of queen[1] to queen[N];
         collisions = computeCollisions(queen,dn,dp);
         limit := C1 * collisions;
         numberOfAttacks = computeAttacks(queen,dn,dp,attack);
         loopcount= 0;
         
         
       
        do
        {
          for(k := 0; k<numberOfAttacks;)
          {        
             i = attack[k];
            // Choose j in [1..N];
            if swap ok(i,j,queen,dn,dp) then begin
        22. perform swap(i,j,queen,dn,dp,collisions);
        23. if collisions = 0 then return;
        24. if collisions < limit then begin
        25. limit := C1  collisions;
        26. number of attacks := compute attacks(queen,dn,dp,attack);
        27. end;
        28. end;
        29. end;
        30. loopcount := loopcount + number of attacks;
        31. until loopcount > C2  N;
        
       }while(collisions!=0)
    }
    
//------------------------------------------------------------------------------------------------    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
