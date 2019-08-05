'use strict';

angular
		.module('carTrade.auction', ['ngRoute'])
		.config(['$routeProvider', function($routeProvider) {
			$routeProvider.when('/auction', {
				templateUrl : 'auction/auction.html',
				controller : 'AuctionCtrl'
			});
		}])

		.controller(
				'AuctionCtrl',
				[
						"$scope",
						"$http",
						"AuctionService",
						function($scope, $http, AuctionService) {

							$scope.auctions = {};
							$scope.init = function() {
								$scope.auctions = AuctionService
										.getAllAuctions()
										.then(
												function(response) {
													if (response.status == 200) {
													  $scope.auctions=response.data;
													} else {
														alert("some error happend while loading auctions")
													}
												});
							}

						}]).service('AuctionService',
				['$http', function($http) {

					var baseurl = '/Auction-Controller';
					this.getAllAuctions = function() {
						return $http({
							method : 'GET',
							url : baseurl + "/Auctions",
							headers : {
								'Content-Type' : 'application/json'
							}
						});
					};

				}]);
