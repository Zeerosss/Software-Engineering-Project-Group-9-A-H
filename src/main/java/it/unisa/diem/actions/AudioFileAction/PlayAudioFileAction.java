package it.unisa.diem.actions.AudioFileAction;

import it.unisa.diem.actions.Action;

public class PlayAudioFileAction implements Action{
    private String filepath;

    public PlayAudioFileAction(String filepath){
        this.filepath=filepath;
    }
    @Override
    public void startAction(){}
}
