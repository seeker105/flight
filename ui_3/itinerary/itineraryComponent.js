angular.module('flight').component('itineraryComponent', {
    templateUrl: './itinerary/itineraryTemplate',
    controller: 'itineraryController',
    bindings: {
        resolvedItinerary: '='
    }

})