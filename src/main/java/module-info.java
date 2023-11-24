module it.unisa.diem{
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires java.desktop;
    exports it.unisa.diem.actions.MessageAction;
    opens it.unisa.diem.rules to javafx.base;
    opens it.unisa.diem.actions to junit;
    opens it.unisa.diem.SEGroup9 to javafx.fxml;
    exports it.unisa.diem.actions;
    exports it.unisa.diem.actions.AudioFileAction;
    //exports it.unisa.diem.triggers;
    opens it.unisa.diem.triggers to junit;
    exports it.unisa.diem.SEGroup9;
}
