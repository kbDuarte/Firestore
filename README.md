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

Coleção: `Produtos`

Cada documento contém:

| Campo | Tipo |
|---|---|
| `Nome` | String |
| `Categoria` | String |
| `Preço` | Number (Double) |

O ID do documento é gerado automaticamente pelo Firestore.

## Configuração obrigatória do Firebase

1. Abra o projeto Firebase já existente no Firebase Console.
2. Em **Configurações do projeto > Seus apps**, adicione um aplicativo Android com o package name exato:
   `br.com.kduarte.produtosfirestore`.
3. Baixe o arquivo `google-services.json` desse novo registro Android.
4. Coloque o arquivo em `app/google-services.json` (dentro da pasta `app`, ao lado do `build.gradle.kts`).
5. No Firebase Console, abra **Firestore Database** e crie o banco de dados.
6. Configure as regras de acesso adequadas ao seu projeto. Para uso real, não deixe regras abertas publicamente.

**Importante:** o arquivo de configuração é específico para o package name do aplicativo. O `google-services.json` do app anterior `com.kduarte.authfirebase` não serve diretamente para este app, pois o package name é diferente. Não compartilhe nem publique esse arquivo em repositório público.

## Executar

Depois de adicionar o `google-services.json` e configurar o Firestore, abra o projeto no Android Studio, sincronize o Gradle e execute em um dispositivo/emulador com internet.

## Arquitetura

- **UI:** Jetpack Compose + Material 3.
- **ViewModel:** estado da tela com StateFlow.
- **Repository:** operações de leitura e escrita no Firestore.
- **Flow:** recebe atualizações em tempo real do Snapshot Listener.
