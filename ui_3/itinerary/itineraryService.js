angular.module('flight').service('itineraryService', ['$http', '$interval', function ($http, $interval) {

    
    this.getBookings = () => {
        if (sessionStorage.getItem('userLogin')) {
            return $http.post('http://localhost:8090/flights/getBookings/' + sessionStorage.getItem('userLogin'), JSON.stringify(this.itinerary))
        } else {
            return $http.post('http://localhost:8090/flights/getBookings/c', JSON.stringify(this.itinerary))
        }
    }
    
    this.bookFlights = () => {
        let userName
        if (sessionStorage.getItem('userLogin')) {
            userName = sessionStorage.getItem('userLogin')
        } else {
            userName = 'c'
        }
        $http.post('http://localhost:8090/flights/book/' + userName, JSON.stringify(this.itinerary))




        // return $http.post('http://localhost:8090/flights/book/' + userName, JSON.stringify(this.itinerary))
        //     .then( (result) => {
        //         if (result.data){
        //             return 'confirmation'
        //         } else {
        //             alert('Oops! Something went wrong')
        //             return 'main'
        //         }
        //     })
    }


    this.stopInterval = () => {
        $interval.cancel(this.interval)
    }


}])