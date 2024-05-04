package GUI;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.Cursor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import DTO.Role;
import BUS.RoleBUS;
import GUI.PhanQuyenGUI;



public class ChiTietLoai extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtMaPhanQuyen;
    private JTextField txtTenPhanQuyen;

    private Role rl;
    private PhanQuyenGUI parentGUI;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    ChiTietPhanQuyenGUI frame = new ChiTietPhanQuyenGUI(new Role(), new PhanQuyenGUI());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ChiTietLoai(Role rl, PhanQuyenGUI parentGUI) {
        this.rl = rl;
        this.parentGUI = parentGUI;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng chi tiết phân quyền không?", "Xác nhận đóng chi tiết phân quyền", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        int width = 600;
        int height = 500;

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, width, height);
        setLocationRelativeTo(null);
        setTitle("Thông tin phân quyền");
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

        JLabel lblNewLabel_4 = new JLabel("Thông tin phân quyền");
        lblNewLabel_4.setForeground(new Color(255, 255, 255));
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel.setBackground(new Color(36, 136, 203));
        panel.add(lblNewLabel_4);

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

        JLabel lblNewLabel_6_2 = new JLabel("Mã nhóm quyền");
        lblNewLabel_6_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(lblNewLabel_6_2);

        txtMaPhanQuyen = new JTextField();
        txtMaPhanQuyen.setEnabled(false);
        txtMaPhanQuyen.setEditable(false);
        txtMaPhanQuyen.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtMaPhanQuyen.setColumns(10);
        panel_5.add(txtMaPhanQuyen);

        JLabel lblNewLabel_6 = new JLabel("Tên nhóm quyền");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(lblNewLabel_6);

        txtTenPhanQuyen = new JTextField();
        txtTenPhanQuyen.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_5.add(txtTenPhanQuyen);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        panel_5.add(panel_2);
        panel_2.setLayout(new GridLayout(0, 2, 20, 0));



        // ========= Xử lý lưu thông tin phân quyền =========
        JButton btnNewButton = new JButton("Lưu");
        btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xuLyLuuThongTinPhanQuyen();
			}
		});
        // ========= Xử lý lưu thông tin phân quyền =========

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
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn huỷ bỏ chỉnh sửa chi tiết phân quyền không?", "Xác nhận huỷ bỏ chỉnh sửa chi tiết phân quyền", JOptionPane.YES_NO_OPTION);
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

        // ========== Gắn giá trị tự động ==========
        if (rl != null) {
            xuLyTuDongGanGiaTri();
        }
    }

    public void xuLyTuDongGanGiaTri() {
        int phanQuyenId = rl.getRole_id();
        if (phanQuyenId == 0) {
            txtMaPhanQuyen.setText(Integer.toString(RoleBUS.generateIdRole(true)));
        } else {
            txtMaPhanQuyen.setText(Integer.toString(rl.getRole_id()));
        }
        
        txtTenPhanQuyen.setText(rl.getRole_name());
        txtTenPhanQuyen.setEditable(true);
    }
    
    // btnLưu
    public void xuLyLuuThongTinPhanQuyen() {
        int role_id = rl.getRole_id();
        String role_name = txtTenPhanQuyen.getText().trim();
        // Kiểm tra form có txt trống không, nếu có thì không cho đi tiếp
        if (role_name.isEmpty()) {
            String message = "Vui lòng nhập tên nhóm quyền.";
            JOptionPane.showMessageDialog(null, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else if (!isAlpha(role_name)) { // Kiểm tra chuỗi chỉ chứa kí tự chữ và khoảng trắng
            String message = "Tên nhóm quyền chỉ được chứa kí tự chữ và khoảng trắng.";
            JOptionPane.showMessageDialog(null, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            // Nếu không tồn tại role_id (tức: không có mã nhóm quyền đó rồi thì insert)
            boolean isExistRoleId = RoleBUS.isExistRole(role_id);
            if (!isExistRoleId) {
                if (RoleBUS.insertRole(role_id, role_name)) {
                    JOptionPane.showMessageDialog(null, "Hệ thống thêm thành công thông tin nhóm quyền.", "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
                    parentGUI.loadDanhSachRole();
                    parentGUI.revalidate();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Hệ thống thêm thất bại thông tin nhóm quyền.", "Thông báo thất bại", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                // Nếu tồn tại role_id (tức: có mã nhóm quyền đó rồi thì update)
                if (RoleBUS.updateRole(role_id, role_name)) {
                    JOptionPane.showMessageDialog(null, "Hệ thống cập nhật thành công thông tin nhóm quyền.", "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
                    parentGUI.loadDanhSachRole();
                    parentGUI.revalidate();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Hệ thống cập nhật thất bại thông tin nhóm quyền.", "Thông báo thất bại", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    // Phương thức kiểm tra chuỗi chỉ chứa kí tự chữ và khoảng trắng
    private boolean isAlpha(String str) {
    	 return str.matches("[\\p{L}\\s]+");
    }


}
