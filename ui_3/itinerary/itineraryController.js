angular.module('flight').controller('itineraryController', ['itineraryService', 'bookingsService', '$interval', '$http', function(itineraryService, bookingsService, $interval, $http){
    
    this.itineraryService = itineraryService
    this.itinerary = this.resolvedItinerary.data
    this.itineraryService.itinerary = this.itinerary

    console.log(this.resolvedItinerary.data)
    console.log(this.itinerary)
    console.log(this.itinerary.length)
    console.log(this.itinerary.length > 0)

    this.checkForItinerary = () => {
        this.itineraryFound = this.itinerary.length > 0
    }

    this.checkForItinerary()

    console.log("in itineraryController this.itineraryService.origin is")
    console.log(this.itineraryService.origin)
    console.log("in itineraryController this.itineraryService.destination is")
    console.log(this.itineraryService.destination)

    const refreshItineraries = () => {
        console.log("getting refreshed results")
        console.log('http://localhost:8090/flights/from/' + this.itineraryService.origin + '/to/' + this.itineraryService.destination)
        $http.get('http://localhost:8090/flights/from/' + this.itineraryService.origin + '/to/' + this.itineraryService.destination)
            .then( (resultVal) => {
                this.itinerary = resultVal.data
                this.checkForItinerary()
                console.dir(this.itinerary)
            })
    }

    this.itineraryService.interval = $interval(refreshItineraries, 500000)



}])