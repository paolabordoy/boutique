import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HubWindow {

	private JFrame frame;
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					HubWindow window = new HubWindow();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public HubWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 345, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Null layout
		frame.getContentPane().setLayout(null);
		
		//Top panel w/ stuff
		JPanel topPanel = new JPanel();
		topPanel.setBounds(10, 11, 309, 75);
		frame.getContentPane().add(topPanel);
		topPanel.setLayout(new BorderLayout(0, 0)); //Border layout to center label
		
		JLabel lblTop = new JLabel("Menu");
		lblTop.setFont(new Font("Eras Medium ITC", Font.BOLD | Font.ITALIC, 40));
		lblTop.setHorizontalAlignment(SwingConstants.CENTER);
		topPanel.add(lblTop, BorderLayout.CENTER); //Centers label
		
		//Bottom panel w/ stuff
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBounds(10, 97, 309, 233);
		frame.getContentPane().add(bottomPanel);
		bottomPanel.setLayout(null);
//		
//		JLabel lblUsers = new JLabel("Add New Users");
//		lblUsers.setHorizontalAlignment(SwingConstants.LEFT);
//		lblUsers.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		lblUsers.setBounds(101, 122, 120, 31);
//		bottomPanel.add(lblUsers);
		
		

//		BufferedImage myPicture = Images.logOut;
		BufferedImage myPicture = null;
		try {
			//myPicture = ImageIO.read(new File("/SiciCapstone/res/Images/logOut.png"));
			myPicture = ImageIO.read(getClass().getResourceAsStream("/Images/logOut.png"));
			//myPicture = ImageIO.read(new File("C:\\Users\\Josean\\Desktop\\Todo lo de gaby\\todo java\\SiciCapstone\\res\\Images\\logOut.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		JButton btnLogOut = new JButton("");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StartAppWindow().getFrame().setVisible(true);
				getFrame().dispose();
			}
		});

		btnLogOut.setBounds(113, 175, 60, 58);
		//btnNewButton.setBounds(122, 18041, 42126, 104, 77);
		
		Image i = myPicture.getScaledInstance(btnLogOut.getWidth(), btnLogOut.getHeight(), Image.SCALE_SMOOTH);
		btnLogOut.setIcon(new ImageIcon (i));
		
		bottomPanel.add(btnLogOut);
		///////////////////////////////////////////

		
		
		JButton btnCash = new JButton("CashRegister");
		btnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CashRegister cr = new CashRegister();
				cr.getFrame().setVisible(true);
				getFrame().dispose();
			}
		});
		btnCash.setBounds(90, 78, 115, 30);
		bottomPanel.add(btnCash);
		
		JButton btnUsers = new JButton("AddUsers");
		btnUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUser au = new AddUser();
				au.getFrame().setVisible(true);
				getFrame().dispose();
			}
		});
		btnUsers.setBounds(90, 134, 115, 30);
		bottomPanel.add(btnUsers);
		
		JButton btnInventory = new JButton("Inventory");
		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Inventory().getFrame().setVisible(true);
				getFrame().dispose();
			}
		});
		btnInventory.setBounds(90, 21, 115, 30);
		bottomPanel.add(btnInventory);
		
		
	}
}
