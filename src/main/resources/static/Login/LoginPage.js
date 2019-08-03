'use strict';

angular.module('carTrade.login', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
    templateUrl: 'login/login.html',
    controller: 'LoginController'
  });
}])
.controller('LoginController', [function($http,AuthenticationService) {


  

}]).service('AuthenticationService', ['$http' ,function($http) {

  var authenticate =  '/authenticate';
  this.authorise = function(param) {
      return $http({
          method: 'POST',
          url: authenticate,
          data: param, //forms user object
          headers: { 'Content-Type': 'application/json' }
      });
};



}]);