package tdlm.workspace.controllers;

import djf.ui.dialogs.AppDialogsFacade;
import java.util.ArrayList;
import tdlm.ToDoListMakerApp;
import static tdlm.ToDoPropertyType.TDLM_ITEM_INVALID_MESSAGE;
import static tdlm.ToDoPropertyType.TDLM_ITEM_INVALID_TITLE;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;
import tdlm.workspace.dialogs.ToDoListItemDialog;
import tdlm.transactions.AddItem_Transaction;
import tdlm.transactions.EditItem_Transaction;
import tdlm.transactions.MoveDownItem_Transaction;
import tdlm.transactions.MoveUpItem_Transaction;
import tdlm.transactions.RemoveItems_Transaction;

/**
 *
 * @author Mustafa Alvi 
 */
public class ItemsController {
    ToDoListMakerApp app;
    ToDoListItemDialog itemDialog;
    
    public ItemsController(ToDoListMakerApp initApp) {
        app = initApp;
        
        itemDialog = new ToDoListItemDialog(app);
    }
    
    public void processAddItem() {
        itemDialog.showAddDialog();
        ToDoItemPrototype newItem = itemDialog.getNewItem();        
        if (newItem != null) {
            
            ToDoData data = (ToDoData)app.getDataComponent();
            AddItem_Transaction transaction = new AddItem_Transaction(data, newItem);
            app.processTransaction(transaction);
            newItem = null; 
        }    
        // OTHERWISE TELL THE USER WHAT THEY
        // HAVE DONE WRONG
    }
    
    public void processRemoveItems() {
        ToDoData data = (ToDoData)app.getDataComponent();
        if (data.isItemSelected() || data.areItemsSelected()) {
            ArrayList<ToDoItemPrototype> itemsToRemove = new ArrayList(data.getSelectedItems());
            RemoveItems_Transaction transaction = new RemoveItems_Transaction(app, itemsToRemove);
            app.processTransaction(transaction);
        }
    }
    
    public void processEditItems() {
        ToDoData data = (ToDoData)app.getDataComponent();
        if (data.isItemSelected()) {
            
            ToDoItemPrototype original = data.getSelectedItem(); 
            itemDialog.showEditDialog(data.getSelectedItem());
            
            if(itemDialog.getEditItem() != null){
                ToDoItemPrototype editedItem = itemDialog.getEditItem();
            
                EditItem_Transaction transaction = new EditItem_Transaction(app, editedItem, original);
                app.processTransaction(transaction);
            }

        }
    
    }
    
    public void processMoveUpItem(){        
        ToDoData data = (ToDoData)app.getDataComponent();
        if(data.isItemSelected()){
            ToDoItemPrototype selected = data.getSelectedItem(); 
                
            MoveUpItem_Transaction moveUpItem = new MoveUpItem_Transaction(data, selected); 
            app.processTransaction(moveUpItem);
        }

    }
    
    public void processMoveDownItem(){
        ToDoData data = (ToDoData)app.getDataComponent();
        if(data.isItemSelected()){
            ToDoItemPrototype selected = data.getSelectedItem(); 
                
            MoveDownItem_Transaction moveDownItem = new MoveDownItem_Transaction(data, selected); 
            app.processTransaction(moveDownItem); 
        }
    }
    
}

