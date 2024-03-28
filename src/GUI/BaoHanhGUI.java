package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BaoHanhGUI {
    static JTextField productNameField, warrantyPeriodField;
    static JButton submitButton;

    public static JPanel baoHanhGUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel productNameLabel = new JLabel("Tên sản phẩm:");
        productNameField = new JTextField();
        JLabel warrantyPeriodLabel = new JLabel("Thời gian bảo hành:");
        warrantyPeriodField = new JTextField();
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        panel.add(productNameLabel);
        panel.add(productNameField);
        panel.add(warrantyPeriodLabel);
        panel.add(warrantyPeriodField);
        panel.add(new JLabel());
        panel.add(submitButton);
        return panel;
    }

    static class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String productName = productNameField.getText();
            String warrantyPeriod = warrantyPeriodField.getText();
            // Thực hiện xử lý dữ liệu ở đây, ví dụ: lưu vào cơ sở dữ liệu
            JOptionPane.showMessageDialog(null,
                    "Đã lưu thông tin sản phẩm: " + productName + " - Bảo hành: " + warrantyPeriod + " tháng");
        }
    }
}
