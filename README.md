
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

```mermaid
sequenceDiagram
User ->> AuctionController: /Auctions
AuctionController-->>User: List of all auctions
User ->> AuctionController: /Auctions/Start/{auctionId}
AuctionController-->>User: auction started 
User ->> UserController: /Users/Bid/ (place bid)
AuctionController-->>User: bid placed 
User ->> AuctionController: /Auctions/{auctionId} (GET AUCTION INFO)
AuctionController-->>User: GET AUCTION INFORMATION AND TOP BIDS
User ->> AuctionController: /Auctions/End/{auctionId}
AuctionController-->>User: auction Ended 

```

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



