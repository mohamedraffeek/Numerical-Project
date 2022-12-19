package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Test {

	private JFrame frame;
	private JTextField[][] coef = new JTextField[10][11];
	private JLabel[][] labels = new JLabel[10][10];
	private JTextField ErrorTextField;
	private JLabel LUText, StoppingConditionText, IterationsNumberText, ErrorText, PrecisionText, DigitsLabel;
	private JComboBox LUComboBox, StoppingConditionComboBox;
	private JSpinner IterationsNumberSpinner, PrecisionDigitsSpinner;
	private JButton button;
	int x = 20, y = 280, width = 80, height = 20;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(193, 255, 255));
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.setBounds(0, 0, 1920, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		
		for(int i = 0; i < 10; i++) {
			x = 20;
			for(int j = 0; j < 11; j++) {
				coef[i][j] = new JTextField();
				coef[i][j].setBackground(new Color(240, 240, 240));
				coef[i][j].setFont(new Font("Tahoma", Font.PLAIN, 18));
				coef[i][j].setText("0");
				coef[i][j].setBounds(x, y, width, height);
				coef[i][j].setVisible(false);
				frame.getContentPane().add(coef[i][j]);
				if(j != 10) {
					labels[i][j] = new JLabel();
					labels[i][j].setFont(new Font("Tahoma", Font.PLAIN, 20));
					labels[i][j].setBounds(x + 80, y, 70, height);
					labels[i][j].setVisible(false);
					frame.getContentPane().add(labels[i][j]);
				}
				x += 135;
			}
			y += 50;
		}
		
		setBoxes(2);
		
		ErrorText = new JLabel("Choose the absolute relative error");
		ErrorText.setFont(new Font("Tahoma", Font.BOLD, 14));
		ErrorText.setBounds(837, 23, 242, 26);
		frame.getContentPane().add(ErrorText);
		ErrorText.setVisible(false);
		
		ErrorTextField = new JTextField();
		ErrorTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		ErrorTextField.setText("0.0001");
		ErrorTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ErrorTextField.setBounds(837, 45, 242, 26);
		frame.getContentPane().add(ErrorTextField);
		ErrorTextField.setVisible(false);
		
		IterationsNumberText = new JLabel("Choose number of iterations");
		IterationsNumberText.setFont(new Font("Tahoma", Font.BOLD, 14));
		IterationsNumberText.setBounds(837, 23, 198, 26);
		frame.getContentPane().add(IterationsNumberText);
		IterationsNumberText.setVisible(false);
		
		IterationsNumberSpinner = new JSpinner();
		IterationsNumberSpinner.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		IterationsNumberSpinner.setFont(new Font("Tahoma", Font.PLAIN, 18));
		IterationsNumberSpinner.setBounds(837, 45, 242, 26);
		frame.getContentPane().add(IterationsNumberSpinner);
		IterationsNumberSpinner.setVisible(false);
		
		StoppingConditionText = new JLabel("Choose the stopping condition\r\n");
		StoppingConditionText.setFont(new Font("Tahoma", Font.BOLD, 14));
		StoppingConditionText.setBounds(567, 23, 223, 26);
		frame.getContentPane().add(StoppingConditionText);
		StoppingConditionText.setVisible(false);
		
		StoppingConditionComboBox = new JComboBox();
		StoppingConditionComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setStoppingCondition((String)StoppingConditionComboBox.getSelectedItem());
			}
		});
		StoppingConditionComboBox.setModel(new DefaultComboBoxModel(new String[] {"Number of Iterations", "Absolute Relative Error"}));
		StoppingConditionComboBox.setSelectedIndex(0);
		StoppingConditionComboBox.setFont(new Font("Segoe UI", Font.BOLD, 16));
		StoppingConditionComboBox.setCursor(cursor);
		StoppingConditionComboBox.setBounds(567, 45, 242, 26);
		frame.getContentPane().add(StoppingConditionComboBox);
		StoppingConditionComboBox.setVisible(false);
		
		LUText = new JLabel("Choose the LU format\r\n");
		LUText.setFont(new Font("Tahoma", Font.BOLD, 14));
		LUText.setBounds(567, 23, 198, 26);
		frame.getContentPane().add(LUText);
		LUText.setVisible(false);
		
		LUComboBox = new JComboBox();
		LUComboBox.setBackground(new Color(255, 255, 255));
		LUComboBox.setModel(new DefaultComboBoxModel(new String[] {"Doolittle", "Crout", "Cholesky"}));
		LUComboBox.setSelectedIndex(0);
		LUComboBox.setFont(new Font("Segoe UI", Font.BOLD, 16));
		LUComboBox.setCursor(cursor);
		LUComboBox.setBounds(567, 45, 242, 26);
		frame.getContentPane().add(LUComboBox);
		LUComboBox.setVisible(false);
		
		JLabel equationsText = new JLabel("Enter number of equations");
		equationsText.setFont(new Font("Tahoma", Font.BOLD, 14));
		equationsText.setBounds(20, 45, 198, 26);
		frame.getContentPane().add(equationsText);
		
		JSpinner equationsSpinner = new JSpinner();
		equationsSpinner.setFont(new Font("Tahoma", Font.PLAIN, 18));
		equationsSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				setBoxes((int)equationsSpinner.getValue());
			}
		});
		equationsSpinner.setModel(new SpinnerNumberModel(2, 2, 10, 1));
		equationsSpinner.setBounds(217, 45, 45, 26);
		frame.getContentPane().add(equationsSpinner);
		
		JLabel methodText = new JLabel("Choose solving method");
		methodText.setFont(new Font("Tahoma", Font.BOLD, 14));
		methodText.setBounds(293, 23, 198, 26);
		frame.getContentPane().add(methodText);
		
		JComboBox methodComboBox = new JComboBox();
		methodComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMethod((String)methodComboBox.getSelectedItem());
			}
		});
		methodComboBox.setBackground(new Color(255, 255, 255));
		methodComboBox.setCursor(cursor);
		methodComboBox.setFont(new Font("Segoe UI", Font.BOLD, 16));
		methodComboBox.setModel(new DefaultComboBoxModel(new String[] {"Gauss Elimination", "Gauss Jordan", "LU Decomposition", "Gauss Seidel", "Jacobi Iteration"}));
		methodComboBox.setSelectedIndex(0);
		methodComboBox.setBounds(293, 45, 242, 26);
		frame.getContentPane().add(methodComboBox);
		
		PrecisionText = new JLabel("Choose the Precision");
		PrecisionText.setFont(new Font("Tahoma", Font.BOLD, 14));
		PrecisionText.setBounds(293, 110, 242, 26);
		frame.getContentPane().add(PrecisionText);
		PrecisionText.setVisible(true);
		
		PrecisionDigitsSpinner = new JSpinner();
		PrecisionDigitsSpinner.setModel(new SpinnerNumberModel(1, 1, 16, 1));
		PrecisionDigitsSpinner.setFont(new Font("Tahoma", Font.PLAIN, 18));
		PrecisionDigitsSpinner.setBounds(293, 132, 50, 26);
		frame.getContentPane().add(PrecisionDigitsSpinner);
		PrecisionDigitsSpinner.setVisible(true);
		
		DigitsLabel = new JLabel("Digits");
		DigitsLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		DigitsLabel.setBounds(348, 132, 50, 26);
		DigitsLabel.setVisible(true);
		frame.getContentPane().add(DigitsLabel);
		
		button = new JButton("Solve");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				solve((String)methodComboBox.getSelectedItem(), (int)equationsSpinner.getValue());
			}
		});
		button.setBackground(new Color(0, 128, 255));
		button.setFont(new Font("Dialog", Font.BOLD, 30));
		button.setCursor(cursor);
		button.setBounds(1350, 11, 138, 63);
		frame.getContentPane().add(button);
		
		Canvas canvas = new Canvas();
		canvas.setBackground(new Color(0, 0, 0));
		canvas.setBounds(0, 250, 1920, 2);
		frame.getContentPane().add(canvas);
		
	}
	
	private void setBoxes(int n) {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 11; j++) {
				if(i >= n || j >= n + 1) {
					coef[i][j].setVisible(false);
					coef[i][j].setText("0");
				}
				if(j < 10) {
					labels[i][j].setText("");
					labels[i][j].setVisible(false);
				}
			}
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n + 1; j++) {
				coef[i][j].setVisible(true);
				if(j < n - 1)labels[i][j].setText("X" + String.valueOf(j + 1) + " + ");
				else if(j == n - 1) labels[i][j].setText("X" + String.valueOf(j + 1) + " = ");
				if(j < n) labels[i][j].setVisible(true);
			}
		}
	}
	
	private void setMethod(String method) {
		LUText.setVisible(false);
		LUComboBox.setVisible(false);
		StoppingConditionText.setVisible(false);
		StoppingConditionComboBox.setVisible(false);
		IterationsNumberText.setVisible(false);
		IterationsNumberSpinner.setVisible(false);
		ErrorText.setVisible(false);
		ErrorTextField.setVisible(false);
		if(method == "LU Decomposition") {
			LUText.setVisible(true);
			LUComboBox.setVisible(true);
		}
		else if(method == "Gauss Seidel" || method == "Jacobi Iteration") {
			StoppingConditionText.setVisible(true);
			StoppingConditionComboBox.setVisible(true);
			setStoppingCondition((String)StoppingConditionComboBox.getSelectedItem());
		}
	}
	
	private void setStoppingCondition(String condition) {
		IterationsNumberText.setVisible(false);
		IterationsNumberSpinner.setVisible(false);
		ErrorText.setVisible(false);
		ErrorTextField.setVisible(false);
		if(!StoppingConditionText.isVisible()) return;
		if(condition == "Number of Iterations") {
			IterationsNumberText.setVisible(true);
			IterationsNumberSpinner.setVisible(true);
		}
		else if(condition == "Absolute Relative Error") {
			ErrorText.setVisible(true);
			ErrorTextField.setVisible(true);
		}
	}
	
	private void solve(String method, int n) {
		double mat[][] = new double[n][n + 1];
		int significantDigits = (int)PrecisionDigitsSpinner.getValue();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n + 1; j++) {
				mat[i][j] = Double.parseDouble(coef[i][j].getText());
			}
		}
		Solve obj = new Solve(method, mat, significantDigits);
		double[] ans = obj.chooseMethod();
		new Solution(ans);
	}
}
