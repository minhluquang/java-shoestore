package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class homeUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int x = 210; // chieu rong
	private int y = 733; // chieu cao
	private JPanel mainMenu;

	public homeUI() {
		setTitle("Danh sách sản phẩm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 800);
		setLocationRelativeTo(null);
		// Tạo menu bar
		// Tạo JPanel chứa menu bar

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 149, 751);
		menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.Y_AXIS));

		// Tạo các menu
		JMenu homeMenu = new JMenu("Trang chủ");
		homeMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		JMenu productMenu = new JMenu("Sản phẩm");
		productMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		JMenu invoiceMenu = new JMenu("Bảo hành");
		invoiceMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		JMenu statisticsMenu = new JMenu("Thống kê");
		statisticsMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		JMenu troGiupMenuItem = new JMenu("Trợ giúp");
		troGiupMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		JMenu loginMenuItem = new JMenu("Đăng nhập");
		loginMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		// JMenu logOutMenuItem = new JMenu("Đăng xuất");
		// logOutMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		// Thêm các menu vào menu bar
		menuBar.add(homeMenu);
		menuBar.add(productMenu);
		menuBar.add(invoiceMenu);
		menuBar.add(statisticsMenu);
		menuBar.add(troGiupMenuItem);
		menuBar.add(loginMenuItem);
		// menuBar.add(logOutMenuItem);

		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(null);
		menuPanel.add(menuBar);

		// Danh sách sản phẩm
		String[] products = { "Sản phẩm 1", "Sản phẩm 2", "Sản phẩm 3", "Sản phẩm 4", "Sản phẩm 5", "Sản phẩm 6" };

		// Tạo JPanel chứa danh sách sản phẩm
		JPanel productPanel = new JPanel(new GridLayout(0, 3, 10, 10));
		productPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Thêm từng sản phẩm vào JPanel
		for (String product : products) {
			JPanel itemPanel = createProductPanel(product);
			productPanel.add(itemPanel);
		}

		// Tạo JSplitPane chứa menu bên trái và danh sách sản phẩm bên phải
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuPanel, productPanel);
		splitPane.setDividerLocation(150); // Thiết lập vị trí chia phần tử
		splitPane.setResizeWeight(0.0); // Không cho phép thay đổi kích thước menu khi thay đổi cửa sổ
		// Thêm JSplitPane vào content pane
		setContentPane(splitPane);
		String[] products2 = { "Sản phẩm 7", "Sản phẩm 7", "Sản phẩm 7", "Sản phẩm 7", "Sản phẩm 7", "Sản phẩm 7" };

		// Tạo JPanel chứa danh sách sản phẩm
		JPanel productPanel2 = new JPanel(new GridLayout(0, 3, 10, 10));
		productPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Thêm từng sản phẩm vào JPanel
		for (String product : products2) {
			JPanel itemPanel = createProductPanel(product);
			productPanel2.add(itemPanel);
		}

		homeMenu.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				splitPane.setRightComponent(productPanel);
				splitPane.setLeftComponent(menuBar);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// Hiển thị JFrame
		invoiceMenu.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// TODO Auto-generated method stub
				splitPane.setRightComponent(BaoHanhGUI.baoHanhGUI());
				splitPane.setLeftComponent(menuBar);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		productMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(homeUI.this, "Danh sách sản phẩm");
				splitPane.setRightComponent(productPanel2);

			}
		});
		statisticsMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(homeUI.this, "Thống kê");
			}
		});

		troGiupMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(homeUI.this, "Trợ giúp");
			}
		});

		loginMenuItem.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				splitPane.setRightComponent(DangNhapGUI.dangNhapGUI());
				splitPane.setLeftComponent(menuBar);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		setVisible(true);
	}

	private JMenuBar createMenuBar() {
		// Tạo menu bar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 149, 751);
		menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.Y_AXIS));

		// Tạo các menu
		JMenu homeMenu = new JMenu("Trang chủ");
		homeMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		JMenu productMenu = new JMenu("Sản phẩm");
		productMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		JMenu invoiceMenu = new JMenu("Bảo hành");
		invoiceMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		JMenu statisticsMenu = new JMenu("Thống kê");
		statisticsMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		JMenu troGiupMenuItem = new JMenu("Trợ giúp");
		troGiupMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		JMenu loginMenuItem = new JMenu("Đăng nhập");
		loginMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		// JMenu logOutMenuItem = new JMenu("Đăng xuất");
		// logOutMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		// Thêm các menu vào menu bar
		menuBar.add(homeMenu);
		menuBar.add(productMenu);
		menuBar.add(invoiceMenu);
		menuBar.add(statisticsMenu);
		menuBar.add(troGiupMenuItem);
		menuBar.add(loginMenuItem);
		// menuBar.add(logOutMenuItem);

		return menuBar;
	}

	private JPanel createProductPanel(String productName) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		JLabel nameLabel = new JLabel(productName);
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(nameLabel, BorderLayout.CENTER);

		return panel;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(homeUI::new);
	}
}
