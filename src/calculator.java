import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class calculator extends JFrame implements ActionListener {
    // GUI components
    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton equalsButton;
    private JButton clearButton;
    private JPanel buttonPanel;

    // Calculator state
    private double firstNumber;
    private String operator;

    public calculator() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Initialize components
        display = new JTextField();
        display.setEditable(false);
        
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
        }
        
        operationButtons = new JButton[4];
        operationButtons[0] = new JButton("+");
        operationButtons[1] = new JButton("-");
        operationButtons[2] = new JButton("*");
        operationButtons[3] = new JButton("/");
        for (JButton button : operationButtons) {
            button.addActionListener(this);
        }
        
        equalsButton = new JButton("=");
        equalsButton.addActionListener(this);
        
        clearButton = new JButton("C");
        clearButton.addActionListener(this);
        
        // Create button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));
        buttonPanel.add(numberButtons[7]);
        buttonPanel.add(numberButtons[8]);
        buttonPanel.add(numberButtons[9]);
        buttonPanel.add(operationButtons[0]);
        buttonPanel.add(numberButtons[4]);
        buttonPanel.add(numberButtons[5]);
        buttonPanel.add(numberButtons[6]);
        buttonPanel.add(operationButtons[1]);
        buttonPanel.add(numberButtons[1]);
        buttonPanel.add(numberButtons[2]);
        buttonPanel.add(numberButtons[3]);
        buttonPanel.add(operationButtons[2]);
        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(clearButton);
        buttonPanel.add(equalsButton);
        buttonPanel.add(operationButtons[3]);
        
        // Set layout and add components to the frame
        setLayout(new BorderLayout());
        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        // Handle number button clicks
        for (int i = 0; i < 10; i++) {
            if (source == numberButtons[i]) {
                display.setText(display.getText() + i);
                return;
            }
        }
        
        // Handle operation button clicks
        for (int i = 0; i < 4; i++) {
            if (source == operationButtons[i]) {
                firstNumber = Double.parseDouble(display.getText());
                operator = operationButtons[i].getText();
                display.setText("");
                return;
            }
        }
        
        // Handle equals button click
        if (source == equalsButton) {
            double secondNumber = Double.parseDouble(display.getText());
            double result = calculateResult(firstNumber, secondNumber, operator);
            display.setText(String.valueOf(result));
        }
        
        // Handle clear button click
        if (source == clearButton) {
            display.setText("");
        }
    }
    
    private double calculateResult(double firstNumber, double secondNumber, String operator) {
        switch (operator) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                if (secondNumber != 0) {
                    return firstNumber / secondNumber;
                } else {
                    // Division by zero error
                    return Double.NaN;
                }
            default:
                return 0.0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new calculator().setVisible(true);
            }
        });
    }
}
