# Heroes_API

API criada para gerenciamento de Heroes dos Quadrinhos utilizando WebFlux.

Executar dynamo: 

 java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb
 
 aws dynamodb list-tables --endpoint-url http://localhost:8000
