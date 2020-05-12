import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;


//@autor chaimoua

public class CompMonthlyEarningSwing {

	/**
	 * Attributs of class
	 */
	private Employee Employee;
	private JFrame window;
	private JLabel header, l1, l2, l3, l5, l6;
	private JButton back, consult;
	private JComboBox month1, year1, month2, year2;
	private final ActionListener Consult = new Consult();
	private final ActionListener Back = new Back();
	private String[] tab = {"Junuary","Februry","March","April","May","June","July","August","September","Obtober","November","December"};
	
	/**
	 * Constructor of menu class
	 *
	 * @param menu of the user
	 */
	public CompMonthlyEarningSwing() {
		
		GUI menu = new GUI();
		this.window = menu.createWindow("Comparaison", 600, 800);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.window.setLocation((screen.width / 2) - 300, (screen.height / 2) - 400);
		this.window.getContentPane().setBackground(Color.white);
		
		// HEADER
		this.header = menu.createLabel("Comparasion of monthly gain.", 100, 50, 300, 50);
		this.window.add(this.header);
		// Change font and text size
	    header.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
		
	    // BODY
		this.l1 = menu.createLabel("Please, choose the months you want to compare.", 100, 120, 400, 30);
		this.window.add(this.l1);

		//1st month
		this.l2 = menu.createLabel("Month", 100, 200, 150, 30);
		this.window.add(this.l2);
		/*String[] list = new String[12];
		for (int i = 1; i <= 12; i++) {
			list[i - 1] = Integer.toString(i);
		}
		this.month1 = menu.createComboBox(list, 100, 230, 150, 30);
		this.window.add(this.month1);*/
		String[] list = new String[12];
		for (int i = 1; i <= 12; i++) {
			list[i - 1] = tab[i-1];
		}
		this.month1 = menu.createComboBox(list, 100, 230, 150, 30);
		this.window.add(this.month1);
		
		//1st year
		this.l3 = menu.createLabel("Year", 300, 200, 150, 30);
		this.window.add(this.l3);
		String[] list1 = new String[30];
		for (int i = 1; i <= 30; i++) {
			list1[i - 1] = Integer.toString(i + 1999);
		}
		this.year1 = menu.createComboBox(list1, 300, 230, 150, 30);
		this.window.add(this.year1);

		// 2nd month
		this.l5 = menu.createLabel("Month", 100, 400, 150, 30);
		this.window.add(this.l5);
		String[] list2 = new String[12];
		for (int i = 1; i <= 12; i++) {
			list2[i - 1] = Integer.toString(i);
		}
		this.month2 = menu.createComboBox(list2, 100, 430, 150, 30);
		this.window.add(this.month2);
		//2nd year
		this.l6 = menu.createLabel("Year", 300, 400, 150, 30);
		this.window.add(this.l6);
		String[] list3 = new String[30];
		for (int i = 1; i <= 30; i++) {
			list3[i - 1] = Integer.toString(i + 1999);
		}
		this.year2 = menu.createComboBox(list3, 300, 430, 150, 30);
		this.window.add(this.year2);

		//Button, one to continue, other to come back
		this.consult = menu.createButton("Consult", 350, 550, 100, 30, new Color(117, 184, 201), Color.WHITE);
		this.consult.addActionListener(this.Consult);
		this.window.add(this.consult);
		this.back = menu.createButton("Back", 460, 550, 100, 30, new Color(117, 184, 201), Color.WHITE);
		this.back.addActionListener(this.Back);
		this.window.add(this.back);
		
		//Repaint
		this.window.setVisible(true);
		this.back.repaint();
		this.consult.repaint();
		this.month1.repaint();
		this.year1.repaint();
		this.month2.repaint();
		this.year2.repaint();
		this.l5.repaint();
		this.l1.repaint();
		this.l2.repaint();
		this.l3.repaint();

	}

	private class Back implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Operation_FinancialState(Employee);
			window.dispose();
		}
	}

	private class Consult implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new EmployeeInterface(Employee);
			window.dispose();
		}
	}
}
