module project.template {
    requires AuthLib;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;
    requires java.logging;
    requires org.apache.commons.lang3;
    requires commons.math3;

    exports pt.ipp.isep.dei.esoft.project.ui to javafx.graphics, javafx.fxml, javafx.base, javafx.controls, javafx.media, javafx.swing, javafx.web;

    opens pt.ipp.isep.dei.esoft.project.ui to javafx.graphics, javafx.fxml, javafx.base, javafx.controls, javafx.media, javafx.swing, javafx.web;
    exports pt.ipp.isep.dei.esoft.project.ui.gui;
    opens pt.ipp.isep.dei.esoft.project.ui.gui to javafx.base, javafx.controls, javafx.fxml, javafx.graphics, javafx.media, javafx.swing, javafx.web;

    exports pt.ipp.isep.dei.esoft.project.application.controller;
    opens pt.ipp.isep.dei.esoft.project.application.controller to javafx.base, javafx.controls, javafx.fxml, javafx.graphics, javafx.media, javafx.swing, javafx.web;
    exports pt.ipp.isep.dei.esoft.project.serialization to javafx.base, javafx.controls, javafx.fxml, javafx.graphics, javafx.media, javafx.swing, javafx.web;
    opens pt.ipp.isep.dei.esoft.project.serialization to javafx.base, javafx.controls, javafx.fxml, javafx.graphics, javafx.media, javafx.swing, javafx.web;
}