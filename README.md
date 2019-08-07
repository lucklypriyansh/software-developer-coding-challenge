
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

![alt text](https://mermaidjs.github.io/mermaid-live-editor/#/view/eyJjb2RlIjoic2VxdWVuY2VEaWFncmFtXG5Vc2VyIC0-PiBBdWN0aW9uQ29udHJvbGxlcjogL0F1Y3Rpb25zXG5BdWN0aW9uQ29udHJvbGxlci0tPj5Vc2VyOiBMaXN0IG9mIGFsbCBhdWN0aW9uc1xuVXNlciAtPj4gQXVjdGlvbkNvbnRyb2xsZXI6IC9BdWN0aW9ucy9TdGFydC97YXVjdGlvbklkfVxuQXVjdGlvbkNvbnRyb2xsZXItLT4-VXNlcjogYXVjdGlvbiBzdGFydGVkIFxuVXNlciAtPj4gVXNlckNvbnRyb2xsZXI6IC9Vc2Vycy9CaWQvIChwbGFjZSBiaWQpXG5BdWN0aW9uQ29udHJvbGxlci0tPj5Vc2VyOiBiaWQgcGxhY2VkIFxuVXNlciAtPj4gQXVjdGlvbkNvbnRyb2xsZXI6IC9BdWN0aW9ucy97YXVjdGlvbklkfSAoR0VUIEFVQ1RJT04gSU5GTylcbkF1Y3Rpb25Db250cm9sbGVyLS0-PlVzZXI6IEdFVCBBVUNUSU9OIElORk9STUFUSU9OIEFORCBUT1AgQklEU1xuVXNlciAtPj4gQXVjdGlvbkNvbnRyb2xsZXI6IC9BdWN0aW9ucy9FbmQve2F1Y3Rpb25JZH1cbkF1Y3Rpb25Db250cm9sbGVyLS0-PlVzZXI6IGF1Y3Rpb24gRW5kZWQgXG4iLCJtZXJtYWlkIjp7InRoZW1lIjoiZGVmYXVsdCJ9fQ)

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



