import React from "react";
import PropTypes from "prop-types";
import { Placeholder, Button, PanelHeader, Panel } from "@vkontakte/vkui";
import Icon28AddCircleOutline from '@vkontakte/icons/dist/28/add_circle_outline';

const Home = ({ id, go }) => (
  <Panel id={id}>
    <PanelHeader>Подкасты</PanelHeader>
    <Placeholder
      stretched
      icon={  <Icon28AddCircleOutline style={{ color: '#99A2AD' }} width={56} height={56}/>  }
      header={ 'Добавьте первый подкаст' }
      action={  <Button size="l" onClick={go} data-to="addition"> Добавить подкаст </Button>  }
      > 
      Добавляйте, редактируйте и делитесь
      <br />
      подкастами вашего сообщества.
    </Placeholder>
  </Panel>
);

Home.propTypes = {
  id: PropTypes.string.isRequired,
  go: PropTypes.func.isRequired,
};

export default Home;
