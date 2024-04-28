	package GUI;
	
	import javax.swing.JPanel;
	import java.awt.BorderLayout;
	import javax.swing.JTextField;
	import java.awt.Font;
	import java.awt.GridLayout;
	import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
	import javax.swing.ImageIcon;
	import javax.swing.JLabel;
	import java.awt.SystemColor;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import java.awt.Color;
	import java.awt.Component;
	import java.awt.Dimension;
	
	import javax.swing.UIManager;
	import javax.swing.SwingConstants;
	import java.awt.FlowLayout;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BUS.ThongKeDoanhThuBUS;
import DTO.ThongKeDoanhThuDTO;

import javax.swing.ListSelectionModel;
	import javax.swing.ScrollPaneConstants;
	
	public class ThongKeTongQuanGUI extends JPanel {
		public String absolutePath = new File("").getAbsolutePath();
	
		private static final long serialVersionUID = 1L;
		private JTable tblTongQuan;
	
		private DefaultTableModel dtmTongQuan;
	
	
		/**
		 * Create the panel.
		 */
		public ThongKeTongQuanGUI() {
			setLayout(new BorderLayout(0, 0));
			
			JPanel pnlTop = new JPanel();
			add(pnlTop, BorderLayout.NORTH);
			pnlTop.setLayout(new GridLayout(0, 3, 30, 0));
			pnlTop.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
	
			
			JPanel pnlSP = new JPanel();
			pnlSP.setBackground(new Color(255, 255, 255));
			pnlTop.add(pnlSP);
			pnlSP.setLayout(new BorderLayout(10, 0));
			
			JLabel lblIconSP = new JLabel("");
			lblIconSP.setIcon(new ImageIcon("C:\\Users\\User\\OneDrive\\Máy tính\\JAVA\\java-shoestore\\src\\images\\icons\\sp.png"));
			pnlSP.add(lblIconSP, BorderLayout.WEST);
			lblIconSP.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
			
			JPanel pnlSPContent = new JPanel();
			pnlSPContent.setBackground(new Color(255, 255, 255));
			pnlSP.add(pnlSPContent);
			pnlSPContent.setLayout(new GridLayout(0, 1, 0, 0));
			pnlSPContent.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
			
			JLabel lblSp = new JLabel("100");
			lblSp.setHorizontalAlignment(SwingConstants.LEFT);
			pnlSPContent.add(lblSp);
			lblSp.setForeground(new Color(70, 130, 180));
			lblSp.setFont(new Font("Tahoma", Font.PLAIN, 20));
			
			JLabel lblNewLabel_1 = new JLabel("Sản phẩm hiện có trong kho");
			pnlSPContent.add(lblNewLabel_1);
			lblNewLabel_1.setForeground(new Color(70, 130, 180));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
			
			JPanel pnlKH = new JPanel();
			pnlKH.setBackground(new Color(255, 255, 255));
			pnlTop.add(pnlKH);
			pnlKH.setLayout(new BorderLayout(10, 0));
			
			JLabel lblIconKH = new JLabel("");
			lblIconKH.setIcon(new ImageIcon("C:\\Users\\User\\OneDrive\\Máy tính\\JAVA\\java-shoestore\\src\\images\\icons\\kh.png"));
			pnlKH.add(lblIconKH, BorderLayout.WEST);
			lblIconKH.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
			
			JPanel pnlKHContent = new JPanel();
			pnlKH.add(pnlKHContent);
			pnlKHContent.setBackground(new Color(255, 255, 255));
			pnlKHContent.setLayout(new GridLayout(0, 1, 0, 0));
			pnlKHContent.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
			
			JLabel lblKH = new JLabel("100");
			pnlKHContent.add(lblKH);
			lblKH.setForeground(new Color(70, 130, 180));
			lblKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
			
			
			JLabel lblNewLabel_1_1 = new JLabel("Khách hàng từ trước tới nay");
			pnlKHContent.add(lblNewLabel_1_1);
			lblNewLabel_1_1.setForeground(new Color(70, 130, 180));
			lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
			
			JPanel pnlNV = new JPanel();
			pnlNV.setBackground(new Color(255, 255, 255));
			pnlTop.add(pnlNV);
			pnlNV.setLayout(new BorderLayout(10, 0));
			
			JLabel lblIconNV = new JLabel("");
			lblIconNV.setIcon(new ImageIcon("C:\\Users\\User\\OneDrive\\Máy tính\\JAVA\\java-shoestore\\src\\images\\icons\\nv.png"));
			pnlNV.add(lblIconNV, BorderLayout.WEST);
			lblIconNV.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
			
			JPanel pnlNVContent = new JPanel();
			pnlNV.add(pnlNVContent);
			pnlNVContent.setBackground(new Color(255, 255, 255));
			pnlNVContent.setLayout(new GridLayout(0, 1, 0, 0));
			pnlNVContent.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
			
			JLabel lblNV = new JLabel("100");
			pnlNVContent.add(lblNV);
			lblNV.setForeground(new Color(70, 130, 180));
			lblNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
			
			JLabel lblNewLabel_1_1_1 = new JLabel("Nhân viên đang hoạt động");
			pnlNVContent.add(lblNewLabel_1_1_1);
			lblNewLabel_1_1_1.setForeground(new Color(70, 130, 180));
			lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
			
			JPanel pnlCenter = new JPanel();
			add(pnlCenter, BorderLayout.CENTER);
		
			pnlCenter.setBorder(BorderFactory.createEmptyBorder(30, 15, 10, 15));
			
			tblTongQuan = new JTable();
			
			tblTongQuan.setBorder(null);
			tblTongQuan.setSelectionBackground(new Color(232, 57, 95));
			tblTongQuan.setRowHeight(25);
			tblTongQuan.setIntercellSpacing(new Dimension(0, 0));
			tblTongQuan.setFocusable(false);
			
			dtmTongQuan = new DefaultTableModel(new Object[] {"Ngày","Tổng đơn nhập", "Vốn","Tổng hóa đơn", "Doanh thu", "Lợi nhuận"}, 0);
			tblTongQuan.setModel(dtmTongQuan);
			tblTongQuan.setDefaultEditor(Object.class, null);
			pnlCenter.setLayout(new BorderLayout(0, 0));
			tblTongQuan.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
			
			List<ThongKeDoanhThuDTO> dataList = ThongKeDoanhThuBUS.getThongKeDoanhThu();
			displayData();
			
			JScrollPane scrollPane = new JScrollPane(tblTongQuan);
			scrollPane.setBorder(null);
			scrollPane.setBackground(new Color(255, 255, 255));
			pnlCenter.add(scrollPane);
		
			((DefaultTableCellRenderer)tblTongQuan.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			tblTongQuan.setDefaultRenderer(Object.class, centerRenderer);
			
			tblTongQuan.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
			tblTongQuan.getTableHeader().setOpaque(false);
			tblTongQuan.getTableHeader().setBackground(new Color(36,136,203));
			tblTongQuan.getTableHeader().setForeground(new Color(255,255,255));
			tblTongQuan.setRowHeight(25);
			
			JLabel lblNewLabel =new JLabel("Thống kê doanh thu 7 ngày gần nhất");
			lblNewLabel.setForeground(new Color(102, 153, 204));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			pnlCenter.add(lblNewLabel, BorderLayout.NORTH);
			lblNewLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 30));
			
			
			tblTongQuan.clearSelection();			
			
			
			
	
		}
		
		public void displayData() {
	        List<ThongKeDoanhThuDTO> dataList = ThongKeDoanhThuBUS.getThongKeDoanhThu();
	        for (ThongKeDoanhThuDTO dto : dataList) {
	            Object[] row = { dto.getThoigian(),dto.getSlDonNhap(), dto.getVon(),dto.getSlHoaDon(), dto.getDoanhthu(), dto.getLoinhuan() };
	            dtmTongQuan.addRow(row);
	        }
	    }
		
//		public void display() {
//		    List<ThongKeDoanhThuDTO> dataList = ThongKeDoanhThuBUS.getThongKeDoanhThu();
//		    System.out.println(dataList);
//		    for (ThongKeDoanhThuDTO dto : dataList) {
//		        System.out.println("Ngày: " + dto.getThoigian());
//		        System.out.println("Vốn: " + dto.getVon());
//		        System.out.println("Doanh thu: " + dto.getDoanhthu());
//		        System.out.println("Lợi nhuận: " + dto.getLoinhuan());
//		        System.out.println("---------------------");
//		    }
//		}
	}
