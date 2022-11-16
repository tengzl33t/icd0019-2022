package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import pdf.ListEntry;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class Sample extends Application {

    @FXML
    private TextField textField;

    @FXML
    private Text text;

    @FXML
    private ListView<ListEntry> listView;

    private Stage stage;

    public void readWriteTextField() {
        textField.setText(textField.getText().toUpperCase());
    }

    public void writeText() {
        text.setText("Wrote some text");
    }

    public void chooseDirectoryAction() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setInitialDirectory(new File(".")); // start from current directory
        File selectedDirectory = chooser.showDialog(stage);

        System.out.println(selectedDirectory);
    }

    public void openBrowser() {
        getHostServices().showDocument("https://www.google.com/search?q=javafx");
    }

    public void setListViewItems() {
        ListEntry item1 = new ListEntry("title 1", "value 1");
        ListEntry item2 = new ListEntry("title 2", "value 2");

        listView.getItems().setAll(List.of(item1, item2));
    }

    @FXML
    public void initialize() {

        textField.setText("Some text");

        EventHandler<? super MouseEvent> handler = e -> {

            System.out.println(e.getClickCount());

            // find first item from selected

            Optional<ListEntry> first = listView.getSelectionModel()
                    .getSelectedItems().stream()
                    .findFirst();

            System.out.println(first);
        };

        listView.setOnMouseClicked(handler);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 400, 600));
        stage.setTitle("Sample application");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
