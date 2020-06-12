import React from 'react';
import styled from 'styled-components';
// assets
import FacebookLogo from '../../assets/images/facebook_logo.svg';
import InstagramLogo from '../../assets/images/insta logo.png';
import { LineBreak } from '../Footer/Footer';

const SocialMediaContainer = styled.div`
    height: 200px;
    width: 70%;
    display: grid;
    place-items: end center;
    padding-bottom: 20px;
`;

const SocialMediaWrapper = styled.span`
    display: grid;
    grid-auto-flow: column;
    grid-gap: 30px;
`;

const SocialMedia = () => (
    <SocialMediaContainer>
        <SocialMediaWrapper>
            <img src={FacebookLogo} alt="Facebook" />
            {/* upload instagram logo to svg */}
            <img src={InstagramLogo} alt="Instagram" />
        </SocialMediaWrapper>
    </SocialMediaContainer>
);

export default SocialMedia;
