import React from 'react';
import styled from 'styled-components';
import colors from '../../theme/colors';
import { useState } from 'react';
import { increaseProductQuantity, decreaseProductQuantity } from '../../redux/bag/actions';
import { connect } from 'react-redux';

const NumericContainer = styled.div`
    position: relative;
`;

const NumericInputStyled = styled.input`
    position: relative;
    margin-right: 15px;
    left: 10px;
    /* top: 10px; */
    width: 55px;
    height: 25px;
    padding: 0px;
    font-size: ${(props) => props.theme.sizes.medium};
    border: 2px solid ${({ theme, color }) => color || theme.palette.main};
    z-index: 1;
    text-align: start;
    background-color: rgba(0, 0, 0, 0);
    padding-left: 22px;
    padding-top: 2px;
    color: ${colors.white};
    border-radius: 5px;
    -moz-appearance: textfield;
    ::-webkit-outer-spin-button,
    ::-webkit-inner-spin-button {
        -webkit-appearance: none;
    }
`;

const SpinnerButton = styled.div`
    position: absolute;
    cursor: pointer;
    z-index: 2;
    background-color: #ccc;
    width: 14.5px;
    text-align: center;
    margin: 0px;
    /* pointer-events: none; */
    height: 10px;
    line-height: 10px;
    color: ${colors.white};
    background-color: rgba(0, 0, 0, 0);
    ${(props) =>
        props.inc
            ? `
        left: 46px;
        top: 8.5px;
    `
            : `
        left: 15.5px;
        top: 7.5px
    `};
`;

const NumericInput = ({
    value,
    min,
    max,
    bagItem,
    increaseProductQuantity,
    decreaseProductQuantity,
    color,
}) => {
    const [inputValue, setInputValue] = useState(value);

    const handleInc = (inputValue) => {
        if (bagItem.currentQuantity >= max) return;
        setInputValue(inputValue + 1);
        const productChanged = JSON.parse(JSON.stringify(bagItem));
        productChanged.quantity += 1;
        increaseProductQuantity(productChanged);
    };

    const handleDec = (inputValue) => {
        if (bagItem.currentQuantity <= min) return;
        setInputValue(inputValue - 1);
        const productChanged = JSON.parse(JSON.stringify(bagItem));
        productChanged.quantity -= 1;
        decreaseProductQuantity(productChanged);
    };

    return (
        <NumericContainer>
            <NumericInputStyled
                color={color}
                type="number"
                value={bagItem.currentQuantity}
                min={min}
                max={max}
                onChange={() => null}
            />
            <SpinnerButton onClick={() => handleInc(inputValue)} inc>
                +
            </SpinnerButton>
            <SpinnerButton onClick={() => handleDec(inputValue)} dec>
                –
            </SpinnerButton>
        </NumericContainer>
    );
};

const mapDispatchToProps = (dispatch) => ({
    increaseProductQuantity: (product) => dispatch(increaseProductQuantity(product)),
    decreaseProductQuantity: (product) => dispatch(decreaseProductQuantity(product)),
});

export default connect(null, mapDispatchToProps)(NumericInput);
