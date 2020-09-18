import React from "react";
import { File } from "@vkontakte/vkui";
import { Icon24DismissOverlay, Icon28PictureOutline } from "@vkontakte/icons";

const defaultFileStyle = {
  background: "none",
  padding: "50px 0",
  position: "relative",
  overflow: "hidden",
};

const defaultImageStyle = {
  position: "absolute",
  left: "0",
  top: "0",
  width: "100%",
  height: "100%",
  objectFit: "cover",
};

export default function UploadPreview() {
  const previewRef = React.useRef(null);
  const [uploaded, setUploaded] = React.useState(false);

  const preview = (e) => {
    e.persist();
    const input = e.target;

    if (!input.files || !input.files[0]) return;

    const reader = new FileReader();

    reader.onload = function (e) {
      previewRef.current.src = e.target.result;
      setUploaded(true);
    };

    reader.readAsDataURL(input.files[0]); // convert to base64 string
  };

  const reset = () => {
    previewRef.current.src = "";
    setUploaded(false);
  };

  const style = uploaded
    ? defaultFileStyle
    : { ...defaultFileStyle, ...{ border: "1.5px dashed" } };

  return (
    <File
      before={<Icon28PictureOutline />}
      controlSize="xl"
      mode="secondary"
      align="center"
      stretched
      onChange={(e) => preview(e)}
      style={style}
    >
      <img style={defaultImageStyle} ref={previewRef} alt="" />
      {uploaded && (
        <Icon24DismissOverlay
          style={{ position: "absolute", right: "10px", top: "10px" }}
          onClick={reset}
        />
      )}
      Загрузить обложку
    </File>
  );
}

