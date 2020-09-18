import React, { useState } from "react";
import PropTypes from "prop-types";
import { platform, IOS, Avatar, Button, Cell, Group, Text, Checkbox, Separator, FormLayout, Div, PanelHeader, Panel, PanelHeaderButton, File, Input, Textarea, Card, Placeholder } from "@vkontakte/vkui";
import {Icon20PodcastOutline, Icon56GalleryOutline, Icon24Back, Icon28ChevronBack} from "@vkontakte/icons";
import './styleCont/Addition.sass';
const osName = platform();

const Addition = (props) => {
  const [podcastUrl, setPodcastUrl] = useState(null);
  const [cover, setCover] = useState(null);
  const [podcastName, setPodcastName] = useState(""); 
  const [description, setDescription] = useState(""); 
  const nextEnabled = cover && podcastName?.length > 0 &&description?.length > 0;
  
  return (
    <Panel id={props.id}>
      <PanelHeader
        left={
          <PanelHeaderButton onClick={props.go} data-to="home">
            {osName === IOS ? <Icon28ChevronBack /> : <Icon24Back />}
          </PanelHeaderButton>
        }
      >
        Новый подкаст
    </PanelHeader>

      <FormLayout>
        <Div style={{ display: 'flex' }}>
          {podcastUrl ? <Avatar mode="app" size={72} src={podcastUrl} /> :
            <Card style={{ width: 72, height: 72 }}>
              <label htmlFor="file-input">
                <Icon56GalleryOutline width={32} height={32} fill="#3F8AE0" style={{ margin: 20 }} />
              </label>
              <File id="file-input" accept="image/*" style={{ display: 'none' }} onChange={(event) => {
                setPodcastUrl(URL.createObjectURL(event.target.files[0]))
              }} />
            </Card>
          }
          <FormLayout id="title-podcast" style={{ width: 'calc(100% - 2*12px)' }}>
            <Input top="Название" placeholder="Введите название подкаста" onChange={(e) => setPodcastName(e.target.value)} />
          </FormLayout>
        </Div>

        <Textarea top="Описание подкаста" onChange={(e) => setDescription(e.target.value)} />
        {cover ?
          <div style={{ paddingTop: 10 }}>
            <Group
              description="Вы можете добавить таймкоды и скорректировать подкаст в режиме редактирования">
              <Cell before={
                <Icon20PodcastOutline width={24} height={24} />
              } asideContent={<Text>59:16</Text>}>{cover.name}</Cell>
            </Group>
            <Button size="xl" mode="outline" style={{ marginBottom: 22 }} onClick={props.go} data-to="edit">Редактировать аудиозапись</Button>
          </div>
          : <Placeholder
            header="Загрузите Ваш подкаст"
            action={<File size="m" mode="outline" accept=".mp3" onChange={(event) => {
              setCover(event.target.files[0]);
            }}>Загрузить файл</File>}
          >
            Выберите готовый аудиофайл из вашего телефона и добавьте его
        </Placeholder>
        }

        <Separator />
        <Checkbox>Ненормативный контент</Checkbox>
        <Checkbox>Исключить эпизод из экспорта</Checkbox>
        <Checkbox>Трейлер подкаста</Checkbox>

        <Group description="При публикации записи с эпизодом, он становится доступным для всех пользователей">
          <Cell description="Всем пользователям" expandable onClick={props.go} data-to="additionMenu">
            Кому доступен данный подкаст
          </Cell>
        </Group>

        <Button size="xl" disabled={!nextEnabled} style={{ marginBottom: 12 }} onClick={props.go} data-to="preview">
          Далее
      </Button>

      </FormLayout>
    </Panel>
  )
};

Addition.propTypes = {
  id: PropTypes.string.isRequired,
  go: PropTypes.func.isRequired,
};

export default Addition;
