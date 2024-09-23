import java.util.Scanner;
import java.io.*;
import java.net.*;
import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;

class Q07{
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        String endereco, nome;
        boolean fim = true;
        char[] letras = {'a', 'e', 'i', 'o', 'u', 'á', 'é', 'í', 'ó', 'ú', 'à', 'è', 'ì', 'ò', 'ù', 'ã', 'õ', 'â', 'ê', 'î', 'ô', 'û'};
        String[] palavras = {"consoante", "<br>", "table"};
        
        do{
        endereco = sc.nextLine();
        nome = sc.nextLine();
        int[] metodos = {isA(endereco), isE(endereco), isI(endereco), isO(endereco), isU(endereco), isÁ(endereco), isÉ(endereco), isÍ(endereco), isÓ(endereco), isÚ(endereco), isÀ(endereco), isÈ(endereco), isÒ(endereco), isÙ(endereco), isÃ(endereco), isÕ(endereco), isÂ(endereco), isÊ(endereco), isÎ(endereco), isÔ(endereco), isÛ(endereco)};
        int[] metodos2 = {isConsoante(endereco), isBR(endereco), isTable(endereco)};
        if(nome.equals("FIM")){
            fim = false;
        }
        else{
        for(int i = 0; i < 22; i++){
            System.out.print(letras[i] + "(" + metodos[i] + ") ");
        }
        for(int i = 0; i < 3; i++){
            System.out.print(palavras[i] + "(" + metodos2[i] + ") ");
        }
        }

        }while(fim);
       



        sc.close();
    }

    public static String getHtml(String endereco){
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;
  
        try {
            URI uri = new URI(endereco);
            url = uri.toURL();
           is = url.openStream();  // throws an IOException
           br = new BufferedReader(new InputStreamReader(is));
  
           while ((line = br.readLine()) != null) {
              resp += line + "\n";
           }
        } catch (MalformedURLException mue) {
           mue.printStackTrace();
        } catch (IOException ioe) {
           ioe.printStackTrace();
        } 
        catch (URISyntaxException e) {
            System.out.println("Erro de sintaxe na URI: " + e.getMessage());
        }
        try {
           is.close();
        } catch (IOException ioe) {
           // nothing to see here
  
        }
  
        return resp;
     }

    public static int isA( String endereco){
        endereco = getHtml(endereco);
        int cont = 0;
        int tam = endereco.length();
        for(int i = 0; i < tam; i++){
            if(endereco.charAt(i) == 'a' || endereco.charAt(i) == 'A');
            cont++; 
        }
    return cont;
}

    public static int isE(String endereco){
        endereco = getHtml(endereco);
        int cont = 0;
        int tam = endereco.length();
        for(int i = 0; i < tam; i++){
            if(endereco.charAt(i) == 'e' || endereco.charAt(i) == 'E')
            cont++; 
        }
    return cont;
    }
    public static int isI(String endereco){
        endereco = getHtml(endereco);
        int cont = 0;
        int tam = endereco.length();
        for(int i = 0; i < tam; i++){
            if(endereco.charAt(i) == 'i' || endereco.charAt(i) == 'I')
            cont++; 
        }
    return cont;
    }
    public static int isO( String endereco){
        endereco = getHtml(endereco);
        int cont = 0;
        int tam = endereco.length();
        for(int i = 0; i < tam; i++){
            if(endereco.charAt(i) == 'o' || endereco.charAt(i) == 'O')
            cont++; 
        }
    return cont;
    }
    public static int isU(String endereco){
        endereco = getHtml(endereco);
        int cont = 0;
        int tam = endereco.length();
        for(int i = 0; i < tam; i++){
            if(endereco.charAt(i) == 'u' || endereco.charAt(i) == 'U')
            cont++; 
        }
    return cont;
    }

    public static int isÁ(String endereco){
        endereco = getHtml(endereco);
        int cont = 0;
        int tam = endereco.length();
        for(int i = 0; i < tam; i++){
            if(endereco.charAt(i) == 'á' || endereco.charAt(i) == 'Á');
            cont++; 
        }
    return cont;
}
public static int isÉ(String endereco){
    endereco = getHtml(endereco);
    int cont = 0;
    int tam = endereco.length();
    for(int i = 0; i < tam; i++){
        if(endereco.charAt(i) == 'é' || endereco.charAt(i) == 'É');
        cont++; 
    }
return cont;
}
public static int isÍ(String endereco){
    endereco = getHtml(endereco);
    int cont = 0;
    int tam = endereco.length();
    for(int i = 0; i < tam; i++){
        if(endereco.charAt(i) == 'í' || endereco.charAt(i) == 'Í');
        cont++; 
    }
return cont;
}
public static int isÓ(String endereco){
    endereco = getHtml(endereco);
    int cont = 0;
    int tam = endereco.length();
    for(int i = 0; i < tam; i++){
        if(endereco.charAt(i) == 'ó' || endereco.charAt(i) == 'Ó');
        cont++; 
    }
return cont;
}
public static int isÚ(String endereco){
    endereco = getHtml(endereco);
    int cont = 0;
    int tam = endereco.length();
    for(int i = 0; i < tam; i++){
        if(endereco.charAt(i) == 'ú' || endereco.charAt(i) == 'Ú');
        cont++; 
    }
return cont;
}
public static int isÀ(String endereco){
    endereco = getHtml(endereco);
    int cont = 0;
    int tam = endereco.length();
    for(int i = 0; i < tam; i++){
        if(endereco.charAt(i) == 'À' || endereco.charAt(i) == 'à');
        cont++; 
    }
return cont;
}
public static int isÈ(String endereco){
 endereco = getHtml(endereco);
int cont = 0;
int tam = endereco.length();
for(int i = 0; i < tam; i++){
    if(endereco.charAt(i) == 'è' || endereco.charAt(i) == 'È');
    cont++; 
}
return cont;
}
public static int isÌ(String endereco){
    endereco = getHtml(endereco);
    int cont = 0;
int tam = endereco.length();
for(int i = 0; i < tam; i++){
    if(endereco.charAt(i) == 'ì' || endereco.charAt(i) == 'Ì');
    cont++; 
}
return cont;
}
public static int isÒ(String endereco){
    endereco = getHtml(endereco);
    int cont = 0;
int tam = endereco.length();
for(int i = 0; i < tam; i++){
    if(endereco.charAt(i) == 'ò' || endereco.charAt(i) == 'Ò');
    cont++; 
}
return cont;
}
public static int isÙ(String endereco){
    endereco = getHtml(endereco);
    int cont = 0;
int tam = endereco.length();
for(int i = 0; i < tam; i++){
    if(endereco.charAt(i) == 'ù' || endereco.charAt(i) == 'Ù');
    cont++; 
}
return cont;
}

public static int isÃ(String endereco){
    endereco = getHtml(endereco);
    int cont = 0;
int tam = endereco.length();
for(int i = 0; i < tam; i++){
    if(endereco.charAt(i) == 'ã' || endereco.charAt(i) == 'Ã');
    cont++; 
}
return cont;
}

public static int isÕ(String endereco){
    endereco = getHtml(endereco);
    int cont = 0;
    int tam = endereco.length();
    for(int i = 0; i < tam; i++){
        if(endereco.charAt(i) == 'õ' || endereco.charAt(i) == 'Õ');
        cont++; 
    }
    return cont;
    }

    public static int isÂ(String endereco){
        endereco = getHtml(endereco);
        int cont = 0;
        int tam = endereco.length();
        for(int i = 0; i < tam; i++){
            if(endereco.charAt(i) == 'â' || endereco.charAt(i) == 'Â');
            cont++; 
        }
    return cont;
    }
    public static int isÊ(String endereco){
        endereco = getHtml(endereco);
        int cont = 0;
    int tam = endereco.length();
    for(int i = 0; i < tam; i++){
        if(endereco.charAt(i) == 'ê' || endereco.charAt(i) == 'Ê');
        cont++; 
    }
    return cont;
    }
    public static int isÎ(String endereco){
        endereco = getHtml(endereco);
        int cont = 0;
    int tam = endereco.length();
    for(int i = 0; i < tam; i++){
        if(endereco.charAt(i) == 'î' || endereco.charAt(i) == 'Î');
        cont++; 
    }
    return cont;
    }
    public static int isÔ(String endereco){
        endereco = getHtml(endereco);
        int cont = 0;
    int tam = endereco.length();
    for(int i = 0; i < tam; i++){
        if(endereco.charAt(i) == 'ô' || endereco.charAt(i) == 'Ô');
        cont++; 
    }
    return cont;
    }
    public static int isÛ(String endereco){
        endereco = getHtml(endereco);
        int cont = 0;
    int tam = endereco.length();
    for(int i = 0; i < tam; i++){
        if(endereco.charAt(i) == 'û' || endereco.charAt(i) == 'Û');
        cont++; 
    }
    return cont;
    }

public static int isConsoante(String endereco){
    endereco = getHtml(endereco);
    int tam = endereco.length();
        int cont = 0;
        char letra;
        for(int i = 0; i < tam; i++){
            letra = endereco.charAt(i);
            if(endereco.charAt(i) != 'a' && endereco.charAt(i) != 'e' && endereco.charAt(i) != 'i' && endereco.charAt(i) != 'o' && endereco.charAt(i) != 'u' && endereco.charAt(i) != 'A' && endereco.charAt(i) != 'E' && endereco.charAt(i) != 'I' && endereco.charAt(i) != 'O' && endereco.charAt(i) != 'U' && !Character.isDigit(letra)){
                cont++;
            } 
        }
        return cont;
}

public static int isBR(String endereco){
    endereco = getHtml(endereco);
    int tam = endereco.length();
        int cont = 0;

        for(int i = 0; i < tam; i++){
            
            if(endereco.charAt(i) == '<' && endereco.charAt(i + 1) == 'b' && endereco.charAt(i + 2) == 'r' && endereco.charAt(i + 3) == '\\' && endereco.charAt(i + 4) == '>'){
                cont++;
            } 
        }
        return cont;
}
public static int isTable(String endereco){
    endereco = getHtml(endereco);
    int tam = endereco.length();
        int cont = 0;
        for(int i = 0; i < tam; i++){
            
            if(endereco.charAt(i) == '<' && endereco.charAt(i + 1) == 't' && endereco.charAt(i + 2) == 'a' && endereco.charAt(i + 3) == 'b' && endereco.charAt(i) == 'l' && endereco.charAt(i) == 'e' && endereco.charAt(i) == '>' ){
                cont++;
            } 
        }
        return cont;
}


}