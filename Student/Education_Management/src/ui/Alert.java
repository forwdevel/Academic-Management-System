package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Alert extends JFrame {

	private JPanel contentPane;

	Color c1 = new Color(95,113,97); 	// Dark Green
	Color c2 = new Color(109,139,116);	// Ash Green
	Color c3 = new Color(239,234,216);	// light meal
	Color c4 = new Color(208,201,192);	// dark meal
	
	public Alert(String str) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 238);
		contentPane = new JPanel();
		contentPane.setBackground(c2);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel alterMessage = new JLabel(str);
		alterMessage.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		alterMessage.setForeground(c3);
		alterMessage.setHorizontalAlignment(SwingConstants.CENTER);
		alterMessage.setBounds(48, 56, 343, 30);
		contentPane.add(alterMessage);
		
		JButton confirm_btn = new JButton("확인");
		confirm_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		confirm_btn.setBackground(c3);
		confirm_btn.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		confirm_btn.setForeground(c1);
		confirm_btn.setBounds(169, 136, 97, 41);
		confirm_btn.setBorder(null);
		contentPane.add(confirm_btn);
		
		setVisible(true);
	}
}
