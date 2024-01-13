import Box from "../../components/Box";
import Input from "../../components/SingleForms/Input";
import { SET_NEW_TASK } from "../../contexts/Provider/AppActions";
import { AppContext } from "../../contexts/Provider/AppProvider";
import If from "../../dist/If";
import COLOR from "../../styles/colors";
import { STATUS } from "../../utils/constants";
import {
  ButtonForm,
  Container,
  Content,
  Label,
  LabelDropdown,
  LabelFile,
  SectionInput,
  Title,
  stylesDropdown,
} from "./styles";
import { isEmpty } from "lodash";
import React, { useCallback, useContext, useState } from "react";
import {
  ActivityIndicator,
  KeyboardAvoidingView,
  ScrollView,
} from "react-native";
import DocumentPicker, {
  DocumentPickerResponse,
} from "react-native-document-picker";
import { showMessage } from "react-native-flash-message";
import RNFS from "react-native-fs";
import SelectDropdown from "react-native-select-dropdown";
import uuid from "react-native-uuid";
import FontAwesome from "react-native-vector-icons/FontAwesome";

export const NewTask = () => {
  const appContext = useContext(AppContext);

  if (!appContext) {
    return null;
  }

  const { state, dispatch } = appContext;

  const [loading, setLoading] = useState(false);
  const [title, setTitle] = useState<string>("");
  const [description, setDescription] = useState<string>("");
  const [dropdownPix, setDropdownPix] = useState<string>(STATUS[0]);
  const [file, setFile] = useState<string | null>(null);
  const [fileName, setFileName] = useState<string | null>(null);

  const handleDocumentSelection = useCallback(async () => {
    try {
      setLoading(true);
      const response: DocumentPickerResponse[] = await DocumentPicker.pick({
        type: [DocumentPicker.types.images],
      });
      if (response.length > 0) {
        const content = await RNFS.readFile(response[0].uri, "base64");
        const nameFile = response[0].name;
        setFile(content);
        setFileName(nameFile);
        setLoading(false);
      }
    } catch (err) {
      setLoading(false);
      console.warn(err);
    }
  }, []);

  const handleSubmit = useCallback(() => {
    setLoading(true);
    const data = {
      id: uuid.v4(),
      title,
      description,
      status: dropdownPix === "Ativo",
      fileName: fileName,
      uri: file,
    };

    if (!data.title || !data.description || !dropdownPix) {
      showMessage({
        type: "danger",
        message: "Erro",
        description: "Por favor, preencha todos os campos.",
      });
      setLoading(false);
      return;
    }

    dispatch({
      type: SET_NEW_TASK,
      payload: {
        tasks: [...(state.tasks || []), data],
      },
    });

    showMessage({
      type: "success",
      message: "Sucesso",
      description: "Nova tarefa gravada com sucesso!",
    });
    setLoading(false);
    setTitle("");
    setDescription("");
    setDropdownPix(STATUS[0]);
  }, [title, description, dropdownPix, file, fileName, state, dispatch]);

  return (
    <ScrollView>
      <KeyboardAvoidingView behavior="padding" style={{ flex: 1 }}>
        <Container>
          <Content>
            <Title>Nova task</Title>
            <SectionInput>
              <Input
                label="Título"
                value={title}
                placeholderTextColor={COLOR.BLACK_PLACEHOLDER}
                onChange={setTitle}
                autoCapitalize="none"
              />
            </SectionInput>

            <SectionInput>
              <Input
                label="Descrição"
                value={description}
                placeholderTextColor={COLOR.BLACK_PLACEHOLDER}
                onChange={setDescription}
                autoCapitalize="none"
              />
            </SectionInput>

            <SectionInput>
              <LabelDropdown>Status</LabelDropdown>
              <SelectDropdown
                data={STATUS}
                defaultValue={dropdownPix}
                dropdownStyle={stylesDropdown.dropdown2DropdownStyle}
                rowStyle={stylesDropdown.dropdown2RowStyle}
                rowTextStyle={stylesDropdown.dropdown2RowTxtStyle}
                buttonStyle={{
                  width: "100%",
                  backgroundColor: "white",
                  borderColor: "#DAE1E8",
                  borderWidth: 2,
                  borderRadius: 6,
                }}
                renderDropdownIcon={(isOpened) => {
                  return (
                    <FontAwesome
                      name={isOpened ? "chevron-up" : "chevron-down"}
                      color={"#444"}
                      size={18}
                    />
                  );
                }}
                dropdownIconPosition={"right"}
                buttonTextStyle={{
                  fontSize: 15,
                  textAlign: "left",
                }}
                onSelect={async (selectedItem) => {
                  setDropdownPix(selectedItem);
                }}
              />
            </SectionInput>

            <Box margin={5}>
              <If condition={!isEmpty(loading)}>
                <ActivityIndicator size="small" color={COLOR.LOADING} />
              </If>
            </Box>
            <ButtonForm onPress={handleDocumentSelection}>
              <Label>Enviar arquivo 📑</Label>
            </ButtonForm>
            <If condition={!isEmpty(fileName)}>
              <LabelFile>{fileName}</LabelFile>
            </If>
            <ButtonForm onPress={handleSubmit}>
              <Label>Confirmar</Label>
            </ButtonForm>
          </Content>
        </Container>
      </KeyboardAvoidingView>
    </ScrollView>
  );
};
