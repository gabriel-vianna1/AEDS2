#include <stdio.h>
#include <string.h>

// Função auxiliar recursiva para verificar se é um palíndromo
int isPalindromoTeste(char palavra[], int inicio, int fim) {
    if (inicio >= fim) {
        return 1; // É um palíndromo
    }
    if (palavra[inicio] != palavra[fim]) {
        return 0; // Não é um palíndromo
    }
    return isPalindromoTeste(palavra, inicio + 1, fim - 1);
}

// Função principal para verificar se a palavra é um palíndromo
int isPalindromo(char palavraTeste[]) {
    return isPalindromoTeste(palavraTeste, 0, strlen(palavraTeste) - 1);
}

int main(void) {
    char str[400];
    int acabar = 0;
    do {
        fgets(str, sizeof(str), stdin);

        // Remove o caractere de nova linha, se presente
        str[strcspn(str, "\n")] = '\0';

        if (strcmp(str, "FIM") == 0) {
            acabar = 1;
        } else {
            if (isPalindromo(str)) {
                printf("SIM\n");
            } else {
                printf("NAO\n");
            }
        }
    } while (acabar == 0);

    return 0;
}
