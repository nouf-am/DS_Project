/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_project;

/**
 *
 * @author noufalmansour
 */
import java.util.*;
public class Main {
    public static void main(String[] args) {
        InvertedIndexUsingList listIndex = new InvertedIndexUsingList();
        InvertedIndexUsingBST bstIndex = new InvertedIndexUsingBST();
        Reading reader = new Reading();

        // Load stop words and dataset
        System.out.println("Loading stop words and dataset...");
        reader.loadStopWords("/Users/noufalmansour/Downloads/data/stop.txt");

        long startTime, endTime;

        // Measure time for building List-based Index
        startTime = System.currentTimeMillis();
        reader.loadDocuments("/Users/noufalmansour/Downloads/data/dataset.csv", listIndex, new InvertedIndexUsingBST());
        endTime = System.currentTimeMillis();
        System.out.println("Time to build List-based Inverted Index: " + (endTime - startTime) + " ms");

        // Measure time for building BST-based Index
        startTime = System.currentTimeMillis();
        reader.loadDocuments("/Users/noufalmansour/Downloads/data/dataset.csv", new InvertedIndexUsingList(), bstIndex);
        endTime = System.currentTimeMillis();
        System.out.println("Time to build BST-based Inverted Index: " + (endTime - startTime) + " ms");

        QueryProcessing qp = new QueryProcessing(reader.getDocumentMap());
        Scanner scanner = new Scanner(System.in);
        String choice;

        System.out.println("################### Search Engine ####################");
        do {
            System.out.println("\nChoose an option:");
            System.out.println("1. Boolean Retrieval (List)");
            System.out.println("2. Boolean Retrieval (BST)");
            System.out.println("3. Ranked Retrieval");
            System.out.println("4. Indexed Documents");
            System.out.println("5. Indexed Tokens");
            System.out.println("q. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleBooleanRetrieval(scanner, qp, listIndex, true);
                    break;
                case "2":
                    handleBooleanRetrieval(scanner, qp, bstIndex, false);
                    break;
                case "3":
                    handleRankedRetrieval(scanner, qp);
                    break;
                case "4":
                    showIndexedDocuments(listIndex, bstIndex);
                    break;
                case "5":
                    showIndexedTokens(listIndex, bstIndex);
                    break;
                case "q":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (!choice.equalsIgnoreCase("q"));

        scanner.close();
    }

    private static void handleBooleanRetrieval(Scanner scanner, QueryProcessing qp, Object index, boolean isList) {
        System.out.println("\n################### Boolean Retrieval ####################");
        System.out.print("\nEnter your query (e.g., term1 AND term2): ");
        String query = scanner.nextLine();

        long startTime = System.currentTimeMillis();
        Set<Integer> result = isList
                ? qp.evaluateBooleanQueryList((InvertedIndexUsingList) index, query)
                : qp.evaluateBooleanQueryBST((InvertedIndexUsingBST) index, query);
        long endTime = System.currentTimeMillis();

        System.out.println("Result doc IDs: " + result);
        System.out.println("Time taken for query processing: " + (endTime - startTime) + " ms");
    }

    private static void handleRankedRetrieval(Scanner scanner, QueryProcessing qp) {
        System.out.println("\n################### Ranked Retrieval ###########################");
        System.out.print("\nEnter your query terms (space-separated): ");
        String[] terms = scanner.nextLine().split("\\s+");

        long startTime = System.currentTimeMillis();
        Map<Integer, Integer> results = qp.rankedQuery(terms);
        long endTime = System.currentTimeMillis();

        System.out.println("DocID\tScore");
        for (Map.Entry<Integer, Integer> entry : results.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
        System.out.println("Time taken for ranked retrieval: " + (endTime - startTime) + " ms");
    }

    private static void showIndexedDocuments(InvertedIndexUsingList listIndex, InvertedIndexUsingBST bstIndex) {
        System.out.println("\nNumber of Indexed Documents (List): " + listIndex.countDocuments());
        System.out.println("Number of Indexed Documents (BST): " + bstIndex.countDocuments());
    }

    private static void showIndexedTokens(InvertedIndexUsingList listIndex, InvertedIndexUsingBST bstIndex) {
        System.out.println("\nNumber of Indexed Tokens (List): " + listIndex.countTokens());
        System.out.println("Number of Indexed Tokens (BST): " + bstIndex.countTokens());
    }
}
