'use strict';

angular.module('carTrade.login', [ 'ngRoute' ])

.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/login', {
		templateUrl : 'login/login.html',
		controller : 'loginController'
	});
} ]).controller(
		'loginController',
		[
				"$scope",
				"store",
				"AuthenticationService",
				function($scope, store, AuthenticationService) {

					$scope.authorise = function() {
						AuthenticationService.authorise($scope.user).then(
								function(response) {
									if (response.status == 200) {
										store.setSession("token",
												response.data.token);

									} else if (response.status == 401) {
										alert("Un authorised user")
									}

								});
					}

				} ]).service('AuthenticationService',
		[ '$http', function($http) {

			var authenticate = '/authenticate';
			this.authorise = function(param) {
				return $http({
					method : 'POST',
					url : authenticate,
					data : param, // forms user object
					headers : {
						'Content-Type' : 'application/json'
					}
				});
			};

		} ]);