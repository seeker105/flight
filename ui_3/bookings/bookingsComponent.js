angular.module('flight').component('bookingsComponent', {
    templateUrl: './bookings/bookingsTemplate',
    controller: 'bookingsController',
    bindings: {
        resolvedConfirmation: '=',
        resolvedBookings: '='
    }

})