angular.module('flight').controller('showMapController', ['showMapService', 'NgMap', function (showMapService, NgMap) {


    this.showMapService = showMapService
    this.itinerary = this.resolvedItinerary
    console.log("In map controller")
    console.log(this.itinerary)
    console.dir(this.itinerary)

    const getPosition = (cityName) => {
        let result;
        switch (cityName.toUpperCase()) {
            case 'MEMPHIS':
                result = {
                    lat: 35.1495,
                    lng: -90.0490
                }
                break;
            case 'NASHVILLE':
                result = {
                    lat: 36.1627,
                    lng: -86.7816
                }
                break;
            case 'KNOXVILLE':
                result = {
                    lat: 35.9606,
                    lng: -83.9207
                }
                break;
            default:
                // CHATTANOOGA
                result = {
                    lat: 35.0456,
                    lng: -85.3097
                }
        }
        return result
    }

    // this.itinerary = [
    //     {
    //         "id": 0,
    //         "origin": "KNOXVILLE",
    //         "destination": "MEMPHIS",
    //         "flightTime": 2,
    //         "itinerary": null,
    //         "offset": 8
    //     },
    //     {
    //         "id": 0,
    //         "origin": "MEMPHIS",
    //         "destination": "NASHVILLE",
    //         "flightTime": 2,
    //         "itinerary": null,
    //         "offset": 8
    //     },
    //     {
    //         "id": 0,
    //         "origin": "NASHVILLE",
    //         "destination": "CHATTANOOGA",
    //         "flightTime": 2,
    //         "itinerary": null,
    //         "offset": 8
    //     }
    // ]
    // console.log("itin changed to:")
    // console.log(this.itinerary)

    this.mapLegend = [
        {
            color: '#008000',
            text: 'Flight 1'
        },
        {
            color: '#0000FF',
            text: 'Flight 2'
        },
        {
            color: '#FF0000',
            text: 'Flight 3'
        },
        {
            color: '#FFFF00',
            text: 'Flight 4'
        },
        {
            color: '#800080',
            text: 'Flight 5'
        }
    ]

    this.xCoord = 35.5175
    this.yCoord = -86.5804

    const memphis = {
        lat: 35.1495,
        lng: -90.0490
    }

    const nashville = {
        lat: 36.1627,
        lng: -86.7816
    }

    const knoxville = {
        lat: 35.9606,
        lng: -83.9207
    }

    const chattanooga = {
        lat: 35.0456,
        lng: -85.3097
    }


    NgMap.getMap().then((map) => {
        this.legendArray = []
        this.showMapService.markers = []
        // map.fitBounds(bounds);  
        // let memphisMarker = new google.maps.Marker({
        //     position: {
        //         lat: 35.1495,
        //         lng: -90.0490
        //     },
        //     map: map
        // });

        // let nashvilleMarker = new google.maps.Marker({
        //     position: {
        //         lat: 36.1627,
        //         lng: -86.7816
        //     },
        //     map: map
        // });

        // let flightPath1 = new google.maps.Polyline({
        //     path: [memphis, nashville, knoxville, chattanooga],
        //     geodesic: true,
        //     strokeColor: '#FF0000',
        //     strokeOpacity: 1.0,
        //     strokeWeight: 2
        // });

        // let flightPath2 = new google.maps.Polyline({
        //     path: [getPosition('MEMPHIS'), getPosition('CHATTANOOGA')],
        //     geodesic: true,
        //     strokeColor: '#008000',
        //     strokeOpacity: 1.0,
        //     strokeWeight: 2
        // });

        // flightPath1.setMap(map)
        // flightPath2.setMap(map)
        console.log(map)
        console.log(this.itinerary)
        this.paths = this.itinerary.map(
            (flightValue, index) => {
                this.legendArray.push({
                    color: this.mapLegend[index].color,
                    fromToString: flightValue.origin + ' to ' + flightValue.destination,
                    flightTimeString:  'Flight Time: ' + flightValue.flightTime,
                    departureTime: flightValue.offset,
                    arrivalTime: flightValue.offset + flightValue.flightTime,
                    layoverPresent: false
                })
                // let y = '5'
                // new google.maps.Marker({
                //         position: getPosition(flightValue.origin),
                //         label: y,
                //         map: map
                //     });
                // new google.maps.Marker({
                //         position: getPosition(flightValue.destination),
                //         map: map
                //     });
                return new google.maps.Polyline({
                    path: [getPosition(flightValue.origin), getPosition(flightValue.destination)],
                    geodesic: true,
                    // strokeColor: '#FF0000',
                    strokeColor: this.mapLegend[index].color,
                    strokeOpacity: 1.0,
                    strokeWeight: 2
                });
            }
        )
        this.showMapService.markers.push(new google.maps.Marker({
            position: getPosition(this.itinerary[0].origin),
            label: '1',
            title: 'Origin City',
            map: map
        }))

        this.legendArray[0].segmentNumbers = '1 -> 2'
        if (this.itinerary.length < 2) {
            this.showMapService.markers.push(new google.maps.Marker({
                position: getPosition(this.itinerary[0].destination),
                label: '2',
                title: 'Destination City',
                map: map
            }))
        } else {
            let x = 1
            let layoverTimeString
            while (x < this.itinerary.length) {
                console.log("while iter")
                this.legendArray[x-1].layoverPresent = true
                layoverTimeString = (this.itinerary[x].offset - (this.itinerary[x-1].offset + this.itinerary[x-1].flightTime)).toString()
                this.legendArray[x-1].layoverText = this.itinerary[x].origin + ' Layover Time: ' + layoverTimeString
                this.legendArray[x].segmentNumbers = (x+1).toString() + ' -> ' + (x+2).toString()
                this.showMapService.markers.push(new google.maps.Marker({
                    position: getPosition(this.itinerary[x].origin),
                    label: (x+1).toString(),
                    title: 'Layover Time in ' + this.itinerary[x].origin + ' = ' + layoverTimeString,
                    map: map
                }))
                console.log(this.itinerary[x-1])
                console.log(this.itinerary[x])
                x++
            }
            this.showMapService.markers.push(new google.maps.Marker({
                position: getPosition(this.itinerary[x-1].destination),
                label: (x+1).toString(),
                title: 'Destination City',
                map: map
            }))
        }
        this.showMapService.paths = this.paths
        for (let path of this.paths) {
            path.setMap(map)
        }
    });



}])