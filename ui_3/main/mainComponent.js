angular.module('flight').component('mainComponent', {
    templateUrl: './main/mainTemplate',
    controller: 'mainController',
    bindings: {
        resolvedFlights: '='
    }

})