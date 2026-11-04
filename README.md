# Cadastro e Listagem de Produtos — Cloud Firestore

Aplicativo Android desenvolvido em Kotlin para cadastrar produtos e consultar em tempo real os documentos da coleção `Produtos` no Cloud Firestore.


## Executar

Depois de colocar o arquivo de configuração e criar/configurar o Firestore, abra o projeto no Android Studio, sincronize o Gradle e execute em um dispositivo/emulador com internet.

## Arquitetura

- **UI:** Jetpack Compose + Material 3.
- **ViewModel:** estado da tela com StateFlow.
- **Repository:** operações de leitura e escrita no Firestore.
- **Flow:** recebe atualizações em tempo real do Snapshot Listener.
