# Adapter Pattern - Exemplo com OldPrinter

## 📌 Visão Geral
O padrão de projeto **Adapter** permite que classes com interfaces incompatíveis trabalhem juntas sem modificar seu código original. Neste exemplo, temos uma classe `OldPrinter` que imprime mensagens em letras maiúsculas, mas nosso sistema espera um comportamento diferente. Para integrar `OldPrinter` sem alterá-la, criamos um **Adapter**.

---

## 🔍 Problema
Temos duas classes:
1. **`OldPrinter`** (código legado) - Possui um método `printUpper(String message)`, que imprime mensagens em letras maiúsculas.
2. **`Printer`** (interface do novo sistema) - Define o método `print(String message)`, que imprime mensagens sem modificações.

O problema é que `OldPrinter` não implementa `Printer`, tornando-as incompatíveis.

---

## ✅ Solução
Criamos a classe **`PrinterAdapter`**, que **implementa `Printer`** e internamente chama `printUpper()` da `OldPrinter`. Assim, nosso sistema pode usar `OldPrinter` sem precisar alterá-la.

### **Código Completo**
```java
// Classe legada que imprime em maiúsculas
class OldPrinter {
    public void printUpper(String message) {
        System.out.println(message.toUpperCase());
    }
}

// Interface esperada pelo novo sistema
interface Printer {
    void print(String message);
}

// Adapter que adapta OldPrinter para Printer
class PrinterAdapter implements Printer {
    private final OldPrinter oldPrinter;

    public PrinterAdapter(OldPrinter oldPrinter) {
        this.oldPrinter = oldPrinter;
    }

    @Override
    public void print(String message) {
        oldPrinter.printUpper(message);
    }
}

// Classe principal para testar
public class NewAdapter {
    public static void main(String[] args) {
        OldPrinter oldPrinter = new OldPrinter();
        Printer adapter = new PrinterAdapter(oldPrinter);

        adapter.print("Hello, Adapter Pattern!");
    }
}
```

---

## 🎯 Como Funciona?
1. **Criamos a interface `Printer`**, que define um método `print(String message)`.
2. **Criamos a classe `PrinterAdapter`**, que implementa `Printer` e encapsula `OldPrinter`.
3. **No método `print()` de `PrinterAdapter`**, chamamos `printUpper()` da `OldPrinter`.
4. **No `main()`**, usamos `PrinterAdapter` para adaptar `OldPrinter` ao novo sistema.

---

## 📌 Benefícios
✅ **Desacoplamento** - O sistema não depende diretamente de `OldPrinter`.
✅ **Facilidade de Manutenção** - Podemos trocar `OldPrinter` sem afetar o código do sistema.
✅ **Reutilização** - Podemos reutilizar `OldPrinter` sem modificar seu código original.

---

## 🔥 Conclusão
O padrão **Adapter** é útil para integrar códigos legados a novos sistemas. Ele cria uma ponte entre interfaces incompatíveis, garantindo flexibilidade e manutenção eficiente.

🚀 **Agora, nosso sistema pode usar `OldPrinter` sem modificar seu código original!**

