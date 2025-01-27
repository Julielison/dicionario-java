package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

public class Dicionario {
	private String idiomaCorrente;
	private String idiomaDestino;
	private ArrayList<String> idiomasArrayList = new ArrayList<String>(List.of("Português", "Inglês", "Espanhol", "Alemão"));
 	
	public Dicionario(String x) throws Exception {
		if (idiomaExiste(x)) {
			this.idiomaCorrente = x;
		} else {
			throw new Exception("Idioma indisponível");
		}
	}
	
	private boolean idiomaExiste(String x) {
		for (String idioma : idiomasArrayList) {
			if (!idioma.equalsIgnoreCase(x)) {
				return false;
			}
		}
		return true;
	}
	
	public ArrayList<String> getIdiomas() {
		return idiomasArrayList;
	}
	
	public void setIdioma(String x) {
		this.idiomaCorrente = x;
	}
	
	public ArrayList<String> traduzirParaPortugues(String termo) {
        return obterTraducoes(termo);
    }
	
	public ArrayList<String> traduzirParaIdioma(String termo) {
		return obterTraducoes(termo);
	}
	
	public ArrayList<String> obterTraducoes(String termo) {
		ArrayList<String> traducoes = new ArrayList<String>();
		
		for (String elemento : carregarParesDeTraducao()) {
			String[] parTraducao = elemento.split(";");
			if (parTraducao[0].equalsIgnoreCase(termo)) {
				traducoes.add(parTraducao[1]);
			}
		}
		return traducoes;
	}
	
	public ArrayList<String> localizarPalavraIdioma(String termo) {
		return localizarPalavra(termo);
	}
	
	public ArrayList<String> localizarPalavraPortugues(String termo) {
		return localizarPalavra(termo);
	}
	
	public ArrayList<String> localizarPalavra(String termo) {
		ArrayList<String> palavrasLocalizadas = new ArrayList<String>();
		
		int index = 0;
		if (idiomaCorrente.equalsIgnoreCase("Português")) {
			index = 1;
		}
		
		for (String string : palavrasLocalizadas) {
			String[] parTraducao = string.split(";");
			String palavra = parTraducao[index];
			
			if (palavra.contains(string)) {
				palavrasLocalizadas.add(palavra);
			}
		}
		return palavrasLocalizadas;
	}
	
	public ArrayList<String> carregarParesDeTraducao (){
		ArrayList<String> paresDeTraducao = new ArrayList<String>();
		
		String idiomaArquivo = idiomaDestino;
		if (idiomaDestino.equalsIgnoreCase("Português")) {
			idiomaArquivo = idiomaCorrente;
		}
		try {
        	Scanner scanner = new Scanner(new File("src/dados/" + idiomaArquivo +".csv"));
        	scanner.nextLine();
        	while (scanner.hasNext()) {
        		String linha = scanner.nextLine();
        		paresDeTraducao.add(linha);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return paresDeTraducao;
	}
}
