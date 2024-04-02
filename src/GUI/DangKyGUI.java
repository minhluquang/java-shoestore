package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import javax.swing.border.LineBorder;

public class DangKyGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsernameRegister;
    private JPasswordField txtMatKhauRegister;
    private JPasswordField txtNhapLaiMatKhauRegister;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                DangKyGUI frame = new DangKyGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public DangKyGUI() {
        int width = 380;
        int height = 500;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, width, height);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        BorderLayout bl_contentPane = new BorderLayout();
        bl_contentPane.setVgap(20);
        contentPane.setLayout(bl_contentPane);
        
        JPanel pnlTop = new JPanel();
        pnlTop.setBackground(new Color(255, 255, 255));
        contentPane.add(pnlTop, BorderLayout.NORTH);
        pnlTop.setLayout(new GridLayout(0, 1, 0, 10));
        
        JPanel pnlTitle = new JPanel();
        pnlTitle.setBackground(new Color(255, 255, 255));
        pnlTop.add(pnlTitle);
        pnlTitle.setLayout(new GridLayout(0, 1, 0, 0));
        
        JLabel spacing = new JLabel("");
        pnlTitle.add(spacing);
        
        JLabel lnlDangNhap = new JLabel("Đăng ký tài khoản");
        lnlDangNhap.setHorizontalAlignment(SwingConstants.CENTER);
        lnlDangNhap.setFont(new Font("Tahoma", Font.BOLD, 18));
        pnlTitle.add(lnlDangNhap);
        
        JPanel pnlTitle_1 = new JPanel();
        pnlTitle_1.setBackground(Color.WHITE);
        pnlTop.add(pnlTitle_1);
        pnlTitle_1.setLayout(new GridLayout(0, 1, 0, 0));
        
        JLabel lblHyNhpThng = new JLabel("Chỉ cần một vài điều nhanh chóng");
        lblHyNhpThng.setHorizontalAlignment(SwingConstants.CENTER);
        lblHyNhpThng.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlTitle_1.add(lblHyNhpThng);
        
        JLabel lblngNhp = new JLabel("để bắt đầu");
        lblngNhp.setHorizontalAlignment(SwingConstants.CENTER);
        lblngNhp.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlTitle_1.add(lblngNhp);
        
        JPanel pnlCenter = new JPanel();
        pnlCenter.setBackground(new Color(255, 255, 255));
        contentPane.add(pnlCenter, BorderLayout.CENTER);
        pnlCenter.setLayout(new GridLayout(0, 1, 0, 0));
        
        JPanel pnlInput = new JPanel();
        pnlInput.setBackground(new Color(255, 255, 255));
        pnlCenter.add(pnlInput);
        pnlInput.setLayout(new GridLayout(0, 1, 0, 5));
        
        JLabel txt = new JLabel("Username");
        txt.setFont(new Font("Tahoma", Font.BOLD, 12));
        pnlInput.add(txt);
        
        txtUsernameRegister = new JTextField();
        txtUsernameRegister.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtUsernameRegister.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
        txtUsernameRegister.setPreferredSize(new Dimension(7, 30));
        pnlInput.add(txtUsernameRegister);
        txtUsernameRegister.setColumns(10);
        
        JLabel lblPassword = new JLabel("Mật khẩu");
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
        pnlInput.add(lblPassword);
        
        txtMatKhauRegister = new JPasswordField();
        txtMatKhauRegister.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtMatKhauRegister.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
        txtMatKhauRegister.setPreferredSize(new Dimension(7, 30));
        txtMatKhauRegister.setColumns(10);
        pnlInput.add(txtMatKhauRegister);
        
        JLabel lblNhpLiMt = new JLabel("Nhập lại mật khẩu");
        lblNhpLiMt.setFont(new Font("Tahoma", Font.BOLD, 12));
        pnlInput.add(lblNhpLiMt);
        
        txtNhapLaiMatKhauRegister = new JPasswordField();
        txtNhapLaiMatKhauRegister.setPreferredSize(new Dimension(7, 30));
        txtNhapLaiMatKhauRegister.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtNhapLaiMatKhauRegister.setColumns(10);
        txtNhapLaiMatKhauRegister.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
        pnlInput.add(txtNhapLaiMatKhauRegister);
        
        JPanel pnlService = new JPanel();
        pnlService.setBackground(new Color(255, 255, 255));
        pnlCenter.add(pnlService);
        pnlService.setLayout(new GridLayout(0, 1, 0, 5));
        
        JPanel panelSubService = new JPanel();
        panelSubService.setBackground(new Color(255, 255, 255));
        pnlService.add(panelSubService);
        panelSubService.setLayout(new GridLayout(0, 1, 0, 0));
        
        JCheckBox chckbxNewCheckBox = new JCheckBox("Tôi đồng ý với những điều khoản");
        chckbxNewCheckBox.setPreferredSize(new Dimension(300, 23));
        chckbxNewCheckBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
        chckbxNewCheckBox.setFocusable(false);
        chckbxNewCheckBox.setBackground(Color.WHITE);
        panelSubService.add(chckbxNewCheckBox);
        
        JButton btnDangNhap = new JButton("Đăng ký");
        btnDangNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDangNhap.setBorder(null);
        btnDangNhap.setBackground(new Color(51, 51, 51));
        btnDangNhap.setForeground(new Color(255, 255, 255));
        btnDangNhap.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnDangNhap.setPreferredSize(new Dimension(200, 30));
        pnlService.add(btnDangNhap);
        
        JPanel pnlSwichToRegister = new JPanel();
        pnlSwichToRegister.setBackground(new Color(255, 255, 255));
        pnlService.add(pnlSwichToRegister);
        
        JLabel lblNewLabel_2 = new JLabel("Bạn đã có tài khoản?");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        pnlSwichToRegister.add(lblNewLabel_2);
        
        JButton btnDangNhapNgay = new JButton("Đăng nhập ngay!");
        btnDangNhapNgay.setFocusable(false);
        btnDangNhapNgay.setForeground(new Color(0, 64, 128));
        btnDangNhapNgay.setBackground(new Color(255, 255, 255));
        btnDangNhapNgay.setBorder(null);
        btnDangNhapNgay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDangNhapNgay.setFont(new Font("Tahoma", Font.ITALIC, 12));
        pnlSwichToRegister.add(btnDangNhapNgay);
    }
}


