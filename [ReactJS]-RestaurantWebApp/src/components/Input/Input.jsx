import React, { forwardRef } from 'react';
import styled from 'styled-components';
import colors from '../../theme/colors';

const StyledInput = styled.input`
    width: 100%;
    color: ${({ theme }) => theme.palette.main};
    background: ${colors.white};
    margin-bottom: 15px;
    padding: 0.375em 0.75em;
    border: 1px solid ${colors.gray};
    border-radius: 0.188em;
    &:focus {
        border-bottom: 1px solid ${colors.skyBlue};
    }
    padding-left: ${({ icon }) => (icon ? '2rem !important' : null)};
`;

const IconContainer = styled.div`
    position: absolute;
    color: ${colors.darkGray};
    left: 0.5rem;
    top: 18%;

    padding-right: 4px;
    border-right: 1px solid ${(props) => props.theme.palette.main};
`;

const InputWrapper = styled.div`
    width: 100%;
    position: relative;
`;

const Input = forwardRef(
    ({ type, placeholder, onChange, sizeProp, icon, ...rest }, ref) => {
        const onInputChange = (e) => {
            e.persist();
            if (onChange) {
                onChange(e);
            }
        };

        return (
            <InputWrapper>
                <IconContainer>{icon}</IconContainer>
                <StyledInput
                    sizeProp={sizeProp}
                    ref={ref}
                    type={type || 'text'}
                    placeholder={placeholder}
                    onChange={onInputChange}
                    icon={icon}
                    {...rest}
                />
            </InputWrapper>
        );
    }
);

Input.displayName = 'Input';

Input.defaultProps = {
    sizeProp: 'md',
    icon: null,
};

export default Input;
