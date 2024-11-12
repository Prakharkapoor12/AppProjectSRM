import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Document class to hold title and content
class Document {
    private String title;
    private String content;

    public Document(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}

// Basic Search Engine class
class SearchEngine {
    private List<Document> documents;

    public SearchEngine() {
        documents = new ArrayList<>();
    }

    public void addDocument(Document doc) {
        documents.add(doc);
    }

    public List<Document> search(String query) {
        List<Document> results = new ArrayList<>();
        System.out.println("Searching for: " + query); // Debug statement
        for (Document doc : documents) {
            System.out.println("Checking document: " + doc.getTitle()); // Debug statement
            if (doc.getContent().toLowerCase().contains(query.toLowerCase())) {
                results.add(doc);
                System.out.println("Match found: " + doc.getTitle()); // Debug statement
            }
        }
        System.out.println("Results found: " + results.size()); // Debug statement
        return results;
    }
}

// Main class to run the search engine with GUI
public class Index {
    private SearchEngine searchEngine;
    private JTextField searchField;
    private JTextArea resultArea;

    public Index() {
        searchEngine = new SearchEngine();
        initializeDocuments();
        createAndShowGUI();
    }

    private void initializeDocuments() {
        // Sample documents
        searchEngine.addDocument(new Document("Java Programming", "Java is a versatile programming language."));
        searchEngine.addDocument(new Document("Python Programming", "Python is known for its simplicity and readability."));
        searchEngine.addDocument(new Document("Web Development", "Web development includes building websites and web applications."));
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Simple Search Engine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create components
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        // Set up layout
        JPanel panel = new JPanel();
        panel.add(searchField);
        panel.add(searchButton);

        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.getContentPane().add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Add action listener for the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });

        frame.setVisible(true);
    }

    private void performSearch() {
        String query = searchField.getText();
        if (query.trim().isEmpty()) {
            resultArea.setText("Please enter a search query.");
            return;
        }

        List<Document> results = searchEngine.search(query);
        resultArea.setText(""); // Clear previous results
        if (results.isEmpty()) {
            resultArea.setText("No results found.");
        } else {
            for (Document doc : results) {
                resultArea.append("Title: " + doc.getTitle() + "\n");
                resultArea.append("Content: " + doc.getContent() + "\n\n");
            }
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Index::new);
    }
}
