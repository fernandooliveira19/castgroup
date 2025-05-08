# Cast-group

### Dependencias

* H2
* Flyway
* Thimeleaf


### Decisoes da implementacao

Foi utilizado o banco de dados em memoria, para nao precisar criar um banco externo.
A geracao das tabelas de banco de dados, foi realizada com o flyway.

A arquitetura utilizada foi MVC

Utilizacao de alguns conceitos de CLEANCODE e SOLID, na estrutura do projeto, como Principio de Responsabilidade Unica, Segregação de Interface, Inversão de Dependencia

Testes unitários utilizando Mockito e JUnit

Implementação utilizando Java 21

A párte de configuração de acesso por perfil não foi implementado. O exemplo de configuração está na classe WebSecurityConfig.java



### Comandos para subir aplicacao

# build docker image

* $ docker build -t castgroup:v1 .

# run docker container

* $ docker run -p 8080:8080 castgroup:v1 