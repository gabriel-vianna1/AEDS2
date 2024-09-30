import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main{
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        boolean fim = true;
        
        Pokemon teste = new Pokemon();
        List<Pokemon> pokedex = teste.lerPokemons();

        do{
        String n = sc.nextLine();
        if(n.equals("FIM")){
            fim = false;
        }
        else{
        int idPokemon = Integer.parseInt(n);
        teste.imprimePokemon(pokedex.get(idPokemon));
        
        }

        }while(fim);

    sc.close();
    }
}

class Pokemon{

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
    public void setId(int id){
        this.id = id;
    }

    public void setGeneration(int generation){
        this.generation = generation;
    }

    public void setName(String name){
        this.name = name;
    }
    
    public void setDescription(String description){
        this.description = description;
    }

    public void setType(String type){
      types.add(type);
    }

    public void setAbility(String ability){
        abilities.add(ability);
    }

    public void setWeight(double weight){
        this.weight = weight;
    }

    public void setHeight(double height){
        this.height = height;
    }
    
    public void setCaptureRate(int captureRate){
        this.captureRate = captureRate;
    }
    
    public void setIsLegendary(boolean legendary){
        this.isLegendary = legendary;
    }

    public void setCaptureDate(LocalDate captureDate){
        this.captureDate = captureDate;
    }

    public int getId(){
        return this.id;
    }

    public int getGeneration(){
        return this.generation;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public List<String> getTypes(){
        return types;
    }

    public List<String> getAbilities(){
        return abilities;
    }

    public double getWeight(){
        return this.weight;
    }

    public double getHeight(){
        return this.height;
    }

    public int getCaptureRate(){
        return this.captureRate;
    }

    public boolean getIsLegendary(){
        return this.isLegendary;
    }

    public LocalDate getCaptureDate(){
        return this.captureDate;
    }

    //Implementação dos contrutores

    public Pokemon(){
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
    
    public Pokemon(int id, String name, int generation, String description, List<String> types, List<String> abilities, double weight, double height, boolean isLegendary,int captureRate,  LocalDate captureDate){
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

    
    // Implementação do método para clonar um pokemkon
    public Pokemon clonePokemon(Pokemon p) {
        return new Pokemon(p.getId(), p.getName(), p.getGeneration(), p.getDescription(), p.getTypes(), p.getAbilities(), p.getWeight(), p.getHeight(), p.getIsLegendary(), p.getCaptureRate(), p.getCaptureDate());
    }


    // Implementação do código para ler os pokemons do arquivo csv
    public  List<Pokemon> lerPokemons(){

        List<Pokemon> pokedex = new ArrayList<>();
     
     try(BufferedReader leitor = new BufferedReader(new FileReader("/tmp/pokemon.csv"))){
       
        String linha;
     

        while((linha = leitor.readLine()) != null){
        
            String[] atributos = linha.split(",");
            int pointerInicio = 0;
            int pointerFim = 0;

            for(int i = 0; i < atributos.length; i++){
                for(int j = 0; j < atributos[i].length(); j++){
                        if(atributos[i].charAt(j) == '['){
                            pointerInicio = i;
                        }
                        else if(atributos[i].charAt(j) == ']'){
                            pointerFim = i;
                        }
                }
            }
            // Tira os colchetes, vírgulas, aspas duplas e simples
            linha = linha.replaceAll("[\"'\\[\\]]", "").trim();
            atributos = linha.split(",");

            Pokemon p = new Pokemon();
            int cont = 0;        
            try{
            p.setId(Integer.parseInt(atributos[cont]));
            cont++;
            p.setGeneration(Integer.parseInt(atributos[cont]));
            cont++;
            p.setName(atributos[cont]);
            cont++;
            p.setDescription(atributos[cont]);
            cont++;
            p.setType(atributos[cont].trim());
            cont++;
            //checar se da erro
            if(!atributos[cont].isEmpty()){
             p.setType(atributos[cont].trim());
            }
            cont++;

           int qtdAbilites = (pointerFim - pointerInicio) + 1;
            
           for(int i = 6; i <= pointerFim; i++){
            p.setAbility(atributos[i].trim());
            
           }
        
           cont += qtdAbilites;

           p.setWeight(Double.parseDouble(atributos[cont].trim()));
           cont++;
           p.setHeight(Double.parseDouble(atributos[cont].trim()));
           cont++;
           p.setCaptureRate(Integer.parseInt(atributos[cont].trim()));
           cont++;
           if(Integer.parseInt(atributos[cont].trim()) == 0){
            p.setIsLegendary(false);
            cont++;
           }
           else{
            p.setIsLegendary(true);
            cont++;
           }
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
 
        try {
            LocalDate data = LocalDate.parse(atributos[cont].trim(), formato);
             p.setCaptureDate(data);
            // set capture date, ta no outro gpt
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

           pokedex.add(p);
        }
    
     }catch(IOException e){
        e.printStackTrace();
     }

     return pokedex;

    }

    public void imprimePokemon(Pokemon p){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = p.getCaptureDate().format(dateFormatter);

     String tiposFormatados = p.getTypes().stream().map(tipo -> "'" + tipo + "'")  // Colocando aspas simples em volta de cada tipo
     .collect(Collectors.joining(", "));  // Juntando os tipos separados por vírgula

     String abilitiesFormatadas = p.getAbilities().stream()
    .map(ability -> "'" + ability + "'")  // Colocando aspas simples em volta de cada habilidade
    .collect(Collectors.joining(", "));  // Juntando as habilidades separadas por vírgula


     System.out.println("[#" + p.getId() + " -> " + p.getName() +": " + p.getDescription() + " - " + "[" + tiposFormatados + "]" + " - " + "[" + abilitiesFormatadas + "]" + " - " + p.getWeight() + "kg" + " - " + p.getHeight() + "m" + " - " +  p.getCaptureRate() + "%" + " - " + p.getIsLegendary() + " - " + p.getGeneration() + " gen" + "]" + " - " + formattedDate);
    
    }
}
    



