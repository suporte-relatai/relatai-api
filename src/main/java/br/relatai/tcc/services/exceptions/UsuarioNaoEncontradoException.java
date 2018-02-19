package br.relatai.tcc.services.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException{
	
	private static final long serialVersionUID = 4026925946530355077L;

	public UsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public UsuarioNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
