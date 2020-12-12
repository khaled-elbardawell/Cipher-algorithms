
package is;


import java.util.Scanner;

public class RSA extends helper {
   
    public static void main(String[] args) { 
              Rsa();
    }
    
    public static void Rsa(){
        boolean bo = true;
        
        while(bo){

                  System.out.print("\n 1- RSA encription \n" 
                         + " 2- RSA decription \n"
                         + " 3- Back \n"
                         + " 4- Exit \n"
                     );
                    
                System.out.print("Enter num option : ");
                int numOptionFromList =  in.nextInt();

                switch(numOptionFromList){
                    case 1 : 
                         System.out.print("Enter plaintxt  : ");
                         Scanner s = new Scanner(System.in);
                         String plain =  s.nextLine();
                         plain = plain.toLowerCase();
                         
                         boolean b = true;
                         int p = -1;
                         while(b){
                            System.out.print("Enter p (int) (prim) : ");
                              p =  in.nextInt(); 
                             if(isPrime(p)){
                                 b =false;
                             }else{
                                 System.out.println("p must be prime number try again !!");
                             }
                         }
                        
                      
                         
                         b = true;
                         int q =-1;
                         while(b){
                            System.out.print("Enter q (int) (prim) : ");
                              q =  in.nextInt(); 
                             if(isPrime(q)){
                                 b =false;
                             }else{
                                 System.out.println("q must be prime number try again !!");
                             }
                         }
                         
                         
                       
                        
                         System.out.print("Enter e (int) : ");
                         int e =  in.nextInt(); 
                         
                         String ciphertext = encriptionRSA(plain,p,q,e);
                         if(ciphertext != null){
                              System.out.println("\n************ Result ***********");
                              System.out.println("Ciphertext => " + ciphertext.toUpperCase());    
                              System.out.println("*******************************");
                         }
                        
                         
                        break;
                    case 2:
                        System.out.print("Enter ciphertext  : ");
                          s = new Scanner(System.in);
                         String cipher =  s.nextLine();
                         plain = cipher.toLowerCase();
                          b = true;
                          p = -1;
                         while(b){
                            System.out.print("Enter p (int) (prim) : ");
                              p =  in.nextInt(); 
                             if(isPrime(p)){
                                 b =false;
                             }else{
                                 System.out.println("p must be prime number try again !!");
                             }
                         }
                        
                      
                         
                         b = true;
                          q =-1;
                         while(b){
                            System.out.print("Enter q (int) (prim) : ");
                              q =  in.nextInt(); 
                             if(isPrime(q)){
                                 b =false;
                             }else{
                                 System.out.println("q must be prime number try again !!");
                             }
                         }
                         
                         
                       
                        
                            System.out.print("Enter e (int) : ");
                              e =  in.nextInt(); 
                             
                         
                        
                         String plaintext = encriptionRSA(plain,p,q,e);
                         System.out.println("\n************ Result ***********");
                         System.out.println("Ciphertext => " + plaintext.toUpperCase());    
                         System.out.println("*******************************");
                        break;
                   case 3 : 
                      bo = false; 
                    
                      break;

                     case 4 : 
                      System.exit(0); 
                    
                      break;     
                }
        }
        
    }
    
    public static String encriptionRSA(String plaintext,int p,int q,int e)
    {
        
       char [] plainTextCharArray = getAlphabeticToarray(plaintext);
       String cipherText = "";
       int n = -1 , euler = -1 , publicKey = -1 , privateKey = -1;
       int plainTextIntArray[] = new int [plainTextCharArray.length];
       
       
         for(int i = 0 ; i < plainTextCharArray.length ; i++ ){ // loop eche char in plain text
               int charNum = getNumCharFromAlphabetic(plainTextCharArray[i]); // get number of plain char from Alphabetic array
               if(charNum != -1){
                    plainTextIntArray[i] = charNum;
               }
         }
         
     
         n = q * p;
         
         euler = euler(q,p);
         if(! checkE(e,euler)){
              System.out.println("\n************ Result ***********");
              System.out.println("Error ( e , euler ) must be coprime !! ");
              System.out.println("*******************************");
             return null;
         }
         
         for(int i = 0 ; i < plainTextIntArray.length ; i++ ){ // loop eche char in plain text              
               int c = (int) Math.pow(plainTextIntArray[i] , e) % n;
               c = checkIndex(c);
               cipherText += String.valueOf(alphabeticArray[c]);
         }
         
         
         return cipherText;
    }    
    
    public static int checkIndex(int n){
            while(n > alphabeticArray.length){
               n = n % alphabeticArray.length;
            }
            
            while(n < 0){
               n = n + alphabeticArray.length;
            }
            return n;
   }
    
    public static String decriptionRSA(String ciphertext,int p,int q,int e)
    {
        
       char [] cipherTextCharArray = getAlphabeticToarray(ciphertext);
       String plainText = "";
       int n = -1 , euler = -1;
       int cipherTextIntArray[] = new int [cipherTextCharArray.length];
       
       
         for(int i = 0 ; i < cipherTextCharArray.length ; i++ ){ // loop eche char in plain text
               int charNum = getNumCharFromAlphabetic(cipherTextCharArray[i]); // get number of plain char from Alphabetic array
               if(charNum != -1){
                    cipherTextIntArray[i] = charNum;
               }
         }
         
        
         n = q * p;
         
         euler = euler(q,p);

         if(! checkE(e,euler)){
              System.out.println("\n************ Result ***********");
              System.out.println("Error ( e , euler ) must be coprime !! ");
              System.out.println("*******************************");
             return null;
         }

          int  d  = inverses(euler,e); 
         for(int i = 0 ; i < cipherTextIntArray.length ; i++ ){ // loop eche char in plain text              
               int c = (int) Math.pow(cipherTextIntArray[i] , d) % n;
               plainText += String.valueOf(alphabeticArray[c]);
         }
         
         
         return plainText;
    }    
    
      public static boolean checkE(int e,int euler){
          if(e >= 1 && e <= euler){
              if ( GCD(e,euler) == 1 ){
                  return true;
              }
          }
          return false;
      }
            
         public static int  euler(int q,int p){  
             return ((p-1) * (q-1));
         }
            
            
      public static boolean isPrime(int n){  
              int i,m=0;      
              m=n/2;      
              if(n==0||n==1){  
               return false;      
              }else{  
               for(i=2;i<=m;i++){      
                if(n%i==0){      
                      return true;
                 }      
               }      
                 return true;
              }//end of else  
            }
    
}
