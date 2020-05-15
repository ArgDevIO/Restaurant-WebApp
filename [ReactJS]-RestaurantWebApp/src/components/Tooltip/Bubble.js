import styled from 'styled-components';
import colors from '../../theme/colors';

const Bubble = styled.div`
    color: ${colors.white};
    background: ${({ background, theme }) => background || theme.palette.main};
    border-radius: ${({ radius }) => (radius ? `${radius}px` : '0.3em')};
    border: ${({ border, background, theme }) =>
        border
            ? `2px solid ${border};`
            : background
            ? `2px solid ${background};`
            : theme.palette.main};
    padding: ${({ padding }) => `${padding}rem` || 0};
    font-size: ${({ fontSize }) => fontSize || `25px;`};
    font-family: ${({ fontFamily, theme }) => fontFamily || theme.font.primary};
    line-height: 1.4;
`;

export default Bubble;