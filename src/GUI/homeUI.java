
package GUI;
import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;
public class homeUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private int x = 210; // chiều rộng
    private int y = 733; // chiều cao
    private JPanel mainMenu;
    private JPanel cards;
    private CardLayout cardLayout;

    public homeUI() {
    	getContentPane().setBackground(Color.BLACK);
        setTitle("Danh sách sản phẩm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 800);
        setLocationRelativeTo(null);
        // Tạo menu bar
        JMenuBar menuBar = createMenuBar();
        
        // Tạo JPanel chứa danh sách sản phẩm
        JPanel productPanel = createProductPanel(new String[]{"Sản phẩm 1", "Sản phẩm 2", "Sản phẩm 3", "Sản phẩm 4", "Sản phẩm 5", "Sản phẩm 6"});

        // Tạo JPanel chứa danh sách sản phẩm 2
        JPanel productPanel2 = createProductPanel(new String[]{"Sản phẩm 7", "Sản phẩm 8", "Sản phẩm 9", "Sản phẩm 10", "Sản phẩm 11", "Sản phẩm 12"});
        
        
        JPanel baoHanhPanel = BaoHanhGUI.baoHanhGUI();
        JPanel loginPanel = DangNhapGUI.dangNhapGUI();
        QuanLyNhanVienGUI quanLyNhanVienPanel = new QuanLyNhanVienGUI();
        NhapHang nhapHangPanel = new NhapHang();
        // Tạo JPanel chứa các panel với CardLayout
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.add(new SanPham(), "PRODUCT_PANEL");
        cards.add(new QuanLySanPham(), "PRODUCT_PANEL_2");
        cards.add(loginPanel, "LOGIN_PANEL");
        cards.add(baoHanhPanel,"BAOHANH_PANEL");
        cards.add(quanLyNhanVienPanel, "NHANVIEN_PANEL");
        cards.add(nhapHangPanel, "NHAPHANG_PANEL");
        // Thêm menu bar và panel chứa cards vào content pane
        getContentPane().add(menuBar, BorderLayout.WEST);
        getContentPane().add(cards, BorderLayout.CENTER);
        // Hiển thị JFrame
        setVisible(true);
    }

    private JMenuBar createMenuBar() {
    	 // Thiết lập màu nền và màu chữ cho menu bar
        UIManager.put("Menu.foreground", Color.WHITE); // Màu trắng
        // Tạo menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(0, 0, 0));
        menuBar.setBounds(0, 0, 149, 751);
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.Y_AXIS));

        // Tạo các menu
        JMenu homeMenu = new JMenu("Trang chủ");
        homeMenu.setBackground(Color.BLACK);
        homeMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        homeMenu.setForeground(Color.WHITE);
        
        JMenu productMenu = new JMenu("Sản phẩm");
        productMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        productMenu.setForeground(Color.WHITE);
        
        JMenu invoiceMenu = new JMenu("Bảo hành");
        invoiceMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        invoiceMenu.setForeground(Color.WHITE);
        
        JMenu nhapHangMenu = new JMenu("Nhập hàng");
        nhapHangMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        nhapHangMenu.setForeground(Color.WHITE);
        
        JMenu statisticsMenu = new JMenu("Thống kê");
        statisticsMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        statisticsMenu.setForeground(Color.WHITE);
        
        JMenu nhanVienMenuItem = new JMenu("Nhân viên");
        nhanVienMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        nhanVienMenuItem.setForeground(Color.WHITE);
        
        JMenu loginMenuItem = new JMenu("Đăng nhập");
        loginMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        loginMenuItem.setForeground(Color.WHITE);

        // Thêm các menu vào menu bar
        menuBar.add(homeMenu);
        menuBar.add(productMenu);
        menuBar.add(invoiceMenu);
        menuBar.add(statisticsMenu);
        menuBar.add(nhanVienMenuItem);
        menuBar.add(nhapHangMenu);
        menuBar.add(loginMenuItem);
        menuBar.setBackground(Color.decode("#333333"));

        // Thêm sự kiện cho menu
        homeMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cards, "PRODUCT_PANEL");
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}
        });

        productMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cards, "PRODUCT_PANEL_2");
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}
        });

        invoiceMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	cardLayout.show(cards, "BAOHANH_PANEL");
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        
        nhapHangMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	cardLayout.show(cards, "NHAPHANG_PANEL");
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        
        
        statisticsMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle statistics menu click
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}
        });

        nhanVienMenuItem.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	cardLayout.show(cards, "NHANVIEN_PANEL");
            }
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}
        });

        loginMenuItem.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	 cardLayout.show(cards, "LOGIN_PANEL");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            	
            }

            @Override
            public void mouseExited(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}
        });

        return menuBar;
    }

    private JPanel createProductPanel(String[] products) {
        JPanel productPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        productPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (String product : products) {
            JPanel itemPanel = createProductItemPanel(product);
            productPanel.add(itemPanel);
        }

        return productPanel;
    }

    private JPanel createProductItemPanel(String productName) {
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

