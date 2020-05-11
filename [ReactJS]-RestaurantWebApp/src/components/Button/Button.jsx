import React from 'react';
import styled from 'styled-components';
import ButtonWrapper from './ButtonWrapper';

const ContentHolder = styled.span`
    padding: 0.2rem;
    grid-gap: 0.2em;
`;

const Content = ({ icon, iconPosition = 'left', children }) => {
    if (iconPosition === 'right') {
        return (
            <ContentHolder>
                {children}
                {icon}
            </ContentHolder>
        );
    }

    return (
        <ContentHolder>
            {icon}
            {children}
        </ContentHolder>
    );
};

const Button = ({ icon, iconPosition, children, disabled, ...rest }) => (
    <ButtonWrapper disabled={disabled} {...rest}>
        <Content icon={icon} iconPosition={iconPosition}>
            {children}
        </Content>
    </ButtonWrapper>
);

export default Button;
