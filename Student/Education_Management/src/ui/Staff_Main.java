package ui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import daovo.EtcDao;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;

//
//	complete
//

@SuppressWarnings("serial")
public class Staff_Main extends JFrame {

	Color c1 = new Color(95,113,97); 	// Dark Green
	Color c2 = new Color(109,139,116);	// Ash Green
	Color c3 = new Color(239,234,216);	// light meal
	Color c4 = new Color(208,201,192);	// dark meal

	private JPanel contentPane;

	public Staff_Main(String id) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(c2);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel fixed_1 = new JLabel("교직원 시스템");
		fixed_1.setHorizontalAlignment(SwingConstants.CENTER);
		fixed_1.setFont(new Font("휴먼엑스포", Font.BOLD, 36));
		fixed_1.setForeground(c3);
		fixed_1.setBounds(272, 56, 397, 49);
		contentPane.add(fixed_1);
		
		JButton enter_stu_btn = new JButton("학생 등록");
		enter_stu_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Staff_Enroll_Student(id);
				setVisible(false);
			}
		});
		enter_stu_btn.setFont(new Font("휴먼엑스포", Font.BOLD, 14));
		enter_stu_btn.setBounds(362, 162, 214, 23);
		enter_stu_btn.setBackground(c3);
		enter_stu_btn.setForeground(c1);
		enter_stu_btn.setBorder(null);
		contentPane.add(enter_stu_btn);
		
		JButton enter_pro_btn = new JButton("교수 등록");
		enter_pro_btn.setFont(new Font("휴먼엑스포", Font.BOLD, 14));
		enter_pro_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Staff_Enroll_Professor(id);
			}
		});
		enter_pro_btn.setBounds(362, 207, 214, 23);
		enter_pro_btn.setBackground(c3);
		enter_pro_btn.setForeground(c1);
		enter_pro_btn.setBorder(null);
		contentPane.add(enter_pro_btn);
		
		JButton inquiry_stu_btn = new JButton("학생 조회");
		inquiry_stu_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Staff_Inquiry_Student(id);
			}
		});
		inquiry_stu_btn.setFont(new Font("휴먼엑스포", Font.BOLD, 14));
		inquiry_stu_btn.setBackground(c1);
		inquiry_stu_btn.setForeground(c3);
		inquiry_stu_btn.setBorder(null);
		inquiry_stu_btn.setBounds(362, 255, 214, 23);
		contentPane.add(inquiry_stu_btn);
		
		JButton inquiry_pro_btn = new JButton("교수 조회");
		inquiry_pro_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Staff_Inquiry_Professor(id);
			}
		});
		inquiry_pro_btn.setFont(new Font("휴먼엑스포", Font.BOLD, 14));
		inquiry_pro_btn.setBackground(c1);
		inquiry_pro_btn.setForeground(c3);
		inquiry_pro_btn.setBorder(null);
		inquiry_pro_btn.setBounds(362, 302, 214, 23);
		contentPane.add(inquiry_pro_btn);
		
		JButton inquiry_lec_btn = new JButton("과목 조회");
		inquiry_lec_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Staff_Inquiry_Lecture(id);
			}
		});
		inquiry_lec_btn.setFont(new Font("휴먼엑스포", Font.BOLD, 14));
		inquiry_lec_btn.setBackground(c1);
		inquiry_lec_btn.setForeground(c3);
		inquiry_lec_btn.setBorder(null);
		inquiry_lec_btn.setBounds(362, 348, 214, 23);
		contentPane.add(inquiry_lec_btn);
		
		JButton back_btn = new JButton("로그아웃");
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new LogIn();
			}
		});
		back_btn.setBounds(420, 489, 97, 23);
		back_btn.setBackground(c1);
		back_btn.setForeground(c3);
		back_btn.setBorder(null);
		contentPane.add(back_btn);
		
		
		boolean enterB = new EtcDao().getEnter();
		JButton canGradeEnter_btn = new JButton("");
		canGradeEnter_btn.setBounds(805, 40, 135, 23);
		canGradeEnter_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean b = new EtcDao().getEnter();
				if(!b) {
					canGradeEnter_btn.setText("수강신청 비허용");
					canGradeEnter_btn.setBackground(c4);
					canGradeEnter_btn.setForeground(c1);
					new EtcDao().setEnter(true);
				} else {
					canGradeEnter_btn.setText("수강신청 허용");
					canGradeEnter_btn.setBackground(c1);
					canGradeEnter_btn.setForeground(c4);
					new EtcDao().setEnter(false);
				}
			}
		});
		if(enterB) {
			canGradeEnter_btn.setText("수강신청 비허용");
			canGradeEnter_btn.setBackground(c4);
			canGradeEnter_btn.setForeground(c1);
		} else {
			canGradeEnter_btn.setText("수강신청 허용");
			canGradeEnter_btn.setBackground(c1);
			canGradeEnter_btn.setForeground(c4);
		}
		canGradeEnter_btn.setBorder(null);
		contentPane.add(canGradeEnter_btn);
		
		boolean inquiryB = new EtcDao().getInquiry();
		JButton canGradeInquiry_btn = new JButton("");
		canGradeInquiry_btn.setBounds(805, 75, 135, 23);
		canGradeInquiry_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean b = new EtcDao().getInquiry();
				System.out.println("inner b : " + b);
				if(!b) {
					canGradeInquiry_btn.setText("성적조회 비허용");
					canGradeInquiry_btn.setBackground(c4);
					canGradeInquiry_btn.setForeground(c1);
					new EtcDao().setInquiry(true);
				} else {
					canGradeInquiry_btn.setText("성적조회 허용");
					canGradeInquiry_btn.setBackground(c1);
					canGradeInquiry_btn.setForeground(c4);
					new EtcDao().setInquiry(false);
				}
			}
		});
		if(inquiryB) {
			canGradeInquiry_btn.setText("성적조회 비허용");
			canGradeInquiry_btn.setBackground(c4);
			canGradeInquiry_btn.setForeground(c1);
		} else {
			canGradeInquiry_btn.setText("성적조회 허용");
			canGradeInquiry_btn.setBackground(c1);
			canGradeInquiry_btn.setForeground(c4);
		}
		canGradeInquiry_btn.setBorder(null);
		contentPane.add(canGradeInquiry_btn);
		
		JButton changePassword_btn = new JButton("비밀번호 변경");
		changePassword_btn.setBackground(c4);
		changePassword_btn.setFont(new Font("휴먼엑스포", Font.BOLD, 14));
		changePassword_btn.setBorder(null);
		changePassword_btn.setForeground(c1);
		changePassword_btn.setBounds(362, 392, 214, 23);
		changePassword_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangePassword(id);
			}
		});
		contentPane.add(changePassword_btn);
		
		setVisible(true);
	}
}
