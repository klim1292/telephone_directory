package by.fksis.telephone_directory.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class View extends JFrame {
	
	private static final long serialVersionUID = 3366640780424280626L;
	public static final View INSTANCE = new View();
	
	private JMenu file = new JMenu();
	private JMenuItem open = new JMenuItem();
	private JMenuItem save = new JMenuItem();
	private JMenuItem exit = new JMenuItem();
	
	private JComboBox<Locale> language = new JComboBox<>(new Locale[]{Locale.US, new Locale("be", "BY"), new Locale("ru", "RU")});
	
	private JLabel languageLabel = new JLabel();
	private JLabel numberLabel = new JLabel();
	private JLabel surnameLabel = new JLabel();
	private JLabel nameLabel = new JLabel();
	private JLabel patronymicLabel = new JLabel();
	private JLabel populatedAreaLabel = new JLabel();
	private JLabel streetLabel = new JLabel();
	private JLabel houseLabel = new JLabel();
	private JLabel flatLabel = new JLabel();

	private JTextField number = new JTextField(11);
	private JTextField surname = new JTextField(11);
	private JTextField name = new JTextField(11);
	private JTextField patronymic = new JTextField(11);
	private JTextField populatedArea = new JTextField(11);
	private JTextField street = new JTextField(11);
	private JTextField house = new JTextField(4);
	private JTextField flat = new JTextField(4);
	
	private JButton reset = new JButton();
	private JButton search = new JButton();
	private JButton add = new JButton();
	private JButton update = new JButton();
	private JButton delete = new JButton();
	
	private JTable subscribers = new JTable();
	
	private View() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(613, 552));
		configure();
		restrictInput();
		changeLocale();
		reset();
		pack();
	}
	
	private void configure() {
		file.add(open);
		file.add(save);
		file.addSeparator();
		file.add(exit);
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(file);
		setJMenuBar(menuBar);
		
		JPanel languagePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		languagePanel.add(languageLabel);
		languagePanel.add(language);
		getContentPane().add(languagePanel, BorderLayout.NORTH);
		
		reset.setPreferredSize(new Dimension(120, 25));
		search.setPreferredSize(new Dimension(120, 25));
		add.setPreferredSize(new Dimension(120, 25));
		update.setPreferredSize(new Dimension(120, 25));
		delete.setPreferredSize(new Dimension(120, 25));
		JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		formPanel.setPreferredSize(new Dimension(140, 200));
		formPanel.add(numberLabel);
		formPanel.add(number);
		formPanel.add(surnameLabel);
		formPanel.add(surname);
		formPanel.add(nameLabel);
		formPanel.add(name);
		formPanel.add(patronymicLabel);
		formPanel.add(patronymic);
		formPanel.add(populatedAreaLabel);
		formPanel.add(populatedArea);
		formPanel.add(streetLabel);
		formPanel.add(street);
		formPanel.add(houseLabel);
		formPanel.add(house);
		formPanel.add(flatLabel);
		formPanel.add(flat);
		formPanel.add(reset);
		formPanel.add(search);
		formPanel.add(add);
		formPanel.add(update);
		formPanel.add(delete);
		getContentPane().add(formPanel, BorderLayout.WEST);
		
		JScrollPane tableScrollPane = new JScrollPane(subscribers);
		subscribers.setModel(new SimpleTableModel(3));
		subscribers.getTableHeader().setReorderingAllowed(false);
		subscribers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		subscribers.setAutoCreateRowSorter(true);
		getContentPane().add(tableScrollPane);
	}
	
	private void restrictInput() {
		number.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar()) || number.getText().length() > 10) {
					e.consume();
				}
			}
			
		});
		number.setTransferHandler(null);
		house.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar()) || house.getText().length() > 2) {
					e.consume();
				}
			}
			
		});
		house.setTransferHandler(null);
		flat.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar()) || flat.getText().length() > 2) {
					e.consume();
				}
			}
			
		});
		flat.setTransferHandler(null);
	}
	
	public void changeLocale() {
		Locale.setDefault(language.getItemAt(language.getSelectedIndex()));
		ResourceBundle rb = ResourceBundle.getBundle("gui");
		setTitle(rb.getString("Frame.title"));
		file.setText(rb.getString("Frame.menuFile"));
		open.setText(rb.getString("Frame.menuOpen"));
		save.setText(rb.getString("Frame.menuSave"));
		exit.setText(rb.getString("Frame.menuExit"));
		languageLabel.setText(rb.getString("Frame.labelLanguage"));
		numberLabel.setText(rb.getString("Frame.labelNumber"));
		surnameLabel.setText(rb.getString("Frame.labelSurname"));
		nameLabel.setText(rb.getString("Frame.labelName"));
		patronymicLabel.setText(rb.getString("Frame.labelPatronymic"));
		populatedAreaLabel.setText(rb.getString("Frame.labelPopulatedArea"));
		streetLabel.setText(rb.getString("Frame.labelStreet"));
		houseLabel.setText(rb.getString("Frame.labelHouse"));
		flatLabel.setText(rb.getString("Frame.labelFlat"));
		reset.setText(rb.getString("Frame.buttonReset"));
		search.setText(rb.getString("Frame.buttonSearch"));
		add.setText(rb.getString("Frame.buttonAdd"));
		update.setText(rb.getString("Frame.buttonUpdate"));
		delete.setText(rb.getString("Frame.buttonDelete"));
		subscribers.getColumnModel().getColumn(0).setHeaderValue(rb.getString("Frame.tableNumberHeader"));
		subscribers.getColumnModel().getColumn(1).setHeaderValue(rb.getString("Frame.tableFullNameHeader"));
		subscribers.getColumnModel().getColumn(2).setHeaderValue(rb.getString("Frame.tableAddressHeader"));
		subscribers.getTableHeader().resizeAndRepaint();
		UIManager.put("FileChooser.openDialogTitleText", rb.getString("FileChooser.openDialogTitleText"));
		UIManager.put("FileChooser.saveDialogTitleText", rb.getString("FileChooser.saveDialogTitleText"));
		UIManager.put("FileChooser.lookInLabelText", rb.getString("FileChooser.lookInLabelText"));
		UIManager.put("FileChooser.saveInLabelText", rb.getString("FileChooser.saveInLabelText"));
		UIManager.put("FileChooser.upFolderToolTipText", rb.getString("FileChooser.upFolderToolTipText"));
		UIManager.put("FileChooser.homeFolderToolTipText", rb.getString("FileChooser.homeFolderToolTipText"));
		UIManager.put("FileChooser.newFolderToolTipText", rb.getString("FileChooser.newFolderToolTipText"));
		UIManager.put("FileChooser.listViewButtonToolTipText", rb.getString("FileChooser.listViewButtonToolTipText"));
		UIManager.put("FileChooser.detailsViewButtonToolTipText", rb.getString("FileChooser.detailsViewButtonToolTipText"));
		UIManager.put("FileChooser.fileNameHeaderText", rb.getString("FileChooser.fileNameHeaderText"));
	    UIManager.put("FileChooser.fileSizeHeaderText", rb.getString("FileChooser.fileSizeHeaderText"));
	    UIManager.put("FileChooser.fileDateHeaderText", rb.getString("FileChooser.fileDateHeaderText"));
	    UIManager.put("FileChooser.fileSizeKiloBytes", rb.getString("FileChooser.fileSizeKiloBytes"));
	    UIManager.put("FileChooser.fileSizeMegaBytes", rb.getString("FileChooser.fileSizeMegaBytes"));
	    UIManager.put("FileChooser.fileSizeGigaBytes", rb.getString("FileChooser.fileSizeGigaBytes"));
	    UIManager.put("FileChooser.viewMenuLabelText", rb.getString("FileChooser.viewMenuLabelText"));
	    UIManager.put("FileChooser.listViewActionLabelText", rb.getString("FileChooser.listViewActionLabelText"));
        UIManager.put("FileChooser.detailsViewActionLabelText", rb.getString("FileChooser.detailsViewActionLabelText"));
        UIManager.put("FileChooser.refreshActionLabelText", rb.getString("FileChooser.refreshActionLabelText"));
        UIManager.put("FileChooser.newFolderActionLabelText", rb.getString("FileChooser.newFolderActionLabelText"));
	    UIManager.put("FileChooser.other.newFolder", rb.getString("FileChooser.newFolder"));
	    UIManager.put("FileChooser.other.newFolder.subsequent", rb.getString("FileChooser.newFolder.subsequent"));	    
	    UIManager.put("FileChooser.fileNameLabelText", rb.getString("FileChooser.fileNameLabelText"));
	    UIManager.put("FileChooser.filesOfTypeLabelText", rb.getString("FileChooser.filesOfTypeLabelText"));
	    UIManager.put("FileChooser.acceptAllFileFilterText", rb.getString("FileChooser.acceptAllFileFilterText"));
	    UIManager.put("FileChooser.openButtonText", rb.getString("FileChooser.openButtonText"));
	    UIManager.put("FileChooser.openButtonToolTipText", rb.getString("FileChooser.openButtonToolTipText"));
		UIManager.put("FileChooser.directoryOpenButtonText", rb.getString("FileChooser.directoryOpenButtonText"));
		UIManager.put("FileChooser.directoryOpenButtonToolTipText", rb.getString("FileChooser.directoryOpenButtonToolTipText"));
		UIManager.put("FileChooser.cancelButtonText", rb.getString("FileChooser.cancelButtonText"));
		UIManager.put("FileChooser.cancelButtonToolTipText", rb.getString("FileChooser.cancelButtonToolTipText"));
		UIManager.put("FileChooser.saveButtonText", rb.getString("FileChooser.saveButtonText"));
		UIManager.put("FileChooser.saveButtonToolTipText", rb.getString("FileChooser.saveButtonToolTipText"));
		UIManager.put("FileChooser.renameErrorTitleText", rb.getString("FileChooser.renameErrorTitleText"));
		UIManager.put("FileChooser.renameErrorText", rb.getString("FileChooser.renameErrorText"));
		UIManager.put("FileChooser.renameErrorFileExistsText", rb.getString("FileChooser.renameErrorFileExistsText"));
	}
	
	public void reset() {
		number.setText("");
		surname.setText("");
		name.setText("");
		patronymic.setText("");
		populatedArea.setText("");
		street.setText("");
		house.setText("");
		flat.setText("");
		update.setEnabled(false);
		delete.setEnabled(false);
		subscribers.clearSelection();
	}
	
	public void setUpdateEnabled(boolean enabled) {
		update.setEnabled(enabled);
	}
	
	public void setDeleteEnabled(boolean enabled) {
		delete.setEnabled(enabled);
	}
	
	public long getNumber() {
		return !number.getText().isBlank() ? Long.parseLong(number.getText()) : 0L;
	}
	
	public void setNumber(long number) {
		this.number.setText(Long.toString(number));
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
		return !patronymic.getText().isBlank() ? patronymic.getText() : null;
	}
	
	public void setPatronymic(String patronymic) {
		this.patronymic.setText(patronymic != null ? patronymic : "");
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
		return !house.getText().isBlank() ? Integer.parseInt(house.getText()) : 0;
	}
	
	public void setHouse(int house) {
		this.house.setText(Integer.toString(house));
	}
	
	public int getFlat() {
		return !flat.getText().isBlank() ? Integer.parseInt(flat.getText()) : 0;
	}
	
	public void setFlat(int flat) {
		this.flat.setText(flat > 0 ? Integer.toString(flat) : "");
	}
	
	public void addSubscriber(long number, String fullName, String address) {
		SimpleTableModel model = (SimpleTableModel)subscribers.getModel();
		model.addRow(number, fullName, address);
		model.fireTableDataChanged();
	}
	
	public long getSelectedSubscriberNumber() {
		int selectedRow = subscribers.convertRowIndexToModel(subscribers.getSelectedRow());
		SimpleTableModel model = (SimpleTableModel)subscribers.getModel();
		return (long)model.getValueAt(selectedRow, 0);
	}
	
	public void updateSubscriber(long number, String fullName, String address) {
		int selectedRow = subscribers.convertRowIndexToModel(subscribers.getSelectedRow());
		SimpleTableModel model = (SimpleTableModel)subscribers.getModel();
		model.setValueAt(number, selectedRow, 0);
		model.setValueAt(fullName, selectedRow, 1);
		model.setValueAt(address, selectedRow, 2);
		model.fireTableDataChanged();
	}
	
	public void deleteSubscriber() {
		int selectedRow = subscribers.convertRowIndexToModel(subscribers.getSelectedRow());
		SimpleTableModel model = (SimpleTableModel)subscribers.getModel();
		model.deleteRow(selectedRow);
		model.fireTableDataChanged();
	}
	
	public void deleteAllSubscribers() {
		SimpleTableModel model = (SimpleTableModel)subscribers.getModel();
		model.clear();
		model.fireTableDataChanged();
	}
	
	public void addOpenActionListener(ActionListener listener) {
		open.addActionListener(listener);
	}
	
	public void addSaveActionListener(ActionListener listener) {
		save.addActionListener(listener);
	}
	
	public void addExitActionListener(ActionListener listener) {
		exit.addActionListener(listener);
	}
	
	public void addLanguageActionListener(ActionListener listener) {
		language.addActionListener(listener);
	}
	
	public void addSubscribersMouseListener(MouseListener listener) {
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
	
	public Optional<File> showOpenDialog() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("DAT", "dat");
		fileChooser.setFileFilter(filter);
		return fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION ? Optional.ofNullable(fileChooser.getSelectedFile()) : Optional.empty();
	}
	
	public Optional<File> showSaveDialog() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("DAT", "dat");
		fileChooser.setFileFilter(filter);
		return fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION ? Optional.ofNullable(fileChooser.getSelectedFile()) : Optional.empty();
	}
	
	public void showExceptionMessage(String message) {
		JOptionPane.showMessageDialog(this, message, "", JOptionPane.ERROR_MESSAGE);
	}
	
}