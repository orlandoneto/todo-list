import styled from 'styled-components/native';

interface DivProps {
  margin?: number | null;
}

export const Div = styled.View<DivProps>`
  margin: ${({ margin }) => (margin === null ? 0 : `${margin}px`)};
`;
