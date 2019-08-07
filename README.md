
# Software Developer Coding Challenge

## Build Status
![Build Status](https://travis-ci.com/lucklypriyansh/software-developer-coding-challenge.svg?branch=master)

  

  

# App url

https://car-auction.herokuapp.com/

# Steps to run locally

```sh
$ git clone https://github.com/lucklypriyansh/software-developer-coding-challenge.git
$ cd software-developer-coding-challenge
$ ./mvnw clean install
$ ./mvnw spring-boot:run
```

## URL
> https://car-auction.herokuapp.com/swagger-ui.html



## UML diagrams

Sequence Diagram for auction flow

![alt text](https://i.ibb.co/JCDcm8k/Screenshot-2019-08-07-at-7-17-02-PM.png)

# Gaps and issues

```sh
1 need to add authentication flow  
2  need to create admin flow for starting of auction 
```

# 

```sh
1  User cannot place bid in the auction whose status ONHOLD
2  For placing the bid user must have valid username and password to place bid
3  user must be a valid user and must have been registered in order to place bid
4  GET User details - https://car-auction.herokuapp.com/User-Management/Users  (for username and password)
```



