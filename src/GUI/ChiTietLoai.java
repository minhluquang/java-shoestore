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

import DTO.HangDTO;
import DTO.Role;
import DTO.TheLoaiDTO;
import BUS.HangBUS;
import BUS.RoleBUS;
import BUS.TheLoaiBUS;
import DAO.TheLoaiDAO;
import GUI.PhanQuyenGUI;

public class ChiTietLoai extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtcateid;
    private JTextField txtcatename;

    private TheLoaiDTO tl;
    private QLLoai parentGUI;

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
    public ChiTietLoai(TheLoaiDTO tl, QLLoai parentGUI) {
        this.tl = tl;
        this.parentGUI = parentGUI;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng chi tiết Thể loại không?",
                        "Xác nhận đóng chi tiết Thể loại", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        int width = 600;
        int height = 400;

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, width, height);
        setLocationRelativeTo(null);
        setTitle("Thông tin thể loại");
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

        JLabel lblNewLabel_4 = new JLabel("Thông tin Thể loại");
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

        JLabel lblNewLabel_6_2 = new JLabel("Mã loại");
        lblNewLabel_6_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(lblNewLabel_6_2);

        txtcateid = new JTextField();
        txtcateid.setEnabled(false);
        txtcateid.setEditable(false);
        txtcateid.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtcateid.setColumns(10);
        panel_5.add(txtcateid);

        JLabel lblNewLabel_6 = new JLabel("Tên loại");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(lblNewLabel_6);

        txtcatename = new JTextField();
        txtcatename.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_5.add(txtcatename);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        panel_5.add(panel_2);
        panel_2.setLayout(new GridLayout(0, 2, 20, 0));

        // ========= Xử lý lưu thông tin =========
        JButton btnNewButton = new JButton("Lưu");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                xuLyLuuThongTinTheLoai();
            }
        });
        // ========= Xử lý lưu thông tin =========

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
                int choice = JOptionPane.showConfirmDialog(null,
                        "Bạn có muốn huỷ bỏ chỉnh sửa chi tiết thể loại không?",
                        "Xác nhận huỷ bỏ chỉnh sửa chi tiết thể loại", JOptionPane.YES_NO_OPTION);
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
        if (tl != null) {
            xuLyTuDongGanGiaTri();
        }
    }

    public void xuLyTuDongGanGiaTri() {
        int cateId = tl.getCategory_id();
        if (cateId == 0) {
            txtcateid.setText(Integer.toString(TheLoaiBUS.generateIdCate()));
        } else {
            txtcateid.setText(Integer.toString(cateId));
        }
        txtcatename.setText(tl.getCategory_name());
    }

    // btnLưu
    public void xuLyLuuThongTinTheLoai() {
        int cate_id = Integer.parseInt(txtcateid.getText());
        String categoryName = txtcatename.getText().trim();
        // Kiểm tra xem tên thể loại có trống không
        if (categoryName.isEmpty()) {
            String message = "Vui lòng nhập tên thể loại.";
            JOptionPane.showMessageDialog(null, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            // Tạo đối tượng TheLoaiDTO từ thông tin nhập liệu
            boolean isExistCateId = TheLoaiBUS.isExistIdTheLoai(cate_id);
            if (!isExistCateId) {
                boolean isExistCateName = TheLoaiBUS.isExistNameTheLoai(categoryName);
                if (!isExistCateName) {
                    if (TheLoaiBUS.themTheLoai(new TheLoaiDTO(cate_id, categoryName, true))) {
                        JOptionPane.showMessageDialog(null, "Thêm thành công.", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                        parentGUI.loadDanhSachTheLoai();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm thất bại.", "Thông báo",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    int choice = JOptionPane.showConfirmDialog(null, "Thể loại đã tồn tại",
                            "Thông báo", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        dispose();
                    }
                }
            } else {
                if (TheLoaiBUS.suaTheLoai(new TheLoaiDTO(cate_id, categoryName, true))) {
                    JOptionPane.showMessageDialog(null, "Sửa thành công.", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                    parentGUI.loadDanhSachTheLoai();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Sửa thất bại.", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }
    }

}
