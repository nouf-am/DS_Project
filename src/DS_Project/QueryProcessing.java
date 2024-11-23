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

public class QueryProcessing {
    private Map<Integer, String> documentMap;

    public QueryProcessing(Map<Integer, String> documentMap) {
        this.documentMap = documentMap;
    }

    public Set<Integer> evaluateBooleanQueryList(InvertedIndexUsingList index, String query) {
        return evaluateBooleanQuery(index, query, true);
    }

    public Set<Integer> evaluateBooleanQueryBST(InvertedIndexUsingBST index, String query) {
        return evaluateBooleanQuery(index, query, false);
    }

    private Set<Integer> evaluateBooleanQuery(Object index, String query, boolean isList) {
        String[] tokens = query.split("\\s+");
        Stack<Set<Integer>> operandStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();

        for (String token : tokens) {
            if (token.equalsIgnoreCase("AND")) {
                while (!operatorStack.isEmpty() && operatorStack.peek().equalsIgnoreCase("AND")) {
                    processOperator(operandStack, operatorStack.pop());
                }
                operatorStack.push("AND");
            } else if (token.equalsIgnoreCase("OR")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equalsIgnoreCase("OR")) {
                    processOperator(operandStack, operatorStack.pop());
                }
                operatorStack.push("OR");
            } else {
                Set<Integer> docs = isList ? getDocumentsForTermList((InvertedIndexUsingList) index, token)
                        : getDocumentsForTermBST((InvertedIndexUsingBST) index, token);
                operandStack.push(docs);
            }
        }

        while (!operatorStack.isEmpty()) {
            processOperator(operandStack, operatorStack.pop());
        }

        return operandStack.isEmpty() ? Collections.emptySet() : operandStack.pop();
    }

    private void processOperator(Stack<Set<Integer>> operandStack, String operator) {
        if (operandStack.size() < 2) return;

        Set<Integer> right = operandStack.pop();
        Set<Integer> left = operandStack.pop();

        if (operator.equalsIgnoreCase("AND")) {
            left.retainAll(right); // Intersection
        } else if (operator.equalsIgnoreCase("OR")) {
            left.addAll(right); // Union
        }

        operandStack.push(left);
    }

    private Set<Integer> getDocumentsForTermList(InvertedIndexUsingList index, String term) {
    	term = term.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        InvertedIndexUsingList.DocumentList docs = index.search(term);
        if (docs == null) return Collections.emptySet();
        return new HashSet<>(docs.toList());
    }

    private Set<Integer> getDocumentsForTermBST(InvertedIndexUsingBST index, String term) {
    	term = term.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return index.search(term);
    }

    public Map<Integer, Integer> rankedQuery(String[] terms) {
        Map<Integer, Integer> scores = new HashMap<>();

        for (Map.Entry<Integer, String> entry : documentMap.entrySet()) {
            int docId = entry.getKey();
            String content = entry.getValue();

            int termFrequency = 0;
            for (String term : terms) {
                termFrequency += countOccurrencesInContent(content, term);
            }

            if (termFrequency > 0) {
                scores.put(docId, termFrequency);
            }
        }

        return sortByValue(scores);
    }

    private int countOccurrencesInContent(String content, String term) {
        int count = 0;
        for (String word : content.split("\\s+")) {
            if (word.equals(term)) {
                count++;
            }
        }
        return count;
    }

    private Map<Integer, Integer> sortByValue(Map<Integer, Integer> map) {
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        Map<Integer, Integer> sorted = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : entries) {
            sorted.put(entry.getKey(), entry.getValue());
        }
        return sorted;
    }
}
