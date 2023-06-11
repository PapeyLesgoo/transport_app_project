import java.io.IOException;

interface FileTabHand{
    void Update() throws IOException;
    void Remove();
    void Add();
    boolean UniqueCheck();
}