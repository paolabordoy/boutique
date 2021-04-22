import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class CashRegister extends DbConnector {

	private JFrame frame;
	private JTable table;
	private JTextField txtInput;
	
	public JTable getTable() {
		return table;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void setTable(JTable table) {
		this.table = table;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CashRegister window = new CashRegister();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CashRegister() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setUpDB();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(598, 549);

		frame.getContentPane().setLayout(null);
		//frame.getContentPane().getHeight()
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Quantity", "Price"
			}
		));
		table.setBackground(Color.WHITE);
		table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		//Adjusts itself based on frame
		scrollPane.setBounds(10, 70, frame.getWidth()/2, frame.getHeight()-120);

		frame.getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 478, 48);
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCashRegister = new JLabel("Cash Register");
		lblCashRegister.setFont(new Font("Eras Medium ITC", Font.BOLD | Font.ITALIC, 40));
		panel.add(lblCashRegister, BorderLayout.CENTER);
		
		JPanel inputPanel = new JPanel();
		inputPanel.setBounds(319, 70, 253, 429);
		frame.getContentPane().add(inputPanel);
		
		inputPanel.setLayout(null);
		
		txtInput = new JTextField();
		txtInput.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		txtInput.setColumns(10);
		txtInput.setBounds(0, 25, 183, 23);
		inputPanel.add(txtInput);
		
		JLabel lblSearch = new JLabel("Search Item:");
		lblSearch.setBounds(0, 0, 133, 23);
		inputPanel.add(lblSearch);
		lblSearch.setFont(new Font("Eras Medium ITC", Font.BOLD, 20));
		
		JButton btnSearch = new JButton();
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sku = txtInput.getText();
				
				String query = "select stock_tb.stock_id, inventory_tb.sku, inventory_tb.inventory_name, inventory_tb.brand, inventory_tb.color, inventory_tb.inventory_size, inventory_tb.list_price, stock_tb.available\r\n" + 
						"from inventory_tb, stock_tb\r\n" + 
						"where (\r\n" + 
						"	inventory_tb.sku = '" + sku + "'\r\n" + 
						"	   and stock_tb.available > 0\r\n" + 
						"	   and inventory_tb.inventory_id = stock_tb.inventory_id\r\n" + 
						"	  );";

				try {
					Statement s = getConnection().createStatement();
					ResultSet r = s.executeQuery(query);
					
					//listModel.contains(r.getString("inventory_name"))
				
					if(r.next()) {
						
						String name = r.getString("inventory_name");
						String brand = r.getString("brand");
						String color = r.getString("color");
						String size = r.getString("inventory_size");
						String price = r.getString("list_price");
						int available = r.getInt("available");
						
						ProductInformation pi = new ProductInformation(name, brand, color, size, price, available);
						pi.getFrame().setVisible(true);

						if(pi.isReadyToAdd()) {

							DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
							tableModel.addRow(new Object[]{pi.getName(), pi.getQuantity(), pi.getPrice()});

						}

					} else {
						JOptionPane.showMessageDialog(frame,
							    "Item not found",
							    "Error 404",
							    JOptionPane.ERROR_MESSAGE);
					}
				
				}catch (SQLException sq) {
					sq.printStackTrace();
				}
			
				
			}
		});
		btnSearch.setBounds(193, 11, 50, 37);
		inputPanel.add(btnSearch);
		
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(getClass().getResourceAsStream("/Images/searchIcon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image i = myPicture.getScaledInstance(btnSearch.getWidth(), btnSearch.getHeight(), Image.SCALE_SMOOTH);
		btnSearch.setIcon(new ImageIcon (i));
		
		JButton btnBack = new JButton("");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HubWindow().getFrame().setVisible(true);
				getFrame().dispose();
				
			}
		});
		btnBack.setBounds(487, 30, 85, 29);
		frame.getContentPane().add(btnBack);

		BufferedImage myPicture1 = null;
		try {
			myPicture1 = ImageIO.read(getClass().getResourceAsStream("/Images/backIcon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image i1 = myPicture1.getScaledInstance(btnBack.getWidth(), btnBack.getHeight(), Image.SCALE_SMOOTH);
		btnBack.setIcon(new ImageIcon (i1));
		
	}
}
