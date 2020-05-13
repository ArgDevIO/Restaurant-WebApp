import React from 'react';
import styled from 'styled-components';
import Sidebar from './Sidebar/Sidebar';
import { LineBreak, Copyright } from '../../Footer/Footer';
import Product from './Products/Product';
import Products from './Products/Products';
import OrderSection from './Order/OrderSection';
import { getProducts } from '../../../redux/products/reducer';
import { connect } from 'react-redux';
import { loginToggler } from '../../../redux/auth/actions';
import { useEffect } from 'react';
import axios from 'axios';
import { useState } from 'react';
import { getCategories } from '../../../redux/categories/reducer';
import { fetchCategories } from '../../../redux/categories/fetchCategories';
import { fetchProductsByCategory } from '../../../redux/products/fetchProducts';
import { setActiveCategory } from '../../../redux/products/actions';

const Wrapper = styled.div`
    /* background-color: red; */
    display: grid;
    grid-auto-flow: column;
    grid-template-columns: 95px 1fr;
    grid-gap: 20px;

    /* todo */
    /* @media (max-width: 662px) {
        grid-template-columns: 1fr;
        grid-template-rows: 500px 100px 20px;
        grid-template-areas: 
        "products"
        "sidebar"
    } */
`;

const ProductsAndOrderWrapper = styled.div`
    display: grid;
    grid-template-columns: 2fr 1fr;
    grid-gap: 20px;
    @media (max-width: 1016px) {
        display: grid;
        grid-template-rows: 1fr;
        grid-template-columns: 1fr;
        /* background-color: red; */
    }
`;

const Menu = ({ products, categories, fetchCategories }) => {
    useEffect(() => {
        console.log('USE EFFECT HERE');
        fetchCategories();
    }, [fetchCategories]);

    return (
        <>
            <LineBreak top={20} bottom={10} />
            <>
                <Wrapper>
                    <Sidebar categories={categories} />
                    <ProductsAndOrderWrapper>
                        <Products products={products} />
                        <OrderSection />
                    </ProductsAndOrderWrapper>
                </Wrapper>
            </>
        </>
    );
};

const mapStateToProps = (state) => ({
    categories: getCategories(state),
    products: getProducts(state),
});

const mapDispatchToProps = (dispatch) => ({
    fetchCategories: () => dispatch(fetchCategories()),
});

export default connect(mapStateToProps, mapDispatchToProps)(Menu);
