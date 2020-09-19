import React, { useState, useEffect } from "react";
import bridge from "@vkontakte/vk-bridge";
import View from "@vkontakte/vkui/dist/components/View/View";
import "@vkontakte/vkui/dist/vkui.css";

import Feed from "./panels/Feed";
import Avatar from "./panels/Avatar";
import Map from "./panels/Map";
import CreatePost from "./panels/CreatePost";

const App = () => {
  const [activePanel, setActivePanel] = useState("createpost");
  const [fetchedUser, setUser] = useState(null);

  useEffect(() => {
    bridge.subscribe(({ detail: { type, data } }) => {
      if (type === "VKWebAppUpdateConfig") {
        const schemeAttribute = document.createAttribute("scheme");
        schemeAttribute.value = data.scheme ? data.scheme : "client_light";
        document.body.attributes.setNamedItem(schemeAttribute);
      }
    });
    async function fetchData() {
      const user = await bridge.send("VKWebAppGetUserInfo");
      setUser(user);
    }
    fetchData();
  }, []);

  const go = (e) => {
    setActivePanel(e.currentTarget.dataset.to);
  };

  return (
    <View activePanel={activePanel}>
      <Map id="map" fetchedUser={fetchedUser} go={go} />
      <CreatePost id="createpost" fetchedUser={fetchedUser} go={go} />
      <Feed id="feed" fetchedUser={fetchedUser} go={go} />
      <Avatar id="avatar" go={go} />
    </View>
  );
};

export default App;
