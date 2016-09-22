import templateUrl from './map.component.html'

/* @ngInject */
class MapController {
  zoom = 3
  center = [27.6648, -81.5158]
  markers = []
  paths = []

  constructor ($map, locations) {
    this.$map = $map

    // add markers from an angular constant
    const { jacksonville, miami, tallahassee } = locations
    const markers = [jacksonville, miami, tallahassee]

    markers.forEach(marker => this.addMarker(marker))

    // add paths manually
    const paths = [
      [jacksonville, miami, '#CC0099'],
      [miami, tallahassee, '#AA1100']
    ]

    paths.forEach(args => this.addPath(...args))

    // add path from webservice
    $map.getMarkerByCityName('Orlando')
      .then(orlando => {
        this.addPath(tallahassee, orlando, '#FF3388')
      })
  }

  addMarker ({ latitude, longitude }) {
    this.markers.push({
      position: `[${latitude}, ${longitude}]`
    })
  }

  addPath (a, b, color) {
    this.paths.push({
      path: `[[${a.latitude}, ${a.longitude}], [${b.latitude}, ${b.longitude}]]`,
      strokeColor: color,
      strokeOpacity: 1.0,
      strokeWeight: 3,
      geodesic: true
    })
  }

}

export default {
  templateUrl,
  controller: MapController,
  controllerAs: '$mapCtrl'
}
