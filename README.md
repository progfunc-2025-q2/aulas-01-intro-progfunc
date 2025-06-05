# Aula: Introdução à programação funcional

Este repositório contém exemplos de programação funcional e concorrente em Java, incluindo demonstrações de condições de corrida, sincronização, funções de primeira classe e funções de ordem superior.

## Conteúdo do Projeto

- **ConcurrentSumDemo.java**: Demonstra uma condição de corrida ao somar números de 1 a 10.000 usando duas threads sem sincronização.
- **ConcurrentSumImperativeSolution.java**: Solução imperativa e thread-safe para o problema acima, utilizando sincronização com `synchronized`.
- **ConcurrentSumDeclarativeDemo.java**: Solução declarativa e thread-safe usando streams e `FutureTask` para somar números em paralelo.
- **FirstClassFunctions.java**: Demonstra funções de primeira classe e interfaces funcionais customizadas em Java.
- **HigherOrderFunctions.java**: Exemplos de funções de ordem superior, como `map` e geração dinâmica de operações aritméticas.

## Como Executar

1. Compile todos os arquivos Java, direcionando os arquivos `.class` para um diretório de saída (por exemplo, `bin`):
   ```bash
   javac -d bin src/*.java
   ```
2. Execute o exemplo desejado, por exemplo:
   ```bash
   java -cp bin ConcurrentSumDemo
   ```
3. Alternativamente, utilize o modo de execução presentes no VS Code ou outra IDE de sua preferência.

## Requisitos
- Java 11 ou superior

## Dev Containers e Codespaces

Este projeto está preparado para ser utilizado em ambientes de desenvolvimento baseados em containers, como o [GitHub Codespaces](https://github.com/features/codespaces) e o [Visual Studio Code Dev Containers](https://code.visualstudio.com/docs/devcontainers/containers).

- Basta abrir o repositório no Codespaces ou em um ambiente com suporte a Dev Containers para ter uma configuração pronta para desenvolvimento em Java.
- O ambiente já inclui o Java, extensões recomendadas e configurações para facilitar a execução e depuração dos exemplos.
- Para usar no Codespaces, abra a página do repositório no GitHub e clique em "Code" > "Codespaces" > "New codespace".
- Para usar no VS Code com Dev Containers, instale a extensão "Dev Containers" e abra o repositório em um container de desenvolvimento. Assume-se que você já tenha o Docker instalado e configurado em sua máquina local.

Assim, você pode começar a programar imediatamente, sem necessidade de configurar o ambiente manualmente.