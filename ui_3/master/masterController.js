angular.module('flight').controller('masterController', ['masterService', function(masterService){
    
    this.masterService = masterService

    this.getUserName = () => {
        return sessionStorage.getItem('userLogin')
    }

}])