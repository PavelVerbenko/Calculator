import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Calculator extends JFrame implements ActionListener {
    private JTextField num1Field;
    private JTextField num2Filed;
    private JComboBox<String> operationBox;
    private JButton calculateButton;
    private JLabel resultLabel;

    public Calculator() {
        setTitle("Калькулятор");
        setSize(800, 400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        num1Field = new JTextField();
        num2Filed = new JTextField();
        operationBox = new JComboBox<>(new String[]{"+", "-", "*", "/", "%"});
        calculateButton = new JButton("Вычислить");
        resultLabel = new JLabel("Результат: ");
        add(new JLabel("Введите первое число: "));
        add(num1Field);
        add(new JLabel("Введите второе число: "));
        add(num2Filed);
        add(new JLabel("Выберите операцию: "));
        add(operationBox);
        add(calculateButton);
        add(resultLabel);

        calculateButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double num1 = Double.parseDouble(num1Field.getText());
        double num2 = Double.parseDouble(num1Field.getText());
        String operation = (String) operationBox.getSelectedItem();
        double result = 0;

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
            default:
                System.out.println("Ошибка: неверная операция.");
                return;
        }
        resultLabel.setText("Результат: " + result);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}