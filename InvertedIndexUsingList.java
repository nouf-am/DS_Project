/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author noufalmansour
 */
public class InvertedIndexUsingList {
	Node head;

	public InvertedIndexUsingList() {
		head = null;
	}

	// Inner Node class representing terms in the index
	class Node {
		String term;
		DocumentList docList;
		Node next;

		Node(String term) {
			this.term = term;
			this.docList = new DocumentList();
			this.next = null;
		}
	}

	// Public DocumentList class for document IDs
	public class DocumentList {
		DocumentNode head;

		// Inner DocumentNode class representing document IDs
		class DocumentNode {
			int docId;
			DocumentNode next;

			DocumentNode(int docId) {
				this.docId = docId;
				this.next = null;
			}
		}

		public void add(int docId) {
			if (head == null) {
				head = new DocumentNode(docId);
			} else {
				DocumentNode current = head;
				while (current.next != null) {
					if (current.docId == docId)
						return; // Avoid duplicates
					current = current.next;
				}
				if (current.docId != docId) {
					current.next = new DocumentNode(docId);
				}
			}
		}

		public boolean contains(int docId) {
			DocumentNode current = head;
			while (current != null) {
				if (current.docId == docId)
					return true;
				current = current.next;
			}
			return false;
		}

		public List<Integer> toList() {
			List<Integer> docIds = new ArrayList<>();
			DocumentNode current = head;
			while (current != null) {
				docIds.add(current.docId);
				current = current.next;
			}
			return docIds;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			DocumentNode current = head;
			while (current != null) {
				sb.append(current.docId).append(" ");
				current = current.next;
			}
			return sb.toString().trim();
		}
	}

	public void add(String term, int docId) {
		if (head == null) {
			head = new Node(term);
			head.docList.add(docId);
		} else {
			Node current = head;
			Node prev = null;
			while (current != null && current.term.compareTo(term) < 0) {
				prev = current;
				current = current.next;
			}
			if (current != null && current.term.equals(term)) {
				current.docList.add(docId);
			} else {
				Node newNode = new Node(term);
				newNode.docList.add(docId);
				if (prev == null) {
					newNode.next = head;
					head = newNode;
				} else {
					newNode.next = current;
					prev.next = newNode;
				}
			}
		}
	}

	public DocumentList search(String term) {
		Node current = head;
		while (current != null) {
			if (current.term.equals(term)) {
				return current.docList;
			}
			current = current.next;
		}
		return null;
	}

	public int countTokens() {
		int count = 0;
		Node current = head;
		while (current != null) {
			count++;
			current = current.next;
		}
		return count;
	}

	public int countDocuments() {
		Set<Integer> uniqueDocs = new HashSet<>();
		Node current = head;
		while (current != null) {
			DocumentList.DocumentNode docNode = current.docList.head;
			while (docNode != null) {
				uniqueDocs.add(docNode.docId);
				docNode = docNode.next;
			}
			current = current.next;
		}
		return uniqueDocs.size();
	}
}
