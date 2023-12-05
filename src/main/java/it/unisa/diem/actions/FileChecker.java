package it.unisa.diem.actions;

import java.io.File;
import java.util.Set;
//this class was created to remove duplicate code among different classes(Move File, Delete File, Copy File) and checks if the Path choosen is a dangerous one, blocking it from being accessible.
public class FileChecker {
    //This set contains Paths that can be dangerous to work with, so it will be impossible to move/copy/delete files from or to these directories.
    private  final Set<String> sensitiveDirectories = Set.of(
    "C:\\Windows",
    "C:\\Program Files",
    "C:\\Program Files (x86)",
    "/System",
    "/Applications",
    "/private");

    //The following two methods use .stream() to convert the List in a item stream and anyMatch to see if any item starts with the directory or the path of the file we want to check
    //If it finds any matches, it return false. This methods are necessary to check if a Path is also present in sensitiveDirectory which contains dangerous paths.
    public boolean unavailableDirectory(String directory) {
        return sensitiveDirectories.stream().anyMatch(directory::startsWith);
    }

    public boolean unavailableFile(File file) {
        String filePath = file.getAbsolutePath();
        return sensitiveDirectories.stream().anyMatch(filePath::startsWith);
    }
}
