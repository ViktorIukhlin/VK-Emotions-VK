import React from "react";
import PropTypes from "prop-types";
import { platform, IOS, Group, Button, FixedLayout, Div, Link, Header, Cell, Avatar, Panel, PanelHeader, PanelHeaderButton } from "@vkontakte/vkui";
import Icon28ChevronBack from "@vkontakte/icons/dist/28/chevron_back";
import Icon24Back from "@vkontakte/icons/dist/24/back";

import './styleCont/Preview.sass';
const osName = platform();

const Preview = (props) => (
  <Panel id={props.id}>
    <PanelHeader
      left={
        <PanelHeaderButton onClick={props.go} data-to="addition">
          {osName === IOS ? <Icon28ChevronBack /> : <Icon24Back />}
        </PanelHeaderButton>
      }
    >
      Новый подкаст
    </PanelHeader>

    <Group separator="show">
      <Cell
        before={<Avatar mode="app" size={72} src={require("./styleCont/papadog.svg")} />}
        size="l"
        bottomContent={
          <div style={{ display: 'flex', flexDirection: 'column' }}>
            <Link href='#'>ПараDogs</Link>
            <span style={{ color: '#818C99' }}>Длительность: 59:16</span>
          </div>
        }
      >
        Подкаст прекрасных людей
            </Cell>
    </Group>
    <Group header={<Header mode="primary">Описание</Header>} separator="show">
      <Div style={{ paddingTop: 0, paddingBottom: 0 }}>
        Подкаст, который рассказывает про то, как много в мире прекрасных вещей, которые можно совершить, а также сколько людей, которые могут помочь вам в реализации ваших заветных мечт.
            </Div>
    </Group>
    <Group header={<Header mode="primary">Содержание</Header>} separator="show">
      <Div id="link-list" style={{ paddingTop: 0, paddingBottom: 0, display: 'flex', flexDirection: 'column' }}>
        <div>
          <Link href="#">05:41</Link> - Начало обсуждения
        </div>
        <div>
          <Link href="#">15:23</Link> - Основная тема подкаста
        </div>
        <div>
          <Link href="#">25:37</Link> - Разговор с гостем
        </div>
        <div>
          <Link href="#">43:15</Link> - Рекламная интеграция
        </div>
        <div>
          <Link href="#">47:00</Link> - Вопросы от зрителей
        </div>
        <div>
          <Link href="#">58:00</Link> - Подведение итогов розыгрыша
        </div>
      </Div>
    </Group>
    <Div>
      <Button size="xl" onClick={props.go} data-to="completed" stretched>
        Опубликовать подкаст
      </Button>
    </Div>
  </Panel>
);

Preview.propTypes = {
  id: PropTypes.string.isRequired,
  go: PropTypes.func.isRequired,
};

export default Preview;
