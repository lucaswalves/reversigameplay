package com.reversi;

import java.util.ArrayList;

public class Board {
	/***************************** ATRIBUTOS *****************************/
	/*** Tamanho estático do tabuleiro quadrado ***/
	public static int SIZE = 8;
	/*** Número de casas do tabuleiro em função do tamanho SIZE ***/
	public Cell[][] cell = new Cell[SIZE][SIZE];

	/****************************** MÉTODOS ******************************/
	/*** Verifica se a posição está vazia ***/
	public boolean isEmpty(int x, int y) {
		if (this.cell[x][y].content == '_') {
			return true;
		} else {
			return false;
		}
	}

	/*** Verifica se a posição no tabuleiro possui algum elemento inimigo ao seu redor ***/
	public ArrayList<Coordinate> hasBadNeighborhood(int x, int y, char enemyCell) {
		ArrayList<Coordinate> badNeighbors = new ArrayList<Coordinate>();
		System.out.println("LOOKING FOR ENEMIES AROUND ["+x+"]["+y+"] - LOOKING FOR '" + enemyCell+"'");
		for (int i = x-1; i <= x+1; i++) {
			for (int j = y-1; j <= y+1; j++) {
				/* Respeito os limites de bordas do tabuleiro */
				if (i >= 0 && j >= 0 && i < SIZE || j < SIZE) {
					/* Verifico se é uma célula inimiga */
					if (this.cell[i][j].content == enemyCell) {
						/* Armazeno a célula inimiga em uma lista dinamica de coordenadas inteiras */
						System.out.print("FOUND AN ENEMY CELL (" + enemyCell +") AT [" +i+"]["+j+"]" );
						badNeighbors.add(new Coordinate(i,j));
						System.out.println(" - WATCHING TARGET, OVER.");
					}
				}
			}
		}
		return badNeighbors;
	}

	/*** Verifica toda a direção de uma coordenada (em relação a outra) até encontrar uma peça aliada ***/
	public void findAllies(int x, int y, char alliedCell, ArrayList<Coordinate> badNeighbors) {
		for (int i = 0; i < badNeighbors.size(); i++) {
			/* Diagonal superior esquerda */
			if (badNeighbors.get(i).x-1 == x && badNeighbors.get(i).y-1 == y) {
				System.out.println("Encontrei na Diagonal superior esquerda");
			}
			/* Esquerda */
			else if(badNeighbors.get(i).x-0 == x && badNeighbors.get(i).y-1 == y) {
				System.out.println("Encontrei na esquerda");
			}
			/* Diagonal inferior esquerda */
			else if(badNeighbors.get(i).x+1 == x && badNeighbors.get(i).y-1 == y) {
				System.out.println("Encontrei na Diagonal inferior esquerda");
			}
			/* Baixo */
			else if(badNeighbors.get(i).x+1 == x && badNeighbors.get(i).y-0 == y) {
				System.out.println("Encontrei logo abaixo");
			}
			/* Diagonal inferior direita */
			else if(badNeighbors.get(i).x+1 == x && badNeighbors.get(i).y+1 == y) {
				System.out.println("Encontrei na Diagonal inferior direita");
			}
			/* Direita */
			else if(badNeighbors.get(i).x-0 == x && badNeighbors.get(i).y+1 == y) {
				System.out.println("Encontrei na direita");
			}
			/* Diagonal superior direita */
			else if(badNeighbors.get(i).x-1 == x && badNeighbors.get(i).y+1 == y) {
				System.out.println("Encontrei na Diagonal superior direita");
			}
			/* Em cima */
			else if(badNeighbors.get(i).x-1 == x && badNeighbors.get(i).y-0 == y) {
				System.out.println("Encontrei logo em cima");
			}
			/* Erro - não encaixou em nenhum dos casos */
			else {
				System.out.println("Erro ao verificar linha de aliados!");
			}
		}
	}

	/*** Método de inserção no tabuleiro ***/
	public void insertItem(int x, int y, char newContent, String player) {
		System.out.println("Jogada: " + player + " jogou na casa ["+x+"]["+y+"] = " + newContent);
		this.cell[x][y].content = newContent;
	}

	/*** Reseta o tabuleiro ***/
	public void resetBoard() {
		for (int i = 0; i < this.cell.length; i++) {
			for (int j = 0; j < this.cell[i].length; j++) {
				if (i==((SIZE/2)-1) && j==((SIZE/2)-1) || i==(SIZE/2) && j==(SIZE/2)) {
					this.cell[i][j].content = 'O';
				}else if(i==((SIZE/2)-1) && j==(SIZE/2) || i==(SIZE/2) && j==((SIZE/2)-1)){
					this.cell[i][j].content = 'X';
				}
			}
		}
	}

	/*** Método Imprimir tabuleiro na tela ***/
	public void printBoard(Cell[][] board) {
		/**** Limpa a tela para exibir a matriz ****/
		this.screenClear();

		/**** Topo da tabela ****/
		for (int i = 0; i < board.length; i++) {
			if (i == 0)
				System.out.print("   ");

			System.out.print("_"+ i +"_ ");

			if (i == board.length-1)
				System.out.println();
		}

		/**** Imprime o resto da tabela ****/
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (j == 0) {System.out.print(i + " |_" + board[i][j].content + "_|");}
				else {System.out.print("_" + board[i][j].content + "_|");}
			}
			System.out.println();
		}
	}

	/*** "Limpa" a tela ***/
	public void screenClear(){
		for(int i = 0; i <= 20; i++){
			System.out.println();
		}
	}

	/***************************** GET'N SET *****************************/
	public Cell[][] getCell() {
		return cell;
	}
	public void setCell(Cell[][] cell) {
		this.cell = cell;
	}

	public static void main(String[] args) {
		Board board = new Board();

		Cell celulinha[][] = new Cell[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				celulinha[i][j] = new Cell();
			}
		}

		board.setCell(celulinha);
		board.printBoard(board.getCell());
		//board.insertItem(0, 0, 'X', "Humano");
		board.resetBoard();
		board.printBoard(board.getCell());
		board.hasBadNeighborhood(3, 3, 'X');
		board.findAllies(3, 3, 'O', board.hasBadNeighborhood(3, 3, 'X'));

	}
}
