import styled from 'styled-components/native';

import COLOR from '../../../styles/colors';

export const Icons = styled.Image`
    padding: 10px;
    margin: 5px;
    height: 25px;
    width: 25px;
    align-items: center;
`;

export const SectionTextInput = styled.View`
    flex-direction: row;
    justify-content: center;
    align-items: center;

    min-height: 50px;
    max-height: 50px;
    height: 50px;

    border: 2px solid #d9d9d9;
    border-radius: 6px;

    color: ${COLOR.BLACK};
`;

export const Label = styled.Text`
    font-family: 'Inter';
    color: #141920;
    font-size: 22px;
    margin-bottom: 8px;
`;

export const Text = styled.TextInput`
    flex: 1;

    font-family: 'Inter';
    font-weight: 400;
    font-size: 22px;
    line-height: 26.63px;

    color: ${COLOR.BLACK_INPUT};
`;

export const TextSimple = styled.TextInput`
    flex: 1;

    min-height: 50px;
    max-height: 50px;
    height: 50px;

    border: 2px solid #d9d9d9;
    border-radius: 6px;

    color: ${COLOR.BLACK_INPUT};
`;
