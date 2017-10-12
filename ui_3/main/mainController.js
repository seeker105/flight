angular.module('flight').controller('mainController', ['mainService', 'NgMap', '$http', '$interval', 'itineraryService', function (mainService, NgMap, $http, $interval, itineraryService) {

    this.mainService = mainService
    this.flights = this.resolvedFlights.data
    this.itineraryService = itineraryService
    // this.mainService.flights = this.flights
    // this.flights = mainService.flights

    const refreshFlights = () => {
        console.log("Refreshing Flights")
        $http.get('http://localhost:8090/flights')
            .then( (resultVal) => {
                this.flights = resultVal.data
            })
    }

    this.mainService.interval = $interval(refreshFlights, 5000)

    console.log(this.flights)

    // this.xCoord = 35.5175
    // this.yCoord = -86.5804

    // const memphis = {
    //     lat: 35.1495,
    //     lng: -90.0490
    // }

    // const nashville = {
    //     lat: 36.1627,
    //     lng: -86.7816
    // }

    // const knoxville = {
    //     lat: 35.9606,
    //     lng: -83.9207
    // }

    // const chattanooga = {
    //     lat: 35.0456,
    //     lng: -85.3097
    // }


    // NgMap.getMap().then(function (map) {
    //     let memphisMarker = new google.maps.Marker({
    //         position: {
    //             lat: 35.1495,
    //             lng: -90.0490
    //         },
    //         map: map
    //     });

    //     let nashvilleMarker = new google.maps.Marker({
    //         position: {
    //             lat: 36.1627,
    //             lng: -86.7816
    //         },
    //         map: map
    //     });

    //     let flightPath1 = new google.maps.Polyline({
    //         path: [memphis, nashville, knoxville, chattanooga],
    //         geodesic: true,
    //         strokeColor: '#FF0000',
    //         strokeOpacity: 1.0,
    //         strokeWeight: 2
    //     });

    //     flightPath1.setMap(map)
    // });




}])