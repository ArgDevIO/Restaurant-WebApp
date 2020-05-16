import React from 'react';
import styled, { css, keyframes } from 'styled-components';

const fadeAnimation = keyframes`
    0% {
        opacity: 0;
    }
    100% {
        opacity: 1;
    }
`;

const animation = ({ fadeDuration, fadeEasing }) => css`
    animation: ${fadeDuration}ms ${fadeEasing} 0s 1 ${fadeAnimation};
`;

const Base = styled.div`
    position: absolute;
    width: max-content;
    white-space: pre-line;
    ${(props) => props.fadeDuration && props.fadeDuration > 0 && animation(props)};
    ${(props) => props.zIndex && `z-index: ${props.zIndex};`}
`;

const Top = styled(Base)`
    bottom: 100%;
    left: 50%;
    transform: translateX(-50%);
    margin-bottom: ${(props) => props.offset}px;
`;

const Bottom = styled(Base)`
    top: 100%;
    left: 50%;
    transform: translateX(-50%);
    margin-top: ${(props) => props.offset}px;
`;

const Left = styled(Base)`
    right: 100%;
    top: 50%;
    transform: translateY(-50%);
    margin-right: ${(props) => props.offset}px;
`;

const Right = styled(Base)`
    left: 100%;
    top: 50%;
    transform: translateY(-50%);
    margin-left: ${(props) => props.offset}px;
`;

const toolTips = {
    left: Left,
    top: Top,
    right: Right,
    bottom: Bottom,
};

const TooltipWrapper = ({
    children,
    offset,
    isOpen,
    placement,
    zIndex,
    fadeDuration,
    fadeEasing,
}) => {
    const Component = toolTips[placement] || toolTips.top;

    if (!isOpen) {
        return null;
    }

    return (
        <Component
            offset={offset}
            zIndex={zIndex}
            fadeDuration={fadeDuration}
            fadeEasing={fadeEasing}
        >
            {children}
        </Component>
    );
};

export default TooltipWrapper;
