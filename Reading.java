/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_project;

/**
 *
 * @author noufalmansour
 */
import java.io.*;
import java.util.*;

public class Reading {
    private Set<String> stopWords;
    private Map<Integer, String> documentMap; // Store document content by ID

    public Reading() {
        stopWords = new HashSet<>();
        documentMap = new HashMap<>();
    }

    public Map<Integer, String> getDocumentMap() {
        return documentMap;
    }

    public void loadStopWords(String stopFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(stopFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                stopWords.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDocuments(String datasetFile, InvertedIndexUsingList listIndex, InvertedIndexUsingBST bstIndex) {
        try (BufferedReader br = new BufferedReader(new FileReader(datasetFile))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2); // Split into doc ID and content
                if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                    // Skip malformed lines
                    continue;
                }
                int docId = Integer.parseInt(parts[0].trim()); // Parse document ID
                String content = parts[1].trim().toLowerCase();
                content = content.replaceAll("[\"']","") // Remove double quotes and single quotes
		                .replaceAll("[,.;:!?()]", " ") // Replace punctuation with spaces
		                .replaceAll("\\s+", " ") // Replace multiple spaces with a single space
		                .trim() // Remove leading and trailing spaces
		                .toLowerCase();
                documentMap.put(docId, content); // Store content in documentMap
                processLine(content, docId, listIndex, bstIndex);
            }
        } catch (IOException e) {
            System.err.println("Error reading dataset file: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Invalid document ID format: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processLine(String line, int docId, InvertedIndexUsingList listIndex, InvertedIndexUsingBST bstIndex) {
    	String[] words = line.replaceAll("[,.;:!?()]", " ") // Replace commas, periods, semicolons, etc. with a space
                .replaceAll("'s\\b", "s")  // Replace "'s" at the end of words with "s"
                .toLowerCase()
                .split("\\s+"); // Split by whitespace into words
        for (String word : words) {
            if (!stopWords.contains(word) && !word.isEmpty()) {
                listIndex.add(word, docId);
                bstIndex.add(word, docId);
            }
        }
    }
}
