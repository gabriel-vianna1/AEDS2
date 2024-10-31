import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        Lista Pokemons =  new Lista(80);
        List<Pokemon> pokedex = Pokemon.lerPokemons();
        
        // Ler os pokemons que serão jogados p/lista
        String entrada;
        boolean fim = true;
    do{
        entrada = sc.nextLine();
        if(entrada.equals("FIM")) {fim = false;}
        else{
        
        try{

            int pokemonID = Integer.parseInt(entrada);
            Pokemons.inserirFim(pokedex.get(pokemonID - 1));

        }catch(NumberFormatException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}while(fim);

int n = sc.nextInt();
sc.nextLine(); // Limpa o buffer

for(int i = 0; i < n; i++){
String input = sc.nextLine();
String[] separar = input.split(" ");
String operacao = separar[0];

if(operacao.equals("II")){
    try{
    int id = Integer.parseInt(separar[1]);
    Pokemons.inserirInicio(pokedex.get(id - 1));
    }catch(Exception e) { e.printStackTrace(); }
}
else if(operacao.equals("IF")){
try{
int id = Integer.parseInt(separar[1]);
Pokemons.inserirFim(pokedex.get(id - 1));
}catch(Exception e) { e.printStackTrace(); }
}
else if(operacao.equals("I*")){
    try{
    int pos = Integer.parseInt(separar[1]);
    int id = Integer.parseInt(separar[2]);
    Pokemons.inserir(pokedex.get(id- 1), pos);
    }catch(Exception e) { e.printStackTrace(); }
  }
 else if(operacao.equals("RI")){
    try{
    Pokemon removido = Pokemons.removerInicio();
    System.out.println("(R) " + removido.getName());
    }catch(Exception e) { e.printStackTrace(); }
}
else if(operacao.equals("RF")){
    try{
    Pokemon removido = Pokemons.removerFim();
    System.out.println("(R) " + removido.getName());
    }catch(Exception e) { e.printStackTrace(); }
}
else if(operacao.equals("R*")){
    try{
    int pos = Integer.parseInt(separar[1]);
    Pokemon removido = Pokemons.remover(pos);
    System.out.println("(R) " + removido.getName());
    }catch(Exception e) { e.printStackTrace(); }
 }
}

while(!Pokemons.isVazia()){
try{
int i = 0;
System.out.print("[" + i + "] ");
Pokemon.imprimePokemon(Pokemons.removerInicio());
i++;
 }catch(Exception e){e.printStackTrace();}
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

        try (BufferedReader leitor = new BufferedReader(new FileReader("/tmp/pokemon.csv"))) {
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

class Lista {
   private Pokemon[] array;
   private int n;

   public Lista () {
      this(6);
   }

   public Lista (int tamanho){
      array = new Pokemon[tamanho];
      n = 0;
   }

   public void inserirInicio(Pokemon x) throws Exception {

      //validar insercao
      if(n >= array.length){
         throw new Exception("Erro ao inserir!");
      } 

      //levar elementos para o fim do array
      for(int i = n; i > 0; i--){
         array[i] = Pokemon.clonePokemon(array[i-1]);
      }

      array[0] = Pokemon.clonePokemon(x);
      n++;
   }

   public void inserirFim(Pokemon x) throws Exception {

      //validar insercao
      if(n >= array.length){
         throw new Exception("Erro ao inserir!");
      }

      array[n] = Pokemon.clonePokemon(x);
      n++;
   }

   public void inserir(Pokemon x, int pos) throws Exception {

      //validar insercao
      if(n >= array.length || pos < 0 || pos > n){
         throw new Exception("Erro ao inserir!");
      }

      //levar elementos para o fim do array
      for(int i = n; i > pos; i--){
         array[i] = Pokemon.clonePokemon(array[i-1]);
      }

      array[pos] = Pokemon.clonePokemon(x);
      n++;
   }

   public Pokemon removerInicio() throws Exception {

      //validar remocao
      if (n == 0) {
         throw new Exception("Erro ao remover!");
      }

      Pokemon resp = array[0];
      n--;

      for(int i = 0; i < n; i++){
         array[i] = Pokemon.clonePokemon(array[i+1]);
      }

      return resp;
   }

   public Pokemon removerFim() throws Exception {

      if (n == 0) {
         throw new Exception("Erro ao remover!");
      }

      return array[--n];
   }

   public Pokemon remover(int pos) throws Exception {

      //validar remocao
      if (n == 0 || pos < 0 || pos >= n) {
         throw new Exception("Erro ao remover!");
      }

      Pokemon resp = array[pos];
      n--;

      for(int i = pos; i < n; i++){
         array[i] = Pokemon.clonePokemon(array[i+1]);
      }

      return resp;
   }

   public void mostrar (){
      System.out.print("[ ");
      for(int i = 0; i < n; i++){
         System.out.print(array[i] + " ");
      }
      System.out.println("]");
   }

   public boolean pesquisar(Pokemon x) {
      boolean retorno = false;
      for (int i = 0; i < n && retorno == false; i++) {
         retorno = (array[i].getName().equals(x.getName()));
      }
      return retorno;
   }

   public boolean isVazia() {
    return n == 0;
}

}