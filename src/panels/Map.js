import React from "react";
import PropTypes from "prop-types";
import { Panel } from "@vkontakte/vkui";
import SimpleMap from "./MapContainer";
 
const Map = (props) => (
  <Panel id={props.id}>
    <SimpleMap nazad={props} />
  </Panel>
);

Map.propTypes = {
  id: PropTypes.string.isRequired,
  go: PropTypes.func.isRequired,
};

export default Map;
