import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import Languages from './Languages/Languages';
import GardenLogoHorizontal from '../../assets/images/garden_logo_horizontal.svg';
import NavItems from './NavItems/NavItems';
import { connect } from 'react-redux';
import { getUser } from '../../redux/auth/reducer';

const LogoStyled = styled.img`
    height: 60px;
    width: 255px;
`;

const HeaderWrapper = styled.div`
    padding-top: 10px;
    display: flex;
    justify-content: space-between;
    margin-top: 18px;
    /* width: 100%; */
    margin: auto;

    /* centering bug */
    @media (max-width: 820px) {
        display: grid;
        place-items: center;
        /* background-color: red; */
    }
`;

const NavLinksWrapper = styled.div`
    display: grid;
    grid-auto-flow: column;
    grid-gap: 15px;
    place-items: center;
`;

const Header = ({ user }) => {
    const languages = [{ language: 'AL' }, { language: 'MK' }, { language: 'EN' }];

    const navLinks = [
        { navLink: 'Menu', to: '/menu' },
        { navLink: 'Contact', to: '/contact' },
        {
            navLink: user.fullName || 'Log In',
            to: '/login',
            disabled: user.fullName ? true : false,
        },
    ];

    return (
        <HeaderWrapper>
            <Link to="/">
                <LogoStyled src={GardenLogoHorizontal} alt="Garden Logo" />
            </Link>
            <NavLinksWrapper>
                <Languages languages={languages} />
                <NavItems items={navLinks} />
            </NavLinksWrapper>
        </HeaderWrapper>
    );
};

const mapStateToProps = (state) => ({
    user: getUser(state),
});

export default connect(mapStateToProps, null)(Header);
