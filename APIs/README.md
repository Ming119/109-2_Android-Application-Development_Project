# 109-2_Android-Application-Development_Project

### Database Web APIs
By 108590050  

I have create a Web Server for accessing the Database, since Android Studio cannot connect to the Database directly.

[Server](https://140.124.184.193/androidfinal/)

methods:  

#### Create a new user
Parameters:
+ uid: userID  
+ name: username  
+ password: password  

Return:
+ HTTP status code 200 if success.  
- HTTP status code 400 if failed.
````php
> /CreateUser?uid=userID&name=username&password=password
````

#### Get all users
Parameters:
+ NONE

Return:
+ a list of users data in JSON format.
````php
> /GetUser
````

#### Get a user by uid
Parameters:
+ uid: userID

Return:
+ a list of user data in JSON format.
````php
/GetUser?uid=userID
````

#### Update a user by uid
Parameters:
+ uid: userID
+ name: newUsername
+ password: newPassword

Return:
+ HTTP status code 200 if success.
- HTTP status code 400 if failed.
````php
/UpdateUser?uid=userID&name=newUsername&assword=newPassword
````

#### Delete a user by uid
Parameters:
+ uid: userID

Return:
+ HTTP status code 200 if success.
- HTTP status code 400 if failed.
````php
/DeleteUser?uid=userID
````


#### Create a new item
Parameters:
+ title: title
+ desc: description  
+ price: price
+ uid: userID  

Return:
+ HTTP status code 200 if success.  
- HTTP status code 400 if failed.
````php
/CreateItem?title=title&desc=description&price=price&uid=userID
````

#### Get all items
Parameters:
+ NONE

Return:
+ a list of items data in JSON format.
````php
> /GetItem
````

#### Get an item by id
Parameters:
+ id: itemID

Return:
+ a list of items data in JSON format.
````php
> /GetItem?id=itemID
````

#### Update a item by id
Parameters:
+ id: itemID
+ title: newTitle
+ desc: newDescription  
+ price: nwePrice

Return:
+ HTTP status code 200 if success.
- HTTP status code 400 if failed.
````php
/UpdateItem?id=itemID&title=newTitle&desc=newDescription&newPrice=price
````

#### Sell an item by id
Parameters:
+ id: itemID

Return:
+ HTTP status code 200 if success.
- HTTP status code 400 if failed.

````php
/UpdateItem?id=itemID
````

#### Delete a item by id
Parameters:
+ id: itemID

Return:
+ HTTP status code 200 if success.
- HTTP status code 400 if failed.
````php
/DeleteItem?id=itemID
````

#### Create a chat record
Parameters:
+ fromUID:
+ toUID:
+ message:

Return:
+ HTTP status code 200 if success.
- HTTP status code 400 if failed.
````php
/CreateChatRecord?fromUID=userID&toUID=userID&message=message
````

#### Get all chat record
Parameters:
+ NONE

Return:
+ a list of chat record data in JSON format.
````php
> /GetChatRecord
````

#### Get chat records between two user
Parameters:
+ fromUID: userID
+ toUID: userID

Return:
+ a list of chat record data in JSON format.
````php
> /GetChatRecord?fromUID=userID&toUID=userID
````

#### Get all last Chat Record
Parameters:
+ uid: userID

Return:
+ a list of chat record data in JSON format.
````php
> /GetChatRecord?uid=userID
````

#### Delete a chat record by crid
Parameters:
+ crid: chatRecordID

Return:
+ HTTP status code 200 if success.
- HTTP status code 400 if failed.
````php
/DeleteChatRecord?crid=chatRecordID
````
