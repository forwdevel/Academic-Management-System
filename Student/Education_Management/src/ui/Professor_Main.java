package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import daovo.EtcDao;
import daovo.ProfessorVo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class Professor_Main extends JFrame {
	private JPanel contentPane;

	Color c1 = new Color(95,113,97); 	// Dark Green
	Color c2 = new Color(109,139,116);	// Ash Green
	Color c3 = new Color(239,234,216);	// light meal
	Color c4 = new Color(208,201,192);	// dark meal
	
	public Professor_Main(ProfessorVo vo) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 600);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setBackground(c3);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel pro_name = new JLabel(vo.getName());
		pro_name.setForeground(c2);
		pro_name.setFont(new Font("휴먼엑스포", Font.PLAIN, 30));
		pro_name.setBounds(27, 33, 150, 52);
		contentPane.add(pro_name);
		
		JLabel fixed_1 = new JLabel("교수,");
		fixed_1.setHorizontalAlignment(SwingConstants.RIGHT);
		fixed_1.setForeground(c1);
		fixed_1.setFont(new Font("휴먼엑스포", Font.BOLD, 36));
		fixed_1.setBounds(164, 33, 116, 52);
		contentPane.add(fixed_1);
		
		JLabel fixed_2 = new JLabel("교수번호");
		fixed_2.setForeground(c1);
		fixed_2.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		fixed_2.setBounds(27, 124, 87, 27);
		contentPane.add(fixed_2);
		
		JLabel fixed_3 = new JLabel("임용년도");
		fixed_3.setForeground(c1);
		fixed_3.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		fixed_3.setBounds(27, 172, 87, 27);
		contentPane.add(fixed_3);
		
		JLabel fixed_4 = new JLabel("단과");
		fixed_4.setForeground(c1);
		fixed_4.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		fixed_4.setBounds(27, 217, 87, 27);
		contentPane.add(fixed_4);
		
		JLabel fixed_5 = new JLabel("전공");
		fixed_5.setForeground(c1);
		fixed_5.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		fixed_5.setBounds(27, 265, 87, 27);
		contentPane.add(fixed_5);
		
		JButton enter_grade_btn = new JButton("성적입력");
		enter_grade_btn.setForeground(c3);
		enter_grade_btn.setBackground(c1);
		enter_grade_btn.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		enter_grade_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Professor_Enter_Grade(vo);
			}
		});
		enter_grade_btn.setBorder(null);
		enter_grade_btn.setBounds(164, 394, 124, 43);
		contentPane.add(enter_grade_btn);
		
		JButton enter_lecture_btn = new JButton("과목등록");
		enter_lecture_btn.setBackground(c1);
		enter_lecture_btn.setForeground(c3);
		enter_lecture_btn.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		enter_lecture_btn.setBounds(27, 394, 124, 43);
		enter_lecture_btn.setBorder(null);
		enter_lecture_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Professor_Enter_Lecture(vo);
			}
		});
		contentPane.add(enter_lecture_btn);
		
		JLabel fixed_8 = new JLabel("일정");
		fixed_8.setHorizontalAlignment(SwingConstants.CENTER);
		fixed_8.setFont(new Font("휴먼엑스포", Font.BOLD, 24));
		fixed_8.setBounds(697, 22, 80, 43);
		fixed_8.setForeground(c1);
		contentPane.add(fixed_8);
		
		JLabel fixed_7 = new JLabel("공지사항");
		fixed_7.setHorizontalAlignment(SwingConstants.CENTER);
		fixed_7.setFont(new Font("휴먼엑스포", Font.BOLD, 24));
		fixed_7.setBounds(684, 288, 110, 43);
		fixed_7.setForeground(c1);
		contentPane.add(fixed_7);
		
		JLabel pro_id = new JLabel("" + vo.getId());
		pro_id.setForeground(c2);
		pro_id.setHorizontalAlignment(SwingConstants.TRAILING);
		pro_id.setFont(new Font("휴먼엑스포", Font.PLAIN, 18));
		pro_id.setBounds(108, 124, 172, 27);
		contentPane.add(pro_id);
		
		JLabel pro_enroll = new JLabel(""+vo.getEnroll());
		pro_enroll.setForeground(c2);
		pro_enroll.setHorizontalAlignment(SwingConstants.TRAILING);
		pro_enroll.setFont(new Font("휴먼엑스포", Font.PLAIN, 18));
		pro_enroll.setBounds(108, 172, 172, 27);
		contentPane.add(pro_enroll);
		
		JLabel pro_college = new JLabel(vo.getCollege());
		pro_college.setForeground(c2);
		pro_college.setHorizontalAlignment(SwingConstants.TRAILING);
		pro_college.setFont(new Font("휴먼엑스포", Font.PLAIN, 18));
		pro_college.setBounds(72, 217, 208, 27);
		contentPane.add(pro_college);
		
		JLabel pro_major = new JLabel(vo.getMajor());
		pro_major.setForeground(c2);
		pro_major.setHorizontalAlignment(SwingConstants.TRAILING);
		pro_major.setFont(new Font("휴먼엑스포", Font.PLAIN, 18));
		pro_major.setBounds(72, 265, 208, 27);
		contentPane.add(pro_major);
		
		boolean enter = new EtcDao().getEnter();
		boolean inquiry = new EtcDao().getInquiry();
		
		JLabel enterNoticeLabel = new JLabel("New label");
		if(enter) {
			enterNoticeLabel.setText("수강신청기간입니다.");
		} else {
			enterNoticeLabel.setText("수강신청기간이 아닙니다.");
		}
		enterNoticeLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		enterNoticeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		enterNoticeLabel.setBounds(595, 75, 286, 26);
		contentPane.add(enterNoticeLabel);
		
		JLabel inquiryNoticeLabel = new JLabel("New label");
		if(inquiry) {
			inquiryNoticeLabel.setText("성적조회기간입니다.");
		} else {
			inquiryNoticeLabel.setText("성적조회기간이 아닙니다.");
		}
		inquiryNoticeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		inquiryNoticeLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		inquiryNoticeLabel.setBounds(595, 117, 286, 26);
		contentPane.add(inquiryNoticeLabel);
		
		JLabel Notice_1 = new JLabel("비밀번호를 주기적으로 변경해주세요.");
		Notice_1.setHorizontalAlignment(SwingConstants.CENTER);
		Notice_1.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		Notice_1.setBounds(595, 343, 286, 26);
		contentPane.add(Notice_1);
		
		JButton logOut_btn = new JButton("로그아웃");
		logOut_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new LogIn();
			}
		});
		logOut_btn.setBounds(855, 525, 97, 26);
		logOut_btn.setBackground(c4);
		logOut_btn.setForeground(c1);
		logOut_btn.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		logOut_btn.setBorder(null);
		contentPane.add(logOut_btn);
		
		JButton change_pw_btn = new JButton("비밀번호 변경");
		change_pw_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangePassword(vo.getId());
			}
		});
		change_pw_btn.setForeground(c1);
		change_pw_btn.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		change_pw_btn.setBorder(null);
		change_pw_btn.setBackground(c4);
		change_pw_btn.setBounds(27, 447, 261, 27);
		contentPane.add(change_pw_btn);
		
		setVisible(true);
	}
}
