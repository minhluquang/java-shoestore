package GUI;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThongKeGUI extends JPanel implements ActionListener  {

	private static final long serialVersionUID = 1L;
	private JPanel pnlTongQuan;
	private JPanel pnlTonKho;
	private JPanel pnlDoanhThu;
	private JPanel pnlKhachHang;
	private JPanel pnlCards;
	private JButton btnTongQuan;
	private JButton btnTonKho;
	private JButton btnDoanhThu;
	private JButton btnKhachHang;
	private CardLayout cardLayout;

	/**
	 * Create the panel.
	 */
	public ThongKeGUI() {
		setLayout(new BorderLayout(0, 10));
		
		 pnlCards = new JPanel();
		add(pnlCards, BorderLayout.CENTER);
		pnlCards.setLayout(new CardLayout(0, 0));
		cardLayout = (CardLayout) pnlCards.getLayout();

		pnlTongQuan = new JPanel();
		pnlCards.add(pnlTongQuan, "pnlTongQuan");
		pnlTongQuan.setLayout(new BorderLayout(0,0));
		pnlTongQuan.add(new ThongKeTongQuanGUI(), BorderLayout.CENTER);
		
		pnlTonKho = new JPanel();
		pnlCards.add(pnlTonKho, "pnlTonKho");
		pnlTonKho.setLayout(new BorderLayout(0,0));
		pnlTonKho.add(new ThongKeTonKhoGUI(), BorderLayout.CENTER);
		
		pnlDoanhThu = new JPanel();
		pnlCards.add(pnlDoanhThu, "pnlDoanhThu");
		pnlDoanhThu.setLayout(new BorderLayout(0,0));
		pnlDoanhThu.add(new ThongKeDoanhThuGUI(), BorderLayout.CENTER);
		
		pnlKhachHang = new JPanel();
		pnlCards.add(pnlKhachHang, "pnlKhachHang");
		pnlKhachHang.setLayout(new BorderLayout(0,0));
		pnlKhachHang.add(new ThongKeKhachHangGUI(), BorderLayout.CENTER);
		
		JPanel pnlTopBottom = new JPanel();
		pnlTopBottom.setBackground(Color.WHITE);
		add(pnlTopBottom, BorderLayout.NORTH);
		pnlTopBottom.setLayout(new GridLayout(0, 4, 20, 0));
		
		btnTongQuan = new JButton("Tổng quan");
		btnTongQuan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTongQuan.setPreferredSize(new Dimension(150, 40));
		btnTongQuan.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTongQuan.setFocusable(false);
		btnTongQuan.setBackground(Color.WHITE);
		pnlTopBottom.add(btnTongQuan);
		
		btnTonKho = new JButton("Tồn kho");
		btnTonKho.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTonKho.setPreferredSize(new Dimension(150, 40));
		btnTonKho.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTonKho.setFocusable(false);
		btnTonKho.setBackground(Color.WHITE);
		pnlTopBottom.add(btnTonKho);
		
		btnDoanhThu = new JButton("Doanh Thu");
		btnDoanhThu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDoanhThu.setPreferredSize(new Dimension(150, 40));
		btnDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDoanhThu.setFocusable(false);
		btnDoanhThu.setBackground(Color.WHITE);
		pnlTopBottom.add(btnDoanhThu);
		
		btnKhachHang = new JButton("Khách Hàng");
		btnKhachHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnKhachHang.setPreferredSize(new Dimension(150, 40));
		btnKhachHang.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnKhachHang.setFocusable(false);
		btnKhachHang.setBackground(Color.WHITE);
		pnlTopBottom.add(btnKhachHang);
		
		
		btnDoanhThu.addActionListener(this);
		btnKhachHang.addActionListener(this);
		btnTongQuan.addActionListener(this);
		btnTonKho.addActionListener(this);

		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == btnTongQuan) {
	        cardLayout.show(pnlCards, "pnlTongQuan");
	        btnTongQuan.setBackground(new Color(163, 163, 163)); 
	        resetButtonColors(btnTongQuan);
	    } else if (e.getSource() == btnDoanhThu) {
	        cardLayout.show(pnlCards, "pnlDoanhThu");
	        btnDoanhThu.setBackground(new Color(163, 163, 163)); 
	        resetButtonColors(btnDoanhThu);
	    } else if (e.getSource() == btnKhachHang ) {
	        cardLayout.show(pnlCards, "pnlKhachHang");
	        btnKhachHang.setBackground(new Color(163, 163, 163)); 
	        resetButtonColors(btnKhachHang);
	    } else if (e.getSource() == btnTonKho) {
	        cardLayout.show(pnlCards, "pnlTonKho");
	        btnTonKho.setBackground(new Color(163, 163, 163)); 
	        resetButtonColors(btnTonKho);
	    } 
	}
	

	
	// ========== Start: Reset màu btns ==========
	private void resetButtonColors(JButton selectedButton) {
	    JButton[] buttons = {btnDoanhThu, btnKhachHang, btnTongQuan, btnTonKho};
	    for (JButton button : buttons) {
	        if (button != selectedButton) {
	            button.setBackground(Color.WHITE);
	        }
	    }
	}


	

	
	
}