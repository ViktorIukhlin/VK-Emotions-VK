import React from "react";
import PropTypes from "prop-types";
import {
  Panel,
  PanelHeader,
  Radio,
  Title,
  IOS,
  PanelHeaderButton,
  platform,
  Div
} from "@vkontakte/vkui/";
import {Icon28ChevronBack, Icon24Back} from '@vkontakte/icons/';

const osName = platform();

const AdditionMenu = (props) => (
  <Panel id={props.id}>
    <PanelHeader
      left={<PanelHeaderButton onClick={props.go} data-to="addition">
        {osName === IOS ? <Icon28ChevronBack /> : <Icon24Back />}
      </PanelHeaderButton>}
    >
    </PanelHeader>
    <Div>
      <Title level="2" weight="medium">Кому доступен данный подкаст</Title>
    </Div>
    <Radio name="radio" defaultChecked>Всем пользователям</Radio>
    <Radio name="radio">Друзьям и друзьям друзей</Radio>
    <Radio name="radio">Всем друзьям</Radio>
    <Radio name="radio">Только мне</Radio>
  </Panel>
);

AdditionMenu.propTypes = {
  id: PropTypes.string.isRequired,
  go: PropTypes.func.isRequired
};

export default AdditionMenu;
