package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class TelaDicionário {

	private JFrame Dicionário;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDicionário window = new TelaDicionário();
					window.Dicionário.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaDicionário() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Dicionário = new JFrame();
		Dicionario dicio = new Dicionario("inglês");
		Dicionário.setBounds(100, 100, 450, 300);
		Dicionário.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dicionário.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(24, 91, 176, 52);
		Dicionário.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Localizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(95, 179, 105, 27);
		Dicionário.getContentPane().add(btnNewButton);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		
		comboBox.setBounds(24, 53, 176, 26);
		Dicionário.getContentPane().add(comboBox);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();

		// Adiciona os idiomas nas comboBoxes
		for (String idioma : dicio.getIdiomas()) {
			comboBox.addItem(idioma);
			comboBox_1.addItem(idioma);
		}
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String idioma = (String) comboBox.getSelectedItem();
				try {
					dicio.setIdioma(idioma);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				if (!idioma.equalsIgnoreCase("português")) {
					comboBox_1.setSelectedItem("português");
				} else {
					comboBox_1.setSelectedItem(null);
				}
			}
		});
		comboBox_1.setBounds(232, 53, 166, 26);
		Dicionário.getContentPane().add(comboBox_1);

		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(232, 91, 166, 76);
		Dicionário.getContentPane().add(textArea);
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setEditable(true);
		comboBox_2.setBounds(24, 141, 176, 26);
		Dicionário.getContentPane().add(comboBox_2);
		
		
		JLabel lblIdiomaCorrente = new JLabel("Idioma corrente");
		lblIdiomaCorrente.setBounds(24, 32, 132, 17);
		Dicionário.getContentPane().add(lblIdiomaCorrente);
		
		JLabel lblTraduo = new JLabel("Idioma de tradução");
		lblTraduo.setBounds(232, 32, 132, 17);
		Dicionário.getContentPane().add(lblTraduo);
		
		JLabel feedback = new JLabel("");
		feedback.setBounds(25, 212, 383, 27);
		Dicionário.getContentPane().add(feedback);
		
		JButton btnTraduzir = new JButton("Traduzir");
		btnTraduzir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().equals(comboBox_1.getSelectedItem())) {
					feedback.setText("Tradução indisponível! Mude um dos idiomas");
					
				} else if (true) {
					
				}
			}
		});
		btnTraduzir.setBounds(232, 179, 105, 27);
		Dicionário.getContentPane().add(btnTraduzir);
	}
}
