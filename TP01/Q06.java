import java.util.Scanner;
class Q06{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean fim = true;
        String palavra = "";
        boolean x[] = new boolean[4];
        do{
        palavra = sc.nextLine();
        if(palavra.length() == 3 && palavra.charAt(0) == 'F' && palavra.charAt(1) == 'I' && palavra.charAt(2) == 'M'){
        fim = false;}
        else{
        x[0] = isVogal(palavra);
        x[1] = isConsoante(palavra);
        x[2] = isInteiro(palavra);
        x[3] = isFloat(palavra);
        for(int i = 0; i < 4; i++){
            imprimeResposta(x[i]);
        }
         System.out.println();
        }}while(fim);

        sc.close();
    }

    public static boolean isVogal(String palavra){
        int tam = palavra.length();
        int cont = 0;
        for(int i = 0; i < tam; i++){
            if(palavra.charAt(i) == 'a' || palavra.charAt(i) == 'e' || palavra.charAt(i) == 'i' || palavra.charAt(i) == 'o' || palavra.charAt(i) == 'u' || palavra.charAt(i) == 'A' || palavra.charAt(i) == 'E' || palavra.charAt(i) == 'I' || palavra.charAt(i) == 'O' || palavra.charAt(i) == 'U'){
                cont++;
            } 
        }
         if(cont == tam){
            return true;
         }
         else{
            return false;
         }
    }

    public static boolean isConsoante(String palavra){
        int tam = palavra.length();
        int cont = 0;
        char letra;
        for(int i = 0; i < tam; i++){
            letra = palavra.charAt(i);
            if(palavra.charAt(i) != 'a' && palavra.charAt(i) != 'e' && palavra.charAt(i) != 'i' && palavra.charAt(i) != 'o' && palavra.charAt(i) != 'u' && palavra.charAt(i) != 'A' && palavra.charAt(i) != 'E' && palavra.charAt(i) != 'I' && palavra.charAt(i) != 'O' && palavra.charAt(i) != 'U' && !Character.isDigit(letra)){
                cont++;
            } 
        }
         if(cont == tam){
            return true;
         }
         else{
            return false;
         }
        }

     public static boolean isInteiro(String palavra){
        try{
            Integer.parseInt(palavra);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
     }  

     public static boolean isFloat(String palavra){
        try{
            Float.parseFloat(palavra);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
     }

     public static void imprimeResposta(boolean resposta){
        if(resposta){
            System.out.print("SIM ");
        }
        else{
            System.out.print("NAO ");
        }
     }

}