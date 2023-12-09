module it.unisa.diem{
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires java.desktop;
    requires javafx.media;
    
    opens it.unisa.diem.rules to javafx.base;
    opens it.unisa.diem.SEGroup9 to javafx.fxml;

    opens it.unisa.diem.actions to junit;
    opens it.unisa.diem.triggers.TimeOfDay to junit;
    opens it.unisa.diem.triggers.FileDimensionExceeds to junit;
    opens it.unisa.diem.triggers.FileExists to junit;
    
    exports it.unisa.diem.actions;
    exports it.unisa.diem.actions.AudioFileAction;
    exports it.unisa.diem.actions.MessageAction;

    exports it.unisa.diem.triggers;
    exports it.unisa.diem.rules;
    exports it.unisa.diem.SEGroup9;
    
    exports it.unisa.diem.actions.FileAction;
}
