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
        int contador = Pokemon.countingSort(pokemonArray);

        // Imprime os pokemons ordenados
        for (Pokemon p : pokemonArray) {
            Pokemon.imprimePokemon(p);
        }

        String matricula = "729281";
        // Pega o instante final do código
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        try (PrintWriter escritor = new PrintWriter(new FileWriter("matrícula_coutingsort.txt"))) {
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
    public static int countingSort(Pokemon[] array) {
        int tamanho = array.length;
        int contOperacao = 0;
    
        // Encontrar o maior valor no array (captureRate)
        int maximo = array[0].getCaptureRate();
        for (int i = 1; i < tamanho; i++) {
            if (array[i].getCaptureRate() > maximo) {
                maximo = array[i].getCaptureRate();
            }
        }
    
        // Criar o array de contagem
        int[] contagem = new int[maximo + 1];
    
        // Contar a frequência de cada captureRate
        for (int i = 0; i < tamanho; i++) {
            contagem[array[i].getCaptureRate()]++;
        }
    
        // Array auxiliar para armazenar os Pokémon ordenados
        Pokemon[] sortedArray = new Pokemon[tamanho];
        int indice = 0;
    
        // Modificar o array original para ordenar por captureRate
        for (int i = 0; i <= maximo; i++) {
            if (contagem[i] > 0) {
                // Criar lista de Pokémon com o mesmo captureRate
                Pokemon[] sameCaptureRatePokemons = new Pokemon[contagem[i]];
                int sameIndice = 0;
    
                // Colocar todos os Pokémon com o mesmo captureRate no array auxiliar
                for (int j = 0; j < tamanho; j++) {
                    if (array[j].getCaptureRate() == i) {
                        sameCaptureRatePokemons[sameIndice] = array[j];
                        sameIndice++;
                    }
                }
    
                // Ordenar os Pokémon com o mesmo captureRate por nome (Insertion Sort)
                insertionSortByName(sameCaptureRatePokemons);
    
                // Inserir os Pokémon ordenados de volta ao array principal
                for (int j = 0; j < sameCaptureRatePokemons.length; j++) {
                    sortedArray[indice] = sameCaptureRatePokemons[j];
                    indice++;
                    contOperacao++;
                }
            }
        }
    
        // Copiar o array ordenado de volta para o array original
        System.arraycopy(sortedArray, 0, array, 0, tamanho);
    
        return contOperacao;
    }
    
    // Função auxiliar para ordenar por nome usando Insertion Sort
    private static void insertionSortByName(Pokemon[] array) {
        for (int i = 1; i < array.length; i++) {
            Pokemon key = array[i];
            int j = i - 1;
    
            // Comparar os nomes dos Pokémon
            while (j >= 0 && array[j].getName().compareTo(key.getName()) > 0) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }
    
}
