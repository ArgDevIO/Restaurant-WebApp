import React from 'react';
import uuid from 'uuid/dist/v4';
import styled from 'styled-components';
import { NavLink } from 'react-router-dom';
import colors from '../../../theme/colors';

const active = 'active';

const StyledNavItem = styled(NavLink).attrs({ active })`
    color: ${colors.gray};
    font-size: ${(props) => props.theme.sizes.large};
    text-decoration: none;
    text-transform: uppercase;
    margin-left: 20px;
    font-weight: ${(props) => props.theme.weights.bold};
    &.${active} {
        color: ${(props) => props.theme.palette.main};
    }
`;

const NavItems = ({ items }) =>
    items.map((item) => (
        <StyledNavItem to={item.to} key={uuid()}>
            {item.navLink}
        </StyledNavItem>
    ));

export default NavItems;
