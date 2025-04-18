public class Main {
    public static void main(String[] args) {
        
        ContratoConfidencial contratoBase = new ContratoConfidencial(
            "Contrato de Confidencialidade",
            "Este é o conteúdo padrão...",
            "Empresa XYZ"
        );

        
        ContratoConfidencial contratoJoao = (ContratoConfidencial) contratoBase.clone();
        contratoJoao.setConteudo("Contrato adaptado para João da Silva");
        contratoJoao.setAssinatura("João da Silva");

        
        ContratoConfidencial contratoMaria = (ContratoConfidencial) contratoBase.clone();
        contratoMaria.setConteudo("Contrato adaptado para Maria Oliveira");
        contratoMaria.setAssinatura("Maria Oliveira");

        
        contratoBase.exibirInformacoes();
        contratoJoao.exibirInformacoes();
        contratoMaria.exibirInformacoes();
    }
}

