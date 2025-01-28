package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
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
	private JTextField termoDigitado;
	static Dicionario dicionario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dicionario = new Dicionario("Português");
					TelaDicionário window = new TelaDicionário();
					window.frameDicionário.setVisible(true);
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
		frameDicionário = new JFrame();
		frameDicionário.setBounds(100, 100, 450, 310);
		frameDicionário.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameDicionário.getContentPane().setLayout(null);
		frameDicionário.setResizable(false);

		
		JLabel labelBandeira1 = new JLabel("");
		labelBandeira1.setIcon(new ImageIcon(TelaDicionário.class.getResource("/imagens/Português.png")));
		labelBandeira1.setBounds(94, 0, 60, 41);
		frameDicionário.getContentPane().add(labelBandeira1);

		JLabel labelBandeira2 = new JLabel("");
		labelBandeira2.setIcon(new ImageIcon(TelaDicionário.class.getResource("/imagens/Inglês.png")));
		labelBandeira2.setBounds(281, 0, 60, 41);
		frameDicionário.getContentPane().add(labelBandeira2);
		
		ArrayList<JLabel> labelsBandeiras = new ArrayList<JLabel>(Arrays.asList(labelBandeira1, labelBandeira2));

		JComboBox<String> comboBoxTraducao = new JComboBox<String>();
		comboBoxTraducao.setFont(new Font("Dialog", Font.BOLD, 14));
		comboBoxTraducao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				definirBandeiras(comboBoxTraducao, labelsBandeiras);
				definirIdiomasAtuais(comboBoxTraducao.getSelectedItem().toString());
			}
		});
		
		comboBoxTraducao.setModel(new DefaultComboBoxModel<String>(
				dicionario.montarStringDeTraducao(comboBoxTraducao).toArray(new String[0])));
		
		definirIdiomasAtuais(comboBoxTraducao.getSelectedItem().toString());
		definirBandeiras(comboBoxTraducao, labelsBandeiras);
		
		// Criando um renderizador personalizado
		DefaultListCellRenderer renderizador = new DefaultListCellRenderer();
		renderizador.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza o texto
		comboBoxTraducao.setRenderer(renderizador);

		comboBoxTraducao.setBounds(34, 47, 364, 26);
		frameDicionário.getContentPane().add(comboBoxTraducao);

		JLabel titulo = new JLabel("Tradutor");
		titulo.setFont(new Font("Dialog", Font.BOLD, 16));
		titulo.setBounds(176, 12, 79, 17);
		frameDicionário.getContentPane().add(titulo);

		JLabel feedback = new JLabel("");
		feedback.setBounds(34, 230, 364, 27);
		frameDicionário.getContentPane().add(feedback);

		JButton btnTraduzir = new JButton("Traduzir");
		btnTraduzir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		btnTraduzir.setBounds(293, 191, 105, 27);
		frameDicionário.getContentPane().add(btnTraduzir);
		
		termoDigitado = new JTextField();
		termoDigitado.setFont(new Font("Dialog", Font.PLAIN, 16));
		termoDigitado.setToolTipText("");
		termoDigitado.setBounds(35, 91, 182, 26);
		frameDicionário.getContentPane().add(termoDigitado);
		termoDigitado.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(229, 91, 169, 88);
		frameDicionário.getContentPane().add(scrollPane);
		
		JTextArea resultadoTraduçãoLocalização = new JTextArea();
		resultadoTraduçãoLocalização.setFont(new Font("Dialog", Font.PLAIN, 16));
		resultadoTraduçãoLocalização.setEditable(false);
		scrollPane.setViewportView(resultadoTraduçãoLocalização);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(34, 123, 183, 56);
		frameDicionário.getContentPane().add(scrollPane_1);
		
		JTextArea resultadoLozalização = new JTextArea();
		resultadoLozalização.setFont(new Font("Dialog", Font.PLAIN, 16));
		resultadoLozalização.setEditable(false);
		scrollPane_1.setViewportView(resultadoLozalização);
		
		JButton btnNewButton = new JButton("Localizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> palavrasLocalizadas;
				String termo = termoDigitado.getText();
				
				if (termo.isEmpty()) {
					feedback.setText("Digite alguma coisa!");
					resultadoTraduçãoLocalização.setText("");
					resultadoLozalização.setText("");
					return;
				}
				
				if (dicionario.getIdiomaCorrente().equalsIgnoreCase("Português")) {
					palavrasLocalizadas = dicionario.localizarPalavraPortugues(termo);
				} else {
					palavrasLocalizadas = dicionario.localizarPalavraIdioma(termo);
				}
				
				if (palavrasLocalizadas.isEmpty()) {
					feedback.setText("Nenhum termo encontrado");
					resultadoTraduçãoLocalização.setText("");
					resultadoLozalização.setText("");
					
				} else {
					resultadoLozalização.setText("");
					for (String palavra : palavrasLocalizadas) {
						resultadoLozalização.append(palavra + "\n");
					}
					feedback.setText("Palavras localizadas com sucesso");
				}
			}
		});
		btnNewButton.setBounds(34, 191, 105, 27);
		frameDicionário.getContentPane().add(btnNewButton);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resultadoTraduçãoLocalização.setText("");
				termoDigitado.setText("");
				feedback.setText("");
				resultadoLozalização.setText("");
			}
		});
		btnLimpar.setBounds(164, 191, 105, 27);
		frameDicionário.getContentPane().add(btnLimpar);
		
		JLabel lblNewLabel = new JLabel("Digite aqui:");
		lblNewLabel.setBounds(35, 76, 88, 17);
		frameDicionário.getContentPane().add(lblNewLabel);
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
	
	private ArrayList<String> separarIdiomas(String idiomasTraduçãoAtual) {
		String[] arrayIdiomas = idiomasTraduçãoAtual.replace(" ", "").split(">");
	    return new ArrayList<>(Arrays.asList(arrayIdiomas));
	}
	
	private void definirIdiomasAtuais(String traduçãoSelecionada) {
		ArrayList<String> idiomasAtuais = separarIdiomas(traduçãoSelecionada);
		
		dicionario.setIdioma(idiomasAtuais.get(0));
		dicionario.setIdiomaDestino(idiomasAtuais.get(1));
	}
}
