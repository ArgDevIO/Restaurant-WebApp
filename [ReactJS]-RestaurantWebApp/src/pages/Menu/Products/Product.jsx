import React, { useState } from 'react';
import styled from 'styled-components';
import { LineBreak } from '../../../components/Footer/Footer';
import colors from '../../../../theme/colors';
import { BsHeart } from 'react-icons/bs';
import { FaShoppingBag } from 'react-icons/fa';
import { addProductToBag } from '../../../../redux/bag/actions';
import { connect } from 'react-redux';
import { getIsLoggedIn } from '../../../redux/auth/reducer';
import { useHistory } from 'react-router';
import Button from '../../../components/Button/Button';

const StyledProduct = styled.div`
    color: ${colors.white};
    display: grid;
    padding: 10px;
    grid-template-columns: minmax(300px, 1fr);
    /* width: 320px; */
    height: 263px;
    border-radius: 10px;
    background-color: rgba(17, 21, 36, 0.8);
`;

const ProductName = styled.h2`
    text-align: center;
    text-transform: uppercase;
`;

const Description = styled.p`
    color: ${(props) => props.theme.palette.main};
    font-size: ${(props) => props.theme.sizes.medium};
    text-align: center;
`;

const ServingWrapper = styled.div`
    display: flex;
    justify-content: space-between;
`;

const Serving = styled.p`
    color: white;
`;

const AddToBagButton = styled.button`
    background-color: ${(props) => props.theme.palette.main};
    color: ${colors.white};
    width: 90%;
    height: 30px;
    border-radius: 5px;
    display: flex;
    justify-content: center;
    font-weight: ${(props) => props.theme.weights.bold};
    outline: none;
    border: none;
    font-size: ${(props) => props.theme.sizes.large};
    cursor: pointer;
`;

const FavIcon = styled(BsHeart)`
    border: 1px solid ${(props) => props.theme.palette.main};
    color: ${(props) => props.theme.palette.main};
    border-radius: 5px;
    width: 10%;
    margin-left: 5px;
    padding: 5px;
    box-sizing: content-box;
    cursor: pointer;

    &:hover {
        color: ${colors.white};
    }
`;

const AddToBagAndFavWrapper = styled.div`
    display: flex;
    justify-content: space-between;
`;

const Product = ({ product, addProductToBag, disabledButton, isLoggedIn }) => {
    const handleAddToBag = (product) => {
        // if (!isLoggedIn) {
        //     // history.push('/login');
        //     history.push({
        //         pathname: '/login',
        //         state: { from: '/menu' },
        //     });
        // }
        product.totalPrice = product.price;
        product.quantity = 1;
        addProductToBag(product);
    };

    return (
        <StyledProduct>
            <ProductName>{product.name}</ProductName>
            <LineBreak />
            <Description>{product.description}</Description>
            <LineBreak />
            <ServingWrapper>
                <Serving>Serving</Serving>
                {/* <NumericInput value={0} min={0} max={5} /> */}
            </ServingWrapper>
            <AddToBagAndFavWrapper>
                <Button
                    disabled={disabledButton}
                    fullWidth
                    onClick={() => handleAddToBag(product)}
                >
                    <div>
                        <FaShoppingBag size={18} /> Add to bag
                    </div>
                </Button>
                <span>{product.price} den</span>
                <FavIcon />
            </AddToBagAndFavWrapper>
        </StyledProduct>
    );
};

const mapStateToProps = (state) => ({
    isLoggedIn: getIsLoggedIn(state),
});

const mapDispatchToProps = (dispatch) => ({
    addProductToBag: (productToAdd) => dispatch(addProductToBag(productToAdd)),
});

export default connect(mapStateToProps, mapDispatchToProps)(Product);
