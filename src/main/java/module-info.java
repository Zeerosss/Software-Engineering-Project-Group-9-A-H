module it.unisa.diem{
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    exports it.unisa.diem.actions.MessageAction;
    opens it.unisa.diem.rules to javafx.base;
    opens it.unisa.diem.SEGroup9 to javafx.fxml;
   // exports it.unisa.diem;
    exports it.unisa.diem.SEGroup9;
}
