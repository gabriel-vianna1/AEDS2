#include <stdio.h>
#include <string.h>

int isPalindromo(char str[]){
int i, j;
int tam = strlen(str);
for(i = 0, j = tam - 1; i < j; i++, j--){
    if(str[i] != str[j]){
        return 0;
    }
}
return 1;
}
int main(void){
char palavra[400];
int fim = 1;
do{
scanf("%399^\n]", palavra);
getchar();
int tamanho = strlen(palavra);
int palindromo = isPalindromo(palavra);

if(tamanho == 3 && palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M'){
    fim = 0;
}
else if(palindromo){
    printf("SIM\n");
}
else{
    printf("NAO\n");
}
}while(fim);

return 0;
}
