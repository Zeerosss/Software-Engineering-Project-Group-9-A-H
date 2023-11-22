module it.unisa.diem {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;

    opens it.unisa.diem to javafx.fxml;
    exports it.unisa.diem;
}
