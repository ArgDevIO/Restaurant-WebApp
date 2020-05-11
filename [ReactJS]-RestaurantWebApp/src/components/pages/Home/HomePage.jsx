import React from 'react';
import styled from 'styled-components';

// assets
import MainLogo from '../../../assets/images/GARDEN Restaurant - No Background-01.png';
import colors from '../../../theme/colors';
import SocialMedia from '../../SocialMedia/SocialMedia';
import { LineBreak, Copyright } from '../../Footer/Footer';
import { Link } from 'react-router-dom';

const Container = styled.div`
    display: grid;
    place-items: center;
    /* width: 80%; */
    /* margin: auto; */
`;

const OrderNowButton = styled.button`
    height: 63px;
    width: 290px;
    color: ${colors.white};
    background-color: ${(props) => props.theme.palette.main};
    border-radius: 8px;
    border: none;
    box-shadow: 0px 3px 6px #00000029;
    font-size: ${(props) => props.theme.sizes.h2};
    text-transform: uppercase;
    font-weight: ${(props) => props.theme.weights.bold};
    outline: none;
    cursor: pointer;
`;

const HomePage = () => (
    <Container>
        <img src={MainLogo} alt="Garden logo" />
        <Link to="/menu">
            <OrderNowButton>Order now</OrderNowButton>
        </Link>
        <SocialMedia />
    </Container>
);

export default HomePage;
