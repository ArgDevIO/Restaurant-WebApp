import React from 'react';
import styled from 'styled-components';
import ActiveCategoryLogo from '../../../../assets/images/breakfast_icon.svg';
import CategoryLogo from '../../../../assets/images/breakfast-icon-png.png';
import colors from '../../../../theme/colors';
import { fetchDynamicProducts } from '../../../../redux/products/actions';
import { connect } from 'react-redux';

const SidebarLogo = styled.img`
    height: 60px;
    width: 60px;
`;

const Category = styled.p`
    color: ${colors.white};
    font-weight: ${(props) => props.theme.weights.bold};
`;

const ListItemsContainer = styled.li`
    cursor: pointer;
    display: grid;
    place-items: center;
`;

const SidebarItem = ({
    category,
    getProducts,
    setActiveCategory,
    active,
}) => {
    const handleOnClick = (product) => {
        getProducts(product);
        setActiveCategory();
    };

    return (
        <ListItemsContainer onClick={() => handleOnClick(category)}>
            <SidebarLogo
                src={active ? ActiveCategoryLogo : CategoryLogo}
                alt={category}
            />
            <Category>{category}</Category>
        </ListItemsContainer>
    );
};

const mapDispatchToProps = (dispatch) => ({
    getProducts: (categoryType) => dispatch(fetchDynamicProducts(categoryType)),
});

export default connect(null, mapDispatchToProps)(SidebarItem);
