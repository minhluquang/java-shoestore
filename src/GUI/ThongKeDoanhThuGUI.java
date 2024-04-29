package GUI;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThongKeDoanhThuGUI extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtTrangDoanhThu;
	private JPanel pnlCards;
	private CardLayout cardLayout;
	private JPanel pnl_ngay;
	private JPanel pnl_thang;
	private JPanel pnl_nam;
	private JButton btn_ngay;
	private JButton btn_thang;
	private JButton btn_nam;
	private JPanel pnl_khoangNgay;
	private JButton btn_khoangNgay;

	/**
	 * Create the panel.
	 */
	public ThongKeDoanhThuGUI() {
		setLayout(new BorderLayout(0, 10));
		
		 pnlCards = new JPanel();
		add(pnlCards, BorderLayout.CENTER);
		pnlCards.setLayout(new CardLayout(0, 0));
		cardLayout = (CardLayout) pnlCards.getLayout();
		pnlCards.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		pnl_ngay = new JPanel();
		pnlCards.add(pnl_ngay, "pnl_ngay");
		pnl_ngay.setLayout(new BorderLayout(0,0));
		pnl_ngay.add(new ThongKeTheoNgayGUI(), BorderLayout.CENTER);
		
		pnl_thang = new JPanel();
		pnlCards.add(pnl_thang, "pnl_thang");
		pnl_thang.setLayout(new BorderLayout(0,0));
		pnl_thang.add(new ThongKeThangGUI(), BorderLayout.CENTER);
		
		pnl_nam = new JPanel();
		pnlCards.add(pnl_nam, "pnl_nam");
		pnl_nam.setLayout(new BorderLayout(0,0));
		pnl_nam.add(new ThongKeNamGUI(), BorderLayout.CENTER);
		
		pnl_khoangNgay = new JPanel();
		pnlCards.add(pnl_khoangNgay, "pnl_khoangNgay");
		pnl_khoangNgay.setLayout(new BorderLayout(0,0));
		pnl_khoangNgay.add(new ThongKeTheoKhoangNgayGUI(), BorderLayout.CENTER);
		
		
		
		JPanel pnlTopBottom = new JPanel();
		pnlTopBottom.setBackground(Color.WHITE);
		add(pnlTopBottom, BorderLayout.NORTH);
		pnlTopBottom.setLayout(new GridLayout(0, 4, 5, 0));
		
		
		btn_ngay = new JButton("Thống kê từng ngày của tháng");
		btn_ngay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_ngay.setPreferredSize(new Dimension(150, 20));
		btn_ngay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_ngay.setFocusable(false);
		btn_ngay.setBackground(Color.WHITE);
		pnlTopBottom.add(btn_ngay);
		
		btn_thang = new JButton("Thống kê từng tháng của năm");
		btn_thang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_thang.setPreferredSize(new Dimension(150, 20));
		btn_thang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_thang.setFocusable(false);
		btn_thang.setBackground(Color.WHITE);
		pnlTopBottom.add(btn_thang);
		
		btn_nam = new JButton("Thống kê theo năm");
		btn_nam.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_nam.setPreferredSize(new Dimension(150, 20));
		btn_nam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_nam.setFocusable(false);
		btn_nam.setBackground(Color.WHITE);
		pnlTopBottom.add(btn_nam);
		
		btn_khoangNgay = new JButton("Thống kê theo khoảng ngày");
		btn_khoangNgay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_khoangNgay.setPreferredSize(new Dimension(150, 20));
		btn_khoangNgay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_khoangNgay.setFocusable(false);
		btn_khoangNgay.setBackground(Color.WHITE);
		pnlTopBottom.add(btn_khoangNgay);
		
		
		
		btn_nam.addActionListener(this);
		btn_ngay.addActionListener(this);
		btn_thang.addActionListener(this);
		btn_khoangNgay.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == btn_ngay) {
	        cardLayout.show(pnlCards, "pnl_ngay");
	        btn_ngay.setBackground(new Color(163, 163, 163)); 
	        resetButtonColors(btn_ngay);
	    } else if (e.getSource() == btn_nam) {
	        cardLayout.show(pnlCards, "pnl_nam");
	        btn_nam.setBackground(new Color(163, 163, 163)); 
	        resetButtonColors(btn_nam);
	    }  else if (e.getSource() == btn_thang) {
	        cardLayout.show(pnlCards, "pnl_thang");
	        btn_thang.setBackground(new Color(163, 163, 163)); 
	        resetButtonColors(btn_thang);
	    }
	    else if (e.getSource() == btn_khoangNgay) {
	        cardLayout.show(pnlCards, "pnl_khoangNgay");
	        btn_thang.setBackground(new Color(163, 163, 163)); 
	        resetButtonColors(btn_khoangNgay);
	    }
	}
	

	
	// ========== Start: Reset màu btns ==========
	private void resetButtonColors(JButton selectedButton) {
	    JButton[] buttons = {btn_nam,  btn_ngay, btn_thang};
	    for (JButton button : buttons) {
	        if (button != selectedButton) {
	            button.setBackground(Color.WHITE);
	        }
	    }
	}

}
