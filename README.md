# Encurtador de Link API (Serverless)

## 📌 Propósito
Este projeto é uma API de encurtamento de links (URL Shortener) construída utilizando a arquitetura Serverless. O objetivo principal é fornecer endpoints rápidos, seguros e altamente escaláveis para criar, gerenciar e redirecionar URLs curtas. A arquitetura tira proveito do ecossistema da AWS aliado à produtividade do ecossistema Spring Boot em Java.

## 🚀 Tecnologias Utilizadas
* **Linguagem:** Java 17
* **Framework:** Spring Boot 3
* **Serverless:** AWS Lambda, AWS SAM (Serverless Application Model)
* **Banco de Dados:** Amazon DynamoDB (AWS SDK v2 Enhanced Client)
* **Ambiente de Desenvolvimento Local:** LocalStack (simulação de serviços AWS) e Docker
* **Segurança:** Spring Security com autenticação JWT (chaves assimétricas RSA)
* **Build e Gerenciamento:** Maven

## ⚙️ Estrutura e Execução Local
O projeto contém scripts para facilitar o desenvolvimento local sem necessidade de implantação real na nuvem durante a construção das features:

- **Banco de Dados Local:** Utiliza o LocalStack para rodar o DynamoDB através do script em `app/local/start-local.sh`.
- **Execução da Lambda:** Utiliza o AWS SAM localmente simulando o API Gateway chamando a função Lambda através do script `app/local/run_local.sh`.

