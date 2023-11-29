package it.unisa.diem.actions.FileAction;
import it.unisa.diem.actions.Action;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;




public class CopyFileAction implements Action {
    private File file;
    private Path sourcePath;
    private Path destinationPath;

    public CopyFileAction(File file, String destinationPath) {
        this.file = file;
        this.sourcePath = file.toPath();
        this.destinationPath = Paths.get(destinationPath, file.getName());
    }
    //if an error occurr during the copy, an exception will be launched
    @Override
    public void startAction() {
        try {
            Files.copy(sourcePath, destinationPath,StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("An error occurred while copying the file: " + e.getMessage());
        }
    }

    @Override
    public String toString(){
        return ("Copying the file:"+ file.getName()+ "\nto:"+ destinationPath);
    }
}
