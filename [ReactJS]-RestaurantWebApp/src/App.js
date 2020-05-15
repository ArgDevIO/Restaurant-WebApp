import React from 'react';
import styled from 'styled-components';
import { Switch, Route } from 'react-router-dom';
import Header from './components/Header/Header';
import HomePage from './components/pages/Home/HomePage';
import Menu from './components/pages/Menu/Menu';
import Footer from './components/Footer/Footer';
import Contact from './components/pages/Contact/Contact';
import ProtectedRoute from './routes/ProtectedRoute';
import { getIsLoggedIn } from './redux/auth/reducer';
import { connect } from 'react-redux';
import LogIn from './components/pages/LogIn/LogIn';
import Register from './components/pages/Register/Register';

const AppWrapper = styled.div`
    width: 80%;
    margin: auto;
    text-align: center;
    display: flex;
    flex-flow: column;
    height: 100vh;
`;

const ContentWrapper = styled.div`
    flex: 1 0 auto;
`;

const PageNotFound = () => <div>Page not found</div>;

const App = ({ isLoggedIn }) => {
    return (
        <AppWrapper>
            <ContentWrapper>
                <Header />
                <Switch>
                    <Route exact path="/">
                        <HomePage />
                    </Route>
                    {/* <ProtectedRoute
                        exact
                        path="/menu"
                        // get user from redux
                        authenticated={isLoggedIn}
                        component={Menu}
                    /> */}
                    <Route exact path="/menu">
                        <Menu />
                    </Route>
                    <Route exact path="/contact">
                        <Contact />
                    </Route>
                    <Route
                        exact
                        path="/login"
                        render={(props) => <LogIn authenticated={isLoggedIn} {...props} />}
                    />
                    <Route exact path="/register">
                        <Register />
                    </Route>
                    <Route>
                        <PageNotFound />
                    </Route>
                </Switch>
            </ContentWrapper>
            <Footer />
        </AppWrapper>
    );
};

const mapStateToProps = (state) => ({
    isLoggedIn: getIsLoggedIn(state),
});

export default connect(mapStateToProps, null)(App);
