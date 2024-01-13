import COLOR from "../../styles/colors";
import styled from "styled-components/native";

interface InfoProps {
  status: boolean;
}

const getStatusColor = (status: boolean): string => (status ? "green" : "red");

export const Container = styled.View`
  flex: 1;
  padding: 16px;
`;



export const ItemContainer = styled.View`
  flex: 1;
  flex-direction: row;
  justify-content: space-between;
  background-color: ${COLOR.GRAY_100};
  padding: 16px;
  margin-bottom: 8px;
  border-radius: 8px;
`;

export const InfoContainer = styled.View``;

export const Title = styled.Text`
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 8px;
`;

export const Info = styled.Text`
  font-size: 15px;
`;

export const ImageContent = styled.View`
  align-items: center;
  width: 200px;
  height: 200px;
`;

export const Status = styled.Text<InfoProps>`
  color: ${(props) => getStatusColor(props.status)};
`;
