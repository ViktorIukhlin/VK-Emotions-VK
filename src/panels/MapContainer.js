import React, { Component } from "react";
import GoogleMapReact from "google-map-react";
import MyGreatPlace from "./MyGreatPlace.js";
import PropTypes from "prop-types";
import SearchCard from "./SearchCard.js";
import "./components/mapcontainer.css";

class SimpleMap extends Component {
  static propTypes = {
    center: PropTypes.array,
    zoom: PropTypes.number,
    greatPlaceCoords: PropTypes.any,
  };
  static defaultProps = {
    center: {
      lat: 59.95,
      lng: 30.33,
    },
    zoom: 11,
  };

  render() {
    return (
      <div className="rel">
        <div style={{ height: "78vh", width: "100%" }}>
          <GoogleMapReact
            bootstrapURLKeys={{
              key: "AIzaSyD_GWR9ykhQV0-A__pmmRHONv6-FeyBrkw",
            }}
            defaultCenter={this.props.center}
            defaultZoom={this.props.zoom}
          >
            <MyGreatPlace
              lat={59.955413}
              nazad={this.props.nazad}
              lng={30.337844}
              text={"😷 \n Карантин"}
            />
            <MyGreatPlace
              lat={59.975413}
              nazad={this.props.nazad}
              lng={30.337844}
              text={"🍂 \n Осень"}
            />
          </GoogleMapReact>
        </div>
        <SearchCard style={{ height: "22vh", width: "100%" }} />
      </div>
    );
  }
}

export default SimpleMap;
