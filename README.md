# SetuProject


Server Details-
1. To start the server clone the project in local.
2. As this is Spring boot project it has inbuilt tomcat server through which we can access and test api's
3. Use the swagger link when the server is UP in the local(http://localhost:8080/swagger-ui.html#/)


API' Details with RequestBody Info--

a)- Register user to splitwise app-
       1. Endpoint- localhost:8080/users/add 
       2. Sample RequestBody- {
                               "userName":"abhinav",
                               "phoneNumber":7353886751,
                               "email":"gupta.abhinav0194@gmail.com"
                              }
       3. Method - POST

b)- Check if the userid registered or not-
       1. Endpoint http://localhost:8080/users/{userName}
       2. Method GET

c)- Create and Add members to the group
       1. Endpoint- http://localhost:8080/group/add
       2. Sample Request Body - {
                                "groupName":"GOA",
                                "userName":"abhinav"
                                }
       3. The User will intiate the group after that with same api endpoint different users can be added to the group
       4. Method POST


d)-
