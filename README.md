# java.test.moan.sample.service
not so very simple service

use Docker Deployment

# and try it
GET http://192.168.99.100:18080/sampleService/api/users 
Content-Type: application/json  
Accept: application/json    

GET http://192.168.99.100:18080/sampleService/api/groups    
Content-Type: application/json  
Accept: application/json    

GET http://192.168.99.100:18080/sampleService/api/user/110  
Content-Type: application/xml   
Accept: application/xml 

POST http://192.168.99.100:18080/sampleService/api/users/add    
Content-Type: application/json  
Accept: application/json    
Body:   
[
    {
        "id": "110",
        "name": "Ann",
        "groupId": "200"
    },
    {
        "id": "111",
        "name": "Gerrit",
        "groupId": "1"
    }
]

POST http://192.168.99.100:18080/sampleService/api/user/add 
Content-Type: application/json  
Accept: application/json    
Body:   
{
    "id": "215",
    "name": "GERRIT",
    "groupId": "777"
}

DELETE http://192.168.99.100:18080/sampleService/api/user/110   
Content-Type: application/json  
Accept: application/json    
