import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingTemperatureConverter extends JFrame {

    private JTextField inputField;
    private JComboBox<String> inputUnitCombo;
    private JComboBox<String> outputUnitCombo;
    private JLabel resultLabel;

    public SwingTemperatureConverter() {
        setTitle("Temperature Converter");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));
        getContentPane().setBackground(new Color(220, 220, 220)); // Light gray background

        // Components
        JLabel inputLabel = new JLabel("Enter Temperature:");
        inputField = new JTextField();
        inputUnitCombo = new JComboBox<>(new String[]{"Celsius", "Fahrenheit"});
        JLabel outputLabel = new JLabel("Convert to:");
        outputUnitCombo = new JComboBox<>(new String[]{"Celsius", "Fahrenheit"});
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel();
        JButton clearButton = new JButton("Clear");


        // Set colors for components
        inputLabel.setForeground(new Color(0, 102, 204)); // Dark blue
        convertButton.setBackground(new Color(0, 153, 51)); // Green
        convertButton.setForeground(Color.WHITE);
        outputLabel.setForeground(new Color(0, 102, 204)); // Dark blue
        resultLabel.setForeground(new Color(0, 102, 204)); // Dark blue
        //clearButton.setForeground(Color.GREEN);
        clearButton.setBackground(new Color(0, 153, 51)); // Green
        clearButton.setForeground(Color.WHITE);
        // Add components to the frame
        add(inputLabel);
        add(inputField);
        add(new JLabel("Select Input Unit :"));
        add(inputUnitCombo);
        add(outputLabel);
        add(outputUnitCombo);
        add(new JLabel());
        add(resultLabel);
        add(convertButton);
        //add(resultLabel);
        add(clearButton);

        // Add ActionListener to the convertButton
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        // Set colors for the heading
        Font headingFont = new Font("Arial", Font.BOLD, 20);
        inputLabel.setFont(headingFont);
        outputLabel.setFont(headingFont);
        resultLabel.setFont(headingFont);

        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
        ImageIcon image = new ImageIcon("T");
        setIconImage(image.getImage());
    }

    private void convertTemperature() {
        // Perform input validation
        String inputText = inputField.getText().trim();
        if (inputText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a temperature value.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double temperature = Double.parseDouble(inputText);
            String inputUnit = (String) inputUnitCombo.getSelectedItem();
            String outputUnit = (String) outputUnitCombo.getSelectedItem();

            double result;

            if (inputUnit.equals("Celsius") && outputUnit.equals("Fahrenheit")) {
                result = (temperature * 9 / 5) + 32;
            } else if (inputUnit.equals("Fahrenheit") && outputUnit.equals("Celsius")) {
                result = (temperature - 32) * 5 / 9;
            } else {
                result = temperature; // No conversion needed
            }

            resultLabel.setText("Result: " + result + " " + outputUnit);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid temperature value. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void clearFields() {
        inputField.setText("");
        resultLabel.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SwingTemperatureConverter());
    }
}


