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

public class InvertedIndexUsingBST {
	private class Node {
		String term;
		Set<Integer> docSet;
		Node left, right;

		Node(String term) {
			this.term = term;
			this.docSet = new HashSet<>();
			this.left = this.right = null;
		}
	}

	private Node root;

	public InvertedIndexUsingBST() {
		root = null;
	}

	public void add(String term, int docId) {
		root = addRecursive(root, term, docId);
	}

	private Node addRecursive(Node node, String term, int docId) {
		if (node == null) {
			Node newNode = new Node(term);
			newNode.docSet.add(docId);
			return newNode;
		}
		if (term.compareTo(node.term) < 0) {
			node.left = addRecursive(node.left, term, docId);
		} else if (term.compareTo(node.term) > 0) {
			node.right = addRecursive(node.right, term, docId);
		} else {
			node.docSet.add(docId);
		}
		return node;
	}

	public Set<Integer> search(String term) {
		Node node = searchRecursive(root, term);
		return node == null ? Collections.emptySet() : node.docSet;
	}

	private Node searchRecursive(Node node, String term) {
		if (node == null || node.term.equals(term)) {
			return node;
		}
		if (term.compareTo(node.term) < 0) {
			return searchRecursive(node.left, term);
		} else {
			return searchRecursive(node.right, term);
		}
	}

	public int countTokens() {
		return countTokensRecursive(root);
	}

	private int countTokensRecursive(Node node) {
		if (node == null)
			return 0;
		return 1 + countTokensRecursive(node.left) + countTokensRecursive(node.right);
	}

	public int countDocuments() {
		Set<Integer> uniqueDocs = new HashSet<>();
		collectDocuments(root, uniqueDocs);
		return uniqueDocs.size();
	}

	private void collectDocuments(Node node, Set<Integer> uniqueDocs) {
		if (node != null) {
			uniqueDocs.addAll(node.docSet);
			collectDocuments(node.left, uniqueDocs);
			collectDocuments(node.right, uniqueDocs);
		}
	}
}
