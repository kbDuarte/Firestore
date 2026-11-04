# Cadastro e Listagem de Produtos — Cloud Firestore

Aplicativo Android desenvolvido em Kotlin para cadastrar produtos e consultar em tempo real os documentos da coleção `Produtos` no Cloud Firestore.

## Funcionalidades

- Cadastro de produtos com os campos `Nome`, `Categoria` e `Preço`.
- Criação automática do ID de cada documento pelo Firestore.
- Listagem dos produtos da coleção `Produtos`.
- Atualização em tempo real usando Snapshot Listener.
- Navegação entre cadastro e listagem.
- Validação de campos, estado de carregamento e mensagens de erro.
- Arquitetura MVVM + Repository + Kotlin Flow.

## Estrutura no Firestore

Projeto Firebase: `authfirebasekot`

Coleção: `Produtos`

Cada documento contém:

| Campo | Tipo |
|---|---|
| `Nome` | String |
| `Categoria` | String |
| `Preço` | Number (Double) |

O ID do documento é gerado automaticamente pelo Firestore.

## Configuração do Firebase

O aplicativo Android já está registrado no projeto Firebase `authfirebasekot` com o package name:

`br.com.kduarte.produtosfirestore`

Para executar localmente:

1. Baixe o `google-services.json` correspondente a esse registro Android no Firebase Console.
2. Coloque-o em `app/google-services.json`, ao lado do `app/build.gradle.kts`.
3. No Firebase Console, abra **Firestore Database** e crie o banco de dados, caso ainda não exista.
4. Configure regras de acesso compatíveis com o modo de uso do aplicativo. Não deixe regras públicas abertas em produção.

**Segurança:** o arquivo `google-services.json` é ignorado pelo Git por meio do `.gitignore` e não deve ser enviado ao repositório público. O arquivo contém identificadores e configuração do projeto Firebase.

## Executar

Depois de colocar o arquivo de configuração e criar/configurar o Firestore, abra o projeto no Android Studio, sincronize o Gradle e execute em um dispositivo/emulador com internet.

## Arquitetura

- **UI:** Jetpack Compose + Material 3.
- **ViewModel:** estado da tela com StateFlow.
- **Repository:** operações de leitura e escrita no Firestore.
- **Flow:** recebe atualizações em tempo real do Snapshot Listener.
