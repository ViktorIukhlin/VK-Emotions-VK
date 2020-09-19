import React, { useState, useEffect } from "react";
import bridge from "@vkontakte/vk-bridge";
import Panel from "@vkontakte/vkui/dist/components/Panel/Panel";
import { View, PanelHeader, PanelHeaderButton, PanelHeaderContent, HorizontalScroll, Div, Button, FormLayout, Epic, Tabbar, TabbarItem } from "@vkontakte/vkui";
import { Icon16User, Icon24Dismiss, Icon48WritebarSend, Icon16Recent, Icon28PictureOutline, Icon28MusicOutline, Icon28MoreHorizontal, Icon28SettingsOutline, Icon28NewsfeedOutline, Icon28ChevronDownOutline } from "@vkontakte/icons/";
import Icon16DropDown from "@vkontakte/icons/dist/16/dropdown"; 

import "./components/Create_Post.css";
 
const CreatePost = ({ id, go, fetchedUser }) => {
  const [user, setUser] = useState(null);
  const [setText] = useState("");
 

  useEffect(() => {
    bridge.send("VKWebAppGetUserInfo", {}).then((user) => {
      setUser({ user });
    });
  });

  return (
    <Panel id={id}>
      <View activePanel="context">
        <Panel id="context" style={{ position: "relative", height: "92vh" }}>
          <PanelHeader
            left={<Icon24Dismiss />}
            right={
              <PanelHeaderButton
                style={{ marginRight: "90px" }}
                onClick={go}
                data-to="feed"
              >
                <Icon48WritebarSend />
              </PanelHeaderButton>
            }
          >
            {user ? (
              <PanelHeaderContent>{user.first_name}</PanelHeaderContent>
            ) : (
              <PanelHeaderContent>{"VK Mini App"}</PanelHeaderContent>
            )}
          </PanelHeader>
          <Div>
            <FormLayout>
              <textarea
                rows="24"
                style={{ overflow: "hidden" }}
                onChange={(e) => {
                  setText(e.target.value);
                }}
              ></textarea>
            </FormLayout>
          </Div>
          <Div style={{ position: "sticky", bottom: 10 }}>
            <HorizontalScroll>
              <Div
                style={{
                  display: "flex",
                }}
              >
                <Button
                  className="outline_Button"
                  before={<Icon16User />}
                  after={<Icon16DropDown />}
                  mode="outline"
                >
                  Видно всем
                </Button>
                <Button
                  className="outline_Button"
                  before={<Icon16Recent />}
                  after={<Icon16DropDown />}
                  mode="outline"
                >
                  Сейчас
                </Button>
                <Button
                  className="outline_Button"
                  after={<Icon16DropDown />}
                  mode="outline"
                >
                  Настроение
                </Button>
                <Button
                  className="outline_Button"
                  after={<Icon16DropDown />}
                  mode="outline"
                >
                  Тематика
                </Button>
              </Div>
            </HorizontalScroll>
          </Div>
          <Epic
            tabbar={
              <Tabbar>
                <TabbarItem data-story="feed">
                  <Icon28NewsfeedOutline />
                </TabbarItem>
                <TabbarItem data-story="services">
                  <Icon28PictureOutline />
                </TabbarItem>
                <TabbarItem data-story="messages">
                  <Icon28MusicOutline />
                </TabbarItem>
                <TabbarItem data-story="clips">
                  <Icon28MoreHorizontal />
                </TabbarItem>
                <TabbarItem></TabbarItem>
                <TabbarItem data-story="profile">
                  <Icon28SettingsOutline />
                </TabbarItem>
                <TabbarItem data-story="profile">
                  <Icon28ChevronDownOutline />
                </TabbarItem>
              </Tabbar>
            }
          ></Epic>
        </Panel>
      </View>
    </Panel>
  );
};
export default CreatePost;
