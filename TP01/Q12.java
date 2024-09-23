import java.util.Scanner;

class Q12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String palavra;
        boolean fim = true;
        do{
        palavra = sc.nextLine();
        if(palavra.equals("FIM")){
            fim = false;
        }
        else{
        System.out.println(cifraCesar(palavra));
        }

        }while(fim);


        sc.close();
    }

    public static String cifraCesarTeste(String palavra, int inicio, String decifrada) {
        if (inicio == palavra.length()) {
            return decifrada;
        }

        char letra = palavra.charAt(inicio);
        if (letra != '\uFFFD') {
            letra += 3;
            decifrada += letra;
        } else {
            decifrada += letra;
        }

        return cifraCesarTeste(palavra, inicio + 1, decifrada);
    }


    public static String cifraCesar(String palavra){
        return cifraCesarTeste(palavra, 0, "");
    }
}