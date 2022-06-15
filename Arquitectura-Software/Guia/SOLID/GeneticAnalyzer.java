public class GeneticAnalyzer {
    private String genome;
    private String[] genes;

    public void loadGeneticFile(String filename) {
        System.out.println("Loading genome data from " + filename);
    }

    public void lookupGenes(String[] referenceGenes) {
        if(genome == null)
            throw new Exception("The genome is not loaded");
        System.out.println("Identifying genes in genome");
    }

    public void displayGenes() {
        if(genes == null)
            throw new Exception("No gene data found");
        System.out.println("The following genes were found in the genome:");
    }
}