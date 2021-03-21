package view.beans;


import java.util.Iterator;
import java.util.List;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.jbo.Key;

import oracle.jbo.Row;

import oracle.jbo.RowSetIterator;

import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;


public class FunWithTables {
    private RichTable departmentsTable;

    public FunWithTables() {
    }

    public void setDepartmentsTable(RichTable departmentsTable) {
        this.departmentsTable = departmentsTable;
    }

    public RichTable getDepartmentsTable() {
        return departmentsTable;
    }

    public String selectFilteredRows(){
        CollectionModel cm = (CollectionModel)getDepartmentsTable().getValue();
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().
            getCurrentBindingsEntry();
        DCIteratorBinding deptsIter = bindings.findIteratorBinding("DepartmentsVO1Iterator");
        RowSetIterator rksIter = deptsIter.getRowSetIterator();
        
        
        for (int i=0; i < cm.getRowCount(); i++){
            cm.setRowIndex(i);
            Object key = cm.getRowKey();
            cm.getRowData(key);
            System.out.println("Key: " +key);
            
        }
        
        return null;
    }
    
    
    public String selectRows() {
        RowKeySet rksSelectedDepts = this.getDepartmentsTable().getSelectedRowKeys();
        Iterator selectedRowsIter = rksSelectedDepts.iterator();
        
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().
            getCurrentBindingsEntry();
        DCIteratorBinding dcIteratorBinding = bindings.findIteratorBinding("DepartmentsVO1Iterator");
        
        RowSetIterator rsi = dcIteratorBinding.getRowSetIterator();
        
        while(selectedRowsIter.hasNext()){
            Key key = (Key)((List)selectedRowsIter.next()).get(0);
            Row row = rsi.getRow(key);
            System.out.println(row.getAttribute("DepartmentName"));
            row.setAttribute("Selection", true);
        }
        
        return null;
    }
}
