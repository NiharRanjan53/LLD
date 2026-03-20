import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

interface DocumentElement {
    public abstract String render();
}

class TextElement implements DocumentElement {
    String text;

    public TextElement(String text) {
        this.text = text;
    }

    @Override
    public String render() {
        System.out.println("Rendering Text Element");
        return text;
    }
}

class ImageElement implements DocumentElement {
    String url;

    public ImageElement(String url) {
        this.url = url;
    }

    @Override
    public String render() {
        System.out.println("Rendering Image Element");
        return "[Image: " + url + "]";
    }
}

class NewLineElement implements DocumentElement {
    @Override
    public String render() {
        return "\n";
    }
}

interface Persistence {
    public void save(String data);
}

class FileStorage implements Persistence {
    @Override
    public void save(String data) {

        try {
            FileWriter outFile = new FileWriter("document.txt");
            outFile.write(data);
            outFile.close();
            System.out.println("Document Saved in document.txt file");

        } catch (IOException e) {
            System.out.println("Error: Unable to open file for writing.");
        }
    }
}

class DBStorage implements Persistence {
    @Override
    public void save(String doc) {
        System.out.println("Saving to DB.");
    }
}

class Document {
    List<DocumentElement> documentElements = new ArrayList<>();

    public void addElement(DocumentElement element) {
        documentElements.add(element);
    }

    public String render() {
        StringBuilder result = new StringBuilder();
        for (DocumentElement ele : documentElements) {
            result.append(ele.render());
        }
        return result.toString();
    }
}

class DocumentEditor {
    private Document document;
    private Persistence storage;
    private String renderedDocument = "";

    public DocumentEditor(Document document, Persistence storage) {
        this.document = document;
        this.storage = storage;
    }

    public void addText(String text) {
        document.addElement(new TextElement(text));
    }

    public void addImage(String link) {
        document.addElement(new ImageElement(link));
    }

    public void addNewLine() {
        document.addElement(new NewLineElement());
    }

    public String renderDocument() {
        if (renderedDocument.isEmpty()) {
            renderedDocument = document.render();
        }
        return renderedDocument;
    }

    public void saveDocument() {
        storage.save(renderedDocument);
    }

}

public class DocumentEditorClient {
    public static void main(String[] args) {
        Document document = new Document();
        Persistence persistence = new FileStorage();

        DocumentEditor editor = new DocumentEditor(document, persistence);

        // Simulate a client using the editor with common text formatting features.
        editor.addText("Hello, world!");
        editor.addNewLine();
        editor.addText("This is a real-world document editor example.");
        editor.addNewLine();
        // editor.addTabSpace();
        editor.addText("Indented text after a tab space.");
        editor.addNewLine();
        editor.addImage("picture.jpg");

        // Render and display the final document.
        System.out.println(editor.renderDocument());

        editor.saveDocument();

    }
}
