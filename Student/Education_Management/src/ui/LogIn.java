package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import daovo.MemberDao;
import daovo.MemberVo;
import daovo.ProfessorDao;
import daovo.ProfessorVo;
import daovo.StudentDao;
import daovo.StudentVo;

//
//	complete
//

@SuppressWarnings("serial")
public class LogIn extends JFrame {
	private JTextField pw;
	private JTextField id;
	private JLabel fixed_2;
	private JLabel fixed_3;
	private JPanel contentPane;
	private JButton logIn_btn;
	private JButton exit_btn;

	Color c1 = new Color(95,113,97); 	// Dark Green
	Color c2 = new Color(109,139,116);	// Ash Green
	Color c3 = new Color(239,234,216);	// light meal
	Color c4 = new Color(208,201,192);	// dark meal

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn frame = new LogIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LogIn() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 600);
		contentPane = new JPanel();
		contentPane.setForeground(c3);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(c2);
		setContentPane(contentPane);

		getContentPane().setLayout(null);
		contentPane.setLayout(null);

		pw = new JPasswordField();
		pw.setHorizontalAlignment(SwingConstants.CENTER);
		pw.setFont(new Font("휴먼엑스포", Font.PLAIN, 30));
		pw.setBounds(402, 310, 224, 41);
		pw.setBorder(null);
		pw.setColumns(10);
		pw.setBackground(c2);
		pw.setForeground(c3);
		pw.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					// staff , professor, student
					MemberDao dao = new MemberDao();
					if (id.getText().equals("")) {
						// Null Id
						new Alert("아이디를입력해주세요.");
					} else if (pw.getText().equals("")) {
						// Null pw
						new Alert("비밀번호를입력해주세요.");
					} else {
						// System Log
						System.out.println(pw.getText());
						System.out.println(id.getText());

						// Check roll
						MemberVo vo = new MemberVo(id.getText(), pw.getText());
						int b = dao.list(vo);

						// Close window
						if (b == 0) {
							System.out.println("b==0");
							new Alert("존재하지않는정보입니다.");
						}
						// Open new window
						else if (b == 1) {
							setVisible(false);
							new Staff_Main(id.getText());
						} else if (b == 2) {
							setVisible(false);
							StudentVo vo1 = new StudentDao().stu(id.getText());
							new Student_Main(vo1);
						} else if (b == 3) {
							setVisible(false);
							ProfessorVo vo1 = new ProfessorDao().pro(id.getText());
							new Professor_Main(vo1);
						}
					}
				}
			}
			public void keyReleased(KeyEvent e) {}
		});
		getContentPane().add(pw);

		id = new JTextField();
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		id.setColumns(10);
		id.setBounds(402, 247, 224, 41);
		id.setBorder(null);
		id.setBackground(c2);
		id.setForeground(c3);
		getContentPane().add(id);

		JLabel fixed_1 = new JLabel("학사관리시스템");
		fixed_1.setForeground(c3);
		fixed_1.setHorizontalAlignment(SwingConstants.CENTER);
		fixed_1.setBounds(333, 131, 311, 75);
		fixed_1.setFont(new Font("휴먼엑스포", Font.PLAIN, 40));
		getContentPane().add(fixed_1);

		fixed_2 = new JLabel("No.");
		fixed_2.setForeground(c3);
		fixed_2.setBounds(333, 247, 57, 28);
		fixed_2.setHorizontalAlignment(JLabel.RIGHT);
		fixed_2.setFont(new Font("휴먼엑스포", Font.BOLD, 30));
		getContentPane().add(fixed_2);

		fixed_3 = new JLabel("PW");
		fixed_3.setForeground(c3);
		fixed_3.setBounds(330, 313, 57, 28);
		fixed_3.setHorizontalAlignment(JLabel.RIGHT);
		fixed_3.setFont(new Font("휴먼엑스포", Font.BOLD, 30));
		getContentPane().add(fixed_3);

		logIn_btn = new JButton("로그인");
		logIn_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// staff , professor, student
				MemberDao dao = new MemberDao();
				if (id.getText().equals("")) {
					// Null Id
					new Alert("아이디를 입력해주세요.");
				} else if (pw.getText().equals("")) {
					// Null pw
					new Alert("비밀번호를 입력해주세요.");
				} else {
					// System Log
					System.out.println(pw.getText());
					System.out.println(id.getText());

					// Check roll
					MemberVo vo = new MemberVo(id.getText(), pw.getText());
					int b = dao.list(vo);

					// Close window
					if (b == 0) {
						System.out.println("b==0");
						new Alert("존재하지 않는 정보입니다.");
					}
					// Open new window
					else if (b == 1) {
						setVisible(false);
						new Staff_Main(id.getText());
					} else if (b == 2) {
						setVisible(false);
						StudentVo vo1 = new StudentDao().stu(id.getText());
						new Student_Main(vo1);
					} else if (b == 3) {
						setVisible(false);
						ProfessorVo vo1 = new ProfessorDao().pro(id.getText());
						new Professor_Main(vo1);
					}
				}
			}
		});

		logIn_btn.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		logIn_btn.setForeground(c1);
		logIn_btn.setBackground(c3);
		logIn_btn.setBorder(null);
		logIn_btn.setBounds(330, 399, 97, 41);
		contentPane.add(logIn_btn);

		exit_btn = new JButton("종료");
		exit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exit_btn.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		exit_btn.setBackground(c1);
		exit_btn.setForeground(c3);
		exit_btn.setBorder(null);
		exit_btn.setBounds(560, 399, 97, 41);
		contentPane.add(exit_btn);

		setVisible(true);
	}
}
