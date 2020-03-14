import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A GUI that converts between different scales
 * Refer to https://contractorquotes.us/temperature-conversion-table/ for formula references
 * @author whyroland
 * @date 3/13/20
 */
public class TemperatureConverter {

	private final int WIDTH = 300, HEIGHT = 75;
	private String currentType;
	
	private String[] scales = {"Fahrenheit", "Celsius", "Kelvin"};
	
	private JFrame frame;
	private JComboBox options;
	private JTextField input;
	private JLabel output1, output2;
	private JPanel panel;
	
	public TemperatureConverter() {
		
		frame = new JFrame("Temperature Converter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		options = new JComboBox(scales);
		options.setSelectedIndex(0); // Sets the default to Fahrenheit
		currentType = "Fahrenheit";
		options.addActionListener(new ScaleListener());
		
		input = new JTextField(5);
		input.addActionListener(new TemperatureListener());
		
		output1 = new JLabel("Celsius: --"); // Sets default to Celsius
		output2 = new JLabel("Kelvin: --"); // Sets default to Kelvin
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.add(options);
		panel.add(input);
		panel.add(output1);
		panel.add(output2);
		
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	/**
	 * Changes scales
	 * @author whyroland
	 */
	private class ScaleListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String inputType = (String)(((JComboBox)e.getSource()).getSelectedItem());
			currentType = inputType;
			switch (inputType) {
				case "Fahrenheit":
					output1.setText("Celsius: --");
					output2.setText("Kelvin: --");
					break;
				case "Celsius":
					output1.setText("Fahrenheit: --");
					output2.setText("Kelvin: --");
					break;
				case "Kelvin":
					output1.setText("Fahrenheit: --");
					output2.setText("Celsius: --");
					break;
				default:
					System.out.println("Error!");
			}
		}
		
	}
	
	/**
	 * Converts temperatures
	 * @author whyroland
	 */
	private class TemperatureListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// Casting everything as an Integer for rounding and display purposes
			double fahrenheit;
			double celsius;
			double kelvin;
			double temp = Double.parseDouble(input.getText());
			
			switch (currentType) {
			case "Fahrenheit":
				celsius = (temp - 32) * 5 / 9;
				kelvin = celsius + 273.15;
				output1.setText(String.format("Celsius: %.2f", celsius));
				output2.setText(String.format("Kelvin: %.2f", kelvin));
				break;
			case "Celsius":
				fahrenheit = (temp * 9 / 5) + 32;
				kelvin = temp + 273.15;
				output1.setText(String.format("Fahrenheit: %.2f", fahrenheit));
				output2.setText(String.format("Kelvin: %.2f", kelvin));
				break;
			case "Kelvin":
				fahrenheit = (temp - 273) * 9 / 5 + 32;
				celsius = temp - 273.15;
				output1.setText(String.format("Fahrenheit: %.2f", fahrenheit));
				output2.setText(String.format("Celsius: %.2f", celsius));
				break;
			default:
				System.out.println("Error!");
			}
		}
		
	}
	
	public static void main(String[] args) {
		new TemperatureConverter();
	}

}
