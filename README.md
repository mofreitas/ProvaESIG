# ProvaESIG
Projeto referente a prova prática de estágio da ESIG

# Descrição
Foi implementado um sistema de listagem de tarefas semelhante ao exemplo deste [link](http://todomvc.com/examples/angularjs/#/), em que um usuário cadastrado pode inserir, atualizar, remover e riscar tarefas de sua lista.

# Itens feitos

- [x] A. Criar uma aplicação java web utilizando JavaServer Faces (JSF)

- [x] B. Utilizar persistência em um banco de dados (qualquer banco desejado)

- [x] C. Utilizar Hibernate e JPA

- [ ] D. Utilizar Spring Framework Boot 2.0

- [ ] E. Utilizar Spring MVC

- [x] F. Utilizar Bootstrap 4

- [x] G. Utilizar Primefaces

- [ ] H. Utilizar testes de unidades

- [ ] I. Criar single page app utilizando react

- [ ] J. Publicar projeto no heroku

- [ ] K. Criar aplicativo mobile utilizando Flutter

# Para executar

## Requisitos

- PostgreSQL `$ sudo apt install postgresql-all`
- Linux
- [Netbeans](https://netbeans.apache.org/download/)
- Maven `$ sudo apt install maven`
- Java 8 `$ sudo apt install openjdk-8-jre` 
- [Glassfish 5](https://javaee.github.io/glassfish/download)

## Procedimentos

Dentro da pasta do projeto, executamos o seguinte comando:

```
$ mvn package
```

A seguir, na pasta onde o glassfish foi extraído, editamos o arquivo glassfish-5.0-web/glassfish5/glassfish/config/asenv.conf, adicionando a seguinte linha no final do arquivo, que coloca o local da instalação do java na variável AS_JAVA:

```
AS_JAVA="/usr/lib/jvm/java-8-openjdk-amd64"
```

Então podemos fazer o deploy abrindo a pasta glassfish-5.0-web/glassfish5/bin e executando:

```
$ ./asadmin start-domain domain1
$ ./asadmin deploy --force /[caminho até pasta do projeto]/ProvaESIG/target/ProvaEsig-1.0-SNAPSHOT.war
```

Por fim, podemos acessar a página em um navegador usando o seguinte endereço:

localhost:8080/ProvaEsig-1.0-SNAPSHOT

Para efetuar o undeploy e desligar o servidor, executamos dentro da pasta glassfish-5.0-web/glassfish5/bin:

```
$ ./asadmin undeploy ProvaEsig-1.0-SNAPSHOT
$ ./asadmin stop-domain domain1
```

