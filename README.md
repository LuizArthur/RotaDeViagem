

# Objetivo
Esse programa possui duas Rest APis e um shell interface, onde é possível adicionar rotas de aeroporto em um arquivo csv e achar a rota mais barata
entre dois aeroportos.
# Execução
Para executar a aplicação você precisa ter instalado curl, docker e docker-compose.
Entre na pasta dockere digite o seguinte comando:
```sh
$ . shellinterface.sh completePathToInputFile
```
Depois de iniciada a execução, o programa pedirá a rota, para o cálculo do menor custo, como shell input. O shell input deve obedecer o seguinte formato:
XXX-XXX => Exemplo: GRU-CDG

Na root folder do projeto existe uma file exemplo chamada input-file.txt, cuja a estrutura é a seguinte:
CodigoDoAeroporto,CodigoDoAeroporto,CustoDaRota
CodigoDoAeroporto,CodigoDoAeroporto,CustoDaRota
...
##### Vale lembrar que para a execução apropriada do programa, o código do aeroporto deve obedecer o padrão [IATA](https://en.wikipedia.org/wiki/IATA_airport_code#:~:text=An%20IATA%20airport%20code%2C%20also,Air%20Transport%20Association%20(IATA).) (composto de três letras) e o custo não deve ser negativo . Isso vale tanto para os dados de entrada, assim como para o input no shell.

Para sair do shell interface basta pressionar CTRL+C. E para parar a imagem docker:
```sh
$ sudo docker-compose stop
```
# Estrutura de arquivos/pacotes
```
RotaDeViagem
|-- .gitignore
|-- input-file.txt
|-- README.md
|-- command
    |-- src
        |-- main
            |-- java
                |-- application
                    |-- AirportAppService.java
                    |-- IAirportAppService.java
                    |-- IRouteAppService.java
                    |-- RouteAppService.java
                |-- domain
                    |-- entities
                        |-- Airport.java
                        |-- EntityBase.java
                        |-- Route.java
                    |-- exceptions
                        |-- DomainRuleException.java
                    |-- services
                        |-- AirportService.java
                        |-- IAirportService.java
                        |-- IRouteService.java
                        |-- RouteService.java
                    |-- specifications
                        |-- AirportIaaCodePatternSpecification.java
                        |-- ArrivalAirportNotNullSpecification.java
                        |-- CostIsNotNegativeSpecification.java
                        |-- CostIsNotNullSpecification.java
                        |-- DepartureAirportNotNullSpecification.java
                        |-- ISpecification.java
                        |-- SpecificationResult.java
                |-- infra
                    |-- data
                        |-- DataBaseFactory.java
                        |-- IDataBaseFactory.java
                    |-- repositories
                        |-- IRouteRepository.java
                        |-- RouteRepository.java
                    |-- services
                        |-- FileService.java
                |-- unittest
                    |-- entities
                        |-- AirportTest.java
                        |-- RouteTest.java
                    |-- specifications
                        |-- AirportIaaCodePatternSpecificationTest.java
                        |-- ArrivalAirportNotNullSpecificationTest.java
                        |-- CostIsNotNegativeSpecificationTest.java
                        |-- CostIsNotNullSpecificationTest.java
                        |-- DepartureAirportNotNullSpecificationTest.java
                |-- webapp
                    |-- dto
                        |-- InsertRoutesAdapter.java
                        |-- InsertRoutesDto.java
                    |-- utils
                        JsonUtils.java
                    |-- RouteWebApp.java
|-- docker
    |-- .env
    |-- docker-compose.yml
    |-- shellinterface.sh
    |-- tomcat.service
    |-- backend
        |-- Dockerfile
    |-- input
        |-- input.csv
|-- query
   |-- src
       |-- main
           |-- java
               |-- application
                   |-- AirportAppService.java
                   |-- IAirportAppService.java
                   |-- IRouteAppService.java
                   |-- RouteAppService.java
               |-- domain
                   |-- entities
                       |-- Airport.java
                       |-- EntityBase.java
                       |-- Route.java
                   |-- exceptions
                       |-- DomainRuleException.java
                   |-- services
                       |-- AirportService.java
                       |-- IAirportService.java
                       |-- IRouteService.java
                       |-- RouteService.java
                   |-- specifications
                       |-- AirportIaaCodePatternSpecification.java
                       |-- ArrivalAirportNotNullSpecification.java
                       |-- CostIsNotNegativeSpecification.java
                       |-- CostIsNotNullSpecification.java
                       |-- DepartureAirportNotNullSpecification.java
                       |-- ISpecification.java
                       |-- SpecificationResult.java
                   |-- vobjects
                       |-- BestRoute.java
               |-- infra
                   |-- data
                       |-- DataBaseFactory.java
                       |-- IDataBaseFactory.java
                   |-- repositories
                       |-- IAirportRepository.java
                       |-- AirportRepository.java
                       |-- IRouteRepository.java
                       |-- RouteRepository.java
                   |-- services
                       |-- bestroute
                           |-- graph
                               |-- Bag.java
                               |-- Dijkstra.java
                               |-- DirectEdge.java
                               |-- EdgeWeigtedDigraph.java
                               |-- IndexMinPQ.java
                           |-- BestRouteRepositoryService.java
                           |-- IBestRouteRepositoryService.java
                       |-- FileService.java
               |-- unittest
                   |-- domain
                       |-- entities
                           |-- AirportTest.java
                           |-- RouteTest.java
                       |-- specifications
                           |-- AirportIaaCodePatternSpecificationTest.java
                           |-- ArrivalAirportNotNullSpecificationTest.java
                           |-- CostIsNotNegativeSpecificationTest.java
                           |-- CostIsNotNullSpecificationTest.java
                           |-- DepartureAirportNotNullSpecificationTest.java
               |-- webapp
                   |-- dto
                       |-- BestRouteAdapter.java
                       |-- BestRouteDto.java
                   |-- utils
                       JsonUtils.java
                   |-- RouteWebApp.java 
```
# Decisão de design
Para criação deste projeto, foi adotado o padrão de projeto DDD e arquitetura em layers. Para o cálculo da rota com menor custo foi utilizado grafos e o algoritmo de menor caminho Dijkstra.
Para ambas as aplicações temos duas entidades Airport e Route, e no caso da app query, temos também o objeto de valor BestRoute. Imaginei que numa aplicação real, cada aeroporto e rota teriam identificaçõs únicas além de outras informações a respeito, diferente de BestRoute, que é uma melhor rota calculada no momento e que é imutável.
A camada WebApp tem a função de receer os request e adaptar a reposta da API para um dto. WebApp por sua vez chama AppService, qu funciona como um orquestrador, que por sua vez, se conecta com domain, onde está contido as regras do negócio. E para finalizar, a camada Infra, onde conteria a conexão com os bancos de dados (no caso consulta e escrita no arquivo csv), assim como qualuer tipo de serviço que não envolva diretamente as regras de negócio.

#Descrição das APIs
* Command API:
    POST que tem como objetivo escrever novas rotas no arquivo de input.
    Recebe como parâmetros:
        - departureAirportCode => Exemplo: GRU
        - arrivalAirportCode => Exemplo: CGH
        - cost => Exemplo: 10 (Número inteiro)
    Encoding: application/x-www-form-urlencoded
    url: http://localhost:8080/command/route
* Query:
    GET que tem como objetivo consultar a rota mais barata entre dois aeroportos
    Recebe como parâmentros:
        - route => Exemplo: GRU-CDG
    url: http://localhost:8080/query/route => Exemplo: http://localhost:8080/query/route?route=GRU-CDG