package Q01;
import java.util.*;
import java.io.*;
import java.text.*;
import java.util.stream.Collectors;

public class Main{
    public static void main(String[] args){

        List<Pokemon> pokedex = new ArrayList<>();

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
    private Date captureDate;

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

    public void setCaptureDate(Date captureDate){
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

    public Date getCaptureDate(){
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
    }
    
    public Pokemon(int id, String name, int generation, String description, List<String> types, List<String> abilities, double weight, double height, boolean isLegendary,int captureRate,  Date captureDate){
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
    public Pokemon clonePokemon(Pokemon p){
        
        Pokemon clone = new Pokemon();
        clone.id = p.getId();
        clone.name = p.getName();
        clone.description = p.getDescription();
        clone.generation = p.getGeneration();
        clone.types = p.getTypes();
        clone.abilities = p.getAbilities();
        clone.weight = p.getWeight();
        clone.height = p.getHeight();
        clone.isLegendary = p.getIsLegendary();
        clone.captureRate = p.getCaptureRate();
        clone.captureDate = p.getCaptureDate();

        return clone;
    }

    // Implementação do código para ler os pokemons do arquivo csv
    public void lerPokemons(List<Pokemon> pokedex){
     
     try(BufferedReader leitor = new BufferedReader(new FileReader("/tmp/pokemon.csv"))){
       
        String linha;
     

        while((linha = leitor.readLine()) != null){
        
            // O parametro dentro do split é para separar das aspas duplas
            String[] linhaSeparada = linha.split("\"");
            String aux = linhaSeparada[1];
            // Parametro 1 pq, as " dividem em apenas 3 strings diferentes.
          
          
            aux = aux.replaceAll("\\[", "").replaceAll("\\]", "").trim();  

            List<String> abilitiesP = new ArrayList<>();
            String[] aux2 = aux.split(",");
            for(int i = 0; i < aux2.length; i++){
                abilitiesP.add(aux2[i]);
            }
            
            List<String> atributos = new ArrayList<>();

            for(int i = 0; i < linhaSeparada.length; i++){

                String[] linhaSeparada2 = linhaSeparada[i].split(",");

                for(String atributo : linhaSeparada2){

                    atributos.add(atributo);

                }
            }
            
         int idP = Integer.parseInt(atributos.get(0));
         int generationP = Integer.parseInt(atributos.get(1));
         String nomeP = atributos.get(2);
         String descriptionP = atributos.get(3);

         List<String> typesP = new ArrayList<>();
         typesP.add(atributos.get(4));
         
         if(atributos.get(5) != null){
         typesP.add(atributos.get(5));   
         }

   

        int n = atributos.size();
        
        // Transformar uma string na formatação de data em uma classe Date
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date captureDateP = null;
        try{
         captureDateP = formato.parse(atributos.get(n - 1));


        }catch(ParseException e){
            e.printStackTrace();
        }

        int isLegendaryAux = Integer.parseInt(atributos.get(n - 2));
        boolean isLegendaryP;
        if(isLegendaryAux == 0)
        isLegendaryP = false;
        else
        isLegendaryP = true;
        
        int captureRateP = Integer.parseInt(atributos.get(n - 3));
        double heightP = Double.parseDouble(atributos.get(n - 4));
        double weightP = Double.parseDouble(atributos.get(n - 5));

        Pokemon p = new Pokemon(idP, nomeP, generationP, descriptionP, typesP, abilitiesP, weightP, heightP, isLegendaryP, captureRateP, captureDateP);
        pokedex.add(p);

        }

     }catch(IOException e){
        e.printStackTrace();
     }
    }

    public void imprimePokemon(Pokemon p){
    
     String tiposFormatados = p.getTypes().stream().map(tipo -> "'" + tipo + "'")  // Colocando aspas simples em volta de cada tipo
     .collect(Collectors.joining(", "));  // Juntando os tipos separados por vírgula

     System.out.println("[#" + p.getId() + " ->" + p.getName() +": " + p.getDescription() + " - " + "[" + tiposFormatados + "]" + " - " + "[" + p.getAbilities() + "]" + " - " + p.getWeight() + "kg" + " - " + p.getHeight() + "m" + " - " +  p.getCaptureRate() + "%" + " - " + p.getIsLegendary() + " - " + p.getGeneration() + " gen" + "]" + " - " + p.getCaptureDate());
    
    }
}
    



