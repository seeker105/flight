angular.module('flight').service('showMapService', [function(){

    this.clearMap = () => {
    //     // NgMap.getMap().then((map) => {
            for (let path of this.paths) {
                path.setMap(null)
            }
    //     // })
            for (let marker of this.markers){
                marker.setMap(null)
            }
    }




    
}])