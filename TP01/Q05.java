import java.util.Scanner;

class Q05{
    public static void main(String[] args){
         Scanner sc = new Scanner(System.in);
         
        sc.close();
    }

    public static boolean isTrue(String expressao){
     
        int n = expressao.charAt(0);
        int A, B, C;
        char[] portasLogicas = new char[10];

     if(n == 2){
        A = expressao.charAt(2);
        B = expressao.charAt(4);
     }
     else if(n == 3){
       A = expressao.charAt(2);
       B = expressao.charAt(4);
       C = expressao.charAt(6);
     }

     for(int i = (2 * n) + 2; i < expressao.length(); i++){
     if(expressao.charAt(i) == '('){
        int j = 0;
        portasLogicas[j] = expressao.charAt(i - 1);
        j++;
     }
 
     if(expressao.charAt(i) == ')'){
     
     }

  }
  return true;
}
    
}