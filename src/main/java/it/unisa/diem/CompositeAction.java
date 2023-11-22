package it.unisa.diem;

import java.util.LinkedList;
import java.util.List;

public class CompositeAction implements Action {
    //LinkedList for faster insert and delete
    private List<Action> actionList=new LinkedList<Action>();

    @Override
    public void startAction(){}

    public void add(Action a){
        actionList.add(a);
    }
    public void delete(Action a){
        actionList.remove(a);
    }
    public void Action(){}
}
