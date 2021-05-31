# 109-2_Android-Application-Development_Project


### Web Server API
By 108590050  

I have create a Web server for accessing the Database, since Android Studio cannot connect to the Database directly.

> Server IP: 140.124.184.193

methods:  

#### Create a new user
> http://140.124.184.193:8080/androidfinal/CreateUser?uid=<userID>&name=<username>&password=<password>
> http://140.124.184.193:8080/androidfinal/CreateUser?uid=108590000&name=test&password=test

#### Get all users
> http://140.124.184.193:8080/androidfinal/GetUser


#### Get a user by uid
> http://140.124.184.193:8080/androidfinal/GetUser?uid=<userID>
> http://140.124.184.193:8080/androidfinal/GetUser?uid=108590000

#### Update a user by uid
> http://140.124.184.193:8080/androidfinal/UpdateUser?uid=<userID>&name=<newUsername>&password=<newPassword>
> http://140.124.184.193:8080/androidfinal/UpdateUser?uid=108590000&name=newTest&password=newTest
