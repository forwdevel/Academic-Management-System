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
public class Confirm extends JFrame {
	private JButton confirm_btn = new JButton("확인");
	JButton cancel_btn = new JButton("취소");
	private JPanel contentPane;
	boolean b;

	Color c1 = new Color(95,113,97); 	// Dark Green
	Color c2 = new Color(109,139,116);	// Ash Green
	Color c3 = new Color(239,234,216);	// light meal
	Color c4 = new Color(208,201,192);	// dark meal
	
	public Confirm(String text, int funcKey) {
		// funcKey
		// 1. Register interest
		// 2. Register lecture
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(c2);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		confirm_btn.setForeground(c1);
		confirm_btn.setBackground(c3);
		confirm_btn.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		confirm_btn.setBorder(null);
		confirm_btn.setBounds(77, 194, 97, 35);
		contentPane.add(confirm_btn);
		
		cancel_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		cancel_btn.setForeground(c3);
		cancel_btn.setBackground(c1);
		cancel_btn.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		cancel_btn.setBorder(null);
		cancel_btn.setBounds(249, 194, 97, 35);
		contentPane.add(cancel_btn);
		
		JLabel lblNewLabel = new JLabel(text);
		lblNewLabel.setForeground(c3);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		lblNewLabel.setBounds(37, 49, 357, 110);
		contentPane.add(lblNewLabel);
		
		setVisible(true);
	}
}
