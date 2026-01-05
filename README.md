# CourseHub API

API REST desenvolvida em **Java com Spring Boot** para gerenciar **alunos, cursos e matrículas**.  
O projeto foi criado com foco em organização, clareza dos endpoints e separação de responsabilidades.

## Sobre o projeto

O CourseHub permite controlar o ciclo completo de uma matrícula:
- alunos se matriculam em cursos
- a matrícula pode ser concluída ou cancelada
- consultas podem ser feitas usando filtros simples

A ideia foi simular um cenário real de back-end, comum em sistemas educacionais e plataformas de cursos.

## Tecnologias

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Maven
- Banco de dados relacional (H2 ou PostgreSQL)

## Estrutura

O projeto está organizado em camadas:

- **controller** – expõe os endpoints da API
- **service** – contém as regras de negócio
- **repository** – acesso ao banco de dados
- **model** – entidades do domínio

Essa separação facilita manutenção e futuras evoluções.

## Endpoints

### Cursos
- `GET /courses` – lista todos os cursos
- `GET /courses?active=true` – lista apenas cursos ativos
- `POST /courses` – cria um curso
- `PUT /courses/{id}` – atualiza um curso
- `DELETE /courses/{id}` – desativa um curso

### Alunos
- `GET /students` – lista alunos
- `GET /students/{id}` – busca aluno por id
- `POST /students` – cria um aluno
- `PUT /students/{id}` – atualiza um aluno

### Matrículas
- `POST /enrollments?studentId={id}&courseId={id}` – cria matrícula
- `PUT /enrollments/{id}/complete` – conclui matrícula
- `DELETE /enrollments/{id}/cancel` – cancela matrícula
- `GET /enrollments` – lista matrículas
- `GET /enrollments?email={email}` – filtra por aluno
- `GET /enrollments?title={courseTitle}` – filtra por curso
- `GET /enrollments?status={status}` – filtra por status

## Observações

- O projeto **não utiliza DTOs**, pois o foco foi consolidar bem controllers, services e regras de negócio.
- Os testes foram feitos manualmente com ferramentas como Postman.
- A API segue boas práticas REST, com URLs claras e uso correto dos métodos HTTP.

## Próximos passos

- Adicionar DTOs
- Implementar validações
- Criar testes automatizados
- Documentar com Swagger

## Autor

João Vitor Machado  
Back-end Java
