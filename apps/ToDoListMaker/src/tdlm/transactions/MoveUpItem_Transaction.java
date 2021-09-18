package tdlm.transactions;

import jtps.jTPS_Transaction;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;

/**
 *
 * @author McDonalds Alvi
 */
public class MoveUpItem_Transaction implements jTPS_Transaction {
    ToDoData data;
    ToDoItemPrototype toMoveUpItem;
    
    public MoveUpItem_Transaction(ToDoData initData, ToDoItemPrototype initMoveUpItem) {
        data = initData;
        toMoveUpItem = initMoveUpItem;
    }

    @Override
    public void doTransaction() {
        data.moveUpItem(toMoveUpItem);        
    }

    @Override
    public void undoTransaction() {
        data.moveDownItem(toMoveUpItem);
    }
}
