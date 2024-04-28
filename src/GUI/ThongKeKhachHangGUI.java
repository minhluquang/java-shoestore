package GUI;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;

public class ThongKeKhachHangGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtTrangKhchHng;

	/**
	 * Create the panel.
	 */
	public ThongKeKhachHangGUI() {
		setLayout(null);
		
		txtTrangKhchHng = new JTextField();
		txtTrangKhchHng.setText("Trang Khách Hàng");
		txtTrangKhchHng.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtTrangKhchHng.setToolTipText("");
		txtTrangKhchHng.setBounds(42, 10, 186, 43);
		add(txtTrangKhchHng);
		txtTrangKhchHng.setColumns(10);

	}

}
