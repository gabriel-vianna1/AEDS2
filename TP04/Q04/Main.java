import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.time.Duration;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Instant start = Instant.now();

        List<Pokemon> pokedex = Pokemon.lerPokemons();
        Alvinegra arvore = new Alvinegra();

        boolean fim = true;
        do{
            String entrada = sc.nextLine();
            
            if(entrada.equals("FIM")){fim = false;}

            else{
                        
                try{
                 int id = Integer.parseInt(entrada);
                 Pokemon inserido = pokedex.get(id - 1);
                 arvore.inserir(inserido);
                }catch(NumberFormatException e){e.printStackTrace();
                }catch(Exception e){e.printStackTrace();}
            }
        }while(fim);

        fim = true;
        do{
            String entrada = sc.nextLine();

            if(entrada.equals("FIM")){fim = false;}
            else{
                System.out.println(entrada);
                boolean achou = arvore.pesquisar(entrada);

                if(achou){System.out.println("SIM");}
                else{System.out.println("NAO");}
            }

        }while(fim);

        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        String matricula = "729281";

        try (PrintWriter escritor = new PrintWriter(new FileWriter("matrícula_arvoreAlvinegra.txt"))) {
            escritor.println(matricula + "\t" + duration + "\t");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
       
sc.close(); }
}

class Pokemon {
    private int id;
    private int generation;
    private String name;
    private String description;
    private List<String> types;
    private List<String> abilities;
    private double weight;
    private double height;
    private int captureRate;
    private boolean isLegendary;
    private LocalDate captureDate;

    // Implementação dos métodos gets e sets de todos os atributos
    public void setId(int id) {
        this.id = id;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        types.add(type);
    }

    public void setAbility(String ability) {
        abilities.add(ability);
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    
    public void setCaptureRate(int captureRate) {
        this.captureRate = captureRate;
    }
    
    public void setIsLegendary(boolean legendary) {
        this.isLegendary = legendary;
    }

    public void setCaptureDate(LocalDate captureDate) {
        this.captureDate = captureDate;
    }

    public int getId() {
        return this.id;
    }

    public int getGeneration() {
        return this.generation;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public List<String> getTypes() {
        return types;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getHeight() {
        return this.height;
    }

    public int getCaptureRate() {
        return this.captureRate;
    }

    public boolean getIsLegendary() {
        return this.isLegendary;
    }

    public LocalDate getCaptureDate() {
        return this.captureDate;
    }

    // Implementação dos construtores
    public Pokemon() {
        this.id = 0;
        this.name = "";
        this.generation = 1;
        this.description = "";
        this.weight = 0;
        this.height = 0;
        this.captureRate = 0;
        this.isLegendary = false;
        this.types = new ArrayList<>();
        this.abilities = new ArrayList<>();
    }
    
    public Pokemon(int id, String name, int generation, String description, List<String> types, List<String> abilities, double weight, double height, boolean isLegendary, int captureRate, LocalDate captureDate) {
        this.id = id;
        this.name = name;
        this.generation = generation;
        this.description = description;
        this.types = types;
        this.abilities = abilities;
        this.weight = weight;
        this.height = height;
        this.isLegendary = isLegendary;
        this.captureRate = captureRate;
        this.captureDate = captureDate;
    }

    // Implementação do método para clonar um Pokémon
    public static Pokemon clonePokemon(Pokemon p) {
        return new Pokemon(p.getId(), p.getName(), p.getGeneration(), p.getDescription(), p.getTypes(), p.getAbilities(), p.getWeight(), p.getHeight(), p.getIsLegendary(), p.getCaptureRate(), p.getCaptureDate());
    }

    // Implementação do código para ler os Pokémons do arquivo CSV
    public static List<Pokemon> lerPokemons() {
        List<Pokemon> pokedex = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader("pokemon.csv"))) {
            String linha = leitor.readLine();
            
            while ((linha = leitor.readLine()) != null) {
                String[] atributos = linha.split(",");
                int pointerInicio = 0;
                int pointerFim = 0;

                for (int i = 0; i < atributos.length; i++) {
                    for (int j = 0; j < atributos[i].length(); j++) {
                        if (atributos[i].charAt(j) == '[') {
                            pointerInicio = i;
                        } else if (atributos[i].charAt(j) == ']') {
                            pointerFim = i;
                        }
                    }
                }
                // Tira os colchetes, vírgulas, aspas duplas e simples
                linha = linha.replaceAll("[\"'\\[\\]]", "").trim();
                atributos = linha.split(",");

                Pokemon p = new Pokemon();
                int cont = 0;        
                try {
                    p.setId(Integer.parseInt(atributos[cont].trim()));
                    cont++;
                    p.setGeneration(Integer.parseInt(atributos[cont].trim()));
                    cont++;
                    p.setName(atributos[cont].trim());
                    cont++;
                    p.setDescription(atributos[cont].trim());
                    cont++;
                    p.setType(atributos[cont].trim());
                    cont++;
                    
                    // Checar se da erro
                    if (!atributos[cont].isEmpty()) {
                        p.setType(atributos[cont].trim());
                    }
                    cont++;

                    int qtdAbilites = (pointerFim - pointerInicio) + 1;

                    for (int i = 6; i <= pointerFim; i++) {
                        p.setAbility(atributos[i].trim());
                    }
                    
                    cont += qtdAbilites;
                    
                    if (!(atributos[cont].isEmpty())) {
                        p.setWeight(Double.parseDouble(atributos[cont].trim()));
                        cont++;    
                    } else {
                        p.setWeight(0);
                        cont++;
                    }

                    if (!(atributos[cont].isEmpty())) {
                        p.setHeight(Double.parseDouble(atributos[cont].trim()));
                        cont++;
                    } else {
                        p.setHeight(0);
                        cont++;
                    }

                    if (!(atributos[cont].isEmpty())) {
                        p.setCaptureRate(Integer.parseInt(atributos[cont].trim()));
                        cont++;
                    } else {
                        p.setCaptureRate(0);
                        cont++;
                    }

                    if (atributos[cont].equals("0")) {
                        p.setIsLegendary(false);
                        cont++;
                    } else {
                        p.setIsLegendary(true);
                        cont++;
                    }
                  
                    if (!(atributos[cont].isEmpty())) {
                        LocalDate data = Pokemon.parseDate(atributos[cont].trim());
                        p.setCaptureDate(data);
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                pokedex.add(p);
            }
        
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pokedex;
    }

    private static LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateStr, formatter);
    }
    
    public static void imprimePokemon(Pokemon p){
        
        String formattedDate = "Nulo";

    if (p.getCaptureDate() != null) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            formattedDate = p.getCaptureDate().format(dateFormatter);
        }

     String tiposFormatados = p.getTypes().stream().map(tipo -> "'" + tipo + "'")  // Colocando aspas simples em volta de cada tipo
     .collect(Collectors.joining(", "));  // Juntando os tipos separados por vírgula

     String abilitiesFormatadas = p.getAbilities().stream()
    .map(ability -> "'" + ability + "'")  // Colocando aspas simples em volta de cada habilidade
    .collect(Collectors.joining(", "));  // Juntando as habilidades separadas por vírgula


     System.out.print("[#" + p.getId() + " -> " + p.getName() +": " + p.getDescription() + " - " + "[" + tiposFormatados + "]" + " - " + "[" + abilitiesFormatadas + "]" + " - " + p.getWeight() + "kg" + " - " + p.getHeight() + "m" + " - " +  p.getCaptureRate() + "%" + " - ");
     System.out.println(p.getIsLegendary() + " - " + p.getGeneration() + " gen" + "]" + " - " + formattedDate);
    
    }

}


class NoAN {
    public boolean cor;
    public Pokemon elemento;
    public NoAN esq, dir;
  
    public NoAN() {
     
    }
  
    public NoAN(Pokemon elemento) {
      this(elemento, false, null, null);
    }
  
    public NoAN(Pokemon elemento, boolean cor) {
      this(elemento, cor, null, null);
    }
  
    public NoAN(Pokemon elemento, boolean cor, NoAN esq, NoAN dir) {
      this.cor = cor;
      this.elemento = Pokemon.clonePokemon(elemento);
      this.esq = esq;
      this.dir = dir;
    }
  }

   class Alvinegra {
    private NoAN raiz; // Raiz da arvore.

    public Alvinegra() {
       raiz = null;
    }

    public boolean pesquisar(String elemento) {
       return pesquisar(elemento, raiz);
    }
 
    private boolean pesquisar(String elemento, NoAN i) {
       boolean resp;
       if (i == null) {
          resp = false;
       } else if (elemento.compareTo(i.elemento.getName()) == 0) {
          resp = true;
       } else if (elemento.compareTo(i.elemento.getName()) < 0) {
        System.out.println(" esq"); 
        resp = pesquisar(elemento, i.esq);
       } else {
           System.out.println(" dir");       
          resp = pesquisar(elemento, i.dir);
       }
       return resp;
    }
  
    public void inserir(Pokemon elemento) throws Exception {
       // Se a arvore estiver vazia
       if (raiz == null) {
          raiz = new NoAN(elemento);
 
       // Senao, se a arvore tiver um elemento
       } else if (raiz.esq == null && raiz.dir == null) {
          if (elemento.getName().compareTo(raiz.elemento.getName()) < 0) {
             raiz.esq = new NoAN(elemento);
          } else {
             raiz.dir = new NoAN(elemento);
          }
 
       // Senao, se a arvore tiver dois elementos (raiz e dir)
       } else if (raiz.esq == null) {
          if (elemento.getName().compareTo(raiz.elemento.getName()) < 0) {
             raiz.esq = new NoAN(elemento);
             
          } else if (elemento.getName().compareTo(raiz.elemento.getName()) > 0) {
             raiz.esq = new NoAN(raiz.elemento);
             raiz.elemento = elemento;
 
          } else {
             raiz.esq = new NoAN(raiz.elemento);
             raiz.elemento = raiz.dir.elemento;
             raiz.dir.elemento = elemento;
          }
          raiz.esq.cor = raiz.dir.cor = false;
 
       // Senao, se a arvore tiver dois elementos (raiz e esq)
       } else if (raiz.dir == null) {
          if (elemento.getName().compareTo(raiz.elemento.getName()) > 0) {
             raiz.dir = new NoAN(elemento);
 
          } else if (elemento.getName().compareTo(raiz.elemento.getName()) < 0) {
             raiz.dir = new NoAN(raiz.elemento);
             raiz.elemento = elemento;
 
          } else {
             raiz.dir = new NoAN(raiz.elemento);
             raiz.elemento = raiz.esq.elemento;
             raiz.esq.elemento = elemento;
          }
          raiz.esq.cor = raiz.dir.cor = false;
 
       // Senao, a arvore tem tres ou mais elementos
       } else {
          inserir(elemento, null, null, null, raiz);
       }
       raiz.cor = false;
    }
 
    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
       // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
       if (pai.cor == true) {
          // 4 tipos de reequilibrios e acoplamento
          if (pai.elemento.getName().compareTo(avo.elemento.getName()) > 0) { // rotacao a esquerda ou direita-esquerda
            if (i.elemento.getName().compareTo(pai.elemento.getName()) > 0){
                avo = rotacaoEsq(avo);
             } else {
                avo = rotacaoDirEsq(avo);
             }
          } else { // rotacao a direita ou esquerda-direita
             if (i.elemento.getName().compareTo(pai.elemento.getName()) < 0) {
                avo = rotacaoDir(avo);
             } else {
                avo = rotacaoEsqDir(avo);
             }
          }
          if (bisavo == null) {
             raiz = avo;
          } else if (avo.elemento.getName().compareTo(bisavo.elemento.getName()) < 0) {
             bisavo.esq = avo;
          } else {
             bisavo.dir = avo;
          }
          // reestabelecer as cores apos a rotacao
          avo.cor = false;
          avo.esq.cor = avo.dir.cor = true;
       } // if(pai.cor == true)
    }
 
    private void inserir(Pokemon elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
       if (i == null) {
          if (elemento.getName().compareTo(pai.elemento.getName()) < 0) {
             i = pai.esq = new NoAN(elemento, true);
          } else {
             i = pai.dir = new NoAN(elemento, true);
          }
          if (pai.cor == true) {
             balancear(bisavo, avo, pai, i);
          }
       } else {

          if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
             i.cor = true;
             i.esq.cor = i.dir.cor = false;
             if (i == raiz) {
                i.cor = false;
             } else if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
             }
          }
          if (elemento.getName().compareTo(i.elemento.getName()) < 0) {
             inserir(elemento, avo, pai, i, i.esq);
          } else if (elemento.getName().compareTo(i.elemento.getName()) > 0) {
             inserir(elemento, avo, pai, i, i.dir);
          } else {
             throw new Exception("Erro inserir (elemento repetido)!");
          }
       }
    }
 
    private NoAN rotacaoDir(NoAN no) {
       NoAN noEsq = no.esq;
       NoAN noEsqDir = noEsq.dir;
 
       noEsq.dir = no;
       no.esq = noEsqDir;
 
       return noEsq;
    }
 
    private NoAN rotacaoEsq(NoAN no) {
       NoAN noDir = no.dir;
       NoAN noDirEsq = noDir.esq;
 
       noDir.esq = no;
       no.dir = noDirEsq;
       return noDir;
    }
 
    private NoAN rotacaoDirEsq(NoAN no) {
       no.dir = rotacaoDir(no.dir);
       return rotacaoEsq(no);
    }
 
    private NoAN rotacaoEsqDir(NoAN no) {
       no.esq = rotacaoEsq(no.esq);
       return rotacaoDir(no);
    }
 }