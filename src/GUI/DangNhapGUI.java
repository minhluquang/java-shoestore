package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DangNhapGUI extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtUsernameLogin;
    private JPasswordField txtMatKhauLogin;

    public DangNhapGUI() {
        int width = 380;
        int height = 400;

        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(255, 255, 255));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setVgap(20);
        setLayout(borderLayout);
        
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
        
        JLabel lnlDangNhap = new JLabel("Đăng nhập vào hệ thống");
        lnlDangNhap.setHorizontalAlignment(SwingConstants.CENTER);
        lnlDangNhap.setFont(new Font("Tahoma", Font.BOLD, 18));
        pnlTitle.add(lnlDangNhap);
        
        JPanel pnlTitle_1 = new JPanel();
        pnlTitle_1.setBackground(Color.WHITE);
        pnlTop.add(pnlTitle_1);
        pnlTitle_1.setLayout(new GridLayout(0, 1, 0, 0));
        
        JLabel lblHyNhpThng = new JLabel("Hãy nhập thông tin chi tiết của bạn để đăng nhập");
        lblHyNhpThng.setHorizontalAlignment(SwingConstants.CENTER);
        lblHyNhpThng.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlTitle_1.add(lblHyNhpThng);
        
        JLabel lblngNhp = new JLabel("vào tài khoản của bạn");
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
        
        txtUsernameLogin = new JTextField();
        txtUsernameLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtUsernameLogin.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
        txtUsernameLogin.setPreferredSize(new Dimension(7, 30));
        pnlInput.add(txtUsernameLogin);
        txtUsernameLogin.setColumns(10);
        
        JLabel lblPassword = new JLabel("Mật khẩu");
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
        pnlInput.add(lblPassword);
        
        txtMatKhauLogin = new JPasswordField();
        txtMatKhauLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtMatKhauLogin.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
        txtMatKhauLogin.setPreferredSize(new Dimension(7, 30));
        txtMatKhauLogin.setColumns(10);
        pnlInput.add(txtMatKhauLogin);
        
        JPanel pnlService = new JPanel();
        pnlService.setBackground(new Color(255, 255, 255));
        pnlCenter.add(pnlService, BorderLayout.SOUTH);
        pnlService.setLayout(new GridLayout(0, 1, 0, 5));
        
        JPanel panelSubService = new JPanel();
        panelSubService.setBackground(new Color(255, 255, 255));
        pnlService.add(panelSubService);
        panelSubService.setLayout(new GridLayout(0, 2, 0, 0));
        
        JCheckBox chckbxNewCheckBox = new JCheckBox("Giữ tôi đăng nhập lần sau");
        chckbxNewCheckBox.setSelected(true);
        chckbxNewCheckBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
        chckbxNewCheckBox.setFocusable(false);
        chckbxNewCheckBox.setBackground(Color.WHITE);
        panelSubService.add(chckbxNewCheckBox);
        
        JLabel lblNewLabel_1_1 = new JLabel("Quên mật khẩu?");
        lblNewLabel_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        panelSubService.add(lblNewLabel_1_1);
        
        JButton btnDangNhap = new JButton("Đăng nhập");
        btnDangNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDangNhap.setBorder(null);
        btnDangNhap.setBackground(new Color(51, 51, 51));
        btnDangNhap.setForeground(new Color(255, 255, 255));
        btnDangNhap.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnDangNhap.setPreferredSize(new Dimension(200, 30));
        pnlService.add(btnDangNhap);
    }
}
