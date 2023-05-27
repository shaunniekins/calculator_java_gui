import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

public class file extends JFrame implements ActionListener{
	// GUI components
    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton[] advanceOperationButtons;
    private JButton equalsButton, clearButton;
    private JButton openBraceButton, closeBraceButton;
    private JPanel buttonPanel, displayPanel;
    
	public file() {
		Color lightGrayColor = Color.GRAY;
        Color blackColor = new Color(0x1C1C1C);
        Color darkGrayColor = Color.DARK_GRAY;
        Color orangeColor = new Color(255, 149, 0);
        
        Border emptyBorder = BorderFactory.createEmptyBorder();
        
		setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(blackColor);
        setLayout(new MigLayout("center"));
        
        
        display = new JTextField();
        display.setEditable(false);
        display.setBackground(darkGrayColor);
        display.setBorder(emptyBorder);
//        display.setPreferredSize(new Dimension(300, display.getPreferredSize().height)); // Set preferred width to 200
        
        // Initialize components
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setBackground(lightGrayColor);
            numberButtons[i].setForeground(Color.WHITE);
            numberButtons[i].setBorder(emptyBorder);
            numberButtons[i].setPreferredSize(new Dimension(80, 60));
            numberButtons[i].addActionListener(this);
        }
        
        operationButtons = new JButton[4];
        operationButtons[0] = new JButton("+");
        operationButtons[1] = new JButton("-");
        operationButtons[2] = new JButton("*");
        operationButtons[3] = new JButton("/");
        for (JButton button : operationButtons) {
        	button.setBackground(darkGrayColor);
        	button.setForeground(Color.WHITE);
        	button.setBorder(emptyBorder);
        	button.setPreferredSize(new Dimension(80, 60));
            button.addActionListener(this);
        }
        
        advanceOperationButtons = new JButton[6];
        advanceOperationButtons[0] = new JButton("mod");
        advanceOperationButtons[1] = new JButton("π");
        advanceOperationButtons[2] = new JButton("√");
        advanceOperationButtons[3] = new JButton("^2");
        advanceOperationButtons[4] = new JButton(".");
        advanceOperationButtons[5] = new JButton("%");
        for (JButton button : advanceOperationButtons) {
        	button.setBackground(darkGrayColor);
        	button.setForeground(Color.WHITE);
        	button.setBorder(emptyBorder);
        	button.setPreferredSize(new Dimension(80, 60));
            button.addActionListener(this);
        }
        
        equalsButton = new JButton("=");
        equalsButton.setBackground(orangeColor);
        equalsButton.setForeground(Color.WHITE);
        equalsButton.setBorder(emptyBorder);
        equalsButton.setPreferredSize(new Dimension(80, 60));
        equalsButton.addActionListener(this);

        openBraceButton = new JButton("(");
        openBraceButton.setBackground(darkGrayColor);
        openBraceButton.setForeground(Color.WHITE);
        openBraceButton.setBorder(emptyBorder);
        openBraceButton.setPreferredSize(new Dimension(80, 60));
        openBraceButton.addActionListener(this);
        
        closeBraceButton = new JButton(")");
        closeBraceButton.setBackground(darkGrayColor);
        closeBraceButton.setForeground(Color.WHITE);
        closeBraceButton.setBorder(emptyBorder);
        closeBraceButton.setPreferredSize(new Dimension(80, 60));
        closeBraceButton.addActionListener(this);
        
        clearButton = new JButton("⌫");
        clearButton.setBackground(darkGrayColor);
        clearButton.setForeground(Color.WHITE);
        clearButton.setBorder(emptyBorder);
        clearButton.setPreferredSize(new Dimension(80, 60));
        clearButton.addActionListener(this);
        
        // Create the panels
        displayPanel = new JPanel(new MigLayout("fill"));
        buttonPanel = new JPanel(new MigLayout("fill"));
        
        displayPanel.setBackground(blackColor);
        buttonPanel.setBackground(blackColor);
        
        add(displayPanel, "wrap");
        add(buttonPanel, "wrap");
        
        displayPanel.add(display, "growx");

        // column row width height
        buttonPanel.add(clearButton, "cell 0 0, center, grow 200"); // Set grow value to 200
        buttonPanel.add(openBraceButton, "cell 1 0, center, grow 200");
        buttonPanel.add(closeBraceButton, "cell 2 0, center, grow 200");
        buttonPanel.add(advanceOperationButtons[0], "cell 3 0, center, grow 200");
        buttonPanel.add(advanceOperationButtons[1], "cell 4 0, center, grow 200");

        buttonPanel.add(numberButtons[7], "cell 0 1, center, grow 200");
        buttonPanel.add(numberButtons[8], "cell 1 1, center, grow 200");
        buttonPanel.add(numberButtons[9], "cell 2 1, center, grow 200");
        buttonPanel.add(operationButtons[3], "cell 3 1, center, grow 200");
        buttonPanel.add(advanceOperationButtons[2], "cell 4 1, center, grow 200");

        buttonPanel.add(numberButtons[4], "cell 0 2, center, grow 200");
        buttonPanel.add(numberButtons[5], "cell 1 2, center, grow 200");
        buttonPanel.add(numberButtons[6], "cell 2 2, center, grow 200");
        buttonPanel.add(operationButtons[2], "cell 3 2, center, grow 200");
        buttonPanel.add(advanceOperationButtons[3], "cell 4 2, center, grow 200");

        buttonPanel.add(numberButtons[1], "cell 0 3, center, grow 200");
        buttonPanel.add(numberButtons[2], "cell 1 3, center, grow 200");
        buttonPanel.add(numberButtons[3], "cell 2 3, center, grow 200");
        buttonPanel.add(operationButtons[1], "cell 3 3, center, grow 200");
        buttonPanel.add(equalsButton, "cell 4 3 1 2, center, grow 200"); // Set grow value to 200 and merged two rows

        buttonPanel.add(numberButtons[0], "cell 0 4, center, grow 200");
        buttonPanel.add(advanceOperationButtons[4], "cell 1 4, center, grow 200");
        buttonPanel.add(advanceOperationButtons[5], "cell 2 4, center, grow 200");
        buttonPanel.add(operationButtons[0], "cell 3 4, center, grow 200");

        
        int buttonPanelWidth = buttonPanel.getPreferredSize().width;
        display.setPreferredSize(new Dimension(buttonPanelWidth, (display.getPreferredSize().height) * 2));
	}
	
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new file().setVisible(true);
            }
        });
    }
}
