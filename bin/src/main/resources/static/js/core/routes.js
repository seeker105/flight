angular.
  module('app').
  config(['$routeProvider', 'baseRoute',
    function config($routeProvider, baseRoute) {

      $routeProvider.
      	when('/map', {
          
          templateUrl: baseRoute + "map/template.html",
          controller: 'MapController',
          controllerAs: 'mapController'
          
        }).
        otherwise('/map');
    }
  ]);