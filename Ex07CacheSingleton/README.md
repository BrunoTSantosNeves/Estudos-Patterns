# Ex07CacheSingleton

## Descrição
Este projeto implementa um sistema de cache híbrido utilizando **Arquitetura Hexagonal**, com uma **Fake API** em **Node.js** e um backend em **Java**. O objetivo é armazenar dados temporariamente para otimizar o desempenho, evitando consultas desnecessárias ao banco de dados MySQL.

A implementação segue as boas práticas de desenvolvimento, incluindo o uso do padrão Singleton para gestão de cache e conexão com o banco de dados, além de separação de responsabilidades conforme a Arquitetura Hexagonal.

## Estrutura do Projeto

A estrutura do projeto está dividida da seguinte forma:

### 1. **Fake API (Node.js - TypeScript)**
Localiza-se dentro do diretório `src/fake-api`. Esta API simula um backend que retorna dados JSON para a aplicação Java.

#### **Arquivos e diretórios principais:**
- `server.ts` - Inicializa o servidor e define rotas.
- `routes/` - Contém as definições das rotas da Fake API.
- `data/db.json` - Arquivo JSON que armazena dados fictícios.
- `infrastructure/JsonDatabase.ts` - Gerencia a persistência dos dados na Fake API.
- `test/seed.ts` - Popula o `db.json` com dados iniciais.
- `dist/` - Contém a versão transpilada do TypeScript para JavaScript.

#### **Comandos para executar a Fake API:**
```sh
# Instalar dependências
npm install

# Transpilar TypeScript para JavaScript
npm run build

# Rodar a Fake API
npm start
```

---
### 2. **Backend (Java)**
O backend principal do projeto está localizado dentro do diretório `src/`.

#### **Camadas do Backend:**

##### **Core** (Regras de negócio e interfaces)
- `core/CacheRepository.java` - Interface para implementação do cache.
- `core/CacheService.java` - Implementa um cache Singleton utilizando `HashMap`.

##### **Infra** (Infraestrutura e persistência de dados)
- `infra/InMemoryCache.java` - Implementa um cache em memória.
- `infra/MySQLCache.java` - Implementa um cache persistente em MySQL.
- `infra/ManagerConnection.java` - Gerencia as conexões com o banco de dados, evitando vazamento de memória (Memory Leak).

##### **Adapter** (Integração com recursos externos)
- `adapter/database/DatabaseAdapter.java` - Adapta a comunicação com o banco de dados.

##### **Application** (Serviços principais da aplicação)
- `aplication/ApiService.java` - Consome a Fake API.
- `aplication/CacheManager.java` - Controla a lógica de busca de dados no cache e no banco.

##### **Transport** (Ponto de entrada da aplicação)
- `transport/Main.java` - Classe principal para execução do sistema.

#### **Fluxo de funcionamento:**
1. A aplicação busca um dado no `InMemoryCache`.
2. Se não encontrar, busca no `MySQLCache` e armazena no `InMemoryCache`.
3. Se ainda assim não encontrar, faz uma requisição à Fake API e armazena o dado no `MySQLCache` e no `InMemoryCache`.

#### **Comandos para rodar o Backend:**
```sh
# Compilar o projeto
javac -d out src/**/*.java

# Rodar a aplicação
java -cp out transport.Main
```

---
## Conclusão
Este projeto demonstrou a implementação de um sistema de cache eficiente, utilizando tanto um cache em memória quanto um cache persistente no banco de dados MySQL. Também foram aplicados conceitos avançados de Arquitetura Hexagonal, boas práticas de desenvolvimento e gestão de conexões para evitar problemas de vazamento de memória.

O projeto está pronto para evoluir, podendo ser expandido com novas funcionalidades, como autenticação, logs e testes automatizados.

