package main.java.telephone_directory.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class View extends JFrame {

	private static final long serialVersionUID = 2431908352200154417L;
	private JLabel phoneLabel = new JLabel("Phone number:");
	private JLabel surnameLabel = new JLabel("Surname:");
	private JLabel nameLabel = new JLabel("Name:");
	private JLabel patronymicLabel = new JLabel("Patronymic:");
	private JLabel populatedAreaLabel = new JLabel("Populated area:");
	private JLabel streetLabel = new JLabel("Street:");
	private JLabel houseCorpsFlatLabel = new JLabel("House/corps/flat:");
	
	private JTextField phone = new JTextField(5);
	private JTextField surname = new JTextField(5);
	private JTextField name = new JTextField(5);
	private JTextField patronymic = new JTextField(5);
	private JTextField populatedArea = new JTextField(5);
	private JTextField street = new JTextField(5);
	private JTextField house = new JTextField(5);
	private JTextField corps = new JTextField(5);
	private JTextField flat = new JTextField(5);
	
	private JButton reset = new JButton("Reset");
	private JButton search = new JButton("Search");
	private JButton add = new JButton("Add");
	private JButton update = new JButton("Update");
	private JButton delete = new JButton("Delete");
	
	private JTable subscribers = new JTable();
	
	public View(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(680, 308));
		setResizable(false);
		init();
	}
	
	private void init() {
		phone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar()) || phone.getText().length() > 10) {
					e.consume();
				}
			}
		});
		house.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar()) || house.getText().length() > 2) {
					e.consume();
				}
			}
		});
		flat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar()) || flat.getText().length() > 2) {
					e.consume();
				}
			}
		});
		DefaultTableModel model = new DefaultTableModel(new String[] {"Phone", "Full name", "Address"}, 0) {
			private static final long serialVersionUID = 5276934906736778218L;
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
        subscribers.setModel(model);
        subscribers.getColumnModel().getColumn(0).setPreferredWidth(45);
		subscribers.getColumnModel().getColumn(1).setPreferredWidth(85);
		subscribers.getColumnModel().getColumn(2).setPreferredWidth(130);
        subscribers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		subscribers.getTableHeader().setReorderingAllowed(false);
		subscribers.setAutoCreateRowSorter(true);
		JScrollPane tablePane = new JScrollPane(subscribers);
		update.setEnabled(false);
		delete.setEnabled(false);

		setLayout(null);
        add(phoneLabel).setBounds(10, 10, 100, 25);
        add(phone).setBounds(10, 30, 100, 25);
        add(populatedAreaLabel).setBounds(120, 10, 100, 25);
        add(populatedArea).setBounds(120, 30, 100, 25);
        add(surname).setBounds(10, 75, 100, 25);
        add(surnameLabel).setBounds(10, 55, 100, 25);
        add(street).setBounds(120, 75, 100, 25);
        add(streetLabel).setBounds(120, 55, 100, 25);
        add(name).setBounds(10, 120, 100, 25);
        add(house).setBounds(120, 120, 30, 25);
        add(corps).setBounds(155, 120, 30, 25);
        add(flat).setBounds(190, 120, 30, 25);
        add(nameLabel).setBounds(10, 100, 100, 25);
        add(houseCorpsFlatLabel).setBounds(120, 100, 100, 25);
        add(patronymicLabel).setBounds(10, 145, 100, 25);
        add(patronymic).setBounds(10, 165, 100, 25);
        add(reset).setBounds(120, 165, 100, 25);
        add(search).setBounds(10, 200, 210, 25);
        add(add).setBounds(10, 235, 100, 25);
        add(update).setBounds(120, 235, 100, 25);
        add(tablePane).setBounds(235, 30, 420, 195);
        add(delete).setBounds(555, 235, 100, 25);
        pack();
	}
	
	public void setUpdateEnabled(boolean enabled) {
		update.setEnabled(enabled);
	}
	
	public void setDeleteEnabled(boolean enabled) {
		delete.setEnabled(enabled);
	}
	
	public long getPhone() {
		return (phone.getText().isBlank()) ? 0L : Long.parseLong(phone.getText());
	}
	
	public void setPhone(long phone) {
		this.phone.setText(Long.toString(phone));
	}
	
	public String getSurname() {
		return surname.getText();
	}
	
	public void setSurname(String surname) {
		this.surname.setText(surname);
	}
	
	public String getName() {
		return name.getText();
	}
	
	public void setName(String name) {
		this.name.setText(name);
	}
	
	public String getPatronymic() {
		return patronymic.getText();
	}
	
	public void setPatronymic(String patronymic) {
		this.patronymic.setText(patronymic);
	}
	
	public String getPopulatedArea() {
		return populatedArea.getText();
	}
	
	public void setPopulatedArea(String populatedArea) {
		this.populatedArea.setText(populatedArea);
	}
	
	public String getStreet() {
		return street.getText();
	}
	
	public void setStreet(String street) {
		this.street.setText(street);
	}
	
	public int getHouse() {
		return (house.getText().isBlank()) ? 0 : Integer.parseInt(house.getText());
	}
	
	public void setHouse(int house) {
		this.house.setText(Integer.toString(house));
	}
	
	public String getCorps() {
		return corps.getText();
	}
	
	public void setCorps(String corps) {
		this.corps.setText(corps);
	}
	
	public int getFlat() {
		return (flat.getText().isBlank()) ? 0 : Integer.parseInt(flat.getText());
	}
	
	public void setFlat(int flat) {
		this.flat.setText((flat == 0) ? "" : Integer.toString(flat));
	}
	
	public void resetForm() {
		phone.setText("");
		setSurname("");
		setName("");
		setPatronymic("");
		setPopulatedArea("");
		setStreet("");
		house.setText("");
		setCorps("");
		flat.setText("");
		subscribers.clearSelection();
	}
	
	public void addSubscriber(String phone, String fullName, String address) {
		DefaultTableModel model = (DefaultTableModel)subscribers.getModel();
		model.addRow(new Object[] {phone, fullName, address});
	}
	
	public String[] readSubscriber() {
		DefaultTableModel model = (DefaultTableModel)subscribers.getModel();
		return new String[] {model.getValueAt(subscribers.getSelectedRow(), 0).toString(), model.getValueAt(subscribers.getSelectedRow(), 1).toString(), model.getValueAt(subscribers.getSelectedRow(), 2).toString()};
	}
	
	public void updateSubscriber(String phoneNumber, String fullName, String address) {
		DefaultTableModel model = (DefaultTableModel)subscribers.getModel();
		model.setValueAt(phoneNumber, subscribers.getSelectedRow(), 0);
		model.setValueAt(fullName, subscribers.getSelectedRow(), 1);
		model.setValueAt(address, subscribers.getSelectedRow(), 2);
	}
	
	public void deleteSubscriber() {
		DefaultTableModel model = (DefaultTableModel)subscribers.getModel();
		model.removeRow(subscribers.getSelectedRow());
	}
	
	public void clearSubscribers() {
		DefaultTableModel model = (DefaultTableModel)subscribers.getModel();
		while(model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}
	
	public void addSubscriberMouseListener(MouseAdapter listener) {
		subscribers.addMouseListener(listener);
	}
	
	public void addResetActionListener(ActionListener listener) {
		reset.addActionListener(listener);
	}
	
	public void addSearchActionListener(ActionListener listener) {
		search.addActionListener(listener);
	}
	
	public void addAddActionListener(ActionListener listener) {
		add.addActionListener(listener);
	}
	
	public void addUpdateActionListener(ActionListener listener) {
		update.addActionListener(listener);
	}
	
	public void addDeleteActionListener(ActionListener listener) {
		delete.addActionListener(listener);
	}
	
	public void showExceptionMessage(String message) {
		JOptionPane.showMessageDialog(this, message, "Exception", JOptionPane.ERROR_MESSAGE);
	}
	
}