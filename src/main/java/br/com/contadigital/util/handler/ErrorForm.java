package br.com.contadigital.util.handler;

public class ErrorForm {

    private String campo;
    private String erro;

    public ErrorForm(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }

}
