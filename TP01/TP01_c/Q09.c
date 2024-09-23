    #include <stdio.h>
#include <stdlib.h>

int main() {
    int quantidade;
    
    // Leitura da quantidade de números
    scanf("%d", &quantidade);
    
    // Abrindo o arquivo para escrita
    FILE *file = fopen("valores.txt", "w");
    if (file == NULL) {
        perror("Erro ao abrir o arquivo para escrita");
        return 1;
    }
    
    // Leitura dos valores e escrita no arquivo
  
    for (int i = 0; i < quantidade; i++) {
        double valor;
        scanf("%lf", &valor);
        fprintf(file, "%.3lf\n", valor); // Escreve o valor com 3 casas decimais
    }
    
    fclose(file);
    
    // Abrindo o arquivo para leitura
    file = fopen("valores.txt", "r");
    if (file == NULL) {
        perror("Erro ao abrir o arquivo para leitura");
        return 1;
    }
    
    // Leitura dos valores e armazenamento em um array
    double *valores = malloc(quantidade * sizeof(double));
    if (valores == NULL) {
        perror("Erro ao alocar memória");
        fclose(file);
        return 1;
    }
    
    for (int i = 0; i < quantidade; i++) {
        fscanf(file, "%lf", &valores[i]);
    }
    
    fclose(file);
    
    // Impressão dos valores em ordem reversa
    for (int i = quantidade - 1; i >= 0; i--) {
        double valor = valores[i];
        if ((int)valor == valor) {
            printf("%d\n", (int)valor);
        } else {
            printf("%g\n", valor);
        }
    }
    
    free(valores);
    
    return 0;
}