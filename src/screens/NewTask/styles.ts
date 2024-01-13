import COLOR from "../../styles/colors";
import { StyleSheet } from "react-native";
import styled from "styled-components/native";

export const Container = styled.SafeAreaView`
  padding: 20px;
`;

export const Content = styled.View`
  background-color: ${COLOR.WHITE};
  padding: 10px;
  height: 100%;
  border-radius: 10px;
`;

export const Title = styled.Text`
  color: ${COLOR.SECONDARY_GREEN};
  text-align: center;
  font-size: 26px;
  font-family: Inter;
  font-style: normal;
  font-weight: 700;
  margin-bottom: 20px;
`;

export const SectionInput = styled.View`
  flex: 1;
  padding-bottom: 10px;
`;

export const ButtonForm = styled.TouchableOpacity`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 10px 16px;
  margin-bottom: 20px;

  margin-top: 15px;
  height: 55px;

  background: #5bc4bc;

  border: 1px solid #5bc4bc;
  border-radius: 6px;
`;
export const LabelFile = styled.Text`
  color: ${COLOR.BLACK};
  text-align: center;
  font-size: 15px;
  font-family: Inter;
  font-style: normal;
  font-weight: 500;
`;

export const Label = styled.Text`
  color: ${COLOR.WHITE};
  text-align: center;
  font-size: 22px;
  font-family: Inter;
  font-style: normal;
  font-weight: 500;
`;

export const LabelDropdown = styled.Text`
  color: ${COLOR.BLACK_INPUT};
  font-size: 22px;
  font-family: Inter;
  font-style: normal;
  font-weight: 400;
  margin-bottom: 10px;
`;

export const stylesDropdown = StyleSheet.create({
  dropdown2BtnTxtStyle: { color: "#444", textAlign: "left" },
  dropdown2DropdownStyle: { backgroundColor: "#EFEFEF" },
  dropdown2RowStyle: {
    backgroundColor: "#EFEFEF",
    borderBottomColor: "#C5C5C5",
  },
  dropdown2RowTxtStyle: { color: "#444", textAlign: "left" },
});
