package sudoku;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import sudoku.model.Board;
import sudoku.model.Space;
import sudoku.util.BordTemplate;

public class Main {

	private final static Scanner scanner = new Scanner(System.in);
	
	private static Board board;
	
	private final static int BOARD_LIMIT = 9;
	
	public static void main(String[] args) {
		
		final var positions = Stream.of(args)
									.collect(Collectors.toMap(
												k -> k.split(";")[0],
												v -> v.split(";")[1]
											));
		
		var option = -1;
		
		while(true) {
			System.out.println("Selecione uma das opções a seguir\r\n"
					+ "1 - Iniciar um novo Jogo\r\n"
					+ "2 - Colocar um novo número\r\n"
					+ "3 - Remover um número\r\n"
					+ "4 - Visualizar jogo atual\r\n"
					+ "5 - Verificar status do jogo\r\n"
					+ "6 - limpar jogo\r\n"
					+ "7 - Finalizar jogo\r\n"
					+ "8 - Sair");
			
			try {
	            option = scanner.nextInt();
	            
	            switch (option){
	                case 1 -> startGame(positions);
	                case 2 -> inputNumber();
	                case 3 -> removeNumber();
	                case 4 -> showCurrentGame();
	                case 5 -> showGameStatus();
	                case 6 -> clearGame();
	                case 7 -> finishGame();
	                case 8 -> System.exit(0);
	                default -> System.out.println("Opção inválida, selecione uma das opções do menu");
	            }
			}
			catch (InputMismatchException e) {
				System.out.println("Entrada inválida!");
	            scanner.nextLine();
			}
		}
	}


	private static void startGame(final Map<String, String> positions) {
		if(Objects.nonNull(board)) {
			System.out.println("O jogo já foi iniciado");
			return;
		}
		
		List<List<Space>> spaces = new ArrayList<>();
		for (int i = 0; i < BOARD_LIMIT; i++) {
			spaces.add(new ArrayList<>());
			for(int j = 0; j < BOARD_LIMIT; j++) {
				var positionConfig = positions.get("%s,%s".formatted(i,j));
				
				var expected = Integer.parseInt(positionConfig.split(",")[0]);
				var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
				
				var currentSpace = new Space(expected, fixed);
				spaces.get(i).add(currentSpace);
			}
		}
		
		board = new Board(spaces);
		System.out.println("Jogo iniciado !!");
	}

	private static void inputNumber() {
		if(Objects.isNull(board)) {
			System.out.println("O jogo não foi iniciado");
			return;
		}
		
		System.out.println("Informe a coluna que deseja inserir o número");
		var col = runUntilGetValidNumber(0, 8);
		System.out.println("Informe a linha que deseja inserir o número");
		var row = runUntilGetValidNumber(0, 8);
		System.out.printf("Informe o número que vai entrar na posição [%s,%s]\n", col, row);
		var value = runUntilGetValidNumber(1, 9);
		if (!board.changeValue(col, row, value)){
			System.out.printf("A posição [%s,%s] tem um valor fixo\n", col, row);
		}
	        
	}
	
	 private static void removeNumber() {
		 if (Objects.isNull(board)){
			 System.out.println("O jogo ainda não foi iniciado iniciado");
			 return;
		 }
		 
		 System.out.println("Informe a coluna que em que o número será removido");
		 var col = runUntilGetValidNumber(0, 8);
		 System.out.println("Informe a linha que em que o número será removido");
		 var row = runUntilGetValidNumber(0, 8);
		 if (!board.clearValue(col, row)){
			 System.out.printf("A posição [%s,%s] tem um valor fixo\n", col, row);
		 }
	 }

	 private static void showCurrentGame() {
		 if (Objects.isNull(board)){
			 System.out.println("O jogo ainda não foi iniciado iniciado");
			 return;
		 }
		 
		 var args = new Object[81];
		 var argPos = 0;
		 
		 for (int i = 0; i < BOARD_LIMIT; i++) {
			for(var col: board.getSpaces()) {
				args[argPos++] = " " + ((Objects.isNull(col.get(i).getActual())) ? " " : col.get(i).getActual());
			}
		}
		 
		 System.out.printf("Seu jogo:"
					 		+ "\r\n"
					 		+ (BordTemplate.BOARD_TEMPLATE) + "%n", args);
	 }

	 private static void showGameStatus() {
		 if (Objects.isNull(board)){
			 System.out.println("O jogo ainda não foi iniciado iniciado");
			 return;
		 }
		 
		 System.out.printf("O jogo se encontra %S\n",board.getStatus().getLabel());
		 
		 if(board.hasError()) {
			 System.out.println("O jogo contém erros");
		 }else {
			 System.out.println("O jogo não contém erros");
		 }
	 }

	 private static void clearGame() {
		 if (Objects.isNull(board)){
			 System.out.println("O jogo ainda não foi iniciado iniciado");
			 return;
		 }
		 
		 System.out.println("Deseja limpar e reiniciar o jogo?\r\n1 - Sim\r\n2 - Não");
		 
		 var confirm = scanner.next();
		 while(!confirm.equalsIgnoreCase("1") || !confirm.equalsIgnoreCase("2")){
			 System.out.println("Informe 1 para Sim e 2 para Não");
		 }
		 
		 board.reset();
		 
	 }
	 
	private static void finishGame() {
		 if (Objects.isNull(board)){
			 System.out.println("O jogo ainda não foi iniciado iniciado");
			 return;
		 }
		 
		 if(board.gameIsFinished()) {
			 System.out.println("Parabéns!!! Você concluiu o jogo");
			 showCurrentGame();
			 board = null;
		 }else if(board.hasError()) {
			 System.out.println("Seu jogo contém erros, verifique seu board");
		 }else {
			 System.out.println("É necessário preencher todos os espaços em branco!");
		 }
	}

	
	  private static int runUntilGetValidNumber(final int min, final int max){
		  while (true) {
		        try {
		            System.out.printf("Informe um número entre %s e %s: ", min, max);
		            int current = scanner.nextInt();
		            
		            if (current >= min && current <= max) {
		                return current;
		            }
		            
		            System.out.printf("Número fora do intervalo! Informe um número entre %s e %s.\n", min, max);
		            
		        } catch (InputMismatchException e) {
		            System.out.printf("Entrada inválida! Por favor, insira um número entre %s e %s.\n", min, max);
		            scanner.nextLine();
		        }
		    }
	  }

}
