import React, { useState, ReactChild, ReactNode } from 'react';
import styled from 'styled-components';

import Arrow from './Arrow';
import TooltipWrapper from './TooltipWrapper';
import Bubble from './Bubble';

const Container = styled.div`
    position: relative;
    display: inline-block;
`;

const Tooltip = ({
    behavior = 'hover',
    arrowWidth = 8,
    background = '',
    border = '',
    children = null,
    color = '',
    content,
    fadeDuration = 150,
    fadeEasing = 'linear',
    fixed = false,
    fontFamily = 'inherit',
    fontSize = '',
    offset = 0,
    padding = 0.7,
    placement = 'top',
    radius = 0,
    zIndex = 1,
}) => {
    const [isOpen, setIsOpen] = useState(false);

    const showTooltip = () => setIsOpen(true);

    const hideTooltip = () => setIsOpen(false);

    const tooltipElement = (
        <TooltipWrapper
            isOpen={!children || fixed ? true : isOpen}
            placement={placement}
            offset={offset + arrowWidth}
            zIndex={zIndex}
            fadeEasing={fadeEasing}
            fadeDuration={fadeDuration}
        >
            <Bubble
                background={background}
                border={border}
                color={color}
                radius={radius}
                fontFamily={fontFamily}
                fontSize={fontSize}
                padding={padding}
            >
                <Arrow
                    width={arrowWidth}
                    background={background}
                    border={border}
                    placement={placement}
                />
                {content}
            </Bubble>
        </TooltipWrapper>
    );

    return (
        <Container
            onClick={behavior === 'click'}
            onMouseEnter={behavior === 'hover' && !fixed ? showTooltip : undefined}
            onMouseLeave={behavior === 'hover' && !fixed ? hideTooltip : undefined}
        >
            <>
                {children}
                {tooltipElement}
            </>
        </Container>
    );
};

export default Tooltip;
