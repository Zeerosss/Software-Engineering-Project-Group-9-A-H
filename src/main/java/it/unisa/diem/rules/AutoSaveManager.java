package it.unisa.diem.rules;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AutoSaveManager implements Observer {
    RuleList ruleList = RuleList.getInstance();
    private static String SAVE_PATH = "src\\main\\resources\\it\\unisa\\diem\\rules.date";
    private static File file= new File(SAVE_PATH);
 
    public AutoSaveManager() {
        ruleList.addObserver(this);

    }

    private void save() throws IOException {
            
            if (!file.exists()) 
                  try {
                    file.createNewFile();
                  } catch (IOException e) {
                    System.err.println("error in file creation");
                  }  
                
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
                Rule[] rules = ruleList.getRules().toArray(new Rule[0]);
                objectOutputStream.writeObject(rules);
        }
        }
    
   
    /**
     * @return
     * @throws Exception
     */
    public Boolean load() throws Exception{
    

    if (file.exists()) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            // Read the object from the file using deserialization
            Object[] rulesArray = (Object[]) objectInputStream.readObject();

            //add to the list;
            
            for (Object obj : rulesArray) {
                if (obj instanceof Rule) {
                    Rule rule = (Rule) obj;
                    
                    ruleList.ruleAdd(rule.getStatus(),rule.getName(),rule.getTrigger(),rule.getAction());
                }
            }
           
        return true;    
        }
    }else{
        return false;
    }
}
    
     @Override
    public void update() {
        try{
            save();
        } catch (IOException e) {
            System.out.println("Error while saving data!");
        }
    }

}
