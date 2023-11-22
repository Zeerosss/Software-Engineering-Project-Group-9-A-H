package it.unisa.diem.actions.AudioFileAction;

import it.unisa.diem.actions.Action;

public class PlayAudioFileAction implements Action{
    private String filepath;

    public PlayAudioFileAction(){
    }
    @Override
    public void startAction(){}

    public void audioAction(String filePath){
        this.filepath=filePath;
    }
    @Override
    public String toString() {
        return "Play Audio File Action ";
    }

    
}
