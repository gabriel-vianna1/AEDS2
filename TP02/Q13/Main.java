import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.time.Duration;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        // Medição de tempo, pega o instante inicial
        Instant start = Instant.now();

        Scanner sc = new Scanner(System.in);
        List<Pokemon> pokedex = Pokemon.lerPokemons();
        List<Pokemon> pokemons = new ArrayList<>();
        boolean fim = true;
        int idPokemon;

        // Joga os pokemons selecionados para uma lista
        while (fim) {
            String entrada = sc.nextLine();
            if (entrada.equals("FIM")) {
                fim = false;
            } else {
                try {
                    idPokemon = Integer.parseInt(entrada);
                    // Verifica se o ID do Pokémon é válido
                    if (idPokemon >= 1 && idPokemon <= pokedex.size()) {
                        pokemons.add(Pokemon.clonePokemon(pokedex.get(idPokemon - 1)));
                    } 
                } catch (NumberFormatException e) {
                  e.printStackTrace();
                }
            }
        }
        //Como pokemon[0] não tem tamanho suficiente, o metodo  ToArray vai criar um do tamanho correto.
        Pokemon[] pokemonArray = pokemons.toArray(new Pokemon[0]);
        Pokemon.mergeSort(pokemonArray);

        // Imprime os pokemons ordenados
        for (Pokemon p : pokemonArray) {
            Pokemon.imprimePokemon(p);
        }

        int contador = Pokemon.cont; 

        String matricula = "729281";
        // Pega o instante final do código
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        try (PrintWriter escritor = new PrintWriter(new FileWriter("matrícula_mergesort.txt"))) {
            escritor.println(matricula + "\t" + duration + "\t" + contador);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sc.close();
    }
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

    public String getType(){
        return this.types.get(0);
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

    public static int cont = 0;

    public static void mergeSort(Pokemon[] array) {
		
		int length = array.length;
		if (length <= 1) return; //base case
		
		int middle = length / 2;
		Pokemon[] leftArray = new Pokemon[middle];
		Pokemon[] rightArray = new Pokemon[length - middle];
		
		int i = 0; //left array
		int j = 0; //right array
		
		for(; i < length; i++) {
			if(i < middle) {
				leftArray[i] = Pokemon.clonePokemon(array[i]);
			}
			else {
				rightArray[j] = Pokemon.clonePokemon(array[i]);
				j++;
			}
		}
		mergeSort(leftArray);
		mergeSort(rightArray);
		merge(leftArray, rightArray, array);
	}
	
	public static void merge(Pokemon[] leftArray, Pokemon[] rightArray, Pokemon[] array) {
		int leftSize = array.length / 2;
		int rightSize = array.length - leftSize;
		int i = 0, l = 0, r = 0; //indices
		
		//check the conditions for merging
		while(l < leftSize && r < rightSize) {
			if(leftArray[l].getType().compareTo(rightArray[r].getType()) < 0) {
				array[i] = Pokemon.clonePokemon(leftArray[l]);
				i++;
				l++;
                cont++;
			}
			else if(leftArray[l].getType().compareTo(rightArray[r].getType()) > 0 ){
				array[i] = Pokemon.clonePokemon(rightArray[r]);
				i++;
				r++;
                cont++;
			}
            else{
                if(leftArray[l].getName().compareTo(rightArray[r].getName()) < 0){
                    array[i] = Pokemon.clonePokemon(leftArray[l]);
				    i++;
				    l++;
                    cont++;
                }
                else{
                    array[i] = Pokemon.clonePokemon(rightArray[r]);
				    i++;
				    r++;
                    cont++;
                }
            } 

		}
		while(l < leftSize) {
			array[i] = Pokemon.clonePokemon(leftArray[l]);
			i++;
			l++;
            cont++;
		}
		while(r < rightSize) {
			array[i] = Pokemon.clonePokemon(rightArray[r]);
			i++;
			r++;
            cont++;
		}
	} 
}