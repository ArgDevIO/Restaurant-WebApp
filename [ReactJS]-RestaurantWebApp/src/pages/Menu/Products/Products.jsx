import React from 'react';
import styled from 'styled-components';
import uuid from 'uuid/dist/v4';
import MenuBackground from '../../../assets/images/food-eggs-breakfast-fried-egg-egg-meal-614325-wallhere.com.png';
import Product from './Product';
import { getBagProducts } from '../../../redux/bag/reducer';
import { connect } from 'react-redux';

const ProductsContainer = styled.div`
    /* grid-area: products; */
    display: grid;
    align-content: flex-start;
    width: 100%;
    background: url(${MenuBackground}) no-repeat center;
    background-size: cover;
    border-radius: 10px;
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
    /* border: 2px solid ${(props) => props.theme.palette.main}; */
    grid-gap: 20px;
    padding: 20px;
    overflow-y: scroll;
    height: 760px;
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

const Products = ({ products, bagItems }) => {
    return (
        <ProductsContainer>
            {products
                ? products.map((product) => {
                      const disabledButton = bagItems.some(
                          (bagItem) => bagItem.name === product.name
                      );
                      return (
                          <Product
                              disabledButton={disabledButton}
                              key={uuid()}
                              product={product}
                          />
                      );
                  })
                : null}
        </ProductsContainer>
    );
};

const mapStateToProps = (state) => ({
    bagItems: getBagProducts(state),
});

export default connect(mapStateToProps, null)(Products);
