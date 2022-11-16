package pdf;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class PdfSearch extends Application {

    // application code goes here

    CoreLogic core = new CoreLogic();
    static final Path PDF_DIRECTORY = Path.of("./slides");

    @FXML
    private TextField searchTextField;

    @FXML
    private ListView<ListEntry> listView;

    public void search() {
        System.out.println(searchTextField.getText());
        List<ListEntry> found = core.search(PDF_DIRECTORY, searchTextField.getText());

        listView.getItems().setAll(found);
    }

    @FXML
    public void initialize() {
        searchTextField.setText("Enter text to search");

        EventHandler<? super MouseEvent> handler = e -> {

            Optional<ListEntry> first = listView.getSelectionModel()
                    .getSelectedItems().stream().findFirst();

            if (e.getClickCount() > 1){
                first.ifPresent(listEntry -> getHostServices().showDocument(listEntry.url()));
            }

        };

        listView.setOnMouseClicked(handler);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pdf.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 400, 600));
        stage.setTitle("Pdf search");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
