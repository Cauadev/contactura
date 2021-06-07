# Contactura

Documentação do projeto contactura realizado no treinamento da Fuctura 🙂.

## Principais tecnologias

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Lombok](https://projectlombok.org/)
* [Maven](http://maven.apache.org/)
* [DevTools](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-devtools.html)
* [Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson)
* [Spring Security](https://spring.io/projects/spring-security)
* Mysql
* [JPA/Hibernate](https://spring.io/projects/spring-data-jpa)

## Pre-requisitos

Você precisa ter o Mysql instalado na sua maquina, para persistência dos dados.

## Configuração

Vá até o `application.properties` para realizar algumas alterações referentes a conexão e porta onde que será utilizada.

```
spring.datasource.url=jdbc:mysql://localhost:3306/contactura?useTimeZone=trues&serverTimeZone=UTC&allowPublicKeyRetrieval=true
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=Seu Username
spring.datasource.password=Sua Senha
```

##Entidades

Temos duas entidades:
* ContacturaUser
* Contactura

`ContacturaUser` está relacionada com a parte de autenticação do usuário.
```java
private Integer id;
private String username;
private String password;
private String name;
private boolean admin;
```

`Contactura` está relacionada com a parte de contatos de usuários.
```java
private Integer id;
private String name;
private String email;
private String phone;
```

## Controllers
`ContacturaController` e `ContacturaUserController`
### Endpoints:

#### ContacturaController
* /contactura `[GET]` => retorna lista de contatos.
* /contactura/{id} `[GET]` => retorna contanto específico e espera um id no parâmetro.
* /contactura `[POST]` => Salva contato.
* /contactura/{id} `[PUT]` => Atualiza contato por id.
* /contactura/{id} `[DELETE]` => Deleta contato por id.

#### ContacturaUserController
* /user/login `[GET]` => retorna token de acesso.
* /user `[GET]` => retorna lista de usuarios.
* /user/{id} `[GET]` => retorna usuario por id.
* /user `[POST]` => cria usuario.
* /user/{id} `[PUT]` => Atualiza usuario por id.
* /user/{id} `[DELETE]` => Deleta usuario por id.


