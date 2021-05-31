# 109-2_Android-Application-Development_Project


### Web Server API
By 108590050  

I have create a Web Server for accessing the Database, since Android Studio cannot connect to the Database directly.

> Server IP: 140.124.184.193:8080/androidfinal/

methods:  

#### Create a new user
Parameters:
> uid: userID
> name: username
> password: password
> > /CreateUser?uid=108590000&name=test&password=test

#### Get all users
Parameters:
> NONE
> > /GetUser


#### Get a user by uid
Parameters:
> uid: userID
> > /GetUser?uid=108590000

#### Update a user by uid
Parameters:
> uid: userID
> name: username
> password: password
> > /UpdateUser?uid=108590000&name=newTest&password=newTest

#### Delete a user bu uid
Parameters:
> uid: userID
> > /DeleteUser?uid=108590000
