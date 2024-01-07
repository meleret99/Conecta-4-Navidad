import java.util.Scanner;

public class Conecta4DefinitivoMelero {

	// Se definen constantes con el final, ya que no van a cambiar a lo largo del
	// juego

	public static final int FILAS = 6;
	public static final int COLUMNAS = 7;
	public static final char VACIO = ' ';
	public static final char JUGADOR1 = 'X';
	public static final char JUGADOR2 = 'O';

	// Se declara una matriz para el tablero
	public char[][] tablero;

	public Conecta4DefinitivoMelero() {
		tablero = new char[FILAS][COLUMNAS];
		inicializarTablero();
	}

	public void inicializarTablero() {
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				tablero[i][j] = VACIO;
			}
		}
	}

//Recorremos el tablero y lo mostramos
	public void mostrarTablero() {
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				System.out.print("| " + tablero[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.println("  0   1   2   3   4   5   6");
	}

	public boolean realizarMovimiento(int columna, char jugador) {
		for (int i = FILAS - 1; i >= 0; i--) {
			if (tablero[i][columna] == VACIO) {
				tablero[i][columna] = jugador;
				return true;
			}
		}
		return false;
	}

	// Recorremos en todas las direcciones
	public boolean hayGanador(char jugador) {
		// Verificamos horizontal (Se usa el 3 porque se necesitan al menos 4 columnas
		// para encontrar un patron de 4 fichas)
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS - 3; j++) {
				if (tablero[i][j] == jugador && tablero[i][j + 1] == jugador && tablero[i][j + 2] == jugador
						&& tablero[i][j + 3] == jugador) {
					return true;
				}
			}
		}

		// Verificamos vertical
		for (int i = 0; i < FILAS - 3; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				if (tablero[i][j] == jugador && tablero[i + 1][j] == jugador && tablero[i + 2][j] == jugador
						&& tablero[i + 3][j] == jugador) {
					return true;
				}
			}
		}

		// Verificamos diagonal arriba
		for (int i = 3; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS - 3; j++) {
				if (tablero[i][j] == jugador && tablero[i - 1][j + 1] == jugador && tablero[i - 2][j + 2] == jugador
						&& tablero[i - 3][j + 3] == jugador) {
					return true;
				}
			}
		}

		// Verificamos diagonal abajo
		for (int i = 0; i < FILAS - 3; i++) {
			for (int j = 0; j < COLUMNAS - 3; j++) {
				if (tablero[i][j] == jugador && tablero[i + 1][j + 1] == jugador && tablero[i + 2][j + 2] == jugador
						&& tablero[i + 3][j + 3] == jugador) {
					return true;
				}
			}
		}

		return false;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MeleroConecta4 juego = new MeleroConecta4();
		char jugadorActual = JUGADOR1;

		while (true) {
			juego.mostrarTablero();
			System.out.println("Turno del Jugador " + jugadorActual);
			System.out.print("Ingrese la columna (0-6): ");
			int columna = scanner.nextInt();

			if (columna >= 0 && columna < COLUMNAS) {
				if (juego.realizarMovimiento(columna, jugadorActual)) {
					if (juego.hayGanador(jugadorActual)) {
						juego.mostrarTablero();
						System.out.println("¡El jugador " + jugadorActual + " ha ganado!");
						break;
					}
//Se va cambiando el jugador para que en cada turno la tirada sea o una X o un O
					if (jugadorActual == JUGADOR1) {
						jugadorActual = JUGADOR2;
					} else {
						jugadorActual = JUGADOR1;
					}
				} else {
					System.out.println("Columna llena. Intentalo otra vez.");
				}
			} else {
				System.out.println("Columna inválida. Intentalo otra vez.");
			}
		}

	}
}
