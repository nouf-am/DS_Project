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
   - Handles preprocessing: lowercase conversion, stop-word removal, punctuation cleaning, and tokenization.
   
2. **Indexing**:
   - Implements a list-based inverted index for mapping terms to document IDs.
   - Enhances performance with a BST-based inverted index for faster term lookups.

3. **Query Processing**:
   - Supports Boolean queries (`AND`, `OR`).
   - Ranks documents based on term frequency for query terms.

4. **User Interface**:
   - Interactive console-based menu for:
     - Boolean Retrieval.
     - Ranked Retrieval.
     - Viewing index statistics (number of indexed documents and tokens).

5. **Performance Analysis**:
   - Provides a comparative analysis of Boolean retrieval using list-based and BST-based indices.

---

### Project Structure
├── src/ │ ├── ds_project/ │ │ ├── Document.java # Represents a document with ID and content │ │ ├── InvertedIndexUsingList.java # Implements a list-based inverted index │ │ ├── InvertedIndexUsingBST.java # Implements a BST-based inverted index │ │ ├── QueryProcessing.java # Handles query evaluation and ranking │ │ ├── Reading.java # Handles document and stop-word file reading │ │ ├── Main.java # Provides the interactive user interface ├── data/ │ ├── stop.txt # Stop words file │ ├── dataset.csv # Document dataset file ├── README.md # Project documentation

### Usage
The program provides an interactive menu with the following options:

1. **Boolean Retrieval (List)**: Perform a Boolean query using the list-based index.
2. **Boolean Retrieval (BST)**: Perform a Boolean query using the BST-based index.
3. **Ranked Retrieval**: Rank documents based on query term frequencies.
4. **Indexed Documents**: Display the number of indexed documents.
5. **Indexed Tokens**: Display the number of tokens in the index.
6. **Quit**: Exit the program.

#### Example Queries
- Boolean Query: `term1 AND term2`
- Ranked Query: `term1 term2 term3`

---

### Deliverables
1. **Index**:
   - List-based and BST-based implementations for mapping terms to document IDs.
2. **Query Processor**:
   - Boolean and ranked retrieval implementations.
3. **Performance Analysis**:
   - Comparative analysis of retrieval times between list and BST indices.
4. **Documentation**:
   - Project design and implementation details, including class diagrams.

---

### Performance Analysis
#### Comparison of Indices
- **List-based Index**:
  - Simpler to implement.
  - Slower term lookups for large datasets (O(n) complexity).
- **BST-based Index**:
  - Faster term lookups (O(log n) complexity).
  - Scales better for larger datasets.


