/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DTO.NhanVien;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author MSI
 */
public class ChiTietSanPhamGUI extends JFrame{

    public String absolutePath = new File("").getAbsolutePath();
    private JTextField txtIDSP;
    private JTextField txtTenSP;
    private JTextField txtGiaBan;
    private JTextField txtXuatXu;
    private JTextField txtNamSX;
    private JTextField txtKhuyenMai;
    private JComboBox cbbTheLoai;
    private JComboBox cbbHangSX;
    private JLabel lblAnhSP;
    private JButton btnThayDoiAnh;
    private JButton btnLuu;
    private JButton btnHuy;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JLabel jLabel1;
    private ImageIcon icon;
    
    public ChiTietSanPhamGUI() {
        
        addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng chi tiết nhân viên không?", "Xác nhận đóng chi tiết nhân viên", JOptionPane.YES_NO_OPTION);
    	        if (choice == JOptionPane.YES_OPTION) {
    	            dispose();
    	        }
    		}
    	});
        
        int width = 1040;
        int height = 600;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, width, height);
        setLocationRelativeTo(null);
        setTitle("Thông tin sản phẩm");
        setBackground(new Color(230, 230, 230));
	setLayout(new BorderLayout(10, 10));
        
        jPanel1 = new JPanel();
        jPanel1.setBackground(new java.awt.Color(36, 136, 203));
        jPanel1.setPreferredSize(new java.awt.Dimension(1040, 40));
        jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));

        jLabel1=new JLabel();
        jLabel1.setFont( new Font("Tahoma", Font.PLAIN, 24));
        jLabel1.setText("Thông tin chi tiết sản phẩm");
        jPanel1.add(jLabel1);
        add(jPanel1, BorderLayout.NORTH);
        
        txtIDSP = new JTextField();
        txtIDSP.setBorder(BorderFactory.createTitledBorder(null, "ID sản phẩm", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 14)));
//        txtIDSP.setPreferredSize(new Dimension(200, 40));
        txtIDSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        txtTenSP = new JTextField();
        txtTenSP.setBorder(BorderFactory.createTitledBorder(null, "Tên sản phẩm", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 14)));
//        txtTenSP.setPreferredSize(new Dimension(200, 40));
        txtTenSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        txtGiaBan = new JTextField();
        txtGiaBan.setBorder(BorderFactory.createTitledBorder(null, "Giá bán", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 14)));
//        txtGiaBan.setPreferredSize(new Dimension(200, 40));
        txtGiaBan.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        txtXuatXu = new JTextField();
        txtXuatXu.setBorder(BorderFactory.createTitledBorder(null, "Xuất xứ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 14)));
//        txtXuatXu.setPreferredSize(new Dimension(200, 40));
        txtXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        txtNamSX = new JTextField();
        txtNamSX.setBorder(BorderFactory.createTitledBorder(null, "Năm sản xuất", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 14)));
//        txtNamSX.setPreferredSize(new Dimension(200, 40));
        txtNamSX.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        txtKhuyenMai = new JTextField();
        txtKhuyenMai.setBorder(BorderFactory.createTitledBorder(null, "Khuyến mãi", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 14)));
//        txtKhuyenMai.setPreferredSize(new Dimension(200, 40));
        txtKhuyenMai.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        cbbHangSX = new JComboBox();
        cbbHangSX.setBorder(BorderFactory.createTitledBorder(null, "Hãng sản xuất", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 14)));
//        cbbHangSX.setPreferredSize(new Dimension(200, 40));
        cbbHangSX.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        cbbTheLoai = new JComboBox();
        cbbTheLoai.setBorder(BorderFactory.createTitledBorder(null, "Thể loại", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 14)));
//        cbbTheLoai.setPreferredSize(new Dimension(200, 40));
        cbbTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        lblAnhSP = new JLabel("");
        lblAnhSP.setPreferredSize(new Dimension(50, 200));
        lblAnhSP.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        icon = new ImageIcon(absolutePath+"/src/images/icons/MyPictures.24.png");
        btnThayDoiAnh = new JButton("Thay đổi ảnh", icon);
//        btnThayDoiAnh.setPreferredSize(new Dimension(200, 40));
        btnThayDoiAnh.setFont(new Font("Tahoma", Font.PLAIN, 14));
        icon = new ImageIcon(absolutePath+"/src/images/icons/save.24.png");
        btnLuu = new JButton("Lưu",icon);
//        btnLuu.setPreferredSize(new Dimension(200, 40));
        btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        icon = new ImageIcon(absolutePath+"/src/images/icons/delete.png");
        btnHuy = new JButton("Hủy",icon);
//        btnHuy.setPreferredSize(new Dimension(200, 40));
        btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        jPanel2 = new JPanel(new GridBagLayout());
        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new GridBagLayout());
        jPanel2.setPreferredSize(new Dimension(1040,560));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.5;
        gbc.weighty=1.5;
        
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=2;
        gbc.gridheight=1;
        jPanel2.add(txtIDSP, gbc);
        gbc.gridy=1;
        jPanel2.add(txtTenSP, gbc);
        gbc.gridy=2;
        jPanel2.add(txtGiaBan, gbc);
        gbc.gridy=3;
        jPanel2.add(cbbHangSX, gbc);
        
        gbc.gridx=2;
        gbc.gridy=0;
        jPanel2.add(txtXuatXu, gbc);
        gbc.gridy=1;
        jPanel2.add(txtNamSX, gbc);
        gbc.gridy=2;
        jPanel2.add(txtKhuyenMai, gbc);
        gbc.gridy=3;
        jPanel2.add(cbbTheLoai, gbc);
        
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 4;
        jPanel2.add(lblAnhSP, gbc);

        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        jPanel2.add(btnThayDoiAnh, gbc);

        JPanel btnPanel = new JPanel(new GridBagLayout());
        btnPanel.setBackground(Color.WHITE);

        GridBagConstraints btnGbc = new GridBagConstraints();
        btnGbc.insets = new Insets(10, 10, 10, 10);
        btnGbc.anchor = GridBagConstraints.WEST;
        btnGbc.fill = GridBagConstraints.HORIZONTAL;
        btnGbc.weightx=1.5;
        btnGbc.weighty=1.5;
        
        btnGbc.gridx = 0;
        btnGbc.gridy = 0;
        btnGbc.gridwidth = 1;
        btnGbc.gridheight = 1;
        btnPanel.add(btnLuu, btnGbc);

        btnGbc.gridx = 1;
        btnPanel.add(btnHuy, btnGbc);

        gbc.gridx = 4; 
        gbc.gridy = 4; 
        gbc.gridwidth = 3; 
        jPanel2.add(btnPanel, gbc);
        
        add(jPanel2);
    }
    
    
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
                        @Override
			public void run() {
				try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					ChiTietSanPhamGUI frame = new ChiTietSanPhamGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
}
