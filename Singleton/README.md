# Singleton Pattern - Documentação

## Introdução
O Singleton é um padrão de projeto que garante que uma classe tenha apenas uma única instância durante todo o ciclo de vida da aplicação, fornecendo um ponto de acesso global a essa instância.

Este README documenta as diferentes versões do Singleton que implementamos, evoluindo progressivamente para uma solução definitiva e segura.

---

## Versão 1: Singleton Básico

```java
class SingletonV1 {
    private static SingletonV1 instance;
    
    private SingletonV1() {}
    
    public static SingletonV1 getInstance() {
        if (instance == null) {
            instance = new SingletonV1();
        }
        return instance;
    }
}
```

### Problemas:
❌ Não é thread-safe, pois múltiplas threads podem criar instâncias simultaneamente.

---

## Versão 2: Singleton com sincronização no método

```java
class SingletonV2 {
    private static SingletonV2 instance;
    
    private SingletonV2() {}
    
    public static synchronized SingletonV2 getInstance() {
        if (instance == null) {
            instance = new SingletonV2();
        }
        return instance;
    }
}
```

### Melhorias:
✅ Agora é thread-safe.

### Problemas:
❌ O uso de `synchronized` em todo o método impacta o desempenho desnecessariamente.

---

## Versão 3: Singleton com Double-Checked Locking

```java
class SingletonV3 {
    private static volatile SingletonV3 instance;
    
    private SingletonV3() {}
    
    public static SingletonV3 getInstance() {
        if (instance == null) {
            synchronized (SingletonV3.class) {
                if (instance == null) {
                    instance = new SingletonV3();
                }
            }
        }
        return instance;
    }
}
```

### Melhorias:
✅ Melhor desempenho, pois a sincronização ocorre apenas na primeira inicialização.

### Problemas:
❌ Uso de `volatile` pode impactar performance dependendo da JVM.

---

## Versão 4: Singleton utilizando classe estática interna

```java
class SingletonV4 {
    private SingletonV4() {}
    
    private static class Holder {
        private static final SingletonV4 INSTANCE = new SingletonV4();
    }
    
    public static SingletonV4 getInstance() {
        return Holder.INSTANCE;
    }
}
```

### Melhorias:
✅ Inicialização preguiçosa sem necessidade de `synchronized`.
✅ Melhor desempenho e segurança.

---

## Versão 5: Singleton usando Enum

```java
enum SingletonV5 {
    INSTANCE;
    
    public void someMethod() {
        System.out.println("Método do Singleton");
    }
}
```

### Melhorias:
✅ Simplicidade e segurança contra serialização.
✅ Proteção contra reflexão.

### Problemas:
❌ Enum não permite controle fino sobre a inicialização.

---

## Versão 6: Singleton com Inicialização Eager

```java
class SingletonV6 {
    private static final SingletonV6 INSTANCE = new SingletonV6();
    
    private SingletonV6() {}
    
    public static SingletonV6 getInstance() {
        return INSTANCE;
    }
}
```

### Melhorias:
✅ Simples e eficiente.

### Problemas:
❌ Instância criada mesmo se nunca for usada.

---

## Versão 7: Singleton Definitivo (Melhor Versão)

```java
class SingletonV7 {
    private static volatile SingletonV7 instance;
    
    private SingletonV7() {
        if (instance != null) {
            throw new RuntimeException("Use getInstance() para obter a instância!");
        }
    }
    
    public static SingletonV7 getInstance() {
        if (instance == null) {
            synchronized (SingletonV7.class) {
                if (instance == null) {
                    instance = new SingletonV7();
                }
            }
        }
        return instance;
    }
    
    protected Object readResolve() {
        return getInstance();
    }
}
```

### Melhorias:
✅ Proteção contra reflexão.
✅ Proteção contra serialização/deserialização.
✅ Inicialização eficiente e segura para múltiplas threads.

---

## Conclusão

Ao longo das versões, evoluímos de uma implementação básica para um Singleton robusto e seguro. O SingletonV7 é a versão definitiva, pois resolve todos os problemas das implementações anteriores, garantindo segurança, desempenho e flexibilidade.

Agora temos um Singleton pronto para uso em aplicações reais! 🚀

