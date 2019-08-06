'use strict';

angular
        .module('carTrade.auction', ['ngRoute'])
        .config(['$routeProvider', function($routeProvider) {
          $routeProvider.when('/auction', {
            templateUrl: 'auction/auction.html',
            controller: 'AuctionCtrl'
          });
          $routeProvider.when("/auctionDetailInfo/:auctionId", {
            templateUrl: 'auction/auctionInfo.html',
            controller: 'AuctionInfo'
          });

        }])
        .controller(
                'AuctionCtrl',
                [
                    "$scope",
                    "$http",
                    "AuctionService",
                    "$location",
                    '$mdDialog',
                    function($scope, $http, AuctionService, $location,
                            $mdDialog) {

                      $scope.go=function(path)
                      {
                        $location.path(path);
                      }
                      $scope.auctions = {};
                      $scope.init = function() {
                        $scope.auctions = AuctionService
                                .getAllAuctions()
                                .then(
                                        function(response) {
                                          if (response.status == 200) {
                                            $scope.auctions = response.data;
                                          } else {
                                            alert("some error happend while loading auctions")
                                          }
                                        });
                      }

                      $scope.placeBid = function(ev, auctionId) {
                        
                        $mdDialog.show({
                          controller: placeBidController,
                          templateUrl: 'auction/placeBidDialouge.html',
                          parent: angular.element(document.body),
                          targetEvent: ev,
                          clickOutsideToClose: true,
                          locals: {
                            auctionServiceReference: AuctionService,
                            auctionId: auctionId
                          }
                        });
                      }

                      function placeBidController($scope, $mdDialog,
                              auctionServiceReference, auctionId) {
                        $scope.saveBid = function() {
                          $scope.bid.auctionId = auctionId;
                          auctionServiceReference.bidonAuction($scope.bid)
                                  .then(function(response) {

                                    if (response.status == 200) {
                                      alert('saved sucessfully')
                                      $mdDialog.hide();
                                    }
                                    if (response.status != 200) {
                                      alert(response.data)
                                    }
                                  },
                                  function(ex) {

                                    alert(ex.status+' '+ex.data.message)
                                  })

                        }

                      }

                    }])
        .controller(
                'AuctionInfo',
                [
                    "$scope",
                    "$http",
                    "AuctionService",
                    '$routeParams',
                    '$mdDialog',
                    function($scope, $http, AuctionService, $routeParams,
                            $mdDialog) {

                      AuctionService
                              .getAuctionInfo($routeParams.auctionId)
                              .then(
                                      function(response) {
                                        if (response.status == 200) {
                                          $scope.auction = response.data;
                                        } else {
                                          alert("some error happend while loading auctions Info")
                                        }
                                      });

                    }]).service('AuctionService', ['$http', function($http) {
          var baseurlAuction = '/Auction-Controller';
          // will be needed to create seperate service for auction controller
          // and user service
          var baseurlUser = '/User-Management';

          this.getAllAuctions = function() {
            return $http({
              method: 'GET',
              url: baseurlAuction + "/Auctions",
              headers: {
                'Content-Type': 'application/json'
              }
            });
          };

          this.getAuctionInfo = function(auctionId) {
            return $http({
              method: 'GET',
              url: baseurlAuction + "/Auctions" + "/" + auctionId,
              headers: {
                'Content-Type': 'application/json'
              }
            });
          };

          this.bidonAuction = function(bid) {
            return $http({
              method: 'POST',
              url: baseurlUser + "/Users/Bid",
              data: bid,
              headers: {
                'Content-Type': 'application/json'
              }
            });
          };

        }]);
