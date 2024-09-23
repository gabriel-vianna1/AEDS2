import java.util.Scanner;

class Q10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean fim = true;
        String palavra;
        do {
            palavra = sc.nextLine();
            if (palavra.equals("FIM")) {
                fim = false;
            } else {
                if (isPalindromo(palavra)) {
                    System.out.println("SIM");
                } else {
                    System.out.println("NAO");
                }
            }
        } while (fim);
        sc.close();
    }

    private static boolean isPalindromoTeste(String palavra, int inicio, int fim) {
        if (inicio >= fim) {
            return true;
        }
        if (palavra.charAt(inicio) != palavra.charAt(fim)) {
            return false;
        }
        return isPalindromoTeste(palavra, inicio + 1, fim - 1);
    }

    public static boolean isPalindromo(String palavra) {
        return isPalindromoTeste(palavra, 0, palavra.length() - 1);
    }
}