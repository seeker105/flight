angular.module('flight').service('registerService',['$http', function($http){
    
    this.registerNewUser = () => {
        if(!this.user || !this.user.credentials || !this.user.credentials.userLogin || !this.user.credentials.password || !this.user.profile || !this.user.profile.email){
            alert('Are you Serious!');
            return
        }
            sessionStorage.setItem('userLogin',this.user.credentials.userLogin);
            sessionStorage.setItem('password',this.user.credentials.password);
            return $http.post('http://localhost:8090/users', this.user);
        
    }
}])