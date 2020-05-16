import React from 'react';
import styled from 'styled-components';
import colors from '../../theme/colors';
import { GardenColorSpan } from '../../pages/Contact/Contact';
import { FaHeart } from 'react-icons/fa';

export const LineBreak = styled.div`
    border-bottom: 2px solid ${(props) => props.theme.palette.main};
    width: 100%;
    margin-top: ${(props) => `${props.top}px` || 0};
    margin-bottom: ${(props) => `${props.bottom}px` || `0px`};
`;

const CopyrightStyled = styled.p`
    color: ${colors.white};
`;

const HyperLink = styled.a`
    text-decoration: none;
    span:hover {
        color: ${colors.emerald};
    }
`;

const FooterHeart = styled(FaHeart)`
    color: ${(props) => props.theme.palette.main};
`;

export const Copyright = () => (
    <CopyrightStyled>
        Copyright ©2020 All rights reserved | Made with <FooterHeart /> by
        &nbsp;
        <HyperLink href="https://github.com/ArgDevIO">
            <GardenColorSpan bold>ArgDevIO </GardenColorSpan>
        </HyperLink>
        &
        <HyperLink href="https://github.com/mustran">
            <GardenColorSpan bold> mustran</GardenColorSpan>
        </HyperLink>
    </CopyrightStyled>
);

const StickyFooter = styled.div`
    flex-shrink: 0;
    justify-self: end;
`;

const Footer = () => (
    <StickyFooter>
        <LineBreak top={10} bottom={20} />
        <Copyright />
    </StickyFooter>
);

export default Footer;
