import styled, { css } from 'styled-components';
import colors from '../../theme/colors';

const ButtonWrapper = styled.button`
    display: grid;
    place-items: center;
    background: ${(props) => props.color || props.theme.palette.main};
    color: ${colors.white};
    font-weight: ${({ theme }) => theme.weights.light};
    border: 1px solid ${(props) => props.color || props.theme.palette.main};
    border-radius: 5px;
    font-size: ${({ theme }) => theme.sizes.medium};
    cursor: pointer;
    letter-spacing: 0.02em;
    outline: none;
    height: 37px;
    ${(props) => (props.fullWidth ? `width: 100%;` : '')};
    &:hover {
        border-color: ${colors.gray};
        color: ${colors.gray};
    }
    ${(props) =>
        props.disabled
            ? css`
      color: ${({ theme }) => theme.palette.text.light}
      background-color: ${colors.lightGray};
      border-color: ${colors.gray};
      pointer-events: none;
    `
            : ''}
`;

export default ButtonWrapper;
