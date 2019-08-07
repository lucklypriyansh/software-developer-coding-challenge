
# Software Developer Coding Challenge

## Build Status
![Build Status](https://travis-ci.com/lucklypriyansh/software-developer-coding-challenge.svg?branch=master)

  
## CI/CD
- Email to lucklypriyansh@gmail.com for Travis CI
- Email to lucklypriyansh@gmail.com for Heroku Acess

## Tech Stack
- Spring Boot
- mongo db
- java 8
- Junit
- Docker

# App url

https://car-auction.herokuapp.com/

# Steps to run locally

```sh
$ git clone https://github.com/lucklypriyansh/software-developer-coding-challenge.git
$ cd software-developer-coding-challenge
$ docker rmi $(docker images -qa -f 'dangling=true')
$ docker stop $(docker ps -q --filter ancestor=traderevauction )
$ ./mvnw clean package docker:build
$ docker run -p 8080:8080 traderevauction
```

## Swagger Link
> https://car-auction.herokuapp.com/swagger-ui.html
> http://localhost:8080/swagger-ui.html

## UML diagrams

Sequence Diagram for auction flow

![alt text](https://i.ibb.co/JCDcm8k/Screenshot-2019-08-07-at-7-17-02-PM.png)

## Object Diagrams
![alt text](https://i.ibb.co/sjHyXCW/Screenshot-2019-08-07-at-10-54-30-PM.png)

# Gaps and Issues

```sh
1 Testing is not comprehensive only testing is done for Auction-Controller ,Need to incorporate more test for each service and  E2E testing need to be done using Selenium
2 Need to add Authentication flow  
3 Admin flow for starting/stopping of auction 
4 Add Swagger documentation
5 Add code documentation
```

#  Steps to place bid

 -  Go to https://car-auction.herokuapp.com/
 -  click on place bid to place bid as shown in image
   ![alt text](https://i.ibb.co/2vtxGDQ/Screenshot-2019-08-07-at-7-21-02-PM.png)
 -  user must be a valid user and must have been registered in order to place bid
 -  Go to Url for getting user information > https://car-auction.herokuapp.com/User-Management/Users
 -  enter username password and bid amount to place bid
    ![alt text](https://i.ibb.co/9rFSkxw/Screenshot-2019-08-07-at-7-27-33-PM.png)
 -  Click on show auction to show auction info
    ![alt text](https://i.ibb.co/1QfXXhF/Screenshot-2019-08-07-at-9-15-21-PM.png)
    



