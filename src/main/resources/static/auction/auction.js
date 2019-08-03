'use strict';

angular.module('carTrade.auction', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/auction', {
    templateUrl: 'auction/auction.html',
    controller: 'AuctionCtrl'
  });
}])

.controller('View1Ctrl', [function($http) {


  
}]);