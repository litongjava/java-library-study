package com.class01;

import cn.hutool.core.util.StrUtil;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("restriction")
@Slf4j
public class BorderPaneApp extends Application {

  private Stage state;
  private SimpleStringProperty statsProperty;
  private TextArea textArea;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void init() throws Exception {
    log.info("init");
    super.init();
  }

  @Override
  public void stop() throws Exception {
    log.info("stop");
    super.stop();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    log.info("start");
    state = primaryStage;
    state.setTitle("Main - JavaFX");

    BorderPane layout = new BorderPane();

    ToolBar footerBar = getFooterBar();
    layout.setBottom(footerBar);
    HBox toolBar = getToolBar();
    layout.setTop(toolBar);

    textArea = getTextArea();
    layout.setCenter(textArea);

    Scene scene = new Scene(layout, 300, 250);

    state.setScene(scene);
    state.show();
  }

  private TextArea getTextArea() {
    TextArea textArea = new TextArea();
    textArea.setId("ocrTextArea");
    textArea.setWrapText(true);
    BorderStroke borderStroke = new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT);
    textArea.setBorder(new Border(borderStroke));
    textArea.setFont(Font.font("Arial", FontPosture.REGULAR, 14));

    textArea.textProperty().addListener((observable, oldValue, newValue) -> {
      log.info("observable:{}", observable);
      this.statsProperty.set("总字数：" + newValue.length());
    });
    return textArea;
  }

  private HBox getToolBar() {
    Runnable screenshotRunnable = () -> log.info("screenshot");

    Window thatStage = state;
    Runnable openImageRunnable = () -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Please Select Image File");
      fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
      File selectedFile = fileChooser.showOpenDialog(thatStage);
      if (selectedFile == null || !selectedFile.isFile()) {
        return;
      }
      log.info("path:{}", selectedFile.getAbsolutePath());
    };
    HBox topBar = new HBox(
      createButton("screenshotBtn", 20, screenshotRunnable, "截图"),
      createButton("openImageBtn", 20, openImageRunnable, "打开"),
      createButton("copyBtn", 20, this::copyText, "复制"),
      createButton("pasteBtn", 20, this::pasteText, "粘贴")
    );
    topBar.setId("topBar");
    topBar.setMinHeight(40);
    topBar.setSpacing(8);
    topBar.setPadding(new Insets(6, 8, 6, 8));
    return topBar;
  }

  private void copyText() {
    String text = textArea.getSelectedText();
    if (StrUtil.isBlank(text)) {
      text = textArea.getText();
    }
    if (StrUtil.isBlank(text)) {
      return;
    }
    Map<DataFormat, Object> data = new HashMap<>();
    data.put(DataFormat.PLAIN_TEXT, text);
    Clipboard.getSystemClipboard().setContent(data);
  }


  private void pasteText() {
    String text = Clipboard.getSystemClipboard().getString();
    if (StrUtil.isBlank(text)) {
      return;
    }
    String oldText = textArea.getText();
    textArea.setText(oldText + text);
  }


  private ToolBar getFooterBar() {
    //label
    Label statsLabel = new Label();
    statsProperty = new SimpleStringProperty("总字数：0");
    statsLabel.textProperty().bind(statsProperty);

    ToolBar footerBar = new ToolBar();
    footerBar.setId("statsToolbar");
    footerBar.getItems().add(statsLabel);
    return footerBar;
  }

  public static Button createButton(String id, int size, Runnable action, String toolTip) {
    javafx.scene.control.Button button = new Button(toolTip);
    initButton(button, id, size, action, toolTip);
    return button;
  }

  private static void initButton(ButtonBase button, String id, int size, Runnable action, String toolTip) {
    button.setId(id);
    button.setOnAction(evt -> action.run());
    button.setMinSize(size, size);
    if (toolTip != null) {
      button.setTooltip(new Tooltip(toolTip));
    }
  }
}