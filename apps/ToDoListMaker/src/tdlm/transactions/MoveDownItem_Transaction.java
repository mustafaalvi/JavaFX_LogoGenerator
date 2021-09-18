package tdlm.transactions;

import jtps.jTPS_Transaction;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;

/**
 *
 * @author Mustafa Alvi
 */
public class MoveDownItem_Transaction implements jTPS_Transaction {
    ToDoData data;
    ToDoItemPrototype toMoveDownItem;
    
    public MoveDownItem_Transaction(ToDoData initData, ToDoItemPrototype initMoveDownItem) {
        data = initData;
        toMoveDownItem = initMoveDownItem;
    }

    @Override
    public void doTransaction() {
        data.moveDownItem(toMoveDownItem);        
    }

    @Override
    public void undoTransaction() {
        data.moveUpItem(toMoveDownItem);
    }
}