package model;

public class Square {

	public static final int MINA = 100;

	public static final int LIBRE = 50;

	private int tipo;

	private boolean seleccionada;

	private int valor;

	public Square(int tipo) {
		this.tipo = tipo;
		seleccionada = false;
		valor = -1;
	}

	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------

	/**
	 * Metodo modificar del atributo valor
	 * 
	 * @param valor - nuevo valor
	 */
	public void modificarValor(int valor) {
		this.valor = valor;
	}

	/**
	 * Retorna true si una casilla es de tipo Mina, false en caso contrario
	 * 
	 * @return
	 */
	public boolean esMina() {
		return tipo == MINA? true:false;
	}

	/**
	 * Genera un String que representa el valor que se debe mostrar de la casilla
	 * 
	 * @return El String con la representación actual de la casilla
	 */
	public String mostrarValorCasilla() {
		String valor = "";

		if (!seleccionada) {
			valor = "-";
		} else if (esMina()) {
			valor = "*";
		} else {
			valor = this.valor + "";
		}

		return valor;
	}

	/**
	 * Marca la casilla como que ya fue selecciona
	 */
	public void destapar() {
		seleccionada = true;
	}

	/**
	 * Metodo dar del atributo seleccionda
	 * 
	 * @return el atributo
	 */
	public boolean darSeleccionada() {
		return seleccionada;
	}

	/**
	 * Metodo dar del atributo valor
	 * 
	 * @return el valor
	 */
	public int darValor() {
		return valor;
	}

}
