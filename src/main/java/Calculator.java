import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class Calculator extends JFrame implements ActionListener, ItemListener {
    private JTextField num1Field;
    private JTextField num2Field;
    private JComboBox<String> operationBox;
    private JButton calculateButton;
    private JLabel resultLabel;
    private JCheckBox darkModeCheckBox;
    private JLabel num1Label;
    private JLabel num2Label;
    private JLabel operationLabel;

    public Calculator() {
        setTitle("Калькулятор");
        setSize(800, 400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        num1Field = new JTextField();
        num2Field = new JTextField();
        operationBox = new JComboBox<>(new String[]{"+", "-", "*", "/", "%", "√", "sin", "cos", "tan", "log"});
        calculateButton = new JButton("Вычислить");
        resultLabel = new JLabel("Результат: ");
        darkModeCheckBox = new JCheckBox("Темная тема");

        num1Label = new JLabel("Введите первое число: ");
        num2Label = new JLabel("Введите второе число: ");
        operationLabel = new JLabel("Выберите операцию: ");

        calculateButton.setToolTipText("Нажмите, чтобы вычислить результат");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(num1Label, gbc);
        gbc.gridx = 1;
        add(num1Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(num2Label, gbc);
        gbc.gridx = 1;
        add(num2Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(operationLabel, gbc);
        gbc.gridx = 1;
        add(operationBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(calculateButton, gbc);
        gbc.gridx = 1;
        add(resultLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(darkModeCheckBox, gbc);

        calculateButton.addActionListener(this);
        darkModeCheckBox.addItemListener(this);

        calculateButton.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                calculateButton.setBackground(Color.LIGHT_GRAY);
            }

            @Override public void mouseExited(MouseEvent e) {
                calculateButton.setBackground(isDarkMode() ? Color.GRAY : Color.white);
            }
        });

        setTheme(false);
    }

    private void setTheme(boolean isDark) {
        Color backgroundColor = isDark ? Color.darkGray : Color.white;
        Color textColor = isDark ? Color.white : Color.BLACK;
        Color componentColor = isDark ? Color.gray : Color.white;

        getContentPane().setBackground(backgroundColor);
        num1Field.setBackground(componentColor);
        num2Field.setBackground(componentColor);
        operationBox.setBackground(componentColor);
        calculateButton.setBackground(componentColor);
        darkModeCheckBox.setBackground(componentColor);
        darkModeCheckBox.setBackground(backgroundColor);

        num1Field.setForeground(textColor);
        num2Field.setForeground(textColor);
        resultLabel.setForeground(textColor);
        darkModeCheckBox.setForeground(textColor);

        num1Label.setForeground(textColor);
        num2Label.setForeground(textColor);
        operationLabel.setForeground(textColor);

        Font font = new Font("Segoe UI", Font.PLAIN, 14);
        num1Field.setFont(font);
        num2Field.setFont(font);
        operationBox.setFont(font);
        calculateButton.setFont(font);
        resultLabel.setFont(font);
        darkModeCheckBox.setFont(font);
        num1Label.setFont(font);
        num2Label.setFont(font);
        operationLabel.setFont(font);

//        Color accentColor = isDark ? new Color(100, 149, 237) : new Color(70, 130, 180); // Cornflower Blue
//        calculateButton.setBackground(accentColor);
    }

    @Override public void actionPerformed(ActionEvent e) {
        double num1 = Double.parseDouble(num1Field.getText());
        double num2 = Double.parseDouble(num2Field.getText());
        String operation = (String) operationBox.getSelectedItem();
        double result = 0;

        try {
            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        System.out.println("Ошибка: деление на ноль!");
                        return;
                    }
                    break;
                case "%":
                    result = num1 % num2;
                    break;
                case "√":
                    if (num1 >= 0) {
                        result = Math.sqrt(num1);
                    } else {
                        resultLabel.setText("Ошибка: Невозможно извлечь корень из отрицательного числа!");
                        return;
                    }
                    break;
                case "sin":
                    result = Math.sin(Math.toRadians(num1));
                    break;
                case "cos":
                    result = Math.cos(Math.toRadians(num1));
                    break;
                case "tan":
                    result = Math.tan(Math.toRadians(num1));
                    break;
                case "log":
                    if (num1 > 0) {
                        result = Math.log10(num1);
                    } else {
                        resultLabel.setText("Ошибка: Логарифм от отрицательного числа или нуля не определен!");
                        return;
                    }
                    break;
                    }
                resultLabel.setText("Результат: " + result);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Ошибка: Введите корректные числа");
            }
        }

        @Override public void itemStateChanged(ItemEvent e) {
            if (e.getSource() == darkModeCheckBox) {
                setTheme(darkModeCheckBox.isSelected());
            }
        }

        private boolean isDarkMode() {
            return darkModeCheckBox.isSelected();
        }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}