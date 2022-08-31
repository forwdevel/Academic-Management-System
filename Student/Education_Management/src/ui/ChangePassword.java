package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import daovo.EtcDao;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class ChangePassword extends JFrame {

	private JPanel contentPane;
	private JPasswordField pw;
	private JPasswordField pwConfirm;

	Color c1 = new Color(95,113,97); 	// Dark Green
	Color c2 = new Color(109,139,116);	// Ash Green
	Color c3 = new Color(239,234,216);	// light meal
	Color c4 = new Color(208,201,192);	// dark meal

	public ChangePassword(String id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(c1);
		contentPane.setLayout(null);
		
		pw = new JPasswordField();
		pw.setBounds(172, 97, 204, 31);
		pw.setBorder(null);
		pw.setBackground(c1);
		pw.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		pw.setForeground(c3);
		contentPane.add(pw);
		
		pwConfirm = new JPasswordField();
		pwConfirm.setBounds(172, 138, 204, 31);
		pwConfirm.setBackground(c1);
		pwConfirm.setBorder(null);
		pwConfirm.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		pwConfirm.setForeground(c3);
		contentPane.add(pwConfirm);
		
		JLabel LabelPw = new JLabel("비밀번호");
		LabelPw.setHorizontalAlignment(SwingConstants.RIGHT);
		LabelPw.setBounds(51, 97, 109, 31);
		LabelPw.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		LabelPw.setForeground(c3);
		contentPane.add(LabelPw);
		
		JLabel LabelPwConfirm = new JLabel("비밀번호 확인");
		LabelPwConfirm.setHorizontalAlignment(SwingConstants.RIGHT);
		LabelPwConfirm.setBounds(51, 138, 109, 31);
		LabelPwConfirm.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		LabelPwConfirm.setForeground(c3);
		contentPane.add(LabelPwConfirm);
		
		JButton confirm_btn = new JButton("변경");
		confirm_btn.setBounds(163, 193, 97, 32);
		confirm_btn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(pw.getText().length() == 0 || pwConfirm.getText().length() == 0) {
					new Alert("비밀번호를 입력해주세요.");
					return;
				}
				if(pw.getText().equals(pwConfirm.getText())) {
					new EtcDao().changePassword(id, pw.getText());
					new Alert("비밀번호를 변경하였습니다.");
					setVisible(false);
				} else {
					new Alert("비밀번호가 일치하지않습니다.");
					pw.setText("");
					pwConfirm.setText("");
				}
			}
		});
		confirm_btn.setBorder(null);
		confirm_btn.setBackground(c3);
		confirm_btn.setForeground(c1);
		confirm_btn.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		contentPane.add(confirm_btn);
		
		JLabel lblNewLabel = new JLabel("비밀번호 변경");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(51, 32, 325, 32);
		lblNewLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 30));
		lblNewLabel.setForeground(c3);
		contentPane.add(lblNewLabel);
		
		setVisible(true);
	}
}
