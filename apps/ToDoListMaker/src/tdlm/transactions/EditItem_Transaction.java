package tdlm.transactions;

import jtps.jTPS_Transaction;
import tdlm.ToDoListMakerApp;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;

/**
 *
 * @author MUSTAFA alvi
 */
public class EditItem_Transaction implements jTPS_Transaction {
    ToDoListMakerApp app; 
    ToDoData data;
    ToDoItemPrototype editedItem;
    ToDoItemPrototype originalItemCopy;
    
    public EditItem_Transaction(ToDoListMakerApp initApp, ToDoItemPrototype initEditedItem, ToDoItemPrototype initOG) {
        app = initApp;
        editedItem = initEditedItem;
        originalItemCopy = initOG;
    }

    @Override
    public void doTransaction() {
        data = (ToDoData)app.getDataComponent(); 
        data.editItem(originalItemCopy, editedItem);        
    }

    @Override
    public void undoTransaction() {
        data = (ToDoData)app.getDataComponent(); 
        data.uneditItem(originalItemCopy, editedItem);
    }
}
