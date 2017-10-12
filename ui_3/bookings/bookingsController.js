angular.module('flight').controller('bookingsController', ['bookingsService', function(bookingsService){
    
    // const parseBookings = () => {
    //     return this.resolvedBookings.data.map(function(element){
    //         console.log(element)
    //         return JSON.parse(element)
    //     }).reverse()
    // }
    
    const parseBookings = () => {
        return bookingsData = this.resolvedBookings.data.map(function(element){
            console.log(element)
            return JSON.parse(element)
        }).reverse()

    }



    this.bookingsService = bookingsService
    this.bookings = parseBookings()

    this.getItineraryString = (itineraryObj) => {
        return JSON.stringify(itineraryObj)
    }

    this.calculateFlightTime = (itinerary) => {
        let flightTimeSum = 0
        for (let flight of itinerary){
            flightTimeSum = flightTimeSum + flight.flightTime
        }
        return flightTimeSum
    }

    this.calculateLayoverTime = (itinerary) => {
        if (itinerary.length < 2){
            return 0
        }
        let layoverTimeSum = 0
        let layoverTime
        let x = 1
        while (x < itinerary.length){
            layoverTime = itinerary[x].offset - (itinerary[x-1].offset + itinerary[x-1].flightTime) 
            layoverTimeSum = layoverTimeSum + layoverTime
            x++
        }
        return layoverTimeSum
    }


}])