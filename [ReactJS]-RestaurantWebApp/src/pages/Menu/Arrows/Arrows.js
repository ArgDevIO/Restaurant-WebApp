import styled from 'styled-components';
import colors from '../../../theme/colors';

const Arrow = styled.i`
    border: solid ${colors.white};
    border-width: 0 5px 5px 0;
    display: inline-block;
    padding: 7px;
    text-align: center;
    margin-top: 15px;
`;

export const ArrowUp = styled(Arrow)`
    transform: rotate(-135deg);
`;

export const ArrowDown = styled(Arrow)`
    transform: rotate(45deg);
`;
