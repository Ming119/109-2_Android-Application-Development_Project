# 109-2_Android-Application-Development_Project

### Database Web APIs
By 108590050  

I have create a Web Server for accessing the Database, since Android Studio cannot connect to the Database directly.

> Server IP: 140.124.184.193:8080/androidfinal/

methods:  

#### Create a new user
Parameters:
+ uid: userID  
+ name: username  
+ password: password  

Return:
+ HTTP status codes 200 if success.  
- HTTP status codes 400 if failed.

> /CreateUser?uid=108590000&name=test&password=test

#### Get all users
Parameters:
+ NONE

Return:
+ a list of users data in JSON format.

> /GetUser


#### Get a user by uid
Parameters:
+ uid: userID

Return:
+ a list of user data in JSON format.

Return:
+ HTTP status codes 200 if success.
- HTTP status codes 400 if failed.

> /GetUser?uid=108590000

#### Update a user by uid
Parameters:
+ uid: userID
+ name: username
+ password: password

Return:
+ HTTP status codes 200 if success.
- HTTP status codes 400 if failed.
```php
/UpdateUser?uid=108590000&name=newTest&password=newTest
```

#### Delete a user by uid
Parameters:
+ uid: userID

Return:
+ HTTP status codes 200 if success.
- HTTP status codes 400 if failed.
```php
/DeleteUser?uid=108590000
```


#### Create a new item
Parameters:
+ uid: userID  
+ name: username  
+ password: password  

Return:
+ HTTP status codes 200 if success.  
- HTTP status codes 400 if failed.

> /CreateItem?uid=108590000
