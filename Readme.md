Entendi! Vou atualizar a estrutura do projeto para refletir os nomes corretos dos arquivos na pasta `dto`.

---

# Concessionária

Este projeto é uma aplicação para a gestão de uma concessionária fictícia, permitindo o cadastro, consulta, atualização e exclusão de veículos.

## Índice

- Funcionalidades
- Tecnologias Utilizadas
- Instalação e Configuração
- Uso
- Testes
- Estrutura do Projeto
- Contribuição
- Licença
- Contato

## Funcionalidades

- **CRUD de veículos**: Permite o cadastro, consulta, atualização e exclusão de veículos na concessionária.

## Tecnologias Utilizadas

- **Back-end**:
    - Linguagem de Programação: Java 21
    - Framework: Spring Boot
    - Banco de Dados: MySQL

- **Front-end**:
    - HTML
    - CSS
    - JavaScript

## Instalação e Configuração

1. Clone o repositório:
   ```bash
   git clone https://github.com/mmartirio/concessionaria.git
   ```
2. Navegue até o diretório do projeto:
   ```bash
   cd concessionaria
   ```
3. Configure o banco de dados no arquivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/concessionaria
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```
4. Execute o script de criação do banco de dados localizado em `src/main/resources/concessionaria.sql` para criar as tabelas necessárias.

## Uso

1. Execute o projeto:
   ```bash
   mvn spring-boot:run
   ```
2. Acesse a aplicação no navegador:
   ```
   http://localhost:8080
   ```

## Testes

Para testar as rotas da aplicação, você pode utilizar o Postman. Crie requisições para as rotas disponíveis e verifique as respostas da API.

### Importando a Collection do Postman

1. Abra o Postman.
2. Clique em "Import" no canto superior esquerdo.
3. Selecione o arquivo JSON da collection localizado na pasta `src/main/resources/postman` do projeto.
4. A collection será importada e você poderá utilizá-la para testar as rotas da aplicação.

## Estrutura do Projeto

```
concessionaria/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── concessionaria/
│   │   │           ├── controller/
│   │   │           │   ├── CarController.java
│   │   │           │   ├── VehicleController.java
│   │   │           │   └── MotorcycleController.java
│   │   │           ├── dtos/
│   │   │           │   ├── CarRecordDto.java
│   │   │           │   ├── VehicleRecordDto.java
│   │   │           │   └── MotorcycleRecordDto.java
│   │   │           ├── model/
│   │   │           │   ├── CarModel.java
│   │   │           │   ├── VehicleModel.java
│   │   │           │   └── MotorcycleModel.java
│   │   │           ├── repository/
│   │   │           │   ├── CarRepository.java
│   │   │           │   ├── VehicleRepository.java
│   │   │           │   └── MotorcycleRepository.java
│   │   │           ├── service/
│   │   │           │   └── VeiculoService.java
│   │   │           └── ConcessionariaApplication.java
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   ├── concessionaria.sql
│   │   │   └── templates/
│   │   │       └── index.html
│   │   │   └── postman/
│   │   │       └── CONCESSIONARIA_ACTIONS.JSON
│   ├── test/
│   │   └── java/
│   │       └── com/
│   │           └── concessionaria/
│   │               └── ConcessionariaApplicationTests.java
├── pom.xml
└── README.md
```

### Descrição das Pastas e Arquivos

- **src/main/java/com/concessionaria**: Contém o código fonte da aplicação.
    - **controller**: Contém os controladores da aplicação, responsáveis por lidar com as requisições HTTP.
        - **CarController.java**: Controlador para operações relacionadas a carros.
        - **VehicleController.java**: Controlador para operações relacionadas a veículos.
        - **MotorcycleController.java**: Controlador para operações relacionadas a motocicletas.
    - **dto**: Contém as classes de Data Transfer Object (DTO) utilizadas para transferir dados entre as camadas da aplicação.
        - **CarRecordDto.java**: DTO para carros.
        - **VehicleRecordDto.java**: DTO para veículos.
        - **MotorcycleRecordDto.java**: DTO para motocicletas.
    - **model**: Contém as classes de modelo que representam as entidades do banco de dados.
        - **CarModel.java**: Modelo para carros.
        - **VehicleModel.java**: Modelo para veículos.
        - **MotorcycleModel.java**: Modelo para motocicletas.
    - **repository**: Contém as interfaces de repositório que interagem com o banco de dados.
        - **CarRepository.java**: Repositório para carros.
        - **VehicleRepository.java**: Repositório para veículos.
        - **MotorcycleRepository.java**: Repositório para motocicletas.
    - **service**: Contém as classes de serviço que implementam a lógica de negócios.
        - **VeiculoService.java**: Serviço para operações relacionadas a veículos.
    - **ConcessionariaApplication.java**: Classe principal que inicia a aplicação Spring Boot.

- **src/main/resources**: Contém os recursos da aplicação.
    - **application.properties**: Arquivo de configuração da aplicação.
    - **concessionaria.sql**: Script SQL para criação do banco de dados.
    - **templates**: Contém os templates HTML utilizados pela aplicação.
    - **postman**: Contém a collection do Postman para testar as rotas da aplicação.
        - **CONCESSIONARIA_ACTIONS.JSON**: Arquivo JSON da collection do Postman.

- **src/test/java/com/concessionaria**: Contém os testes unitários da aplicação.
    - **ConcessionariaApplicationTests.java**: Classe de teste para a aplicação.

- **pom.xml**: Arquivo de configuração do Maven.
- **README.md**: Arquivo de documentação do projeto.

## Contribuição

1. Faça um fork do projeto.
2. Crie uma branch para sua feature:
   ```bash
   git checkout -b minha-feature
   ```
3. Faça commit das suas alterações:
   ```bash
   git commit -m 'Minha nova feature'
   ```
4. Envie para o branch original:
   ```bash
   git push origin minha-feature
   ```
5. Crie um Pull Request.

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo LICENSE para mais detalhes.

## Contato

Se você tiver alguma dúvida ou sugestão, entre em contato através do e-mail: seu-email@dominio.com.

---

Espero que isso atenda às suas expectativas! Se precisar de mais alguma coisa, é só avisar.