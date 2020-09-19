import React from "react";
import { platform, Group, Card, Search, CardGrid, Avatar, HorizontalScroll, } from "@vkontakte/vkui";

const SearchCard = () => {
  const itemStyle = {
    flexShrink: 0,
    width: 80,
    height: 94,
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    fontSize: 12,
  };
  return (
    <CardGrid>
      <Card size="l" mode="outline">
        <div>
          <Group>
            <Search placeholder="Поиск по теме и настроению" />
            <HorizontalScroll>
              <div style={{ display: "flex" }}>
                <div style={{ ...itemStyle, paddingLeft: 4 }}>
                  <Avatar size={64} style={{ marginBottom: 8 }}>
                    <span style={{ fontSize: "24px" }} role="img">
                      🍿
                    </span>
                  </Avatar>
                  Фильмы
                </div>
                <div style={itemStyle}>
                  <Avatar size={64} style={{ marginBottom: 8 }}>
                    <span style={{ fontSize: "24px" }} role="img">
                      🎧
                    </span>
                  </Avatar>
                  Музыка
                </div>
                <div style={itemStyle}>
                  <Avatar size={64} style={{ marginBottom: 8 }}>
                    <span style={{ fontSize: "24px" }} role="img">
                      🍂
                    </span>
                  </Avatar>
                  Осень
                </div>
                <div style={itemStyle}>
                  <Avatar size={64} style={{ marginBottom: 8 }}>
                    <span style={{ fontSize: "24px" }} role="img">
                      👔
                    </span>
                  </Avatar>
                  Работа
                </div>
                <div style={itemStyle}>
                  <Avatar size={64} style={{ marginBottom: 8 }}>
                    <span style={{ fontSize: "24px" }} role="img">
                      😷{" "}
                    </span>
                  </Avatar>
                  Карантин
                </div>
              </div>
            </HorizontalScroll>
          </Group>
        </div>
      </Card>
    </CardGrid>
  );
};

export default SearchCard;
