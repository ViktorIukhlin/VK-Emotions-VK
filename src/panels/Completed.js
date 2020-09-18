import React from "react";
import PropTypes from "prop-types";
import { Panel, PanelHeader,  platform, Placeholder, Button } from "@vkontakte/vkui";
import {Icon56CheckCircleOutline} from "@vkontakte/icons";

const Completed = (props) => (
  <Panel id={props.id}>
    <PanelHeader>
      Подкасты
  </PanelHeader>
    <Placeholder
      icon={<Icon56CheckCircleOutline fill="#3F8AE0" />}
      header="Подкаст добавлен"
      action={<Button size="l" onClick={props.go} data-to="home">Перейти к списку подкастов</Button>}
      stretched
    >
      Раскажите своим подписчикам о новом подкасте, чтобы получить больше слушателей.
  </Placeholder>
  </Panel>
);

Completed.propTypes = {
  id: PropTypes.string.isRequired,
  go: PropTypes.func.isRequired,
};

export default Completed;
