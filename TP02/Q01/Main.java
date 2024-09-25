package Q01;
import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args){

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

    public List<String> getType(){
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
        clone.types = p.getType();
        clone.abilities = p.getAbilities();
        clone.weight = p.getWeight();
        clone.height = p.getHeight();
        clone.isLegendary = p.getIsLegendary();
        clone.captureRate = p.getCaptureRate();
        clone.captureDate = p.getCaptureDate();

        return clone;
    }
    
    // Implementação do código para ler os pokemons do arquivo csv
    public void lerPokemons(){
     
     try(BufferedReader leitor = new BufferedReader(new FileReader("pokemon.csv"))){
       
        String linha;

        while((linha = leitor.readLine()) != null){
        
            // O parametro dentro do split é para separar das aspas duplas
            String[] linhaSeparada = linha.split("\"");
            String abilities = linhaSeparada[1];
            // Parametro 1 pq, as " dividem em apenas 3 strings diferentes.
        


           
            

        }

     }catch(IOException e){
        e.printStackTrace();
     }
    }

    



}