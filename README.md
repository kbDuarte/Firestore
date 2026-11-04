# Cadastro e Listagem de Produtos

Aplicativo Android desenvolvido em Kotlin para cadastrar produtos e consultar a lista salva localmente no aparelho.

## Funcionalidades

- Cadastro de produtos com **Nome**, **Categoria** e **Preço**.
- Identificador numérico gerado automaticamente pelo Room.
- Listagem de todos os produtos cadastrados.
- Atualização automática da lista quando um produto é inserido.
- Navegação entre as telas de cadastro e listagem.
- Validação dos campos e feedback de carregamento/erro.
- Valores exibidos em Real (R$).

## Banco de dados

O aplicativo utiliza **Room**, biblioteca de persistência local baseada em SQLite. Os dados ficam armazenados no arquivo `produtos.db` dentro do armazenamento privado do aplicativo.

Não é necessário criar projeto no Firebase, baixar `google-services.json`, configurar credenciais ou ter conexão com a internet para cadastrar e consultar produtos.

A tabela `produtos` possui os campos:

| Campo | Tipo |
|---|---|
| `id` | Inteiro, chave primária com geração automática |
| `nome` | Texto |
| `categoria` | Texto |
| `preco` | Número decimal |

## Arquitetura

- **MVVM:** separação entre interface e lógica de apresentação.
- **Repository:** centraliza o acesso aos dados.
- **Room + DAO:** persistência e consultas ao banco local.
- **Kotlin Flow / StateFlow:** atualização reativa da interface.
- **Jetpack Compose + Material 3:** construção das telas.

## Como executar

1. Abra o repositório no Android Studio.
2. Aguarde a sincronização do Gradle.
3. Execute o app em um emulador ou dispositivo Android (API 24 ou superior).

Os produtos permanecem salvos ao fechar e abrir o aplicativo novamente. Desinstalar o aplicativo ou limpar os dados dele remove o banco local.
