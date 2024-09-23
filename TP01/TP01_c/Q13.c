#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char* alteracaoAleatorioTeste(char* palavra, int inicio){
char letra1 = 'a' + abs((rand() % 26));
char letra2 = 'a' + abs((rand() % 26));

if(inicio == strlen(palavra)){
    return palavra;
}

if(palavra[inicio] == letra1){
    palavra[inicio] = letra2;
}

return alteracaoAleatorioTeste(palavra, inicio + 1);

}

char* alteracaoAleatoria(char* str){
return alteracaoAleatorioTeste(str, 0);
}

int main(void){
 unsigned int seed = 4;
srand(seed);

char string[400];
int fim = 1;

do{

fgets(string, sizeof(string), stdin);
 // Remove o caractere de nova linha, se presente
string[strcspn(string, "\n")] = '\0';
if(strcmp(string, "FIM") == 0){
    fim = 0;
}
else{
    alteracaoAleatoria(string);
    printf("%s\n", string);
}
}while(fim == 1);




return 0;
}
