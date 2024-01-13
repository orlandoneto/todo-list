import styled from "styled-components/native";

interface ProgressBarContentProps {
  width: number;
  progressBorder: string;
  progressBgColor: string;
}

interface LabelProps {
  progressInfoColor: string;
}

export const Container = styled.View`
  margin-top: 25px;
  width: 300px;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding-top: 10px;
`;

export const ProgressBarContent = styled.View<ProgressBarContentProps>`
  height: 30px;
  width: ${(props) => props.width}%;
  flex-direction: row;
  background-color: ${(props) => props.progressBgColor};
  border-color: ${(props) => props.progressBorder};
  border-width: 3px;
  border-radius: 100px;
`;

export const Label = styled.Text<LabelProps>`
  color: ${(props) => props.progressInfoColor};
  text-align: center;
  font-size: 22px;
  font-family: Inter;
  font-weight: 500;
`;
