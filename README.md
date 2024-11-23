# Data Structures Project
 ---
 ## Group Members:
 - Nouf AlMansour (444200525)
 - Maha AlBakr (444201108)

---

## Simple Search Engine Project

### Overview
This project is a basic search engine capable of indexing, retrieving, and ranking documents based on user queries. It demonstrates the implementation of key data structures such as Lists and Binary Search Trees (BSTs) to build efficient inverted indices for document retrieval.

The project supports:
- **Boolean Retrieval**: Processes Boolean queries (`AND`, `OR`) to return unranked document sets.
- **Ranked Retrieval**: Ranks documents based on term frequency (TF) for query relevance.

---

### Features
1. **Document Processing**:
   - Reads documents from a CSV file.
   - Handles preprocessing:
     - Lowercase conversion.
     - Stop-word removal.
     - Punctuation cleaning.
     - Tokenization.

2. **Indexing**:
   - **List-based Inverted Index**:
     - Maps terms to document IDs using a linked list.
     - Efficient for small datasets but slower for large-scale queries.
   - **BST-based Inverted Index**:
     - Maps terms to document IDs using a binary search tree.
     - Optimized for faster term lookups with large datasets.

3. **Query Processing**:
   - **Boolean Queries**:
     - Supports `AND` and `OR` operators for finding documents containing the specified terms.
   - **Ranked Retrieval**:
     - Ranks documents based on term frequency (TF).

4. **User Interface**:
   - Interactive console-based menu for:
     - Performing Boolean Retrieval using list or BST indices.
     - Performing Ranked Retrieval.
     - Viewing index statistics (number of indexed documents and tokens).

5. **Performance Analysis**:
   - Provides a comparative analysis of Boolean retrieval efficiency between list-based and BST-based indices.

---

### Usage
The program provides an interactive menu with the following options:

1. **Boolean Retrieval (List)**: Perform a Boolean query using the list-based inverted index.
2. **Boolean Retrieval (BST)**: Perform a Boolean query using the BST-based inverted index.
3. **Ranked Retrieval**: Rank documents based on query term frequencies.
4. **Indexed Documents**: Display the total number of documents indexed in both indices.
5. **Indexed Tokens**: Display the total number of unique terms (tokens) indexed in both indices.
6. **Quit**: Exit the program.

#### Example Queries
- **Boolean Query**: `term1 AND term2`
- **Ranked Query**: `term1 term2 term3`

  ----
  ---

### Performance Analysis
#### Comparison of Indices
- **List-based Index**:
  - Simpler to implement.
  - Slower term lookups for large datasets (O(n) complexity).
- **BST-based Index**:
  - Faster term lookups (O(log n) complexity).
  - Scales better for larger datasets.





