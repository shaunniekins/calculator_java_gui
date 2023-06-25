import java.awt.*;
import java.util.Stack;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Calculator extends JFrame implements ActionListener {

    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton[] advanceOperationButtons;
    private JButton equalsButton, clearButton, acButton;
    private JButton openBraceButton, closeBraceButton, dotButton;
    private JPanel buttonPanel, displayPanel;

    private double firstNumber = 0.0;
    private double secondNumber = 0.0;
    private String operator = "";
    private String symbol = "";
    private boolean isAdvance = false;
    private double PI = 3.141592;
    private String firstChar;

    public Calculator() {
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
        display.setForeground(Color.WHITE);
        display.setBorder(emptyBorder);
        display.setFont(new Font(display.getFont().getName(), display.getFont().getStyle(), 20));
        display.setPreferredSize(new Dimension(300, display.getPreferredSize().height));

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

        operationButtons = new JButton[8];
        operationButtons[0] = new JButton("+");
        operationButtons[1] = new JButton("-");
        operationButtons[2] = new JButton("*");
        operationButtons[3] = new JButton("/");
        operationButtons[4] = new JButton("^");
        operationButtons[5] = new JButton("π");
        operationButtons[6] = new JButton("√");
        operationButtons[7] = new JButton("%");
        for (JButton button : operationButtons) {
            button.setBackground(darkGrayColor);
            button.setForeground(Color.WHITE);
            button.setBorder(emptyBorder);
            button.setPreferredSize(new Dimension(80, 60));
            button.addActionListener(this);
        }
        
        dotButton = new JButton(".");
        dotButton.setBackground(darkGrayColor);
        dotButton.setForeground(Color.WHITE);
        dotButton.setBorder(emptyBorder);
        dotButton.setPreferredSize(new Dimension(80, 60));
        dotButton.addActionListener(this);

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

        acButton = new JButton("AC");
        acButton.setBackground(darkGrayColor);
        acButton.setForeground(Color.WHITE);
        acButton.setBorder(emptyBorder);
        acButton.setPreferredSize(new Dimension(80, 60));
        acButton.addActionListener(this);

        displayPanel = new JPanel(new MigLayout("fill"));
        buttonPanel = new JPanel(new MigLayout("fill"));

        displayPanel.setBackground(blackColor);
        buttonPanel.setBackground(blackColor);

        add(displayPanel, "wrap");
        add(buttonPanel, "wrap");

        displayPanel.add(display, "growx");

        buttonPanel.add(acButton, "cell 0 0, center, grow 200");
        buttonPanel.add(clearButton, "cell 1 0, center, grow 200");
        buttonPanel.add(openBraceButton, "cell 2 0, center, grow 200");
        buttonPanel.add(closeBraceButton, "cell 3 0, center, grow 200");
        buttonPanel.add(operationButtons[5], "cell 4 0, center, grow 200");

        buttonPanel.add(numberButtons[7], "cell 0 1, center, grow 200");
        buttonPanel.add(numberButtons[8], "cell 1 1, center, grow 200");
        buttonPanel.add(numberButtons[9], "cell 2 1, center, grow 200");
        buttonPanel.add(operationButtons[3], "cell 3 1, center, grow 200");
        buttonPanel.add(operationButtons[6], "cell 4 1, center, grow 200");

        buttonPanel.add(numberButtons[4], "cell 0 2, center, grow 200");
        buttonPanel.add(numberButtons[5], "cell 1 2, center, grow 200");
        buttonPanel.add(numberButtons[6], "cell 2 2, center, grow 200");
        buttonPanel.add(operationButtons[2], "cell 3 2, center, grow 200");
        buttonPanel.add(operationButtons[4], "cell 4 2, center, grow 200");

        buttonPanel.add(numberButtons[1], "cell 0 3, center, grow 200");
        buttonPanel.add(numberButtons[2], "cell 1 3, center, grow 200");
        buttonPanel.add(numberButtons[3], "cell 2 3, center, grow 200");
        buttonPanel.add(operationButtons[1], "cell 3 3, center, grow 200");
        buttonPanel.add(equalsButton, "cell 4 3 1 2, center, grow 200"); // Set grow value to 200 and merged two rows

        buttonPanel.add(numberButtons[0], "cell 0 4, center, grow 200");
        buttonPanel.add(dotButton, "cell 1 4, center, grow 200");
        buttonPanel.add(operationButtons[7] , "cell 2 4, center, grow 200");
        buttonPanel.add(operationButtons[0], "cell 3 4, center, grow 200");


        int buttonPanelWidth = buttonPanel.getPreferredSize().width;
        display.setPreferredSize(new Dimension(buttonPanelWidth, (display.getPreferredSize().height) * 4));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        for (int i = 0; i < 10; i++) {
            if (source == numberButtons[i]) {
                display.setText(display.getText() + i);
                return;
            }
        }

        for (int i = 0; i < 8; i++) {
            if (source == operationButtons[i]) {
                String operator = operationButtons[i].getText();
                handleOperation(operator);
                return;
            }
        }

        if (source == dotButton) {
            display.setText(display.getText() + ".");
        }

        if (source == openBraceButton) {
            display.setText(display.getText() + "(");
        }

        if (source == closeBraceButton) {
            display.setText(display.getText() + ")");
        }

        if (source == equalsButton) {
            evaluateExpression();
        }

        if (source == acButton) {
            display.setText("");
        }

        if (source == clearButton) {
            String text = display.getText();
            if (!text.isEmpty()) {
                text = text.substring(0, text.length() - 1);
                display.setText(text);
            }
        }
    }

    private void handleOperation(String operator) {
        String expression = display.getText();
        if (!expression.isEmpty()) {
            if (operator.equals("π")) {
                expression += " * " + Math.PI;
            } else if (operator.equals("√")) {
                expression = "sqrt(" + expression + ")";
            } else if (operator.equals("%")) {
                expression += " / 100";
            } else {
                expression += " " + operator + " ";
            }
            display.setText(expression);
        }
    }

    private void evaluateExpression() {
        String expression = display.getText();
        if (!expression.isEmpty()) {
            try {
                double result = evaluate(expression);
                display.setText(String.valueOf(result));
            } catch (Exception e) {
                display.setText("Error");
            }
        }
    }

    private double evaluate(String expression) throws Exception {
        expression = expression.replace("π", String.valueOf(Math.PI));
        expression = expression.replace("sqrt", "Math.sqrt");
        expression = expression.replace("%", "/100");

        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == ' ')
                continue;

            if (Character.isDigit(ch) || ch == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(ch) || ch == '.')) {
                    sb.append(ch);
                    i++;
                    if (i < expression.length())
                        ch = expression.charAt(i);
                }
                i--;
                double number = Double.parseDouble(sb.toString());
                numbers.push(number);
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (operators.peek() != '(')
                    numbers.push(applyOperator(operators.pop(), numbers.pop(), numbers.pop()));
                operators.pop();
            } else if (isOperator(ch)) {
                while (!operators.empty() && hasPrecedence(ch, operators.peek()))
                    numbers.push(applyOperator(operators.pop(), numbers.pop(), numbers.pop()));
                operators.push(ch);
            }
        }

        while (!operators.empty())
            numbers.push(applyOperator(operators.pop(), numbers.pop(), numbers.pop()));

        return numbers.pop();
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

    private boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        return (op1 == '^' && op2 != '^') || (op1 != '^' && op2 != '^');
    }

    private double applyOperator(char operator, double b, double a) throws Exception {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b != 0)
                    return a / b;
                else
                    throw new Exception("Division by zero");
            case '^':
                return Math.pow(a, b);
            default:
                throw new Exception("Invalid operator");
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	try {
        			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
        				| UnsupportedLookAndFeelException e) {
        			e.printStackTrace();
        		}
                new Calculator().setVisible(true);
            }
        });
    }
}
