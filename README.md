# Objetivo
Esse programa possui duas est APis e um shell interface, onde é possível adicionar rotas de aeroporto em um arquivo csv e achar a rota mais barata
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
    

```