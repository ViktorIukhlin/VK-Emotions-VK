import React, { useState, useEffect } from "react";
import bridge from "@vkontakte/vk-bridge";
import View from "@vkontakte/vkui/dist/components/View/View";
import "@vkontakte/vkui/dist/vkui.css";

import Home from "./panels/Home";
import Addition from "./panels/Addition";
import AdditionMenu from "./panels/AdditionMenu";
import Edit from "./panels/Edit";
import Preview from "./panels/Preview";
import Completed from "./panels/Completed";

import state from "./panels/state";

const App = () => {
  const [activePanel, setActivePanel] = useState("home");

  useEffect(() => {
    bridge.subscribe(({ detail: { type, data } }) => {
      if (type === "VKWebAppUpdateConfig") {
        const schemeAttribute = document.createAttribute("scheme");
        schemeAttribute.value = data.scheme ? data.scheme : "client_light";
        document.body.attributes.setNamedItem(schemeAttribute);
      }
    });
  }, []);

  const go = (e) => {
    setActivePanel(e.currentTarget.dataset.to);
  };

  return (
    <View activePanel={activePanel}>
      <Home id="home" go={go} />
      <Addition id="addition" go={go} state={state} />
      <AdditionMenu id="additionMenu" go={go} />
      <Edit id="edit" go={go} />
      <Preview id="preview" go={go} />
      <Completed id="completed" go={go} />
    </View>
  );
};

export default App;
