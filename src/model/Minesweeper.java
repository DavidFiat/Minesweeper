package model;

public class Minesweeper {

	public static final int FILAS_PRINCIPIANTE = 8;
	public static final int FILAS_INTERMEDIO = 16;
	public static final int FILAS_EXPERTO = 16;
	public static final int COLUMNAS_PRINCIPIANTE = 8;
	public static final int COLUMNAS_INTERMEDIO = 16;
	public static final int COLUMNAS_EXPERTO = 30;

	public static final int PRINCIPIANTE = 1;

	public static final int INTERMEDIO = 2;

	public static final int EXPERTO = 3;

	public static final int CANTIDAD_MINAS_PRINCIPIANTE = 10;

	public static final int CANTIDAD_MINAS_INTERMEDIO = 40;

	public static final int CANTIDAD_MINAS_EXPERTO = 99;

	// -----------------------------------------------------------------
	// Atributos y relaciones
	// -----------------------------------------------------------------

	/**
	 * Relacion que tiene la matriz de casillas
	 */
	private Square[][] squares;

	/**
	 * Atributo que representa el nivel del juego <Solo puede tomar valores
	 * PRINCIPIANTE, INTERMEDIO, EXPERTO>
	 */
	private int nivel;

	public int getNivel() {
		return nivel;
	}

	/**
	 * Atributo que tiene la cantidad de minas en el tablero
	 */
	private int cantidadMinas;

	public int getCantidadMinas() {
		return cantidadMinas;
	}

	public void setCantidadMinas(int cantidadMinas) {
		this.cantidadMinas = cantidadMinas;
	}

	/**
	 * Atributo que representa si el usuario perdio al abrir una mina
	 */
	private boolean perdio;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructo de la clase Buscaminas
	 * 
	 * @param nivel - el nivel seleccionado por el usuario
	 */
	public Minesweeper(int nivel) {
		this.nivel = nivel;
		perdio = false;
		inicializarPartida();

	}

	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------

	/**
	 * Se encarga de inicializar los atributos y relaciones de la clase buscaminas a
	 * partir del nivel elegido por el usuario
	 */
	private void inicializarPartida() {

		if (nivel == PRINCIPIANTE) {
			squares = new Square[FILAS_PRINCIPIANTE][COLUMNAS_PRINCIPIANTE];
			setCantidadMinas(CANTIDAD_MINAS_PRINCIPIANTE);

		} else if (nivel == INTERMEDIO) {
			squares = new Square[FILAS_INTERMEDIO][COLUMNAS_INTERMEDIO];
			setCantidadMinas(CANTIDAD_MINAS_INTERMEDIO);

		} else {
			squares = new Square[FILAS_EXPERTO][COLUMNAS_EXPERTO];
			setCantidadMinas(CANTIDAD_MINAS_EXPERTO);

		}

		generarMinas();
		inicializarCasillasLibres();
	}

	/**
	 * Metodo que se encarga de inicializar todas las casillas que no son minas
	 */
	public void inicializarCasillasLibres() {
		for (int i = 0; i < squares.length; i++) {
			for (int i2 = 0; i2 < squares[i].length; i2++) {
				if (squares[i][i2] == null) {
					squares[i][i2] = new Square(Square.LIBRE);
					squares[i][i2].modificarValor(cantidadMinasAlrededor(i, i2));
				}
			}
		}
		for (int i3 = 0; i3 < squares.length; i3++) {
			for (int i4 = 0; i4 < squares[0].length; i4++) {
				squares[i3][i4].modificarValor(cantidadMinasAlrededor(i3, i4));
				if (!squares[i3][i4].esMina()) {
					squares[i3][i4].modificarValor(cantidadMinasAlrededor(i3, i4));
				}
			}
		}
	}

	/**
	 * Metodo que permite contar la cantidad de minas que tiene alrededor una
	 * casillas
	 * 
	 * @param i - La fila de la matriz
	 * @param j - la columna de la matriz
	 * @return int - La cantidad de minas que tiene alrededor la casilla [i][j]
	 */
	public int cantidadMinasAlrededor(int a, int b) {

		int counter = 0;
		int v = 0;
		int h = 0;

		for (int i = a - 1; v < 3 && i < squares.length; i++) {
			for (int i2 = b - 1; h < 3; i2++) {
				if ((i >= 0 && i2 >= 0) && (i2 < squares[i].length)) {
					if (squares[i][i2] != null) {
						if (squares[i][i2].esMina()) {
							counter++;
						}
					}
				}
				h++;
			}
			v++;
			h = 0;
		}
		return counter;
	}

	/**
	 * Método que se encarga de generar aleatoriomente las minas
	 */
	public void generarMinas() {

		int i = squares.length;
		int i2 = squares[0].length;

		int counter = 0;
		while (counter < cantidadMinas) {
			int i3 = (int) (Math.random() * i);
			int i4 = (int) (Math.random() * i2);
			if (squares[i3][i4] == null) {
				squares[i3][i4] = new Square(Square.MINA);

			}
			counter++;
		}

	}

	/**
	 * Metodo que se encarga de marcar todas las casillas como destapadas
	 */
	public void resolver() {
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[0].length; j++) {
				squares[i][j].destapar();
			}
		}
	}

	/**
	 * Metodo dar del atributo casillas
	 * 
	 * @return la relacion casillas
	 */
	public Square[][] getSquares() {
		return squares;
	}

	/**
	 * Este metodo se encargaa de abrir una casilla Si se abre una casilla de tipo
	 * Mina, se marca que el jugador perdio el juego.
	 * 
	 * @param i - la fila donde esta la casilla
	 * @param j - la columna donde esta la casilla
	 * @return boolean - true si fue posible destaparla, false en caso contrario
	 */
	public boolean abrirCasilla(int a, int b) {
		boolean abrir = false;
		if (!squares[a][b].darSeleccionada()) {
			squares[a][b].destapar();
			if (squares[a][b].esMina()) {
				perdio = true;
			}
			abrir = true;
		}
		return abrir;
	}

	/**
	 * Metodo que se encarga de revisar si el jugador gano el juego
	 * 
	 * @return boolean - true si gano el juego, false en caso contrario
	 */
	public boolean gano() {
		boolean gano = true;
		for (int i = 0; i < squares.length; i++) {
			for (int i2 = 0; i2 < squares[i].length; i2++) {
				if (!squares[i][i2].esMina() && !squares[i][i2].darSeleccionada()) {
					gano = false;
				}
			}
		}
		return gano;
	}

	/**
	 * Metodo que se encarga de abrir la primera casilla que no sea una Mina y cuyo
	 * valor sea Mayor que 0
	 * 
	 * @return String, Mensaje de la Casilla que marco abierta, En caso de no haber
	 *         casillas posibles para dar una pista, retorna el mensaje no hay
	 *         pistas para dar
	 */

	public String darPista() {
		String m = "";
		boolean ya = false;
		for (int i = 0; i < squares.length && !ya; i++) {
			for (int i2 = 0; i2 < squares[0].length - 1 && !ya; i2++) {
				if (!squares[i][i2].darSeleccionada()) {
					if (!squares[i][i2].esMina()) {
						if (squares[i][i2].darValor() > 0) {
							squares[i][i2].destapar();
							ya = true;
							m = i + "-" + i2;
						}

					}
				}
			}
		}
		return m;

	}

	/*
	 * Metodo dar del atributo perdio
	 * 
	 * @return boolean el atributo
	 */
	public boolean darPerdio() {
		return perdio;
	}

	public boolean esMina(int i, int i2) {
		return squares[i][i2].esMina();
	}

	public boolean estaSeleccionada(int i, int i2) {
		return squares[i][i2].darSeleccionada();
	}
}
