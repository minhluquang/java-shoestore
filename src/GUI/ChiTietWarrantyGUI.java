package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import BUS.WarrantyBUS;
import DTO.Warranty;

public class ChiTietWarrantyGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtWarrantyId;
    private JTextField txtProductId;
    private JTextField txtWarrantyDate;
    private JTextField txtReason;
    private JTextField txtActive;
    private JComboBox cmbTrangThai;
    private Warranty wt;
    private WarrantyGUI parentGUI;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    ChiTietWarrantyGUI frame = new ChiTietWarrantyGUI(new Warranty(), new WarrantyGUI());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public ChiTietWarrantyGUI(Warranty wt, WarrantyGUI parentGUI) {
        this.wt = wt;
        this.parentGUI = parentGUI;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng chi tiết bảo hành không?", "Xác nhận đóng chi tiết bảo hành", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
        int width = 400;
        int height = 600;
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, width, height);
        setLocationRelativeTo(null);
        setTitle("Thông tin bảo hành");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel pnlRight = new JPanel();
        pnlRight.setPreferredSize(new Dimension(200, 10));
        pnlRight.setBorder(null);
        pnlRight.setBackground(Color.WHITE);
        contentPane.add(pnlRight);
        pnlRight.setLayout(new BorderLayout(0, 10));
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        pnlRight.add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(0, 1, 0, 0));
        
        JLabel lblNewLabel_5 = new JLabel("");
        panel.add(lblNewLabel_5);
        
        JLabel lblNewLabel_4 = new JLabel("Thông tin bảo hành");
        lblNewLabel_4.setForeground(new Color(255, 255, 255));
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel.setBackground(new Color(36, 136, 203));
        panel.add(lblNewLabel_4);
        
        JLabel lblNewLabel_5_1 = new JLabel("");
        panel.add(lblNewLabel_5_1);
        
        JPanel panel_3 = new JPanel();
        pnlRight.add(panel_3, BorderLayout.CENTER);
        panel_3.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_4 = new JPanel();
        panel_4.setBackground(Color.WHITE);
        panel_3.add(panel_4, BorderLayout.CENTER);
        panel_4.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_5 = new JPanel();
        panel_5.setBackground(Color.WHITE);
        panel_4.add(panel_5);
        panel_5.setLayout(new GridLayout(0, 1, 0, 5));
        
        JLabel lblNewLabel_6_2 = new JLabel("WarrantyID");
        lblNewLabel_6_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(lblNewLabel_6_2);
        
        txtWarrantyId = new JTextField();
        txtWarrantyId.setEnabled(false);
        txtWarrantyId.setEditable(false);
        txtWarrantyId.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtWarrantyId.setColumns(10);
        panel_5.add(txtWarrantyId);
        
        JLabel lblNewLabel_6 = new JLabel("ProductID");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(lblNewLabel_6);
        
        txtProductId = new JTextField();
        txtProductId.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtProductId.setColumns(10);
        panel_5.add(txtProductId);
        
        JLabel lblNewLabel_6_3_1_2 = new JLabel("Warranty Date");
        lblNewLabel_6_3_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(lblNewLabel_6_3_1_2);
    
        txtWarrantyDate = new JTextField();
        txtWarrantyDate.setPreferredSize(new Dimension(100, 19));
        txtWarrantyDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtWarrantyDate.setColumns(10);
        panel_5.add(txtWarrantyDate);
        
        JLabel lblNewLabel_6_3_1_1 = new JLabel("Reason");
        lblNewLabel_6_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(lblNewLabel_6_3_1_1);
    
        txtReason = new JTextField();
        txtReason.setPreferredSize(new Dimension(100, 19));
        txtReason.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtReason.setColumns(10);
        panel_5.add(txtReason);
        
        JLabel lblNewLabel_5_2 = new JLabel("Active");
        panel_5.add(lblNewLabel_5_2);
        txtActive = new JTextField();
        txtActive.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtActive.setColumns(10);
        panel_5.add(txtActive);
        
        
        JLabel lblNewLabel_6_3_1_3 = new JLabel("Status");
        lblNewLabel_6_3_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(lblNewLabel_6_3_1_3);
        
        cmbTrangThai = new JComboBox();
        cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] {"1", "0"}));
        cmbTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cmbTrangThai.setFocusable(false);
        panel_5.add(cmbTrangThai);
        
        JLabel lblNewLabel_7_1_1 = new JLabel("");
        panel_5.add(lblNewLabel_7_1_1);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        panel_5.add(panel_2);
        panel_2.setLayout(new GridLayout(0, 2, 20, 0));
        
        JButton btnNewButton = new JButton("Lưu");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                xuLyLuuThongTinBaoHanh();
            }
        });
        btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton.setPreferredSize(new Dimension(100, 30));
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton.setFocusable(false);
        btnNewButton.setBorder(null);
        btnNewButton.setBackground(new Color(21, 155, 71));
        panel_2.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Huỷ bỏ");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn huỷ bỏ chỉnh sửa chi tiết bảo hành không?", "Xác nhận huỷ bỏ chỉnh sửa chi tiết bảo hành", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
        btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton_1.setPreferredSize(new Dimension(100, 30));
        btnNewButton_1.setForeground(Color.WHITE);
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton_1.setFocusable(false);
        btnNewButton_1.setBorder(null);
        btnNewButton_1.setBackground(new Color(220, 53, 69));
        panel_2.add(btnNewButton_1);
        
        JPanel panel_6 = new JPanel();
        panel_6.setBackground(Color.WHITE);
        panel_3.add(panel_6, BorderLayout.EAST);
        
        JPanel panel_6_1 = new JPanel();
        panel_6_1.setBackground(Color.WHITE);
        panel_3.add(panel_6_1, BorderLayout.WEST);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        pnlRight.add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new GridLayout(0, 1, 0, 0));
        
        JLabel lblNewLabel = new JLabel("");
        panel_1.add(lblNewLabel);
        
        if(wt != null) {
            xuLyTuDongGanGiaTri();
        }
    }
    
    public void xuLyTuDongGanGiaTri() {
        int warrantyId = wt.getWarrantyid();
        if(warrantyId == 0) {
            txtWarrantyId.setText(Integer.toString(WarrantyBUS.generateIdWar(true)));
        } else {
            txtWarrantyId.setText(Integer.toString(wt.getWarrantyid()));
        }
        txtProductId.setText(Integer.toString(wt.getProduct_serial_id()));
        txtProductId.setEditable(true);
        txtWarrantyDate.setText(wt.getWarrantyDate());
        txtWarrantyDate.setEditable(true);
        txtReason.setText(wt.getReason());
        txtReason.setEditable(true);
    	txtActive.setText(wt.getActive());
       	txtActive.setEditable(true);
    }
    
    public void xuLyLuuThongTinBaoHanh() {
        int warranty_id = Integer.parseInt(txtWarrantyId.getText());
        int product_id;
        String wardate = txtWarrantyDate.getText().trim();
        String reason = txtReason.getText().trim();
        String active = txtActive.getText().trim();
        int warstatus = Integer.parseInt(cmbTrangThai.getSelectedItem().toString());

        // Kiểm tra trường ProductID
        if (txtProductId.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ProductID không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            product_id = Integer.parseInt(txtProductId.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ProductID phải là số nguyên", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (product_id <= 0) {
            JOptionPane.showMessageDialog(null, "ProductID phải lớn hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Kiểm tra định dạng ngày tháng
        Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher dateMatcher = datePattern.matcher(wardate);
        if (!dateMatcher.matches()) {
            JOptionPane.showMessageDialog(null, "Ngày bảo hành phải có định dạng yyyy-mm-dd", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Tách năm, tháng và ngày từ chuỗi ngày tháng
        String[] dateParts = wardate.split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);
        // Kiểm tra hợp lệ của ngày và tháng
        if (month < 1 || month > 12 || day < 1 || day > 31) {
            JOptionPane.showMessageDialog(null, "Ngày hoặc tháng không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Kiểm tra trường Reason
        if (reason.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Lý do không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!active.equals("OK") && !active.equals("NO")) {
            JOptionPane.showMessageDialog(null, "Trường Active chỉ được nhập 'OK' hoặc 'NO'", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Xử lý lưu thông tin bảo hành
        boolean isExistWarId = WarrantyBUS.isExitWar(warranty_id);
        if (!isExistWarId) {
            if (WarrantyBUS.insertWar(warranty_id, product_id, wardate, reason,active, warstatus)) {
                JOptionPane.showMessageDialog(null, "Hệ thống thêm thành công thông tin bảo hành", "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
                parentGUI.loadDanhSachWarranty();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Hệ thống thêm thất bại thông tin bảo hành", "Thông báo thất bại", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if (WarrantyBUS.updateWar(warranty_id, product_id, wardate, reason,active, warstatus)) {
                JOptionPane.showMessageDialog(null, "Hệ thống cập nhật thành công thông tin bảo hành", "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
                parentGUI.loadDanhSachWarranty();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Hệ thống cập nhật thất bại thông tin bảo hành", "Thông báo thất bại", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
