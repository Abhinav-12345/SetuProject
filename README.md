# SetuProject


Server Details-
1. To start the server clone the project in local.
2. As this is Spring boot project it has inbuilt tomcat server through which we can access and test api's
3. Use the swagger link when the server is UP in the local(http://localhost:8080/swagger-ui.html#/)


API' Details with RequestBody Info--

Following is the sequence of orders of the api while testing as well-

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
       3. The User will intiate the group after that with same api endpoint different users can be added to the group e.g- {
                                                                                                                           "groupName":"GOA",
                                                                                                                           "userName":"Ayush"
                                                                                                                           }
                                
       4. Method POST


d)- A user can add the expense by using-
        1. Endpoint - expense/addExpense
        2. Sample Request Body- {
                             "amount": 4000,
                             "groupName": "GOA",
                              "paidBy": "abhinav"
                             }
        3. Due to time constraint I assumed group name is going to be unique and will not be same for a different groups of friends  
        4. Method POST

e)- Owe list can be dispayed on the screen using the API below-
        1. Endpoint - /expense/getOweList/{groupName}
        2. Here we are getting all the username who owe some amount from their group friends
        3. Method- GET
        4. Response is a list of strings having the format "User A owes User B amount 400"
        5. Once users settleup which is a different API Then the above stamement is not displayed from owe list api


f)-  A user can settle the payment accordung to displayed amount in Owe list
        1. Endpoint-  POST /expense/settlePayment
        2. Sample Request Body--- {
                            "amount": 1000,
                            "groupName": "GOA",
                           "paidBy": "bhaskar",
                           "paidTo": "ayush"
                         }
        3. Method- POST
        4. In this method we are not allowing partial settlement so if a user settles with less it throws error on client.
        5. We also update the information in Expense table with amount 0 so that it does not dispays in Owe List
        6. Also we are updating the TransactionHistory Table to show all the paid transaction Information


g)- Get the transaction
        1. Its a API which just returns the already paid transactions during settles
        2. Method GET
        3. Retrieves Info from TransactionsHistory DB






