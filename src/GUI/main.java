package GUI;
import javax.swing.JFrame;

public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Quản Lý Nhân Viên");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        QuanLyNhanVienGUI quanLyNhanVienGUI = new QuanLyNhanVienGUI();
        frame.getContentPane().add(quanLyNhanVienGUI);
        
        frame.pack(); // Đảm bảo các thành phần được sắp xếp đúng cách
        frame.setVisible(true);
    }
}
