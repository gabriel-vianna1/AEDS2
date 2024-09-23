import java.util.Scanner;
class Q03{
public static void main(String[] args){
Scanner sc = new Scanner(System.in);
boolean fim = true;
do{
String palavra = sc.nextLine();
int tam = palavra.length();
if(tam == 3 && palavra.charAt(0) == 'F' && palavra.charAt(1) == 'I' && palavra.charAt(2) == 'M' ){
    fim = false;
}else{
String codigo = cifraCesar(palavra);
System.out.println(codigo);
}}
while(fim);
sc.close();
}
public static String cifraCesar(String palavra){
String codificado = "";
char letra;
int tam = palavra.length();
for(int i = 0; i < tam; i++){
    letra = palavra.charAt(i);
    if(letra > 0 && letra < 127){
    letra += 3;
    codificado += letra;
  }
  else if(letra == '\uFFFD'){
    codificado += letra;
  }
}
 return codificado;
}
 }
