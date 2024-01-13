import COLOR from "../../styles/colors";
import styled from "styled-components/native";

export const Container = styled.SafeAreaView`
  justify-content: center;
  padding: 10px;
`;

export const ListItem = styled.TouchableOpacity`
  flex-direction: row;
  align-items: center;
  justify-content: space-around;
  background-color: ${COLOR.WHITE};

  border-radius: 13px;
  height: 96px;
  margin: 10px;

  border-color: #ddd;
  border-width: 1;
`;

export const ItemText = styled.Text`
  flex-shrink: 0;
  color: ${COLOR.PRIMARY_DARK_PAY};
  font-size: 26px;
  font-family: Inter;
  font-weight: 700;
`;
