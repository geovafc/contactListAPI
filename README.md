# Desafio implementador fullstack

# Desenvolvimento
### Bibliotecas Utilizadas
- Spring Boot 2.2.1
- Spring Data Jpa
- Lombok
- Mockito
- Junit para testar as funcionalidades do sistema
 - Obs: É necessário configurar o projeto Lombok  na IDE para que o projeto funcione corretamente, senão o código apresentará problemas de compilação quando se tentar usar algum método getter ou setter (por exemplo). Caso esteja usando o Eclipse siga os passos descritos no link (https://projectlombok.org/setup/eclipse) e caso esteja usando IntelliJ IDEA instale o plugin descrito no link (https://plugins.jetbrains.com/plugin/6317-lombok-plugin). No link do projeto (https://projectlombok.org/) também pode encontrar os passos para outras IDES e editores ou se preferir, sugiro comentar as anotações : @AllArgsConstructor, @NoArgsConstructor, @Data das classes de entidade e do DTO e gerar seus respectivos getter ou setter.
- H2
	- Obs: Foi utilizado o banco H2 por não haver a necessidade de uma permanencia constante dos dados.


### Funcionalidades implementadas
- Create people by endpoint http://localhost:8080/peoples
- Get All Peoples by endpoint http://localhost:8080/peoples
- Update People by endpoint http://localhost:8080/peoples/{idPeople}
- Get People with contacts info by endpoint http://localhost:8080/peoples/{idPeople}

### Testes automatizados implementadas
- Testes na classe PeopleControllerTest implementados usando o mokito para testar o funcionamento do webservice
	- successfullyCreateAPeople para checar se o endpoint Create people está salvando corretamente os dados;
	- successfullyUpdateAPeople para checar se o endpoint Update people está atualizando corretamente os dados;
	- getAllPeoples para checar se o endpoint Get All Peoples está retornando corretamente os dados;
- Testes na classe PeopleServiceTest implementados para testar o funcionamento da classe service responsável pelas regras de negócio
	- saveAPeople para checar está salvando corretamente os dados;
	- updateAPeople para checar se está atualizando corretamente os dados;
	- getAllPeoples para checar se está retornando corretamente os dados;


### Recursos
Para rodar a aplicação basta iniciar a classe BackendappApplication. 


