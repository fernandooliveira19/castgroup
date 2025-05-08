# Cast-group

### Dependencias

* H2
* Flyway
* Thimeleaf


### Decisoes da implementacao

Implementação utilizando Java 21

Foi utilizado o banco de dados em memoria, para nao precisar criar um banco externo.
A geracao das tabelas de banco de dados, foi realizada com o flyway.

A arquitetura utilizada foi MVC

Utilizacao de alguns conceitos de CLEANCODE e SOLID, na estrutura do projeto, como Principio de Responsabilidade Unica, Segregação de Interface, Inversão de Dependencia

Testes unitários utilizando Mockito e JUnit



A párte de configuração de acesso por perfil não foi implementado. O exemplo de configuração está na classe WebSecurityConfig.java.

E a apresentacao do acesso na tela seria controlada atraves de um usuario logado. Exemplo:

### arquivo sidebar.html
                <ul class="nav nav-pills" th:if="${isUsuarioAdmin}">
					<li class="nav-item"><span class="nav-link active" >Agencia</span></li>
					<li class="nav-item"><a class="nav-link"
						href="/agencias/cadastrar"> <i class="oi oi-plus"></i> <span>Cadastrar</span>
					</a></li>

				</ul>

				<ul class="nav nav-pills">
					<li class="nav-item"><span class="nav-link active" >Contas</span></li>
					<li class="nav-item"><a class="nav-link" 
						href="/contas/cadastro" th:if="${isUsuarioAdmin}"> <i
							class="oi oi-plus"></i> <span>Cadastrar </span>
					</a></li>
					<li class="nav-item"><a class="nav-link" 
						href="/contas/credito"> <i
							class="oi oi-spreadsheet"></i> <span>Creditar</span>
					</a></li>


## Comandos para subir aplicacao

## build docker image

* $ docker build -t castgroup:v1 .

## run docker container

* $ docker run -p 8080:8080 castgroup:v1 