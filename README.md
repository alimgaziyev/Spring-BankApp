# JAVA SPRING SOLID-project
# Bank web app
## Instruction
    Database 
        o http://localhost:8080/h2-console
    Request
        o http://localhost:8080/*

## Project
    0_Run Spring Boot application
    1_Send requests with Swagger UI http://localhost:8080/swagger-ui/index.html#/
    2_App gets request and do operation
    3_Result of operation send back in JSON format
    
## Project Structure

    kz.jusan.solidbankapp
        +- bank
            +- Account                                              / account types /
                (c) Account
                (e) AccountType 
            +- Transaction                                          / transaction types /
                (c) Transaction
                (e) TransactionType 
            (c) Bank

        +- database                                                 / repesitories /
                (i) accountRepo extends CrudRepository
                (i) TreansactionDAO extends CrudRepository

        +- services
            +- creationservice                                      / creation service /
                (i) AccountCreationService
                (c) AccountCreationServiceImpl
            +- listingservice                                       / listing service /
                (i) AccountListingService
                (c) AccountListingServiceImpl
            +- deleteservice                                        / delete service /
                (i) AccountDeleteService
                (c) AccountDeleteServiceImpl
            +- listingtransactions                                  / listing transactions service /
                (i) TransactionListingService
                (c) TransactionListingServiceImpl
            +- depositservice                                       / deposit operation service /
                (i) AccountDepositService
                (c) AccountDepositServiceImpl
            +- withdrawservice                                      / withdraw operation service /
                (i) AccountWithdrawService
                (c) AccountWithdrawServiceImpl

        +- controllers    
            (c) AccountController

        +- requestoutput
            (c) Messages
   
        +- dto                                                      / Data transfer object /
            (c) AccountDto
            (c) BankDto
            (c) TransactionDto
            (c) WithdrawDepositDto

        (c) BankAppSpringBootH2Application                          / run here / 
            
                
## Tables
    TABLE Account (
        id                          ex:     Long    1l
        account_id                          String  00100000
        client_id                           String  1
        balance                             Double  0.0
        account_type                        String  FIXED
        withdraw_allowed                    boolean false
    );

    TABLE Transaction (
        id                          ex:     Long    1
        account_id                          String  001000001
        amount                              Double  100
        date                                String  2022/05/25
        is_done                             boolean true
    );

## Requests
    GET     /accounts                               Получение списка счетов
    POST    /accounts                               Создание нового счета
    GET     /accounts/{account_id}                  Получение информации об одном счете
    DELETE  /accounts/{account_id}                  Удаление счета
    POST    /accounts/{account_id}/withdraw         Снятие денег со счета
    POST    /accounts/{account_id}/deposit          Внесение денег на счет
    GET     /accounts/{account_id}/transactions     Получение списка всех транзакций   

## About project
    + Java 17
    + Spring Boot V-2.7
    + JPA
    + web
    + Swagger UI
    + Database H2
        + flywaydb
    