import java.util.Scanner;
import java.util.Random;

class Q04 {
    private static Random gerador = new Random();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean fim = true;
        String novaPalavra;
        int tam;
        gerador.setSeed(4);

        do {
            String palavra = sc.nextLine();
            tam = palavra.length();

            if (tam == 3 && palavra.charAt(0) == 'F' && palavra.charAt(1) == 'I' && palavra.charAt(2) == 'M') {
                fim = false;
            } else {
                novaPalavra = alteracaoAleatoria(palavra);
                System.out.println(novaPalavra);
            }
        } while (fim);

        sc.close();
    }

    public static String alteracaoAleatoria(String palavra) {
        
        
        char letra1, letra2;
        
        int numGerado = Math.abs(gerador.nextInt()) % 26;
        letra1 = (char) ('a' + numGerado);

        int numGerado2 = Math.abs(gerador.nextInt()) % 26;
        letra2 = (char) ('a' + numGerado2);
       
        int tam = palavra.length();
        StringBuilder novaPalavra = new StringBuilder(palavra);

        for (int i = 0; i < tam; i++) {
            if (palavra.charAt(i) == letra1) {
                novaPalavra.setCharAt(i, letra2);
            }
        }

        return novaPalavra.toString();
    }
}