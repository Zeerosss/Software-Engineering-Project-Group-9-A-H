module it.unisa.diem{
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    exports it.unisa.diem.triggers;
    opens it.unisa.diem.triggers to junit;
    opens it.unisa.diem.SEGroup9 to javafx.fxml;
    //exports it.unisa.diem;
    exports it.unisa.diem.SEGroup9;
}
