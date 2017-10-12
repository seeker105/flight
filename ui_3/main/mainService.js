angular.module('flight').service('mainService', ['$http', '$interval', 'itineraryService', function ($http, $interval, itineraryService) {

    this.itineraryService = itineraryService

    this.getFlights = () => {
        return $http.get('http://localhost:8090/flights')
    }

    this.searchForFlights = () => {
        this.itineraryService.origin = this.origin
        console.log("in main service the this.itineraryService.origin is")
        console.log(this.itineraryService.origin)
        this.itineraryService.destination = this.destination
        return $http.get('http://localhost:8090/flights/from/' + this.origin + '/to/' + this.destination)
    }

    this.stopInterval = () => {
        $interval.cancel(this.interval)
    }



}])