import React from 'react';
import uuid from 'uuid/dist/v4';
import styled from 'styled-components';
import { NavLink } from 'react-router-dom';
import colors from '../../../theme/colors';
import Tooltip from '../../Tooltip/Tooltip';

const active = 'active';

const StyledNavItem = styled(NavLink).attrs({ active })`
    color: ${colors.gray};
    font-size: ${(props) => props.theme.sizes.large};
    text-decoration: none;
    text-transform: uppercase;
    font-weight: ${(props) => props.theme.weights.bold};
    &.${active} {
        color: ${(props) => props.theme.palette.main};
    }
`;

const itemOnClickHandler = (e, item) => {
    if (item.disabled) e.preventDefault();
};

const NavItems = ({ items }) =>
    items.map((item) => {
        return item.disabled ? (
            <Tooltip
                placement="bottom"
                content={<button>Log out</button>}
                fontSize="15"
                arrowWidth={0}
            >
                <StyledNavItem
                    onClick={(e) => itemOnClickHandler(e, item)}
                    to={item.to}
                    key={uuid()}
                >
                    {item.navLink}
                </StyledNavItem>
            </Tooltip>
        ) : (
            <StyledNavItem onClick={(e) => itemOnClickHandler(e, item)} to={item.to} key={uuid()}>
                {item.navLink}
            </StyledNavItem>
        );
    });

export default NavItems;
