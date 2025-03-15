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