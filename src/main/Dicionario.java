package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Dicionario {
	private String idiomaCorrente;
	private String idiomaDeTraducao;
	private ArrayList<String> idiomas = new ArrayList<String>(Arrays.asList("inglês", "português", "espanhol", "alemão"));
 	
	public Dicionario(String x) {
		this.idiomaCorrente = x;
	}
	
	public void setIdioma(String idioma) throws Exception {
		if (idiomas.contains(idioma)) {
			idiomaCorrente = idioma;
			
		} else {
			throw new Exception("Idioma indisponível");
		}
	}
	
	
	public ArrayList<String> getIdiomas() {
		return idiomas;
	}
	
	public ArrayList<String> traduzirParaPortugues(String termo) {
		ArrayList<String> traducoes = new ArrayList<String>();
		
        for (String linha : carregarParesDeTraducao(termo)) {
			
		}
        return traducoes;
    }
	
	public ArrayList<String> carregarParesDeTraducao (String idiomaEstrangeiro){
		ArrayList<String> paresDeTraducao = new ArrayList<String>();
		try {
        	Scanner scanner = new Scanner(new File("src/dados/" + idiomaEstrangeiro +".csv"));
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
	
	
	public static void main(String[] args) {
		Dicionario dicio = new Dicionario("inglês");
		ArrayList<String> teste = dicio.carregarParesDeTraducao("inglês");
		for (String linha : teste) {
			System.out.println(linha);
		}
	}
}
