import React from 'react';
import { useField, Field } from 'formik';
import styled from 'styled-components';
import colors from '../theme/colors';

const StyledField = styled(Field)`
    width: 100%;
    color: ${({ theme }) => theme.palette.main};
    background: ${colors.white};
    /* margin-bottom: 15px; */
    padding: 0.375em 0.75em;
    border: 1px solid ${colors.gray};
    border-radius: 0.188em;
    outline: none;
    &:focus {
        border-bottom: 1px solid ${({ theme }) => theme.palette.main};
    }
    padding-left: ${({ icon }) => (icon ? '2rem !important' : null)};
`;

const StyledErrorMessage = styled.div`
    /* margin-bottom: 5px; */
    text-align: start;
    font-size: 14px;
    color: red;
    &:before {
        content: '❌ ';
        font-size: 10px;
    }
`;

const InputWrapper = styled.div`

    width: 100%;
    position: relative;
    margin-bottom: 7px;
`;

const IconContainer = styled.div`
    position: absolute;
    color: ${colors.darkGray};
    left: 0.5rem;
    top: 20%;

    padding-right: 4px;
    border-right: 1px solid ${(props) => props.theme.palette.main};
`;

const Label = styled.label`
    display: block;
    color: white;
    text-align: start;
`;

const FormikTextInput = ({ icon, label, ...props }) => {
    const [field, meta] = useField(props);
    const { id, name } = props;
    const { touched, error } = meta;
    return (
        <>
            <Label htmlFor={id || name}>{label}</Label>
            <InputWrapper>
                <IconContainer>{icon}</IconContainer>
                <StyledField icon={icon} {...field} {...props} />
                {touched && error ? (
                    <StyledErrorMessage>{error}</StyledErrorMessage>
                ) : null}
            </InputWrapper>
        </>
    );
};

export default FormikTextInput;
