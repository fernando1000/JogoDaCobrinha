package br.com.x10d;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Principal extends Application {

	private Rectangle cobrinha;
	private Rectangle comida;
	private Label labelGameOver;
	private Label labelPontuacao;
	private KeyCode direcaoAtual;
	
	private static final int VELOCIDADE_JOGO = 500;
	private static final int TAMANHO_TELA = 500;
	private static int POSICAO_HORIZONTAL_COLUNA_X = 240;
	private static int POSICAO_VERTICAL_LINHA_Y = 240;
	private static int TAMANHO_QUADRADO = 20;
	private int pontuacao;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Group root = new Group();
		Scene scene = new Scene(root, TAMANHO_TELA, TAMANHO_TELA, Color.LIGHTSKYBLUE);
		scene.setOnKeyPressed((event) -> this.teclaPressionada(event));

		cobrinha = new Rectangle();
		cobrinha.setWidth(TAMANHO_QUADRADO);
		cobrinha.setHeight(TAMANHO_QUADRADO);
		cobrinha.setX(POSICAO_HORIZONTAL_COLUNA_X);
		cobrinha.setY(POSICAO_VERTICAL_LINHA_Y);

		comida = new Rectangle();
		comida.setFill(Color.RED);
		comida.setWidth(TAMANHO_QUADRADO);
		comida.setHeight(TAMANHO_QUADRADO);

		labelGameOver = new Label();
		labelGameOver.setText("FIM DE JOGO");
		labelGameOver.setVisible(false);
		labelGameOver.setLayoutX(POSICAO_HORIZONTAL_COLUNA_X);
		labelGameOver.setLayoutY(POSICAO_VERTICAL_LINHA_Y);

		labelPontuacao = new Label();
		labelPontuacao.setText(""+pontuacao);
		labelPontuacao.setLayoutX(0);
		labelPontuacao.setLayoutY(0);

		root.getChildren().add(cobrinha);
		root.getChildren().add(comida);
		root.getChildren().add(labelGameOver);
		root.getChildren().add(labelPontuacao);
		
		stage.setTitle("JOGO DA COBRINHA");
		stage.setScene(scene);
		stage.show();
		
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				loopDoJogo();
			}
		};
		timer.start();		
	}
	
	private void loopDoJogo() {
		try {
			Thread.sleep(VELOCIDADE_JOGO);
			
			verificaDirecaoDaCobra();
			
			if(cobraComeuComida()) {
				criaNovaComidaPosicionandoNoJogo();
				adicionaPontuacao();
				aumentaTamanhoCobra();
			}
			verificaFimDeJogo();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}				
	}
	
	private void aumentaTamanhoCobra() {
		
		System.out.println("cobrinha.getWidth: "+cobrinha.getWidth());
		System.out.println("cobrinha.getHeight: "+cobrinha.getHeight());
		//System.out.println("cobrinha.getX: "+cobrinha.getX());
		//System.out.println("cobrinha.getY: "+cobrinha.getY());
		
		cobrinha.setWidth(cobrinha.getWidth());
		cobrinha.setHeight(cobrinha.getHeight()+TAMANHO_QUADRADO);
		//cobrinha.setX(POSICAO_HORIZONTAL_COLUNA_X);
		//cobrinha.setY(POSICAO_VERTICAL_LINHA_Y);

		System.out.println("#########################");
		System.out.println("cobrinha.getWidth: "+cobrinha.getWidth());
		System.out.println("cobrinha.getHeight: "+cobrinha.getHeight());
		//System.out.println("cobrinha.getX: "+cobrinha.getX());
		//System.out.println("cobrinha.getY: "+cobrinha.getY());
		
		
	}
	private void teclaPressionada(KeyEvent event) {	
		KeyCode teclaPressionada = event.getCode();
		if(teclaPressionada == KeyCode.UP) {
			direcaoAtual = teclaPressionada;
		}
		if(teclaPressionada == KeyCode.DOWN) {
			direcaoAtual = teclaPressionada;
		}
		if(teclaPressionada == KeyCode.LEFT) {
			direcaoAtual = teclaPressionada;
		}
		if(teclaPressionada == KeyCode.RIGHT) {
			direcaoAtual = teclaPressionada;
		}
	}
	
	private void vaiParaCima() {
		cobrinha.setY(cobrinha.getY() - TAMANHO_QUADRADO);
	}
	private void vaiParaBaixo() {
		cobrinha.setY(cobrinha.getY() + TAMANHO_QUADRADO);
	}
	private void vaiParaEsquerda() {
		cobrinha.setX(cobrinha.getX() - TAMANHO_QUADRADO);
	}
	private void vaiParaDireita() {
		cobrinha.setX(cobrinha.getX() + TAMANHO_QUADRADO);
	}

	private void verificaDirecaoDaCobra() {
		if(direcaoAtual == KeyCode.UP) {
			vaiParaCima();
		}
		if(direcaoAtual == KeyCode.DOWN) {
			vaiParaBaixo();
		}
		if(direcaoAtual == KeyCode.LEFT) {
			vaiParaEsquerda();
		}
		if(direcaoAtual == KeyCode.RIGHT) {
			vaiParaDireita();
		}
	}
	
	private boolean cobraComeuComida() {
		return (cobrinha.getY() == comida.getY() && cobrinha.getX() == comida.getY());
	}

	private void verificaFimDeJogo() {
		if (cobrinha.getY() < 0 || cobrinha.getY() > TAMANHO_TELA 
				|| cobrinha.getX() < 0 || cobrinha.getX() > TAMANHO_TELA) {
			labelGameOver.setVisible(true);
		}
	}

	private void criaNovaComidaPosicionandoNoJogo() {
		int posicaoComida = criaNovaComida();
		comida.setX(posicaoComida);
		comida.setY(posicaoComida);
	}
	
	private int criaNovaComida() {
		int numeroAleatorio = new Random().nextInt(25);
		int posicaoComida = numeroAleatorio * TAMANHO_QUADRADO;
		return posicaoComida;
	}
	
	private void adicionaPontuacao() {
		pontuacao++;
		labelPontuacao.setText(""+pontuacao);
	}

}
