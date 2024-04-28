package GUI;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;

public class ThongKeTonKhoGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtTrangTnKho;

	/**
	 * Create the panel.
	 */
	public ThongKeTonKhoGUI() {
		setLayout(null);
		
		txtTrangTnKho = new JTextField();
		txtTrangTnKho.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtTrangTnKho.setText("Trang Tồn Kho");
		txtTrangTnKho.setBounds(36, 10, 193, 30);
		add(txtTrangTnKho);
		txtTrangTnKho.setColumns(10);

	}

}
