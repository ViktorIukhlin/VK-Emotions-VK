import React from "react";
import PropTypes from "prop-types";
import { Card, Div, platform, IOS, Panel, PanelHeader, PanelHeaderButton, Group, Cell, Header, Gallery} from "@vkontakte/vkui";
import { Icon28ChevronBack, Icon24Back, Icon28AddCircleOutline } from "@vkontakte/icons";

const osName = platform();

const Edit = (props) => (
  <Panel id={props.id}>
    <PanelHeader
      left={<PanelHeaderButton onClick={props.go} data-to="addition" >
        {osName === IOS ? <Icon28ChevronBack /> : <Icon24Back />}
      </PanelHeaderButton>}
    >
      Редактирование
	</PanelHeader>
    <Div>
      <Card style={{ display: 'flex', flexDirection: 'column' }}>
        <div style={{ height: 182, backgroundImage: `url(${require("./styleCont/zag.svg")})`, backgroundRepeat: 'no-repeat' }}/>
      </Card>
    </Div>
    <Group
      header={<Header mode="secondary">ТАЙМКОДЫ</Header>}
      description="Отметки времени с названием темы. Позволяют слушателям легче путешествовать по подкасту."
    >
      <Cell before={<Icon28AddCircleOutline width={22} height={22} />}>
        Добавить таймкод
		  </Cell>
    </Group>
  </Panel>
);

Edit.propTypes = {
  id: PropTypes.string.isRequired,
  go: PropTypes.func.isRequired
};

export default Edit;
