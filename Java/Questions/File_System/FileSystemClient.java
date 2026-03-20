
import java.lang.annotation.Inherited;
import java.time.LocalDateTime;
import java.util.*;

class FileSystem {
    private FileSystemNode root;

    public FileSystem() {
        this.root = new Directory("/");
    }
}

abstract class FileSystemNode {
    private String name;
    private Map<String, FileSystemNode> children;
    private String createdAt;
    private String modifiedAt;

    FileSystemNode(String name) {
        this.name = name;
        this.children = new HashMap<>();
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public void addChild(String name, FileSystemNode child) {
        this.children.put(name, child);
        this.modifiedAt = LocalDateTime.now();
    }

    public abstract boolean isFile();

    public abstract void display();

}

class File extends FileSystemNode {
    private String content;
    private String extension;

    File(String name) {
        super(name);
        this.extension = extractExtension(name);

    }

    private String extractExtension(String name) {
        return name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public void display() {
        System.out.println(content);
    }
}

class Directory extends FileSystemNode {
    Directory(String name) {
        super(name);

    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public void display() {

    }
}

public class FileSystemClient {
    public static void main(String[] args) {
        Node obj = new Node();

    }
}

@Entity
public class Order {
    @id
    @GeneratedValue
    private Long id;

    private String item;

    @ManyToOne
    @JoinColumn(name = 'customer_id')
    private Customet customer;

}

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedby = "customer")
    private List<Order> orders;
}
