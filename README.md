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

- PostgreSQL
- Linux
- Java 8
```
$ sudo apt-get install openjdk-8-jre
``` 
- [glassfish 5](https://javaee.github.io/glassfish/download)

// aqui-> /usr/lib/jvm/java-8-oracle
Na pasta do projeto, executamos a seguinte linha de comando:

```
$ mvn package
```

Na pasta onde o glassfish foi extraído, editamos o arquivo glassfish5/bin/asenv.conf, adicionando o local da instalação do java sob a variável AS_JAVA:

```
AS_JAVA="/usr/lib/jvm/java-8-openjdk-amd64"
```

O deploy é feito abrindo a pasta glassfish5/bin e executando:

```
$ ./asadmin start-domain domain1
$ ./asadmin \[pasta do projeto\]/target/ProvaEsig-1.0-SNAPSHOT.war

```


