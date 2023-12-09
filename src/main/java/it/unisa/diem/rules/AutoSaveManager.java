package it.unisa.diem.rules;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AutoSaveManager implements Observer {
    RuleList ruleList = RuleList.getInstance();

    // Define the file path for saving and loading rules
    private static File file;

    // Constructor to set up the observer relationship
    public AutoSaveManager(String savePath) {
        ruleList.addObserver(this);
        file = new File(savePath);
    }

    // Private method to save the rules to a file
    private void save() throws IOException {
        // If the file does not exist, create a new file
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error in file creation");
            }
        }

        // Use try-with-resources to automatically close the ObjectOutputStream
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            Rule[] rules = ruleList.getRules().toArray(new Rule[0]);
            objectOutputStream.writeObject(rules);
        }
    }

    /**
     * Load rules from the saved file.
     *
     * @return True if loading was successful, false otherwise.
     * @throws Exception
     */
    public Boolean load() throws Exception {
        if (file.exists()) {
            // Use try-with-resources to automatically close the ObjectInputStream
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                // Read the object from the file using deserialization
                Object[] rulesArray = (Object[]) objectInputStream.readObject();

                // Add the loaded rules to the RuleList
                for (Object obj : rulesArray) {
                    if (obj instanceof Rule) {
                        Rule rule = (Rule) obj;

                        ruleList.ruleAdd(rule.getStatus(), rule.getName(), rule.getTrigger(), rule.getAction(),
                                rule.isOnlyOnce(), rule.getSleepingTime(), rule.getNextUsefulDate());
                    }
                }
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public void update() {
        try {
            // Trigger save method when the RuleList is updated
            save();
        } catch (IOException e) {
            System.out.println("Error while saving data!");
        }
    }
}
