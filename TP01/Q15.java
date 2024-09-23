import java.util.Scanner;

class Q15{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean fim = true;
        boolean x[] = new boolean[4];
        String palavra =  "";
        do{
        palavra = sc.nextLine();
        if(palavra.equals("FIM")){
            fim = false;
        }
        else{
            x[0] = isVogal(palavra);
            x[1] = isConsoante(palavra);
            x[2] = isInt(palavra);
            x[3] = isFloat(palavra);

        for(int i = 0; i < 4; i++){
            imprimeResposta(x[i]);
        }
        System.out.println();
    }   
 }while(fim);

        sc.close();
    }

    public static boolean isConsoanteTeste(String palavra, int inicio){
        if(inicio == palavra.length()){
        return true; 
        }
        char letra = palavra.charAt(inicio);
        if(letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u' || letra == 'A' || letra == 'E' || letra == 'I' || letra == 'O' || letra == 'U'  || Character.isDigit(letra)){
        return false;
        }
        return isConsoanteTeste(palavra, inicio + 1);
    }

    public static boolean isConsoante(String palavra){
        return isConsoanteTeste(palavra, 0);
    }

    public static boolean isVogalTeste(String palavra, int inicio){
        if(inicio == palavra.length()){
            return true;
        }
        
        char letra = palavra.charAt(inicio);

        if(letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u' || letra == 'A' || letra == 'E' || letra == 'I' || letra == 'O' || letra == 'U'){
        return isVogalTeste(palavra, inicio + 1);
        }
        else{
            return false;
        }
    }

    public static boolean isVogal(String palavra){
        return isVogalTeste(palavra, 0);
    }

    public static boolean isIntTeste(String palavra, int inicio){
        if(inicio == palavra.length()){
            return true;
        }

        char letra = palavra.charAt(inicio);

        if(Character.isDigit(letra)){
            return isIntTeste(palavra, inicio + 1);
        }
        else{
             return false;
        }
    }

    public static boolean isInt(String palavra){
        return isIntTeste(palavra, 0);
    }

    public static boolean isFloatTeste(String palavra, int inicio){
        if(inicio == palavra.length()){
            return true;
        }

        char letra = palavra.charAt(inicio);

        if(Character.isDigit(letra) || letra == '.'){
            return isIntTeste(palavra, inicio + 1);
        }
        else{
             return false;
        }

    }

    public static boolean isFloat(String palavra){
        return isFloatTeste(palavra, 0);
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