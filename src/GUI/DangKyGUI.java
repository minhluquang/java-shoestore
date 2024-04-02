package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DangKyGUI extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtUsernameRegister;
    private JPasswordField txtMatKhauRegister;
    private JPasswordField txtNhapLaiMatKhauRegister;

    public DangKyGUI() {
        int width = 380;
        int height = 500;

        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(255, 255, 255));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());
        
        JPanel pnlTop = new JPanel();
        pnlTop.setBackground(new Color(255, 255, 255));
        add(pnlTop, BorderLayout.NORTH);
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
        add(pnlCenter, BorderLayout.CENTER);
        pnlCenter.setLayout(new BorderLayout(0, 0));
        
        JPanel pnlInput = new JPanel();
        pnlInput.setBackground(new Color(255, 255, 255));
        pnlCenter.add(pnlInput, BorderLayout.NORTH);
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
        pnlCenter.add(pnlService, BorderLayout.SOUTH);
        pnlService.setLayout(new GridLayout(0, 1, 0, 5));
        
        JPanel panelSubService = new JPanel();
        panelSubService.setBackground(new Color(255, 255, 255));
        pnlService.add(panelSubService);
        panelSubService.setLayout(new GridLayout(0, 1, 0, 0));
        
        JCheckBox chckbxNewCheckBox = new JCheckBox("Tôi đồng ý với những điều khoản");
        chckbxNewCheckBox.setSelected(true);
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
    }
}
