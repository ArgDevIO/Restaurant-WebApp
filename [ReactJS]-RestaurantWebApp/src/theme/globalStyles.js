import { createGlobalStyle } from 'styled-components';
import { normalize } from 'polished';

const GlobalStyle = createGlobalStyle`
    ${normalize()};

    html,
    body{
        font-family: ${(props) => props.theme.font.primary};
    }

    * {
        box-sizing: border-box;
    }
`;

export default GlobalStyle;
