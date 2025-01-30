package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TelaDicionário {

	private JFrame frameDicionário;
	private JTextField textFieldTermo;
	static Dicionario dicionario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dicionario = new Dicionario("Inglês");
					TelaDicionário window = new TelaDicionário();
					window.frameDicionário.setVisible(true);
				} catch (Exception e) {
					System.out.println(e.getMessage());
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
		frameDicionário = new JFrame();
		frameDicionário.setBounds(100, 100, 450, 310);
		frameDicionário.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameDicionário.getContentPane().setLayout(null);
		frameDicionário.setResizable(false);

		textFieldTermo = new JTextField();
		textFieldTermo.setFont(new Font("Dialog", Font.PLAIN, 16));
		textFieldTermo.setToolTipText("");
		textFieldTermo.setBounds(35, 59, 148, 27);
		frameDicionário.getContentPane().add(textFieldTermo);
		textFieldTermo.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(195, 110, 203, 121);
		frameDicionário.getContentPane().add(scrollPane);
		
		JTextArea resultados = new JTextArea();
		resultados.setFont(new Font("Dialog", Font.PLAIN, 16));
		resultados.setEditable(false);
		scrollPane.setViewportView(resultados);
		
		JLabel labelBandeira = new JLabel("");
		labelBandeira.setBounds(298, 3, 72, 44);
		frameDicionário.getContentPane().add(labelBandeira);
		
		JComboBox<String> comboBoxIdiomaCorrente = new JComboBox<String>();
		comboBoxIdiomaCorrente.setFont(new Font("Dialog", Font.BOLD, 14));
		comboBoxIdiomaCorrente.setModel(new DefaultComboBoxModel<>(dicionario.getIdiomas().toArray(new String[0])));
		comboBoxIdiomaCorrente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dicionario.setIdioma(comboBoxIdiomaCorrente.getSelectedItem().toString());
				definirBandeira(labelBandeira);
			}
		});
		comboBoxIdiomaCorrente.setSelectedItem(dicionario.getIdioma());
		definirBandeira(labelBandeira);

		// Criando um renderizador personalizado
		DefaultListCellRenderer renderizador = new DefaultListCellRenderer();
		renderizador.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza o texto
		comboBoxIdiomaCorrente.setRenderer(renderizador);

		comboBoxIdiomaCorrente.setBounds(195, 59, 203, 26);
		frameDicionário.getContentPane().add(comboBoxIdiomaCorrente);
		
		
		JComboBox<String> comboBoxFunção = new JComboBox<String>();
		comboBoxFunção.setFont(new Font("Dialog", Font.BOLD, 14));
		comboBoxFunção.setModel(new DefaultComboBoxModel<String>(new String[] {"Traduzir para:", "Localizar no:"}));
		comboBoxFunção.setBounds(35, 111, 148, 26);
		frameDicionário.getContentPane().add(comboBoxFunção);
		
		JLabel lblFuncao = new JLabel("Função dos botões");
		lblFuncao.setBounds(34, 92, 148, 17);
		frameDicionário.getContentPane().add(lblFuncao);
		
		JLabel lblResultados = new JLabel("Resultados");
		lblResultados.setBounds(195, 93, 81, 17);
		frameDicionário.getContentPane().add(lblResultados);
		
		JLabel labelFeedback = new JLabel("");
		labelFeedback.setBounds(34, 232, 364, 17);
		frameDicionário.getContentPane().add(labelFeedback);
		
		JButton btnTdzParaPortuguês = new JButton("Português");
		btnTdzParaPortuguês.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> palavrasObtidas;
				String termo = textFieldTermo.getText();
				
				if (termo.isEmpty()) {
					resultados.setText("");
					labelFeedback.setText("Digite alguma coisa!");
					return;
				}
				
				if (comboBoxFunção.getSelectedItem().toString().equalsIgnoreCase("Traduzir para:")) {
					palavrasObtidas = dicionario.traduzirParaPortugues(termo);
				} else {
					palavrasObtidas = dicionario.localizarPalavraPortugues(termo);
				}
				resultados.setText("");
				
				if (palavrasObtidas.isEmpty()) {
					labelFeedback.setText("Nenhum resultado encontrado!");
				} else {
					
					for (String palavra : palavrasObtidas) {
						resultados.append(palavra + "\n");
					}
					labelFeedback.setText("Operação feita com sucesso! "
							+ palavrasObtidas.size() + " resultado(s).");
				}
			}
		});

		btnTdzParaPortuguês.setBounds(34, 142, 149, 27);
		frameDicionário.getContentPane().add(btnTdzParaPortuguês);
		
		JButton btnParaIdioma = new JButton("Idioma");
		btnParaIdioma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> palavrasObtidas;
				String termo = textFieldTermo.getText();
				
				if (termo.isEmpty()) {
					resultados.setText(termo);
					labelFeedback.setText("Digite alguma coisa!");
					return;
				}
				
				if (comboBoxFunção.getSelectedItem().toString().equalsIgnoreCase("Traduzir para:")) {
					palavrasObtidas = dicionario.traduzirParaIdioma(termo);
				} else {
					palavrasObtidas = dicionario.localizarPalavraIdioma(termo);
				}
				resultados.setText("");
				
				if (palavrasObtidas.isEmpty()) {
					labelFeedback.setText("Nenhum resultado encontrado!");
				} else {
					resultados.setText("");
					
					for (String palavra : palavrasObtidas) {
						resultados.append(palavra + "\n");
					}
					labelFeedback.setText("Operação feita com sucesso! "
							+ palavrasObtidas.size() + " resultado(s).");
				}
			}
		});
		btnParaIdioma.setBounds(34, 172, 149, 27);
		frameDicionário.getContentPane().add(btnParaIdioma);

		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resultados.setText("");
				textFieldTermo.setText("");
				labelFeedback.setText("");
			}
		});
		btnLimpar.setBounds(35, 204, 148, 27);
		frameDicionário.getContentPane().add(btnLimpar);
		
		JLabel lblDitgiteAqui = new JLabel("Digite aqui:");
		lblDitgiteAqui.setBounds(35, 40, 88, 17);
		frameDicionário.getContentPane().add(lblDitgiteAqui);
		
		JLabel lblTitulo = new JLabel("TRAIN");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTitulo.setBounds(74, 11, 60, 17);
		frameDicionário.getContentPane().add(lblTitulo);
		
		JLabel lblIdiomaCorrente = new JLabel("Idioma corrente");
		lblIdiomaCorrente.setBounds(195, 40, 112, 17);
		frameDicionário.getContentPane().add(lblIdiomaCorrente);
	}

	
	private void definirBandeira(JLabel labelBadeira) {
			ImageIcon bandeiraIcon = carregarBandeira(dicionario.getIdioma());
			labelBadeira.setIcon(new ImageIcon(bandeiraIcon
					.getImage()
					.getScaledInstance(40, 30, Image.SCALE_DEFAULT)));
	}
	
	
	private ImageIcon carregarBandeira(String idioma) {
		return new ImageIcon(
				Objects.requireNonNull(
						getClass().
						getResource("/imagens/" + idioma + ".png")));
	}
}
