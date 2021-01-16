
package is;

import java.util.Scanner;
import java.lang.reflect.Array;



public class helper {
    
     protected static Scanner in = new Scanner(System.in);
     protected static String alphabetic = "abcdefghijklmnopqrstuvwxyz";
     protected static char [] alphabeticArray  =  getAlphabeticToarray(alphabetic);

    
    protected static char[] getAlphabeticToarray(String str)
    {
          return str.toCharArray();
    }
    
    protected static int getNumCharFromAlphabetic(char plainChar)
    {
         char [] alphabeticArray  =  getAlphabeticToarray(alphabetic);
         for(int i = 0 ; i < alphabeticArray.length ; i++ ){
            if(alphabeticArray[i] == plainChar ){
                   return i;
                 }
           }
         return -1;
    }
    
    
    protected static int GCD(int A, int B)
    {
          int r = 0;
          while(B > 0){
              r = A % B;
              A = B;
              B = r;
          }
          return A;
    }
    
     protected static int inverses(int m, int b)
     {
          // 3 mod 10 
            for(int i = 0;i < m;i++){
                if( ((b * i) % m) == 1 ){
                    return i % m;
                }
            }
            
           return -1;
     } // end inverse function
    
    
    
    
    
     protected static int [][] inversesMatrixMod(int matrix[][],int n , int mod)
     {
        // check if matrix have inverse or not
        int determinant = determinantMatrixMod(matrix, n,mod);
       if( (determinant == 0) || (GCD(determinant,mod) != 1) ){
           System.out.println("This matrix have not inverse !! ");
           return null;
       }else{
            int inverseDeterminant = inverses(mod,determinant);
            int result [][] = new int[n][n]; 
            
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    int r  = inverseDeterminant * (((int) Math.pow(-1,i+j)) * Dji(matrix,n,mod,j,i));
                    while(r < 0){
                           r += mod;
                     }
                     result[i][j] = r % mod;
                }
             }
            return result;
       }

         
     }
     
     
       // 3 * 3 or 2 * 2 just
       private static int Dji(int [][] matrix,int n,int mod,int j,int i){
                  
                  if(n == 2){
                         return  determinantMatrixMod( matrix,2,mod);
                   }else if(n == 3){
                   int v[] = new int[4];
                   int count = 0;
                   for(int row = 0; row < n ; row++){
                       for(int col = 0; col < n ; col++){ 
                           if(col != i && row != j ){
                                Array.set(v, count, matrix[row][col]);
                                count++;   
                           }
                        }
                  }
                
                 int  d =  (v[0] * v[3]) - (v[1]* v[2]);
                            while(d < 0){
                              d += mod;
                            }
                            d = d % mod;
                         return   d;
                   }else{
                        System.out.println("can not calc determinant when length of matrix > 3");
                        return -1;
                   }
                  
       }
       
       

       protected static int[][] multiMatrix(int x[][],int y[][])
       {
         
              int r [][] = null;
                      
              if(x[0].length == y.length){
                  r = new int[x.length][y[0].length]; 
                  for(int row = 0 ; row < x.length ; row++){
                      for(int colY = 0 ; colY < y[0].length ; colY++ ){
                          int sum = 0 ;
                        for(int col = 0 ; col < x[0].length ; col++){
                           sum = sum +  (x[row][col] * y[col][colY]) ;
                        } // end for col
                        r[row][colY] = sum; 
                      } // end for colY
                  } // end for row
                   return r;
              } // end if
               
              System.out.println("Error in multi you must use 2 matrix ( first matrix length row = 2nd matrix length col )");             
              return r;          
          }
 
       
     // 3 * 3 or 2 * 2 just
     protected static int determinantMatrixMod(int [][] matrix,int n,int mod)
     {
              int d = -1;
              int r = 0;
              int sum = 0;
              if (n == 2){
                   d =  (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
                    while(d < 0){
                      d += mod;
                    }
                  
                  d = d % mod;
              }else if(n == 3){
                   
                  for(int i = 0 ; i < n ; i++){
                      r =  (int) Math.pow(-1,i) * matrix[0][i];
                     if(i == 0){
                      sum +=  r * ((matrix[1][1]* matrix[2][2]) - (matrix[1][2]* matrix[2][1])) ;
                     }else if(i == 1){
                      sum +=  r * ((matrix[1][0]* matrix[2][2]) - (matrix[1][2]* matrix[2][0]));
                     }else if(i == 2){
                      sum +=  r * ( (matrix[1][0]* matrix[2][1]) - ( matrix[1][1]* matrix[2][0]) );

                     }
                  }
                  
                  while(sum < 0){
                      sum += mod;
                  }
                  d = sum % mod;
                 
              }else{
                  System.out.println("can not calc determinant when length of matrix > 3");
              }
              return d;
      }
     
     
     
              
          
      protected static void print2DArray(int x[][])
      {
             System.out.println("************* print 2D Array ******************");
              for(int row = 0; row < x.length ; row++){
                   System.out.println("********************* row "+ (row + 1) +"**************");
                  for(int col = 0; col < x[0].length; col++){
                   System.out.println("*********************col "+ (col + 1) +"**************");
                   System.out.println(x[row][col]);
                  }
              }
            System.out.println("**************************************");
      
      }
          
     
    
}
