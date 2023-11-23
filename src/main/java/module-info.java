module it.unisa.diem{
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires java.desktop;

    opens it.unisa.diem.actions to junit;
    opens it.unisa.diem.SEGroup9 to javafx.fxml;
    exports it.unisa.diem.actions;
    exports it.unisa.diem.actions.AudioFileAction;
    //exports it.unisa.diem;
    exports it.unisa.diem.SEGroup9;
}
