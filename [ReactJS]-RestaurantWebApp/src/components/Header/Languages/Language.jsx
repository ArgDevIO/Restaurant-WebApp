import React from 'react';
import styled from 'styled-components';
import colors from '../../../theme/colors';

export const StyledLanguage = styled.span`
    color: ${colors.gray};
    font-size: 20px;
    text-decoration: none;
    font-weight: ${(props) => props.theme.weights.bold};
`;

const Language = ({ language }) => <StyledLanguage>{language}</StyledLanguage>;

export default Language;
