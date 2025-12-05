# 📘 Sistema Acadêmico – API REST com Spring Boot

Este projeto é uma API REST para gerenciamento de **Alunos** e **Cursos**, desenvolvida em **Spring Boot**, com integração de monitoramento (Prometheus + Grafana), testes de carga (JMeter), e empacotamento com Docker.

---

## 🚀 Tecnologias Utilizadas
- Java 17  
- Spring Boot 3  
- Spring Web  
- Spring Data JPA (Hibernate)  
- H2 Database (em memória)  
- Maven  
- Docker  
- Prometheus (coleta de métricas)  
- Grafana (visualização)  
- Apache JMeter (teste de carga)

---

## 📂 Estrutura do Projeto (resumida)

```
/ (raiz do repositório)
 ├── src/
 ├── pom.xml
 ├── Dockerfile
 ├── README.md
 ├── jmeter/
     ├── teste-carga.jmx
     ├── resultado.jtl
     └── resultado.png
 
```

---

## 🔐 Autenticação
A API utiliza **Basic Auth** para proteger os endpoints:

- **usuário:** `admin`  
- **senha:** `admin`

No Postman: Authorization → Basic Auth.

---

## 📘 Documentação (Swagger)
A documentação OpenAPI/Swagger fica disponível em:
```
http://localhost:8080/swagger-ui/index.html
```

---

## 📦 Como Executar o Projeto Localmente

### 1. Rodar pela IDE (IntelliJ/Eclipse)
Abra o projeto e execute a classe principal com `@SpringBootApplication`.

### 2. Rodar pelo Maven (se tiver mvn instalado)
```bash
mvn spring-boot:run
```

A API estará disponível em:
```
http://localhost:8080
```

---

## 🛠 Endpoints Principais

### Alunos
```
GET    /api/alunos
GET    /api/alunos/{id}
POST   /api/alunos
PUT    /api/alunos/{id}
DELETE /api/alunos/{id}
```

### Cursos
```
GET    /api/cursos
GET    /api/cursos/{id}
POST   /api/cursos
PUT    /api/cursos/{id}
DELETE /api/cursos/{id}
```

### Matrículas
```
POST   /api/alunos/{alunoId}/curso/{cursoId}    # matricular aluno em curso
DELETE /api/alunos/{alunoId}/curso/{cursoId}    # remover matrícula
GET    /api/alunos/{alunoId}/cursos             # listar cursos de um aluno
GET    /api/cursos/{cursoId}/alunos             # listar alunos de um curso
```

---

## 📈 Monitoramento — Prometheus + Grafana

### Ativar métricas no `application.properties`:
```properties
management.endpoints.web.exposure.include=health,info,metrics,prometheus
management.endpoint.health.show-details=always
```

### Expor métricas:
```
http://localhost:8080/actuator/prometheus
```

### Exemplo de `prometheus.yml` (usar com docker-compose)
```yaml
global:
  scrape_interval: 5s

scrape_configs:
  - job_name: 'spring-boot-app'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['backend:8080']
```

No Grafana, adicione o Prometheus como Data Source:
- URL: `http://prometheus:9090` (ou `http://localhost:9090` se rodando localmente)
- Teste e salve.

Query recomendada para relatório:
```
increase(http_server_requests_seconds_count[1m])
```

---

## 🧪 Teste de Carga — JMeter

Fornecemos um plano de teste simples:

- Arquivo: `jmeter/teste-carga.jmx`
- Cenário usado: 50 usuários, rampa 10s, duração 60s
- Endpoint testado: `GET /api/alunos`

### Como executar (GUI)
1. Abra o Apache JMeter.
2. File → Open → `jmeter/teste-carga.jmx`
3. Clique em **Start**.
4. Após terminar, selecione **Summary Report** → botão direito → Save Table Data → salve como `resultado.jtl`.
5. Tire um print da tela do Summary Report (`jmeter/resultado.png`) e envie para `evidencias/`.

---

## 🐳 Docker

### Dockerfile (exemplo simples — já presente no projeto)
```dockerfile
FROM eclipse-temurin:17-jdk
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### Gerar .jar e construir imagem
```bash
mvn clean package -DskipTests
docker build -t sistema-academico .
```

### Rodar o container
```bash
docker run -p 8080:8080 sistema-academico
```

---

## 🌐 Deploy (sugestão: Render)
1. Crie um Web Service no Render.
2. Conecte ao repositório GitHub.
3. Escolha **Docker** como runtime (Render detecta Dockerfile).
4. Deixe Build/Start em branco (Render usa Dockerfile).
5. Deploy e copie a URL pública para o README.

---

## ✔ Checklist de Entrega
- [ ] Código fonte
- [ ] `pom.xml`
- [ ] `Dockerfile`
- [ ] `README.md`
- [ ] `jmeter/teste-carga.jmx`
- [ ] `jmeter/resultado.jtl`

---

## Contato
Alessandro Ferreira Eugênio dos Santos.
202251061387
