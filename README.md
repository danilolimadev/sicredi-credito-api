# Sicredi Crédito API

## 📌 Descrição

API REST para contratação de crédito, com validação de regras de negócio e integração com serviço externo de produtos.

---

## 🚀 Tecnologias

* Java 21
* Spring Boot
* MySQL
* OpenFeign
* JUnit + Mockito

---

## ▶️ Como rodar

### 1. Criar banco:

```sql
CREATE DATABASE sicredi_credito;
```

### 2. Configurar `application.yml`

### 3. Rodar:

```bash
mvn spring-boot:run
```

---

## 📡 Endpoints

### POST /credito

Cria operação de crédito

### GET /credito/{id}

Consulta operação

---

## 📌 Regras implementadas

* ✔ Validação de produto via API externa
* ✔ Validação de segmento AGRO
* ✔ Persistência em banco relacional
* ✔ Regra PJ com tabela adicional
* ✔ Tratamento de erros global
* ✔ Testes unitários com mock

---

## 🧪 Testes

```bash
mvn test
```

---

## 📖 Documentação

Swagger disponível em:

```
http://localhost:8080/swagger-ui.html
```
