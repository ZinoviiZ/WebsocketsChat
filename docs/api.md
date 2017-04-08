
LOGIN
GET
/login
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
        private String birthday;
        private String registrationDate;
    }
}
- if login unsuccess statusCode = 1 and errorMessage have a message about it;

------------------------------------------------------------------------------
USERS

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
        private String birthday;
        private String registrationDate;
    }
}

GET
/rest/users/current
response
{
    private int statusCode;   
    private String errorMessage;
    private data: {
        private Long id;
        private String email;
        private String firstName;
        private String lastName;
        private String birthday;
        private String registrationDate;
    }
}

POST
/rest/users
request
{
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String birthday;
}
response
{
    private int statusCode;
    private String errorMessage;
    private data: null;
}

PUT
/rest/users/{id}
{
request
{
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String birthday;
}
response
{
    private int statusCode;
    private String errorMessage;
    private data: null;
}

------------------------------------------------------------------------------
PHOTO
GET
/rest/photos/{id}
get photo

DELETE
/rest/photos{id}
remove photo

POST
/rest/photos
multipart
request param's name - photo

------------------------------------------------------------------------------
MY PROFILE
GET
/rest/users/current/profile
response:
{
    statusCode
    errorMessage
    data : {
        String email;
        String firstName;
        String lastName;
        String birthDay;
        String photoUrl
        String phoneNumber;
    }
}

NOT MY PROFILE
GET
/rest/users/{id}/profile
response:
{
    statusCode
    errorMessage
    data : {
        String email;
        String firstName;
        String lastName;
        String birthDay;
        String photoUrl
        String phoneNumber;
        String lastVisit;
    }
}

CHANGE MY PROFILE
POST
/rest/users/profile
request:
{
    String email;
    String password;
    String firstName;
    String lastName;
    Long timeStamp;
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
        Long timeStamp;
        String photoUrl;
        String phoneNumber;
    }
}

CHANGE SETTING
PUT
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
        String showEmail;
        String showBirthday;
        String showPhoneNumber;
    }
}

GET MY CHATS
GET
/rest/chats/{offSet}/{count}
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

GET MY MESSAGES
GET
/rest/messages/{offSet}/{count}
response:
{
    int statusCode;
    String errorMessage;
    data: {
        boolean last;
        messages: [
            {
                String authorName;
                String text;
                long sendTime;
                boolean isLike;
                count likes;
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

