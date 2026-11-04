# Produtos Firestore

Aplicativo Android em Kotlin + Jetpack Compose para cadastrar e listar produtos usando Cloud Firestore.

## Funcionalidades

- Cadastro de produtos com **Nome**, **Categoria** e **Preço**.
- Geração automática do ID do documento pelo Firestore.
- Leitura em tempo real da coleção `Produtos`.
- Navegação animada entre cadastro e listagem.
- Arquitetura MVVM, Repository e estado reativo com Kotlin Flow.
- Validação dos campos e feedback de carregamento/erro.
- Interface Material 3 adaptável a tema claro/escuro (a paleta inicial está configurada em tema claro).

## Configuração do Firebase

1. Crie ou abra um projeto no [Firebase Console](https://console.firebase.google.com/).
2. Adicione um aplicativo Android com o package name:
   `br.com.kduarte.produtosfirestore`
3. Baixe o arquivo `google-services.json` e coloque-o em `app/google-services.json`.
4. No Firebase Console, acesse **Firestore Database** e crie o banco de dados.
5. Configure as regras de acesso adequadas ao seu cenário. Para testes locais, use regras temporárias e restritas; não publique regras abertas em produção.
6. Abra o projeto no Android Studio e sincronize o Gradle.

## Estrutura do Firestore

Coleção: `Produtos`

Cada documento recebe ID automático e contém os campos:

| Campo | Tipo |
|---|---|
| `Nome` | String |
| `Categoria` | String |
| `Preço` | Number (Double) |

## Tecnologias

- Kotlin
- Jetpack Compose + Material 3
- Firebase Cloud Firestore
- Navigation Compose
- MVVM + Repository
- Kotlin Coroutines / Flow

> O arquivo `google-services.json` é específico do projeto Firebase e não é incluído no repositório. Adicione o arquivo obtido no console para executar o app conectado ao seu banco.
