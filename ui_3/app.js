angular.module('flight', ['ui.router', 'ngMap']).config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider, $transition$) {

    $urlRouterProvider.otherwise('/signIn')

    let signInState = {
        name: 'signIn',
        url: '/signIn',
        component: 'signInComponent',
        onEnter: ['signInService', function (signInService) {
            signInService.clearSessionStorage();
            signInService.myStyle = { display: 'none' }
        }]
    }
    $stateProvider.state(signInState)

    let mainPageState = {
        name: 'master.main',
        url: '/main',
        component: 'mainComponent',
        resolve: {
            resolvedFlights: ['mainService', function (mainService) {
                return mainService.getFlights();
            }]
        },
        onExit: ['mainService', function (mainService) {
            mainService.stopInterval()
        }]
    }
    $stateProvider.state(mainPageState)

    let authenticationState = {
        name: 'authentication',
        url: '/authentication',
        redirectTo: (transition) => {
            let svc = transition.injector().get('signInService');
            return svc.authenticateUser()
        }
    }
    $stateProvider.state(authenticationState)

    let itineraryState = {
        name: 'master.itinerary',
        url: '/itinerary',
        component: 'itineraryComponent',
        resolve: {
            resolvedItinerary: ['mainService', function (mainService) {
                let result = mainService.searchForFlights()
                console.log("in app.js itineraryState Resolve block get result is:")
                console.log(result)
                return result
            }]
        },
        onExit: ['itineraryService', function (itineraryService) {
            itineraryService.stopInterval()
        }]
    }
    $stateProvider.state(itineraryState)

    // let itineraryState = {
    //     name: 'itinerary',
    //     url: '/itinerary',
    //     component: 'itineraryComponent',
    //     resolve: {
    //         resolvedItinerary: ['mainService', function(mainService) {
    // return mainService.searchForFlights()
    //         }]
    //     }
    // }
    // $stateProvider.state(itineraryState)

    let confirmationState = {
        name: 'confirmation',
        url: '/confirmation',
        component: 'confirmationComponent',
        resolve: {
            resolvedConfirmationData: ['itineraryService', function (itineraryService) {
                return itineraryService.bookFlights()
            }]
        }
    }
    $stateProvider.state(confirmationState)

    let bookingsState = {
        name: 'master.bookings',
        url: '/bookings',
        component: 'bookingsComponent',
        resolve: {
            resolvedConfirmationData: ['itineraryService', function (itineraryService) {
                // return itineraryService.bookFlights()
            }],
            resolvedBookings: ['itineraryService', function (itineraryService) {
                let temp = itineraryService.getBookings()
                console.log(temp)
                console.dir(temp)
                return temp
            }]
        }
    }
    $stateProvider.state(bookingsState)

    let registerState = {
        name: 'register',
        url: '/register',
        component: 'registerComponent'
    }
    $stateProvider.state(registerState)

    let createNewUserState = {
        name: 'userCreation',
        url: '/userCreation',
        redirectTo: (transition) => {
            let svc = transition.injector().get('registerService');
            return svc.registerNewUser().then((result) => {
                return 'main';
            });
        }
    }
    $stateProvider.state(createNewUserState)

    let masterState = {
        name: 'master',
        url: '/master',
        component: 'masterComponent'
    }
    $stateProvider.state(masterState)

    let showMapState = {
        name: 'master.showMap',
        url: '/showMap/{itineraryObj}',
        component: 'showMapComponent',
        resolve: {
            resolvedItinerary: ['$transition$', '$state', '$stateParams',
                function ($transition$, $state, $stateParams) {
                    // let dataString = $transition$.params().itineraryObj
                    // return JSON.parse(dataString)
                    console.log("first step")
                    console.dir($transition$.params().itineraryObj)
                    console.log("second step")
                    let result = JSON.parse($transition$.params().itineraryObj)
                    console.log(result)
                    return result
                }]
        },
        onExit: ['showMapService', function (showMapService) {
            showMapService.clearMap()
        }]
    }
    $stateProvider.state(showMapState)




}])







// let bookingProcessState = {
//     name: 'bookingProcess',
//     url: '/bookingProcess',
//     redirectTo: (transition) => {
//         let svc = transition.injector().get('itineraryService');
//         return svc.bookFlights()
//     }
// }
// $stateProvider.state(bookingProcessState)