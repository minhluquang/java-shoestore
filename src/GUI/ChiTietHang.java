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
import BUS.HangBUS;

public class ChiTietHang extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtMaHang;
    private JTextField txtTenHang;
    private JButton btnHuy;
    private JButton btnLuu;

    public HangDTO hang;
    public QLHang qlHang;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    ChiTietHang frame = new ChiTietHang(new HangDTO(), new QLHang());
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
    public ChiTietHang(HangDTO hangDTO, QLHang qlHangGUI) {
        this.hang = hangDTO;
        this.qlHang = qlHangGUI;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng chi tiết hãng không?",
                        "Xác nhận đóng chi tiết hãng", JOptionPane.YES_NO_OPTION);
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
        setTitle("Thông tin hãng");
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

        JLabel lblNewLabel_4 = new JLabel("Thông tin hãng");
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

        JLabel lblNewLabel_6_2 = new JLabel("Mã hãng");
        lblNewLabel_6_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(lblNewLabel_6_2);

        txtMaHang = new JTextField();
        txtMaHang.setEnabled(false);
        txtMaHang.setEditable(false);
        txtMaHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtMaHang.setColumns(10);
        panel_5.add(txtMaHang);

        JLabel lblNewLabel_6 = new JLabel("Tên hãng");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(lblNewLabel_6);

        txtTenHang = new JTextField();
        txtTenHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_5.add(txtTenHang);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        panel_5.add(panel_2);
        panel_2.setLayout(new GridLayout(0, 2, 20, 0));

        btnLuu = new JButton("Lưu");
        btnLuu.addActionListener(this);
        btnLuu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLuu.setPreferredSize(new Dimension(100, 30));
        btnLuu.setForeground(Color.WHITE);
        btnLuu.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnLuu.setFocusable(false);
        btnLuu.setBorder(null);
        btnLuu.setBackground(new Color(21, 155, 71));
        panel_2.add(btnLuu);

        btnHuy = new JButton("Huỷ bỏ");
        btnHuy.addActionListener(this);
        btnHuy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnHuy.setPreferredSize(new Dimension(100, 30));
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnHuy.setFocusable(false);
        btnHuy.setBorder(null);
        btnHuy.setBackground(new Color(220, 53, 69));
        panel_2.add(btnHuy);

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

        xuLyTuDongGanGiaTri();
    }

    public void xuLyTuDongGanGiaTri() {
        int hangID = hang.getBrand_id();
        if (hangID == 0) {
            txtMaHang.setText(Integer.toString(HangBUS.generateIdHang()));
        } else {
            txtMaHang.setText(Integer.toString(hang.getBrand_id()));
        }

        txtTenHang.setText(hang.getBrand_name());
    }

    // btnLưu
    public void xuLyLuuThongTinHang() {
        int hangID = Integer.parseInt(txtMaHang.getText());
        String hangName = txtTenHang.getText().trim();
        // Kiểm tra form có txt trống không, nếu có thì không cho đi tiếp
        if (hangName.isEmpty()) {
            String message = "Vui lòng nhập Tên hãng.";
            JOptionPane.showMessageDialog(null, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            // Nếu không tồn tại hangName (tức: không có Mã hãng đó rồi thì insert)
            boolean isExistHangId = HangBUS.isExistIdHang(hangID);
            if (!isExistHangId) {
                boolean isExistHangName = HangBUS.isExistTenHang(hangName);
                if (!isExistHangName) {
                    if (HangBUS.themHang(new HangDTO(hangID, hangName, true))) {
                        JOptionPane.showMessageDialog(null, "Thêm thành công.", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                        qlHang.reLoad();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm thất bại.", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    int choice = JOptionPane.showConfirmDialog(null, "Hãng đã tồn tại",
                            "Thông báo", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        dispose();
                    }
                }
            } else {
                if (HangBUS.suaHang(new HangDTO(hangID, hangName, true))) {
                    JOptionPane.showMessageDialog(null, "Sửa thành công.", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                    qlHang.reLoad();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Sửa thất bại.", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnHuy) {
            int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn huỷ bỏ chỉnh sửa chi tiết hãng không?",
                    "Xác nhận huỷ bỏ chỉnh sửa chi tiết hãng", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                dispose();
            }
        }
        if (e.getSource() == btnLuu) {
            xuLyLuuThongTinHang();
        }
    }
}