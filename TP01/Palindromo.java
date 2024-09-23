import java.util.Scanner;
class Palindromo{
  public static void main(String[] args){
  Scanner sc = new Scanner(System.in);
  String palavra;
  boolean fim = false;
  do{
  palavra = sc.nextLine();
  boolean palindromo = isPalindromo(palavra);
  if(palavra.length() == 3 && palavra.charAt(0) == 'F' && palavra.charAt(1) == 'I' && palavra.charAt(2) == 'M'){
    fim = true;
  }
  else if(palindromo){
    System.out.println("SIM");
  }
  else{
    System.out.println("NAO");
  }
  
  }
  while(!fim);

  sc.close();
  }
  
  public static boolean isPalindromo(String palavra){
    boolean palindromo = true;
    int i = 0;
    int j = palavra.length() - 1;
    while(palindromo && i < j){
        if(palavra.charAt(i) != palavra.charAt(j)){
         palindromo = false;
         return false;
        }
        i++;
        j--;
    }
    return true;
    } 
 }