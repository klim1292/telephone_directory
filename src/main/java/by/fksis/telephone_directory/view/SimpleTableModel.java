package by.fksis.telephone_directory.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

class SimpleTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 7732665770880050184L;
	private List<Object[]> rows = new ArrayList<>();
	private final int COLUMN_COUNT;
	
	SimpleTableModel(int columnCount) {
		COLUMN_COUNT = columnCount;
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_COUNT;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return rows.get(rowIndex)[columnIndex];
	}
	
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		rows.get(rowIndex)[columnIndex] = value;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public void addRow(Object... row) {
		rows.add(row);
	}
	
	public void deleteRow(int rowIndex) {
		rows.remove(rowIndex);
	}
	
	public void clear() {
		rows.clear();
	}

}