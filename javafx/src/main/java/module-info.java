module pdf {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.pdfbox;

    opens sample to javafx.fxml;
    opens pdf to javafx.fxml;

    exports sample;
    exports pdf;
}