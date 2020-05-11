import React from 'react';
import { ThemeProvider } from 'styled-components';
import GlobalStyle from './globalStyles';

const ThemeWrapper = ({ children, theme }) => {
    const appTheme = theme;

    return (
        <ThemeProvider theme={appTheme}>
            <GlobalStyle suppressMultiMountWarning />
            {children}
        </ThemeProvider>
    );
};

export default ThemeWrapper;
