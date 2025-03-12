# Adapter Pattern 🛠️

## 📌 Visão Geral
O **Adapter Pattern** é um padrão estrutural que permite que classes com interfaces incompatíveis trabalhem juntas, agindo como um "tradutor" entre elas.

Neste projeto, o Adapter foi utilizado para converter dados retornados em **XML** para **JSON**, permitindo que um sistema legado se comunique com uma nova interface.

---

## 🏗️ Estrutura do Código

### 🔹 Versão 1.0: Implementação Simples
A primeira versão era mais direta e resolvia o problema de conversão, mas de forma menos flexível. 
O `Adaptador` apenas pegava os dados do `SistemaAntigo` e os retornava sem um conversor separado.

```java
// Classe Legada (Retorna XML)
class SistemaAntigo {
    public String getXMLData() {
        return "<data>Old System Data</data>";
    }
}

// Interface esperada pelo novo sistema (Retorna JSON)
interface JsonDataProvider {
    Object getData();
}

// Adapter
class Adaptador implements JsonDataProvider {
    private final SistemaAntigo sistemaAntigo;
    
    public Adaptador(SistemaAntigo sistemaAntigo) {
        this.sistemaAntigo = sistemaAntigo;
    }

    @Override
    public Object getData() {
        return "{\"name\": \"Old System Data (converted)\" }"; // Conversão fixa
    }
}
```

🔹 **Problemas dessa Versão:**
- A conversão XML → JSON está embutida diretamente no Adapter, tornando-o menos reutilizável.
- Se precisarmos converter outros formatos no futuro, teríamos que modificar o Adapter diretamente.

---

### 🔹 Versão 2.0: Separando a Conversão
Nesta versão, criamos uma classe `ToJsonConverter` para lidar com a conversão XML → JSON, deixando o `Adaptador` responsável apenas por delegar a conversão.

```java
// Conversor de XML para JSON
class ToJsonConverter {
    public static String convert(String xmlData) {
        return "{\"name\": \"Old System Data (converted)\" }";
    }
}

// Adapter
class Adaptador implements JsonDataProvider {
    private final SistemaAntigo sistemaAntigo;
    
    public Adaptador(SistemaAntigo sistemaAntigo) {
        this.sistemaAntigo = sistemaAntigo;
    }

    @Override
    public Object getData() {
        String xmlData = sistemaAntigo.getXMLData();
        return ToJsonConverter.convert(xmlData);
    }
}
```

🔹 **Melhorias dessa Versão:**
- O Adapter agora apenas chama um conversor especializado.
- Se precisarmos mudar a forma de conversão, alteramos apenas a classe `ToJsonConverter`.
- **Melhor separação de responsabilidades (SRP - SOLID).**

---

### 🔹 Versão 2.1: Adicionando uma Interface para Conversores
Nesta versão, melhoramos ainda mais a flexibilidade ao criar uma interface `DataConverter`, permitindo que **novos conversores** sejam adicionados facilmente no futuro.

```java
// Interface genérica para conversão de dados
interface DataConverter {
    String convert(String data);
}

// Implementação do conversor XML -> JSON
class ToJsonConverter implements DataConverter {
    @Override
    public String convert(String xmlData) {
        return "{\"name\": \"Old System Data (converted)\" }";
    }
}

// Adapter atualizado
class Adaptador implements JsonDataProvider {
    private final SistemaAntigo sistemaAntigo;
    private final DataConverter converter;
    
    public Adaptador(SistemaAntigo sistemaAntigo, DataConverter converter) {
        this.sistemaAntigo = sistemaAntigo;
        this.converter = converter;
    }

    @Override
    public Object getData() {
        String xmlData = sistemaAntigo.getXMLData();
        return converter.convert(xmlData);
    }
}
```

🔹 **Melhorias dessa Versão:**
- Agora temos uma interface **`DataConverter`**, que pode ser implementada para suportar outras conversões (ex: **CSV → JSON** ou **YAML → JSON**).
- O Adapter recebe um conversor **dinamicamente**, tornando-o mais reutilizável.
- O próprio Adapter **não sabe como a conversão ocorre**, ele apenas delega.

---

## 🎯 Benefícios do Adapter Pattern

✅ Permite a integração de **sistemas legados** com novos sistemas.  
✅ Segue o princípio da **responsabilidade única (SRP - SOLID)**.  
✅ **Desacopla** a conversão de dados da lógica principal do sistema.  
✅ **Extensível** → Se novos formatos forem necessários (ex: CSV, YAML), basta criar novos `DataConverter`.  

---

## 📌 Conclusão
O **Adapter Pattern** é uma solução poderosa para integrar sistemas que não foram projetados para trabalhar juntos. Ao separar a conversão da lógica do Adapter, garantimos um código mais **modular, reutilizável e fácil de manter**.
