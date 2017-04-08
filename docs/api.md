## LOGIN
GET
/login
```
request
{
    private String username;
    private String password;
}
response
current user
{
    private int statusCode;   
    private String errorMessage;
    private data: {
        private Long id;
        private String email;
        private String firstName;
        private String lastName;
        private Long birthday;
        private String registrationDate;
    }
}
```
- if login unsuccess statusCode = 1 and errorMessage have a message about it;

------------------------------------------------------------------------------
## USERS
```
GET
/rest/users/{id}
response
{
    private int statusCode;
    private String errorMessage;
    private data: {
        private Long id;
        private String email;
        private String firstName;
        private String lastName;
        private Long birthday;
        private String registrationDate;
    }
}
```

## Get current user
GET
/rest/users/current
```
response
{
    private int statusCode;   
    private String errorMessage;
    private data: {
        private Long id;
        private String email;
        private String firstName;
        private String lastName;
        private Long birthday;
        private String registrationDate;
    }
}
```

POST
/rest/users
```
request
{
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Long birthday;
}
response
{
    private int statusCode;
    private String errorMessage;
    private data: null;
}
```
PUT
/rest/users/{id}
```
request
{
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Long birthday;
}
response
{
    private int statusCode;
    private String errorMessage;
    private data: null;
}
```

------------------------------------------------------------------------------
PHOTO
GET
/rest/photos/{id}
```
get photo
```
DELETE
/rest/photos{id}
```
remove photo
```
POST
/rest/photos
```
multipart
request param's name - photo
response:
{
    int statusCode;
    int errorMessage;
    data: {
        int photoId;
        String photoUrl;
    }
}
```
------------------------------------------------------------------------------
MY PROFILE
GET
/rest/users/current/profile
```
response:
{
    statusCode
    errorMessage
    data : {
        String email;
        String firstName;
        String lastName;
        Long birthDay;
        String photoUrl
        String phoneNumber;
    }
}
```
NOT MY PROFILE
GET
/rest/users/{id}/profile
```
response:
{
    statusCode
    errorMessage
    data : {
        String email;
        String firstName;
        String lastName;
        Long birthDay;
        String photoUrl
        String phoneNumber;
        Long lastVisit;
    }
}
```
CHANGE MY PROFILE
PUT
/rest/users/profile
```
request:
{
    String email;
    String password;
    String firstName;
    String lastName;
    Long birthday;
    Long photoId;
    String phoneNumber;
}
response:
{   
    int statusCode;
    String errorMessage;
    data: {
        String email;
        String password;
        String firstName;
        String lastName;
        Long birthday;
        String photoUrl;
        String phoneNumber;
    }
}
```
CHANGE SETTING
PUT
```
request:
{
    String showEmail;
    String showBirthday;
    String showPhoneNumber;
}
response:
{
    int statusCode
    String errorMessage
    data: {
        Boolean showEmail;
        Boolean showBirthday;
        Boolean showPhoneNumber;
    }
}
```
GET MY CHATS
GET
```
/rest/chats
parameters:
int offset
int count
response:
{   
    int statusCode
    String errorMessage
    data: {
        boolean last;
        chats:[
            {
                String chatName;
                int usersCount;
                String lastMessageAuthorName;
                String lastMessage
                long sendTime;
            }
        ]
    }
}
```
GET MY MESSAGES
GET
/rest/messages
```
parameters:
int offset
int count
response:
{
    int statusCode;
    String errorMessage;
    data: {
        boolean last;
        messages: [
            {
                String authorPhotoUrl
                String authorName;
                String text;
                long sendTime;
                boolean isLike;
                int likes;
                attachments [
                    {
                        String type;
                        String url;
                    }
                ]
            }
        ]
    }
}
```