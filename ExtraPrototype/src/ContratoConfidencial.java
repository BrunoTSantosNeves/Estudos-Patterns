public class ContratoConfidencial implements Documento {
    private String titulo;
    private String conteudo;
    private String assinatura;

    public ContratoConfidencial(String titulo, String conteudo, String assinatura) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.assinatura = assinatura;
    }

    @Override
    public Documento clone() {
        try {
            return (Documento) super.clone(); // clonagem superficial (suficiente aqui)
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Erro ao clonar documento", e);
        }
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("📄 " + titulo);
        System.out.println("Conteúdo: " + conteudo);
        System.out.println("Assinatura: " + assinatura);
        System.out.println("--------------------------------------");
    }

    // Getters e Setters se quiser alterar os dados depois
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public void setAssinatura(String assinatura) {
        this.assinatura = assinatura;
    }
}
