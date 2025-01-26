package main;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
		Dicionário.setBounds(100, 100, 450, 300);
		Dicionário.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dicionário.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(34, 91, 180, 76);
		Dicionário.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Localizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(70, 173, 105, 27);
		Dicionário.getContentPane().add(btnNewButton);
		
		JLabel labelBandeira1 = new JLabel("");
		labelBandeira1.setIcon(new ImageIcon(TelaDicionário.class.getResource("/imagens/Português.png")));
		labelBandeira1.setBounds(89, 12, 60, 41);
		Dicionário.getContentPane().add(labelBandeira1);

		JLabel labelBandeira2 = new JLabel("");
		labelBandeira2.setIcon(new ImageIcon(TelaDicionário.class.getResource("/imagens/Inglês.png")));
		labelBandeira2.setBounds(278, 12, 60, 41);
		Dicionário.getContentPane().add(labelBandeira2);
		
		ArrayList<JLabel> labelsBandeiras = new ArrayList<JLabel>(Arrays.asList(labelBandeira1, labelBandeira2));

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				definirBandeiras(comboBox, labelsBandeiras);
			}
		});
		
		comboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Português    >    Inglês", "Português    >    Espanhol", "Português    >    Alemão",
						"Inglês    >    Português", "Espanhol    >    Português", "Alemão    >    Português" }));
		
		definirBandeiras(comboBox, labelsBandeiras);
		
		// Criando um renderizador personalizado
		DefaultListCellRenderer renderizador = new DefaultListCellRenderer();
		renderizador.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza o texto
		comboBox.setRenderer(renderizador);

		comboBox.setBounds(34, 53, 364, 26);
		Dicionário.getContentPane().add(comboBox);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(229, 91, 169, 76);
		Dicionário.getContentPane().add(textArea);

		JLabel lblIdiomaCorrente = new JLabel("Tradutor");
		lblIdiomaCorrente.setBounds(188, 24, 60, 17);
		Dicionário.getContentPane().add(lblIdiomaCorrente);

		JLabel feedback = new JLabel("");
		feedback.setBounds(25, 212, 383, 27);
		Dicionário.getContentPane().add(feedback);

		JButton btnTraduzir = new JButton("Traduzir");
		btnTraduzir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		btnTraduzir.setBounds(260, 173, 105, 27);
		Dicionário.getContentPane().add(btnTraduzir);
	}

	
	private void definirBandeiras(JComboBox<String> comboBox, ArrayList<JLabel> labelsBadeiras) {
		int index = 0;
		String idiomas = (String) comboBox.getSelectedItem();
		ArrayList<String> idiomasList = separarIdiomas(idiomas);
		
		for (JLabel label : labelsBadeiras) {
			ImageIcon bandeiraIcon = carregarBandeira(idiomasList.get(index));
			label.setIcon(new ImageIcon(bandeiraIcon
					.getImage()
					.getScaledInstance(40, 30, Image.SCALE_DEFAULT)));
			index++;
		}
	}
	
	
	private ImageIcon carregarBandeira(String idioma) {
		ImageIcon bandeiraIcon = new ImageIcon(
				Objects.requireNonNull(
						getClass().
						getResource("/imagens/" + idioma + ".png")));
		return bandeiraIcon;
	}
	
	private ArrayList<String> separarIdiomas(String idiomas) {
		String[] arrayIdiomas = idiomas.replace(" ", "").split(">");
	    return new ArrayList<>(Arrays.asList(arrayIdiomas));
	}
}
