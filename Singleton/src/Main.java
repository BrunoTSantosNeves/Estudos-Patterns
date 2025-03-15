// Singleton
// Design Patterns: Singleton
// Versão 1.0

   /* class Singleton {
    private static Singleton instance;

    // Construtor privado para evitar instancias fora da classe
    private Singleton(){}

    // Método para retornar a instância única
    public static Singleton getInstance(){
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }

    public void showMessage(){
        System.out.println("Singleton versão 1.0: Instancia única criada com sucesso!");
    }
}

public class Main {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        singleton1.showMessage();
        singleton2.showMessage();

        System.out.println("singleton1 == singleton2?" + (singleton1 == singleton2));
    }
}
    */

// Singleton
// Design Patterns: Singleton
// Versão 1.1

/*class Singleton {
    private static Singleton instance;

    // Construtor privado para evitar instancias externas
    private Singleton(){}

    // Método sincronizado para garantir a segurança em multiplas threads
    public static synchronized Singleton getInstance(){
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void showMessage(){
        System.out.println("Singleton com Thread Safety: Instância única criada!");
    }

    public class Main {
        public static void main(String[] args){
            Singleton singleton1 = Singleton.getInstance();
            Singleton singleton2 = Singleton.getInstance();

            singleton1.showMessage();
            singleton2.showMessage();
        }
    }
}
*/

// Singleton
// Design Patterns: Singleton
// Versão 1.2


/*
class Singleton {
    private static Singleton instance;

    // Construtor privado para evitar instâncias externas
    private Singleton(){}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized(Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void showMessage(){
        System.out.println("Singleton com Thread Safety: Instância única criada!");
    }
}

// Classe principal para teste

public class Main {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        singleton1.showMessage();

        // Verificando se as instâncias são as mesmas
        System.out.println("singleton1 == singleton2? " + (singleton1 == singleton2));
    }
}
*/

// Singleton
// Design Patterns: Singleton
// Versão 1.3

/*
class Singleton {

    // Instância criada no carregamento da classe (Eager Initialization)

    private static final Singleton instance = new Singleton();

    // Construtor privado para evitar instâncias externas
    private Singleton(){}

    public static Singleton getInstance(){
        return instance;        
    }

    public void showMessage(){
        System.out.println("Singleton com Eager Initialization: Instância única criada!");
    }

    public class Main {
        public static void main(String[] args) {
            Singleton singleton1 = Singleton.getInstance();
            Singleton singleton2 = Singleton.getInstance();

            singleton1.showMessage();
            singleton2.showMessage();

            System.out.println("singleton1 == singleton2? " + (singleton1 == singleton2));
        }
    }
}
*/

// Singleton
// Design Patterns: Singleton
// Versão 1.4

/*
class Singleton {
    // Classe estática interna para manter a instância única
    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }

    // Construtor privado para evitar instâncias externas
    private Singleton() {}

    // Método público que retorna a instância única
    public static Singleton getInstance() {
        return Holder.INSTANCE; // Somente inicializa quando chamado pela primeira vez
    }

    public void showMessage() {
        System.out.println("Singleton com Holder: Eficiência e segurança garantidas!");
    }
}

// Classe principal para teste
public class Main {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        singleton1.showMessage();

        // Verificando se as instâncias são as mesmas
        System.out.println("singleton1 == singleton2? " + (singleton1 == singleton2));
    }
}
*/

// Singleton
// Design Patterns: Singleton
// Versão 1.5

/*
enum Singleton {
    INSTANCE; // Instância única gerenciada pela JVM

    public void showMessage() {
        System.out.println("Singleton com Enum: A solução mais segura e recomendada!");
    }
}

// Classe principal para teste
public class Main {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.INSTANCE;
        Singleton singleton2 = Singleton.INSTANCE;

        singleton1.showMessage();

        // Verificando se as instâncias são as mesmas
        System.out.println("singleton1 == singleton2? " + (singleton1 == singleton2));
    }
}
*/

// Singleton
// Design Patterns: Singleton
// Versão 1.6

class Singleton {
    private static volatile Singleton instance; // Volatile para evitar problemas de visibilidade entre threads
    private final String config; // Parâmetro personalizado

    // Construtor privado para impedir instâncias externas
    private Singleton(String config) {
        this.config = config;
    }

    // Método para obter a instância única com suporte a parâmetros
    public static Singleton getInstance(String config) {
        if (instance == null) { // Primeira verificação sem bloqueio
            synchronized (Singleton.class) {
                if (instance == null) { // Segunda verificação dentro do bloco sincronizado
                    instance = new Singleton(config);
                }
            }
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Singleton definitivo funcionando! Config: " + config);
    }
}


public class Main {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance("Config A");
        Singleton singleton2 = Singleton.getInstance("Config B");

        singleton1.showMessage();
        singleton2.showMessage();

        // Verificando se as instâncias são as mesmas
        System.out.println("singleton1 == singleton2? " + (singleton1 == singleton2));
    }
}