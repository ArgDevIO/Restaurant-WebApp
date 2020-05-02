import React from 'react';
import styled from 'styled-components';
import uuid from 'uuid/dist/v4';
import MenuBackground from '../../../../assets/images/food-eggs-breakfast-fried-egg-egg-meal-614325-wallhere.com.png';
import Product from './Product';

const ProductsContainer = styled.div`
    display: grid;
    align-content: flex-start;
    width: 100%;
    background: url(${MenuBackground}) no-repeat center;
    background-size: cover;
    border-radius: 10px;
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(320px, 1fr));
    /* border: 2px solid ${(props) => props.theme.palette.main}; */
    grid-gap: 20px;
    padding: 20px;
    overflow-y: scroll;
    height: 805px;
    /* IE10+ */
    -ms-overflow-style: none;
    /* Firefox */
    scrollbar-width: none;
    /* Chrome */
    &::-webkit-scrollbar {
        display: none;
    };

    box-shadow: -1px -1px 11px 6px rgba(177,224,201,0.37);
`;

const Products = ({ products }) => (
    <ProductsContainer>
        {products.map((p) => (
            <Product
                key={uuid()}
                productName={p.product}
                description={p.description}
            />
        ))}
    </ProductsContainer>
);

export default Products;
