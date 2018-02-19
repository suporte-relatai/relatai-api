package br.relatai.tcc.services.exceptions;

public class CategoriaNaoEncontradaException extends RuntimeException{

	private static final long serialVersionUID = 926208898182835229L;

	public CategoriaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CategoriaNaoEncontradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
