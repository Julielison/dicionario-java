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
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.Font;

public class TelaDicionário {

	private JFrame frameDicionário;
	private JTextField palavraParaTraduzir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		frameDicionário.setBounds(100, 100, 450, 300);
		frameDicionário.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameDicionário.getContentPane().setLayout(null);
		frameDicionário.setResizable(false);

		
		JLabel labelBandeira1 = new JLabel("");
		labelBandeira1.setIcon(new ImageIcon(TelaDicionário.class.getResource("/imagens/Português.png")));
		labelBandeira1.setBounds(89, 12, 60, 41);
		frameDicionário.getContentPane().add(labelBandeira1);

		JLabel labelBandeira2 = new JLabel("");
		labelBandeira2.setIcon(new ImageIcon(TelaDicionário.class.getResource("/imagens/Inglês.png")));
		labelBandeira2.setBounds(278, 12, 60, 41);
		frameDicionário.getContentPane().add(labelBandeira2);
		
		ArrayList<JLabel> labelsBandeiras = new ArrayList<JLabel>(Arrays.asList(labelBandeira1, labelBandeira2));

		JComboBox<String> comboBoxTraducao = new JComboBox<String>();
		comboBoxTraducao.setFont(new Font("Dialog", Font.BOLD, 14));
		comboBoxTraducao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				definirBandeiras(comboBoxTraducao, labelsBandeiras);
			}
		});
		
		comboBoxTraducao.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Português    >    Inglês", "Português    >    Espanhol", "Português    >    Alemão",
						"Inglês    >    Português", "Espanhol    >    Português", "Alemão    >    Português" }));
		
		definirBandeiras(comboBoxTraducao, labelsBandeiras);
		
		// Criando um renderizador personalizado
		DefaultListCellRenderer renderizador = new DefaultListCellRenderer();
		renderizador.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza o texto
		comboBoxTraducao.setRenderer(renderizador);

		comboBoxTraducao.setBounds(34, 53, 364, 26);
		frameDicionário.getContentPane().add(comboBoxTraducao);

		JLabel lblIdiomaCorrente = new JLabel("Tradutor");
		lblIdiomaCorrente.setBounds(188, 24, 60, 17);
		frameDicionário.getContentPane().add(lblIdiomaCorrente);

		JLabel feedback = new JLabel("");
		feedback.setBounds(34, 212, 364, 27);
		frameDicionário.getContentPane().add(feedback);

		JButton btnTraduzir = new JButton("Traduzir");
		btnTraduzir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		btnTraduzir.setBounds(293, 173, 105, 27);
		frameDicionário.getContentPane().add(btnTraduzir);
		
		palavraParaTraduzir = new JTextField();
		palavraParaTraduzir.setFont(new Font("Dialog", Font.PLAIN, 16));
		palavraParaTraduzir.setToolTipText("");
		palavraParaTraduzir.setBounds(35, 91, 182, 76);
		frameDicionário.getContentPane().add(palavraParaTraduzir);
		palavraParaTraduzir.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(229, 91, 169, 76);
		frameDicionário.getContentPane().add(scrollPane);
		
		JTextArea resultadoTraduçãoLocalização = new JTextArea();
		resultadoTraduçãoLocalização.setFont(new Font("Dialog", Font.PLAIN, 16));
		resultadoTraduçãoLocalização.setEditable(false);
		scrollPane.setViewportView(resultadoTraduçãoLocalização);
		
		JButton btnNewButton = new JButton("Localizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnNewButton.setBounds(34, 173, 105, 27);
		frameDicionário.getContentPane().add(btnNewButton);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(165, 173, 105, 27);
		frameDicionário.getContentPane().add(btnLimpar);
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
