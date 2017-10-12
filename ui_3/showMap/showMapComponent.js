angular.module('flight').component('showMapComponent', {
    templateUrl: './showMap/showMapTemplate',
    controller: 'showMapController',
    bindings: {
        resolvedItinerary: '='
    }

})